// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.fragments;

import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import com.flipkart.android.datahandler.ProductUgcVDataHandler;
import com.flipkart.android.response.productInfo.ProductInfo;

// Referenced classes of package com.flipkart.android.fragments:
//            ProductPageReviewFragment

final class aI
    implements android.widget.AdapterView.OnItemSelectedListener
{

    private ProductPageReviewFragment a;

    aI(ProductPageReviewFragment productpagereviewfragment)
    {
        a = productpagereviewfragment;
        super();
    }

    public final void onItemSelected(AdapterView adapterview, View view, int i, long l)
    {
        ProductPageReviewFragment.a(a, ProductPageReviewFragment.e(a).getSelectedItem().toString());
        if (!ProductPageReviewFragment.f(a).equals("Most Helpful")) goto _L2; else goto _L1
_L1:
        ProductPageReviewFragment.a(a, "MOST_USEFUL");
_L4:
        if (!ProductPageReviewFragment.f(a).equals(ProductPageReviewFragment.g(a)) && ProductPageReviewFragment.h(a) == null)
        {
            ProductPageReviewFragment.b(a, ProductPageReviewFragment.f(a));
            if (ProductPageReviewFragment.i(a) != null)
            {
                ProgressBar progressbar = (ProgressBar)ProductPageReviewFragment.i(a).findViewById(0x7f0a008b);
                if (progressbar != null)
                {
                    progressbar.setVisibility(0);
                }
            }
            ProductPageReviewFragment.k(a).fetchUgcInfo(ProductPageReviewFragment.j(a).getProductId(), ProductPageReviewFragment.f(a), 0, 10);
        }
        return;
_L2:
        if (ProductPageReviewFragment.f(a).equals("Most Recent"))
        {
            ProductPageReviewFragment.a(a, "REVERSE_CHRONO");
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    public final void onNothingSelected(AdapterView adapterview)
    {
    }
}
