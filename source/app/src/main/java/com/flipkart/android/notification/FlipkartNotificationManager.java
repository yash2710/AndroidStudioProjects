// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.notification;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import com.flipkart.android.activity.HomeFragmentHolderActivity;
import com.flipkart.android.config.FlipkartDeviceInfoProvider;
import com.flipkart.android.config.FlipkartPreferenceManager;
import com.flipkart.android.datahandler.NotificationVDataHandler;
import com.flipkart.android.init.FlipkartApplication;
import com.flipkart.android.notification.data.NotificationDataPacket;
import com.flipkart.android.notification.data.NotificationType;
import com.flipkart.android.notification.data.pulldown.NotificationAction;
import com.flipkart.android.notification.data.pulldown.NotificationPayload;
import com.flipkart.android.utils.ImageUtils;
import com.flipkart.android.utils.StringUtils;
import com.google.mygson.Gson;
import com.newrelic.agent.android.instrumentation.AsyncTaskInstrumentation;
import java.util.ArrayList;
import java.util.Iterator;

// Referenced classes of package com.flipkart.android.notification:
//            NotificationDeleteIntentReceiver, e, d, c, 
//            a, b

public class FlipkartNotificationManager
{

    public static final String EXTRAS_NOTIFICATION_ID = "extras_notification_id";
    public static final String NOTIFICATION_SCREEN_TYPE = "notification";
    private static Context a = FlipkartApplication.getAppContext();

    public FlipkartNotificationManager()
    {
    }

    private static PendingIntent a(String s, String s1, String s2)
    {
        Intent intent = new Intent(a, com/flipkart/android/activity/HomeFragmentHolderActivity);
        intent.putExtra("HOME_ACTIVITY_EXTRAS_FROM_SCREEN", "notification");
        intent.putExtra("actionJson", s);
        intent.putExtra("omniture", s1);
        intent.putExtra("extras_notification_id", s2);
        if (FlipkartDeviceInfoProvider.getAndroidSDKVersion() > 10)
        {
            intent.setFlags(0x20008000);
        } else
        {
            intent.setFlags(0x20000000);
        }
        return PendingIntent.getActivity(a, s2.hashCode(), intent, 0);
    }

    static Context a()
    {
        return a;
    }

    private static NotificationPayload a(String s)
    {
        NotificationPayload notificationpayload;
        try
        {
            notificationpayload = (NotificationPayload)FlipkartApplication.getGsonInstance().fromJson(s, com/flipkart/android/notification/data/pulldown/NotificationPayload);
        }
        catch (Exception exception)
        {
            return null;
        }
        return notificationpayload;
    }

    private static void a(NotificationDataPacket notificationdatapacket, android.support.v4.app.NotificationCompat.Builder builder)
    {
        if (!StringUtils.isNullOrEmpty(notificationdatapacket.getPayload()))
        {
            NotificationPayload notificationpayload = a(notificationdatapacket.getPayload());
            if (notificationpayload != null)
            {
                NotificationAction notificationaction;
                for (Iterator iterator = notificationpayload.getActions().iterator(); iterator.hasNext(); builder.addAction(0, notificationaction.getTitle(), a(notificationaction.getAction(), notificationaction.getOmniture(), notificationdatapacket.getNotificationId())))
                {
                    notificationaction = (NotificationAction)iterator.next();
                }

            }
        }
    }

