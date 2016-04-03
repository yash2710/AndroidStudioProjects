// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.customviews;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;
import com.flipkart.logging.FkLogger;

public class ScrollViewFixed extends ScrollView
{

    private OnScrollChangedListener a;

    public ScrollViewFixed(Context context)
    {
        super(context);
    }

    public ScrollViewFixed(Context context, AttributeSet attributeset)
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

    protected void onScrollChanged(int i, int j, int k, int l)
    {
        super.onScrollChanged(i, j, k, l);
        if (a != null)
        {
            a.onScrollChanged(this, i, j, k, l);
        }
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

    public void setOnScrollChangedListener(OnScrollChangedListener onscrollchangedlistener)
    {
        a = onscrollchangedlistener;
    }

    private class OnScrollChangedListener
    {

        public abstract void onScrollChanged(ScrollView scrollview, int i, int j, int k, int l);
    }

}
