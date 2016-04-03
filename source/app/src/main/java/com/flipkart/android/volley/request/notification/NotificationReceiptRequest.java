// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.volley.request.notification;

import com.flipkart.android.response.notification.NotificationResponse;
import com.flipkart.android.volley.request.GsonRequest;
import com.flipkart.android.volley.request.notification.params.NotificationReceiptParams;

// Referenced classes of package com.flipkart.android.volley.request.notification:
//            a

public class NotificationReceiptRequest extends GsonRequest
{

    private NotificationReceiptParams a;

    public NotificationReceiptRequest(NotificationReceiptParams notificationreceiptparams, com.android.volley.Response.Listener listener, com.android.volley.Response.ErrorListener errorlistener)
    {
        super(1, constructPostUri("2", "received/notification"), null, (new a()).getType(), listener, errorlistener);
        a = null;
        a = notificationreceiptparams;
    }

    public byte[] getBody()
    {
        return a.generateBody();
    }

    public void performJsonUpdate(byte abyte0[], NotificationResponse notificationresponse, boolean flag)
    {
    }

    public volatile void performJsonUpdate(byte abyte0[], Object obj, boolean flag)
    {
        performJsonUpdate(abyte0, (NotificationResponse)obj, flag);
    }
}
