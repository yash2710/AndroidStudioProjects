// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.fragments;

import android.view.View;
import android.widget.LinearLayout;
import com.flipkart.android.datahandler.AnalyticData.AnalyticData;
import com.flipkart.android.datahandler.SearchVDataHander;
import com.flipkart.android.datahandler.param.BrowseParam;
import java.util.ArrayList;

// Referenced classes of package com.flipkart.android.fragments:
//            SubCategoryTreeView, AllFiltersFragment

final class bt
    implements android.view.View.OnClickListener
{

    private SubCategoryTreeView a;

    bt(SubCategoryTreeView subcategorytreeview)
    {
        a = subcategorytreeview;
        super();
    }

    public final void onClick(View view)
    {
        if (!SubCategoryTreeView.isShowingLoading) goto _L2; else goto _L1
_L1:
        String as[];
        return;
_L2:
        if (!a.checkIfClickToProcess(1 + Integer.valueOf((as = ((String)view.getTag()).split(","))[1]).intValue())) goto _L1; else goto _L3
_L3:
        SubCategoryTreeView.a(a, (LinearLayout)view);
        if (!as[2].equals("child")) goto _L5; else goto _L4
_L4:
        SubCategoryTreeView.a(a, -1 + (SubCategoryTreeView.f(a).getChildCount() - SubCategoryTreeView.k(a).size()));
        SubCategoryTreeView.f(a).removeViews(SubCategoryTreeView.f(a).getChildCount() - SubCategoryTreeView.k(a).size(), SubCategoryTreeView.k(a).size());
_L7:
        a.showLoadingPanel();
        ((AllFiltersFragment)SubCategoryTreeView.c(a)).subStoreCalled(SubCategoryTreeView.isShowingLoading, null);
        SubCategoryTreeView.g(a).setIsPageFirstLanding(false);
        SubCategoryTreeView.j(a).doSearch(null, SubCategoryTreeView.h(a).getQuery(), as[0], null, SubCategoryTreeView.a(a, SubCategoryTreeView.i(a)), null, SubCategoryTreeView.h(a).getTags(), 0, 0, 0, SubCategoryTreeView.h(a).isEnableAugmentSearch(), SubCategoryTreeView.g(a), SubCategoryTreeView.h(a).getViews());
        return;
_L5:
        if (as[2].equals("parent"))
        {
            SubCategoryTreeView.f(a).removeViews(1 + Integer.valueOf(as[1]).intValue(), -1 + (SubCategoryTreeView.f(a).getChildCount() - Integer.valueOf(as[1]).intValue()));
            SubCategoryTreeView.a(a, 1 + Integer.valueOf(as[1]).intValue());
        } else
        if (as[2].equals("cached_child"))
        {
            SubCategoryTreeView.a(a, -1 + (SubCategoryTreeView.f(a).getChildCount() - SubCategoryTreeView.l(a).size()));
            SubCategoryTreeView.f(a).removeViews(SubCategoryTreeView.f(a).getChildCount() - SubCategoryTreeView.l(a).size(), SubCategoryTreeView.l(a).size());
        }
        if (true) goto _L7; else goto _L6
_L6:
    }
}
