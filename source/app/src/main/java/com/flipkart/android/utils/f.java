// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.utils;

import android.app.Activity;
import com.flipkart.android.activity.HomeFragmentHolderActivity;
import com.flipkart.android.datahandler.InAppNotificationDataHandler;
import com.flipkart.android.response.inAppNotification.InAppNotificationResponse;

final class f extends InAppNotificationDataHandler
{

    private Activity a;

    f(Activity activity)
    {
        a = activity;
        super();
    }

    public final void resultReceived(InAppNotificationResponse inappnotificationresponse, boolean flag)
    {
        try
        {
            if (a != null && !a.isFinishing())
            {
                ((HomeFragmentHolderActivity)a).updateInAppNotificationCount();
            }
            return;
        }
        catch (Exception exception)
        {
            return;
        }
    }

    public final volatile void resultReceived(Object obj, boolean flag)
    {
        resultReceived((InAppNotificationResponse)obj, flag);
    }
}
