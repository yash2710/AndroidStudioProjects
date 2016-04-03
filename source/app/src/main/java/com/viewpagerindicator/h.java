// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.viewpagerindicator;

import android.support.v4.view.ViewPager;
import android.view.View;

// Referenced classes of package com.viewpagerindicator:
//            j, TabPageIndicator

final class h
    implements android.view.View.OnClickListener
{

    private TabPageIndicator a;

    h(TabPageIndicator tabpageindicator)
    {
        a = tabpageindicator;
        super();
    }

    public final void onClick(View view)
    {
        j j1 = (j)view;
        int i = TabPageIndicator.a(a).getCurrentItem();
        int k = j1.getIndex();
        TabPageIndicator.a(a).setCurrentItem(k);
        if (i == k && TabPageIndicator.b(a) != null)
        {
            TabPageIndicator.b(a).onTabReselected(k);
        }
    }
}
