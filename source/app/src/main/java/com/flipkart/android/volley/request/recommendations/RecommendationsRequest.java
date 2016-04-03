// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.volley.request.recommendations;

import com.flipkart.android.datahandler.AnalyticData.AnalyticData;
import com.flipkart.android.response.component.ComponentResponse;
import com.flipkart.android.volley.request.GsonRequest;
import com.flipkart.android.volley.request.recommendations.params.RecommendationsParams;

// Referenced classes of package com.flipkart.android.volley.request.recommendations:
//            b

public class RecommendationsRequest extends GsonRequest
{

    public RecommendationsRequest(RecommendationsParams recommendationsparams, com.android.volley.Response.Listener listener, com.android.volley.Response.ErrorListener errorlistener, AnalyticData analyticdata)
    {
        super(0, constructGetUri("2", "recommendation/product", recommendationsparams.generateURI()), null, (new b()).getType(), listener, errorlistener, analyticdata);
    }

    public void performJsonUpdate(byte abyte0[], ComponentResponse componentresponse, boolean flag)
    {
    }

    public volatile void performJsonUpdate(byte abyte0[], Object obj, boolean flag)
    {
        performJsonUpdate(abyte0, (ComponentResponse)obj, flag);
    }
}
