// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.datahandler;

import com.android.volley.RequestQueue;
import com.flipkart.android.datahandler.AnalyticData.AnalyticData;
import com.flipkart.android.init.FlipkartApplication;
import com.flipkart.android.volley.request.recommendations.RecommendationRequest;
import com.flipkart.android.volley.request.recommendations.params.RecommendationsParams;
import java.util.ArrayList;

// Referenced classes of package com.flipkart.android.datahandler:
//            BaseVDataHandler

public abstract class RecommendationDataHandler extends BaseVDataHandler
{

    public RecommendationDataHandler()
    {
    }

    public void getRecommendations(ArrayList arraylist, int i, String s, AnalyticData analyticdata)
    {
        RecommendationRequest recommendationrequest = new RecommendationRequest(new RecommendationsParams(arraylist, i, s), listner, errorListner, analyticdata);
        request = recommendationrequest;
        FlipkartApplication.getRequestQueue().add(recommendationrequest);
    }
}
