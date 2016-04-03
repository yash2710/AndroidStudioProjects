// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.datahandler;

import com.android.volley.RequestQueue;
import com.flipkart.android.init.FlipkartApplication;
import com.flipkart.android.volley.request.urlencode.URLDecodeRequest;
import com.flipkart.android.volley.request.urlencode.params.URLDecodeParams;

// Referenced classes of package com.flipkart.android.datahandler:
//            BaseVDataHandler

public abstract class UrlDecodeDatahandler extends BaseVDataHandler
{

    public UrlDecodeDatahandler()
    {
    }

    public void decodeFlipkartLink(String s)
    {
        URLDecodeRequest urldecoderequest = new URLDecodeRequest(new URLDecodeParams(s), listner, errorListner);
        request = urldecoderequest;
        FlipkartApplication.getRequestQueue().add(urldecoderequest);
    }
}
