// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.response.wishlist;

import java.util.LinkedHashMap;
import java.util.Map;

// Referenced classes of package com.flipkart.android.response.wishlist:
//            WishListJsonResponse

public class WishListResponse
{

    private Map product;
    private WishListJsonResponse wishlist;

    public WishListResponse()
    {
        product = new LinkedHashMap();
    }

    public Map getProduct()
    {
        if (product == null)
        {
            product = new LinkedHashMap();
        }
        return product;
    }

    public WishListJsonResponse getWishlist()
    {
        return wishlist;
    }

    public void setProduct(Map map)
    {
        product = map;
    }

    public void setWishlist(WishListJsonResponse wishlistjsonresponse)
    {
        wishlist = wishlistjsonresponse;
    }
}
