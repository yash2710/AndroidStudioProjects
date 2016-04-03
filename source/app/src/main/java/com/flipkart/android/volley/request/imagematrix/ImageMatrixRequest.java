// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.volley.request.imagematrix;

import com.flipkart.android.datahandler.AnalyticData.AnalyticData;
import com.flipkart.android.response.appconfig.ImageProfileMatrix;
import com.flipkart.android.volley.request.GsonRequest;
import com.flipkart.android.volley.request.imagematrix.params.ImageMatrixParams;

// Referenced classes of package com.flipkart.android.volley.request.imagematrix:
//            a

public class ImageMatrixRequest extends GsonRequest
{

    public ImageMatrixRequest(ImageMatrixParams imagematrixparams, com.android.volley.Response.Listener listener, com.android.volley.Response.ErrorListener errorlistener, AnalyticData analyticdata)
    {
        super(0, constructGetUri("2", "util/image/android", imagematrixparams.generateURI()), null, (new a()).getType(), listener, errorlistener, analyticdata);
    }

    public void performJsonUpdate(byte abyte0[], ImageProfileMatrix imageprofilematrix, boolean flag)
    {
    }

    public volatile void performJsonUpdate(byte abyte0[], Object obj, boolean flag)
    {
        performJsonUpdate(abyte0, (ImageProfileMatrix)obj, flag);
    }
}
