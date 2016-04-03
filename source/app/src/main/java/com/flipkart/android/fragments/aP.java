// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.fragments;

import android.view.View;
import android.widget.ExpandableListView;
import com.flipkart.android.datahandler.param.BrowseParam;
import com.flipkart.android.response.discovery.StoreMetaInfo;
import java.util.ArrayList;

// Referenced classes of package com.flipkart.android.fragments:
//            RefineCategoryFragment, aT

final class aP
    implements android.widget.ExpandableListView.OnGroupClickListener
{

    private RefineCategoryFragment a;

    aP(RefineCategoryFragment refinecategoryfragment)
    {
        a = refinecategoryfragment;
        super();
    }

    public final boolean onGroupClick(ExpandableListView expandablelistview, View view, int i, long l)
    {
        if (RefineCategoryFragment.a(a).getStoreId() != null && !RefineCategoryFragment.a(a).getStoreId().equalsIgnoreCase("search.flipkart.com") && RefineCategoryFragment.a(a).getStoreId().length() != 0) goto _L2; else goto _L1
_L1:
        StoreMetaInfo storemetainfo = (StoreMetaInfo)RefineCategoryFragment.b(a).get(i);
        if (RefineCategoryFragment.d(a).getChildrenCount(i) > 0)
        {
            return false;
        }
        RefineCategoryFragment.a(a, storemetainfo.getId());
_L4:
        return true;
_L2:
        if (i == -1 + RefineCategoryFragment.d(a).getGroupCount())
        {
            return true;
        }
        if (RefineCategoryFragment.a(a).getQuery() == null || RefineCategoryFragment.a(a).getQuery().length() == 0)
        {
            StoreMetaInfo storemetainfo1 = (StoreMetaInfo)RefineCategoryFragment.c(a).get(i);
            if (storemetainfo1.getChildMetaInfo() != null && storemetainfo1.getChildMetaInfo().size() != 0)
            {
                return true;
            }
            RefineCategoryFragment.a(a, storemetainfo1.getId());
        } else
        if (i != 0)
        {
            StoreMetaInfo storemetainfo2 = (StoreMetaInfo)RefineCategoryFragment.c(a).get(i - 1);
            if (storemetainfo2.getChildMetaInfo() != null && storemetainfo2.getChildMetaInfo().size() != 0)
            {
                return true;
            }
            RefineCategoryFragment.a(a, storemetainfo2.getId());
        }
        if (true) goto _L4; else goto _L3
_L3:
    }
}
