// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.utils.share;

import android.app.Activity;
import com.flipkart.android.activity.HomeFragmentHolderActivity;
import com.flipkart.android.analytics.TrackingHelper;
import com.flipkart.android.datahandler.ShareTheAppDataHandler;
import com.flipkart.android.response.shareapp.ShareAppResponse;
import com.flipkart.android.utils.CustomDialog;

final class a extends ShareTheAppDataHandler
{

    private Activity a;
    private String b;

    a(Activity activity, String s)
    {
        a = activity;
        b = s;
        super();
    }

    public final void errorReceived(int i, int j, String s)
    {
        CustomDialog.showAlertMessage("Thanks for sharing!", "Some error happened. Pls try again next time.", false, a);
    }

    public final void resultReceived(ShareAppResponse shareappresponse, boolean flag)
    {
        if (a != null)
        {
            if (a instanceof HomeFragmentHolderActivity)
            {
                ((HomeFragmentHolderActivity)a).purgeAndPushOrderWebView(b);
            }
            CustomDialog.showAlertMessage("Thanks for sharing!", shareappresponse.getMessage(), shareappresponse.isStatus(), a);
            if (shareappresponse.isStatus())
            {
                TrackingHelper.sendShareConfirmed();
            }
        }
    }

    public final volatile void resultReceived(Object obj, boolean flag)
    {
        resultReceived((ShareAppResponse)obj, flag);
    }
}
