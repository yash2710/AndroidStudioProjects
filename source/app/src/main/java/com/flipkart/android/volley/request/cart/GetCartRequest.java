// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.volley.request.cart;

import com.flipkart.android.response.cart.CartResponse;
import com.flipkart.android.volley.request.GsonRequest;

// Referenced classes of package com.flipkart.android.volley.request.cart:
//            b

public class GetCartRequest extends GsonRequest
{

    public GetCartRequest(com.android.volley.Response.Listener listener, com.android.volley.Response.ErrorListener errorlistener)
    {
        super(0, constructSecureGetUri("2", "cart/all", ""), null, (new b()).getType(), listener, errorlistener);
    }

    public void performJsonUpdate(byte abyte0[], CartResponse cartresponse, boolean flag)
    {
    }

    public volatile void performJsonUpdate(byte abyte0[], Object obj, boolean flag)
    {
        performJsonUpdate(abyte0, (CartResponse)obj, flag);
    }
}
