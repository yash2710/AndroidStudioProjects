// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.cart;

import com.flipkart.android.config.FlipkartPreferenceManager;
import com.flipkart.android.utils.StringUtils;
import java.util.ArrayList;

// Referenced classes of package com.flipkart.android.cart:
//            Cart

public class CartHandler
{

    public CartHandler()
    {
    }

    public static Cart getCart()
    {
        return Cart.fromStringValue(FlipkartPreferenceManager.instance().getCartItems());
    }

    public static boolean isCartItem(String s)
    {
        if (StringUtils.isNullOrEmpty(s))
        {
            return false;
        } else
        {
            return getCart().getProductsIds().contains(s);
        }
    }

    public static void save(Cart cart)
    {
        if (cart == null)
        {
            return;
        } else
        {
            FlipkartPreferenceManager.instance().saveCartItems(Cart.toStringValue(cart));
            return;
        }
    }
}
