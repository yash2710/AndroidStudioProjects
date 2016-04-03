// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.datahandler;

import com.android.volley.RequestQueue;
import com.flipkart.android.analytics.TrackingHelper;
import com.flipkart.android.datahandler.AnalyticData.AnalyticData;
import com.flipkart.android.init.FlipkartApplication;
import com.flipkart.android.volley.request.notifyme.NotifymeRequest;
import com.flipkart.android.volley.request.notifyme.params.NotifymeParam;

// Referenced classes of package com.flipkart.android.datahandler:
//            BaseVDataHandler

public abstract class NotifyMeVDataHandler extends BaseVDataHandler
{

    public NotifyMeVDataHandler()
    {
    }

    public void notifyMe(String s, String s1, String s2, AnalyticData analyticdata)
    {
        TrackingHelper.sendProductNotifyMe(s1, s2);
        NotifymeRequest notifymerequest = new NotifymeRequest(new NotifymeParam(s, s1), listner, errorListner, analyticdata);
        request = notifymerequest;
        FlipkartApplication.getRequestQueue().add(notifymerequest);
    }
}