    public static void pushToNotificationView(NotificationDataPacket notificationdatapacket)
    {
        String s = (new StringBuilder()).append(notificationdatapacket.getNotificationId()).toString();
        boolean flag;
        String s1;
        String s5;
        if (FlipkartPreferenceManager.instance().getNotificationIds() != null && FlipkartPreferenceManager.instance().getNotificationIds().contains(s))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag) goto _L2; else goto _L1
_L1:
        s1 = FlipkartPreferenceManager.instance().getNotificationIds();
        if (!StringUtils.isNullOrEmpty(notificationdatapacket.getNotificationId()))
        {
            s5 = (new StringBuilder()).append(s1).append(",").append(notificationdatapacket.getNotificationId()).toString();
            FlipkartPreferenceManager.instance().saveNotificationIds(s5);
        }
        if (notificationdatapacket.getExpiry() <= 0L || System.currentTimeMillis() / 1000L <= notificationdatapacket.getExpiry()) goto _L3; else goto _L2
_L2:
        return;
_L3:
        if (!StringUtils.isNullOrEmpty(notificationdatapacket.getRelativeTo()))
        {
            String s4 = notificationdatapacket.getRelativeTo();
            ((NotificationManager)a.getSystemService("notification")).cancel(s4, 1);
        }
        if (notificationdatapacket.getType() != NotificationType.PULLDOWN_NOTIFICATION) goto _L2; else goto _L4
_L4:
        android.support.v4.app.NotificationCompat.Builder builder1;
        if (notificationdatapacket != null && notificationdatapacket.isDoPostbackOnShow())
        {
            sendNotificationDisplayedEvent(notificationdatapacket.getNotificationId());
        }
        android.support.v4.app.NotificationCompat.Builder builder = (new android.support.v4.app.NotificationCompat.Builder(a)).setSmallIcon(0x7f0200f1).setContentTitle(notificationdatapacket.getTitle()).setContentText(notificationdatapacket.getMessage()).setAutoCancel(true);
        String s2 = notificationdatapacket.getNotificationId();
        Intent intent = new Intent(a, com/flipkart/android/notification/NotificationDeleteIntentReceiver);
        intent.setAction("notification_delete_action");
        intent.putExtra("extras_notification_id", s2);
        builder1 = builder.setDeleteIntent(PendingIntent.getBroadcast(a, 0, intent, 0x10000000)).setContentIntent(a(notificationdatapacket.getAction(), notificationdatapacket.getOmniture(), notificationdatapacket.getNotificationId()));
        e e1 = new e((byte)0);
        e1.setUrl(ImageUtils.getImageUrl(notificationdatapacket.getIconImage()));
        e1.setTag("icon");
        d d1 = new d(builder1, notificationdatapacket);
        e ae[] = {
            e1
        };
        String s3;
        boolean flag1;
        e e2;
        if (!(d1 instanceof AsyncTask))
        {
            d1.execute(ae);
        } else
        {
            AsyncTaskInstrumentation.execute((AsyncTask)d1, ae);
        }
        if (e1.getBitmap() == null)
        {
            e1.setBitmap(((BitmapDrawable)a.getResources().getDrawable(0x7f020119)).getBitmap());
        }
        builder1.setLargeIcon(e1.getBitmap());
        s3 = ImageUtils.getImageUrl(notificationdatapacket.getBigImage());
        flag1 = StringUtils.isNullOrEmpty(s3);
        e2 = null;
        if (!flag1)
        {
            int i = FlipkartDeviceInfoProvider.getAndroidSDKVersion();
            e2 = null;
            if (i >= 16)
            {
                e e3 = new e((byte)0);
                e3.setUrl(s3);
                e3.setTag("bigImage");
                d d2 = new d(builder1, notificationdatapacket);
                e ae1[] = {
                    e3
                };
                android.support.v4.app.NotificationCompat.BigPictureStyle bigpicturestyle;
                if (!(d2 instanceof AsyncTask))
                {
                    d2.execute(ae1);
                } else
                {
                    AsyncTaskInstrumentation.execute((AsyncTask)d2, ae1);
                }
                e2 = e3;
            }
        }
        if (e2 == null || e2.getBitmap() == null) goto _L6; else goto _L5
_L5:
        bigpicturestyle = new android.support.v4.app.NotificationCompat.BigPictureStyle();
        bigpicturestyle.setBigContentTitle(notificationdatapacket.getTitle());
        bigpicturestyle.setSummaryText(notificationdatapacket.getMessageExtra());
        bigpicturestyle.bigPicture(e2.getBitmap());
        builder1.setStyle(bigpicturestyle);
_L8:
        a(notificationdatapacket, builder1);
        ((NotificationManager)a.getSystemService("notification")).notify((new StringBuilder()).append(notificationdatapacket.getNotificationId()).toString(), 1, builder1.build());
        return;
_L6:
        if (!StringUtils.isNullOrEmpty(notificationdatapacket.getMessageExtra()))
        {
            android.support.v4.app.NotificationCompat.BigTextStyle bigtextstyle = new android.support.v4.app.NotificationCompat.BigTextStyle();
            bigtextstyle.bigText(notificationdatapacket.getMessageExtra());
            bigtextstyle.setBigContentTitle(notificationdatapacket.getTitle());
            bigtextstyle.setSummaryText(notificationdatapacket.getMessage());
            builder1.setStyle(bigtextstyle);
        }
        if (true) goto _L8; else goto _L7
_L7:
    }

    public static void sendNotificationDismissedEvent(String s)
    {
        (new c()).sendNotificationReceipt(s, "Dismissed");
    }

    public static void sendNotificationDisplayedEvent(String s)
    {
        (new a()).sendNotificationReceipt(s, "Displayed");
    }

    public static void sendNotificationReadEvent(String s)
    {
        (new b()).sendNotificationReceipt(s, "Read");
    }

}
