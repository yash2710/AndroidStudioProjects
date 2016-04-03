// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.utils;

import android.view.View;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;

// Referenced classes of package com.flipkart.android.utils:
//            ScreenMathUtils

public class MiscScreenUtils
{

    public MiscScreenUtils()
    {
    }

    public static void setListViewHeightBasedOnChildren(ExpandableListView expandablelistview, BaseExpandableListAdapter baseexpandablelistadapter, boolean flag)
    {
        if (baseexpandablelistadapter == null)
        {
            return;
        }
        int i = 0;
        int j = 0;
        for (; i < baseexpandablelistadapter.getGroupCount(); i++)
        {
            View view = baseexpandablelistadapter.getGroupView(i, false, null, null);
            view.measure(0, 0);
            j += view.getMeasuredHeight();
            if (!expandablelistview.isGroupExpanded(i))
            {
                continue;
            }
            int k = 0;
            int l;
            int i1;
            for (l = j; k < baseexpandablelistadapter.getChildrenCount(i); l = i1)
            {
                View view1 = baseexpandablelistadapter.getChildView(i, k, true, null, null);
                view1.measure(0, 0);
                i1 = l + view1.getMeasuredHeight() + expandablelistview.getDividerHeight();
                k++;
            }

            j = l;
        }

        if (flag)
        {
            j += ScreenMathUtils.dpToPx(49, expandablelistview.getContext());
        }
        android.view.ViewGroup.LayoutParams layoutparams = expandablelistview.getLayoutParams();
        Object obj;
        if (layoutparams == null)
        {
            obj = new android.widget.LinearLayout.LayoutParams(-1, j + expandablelistview.getDividerHeight() * (-1 + baseexpandablelistadapter.getGroupCount()));
        } else
        {
            layoutparams.height = j + expandablelistview.getDividerHeight() * (-1 + baseexpandablelistadapter.getGroupCount());
            obj = layoutparams;
        }
        expandablelistview.setLayoutParams(((android.view.ViewGroup.LayoutParams) (obj)));
    }
}
