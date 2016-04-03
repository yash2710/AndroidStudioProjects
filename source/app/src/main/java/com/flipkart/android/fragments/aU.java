// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.fragments;

import android.view.View;
import com.flipkart.android.analytics.TrackingHelper;

// Referenced classes of package com.flipkart.android.fragments:
//            SearchFragment

final class aU
    implements android.view.View.OnClickListener
{

    private SearchFragment a;

    aU(SearchFragment searchfragment)
    {
        a = searchfragment;
        super();
    }

    public final void onClick(View view)
    {
        TrackingHelper.sendUpCarrotClicked();
        SearchFragment.a(a);
    }
}
