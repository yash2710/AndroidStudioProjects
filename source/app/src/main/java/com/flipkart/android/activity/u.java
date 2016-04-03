// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.activity;

import com.facebook.Session;
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

final class u extends LoginVDataHandler
{

    private String a;
    private LoginActivity b;

    u(LoginActivity loginactivity, String s)
    {
        b = loginactivity;
        a = s;
        super();
    }

    public final void errorReceived(int i, int j, String s)
    {
        Session session = Session.getActiveSession();
        if (session != null)
        {
            session.closeAndClearTokenInformation();
        }
        CrashLoggerUtils.pushAndUpdate((new StringBuilder("login with facebook failed ")).append(i).toString());
        LoginActivity.j(b);
        ToastMessageUtils.showErrorToastMessage((new StringBuilder("Login Failed.")).append(CustomDialog.getErrorMessage(i)).toString(), b, true);
    }

    public final void resultReceived(LoginResponse loginresponse, boolean flag)
    {
        if (loginresponse.isLoggedIn())
        {
            try
            {
                FlipkartPreferenceManager.instance().saveLastLoginType(LoginType.Facebook);
                FlipkartPreferenceManager.instance().saveLoginAccessToken(a);
                FlipkartPreferenceManager.instance().saveLoginAccessExpires(0L);
                LoginActivity.h(b);
                LoginActivity.i(b);
                CrashLoggerUtils.pushAndUpdate("login with facebook succeed");
                return;
            }
            catch (Exception exception)
            {
                FkLogger.debug(LoginActivity.a(), (new StringBuilder("Exception while logging in.")).append(exception.getMessage()).toString());
            }
            LoginActivity.j(b);
            ToastMessageUtils.showErrorToastMessage("Login Failed.Please try again.", b, true);
            return;
        }
        String s = loginresponse.getMessage();
        Session session = Session.getActiveSession();
        if (session != null)
        {
            session.closeAndClearTokenInformation();
        }
        if (s.equalsIgnoreCase("null"))
        {
            s = "";
        }
        CrashLoggerUtils.pushAndUpdate((new StringBuilder("login with facebook failed ")).append(s).toString());
        LoginActivity.j(b);
        ToastMessageUtils.showErrorToastMessage((new StringBuilder("Login Failed.")).append(s).toString(), b, true);
    }

    public final volatile void resultReceived(Object obj, boolean flag)
    {
        resultReceived((LoginResponse)obj, flag);
    }
}
