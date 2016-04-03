// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.customviews;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.HorizontalScrollView;

// Referenced classes of package com.flipkart.android.customviews:
//            ViewPagerFixed

public class HorizontalScrollViewPager extends ViewPagerFixed
{

    public HorizontalScrollViewPager(Context context)
    {
        super(context);
        setLayoutParams(new android.widget.LinearLayout.LayoutParams(-1, -2));
    }

    public HorizontalScrollViewPager(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
    }

    protected boolean canScroll(View view, boolean flag, int i, int j, int k)
    {
        if (view instanceof HorizontalScrollView)
        {
            return true;
        } else
        {
            return super.canScroll(view, flag, i, j, k);
        }
    }
}
