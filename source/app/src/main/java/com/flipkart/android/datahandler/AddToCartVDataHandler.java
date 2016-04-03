// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.datahandler;

import com.android.volley.RequestQueue;
import com.flipkart.android.analytics.AddCartLocation;
import com.flipkart.android.analytics.TrackingHelper;
import com.flipkart.android.datahandler.AnalyticData.AnalyticData;
import com.flipkart.android.init.FlipkartApplication;
import com.flipkart.android.utils.ProductSpecificSellerTypes;
import com.flipkart.android.utils.SellerTypes;
import com.flipkart.android.utils.StringUtils;
import com.flipkart.android.volley.request.cart.AddToCartRequest;
import com.flipkart.android.volley.request.cart.param.CartParam;

// Referenced classes of package com.flipkart.android.datahandler:
//            BaseVDataHandler

public abstract class AddToCartVDataHandler extends BaseVDataHandler
{

    public AddToCartVDataHandler()
    {
    }

    public void addToCart(String s, String s1, String s2, String s3, ProductSpecificSellerTypes productspecificsellertypes, SellerTypes sellertypes, AddCartLocation addcartlocation, 
            AnalyticData analyticdata)
    {
        TrackingHelper.sendAddToCart(addcartlocation, s, s3, s1, s2, productspecificsellertypes, sellertypes);
        CartParam cartparam;
        AddToCartRequest addtocartrequest;
        if (StringUtils.isNullOrEmpty(s1))
        {
            cartparam = new CartParam(s);
        } else
        {
            cartparam = new CartParam(s1);
        }
        addtocartrequest = new AddToCartRequest(cartparam, listner, errorListner, analyticdata);
        request = addtocartrequest;
        FlipkartApplication.getRequestQueue().add(addtocartrequest);
    }
}
