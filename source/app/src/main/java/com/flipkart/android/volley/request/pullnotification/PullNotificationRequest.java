// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.volley.request.pullnotification;

import com.flipkart.android.response.pullnotification.PullNotificationResponse;
import com.flipkart.android.utils.volley.HeaderUtils;
import com.flipkart.android.volley.request.GsonRequest;
import java.util.HashMap;
import java.util.Map;

// Referenced classes of package com.flipkart.android.volley.request.pullnotification:
//            a

public class PullNotificationRequest extends GsonRequest
{

    public PullNotificationRequest(com.android.volley.Response.Listener listener, com.android.volley.Response.ErrorListener errorlistener)
    {
        super(0, constructGetUri("3", "notification/push/", "mailbox"), null, (new a()).getType(), listener, errorlistener);
    }

    public Map getHeaders()
    {
        Object obj = super.getHeaders();
        if (obj == null)
        {
            obj = new HashMap();
        }
        HeaderUtils.addSessionSpecificHeaders(((Map) (obj)));
        return ((Map) (obj));
    }

    public void performJsonUpdate(byte abyte0[], PullNotificationResponse pullnotificationresponse, boolean flag)
    {
    }

    public volatile void performJsonUpdate(byte abyte0[], Object obj, boolean flag)
    {
        performJsonUpdate(abyte0, (PullNotificationResponse)obj, flag);
    }
}
