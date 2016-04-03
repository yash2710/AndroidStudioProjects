// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.flipkart.android.analytics.AddCartLocation;
import com.flipkart.android.analytics.PageName;
import com.flipkart.android.analytics.PageType;
import com.flipkart.android.analytics.TrackingHelper;
import com.flipkart.android.customviews.NullResultViewWidget;
import com.flipkart.android.datahandler.param.ProductsListParam;
import com.flipkart.android.init.FlipkartApplication;
import com.flipkart.android.utils.FkProductListContext;
import com.flipkart.android.utils.PageTypeUtils;
import com.flipkart.android.utils.ScreenMathUtils;

// Referenced classes of package com.flipkart.android.fragments:
//            ProductListFragment

public class SimpleProductListFragment extends ProductListFragment
{

    private View b;
    private TextView c;

    public SimpleProductListFragment()
    {
    }

    protected View getHeader()
    {
        b = layoutInflater.inflate(0x7f03005c, null);
        c = (TextView)b.findViewById(0x7f0a0125);
        if (fkContext != null && (fkContext.getParam() instanceof ProductsListParam))
        {
            ProductsListParam productslistparam = (ProductsListParam)fkContext.getParam();
            if (productslistparam != null)
            {
                c.setText(productslistparam.getTitle());
                LinearLayout linearlayout;
                if (context != null)
                {
                    c.setPadding(ScreenMathUtils.dpToPx(10, context), ScreenMathUtils.dpToPx(5, context), ScreenMathUtils.dpToPx(10, context), ScreenMathUtils.dpToPx(5, context));
                } else
                {
                    context = FlipkartApplication.getAppContext();
                    c.setPadding(ScreenMathUtils.dpToPx(10, context), ScreenMathUtils.dpToPx(5, context), ScreenMathUtils.dpToPx(10, context), ScreenMathUtils.dpToPx(5, context));
                }
                c.setSingleLine(true);
                c.setEllipsize(android.text.TextUtils.TruncateAt.END);
                b.setVisibility(0);
                fkContext.setTitleViewText(c.getText().toString());
            }
        }
        nullResultViewWidget = (NullResultViewWidget)layoutInflater.inflate(0x7f030064, null);
        nullResultViewWidget.setOnClickOnViews(nullResultViewWidgetOnclickListener);
        linearlayout = (LinearLayout)layoutInflater.inflate(0x7f03005b, null);
        linearlayout.addView(b);
        linearlayout.addView(nullResultViewWidget);
        return linearlayout;
    }

    public View onCreateView(LayoutInflater layoutinflater, ViewGroup viewgroup, Bundle bundle)
    {
        return super.onCreateView(layoutinflater, viewgroup, bundle);
    }

    protected void processExtras()
    {
        Bundle bundle;
        bundle = getArguments();
        if (fkContext == null)
        {
            fkContext = new FkProductListContext();
        }
        if (bundle == null) goto _L2; else goto _L1
_L1:
        java.util.ArrayList arraylist;
        String s;
        String s1;
        String s2;
        arraylist = bundle.getStringArrayList("PRODUCT_LIST_EXTRAS_PRODUCTS");
        s = bundle.getString("PRODUCT_LIST_EXTRAS_CUR_PRD");
        s1 = bundle.getString("PRODUCT_LIST_EXTRAS_TITLE");
        s2 = bundle.getString("PRODUCT_LIST_EXTRAS_PAGE_TYPE");
        if (PageTypeUtils.valueOf(s2) == PageTypeUtils.ProductList) goto _L2; else goto _L3
_L3:
        if (PageTypeUtils.valueOf(s2) != PageTypeUtils.HomePageRecommendation) goto _L5; else goto _L4
_L4:
        TrackingHelper.sendPageView(PageName.HomePageRecommendation.name(), PageType.Recommendation);
        addToCartLocation = AddCartLocation.HomePageRecommendationListPage;
_L7:
        dataParam = new ProductsListParam(arraylist, s, s1, PageTypeUtils.valueOf(s2));
        fkContext.setParam(dataParam);
_L2:
        return;
_L5:
        if (PageTypeUtils.valueOf(s2) == PageTypeUtils.ProductPageRecommendation)
        {
            TrackingHelper.sendPageView(PageName.ProductPageRecommendation.name(), PageType.Recommendation);
            addToCartLocation = AddCartLocation.ProductPageRecommendationListPage;
        } else
        if (PageTypeUtils.valueOf(s2) == PageTypeUtils.ProductListRecommendation)
        {
            TrackingHelper.sendPageView(PageName.ProductListRecommendation.name(), PageType.Recommendation);
            addToCartLocation = AddCartLocation.ProductListRecommendationPage;
        }
        if (true) goto _L7; else goto _L6
_L6:
    }
}
