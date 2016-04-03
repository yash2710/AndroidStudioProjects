// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.fragments;

import android.app.Dialog;
import android.view.View;

// Referenced classes of package com.flipkart.android.fragments:
//            FiltersListFragment

final class v
    implements android.view.View.OnClickListener
{

    private FiltersListFragment a;

    v(FiltersListFragment filterslistfragment)
    {
        a = filterslistfragment;
        super();
    }

    public final void onClick(View view)
    {
        if (FiltersListFragment.e(a) != null && FiltersListFragment.e(a).isShowing())
        {
            FiltersListFragment.e(a).dismiss();
        }
    }
}
