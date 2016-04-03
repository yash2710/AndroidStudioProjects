// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.utils;

import android.text.Html;
import android.view.View;
import android.widget.TextView;
import com.flipkart.android.fragments.model.ProductPageModel;

// Referenced classes of package com.flipkart.android.utils:
//            StringUtils, ProductPageBuilder

public class ProductPageSummaryBuilder
{

    public ProductPageSummaryBuilder()
    {
    }

    public static void buildProductPageSumary(ProductPageModel productpagemodel, View view, android.view.View.OnClickListener onclicklistener)
    {
        if (!StringUtils.isNullOrEmpty(productpagemodel.getProductId()))
        {
            ProductPageBuilder.buildProductPageSubPagesTiltBar(productpagemodel.getMainTitle(), productpagemodel.getSubTitle(), view, onclicklistener, productpagemodel.isEbook());
            ProductPageBuilder.buildProductPageSubPagesPriceBar(productpagemodel, view);
            if (productpagemodel.isSummaryEnabled())
            {
                String s = productpagemodel.getSummaryText();
                ((TextView)view.findViewById(0x7f0a01f8)).setText(Html.fromHtml(s));
            }
        }
    }
}
