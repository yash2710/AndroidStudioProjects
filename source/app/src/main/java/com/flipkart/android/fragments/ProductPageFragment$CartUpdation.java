// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.fragments;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.flipkart.android.response.productInfo.ProductInfo;
import com.flipkart.android.utils.FkProductListContext;
import com.flipkart.android.utils.ProductPageBuilder;
import java.util.ArrayList;

// Referenced classes of package com.flipkart.android.fragments:
//            ProductPageFragment

public class a extends BroadcastReceiver
{

    private ProductPageFragment a;

    public void onReceive(Context context, Intent intent)
    {
        if (ProductPageFragment.c(a) != null && ProductPageFragment.c(a).getProductIds() != null && ProductPageFragment.b(a) < ProductPageFragment.c(a).getProductIds().size())
        {
            ProductInfo productinfo = ProductPageFragment.c(a).getProductForId((String)ProductPageFragment.c(a).getProductIds().get(ProductPageFragment.b(a)));
            if (productinfo != null)
            {
                ProductPageBuilder.updateBottomBarCartIcon(productinfo.getProductId(), a.activity.getCurrentFocus());
            }
        }
    }

    public (ProductPageFragment productpagefragment)
    {
        a = productpagefragment;
        super();
    }
}
