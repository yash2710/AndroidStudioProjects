// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.customviews;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import com.flipkart.logging.FkLogger;

public class ViewPagerFixed extends ViewPager
{

    public ViewPagerFixed(Context context)
    {
        super(context);
    }

    public ViewPagerFixed(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
    }

    public boolean onInterceptTouchEvent(MotionEvent motionevent)
    {
        boolean flag;
        try
        {
            flag = super.onInterceptTouchEvent(motionevent);
        }
        catch (Exception exception)
        {
            FkLogger.printStackTrace(exception);
            return false;
        }
        return flag;
    }

    public boolean onTouchEvent(MotionEvent motionevent)
    {
        boolean flag;
        try
        {
            flag = super.onTouchEvent(motionevent);
        }
        catch (Exception exception)
        {
            FkLogger.printStackTrace(exception);
            return false;
        }
        return flag;
    }
}
