// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.volley.request.offer;

import com.flipkart.android.datahandler.AnalyticData.AnalyticData;
import com.flipkart.android.response.offerTermsResponse.OfferTermsResponse;
import com.flipkart.android.volley.request.GsonRequest;
import com.flipkart.android.volley.request.offerTerms.param.OfferTermParam;

// Referenced classes of package com.flipkart.android.volley.request.offer:
//            a

public class GetOfferTermsRequest extends GsonRequest
{

    public GetOfferTermsRequest(OfferTermParam offertermparam, com.android.volley.Response.Listener listener, com.android.volley.Response.ErrorListener errorlistener, AnalyticData analyticdata)
    {
        super(0, constructGetUri("2", "discover/santaOfferDetails", offertermparam.generateURI()), null, (new a()).getType(), listener, errorlistener, analyticdata);
    }

    public void performJsonUpdate(byte abyte0[], OfferTermsResponse offertermsresponse, boolean flag)
    {
    }

    public volatile void performJsonUpdate(byte abyte0[], Object obj, boolean flag)
    {
        performJsonUpdate(abyte0, (OfferTermsResponse)obj, flag);
    }
}
