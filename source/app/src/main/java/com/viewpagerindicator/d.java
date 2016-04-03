// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.viewpagerindicator;

import android.support.v4.view.ViewPager;
import android.view.ViewTreeObserver;

// Referenced classes of package com.viewpagerindicator:
//            SmoothTabPageIndicator

final class d
    implements android.view.ViewTreeObserver.OnGlobalLayoutListener
{

    private SmoothTabPageIndicator a;

    d(SmoothTabPageIndicator smoothtabpageindicator)
    {
        a = smoothtabpageindicator;
        super();
    }

    public final void onGlobalLayout()
    {
        if (android.os.Build.VERSION.SDK_INT < 16)
        {
            a.getViewTreeObserver().removeGlobalOnLayoutListener(this);
        } else
        {
            a.getViewTreeObserver().removeOnGlobalLayoutListener(this);
        }
        SmoothTabPageIndicator.a(a, SmoothTabPageIndicator.a(a).getCurrentItem());
        SmoothTabPageIndicator.a(a, SmoothTabPageIndicator.b(a), 0);
    }
}
