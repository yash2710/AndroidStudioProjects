// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.viewpagerindicator;

import android.content.Context;
import android.widget.TextView;

// Referenced classes of package com.viewpagerindicator:
//            TabPageIndicator

final class j extends TextView
{

    private int a;
    private TabPageIndicator b;

    public j(TabPageIndicator tabpageindicator, Context context)
    {
        b = tabpageindicator;
        super(context, null, R.attr.vpiTabPageIndicatorStyle);
    }

    static int a(j j1, int i)
    {
        j1.a = i;
        return i;
    }

    public final int getIndex()
    {
        return a;
    }

    public final void onMeasure(int i, int k)
    {
        super.onMeasure(i, k);
        if (TabPageIndicator.c(b) > 0 && getMeasuredWidth() > TabPageIndicator.c(b))
        {
            super.onMeasure(android.view.View.MeasureSpec.makeMeasureSpec(TabPageIndicator.c(b), 0x40000000), k);
        }
    }
}
