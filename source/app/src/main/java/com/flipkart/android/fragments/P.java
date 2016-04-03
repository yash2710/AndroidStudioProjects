// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.fragments;

import android.view.View;
import android.widget.ListView;

// Referenced classes of package com.flipkart.android.fragments:
//            ProductListFragment

final class P
    implements android.view.ViewTreeObserver.OnGlobalLayoutListener
{

    private ProductListFragment a;

    P(ProductListFragment productlistfragment)
    {
        a = productlistfragment;
        super();
    }

    public final void onGlobalLayout()
    {
        if (a.productList != null)
        {
            View view = a.productList.getChildAt(1);
            if (view != null)
            {
                View view1 = view.findViewById(0x7f0a00f1);
                if (view1 != null && ProductListFragment.b(a) != null && view1.getVisibility() == 0)
                {
                    android.widget.LinearLayout.LayoutParams layoutparams = (android.widget.LinearLayout.LayoutParams)view1.getLayoutParams();
                    layoutparams.height = ProductListFragment.b(a).getMeasuredHeight();
                    view1.setLayoutParams(layoutparams);
                }
            }
        }
        ProductListFragment.removeOnGlobalLayoutListener(ProductListFragment.b(a), this);
    }
}
