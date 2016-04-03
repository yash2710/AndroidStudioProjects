// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.datahandler;

import com.android.volley.RequestQueue;
import com.flipkart.android.datahandler.AnalyticData.AnalyticData;
import com.flipkart.android.init.FlipkartApplication;
import com.flipkart.android.volley.request.imagematrix.ImageMatrixRequest;
import com.flipkart.android.volley.request.imagematrix.params.ImageMatrixParams;

// Referenced classes of package com.flipkart.android.datahandler:
//            BaseVDataHandler

public abstract class ImageMatrixConfigDataHandler extends BaseVDataHandler
{

    public ImageMatrixConfigDataHandler()
    {
    }

    public void fetchImageMatrix(String s, long l, AnalyticData analyticdata)
    {
        ImageMatrixRequest imagematrixrequest = new ImageMatrixRequest(new ImageMatrixParams(s, l), listner, errorListner, analyticdata);
        FlipkartApplication.getRequestQueue().add(imagematrixrequest);
    }
}
