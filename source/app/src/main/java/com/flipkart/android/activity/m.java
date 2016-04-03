// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.activity;

import com.facebook.Session;
import com.flipkart.android.config.FlipkartPreferenceManager;
import com.flipkart.android.datahandler.LogoutVDataHandler;
import com.flipkart.android.notification.GcmUtils;
import com.flipkart.android.response.login.LogoutResponse;
import com.flipkart.android.utils.CustomDialog;
import com.flipkart.android.utils.FkLoadingDialog;
import com.flipkart.android.utils.ToastMessageUtils;

// Referenced classes of package com.flipkart.android.activity:
//            HomeFragmentHolderActivity

final class m extends LogoutVDataHandler
{

    private HomeFragmentHolderActivity a;

    m(HomeFragmentHolderActivity homefragmentholderactivity)
    {
        a = homefragmentholderactivity;
        super();
    }

    public final void errorReceived(int i, int j, String s)
    {
        if (HomeFragmentHolderActivity.d(a) != null)
        {
            HomeFragmentHolderActivity.d(a).dismissDlg();
        }
        ToastMessageUtils.showErrorToastMessage((new StringBuilder("Logout failed.")).append(CustomDialog.getErrorMessage(i)).toString(), a, true);
    }

    public final void resultReceived(LogoutResponse logoutresponse, boolean flag)
    {
        if (HomeFragmentHolderActivity.d(a) != null)
        {
            HomeFragmentHolderActivity.d(a).dismissDlg();
        }
        if (!logoutresponse.isLoggedOut())
        {
            break MISSING_BLOCK_LABEL_95;
        }
        Session session;
        Session session1;
        try
        {
            FlipkartPreferenceManager.instance().clearUserSessionVariables();
            HomeFragmentHolderActivity.f(a);
            session = Session.getActiveSession();
        }
        catch (Exception exception)
        {
            return;
        }
        if (session == null) goto _L2; else goto _L1
_L1:
        session.closeAndClearTokenInformation();
_L4:
        GcmUtils.sendRegistrationIdToBackend("logout");
        a.loadHomeFragment();
        return;
_L2:
        session1 = Session.openActiveSession(a, false, null);
        if (session1 == null) goto _L4; else goto _L3
_L3:
        session1.closeAndClearTokenInformation();
          goto _L4
        ToastMessageUtils.showErrorToastMessage("Logout failed. Please try again", a, true);
        return;
    }

    public final volatile void resultReceived(Object obj, boolean flag)
    {
        resultReceived((LogoutResponse)obj, flag);
    }
}
