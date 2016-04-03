// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.volley.request.pushnotification;

import com.flipkart.android.response.notification.NotificationResponse;
import com.flipkart.android.volley.request.GsonRequest;
import com.flipkart.android.volley.request.pushnotification.params.NotificationParams;

// Referenced classes of package com.flipkart.android.volley.request.pushnotification:
//            a

public class NotificationRequest extends GsonRequest
{

    private NotificationParams a;

    public NotificationRequest(NotificationParams notificationparams, com.android.volley.Response.Listener listener, com.android.volley.Response.ErrorListener errorlistener)
    {
        super(1, constructSecurePostUri("3", "notification/push/register"), null, (new a()).getType(), listener, errorlistener);
        a = null;
        a = notificationparams;
    }

    public byte[] getBody()
    {
        if (a != null)
        {
            return a.generateToByteArray();
        } else
        {
            return null;
        }
    }

    public void performJsonUpdate(byte abyte0[], NotificationResponse notificationresponse, boolean flag)
    {
    }

    public volatile void performJsonUpdate(byte abyte0[], Object obj, boolean flag)
    {
        performJsonUpdate(abyte0, (NotificationResponse)obj, flag);
    }
}
