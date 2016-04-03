// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.volley.request.cart;

import com.flipkart.android.datahandler.AnalyticData.AnalyticData;
import com.flipkart.android.response.cart.AddToCartResponse;
import com.flipkart.android.volley.request.GsonRequest;
import com.flipkart.android.volley.request.cart.param.CartParam;

// Referenced classes of package com.flipkart.android.volley.request.cart:
//            a

public class AddToCartRequest extends GsonRequest
{

    private CartParam a;

    public AddToCartRequest(CartParam cartparam, com.android.volley.Response.Listener listener, com.android.volley.Response.ErrorListener errorlistener, AnalyticData analyticdata)
    {
        super(1, constructSecureGetUri("2", "cart/add", cartparam.generateURI()), null, (new a()).getType(), listener, errorlistener, analyticdata);
        a = cartparam;
    }

    public byte[] getBody()
    {
        return a.generateToByteArray();
    }

    public void performJsonUpdate(byte abyte0[], AddToCartResponse addtocartresponse, boolean flag)
    {
    }

    public volatile void performJsonUpdate(byte abyte0[], Object obj, boolean flag)
    {
        performJsonUpdate(abyte0, (AddToCartResponse)obj, flag);
    }
}
