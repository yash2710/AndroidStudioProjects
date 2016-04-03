// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.customviews;

import android.content.Context;
import android.widget.TextView;

// Referenced classes of package com.flipkart.android.customviews:
//            TabbedItemSelector

final class H extends TextView
{

    private int a;
    private TabbedItemSelector b;

    public H(TabbedItemSelector tabbeditemselector, Context context)
    {
        b = tabbeditemselector;
        super(context, null, 0x7f0100cf);
    }

    static int a(H h, int i)
    {
        h.a = i;
        return i;
    }

    public final int getIndex()
    {
        return a;
    }

    public final void onMeasure(int i, int j)
    {
        super.onMeasure(i, j);
        if (TabbedItemSelector.c(b) > 0 && getMeasuredWidth() > TabbedItemSelector.c(b))
        {
            super.onMeasure(android.view.View.MeasureSpec.makeMeasureSpec(TabbedItemSelector.c(b), 0x40000000), j);
        }
    }
}
