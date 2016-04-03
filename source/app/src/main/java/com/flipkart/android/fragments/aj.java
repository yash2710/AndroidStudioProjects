// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.fragments;

import android.app.Activity;
import android.os.AsyncTask;
import android.support.v4.util.LruCache;
import android.view.View;
import com.flipkart.android.analytics.TrackingHelper;
import com.flipkart.android.customviews.ViewPagerFixed;
import com.flipkart.android.fragments.model.ProductPageModel;
import com.flipkart.android.response.productInfo.ProductInfo;
import com.flipkart.android.utils.FkProductListContext;
import com.flipkart.android.utils.ProductPageBuilder;
import com.flipkart.android.utils.ScreenMathUtils;
import com.flipkart.android.utils.StringUtils;
import com.newrelic.agent.android.instrumentation.AsyncTaskInstrumentation;
import java.util.ArrayList;
import java.util.Map;

// Referenced classes of package com.flipkart.android.fragments:
//            ProductPageFragment, as, au

final class aj
    implements android.support.v4.view.ViewPager.OnPageChangeListener
{

    private ProductPageFragment a;

    aj(ProductPageFragment productpagefragment)
    {
        a = productpagefragment;
        super();
    }

    public final void onPageScrollStateChanged(int i)
    {
    }

    public final void onPageScrolled(int i, float f, int j)
    {
    }

    public final void onPageSelected(int i)
    {
        TrackingHelper.sendProductSwypeUsed();
        ProductPageFragment.a(a, 0);
        if (ProductPageFragment.a(a))
        {
            ProductPageFragment.a(a, false);
            ProductPageFragment.d(a).remove(ProductPageFragment.c(a).getProductIds().get(ProductPageFragment.b(a)));
            ProductPageFragment.e(a).notifyDataSetChanged();
            ProductPageFragment.f(a);
        }
        ProductPageFragment.b(a, i);
        ProductPageFragment.a(a, (String)ProductPageFragment.c(a).getProductIds().get(i));
        if (!StringUtils.isNullOrEmpty(ProductPageFragment.g(a)))
        {
            au au1 = new au(a, ProductPageFragment.g(a));
            Void avoid[] = new Void[0];
            android.widget.LinearLayout.LayoutParams layoutparams1;
            if (!(au1 instanceof AsyncTask))
            {
                au1.execute(avoid);
            } else
            {
                AsyncTaskInstrumentation.execute((AsyncTask)au1, avoid);
            }
            if (ProductPageFragment.c(a).getProductMap().size() > 0 && ProductPageFragment.c(a).getProductMap().get(ProductPageFragment.g(a)) != null)
            {
                TrackingHelper.sendProductView((ProductInfo)ProductPageFragment.c(a).getProductMap().get(ProductPageFragment.g(a)), (ProductPageModel)ProductPageFragment.d(a).get(ProductPageFragment.g(a)));
            }
        }
        ProductPageBuilder.buildBottomBar(ProductPageFragment.c(a).getProductForId(ProductPageFragment.g(a)), ProductPageFragment.h(a), a);
        ProductPageFragment.a(a, ProductPageFragment.b(a), 0);
        if (ProductPageFragment.h(a).getVisibility() == 8)
        {
            layoutparams1 = (android.widget.LinearLayout.LayoutParams)ProductPageFragment.i(a).getLayoutParams();
            layoutparams1.setMargins(0, 0, 0, 0);
            ProductPageFragment.i(a).setLayoutParams(layoutparams1);
        } else
        {
            android.widget.LinearLayout.LayoutParams layoutparams = (android.widget.LinearLayout.LayoutParams)ProductPageFragment.i(a).getLayoutParams();
            layoutparams.setMargins(0, 0, 0, ScreenMathUtils.dpToPx(31, a.activity.getApplicationContext()));
            ProductPageFragment.i(a).setLayoutParams(layoutparams);
        }
        if (ProductPageFragment.c(a).getProductsCount() > 0 && ProductPageFragment.b(a) >= -2 + ProductPageFragment.c(a).getProductsCount())
        {
            ProductPageFragment.c(a, 0);
        }
    }
}
