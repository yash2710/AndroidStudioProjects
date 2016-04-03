// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.datahandler;

import com.android.volley.RequestQueue;
import com.flipkart.android.datahandler.AnalyticData.AnalyticData;
import com.flipkart.android.init.FlipkartApplication;
import com.flipkart.android.volley.request.inAppNotification.DeleteInAppNotificationRequest;
import com.flipkart.android.volley.request.inAppNotification.FetchInAppNotificationRequest;
import com.flipkart.android.volley.request.inAppNotification.MarkAllReadRequest;
import com.flipkart.android.volley.request.inAppNotification.MarkAsReadRequest;
import com.flipkart.android.volley.request.inAppNotification.UnReadCountRequest;
import com.flipkart.android.volley.request.inAppNotification.params.InAppNotificationParam;

// Referenced classes of package com.flipkart.android.datahandler:
//            BaseVDataHandler

public abstract class InAppNotificationDataHandler extends BaseVDataHandler
{

    public InAppNotificationDataHandler()
    {
    }

    public void deleteInAppNotification(String s, String s1)
    {
        DeleteInAppNotificationRequest deleteinappnotificationrequest = new DeleteInAppNotificationRequest(new InAppNotificationParam(0, 0L, s, false, s1), listner, errorListner);
        request = deleteinappnotificationrequest;
        FlipkartApplication.getRequestQueue().add(deleteinappnotificationrequest);
    }

    public void getInappNotification(int i, long l, AnalyticData analyticdata)
    {
        FetchInAppNotificationRequest fetchinappnotificationrequest = new FetchInAppNotificationRequest(new InAppNotificationParam(i, l, "", false, ""), listner, errorListner, analyticdata);
        request = fetchinappnotificationrequest;
        FlipkartApplication.getRequestQueue().add(fetchinappnotificationrequest);
    }

    public void getUnReadCount()
    {
        UnReadCountRequest unreadcountrequest = new UnReadCountRequest(null, listner, errorListner);
        request = unreadcountrequest;
        FlipkartApplication.getRequestQueue().add(unreadcountrequest);
    }

    public void markAllRead()
    {
        MarkAllReadRequest markallreadrequest = new MarkAllReadRequest(new InAppNotificationParam(0, 0L, "", false, ""), listner, errorListner);
        request = markallreadrequest;
        FlipkartApplication.getRequestQueue().add(markallreadrequest);
    }

    public void markAsRead(String s, String s1)
    {
        MarkAsReadRequest markasreadrequest = new MarkAsReadRequest(new InAppNotificationParam(0, 0L, s, true, s1), listner, errorListner);
        request = markasreadrequest;
        FlipkartApplication.getRequestQueue().add(markasreadrequest);
    }
}
