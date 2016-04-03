// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.volley.request.inAppNotification;

import com.flipkart.android.response.inAppNotification.InAppNotificationResponse;
import com.flipkart.android.volley.request.GsonRequest;
import com.flipkart.android.volley.request.inAppNotification.params.InAppNotificationParam;

// Referenced classes of package com.flipkart.android.volley.request.inAppNotification:
//            d

public class MarkAsReadRequest extends GsonRequest
{

    private InAppNotificationParam a;

    public MarkAsReadRequest(InAppNotificationParam inappnotificationparam, com.android.volley.Response.Listener listener, com.android.volley.Response.ErrorListener errorlistener)
    {
        super(1, constructSecureGetUri("2", "notification", inappnotificationparam.generateURI()), null, (new d()).getType(), listener, errorlistener);
        a = null;
        a = inappnotificationparam;
    }

    public byte[] getBody()
    {
        return a.generateToByteArray();
    }

    public void performJsonUpdate(byte abyte0[], InAppNotificationResponse inappnotificationresponse, boolean flag)
    {
    }

    public volatile void performJsonUpdate(byte abyte0[], Object obj, boolean flag)
    {
        performJsonUpdate(abyte0, (InAppNotificationResponse)obj, flag);
    }
}
