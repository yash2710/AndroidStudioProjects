// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.notification;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.flipkart.android.utils.StringUtils;

// Referenced classes of package com.flipkart.android.notification:
//            FlipkartNotificationManager

public class NotificationDeleteIntentReceiver extends BroadcastReceiver
{

    public static final String NOTIFICATION_DELETE_ACTION = "notification_delete_action";

    public NotificationDeleteIntentReceiver()
    {
    }

    public void onReceive(Context context, Intent intent)
    {
        if (intent.getAction().equals("notification_delete_action"))
        {
            String s = intent.getStringExtra("extras_notification_id");
            if (!StringUtils.isNullOrEmpty(s))
            {
                FlipkartNotificationManager.sendNotificationDismissedEvent(s);
            }
        }
    }
}
