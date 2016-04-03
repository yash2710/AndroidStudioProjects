// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.datahandler;

import com.android.volley.RequestQueue;
import com.flipkart.android.init.FlipkartApplication;
import com.flipkart.android.volley.request.pullnotification.PullNotificationRequest;

// Referenced classes of package com.flipkart.android.datahandler:
//            BaseVDataHandler

public abstract class PullNotificationVDataHandler extends BaseVDataHandler
{

    public PullNotificationVDataHandler()
    {
    }

    public void fetchNotifications()
    {
        PullNotificationRequest pullnotificationrequest = new PullNotificationRequest(listner, errorListner);
        request = pullnotificationrequest;
        FlipkartApplication.getRequestQueue().add(pullnotificationrequest);
    }
}
