// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.viewpagerindicator;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;

// Referenced classes of package com.viewpagerindicator:
//            PageIndicator, IcsLinearLayout, IconPagerAdapter, b

public class IconPageIndicator extends HorizontalScrollView
    implements PageIndicator
{

    private final IcsLinearLayout a;
    private ViewPager b;
    private android.support.v4.view.ViewPager.OnPageChangeListener c;
    private Runnable d;
    private int e;

    public IconPageIndicator(Context context)
    {
        this(context, null);
    }

    public IconPageIndicator(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        setHorizontalScrollBarEnabled(false);
        a = new IcsLinearLayout(context, R.attr.vpiIconPageIndicatorStyle);
        addView(a, new android.widget.FrameLayout.LayoutParams(-2, -1, 17));
    }

    static Runnable a(IconPageIndicator iconpageindicator, Runnable runnable)
    {
        iconpageindicator.d = null;
        return null;
    }

    public void notifyDataSetChanged()
    {
        a.removeAllViews();
        IconPagerAdapter iconpageradapter = (IconPagerAdapter)b.getAdapter();
        int i = iconpageradapter.getCount();
        for (int j = 0; j < i; j++)
        {
            ImageView imageview = new ImageView(getContext(), null, R.attr.vpiIconPageIndicatorStyle);
            imageview.setImageResource(iconpageradapter.getIconResId(j));
            a.addView(imageview);
        }

        if (e > i)
        {
            e = i - 1;
        }
        setCurrentItem(e);
        requestLayout();
    }

    public void onAttachedToWindow()
    {
        super.onAttachedToWindow();
        if (d != null)
        {
            post(d);
        }
    }

    public void onDetachedFromWindow()
    {
        super.onDetachedFromWindow();
        if (d != null)
        {
            removeCallbacks(d);
        }
    }

    public void onPageScrollStateChanged(int i)
    {
        if (c != null)
        {
            c.onPageScrollStateChanged(i);
        }
    }

    public void onPageScrolled(int i, float f, int j)
    {
        if (c != null)
        {
            c.onPageScrolled(i, f, j);
        }
    }

    public void onPageSelected(int i)
    {
        setCurrentItem(i);
        if (c != null)
        {
            c.onPageSelected(i);
        }
    }

    public void setCurrentItem(int i)
    {
        if (b == null)
        {
            throw new IllegalStateException("ViewPager has not been bound.");
        }
        e = i;
        b.setCurrentItem(i);
        int j = a.getChildCount();
        int k = 0;
        while (k < j) 
        {
            View view = a.getChildAt(k);
            boolean flag;
            View view1;
            if (k == i)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            view.setSelected(flag);
            if (!flag)
            {
                continue;
            }
            view1 = a.getChildAt(i);
            if (d != null)
            {
                removeCallbacks(d);
            }
            d = new b(this, view1);
            post(d);
            k++;
        }
    }

    public void setOnPageChangeListener(android.support.v4.view.ViewPager.OnPageChangeListener onpagechangelistener)
    {
        c = onpagechangelistener;
    }

    public void setViewPager(ViewPager viewpager)
    {
        if (b == viewpager)
        {
            return;
        }
        if (b != null)
        {
            b.setOnPageChangeListener(null);
        }
        if (viewpager.getAdapter() == null)
        {
            throw new IllegalStateException("ViewPager does not have adapter instance.");
        } else
        {
            b = viewpager;
            viewpager.setOnPageChangeListener(this);
            notifyDataSetChanged();
            return;
        }
    }

    public void setViewPager(ViewPager viewpager, int i)
    {
        setViewPager(viewpager);
        setCurrentItem(i);
    }
}
