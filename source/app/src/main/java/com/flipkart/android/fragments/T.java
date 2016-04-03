// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.fragments;

import android.widget.AbsListView;
import com.flipkart.android.analytics.ProductListViewType;
import com.flipkart.android.config.FlipkartDeviceInfoProvider;
import com.flipkart.android.utils.AppConfigUtils;

// Referenced classes of package com.flipkart.android.fragments:
//            ProductListFragment, Y

final class T
    implements android.widget.AbsListView.OnScrollListener
{

    private int a;
    private long b;
    private double c;
    private int d;
    private ProductListFragment e;

    T(ProductListFragment productlistfragment)
    {
        e = productlistfragment;
        super();
        a = 0;
        b = 0L;
        c = 0.0D;
        d = 0;
    }

    public final void onScroll(AbsListView abslistview, int i, int j, int k)
    {
        if (!ProductListFragment.c(e) && j != 0 && i + j < k)
        {
            ProductListFragment.b(e, true);
        }
        if (i != 0)
        {
            ProductListFragment.b(e, 110F);
        }
        ProductListFragment.a(e, i);
        ProductListFragment.k(e);
        ProductListViewType.Grid;
        if (k != 0 && j != 0 && i + j > k - 2 && !e.isRefreshing && !ProductListFragment.l(e))
        {
            e.triggerRequest();
        }
        if (a != i || a > i)
        {
            int l = i + ProductListFragment.m(e);
            if (ProductListFragment.k(e) == ProductListViewType.Grid)
            {
                l <<= 1;
            }
            ProductListFragment.b(e, l);
        }
        if (AppConfigUtils.getInstance().isEnableAnimation() && FlipkartDeviceInfoProvider.getAndroidSDKVersion() >= 11 && a != i && ProductListFragment.k(e) != ProductListViewType.Grid)
        {
            long l1 = System.currentTimeMillis();
            c = 1000D * (1.0D / (double)(l1 - b));
            b = l1;
            if (c > 20D)
            {
                ProductListFragment.j(e).setAnimate(Boolean.valueOf(false));
            } else
            {
                ProductListFragment.j(e).setAnimate(Boolean.valueOf(true));
            }
        }
        if (d != 1 || !ProductListFragment.n(e))
        {
            return;
        }
        if (a <= i) goto _L2; else goto _L1
_L1:
        ProductListFragment.f(e);
_L4:
        a = i;
        return;
_L2:
        if (a < i && a != 0)
        {
            ProductListFragment.e(e);
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    public final void onScrollStateChanged(AbsListView abslistview, int i)
    {
        if (ProductListFragment.j(e) != null)
        {
            Y y = ProductListFragment.j(e);
            boolean flag;
            if (d == 2 || d == 1)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            y.setAnimate(Boolean.valueOf(flag));
        }
        if (i == 1)
        {
            ProductListFragment.a(e, true);
        }
        if (d != 1)
        {
            d = 1;
            ProductListFragment.a(false);
        }
    }
}
