// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.datahandler;

import com.android.volley.RequestQueue;
import com.flipkart.android.datahandler.AnalyticData.AnalyticData;
import com.flipkart.android.init.FlipkartApplication;
import com.flipkart.android.volley.request.offer.GetOfferTermsRequest;
import com.flipkart.android.volley.request.offerTerms.param.OfferTermParam;

// Referenced classes of package com.flipkart.android.datahandler:
//            BaseVDataHandler

public abstract class OfferTermsVDataHandler extends BaseVDataHandler
{

    public OfferTermsVDataHandler()
    {
    }

    public void getTermsAndCondition(String s, AnalyticData analyticdata)
    {
        GetOfferTermsRequest getoffertermsrequest = new GetOfferTermsRequest(new OfferTermParam(s), listner, errorListner, analyticdata);
        request = getoffertermsrequest;
        FlipkartApplication.getRequestQueue().add(getoffertermsrequest);
    }
}