// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.datahandler;

import com.android.volley.RequestQueue;
import com.flipkart.android.init.FlipkartApplication;
import com.flipkart.android.volley.request.cart.GetCartRequest;

// Referenced classes of package com.flipkart.android.datahandler:
//            BaseVDataHandler

public abstract class CartVDataHandler extends BaseVDataHandler
{

    public CartVDataHandler()
    {
    }

    public void getCart()
    {
        GetCartRequest getcartrequest = new GetCartRequest(listner, errorListner);
        request = getcartrequest;
        FlipkartApplication.getRequestQueue().add(getcartrequest);
    }
}
