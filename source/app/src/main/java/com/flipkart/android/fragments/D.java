// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.fragments;

import android.app.Dialog;
import android.view.View;

// Referenced classes of package com.flipkart.android.fragments:
//            FiltersListFragment

final class D
    implements android.view.View.OnClickListener
{

    private FiltersListFragment a;

    D(FiltersListFragment filterslistfragment)
    {
        a = filterslistfragment;
        super();
    }

    public final void onClick(View view)
    {
        int i = Integer.parseInt(view.getTag().toString());
        if (FiltersListFragment.i(a) != i)
        {
            FiltersListFragment.c(a, i);
        }
        FiltersListFragment.e(a).dismiss();
    }
}
