// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.customviews;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewParent;

// Referenced classes of package com.flipkart.android.customviews:
//            ViewPagerFixed

public class UninterceptableViewPager extends ViewPagerFixed
{

    public UninterceptableViewPager(Context context)
    {
        super(context);
    }

    public UninterceptableViewPager(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
    }

    public boolean onInterceptTouchEvent(MotionEvent motionevent)
    {
        boolean flag = super.onInterceptTouchEvent(motionevent);
        if (flag)
        {
            getParent().requestDisallowInterceptTouchEvent(true);
        }
        return flag;
    }
}
