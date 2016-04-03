// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.datahandler;

import com.android.volley.RequestQueue;
import com.flipkart.android.init.FlipkartApplication;
import com.flipkart.android.volley.request.notification.NotificationReceiptRequest;
import com.flipkart.android.volley.request.notification.params.NotificationReceiptParams;
import com.flipkart.android.volley.request.pushnotification.NotificationRequest;
import com.flipkart.android.volley.request.pushnotification.params.NotificationParams;

// Referenced classes of package com.flipkart.android.datahandler:
//            BaseVDataHandler

public abstract class NotificationVDataHandler extends BaseVDataHandler
{

    public NotificationVDataHandler()
    {
    }

    public void sendNotificationReceipt(String s, String s1)
    {
        NotificationReceiptRequest notificationreceiptrequest = new NotificationReceiptRequest(new NotificationReceiptParams(s, s1, System.currentTimeMillis() / 1000L), listner, errorListner);
        request = notificationreceiptrequest;
        FlipkartApplication.getRequestQueue().add(notificationreceiptrequest);
    }

    public void sendNotificationRegId(String s, String s1)
    {
        NotificationRequest notificationrequest = new NotificationRequest(new NotificationParams(s, s1), listner, errorListner);
        request = notificationrequest;
        FlipkartApplication.getRequestQueue().add(notificationrequest);
    }
}
