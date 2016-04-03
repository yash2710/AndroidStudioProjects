// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.volley.request.offer;

import com.flipkart.android.datahandler.AnalyticData.AnalyticData;
import com.flipkart.android.response.discovery.SiteWideOfferResponse;
import com.flipkart.android.volley.request.GsonRequest;

// Referenced classes of package com.flipkart.android.volley.request.offer:
//            b

public class SiteWideOfferRequest extends GsonRequest
{

    public SiteWideOfferRequest(com.android.volley.Response.Listener listener, com.android.volley.Response.ErrorListener errorlistener, AnalyticData analyticdata)
    {
        super(0, constructSecureGetUri("3", "discover/sitewide/offers", ""), null, (new b()).getType(), listener, errorlistener, analyticdata);
    }

    public void performJsonUpdate(byte abyte0[], SiteWideOfferResponse sitewideofferresponse, boolean flag)
    {
    }

    public volatile void performJsonUpdate(byte abyte0[], Object obj, boolean flag)
    {
        performJsonUpdate(abyte0, (SiteWideOfferResponse)obj, flag);
    }
}
