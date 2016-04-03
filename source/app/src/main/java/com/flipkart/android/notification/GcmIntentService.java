// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.notification;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import com.flipkart.android.config.FlipkartDeviceInfoProvider;
import com.flipkart.android.datahandler.DebugLogger;
import com.flipkart.android.init.FlipkartApplication;
import com.flipkart.android.notification.data.NotificationDataPacket;
import com.flipkart.android.notification.data.NotificationType;
import com.flipkart.android.response.component.data.customvalues.Image;
import com.flipkart.android.utils.AppConfigUtils;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.mygson.Gson;
import org.json.JSONObject;

// Referenced classes of package com.flipkart.android.notification:
//            FlipkartNotificationManager, GcmBroadcastReceiver

public class GcmIntentService extends IntentService
{

    public GcmIntentService()
    {
        super("GcmIntentService");
    }

    private static long a(String s)
    {
        long l;
        try
        {
            l = Long.parseLong(s);
        }
        catch (Exception exception)
        {
            return -1L;
        }
        return l;
    }

    private static int b(String s)
    {
        int i;
        try
        {
            i = Integer.parseInt(s);
        }
        catch (Exception exception)
        {
            return -1;
        }
        return i;
    }

    private static boolean c(String s)
    {
        boolean flag;
        try
        {
            flag = Boolean.parseBoolean(s);
        }
        catch (Exception exception)
        {
            return true;
        }
        return flag;
    }

    public static void handleBundle(Bundle bundle)
    {
        String s = bundle.getString("id");
        long l = a(bundle.getString("expiry"));
        String s1 = bundle.getString("relative_to");
        boolean flag = c(bundle.getString("do_postback_on_show"));
        boolean flag1 = c(bundle.getString("do_postback_on_read"));
        boolean flag2 = c(bundle.getString("do_postback_on_dismiss"));
        int i = b(bundle.getString("type"));
        int j;
        String s2;
        String s3;
        String s4;
        String s5;
        String s6;
        String s7;
        String s8;
        String s9;
        Gson gson;
        Image image;
        Image image1;
        JSONObject jsonobject;
        JSONObject jsonobject1;
        Exception exception;
        if (i == -1)
        {
            j = 1;
        } else
        {
            j = i;
        }
        s2 = bundle.getString("payload");
        s3 = bundle.getString("title");
        s4 = bundle.getString("message");
        s5 = bundle.getString("message_extras");
        s6 = bundle.getString("action");
        s7 = bundle.getString("omniture");
        s8 = bundle.getString("icon_image");
        s9 = bundle.getString("big_image");
        gson = FlipkartApplication.getGsonInstance();
        image = (Image)gson.fromJson(s8, com/flipkart/android/response/component/data/customvalues/Image);
        image1 = (Image)gson.fromJson(s9, com/flipkart/android/response/component/data/customvalues/Image);
        FlipkartNotificationManager.pushToNotificationView(new NotificationDataPacket(s, l, s1, flag, flag1, flag2, NotificationType.getType(j), s2, s3, s4, s5, image, image1, s6, s7));
        if (!AppConfigUtils.getInstance().getEnableBatch())
        {
            break MISSING_BLOCK_LABEL_297;
        }
        jsonobject = new JSONObject();
        jsonobject1 = new JSONObject();
        jsonobject1.put("notificationId", s);
        jsonobject1.put("deviceId", FlipkartDeviceInfoProvider.getDeviceId());
        jsonobject1.put("isPush", true);
        jsonobject.put("event", "PUSH_NOTIFICATION_RECEIVED");
        jsonobject.put("data", jsonobject1);
        DebugLogger.logDebuggingJson(jsonobject);
        return;
        exception;
        exception.printStackTrace();
        return;
    }

    protected void onHandleIntent(Intent intent)
    {
        Bundle bundle;
        String s1;
        String s = intent.getAction();
        if (s.equals("com.google.android.c2dm.intent.REGISTRATION") || !s.equals("com.google.android.c2dm.intent.RECEIVE"))
        {
            break MISSING_BLOCK_LABEL_70;
        }
        bundle = intent.getExtras();
        s1 = GoogleCloudMessaging.getInstance(this).getMessageType(intent);
        if (bundle != null)
        {
            try
            {
                if (!bundle.isEmpty() && "gcm".equals(s1))
                {
                    handleBundle(bundle);
                }
            }
            catch (Exception exception)
            {
                exception.printStackTrace();
            }
        }
        GcmBroadcastReceiver.completeWakefulIntent(intent);
        return;
    }
}
