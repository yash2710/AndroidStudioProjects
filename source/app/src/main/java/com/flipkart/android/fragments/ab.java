// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.fragments;

import android.app.Activity;
import android.view.View;
import com.flipkart.android.customviews.ViewPagerFixed;
import com.flipkart.android.response.productInfo.ProductInfo;
import com.flipkart.android.utils.FkProductListContext;
import com.flipkart.android.utils.ProductPageBuilder;
import com.flipkart.android.utils.ScreenMathUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

// Referenced classes of package com.flipkart.android.fragments:
//            ProductPageFragment

final class ab
    implements Runnable
{

    private boolean a;
    private Map b;
    private boolean c;
    private ProductPageFragment d;

    ab(ProductPageFragment productpagefragment, boolean flag, Map map, boolean flag1)
    {
        d = productpagefragment;
        a = flag;
        b = map;
        c = flag1;
        super();
    }

    public final void run()
    {
_L2:
        return;
        if (ProductPageFragment.c(d) == null || ProductPageFragment.k(d) == null || ProductPageFragment.c(d).getProductsCount() <= 0 || ProductPageFragment.b(d) >= ProductPageFragment.c(d).getProductIds().size()) goto _L2; else goto _L1
_L1:
        if (a)
        {
            break; /* Loop/switch isn't completed */
        }
        if (ProductPageFragment.e(d) == null || ProductPageFragment.c(d) == null)
        {
            continue; /* Loop/switch isn't completed */
        }
        Iterator iterator = b.keySet().iterator();
        do
        {
            if (!iterator.hasNext())
            {
                break;
            }
            String s = (String)iterator.next();
            View view = ProductPageFragment.i(d).findViewWithTag(new com.flipkart.android.utils.ProductPageBuilder.ProductViewTag(s));
            if (view != null)
            {
                ProductPageFragment.a(d, view, (ProductInfo)b.get(s));
            }
        } while (true);
        break; /* Loop/switch isn't completed */
        if (true) goto _L2; else goto _L3
_L3:
        ProductPageFragment.f(d);
        if (a)
        {
            continue; /* Loop/switch isn't completed */
        }
        if (c)
        {
            ProductPageFragment.i(d).setCurrentItem(ProductPageFragment.b(d));
        }
        ProductInfo productinfo = ProductPageFragment.c(d).getProductForId((String)ProductPageFragment.c(d).getProductIds().get(ProductPageFragment.b(d)));
        android.widget.LinearLayout.LayoutParams layoutparams1;
        if (productinfo != null)
        {
            ProductPageBuilder.buildBottomBar(productinfo, ProductPageFragment.h(d), d);
        } else
        {
            ProductPageFragment.a(d, ProductPageFragment.b(d), 0);
        }
        if (ProductPageFragment.k(d) == null)
        {
            continue; /* Loop/switch isn't completed */
        }
        if (ProductPageFragment.h(d).getVisibility() != 8)
        {
            break; /* Loop/switch isn't completed */
        }
        layoutparams1 = (android.widget.LinearLayout.LayoutParams)ProductPageFragment.i(d).getLayoutParams();
        if (layoutparams1 != null)
        {
            layoutparams1.setMargins(0, 0, 0, 0);
            ProductPageFragment.i(d).setLayoutParams(layoutparams1);
            return;
        }
        if (true) goto _L2; else goto _L4
_L4:
        android.widget.LinearLayout.LayoutParams layoutparams = (android.widget.LinearLayout.LayoutParams)ProductPageFragment.i(d).getLayoutParams();
        if (layoutparams != null)
        {
            layoutparams.setMargins(0, 0, 0, ScreenMathUtils.dpToPx(31, d.activity.getApplicationContext()));
            ProductPageFragment.i(d).setLayoutParams(layoutparams);
            return;
        }
        if (true) goto _L2; else goto _L5
_L5:
    }
}
