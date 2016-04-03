// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.volley.request.inAppNotification;

import com.flipkart.android.response.inAppNotification.InAppNotificationResponse;
import com.flipkart.android.volley.request.GsonRequest;
import com.flipkart.android.volley.request.inAppNotification.params.InAppNotificationParam;

// Referenced classes of package com.flipkart.android.volley.request.inAppNotification:
//            e

public class UnReadCountRequest extends GsonRequest
{

    public UnReadCountRequest(InAppNotificationParam inappnotificationparam, com.android.volley.Response.Listener listener, com.android.volley.Response.ErrorListener errorlistener)
    {
        super(0, constructSecureGetUri("2", "notification/unreadCount", ""), null, (new e()).getType(), listener, errorlistener);
    }

    public void performJsonUpdate(byte abyte0[], InAppNotificationResponse inappnotificationresponse, boolean flag)
    {
    }

    public volatile void performJsonUpdate(byte abyte0[], Object obj, boolean flag)
    {
        performJsonUpdate(abyte0, (InAppNotificationResponse)obj, flag);
    }
}
