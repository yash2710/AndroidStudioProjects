// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.activity;

import com.flipkart.android.config.FlipkartPreferenceManager;
import com.flipkart.android.datahandler.LoginVDataHandler;
import com.flipkart.android.log.CrashLoggerUtils;
import com.flipkart.android.login.GoogleTokenFetcher;
import com.flipkart.android.login.LoginType;
import com.flipkart.android.response.login.LoginResponse;
import com.flipkart.android.utils.CustomDialog;
import com.flipkart.android.utils.ToastMessageUtils;
import com.flipkart.logging.FkLogger;

// Referenced classes of package com.flipkart.android.activity:
//            LoginActivity

final class v extends LoginVDataHandler
{

    private LoginActivity a;

    v(LoginActivity loginactivity)
    {
        a = loginactivity;
        super();
    }

    public final void errorReceived(int i, int j, String s)
    {
        CrashLoggerUtils.pushAndUpdate((new StringBuilder("login with google failed ")).append(i).toString());
        LoginActivity.j(a);
        ToastMessageUtils.showErrorToastMessage((new StringBuilder("Login Failed.")).append(CustomDialog.getErrorMessage(i)).toString(), a, true);
    }

    public final void resultReceived(LoginResponse loginresponse, boolean flag)
    {
        if (loginresponse.isLoggedIn())
        {
            try
            {
                FlipkartPreferenceManager.instance().saveLastLoginType(LoginType.Google);
                FlipkartPreferenceManager.instance().saveLoginAccessToken(a.a.getAuthToken());
                FlipkartPreferenceManager.instance().saveLoginAccessExpires(0L);
                LoginActivity.h(a);
                LoginActivity.i(a);
                CrashLoggerUtils.pushAndUpdate("login with google succeed");
                return;
            }
            catch (Exception exception)
            {
                FkLogger.debug(LoginActivity.a(), (new StringBuilder("Exception while logging in.")).append(exception.getMessage()).toString());
            }
            LoginActivity.j(a);
            ToastMessageUtils.showErrorToastMessage("Login Failed.Please try again. ", a, true);
            return;
        }
        String s = loginresponse.getMessage();
        if (s.equalsIgnoreCase("null"))
        {
            s = "";
        }
        LoginActivity.j(a);
        ToastMessageUtils.showErrorToastMessage((new StringBuilder("Login Failed. ")).append(s).toString(), a, true);
        CrashLoggerUtils.pushAndUpdate((new StringBuilder("login with google failed ")).append(s).toString());
    }

    public final volatile void resultReceived(Object obj, boolean flag)
    {
        resultReceived((LoginResponse)obj, flag);
    }
}
