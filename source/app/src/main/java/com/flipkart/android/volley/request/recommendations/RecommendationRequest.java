// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.volley.request.recommendations;

import com.flipkart.android.datahandler.AnalyticData.AnalyticData;
import com.flipkart.android.volley.request.GsonRequest;
import com.flipkart.android.volley.request.recommendations.params.RecommendationsParams;
import java.util.Map;

// Referenced classes of package com.flipkart.android.volley.request.recommendations:
//            a

public class RecommendationRequest extends GsonRequest
{

    public RecommendationRequest(RecommendationsParams recommendationsparams, com.android.volley.Response.Listener listener, com.android.volley.Response.ErrorListener errorlistener, AnalyticData analyticdata)
    {
        super(0, constructSecureGetUri("3", "recommendation/product", recommendationsparams.generateURI()), null, (new a()).getType(), listener, errorlistener, analyticdata);
    }

    public volatile void performJsonUpdate(byte abyte0[], Object obj, boolean flag)
    {
        performJsonUpdate(abyte0, (Map)obj, flag);
    }

    public void performJsonUpdate(byte abyte0[], Map map, boolean flag)
    {
    }
}
