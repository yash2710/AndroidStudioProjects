// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.fragments;

import android.view.View;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import com.flipkart.android.log.CrashLoggerUtils;
import com.flipkart.android.utils.FilterPagePreCallBackCache;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

// Referenced classes of package com.flipkart.android.fragments:
//            AllFiltersFragment

final class c
    implements android.widget.ExpandableListView.OnGroupClickListener
{

    private AllFiltersFragment a;

    c(AllFiltersFragment allfiltersfragment)
    {
        a = allfiltersfragment;
        super();
    }

    public final boolean onGroupClick(ExpandableListView expandablelistview, View view, int i, long l)
    {
        String s;
        String s1;
        if (AllFiltersFragment.w(a) == view)
        {
            return true;
        }
        s = view.getTag().toString();
        s1 = s.split("/")[1];
        if (s1 != null && s.contains("Offers"))
        {
            AllFiltersFragment.f(a);
        }
        if (AllFiltersFragment.r(a).isGroupExpanded(i))
        {
            AllFiltersFragment.a(a, "");
            return false;
        }
        AllFiltersFragment.a(a, s1);
        String s3 = AllFiltersFragment.b().split("/")[1];
        String s2 = s3;
_L1:
        AllFiltersFragment.a(s);
        CrashLoggerUtils.pushAndUpdate((new StringBuilder("clicked on list more filter : ")).append(s1).toString());
        AllFiltersFragment.b(a, (LinearLayout)view);
        ArrayList arraylist = new ArrayList(AllFiltersFragment.d(a));
        AllFiltersFragment.d(a).clear();
        Exception exception;
        try
        {
            AllFiltersFragment.d(a).addAll((Collection)AllFiltersFragment.p(a).getSelectedFilterMap().get(s1));
        }
        catch (Exception exception1) { }
        if (AllFiltersFragment.n(a).get(s2) != null)
        {
            ((ArrayList)AllFiltersFragment.n(a).get(s2)).clear();
        }
        if (!s2.contains("onclick_more"))
        {
            AllFiltersFragment.n(a).put(s2, arraylist);
        }
        AllFiltersFragment.a(a, false);
        return false;
        exception;
        s2 = AllFiltersFragment.b();
          goto _L1
    }
}
