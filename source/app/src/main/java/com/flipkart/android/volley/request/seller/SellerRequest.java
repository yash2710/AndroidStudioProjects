// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.volley.request.seller;

import com.flipkart.android.response.seller.SellerResponse;
import com.flipkart.android.volley.request.GsonRequest;
import com.flipkart.android.volley.request.seller.params.SellerParam;

// Referenced classes of package com.flipkart.android.volley.request.seller:
//            a, b

public class SellerRequest extends GsonRequest
{

    private final SellerParam a;

    public SellerRequest(SellerParam sellerparam, com.android.volley.Response.Listener listener, com.android.volley.Response.ErrorListener errorlistener)
    {
        super(0, constructGetUri("2", "seller/getSellerSummary", sellerparam.generateURI()), null, (new a()).getType(), listener, errorlistener);
        a = sellerparam;
    }

    static SellerParam a(SellerRequest sellerrequest)
    {
        return sellerrequest.a;
    }

    public void performJsonUpdate(byte abyte0[], SellerResponse sellerresponse, boolean flag)
    {
        if (sellerresponse == null)
        {
            return;
        } else
        {
            (new b(this, flag, abyte0)).start();
            return;
        }
    }

    public volatile void performJsonUpdate(byte abyte0[], Object obj, boolean flag)
    {
        performJsonUpdate(abyte0, (SellerResponse)obj, flag);
    }
}
