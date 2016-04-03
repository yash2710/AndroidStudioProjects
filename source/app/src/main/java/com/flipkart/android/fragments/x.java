// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.fragments;

import android.view.View;

// Referenced classes of package com.flipkart.android.fragments:
//            FiltersListFragment

final class x
    implements android.view.View.OnClickListener
{

    private FiltersListFragment a;

    x(FiltersListFragment filterslistfragment)
    {
        a = filterslistfragment;
        super();
    }

    public final void onClick(View view)
    {
        FiltersListFragment.b(a);
    }
}
