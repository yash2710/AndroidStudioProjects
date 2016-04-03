// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.viewpagerindicator;

import android.support.v4.view.ViewPager;
import android.view.View;

// Referenced classes of package com.viewpagerindicator:
//            SmoothTabPageIndicator

final class e
    implements android.view.View.OnClickListener
{

    private int a;
    private SmoothTabPageIndicator b;

    e(SmoothTabPageIndicator smoothtabpageindicator, int i)
    {
        b = smoothtabpageindicator;
        a = i;
        super();
    }

    public final void onClick(View view)
    {
        SmoothTabPageIndicator.a(b).setCurrentItem(a);
    }
}
