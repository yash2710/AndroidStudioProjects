// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.fragments;

import android.widget.ExpandableListView;

// Referenced classes of package com.flipkart.android.fragments:
//            AllFiltersFragment

final class e
    implements android.widget.ExpandableListView.OnGroupExpandListener
{

    private AllFiltersFragment a;

    e(AllFiltersFragment allfiltersfragment)
    {
        a = allfiltersfragment;
        super();
    }

    public final void onGroupExpand(int i)
    {
        if (AllFiltersFragment.x(a) != -1 && i != AllFiltersFragment.x(a))
        {
            AllFiltersFragment.r(a).collapseGroup(AllFiltersFragment.x(a));
        }
        AllFiltersFragment.b(a, i);
    }
}
