// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.activity;

import com.flipkart.android.config.FlipkartPreferenceManager;
import com.flipkart.android.datahandler.LoginVDataHandler;
import com.flipkart.android.log.CrashLoggerUtils;
import com.flipkart.android.login.LoginType;
import com.flipkart.android.response.login.LoginResponse;
import com.flipkart.android.utils.CustomDialog;
import com.flipkart.android.utils.ToastMessageUtils;
import com.flipkart.logging.FkLogger;

// Referenced classes of package com.flipkart.android.activity:
//            LoginActivity

final class t extends LoginVDataHandler
{

    private LoginActivity a;

    t(LoginActivity loginactivity)
    {
        a = loginactivity;
        super();
    }

    public final void errorReceived(int i, int j, String s)
    {
        CrashLoggerUtils.pushAndUpdate((new StringBuilder("direct login failed ")).append(i).toString());
        LoginActivity.j(a);
        ToastMessageUtils.showErrorToastMessage((new StringBuilder("Login Failed.")).append(CustomDialog.getErrorMessage(i)).toString(), a, true);
    }

    public final void resultReceived(LoginResponse loginresponse, boolean flag)
    {
        if (loginresponse == null)
        {
            break MISSING_BLOCK_LABEL_54;
        }
        if (!loginresponse.isLoggedIn())
        {
            break MISSING_BLOCK_LABEL_102;
        }
        FlipkartPreferenceManager.instance().saveLastLoginType(LoginType.Direct);
        FlipkartPreferenceManager.instance().saveLoginAccessToken("");
        FlipkartPreferenceManager.instance().saveLoginAccessExpires(0L);
        LoginActivity.h(a);
        LoginActivity.i(a);
        CrashLoggerUtils.pushAndUpdate("direct login succeed");
        return;
        Exception exception;
        exception;
        FkLogger.debug(LoginActivity.a(), (new StringBuilder("Exception while logging in.")).append(exception.getMessage()).toString());
        LoginActivity.j(a);
        ToastMessageUtils.showErrorToastMessage("Login Failed.Please try again.", a, true);
        return;
        String s = loginresponse.getMessage();
        if (s.equalsIgnoreCase("null"))
        {
            s = "";
        }
        CrashLoggerUtils.pushAndUpdate((new StringBuilder("direct login failed ")).append(s).toString());
        LoginActivity.j(a);
        ToastMessageUtils.showErrorToastMessage((new StringBuilder("Login Failed.")).append(s).toString(), a, true);
        return;
    }

    public final volatile void resultReceived(Object obj, boolean flag)
    {
        resultReceived((LoginResponse)obj, flag);
    }
}
