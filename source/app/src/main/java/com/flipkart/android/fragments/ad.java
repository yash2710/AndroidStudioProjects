// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.fragments;

import android.view.View;
import com.flipkart.android.activity.HomeFragmentHolderActivity;
import com.flipkart.android.analytics.SearchMode;
import com.flipkart.android.analytics.TrackingHelper;
import com.flipkart.android.customwidget.WidgetAction;
import com.flipkart.android.response.customwidgetitemvalue.Action;
import com.flipkart.android.utils.PageTypeUtils;
import com.flipkart.android.utils.StringUtils;

// Referenced classes of package com.flipkart.android.fragments:
//            ProductPageFragment

final class ad
    implements android.view.View.OnClickListener
{

    private ProductPageFragment a;

    ad(ProductPageFragment productpagefragment)
    {
        a = productpagefragment;
        super();
    }

    public final void onClick(View view)
    {
        if (view.getTag() instanceof Action)
        {
            WidgetAction.performAction((Action)view.getTag(), a.activity, PageTypeUtils.ProductPageBarCode);
        } else
        {
            if (view.getTag() instanceof com.flipkart.android.response.component.data.customvalues.Action)
            {
                WidgetAction.performAction((com.flipkart.android.response.component.data.customvalues.Action)view.getTag(), a.activity, PageTypeUtils.ProductPage);
                return;
            }
            if (view.getTag() instanceof String)
            {
                String s = (String)view.getTag();
                if (!StringUtils.isNullOrEmpty(s) && s.equals("open_search_page"))
                {
                    TrackingHelper.sendSearchMode(SearchMode.ProductPageBarCode);
                    ((HomeFragmentHolderActivity)a.activity).openSearchPage();
                    return;
                }
            }
        }
    }
}
