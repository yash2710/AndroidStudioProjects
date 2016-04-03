// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.viewpagerindicator;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.widget.HorizontalScrollView;

// Referenced classes of package com.viewpagerindicator:
//            PageIndicator, h, IcsLinearLayout, IconPagerAdapter, 
//            j, i

public class TabPageIndicator extends HorizontalScrollView
    implements PageIndicator
{

    private static final CharSequence a = "";
    private Runnable b;
    private final android.view.View.OnClickListener c;
    private final IcsLinearLayout d;
    private ViewPager e;
    private android.support.v4.view.ViewPager.OnPageChangeListener f;
    private int g;
    private int h;
    private OnTabReselectedListener i;

    public TabPageIndicator(Context context)
    {
        this(context, null);
    }

    public TabPageIndicator(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        c = new h(this);
        setHorizontalScrollBarEnabled(false);
        d = new IcsLinearLayout(context, R.attr.vpiTabPageIndicatorStyle);
        addView(d, new android.view.ViewGroup.LayoutParams(-2, -1));
    }

    static ViewPager a(TabPageIndicator tabpageindicator)
    {
        return tabpageindicator.e;
    }

    static Runnable a(TabPageIndicator tabpageindicator, Runnable runnable)
    {
        tabpageindicator.b = null;
        return null;
    }

    static OnTabReselectedListener b(TabPageIndicator tabpageindicator)
    {
        return tabpageindicator.i;
    }

    static int c(TabPageIndicator tabpageindicator)
    {
        return tabpageindicator.g;
    }

    public void notifyDataSetChanged()
    {
        d.removeAllViews();
        PagerAdapter pageradapter = e.getAdapter();
        boolean flag = pageradapter instanceof IconPagerAdapter;
        IconPagerAdapter iconpageradapter = null;
        if (flag)
        {
            iconpageradapter = (IconPagerAdapter)pageradapter;
        }
        int k = pageradapter.getCount();
        int l = 0;
        while (l < k) 
        {
            CharSequence charsequence = pageradapter.getPageTitle(l);
            CharSequence charsequence1;
            int i1;
            j j1;
            if (charsequence == null)
            {
                charsequence1 = a;
            } else
            {
                charsequence1 = charsequence;
            }
            if (iconpageradapter != null)
            {
                i1 = iconpageradapter.getIconResId(l);
            } else
            {
                i1 = 0;
            }
            j1 = new j(this, getContext());
            j.a(j1, l);
            j1.setFocusable(true);
            j1.setOnClickListener(c);
            j1.setText(charsequence1);
            if (i1 != 0)
            {
                j1.setCompoundDrawablesWithIntrinsicBounds(i1, 0, 0, 0);
            }
            d.addView(j1, new android.widget.LinearLayout.LayoutParams(0, -1, 1.0F));
            l++;
        }
        if (h > k)
        {
            h = k - 1;
        }
        setCurrentItem(h);
        requestLayout();
    }

    public void onAttachedToWindow()
    {
        super.onAttachedToWindow();
        if (b != null)
        {
            post(b);
        }
    }

    public void onDetachedFromWindow()
    {
        super.onDetachedFromWindow();
        if (b != null)
        {
            removeCallbacks(b);
        }
    }

    public void onMeasure(int k, int l)
    {
        int i1 = android.view.View.MeasureSpec.getMode(k);
        boolean flag;
        int j1;
        int k1;
        int l1;
        if (i1 == 0x40000000)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        setFillViewport(flag);
        j1 = d.getChildCount();
        if (j1 > 1 && (i1 == 0x40000000 || i1 == 0x80000000))
        {
            if (j1 > 2)
            {
                g = (int)(0.4F * (float)android.view.View.MeasureSpec.getSize(k));
            } else
            {
                g = android.view.View.MeasureSpec.getSize(k) / 2;
            }
        } else
        {
            g = -1;
        }
        k1 = getMeasuredWidth();
        super.onMeasure(k, l);
        l1 = getMeasuredWidth();
        if (flag && k1 != l1)
        {
            setCurrentItem(h);
        }
    }

    public void onPageScrollStateChanged(int k)
    {
        if (f != null)
        {
            f.onPageScrollStateChanged(k);
        }
    }

    public void onPageScrolled(int k, float f1, int l)
    {
        if (f != null)
        {
            f.onPageScrolled(k, f1, l);
        }
    }

    public void onPageSelected(int k)
    {
        setCurrentItem(k);
        if (f != null)
        {
            f.onPageSelected(k);
        }
    }

    public void setCurrentItem(int k)
    {
        if (e == null)
        {
            throw new IllegalStateException("ViewPager has not been bound.");
        }
        h = k;
        e.setCurrentItem(k);
        int l = d.getChildCount();
        int i1 = 0;
        while (i1 < l) 
        {
            View view = d.getChildAt(i1);
            boolean flag;
            View view1;
            if (i1 == k)
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
            view1 = d.getChildAt(k);
            if (b != null)
            {
                removeCallbacks(b);
            }
            b = new i(this, view1);
            post(b);
            i1++;
        }
    }

    public void setOnPageChangeListener(android.support.v4.view.ViewPager.OnPageChangeListener onpagechangelistener)
    {
        f = onpagechangelistener;
    }

    public void setOnTabReselectedListener(OnTabReselectedListener ontabreselectedlistener)
    {
        i = ontabreselectedlistener;
    }

    public void setViewPager(ViewPager viewpager)
    {
        if (e == viewpager)
        {
            return;
        }
        if (e != null)
        {
            e.setOnPageChangeListener(null);
        }
        if (viewpager.getAdapter() == null)
        {
            throw new IllegalStateException("ViewPager does not have adapter instance.");
        } else
        {
            e = viewpager;
            viewpager.setOnPageChangeListener(this);
            notifyDataSetChanged();
            return;
        }
    }

    public void setViewPager(ViewPager viewpager, int k)
    {
        setViewPager(viewpager);
        setCurrentItem(k);
    }

}
