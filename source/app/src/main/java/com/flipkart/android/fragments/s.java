// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.flipkart.android.utils.FacetData;
import com.flipkart.android.utils.FilterPagePreCallBackCache;
import com.flipkart.android.utils.FontCache;
import com.flipkart.android.utils.ScreenMathUtils;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

// Referenced classes of package com.flipkart.android.fragments:
//            AllFiltersFragment

final class s extends BaseExpandableListAdapter
{

    private AllFiltersFragment a;

    private s(AllFiltersFragment allfiltersfragment)
    {
        a = allfiltersfragment;
        super();
    }

    s(AllFiltersFragment allfiltersfragment, byte byte0)
    {
        this(allfiltersfragment);
    }

    public final boolean areAllItemsEnabled()
    {
        return super.areAllItemsEnabled();
    }

    public final Object getChild(int i, int j)
    {
        return null;
    }

    public final long getChildId(int i, int j)
    {
        return 0L;
    }

    public final View getChildView(int i, int j, boolean flag, View view, ViewGroup viewgroup)
    {
        LinearLayout linearlayout = (LinearLayout)LayoutInflater.from(a.activity).inflate(0x7f030060, null);
        linearlayout.setBackgroundColor(a.activity.getApplicationContext().getResources().getColor(0x7f090045));
        TextView textview = (TextView)linearlayout.findViewById(0x7f0a0134);
        textview.setTypeface(Typeface.DEFAULT);
        CheckBox checkbox = (CheckBox)linearlayout.findViewById(0x7f0a0135);
        String s1 = AllFiltersFragment.c(a, i);
        Map map = (Map)AllFiltersFragment.p(a).getFilterMap().get(s1);
        if (map == null)
        {
            return null;
        }
        String s2 = AllFiltersFragment.a(a, map, j);
        int k = ((FacetData)((Map)AllFiltersFragment.p(a).getFilterMap().get(s1)).get(s2)).getCount();
        if (k == 0)
        {
            checkbox.setVisibility(8);
            textview.setTextColor(Color.parseColor("#A4A4A4"));
        }
        textview.setText((new StringBuilder()).append(s2).append(" (").append(k).append(" results)").toString());
        if (((ArrayList)AllFiltersFragment.p(a).getSelectedFilterMap().get(s1)).contains(s2))
        {
            checkbox.setChecked(true);
            textview.setTypeface(Typeface.DEFAULT_BOLD);
        }
        linearlayout.setTag((new StringBuilder()).append(s2).append(";").append(k).toString());
        textview.setGravity(19);
        return linearlayout;
    }

    public final int getChildrenCount(int i)
    {
        String s1 = AllFiltersFragment.c(a, i);
        return ((Map)AllFiltersFragment.p(a).getFilterMap().get(s1)).size();
    }

    public final Object getGroup(int i)
    {
        return null;
    }

    public final int getGroupCount()
    {
        int i = -5 + AllFiltersFragment.p(a).getSelectedFilterMap().size();
        if (i < 0)
        {
            i = 0;
        }
        return i;
    }

    public final long getGroupId(int i)
    {
        return 0L;
    }

    public final View getGroupView(int i, boolean flag, View view, ViewGroup viewgroup)
    {
        LinearLayout linearlayout = (LinearLayout)LayoutInflater.from(a.activity).inflate(0x7f030079, null);
        linearlayout.setVisibility(0);
        TextView textview = (TextView)linearlayout.findViewById(0x7f0a0168);
        textview.setTypeface(FontCache.getFont("robotolight.ttf"));
        textview.setTextColor(0xff2e2e2e);
        textview.setTextSize(2, 16F);
        textview.setGravity(19);
        int j = ScreenMathUtils.dpToPx(10, a.activity);
        int k = ScreenMathUtils.dpToPx(15, a.activity);
        textview.setPadding(j, k, j, k);
        String s1 = AllFiltersFragment.c(a, i);
        textview.setText(s1);
        linearlayout.setTag((new StringBuilder("in_more/")).append(s1).toString());
        if (AllFiltersFragment.y(a).equals(s1) && !flag)
        {
            flag = true;
            if (a.b.getGroupCount() >= i)
            {
                AllFiltersFragment.r(a).expandGroup(i);
            }
            AllFiltersFragment.d(a).clear();
            AllFiltersFragment.d(a).addAll((Collection)AllFiltersFragment.p(a).getSelectedFilterMap().get(s1));
        }
        if (flag)
        {
            textview.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0x7f020087, 0);
            return linearlayout;
        } else
        {
            textview.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0x7f020088, 0);
            return linearlayout;
        }
    }

    public final boolean hasStableIds()
    {
        return false;
    }

    public final boolean isChildSelectable(int i, int j)
    {
        return true;
    }
}
