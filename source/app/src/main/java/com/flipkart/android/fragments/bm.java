// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.fragments;

import android.view.View;
import com.flipkart.android.activity.HomeFragmentHolderActivity;

// Referenced classes of package com.flipkart.android.fragments:
//            SearchListFragment

final class bm
    implements android.view.View.OnClickListener
{

    private SearchListFragment a;

    bm(SearchListFragment searchlistfragment)
    {
        a = searchlistfragment;
        super();
    }

    public final void onClick(View view)
    {
        ((HomeFragmentHolderActivity)a.activity).doShowCart();
    }
}
