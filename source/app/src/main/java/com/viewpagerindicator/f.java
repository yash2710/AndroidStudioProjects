// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.viewpagerindicator;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;

// Referenced classes of package com.viewpagerindicator:
//            SmoothTabPageIndicator

final class f
    implements android.support.v4.view.ViewPager.OnPageChangeListener
{

    private int a;
    private int b;
    private SmoothTabPageIndicator c;

    private f(SmoothTabPageIndicator smoothtabpageindicator)
    {
        c = smoothtabpageindicator;
        super();
    }

    f(SmoothTabPageIndicator smoothtabpageindicator, byte byte0)
    {
        this(smoothtabpageindicator);
    }

    public final void onPageScrollStateChanged(int i)
    {
        if (i == 0)
        {
            SmoothTabPageIndicator.a(c, SmoothTabPageIndicator.a(c).getCurrentItem(), 0);
        }
        if (c.delegatePageListener != null)
        {
            c.delegatePageListener.onPageScrollStateChanged(i);
        }
        if (i != 0 || !SmoothTabPageIndicator.e(c)) goto _L2; else goto _L1
_L1:
        boolean flag;
        int j;
        if (b == 2)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag) goto _L2; else goto _L3
_L3:
        j = -1 + SmoothTabPageIndicator.a(c).getAdapter().getCount();
        if (a != 0) goto _L5; else goto _L4
_L4:
        SmoothTabPageIndicator.a(c).setCurrentItem(j, false);
        SmoothTabPageIndicator.a(c, j, 0);
        c.notifyDataSetChanged();
_L2:
        b = i;
        SmoothTabPageIndicator.d(c).pageStateChanged(i);
        return;
_L5:
        if (a == j)
        {
            SmoothTabPageIndicator.a(c).setCurrentItem(0, false);
            SmoothTabPageIndicator.a(c, 0, 0);
            c.notifyDataSetChanged();
        }
        if (true) goto _L2; else goto _L6
_L6:
    }

    public final void onPageScrolled(int i, float f1, int j)
    {
        SmoothTabPageIndicator.a(c, i);
        SmoothTabPageIndicator.a(c, f1);
        SmoothTabPageIndicator.a(c, i, (int)(f1 * (float)SmoothTabPageIndicator.c(c).getChildAt(i).getWidth()));
        c.invalidate();
        if (c.delegatePageListener != null)
        {
            c.delegatePageListener.onPageScrolled(i, f1, j);
        }
    }

    public final void onPageSelected(int i)
    {
        a = i;
        if (c.delegatePageListener != null)
        {
            c.delegatePageListener.onPageSelected(i);
        }
        SmoothTabPageIndicator.d(c).pageChanged(i);
    }
}
