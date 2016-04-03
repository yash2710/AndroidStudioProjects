// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.fragments;

import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.flipkart.android.datahandler.AnalyticData.AnalyticData;
import com.flipkart.android.datahandler.SearchVDataHander;
import com.flipkart.android.datahandler.param.BrowseParam;
import com.flipkart.android.utils.FkProductListContext;
import com.flipkart.android.utils.ScreenMathUtils;

// Referenced classes of package com.flipkart.android.fragments:
//            SubCategoryTreeView, AllFiltersFragment

final class bs
    implements android.view.View.OnClickListener
{

    private SubCategoryTreeView a;

    bs(SubCategoryTreeView subcategorytreeview)
    {
        a = subcategorytreeview;
        super();
    }

    public final void onClick(View view)
    {
        if (SubCategoryTreeView.isShowingLoading)
        {
            return;
        }
        SubCategoryTreeView.b(a).setStoreID(((String)view.getTag()).split(",")[0]);
        ((AllFiltersFragment)SubCategoryTreeView.c(a)).subStoreCalled(true, null);
        SubCategoryTreeView.a(a, (LinearLayout)view);
        TextView textview = (TextView)SubCategoryTreeView.d(a).getChildAt(0);
        textview.setCompoundDrawablesWithIntrinsicBounds(null, null, SubCategoryTreeView.a(a).getResources().getDrawable(0x7f020155), null);
        textview.setCompoundDrawablePadding(ScreenMathUtils.dpToPx(5, SubCategoryTreeView.a(a)));
        if (SubCategoryTreeView.e(a) != -1)
        {
            LinearLayout linearlayout = (LinearLayout)SubCategoryTreeView.f(a).getChildAt(SubCategoryTreeView.e(a));
            if (linearlayout != null && linearlayout.getChildAt(0) != null)
            {
                ((TextView)linearlayout.getChildAt(0)).setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
            }
        }
        SubCategoryTreeView.g(a).setIsPageFirstLanding(false);
        SubCategoryTreeView.j(a).doSearch(null, SubCategoryTreeView.h(a).getQuery(), SubCategoryTreeView.b(a).getStoreID(), null, SubCategoryTreeView.a(a, SubCategoryTreeView.i(a)), null, SubCategoryTreeView.h(a).getTags(), 0, 0, 0, SubCategoryTreeView.h(a).isEnableAugmentSearch(), SubCategoryTreeView.g(a), SubCategoryTreeView.h(a).getViews());
    }
}
