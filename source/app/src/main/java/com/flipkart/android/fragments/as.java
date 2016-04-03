// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.fragments;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import com.flipkart.android.customviews.ViewPortScrollView;
import com.flipkart.android.fragments.model.ProductPageModel;
import com.flipkart.android.response.productInfo.ProductInfo;
import com.flipkart.android.utils.FkProductListContext;
import com.flipkart.android.utils.ProductPageBuilder;
import com.flipkart.android.utils.ScreenMathUtils;
import java.util.ArrayList;
import java.util.Map;

// Referenced classes of package com.flipkart.android.fragments:
//            ProductPageFragment

final class as extends PagerAdapter
{

    private View a[];
    private ProductPageFragment b;

    as(ProductPageFragment productpagefragment)
    {
        b = productpagefragment;
        super();
        a = new View[6];
    }

    private View a(int i)
    {
        ViewPortScrollView viewportscrollview = new ViewPortScrollView(b.activity);
        a[i] = viewportscrollview;
        return viewportscrollview;
    }

    public final void clearAllViews()
    {
        View aview[] = a;
        int i = aview.length;
        for (int j = 0; j < i; j++)
        {
            View view = aview[j];
            if (view != null)
            {
                ScreenMathUtils.unbindDrawables(view);
            }
        }

    }

    public final void destroyItem(View view, int i, Object obj)
    {
        ((ViewPager)view).removeView((View)obj);
    }

    public final int getCount()
    {
        if (ProductPageFragment.c(b) == null)
        {
            return 0;
        } else
        {
            return ProductPageFragment.c(b).getProductIds().size();
        }
    }

    public final int getItemPosition(Object obj)
    {
        return -2;
    }

    public final Object instantiateItem(View view, int i)
    {
        View view1;
        android.view.ViewParent viewparent;
        int k;
        int j = i % 6;
        view1 = a[j];
        if (view1 == null)
        {
            view1 = a(j);
        }
        viewparent = view1.getParent();
        k = 0;
        if (viewparent != null) goto _L2; else goto _L1
_L1:
        View view2 = view1;
_L11:
        if (i > 1 + ProductPageFragment.b(b) || i < -1 + ProductPageFragment.b(b)) goto _L4; else goto _L3
_L3:
        ProductPageModel productpagemodel;
        ProductInfo productinfo;
        productpagemodel = (ProductPageModel)ProductPageFragment.d(b).get(ProductPageFragment.c(b).getProductIds().get(i));
        productinfo = ProductPageFragment.c(b).getProductForId((String)ProductPageFragment.c(b).getProductIds().get(i));
        ((ViewPortScrollView)view2).getRootLayout();
        com.flipkart.android.utils.ProductPageBuilder.ProductViewTag productviewtag;
        View view3;
        if (productpagemodel == null && productinfo != null)
        {
            try
            {
                productpagemodel = ProductPageModel.getModel(productinfo, b.activity);
                ProductPageFragment.d(b).put(productinfo.getProductId(), productpagemodel);
            }
            catch (Exception exception)
            {
                return view2;
            }
        }
        if (productpagemodel == null)
        {
            break MISSING_BLOCK_LABEL_428;
        }
        view2.setVisibility(0);
        ProductPageFragment.c(b, "ProductPage");
        if (!productpagemodel.getProductId().equals(ProductPageFragment.c(b).getProductIds().get(ProductPageFragment.b(b)))) goto _L6; else goto _L5
_L5:
        ProductPageBuilder.buildProductPage(productpagemodel, view2, ProductPageFragment.o(b), b, b, ProductPageFragment.a(b), ProductPageFragment.p(b), b.activity, productpagemodel.getRequestId(), ProductPageFragment.c(b));
_L12:
        if (productinfo == null) goto _L8; else goto _L7
_L7:
        if (productinfo.getInfoLevel() == 0) goto _L4; else goto _L8
_L8:
        ProductPageFragment.a(b, i, 0);
_L4:
        ((ViewPager)view).addView(view2);
        return view2;
_L10:
        k++;
_L2:
        if (k >= 6)
        {
            break MISSING_BLOCK_LABEL_370;
        }
        view3 = a[k];
        if (view3 == null)
        {
            view3 = a(k);
        }
        if (view3.getParent() != null) goto _L10; else goto _L9
_L9:
        view2 = view3;
          goto _L11
        view2 = null;
          goto _L11
_L6:
        ProductPageBuilder.buildProductPage(productpagemodel, view2, ProductPageFragment.o(b), b, b, false, ProductPageFragment.p(b), b.activity, productpagemodel.getRequestId(), ProductPageFragment.c(b));
          goto _L12
        productviewtag = new com.flipkart.android.utils.ProductPageBuilder.ProductViewTag();
        productviewtag.setProductId((String)ProductPageFragment.c(b).getProductIds().get(i));
        view2.setTag(productviewtag);
        view2.setVisibility(4);
          goto _L12
    }

    public final boolean isViewFromObject(View view, Object obj)
    {
        return view == (View)obj;
    }
}
