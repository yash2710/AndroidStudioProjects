// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.customviews;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.HorizontalScrollView;
import com.viewpagerindicator.IcsLinearLayout;

// Referenced classes of package com.flipkart.android.customviews:
//            F, H, G

public class TabbedItemSelector extends HorizontalScrollView
{

    private static final CharSequence a = "";
    private Runnable b;
    private final android.view.View.OnClickListener c;
    private final IcsLinearLayout d;
    private int e;
    private int f;
    private OnTabReselectedListener g;
    private String h[];

    public TabbedItemSelector(Context context)
    {
        this(context, null);
    }

    public TabbedItemSelector(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        c = new F(this);
        h = null;
        setHorizontalScrollBarEnabled(false);
        d = new IcsLinearLayout(context, 0x7f0100cf);
        addView(d, new android.view.ViewGroup.LayoutParams(-2, -1));
    }

    static int a(TabbedItemSelector tabbeditemselector)
    {
        return tabbeditemselector.f;
    }

    static Runnable a(TabbedItemSelector tabbeditemselector, Runnable runnable)
    {
        tabbeditemselector.b = null;
        return null;
    }

    static OnTabReselectedListener b(TabbedItemSelector tabbeditemselector)
    {
        return tabbeditemselector.g;
    }

    static int c(TabbedItemSelector tabbeditemselector)
    {
        return tabbeditemselector.e;
    }

    public void notifyDataSetChanged(String as[])
    {
        d.removeAllViews();
        h = as;
        if (as == null)
        {
            requestLayout();
            return;
        }
        int i = h.length;
        for (int j = 0; j < i; j++)
        {
            Object obj = h[j];
            if (obj == null)
            {
                obj = a;
            }
            H h1 = new H(this, getContext());
            H.a(h1, j);
            h1.setFocusable(true);
            h1.setOnClickListener(c);
            h1.setText(((CharSequence) (obj)));
            d.addView(h1, new android.widget.LinearLayout.LayoutParams(0, -1, 1.0F));
        }

        if (f > i)
        {
            f = i - 1;
        }
        setCurrentItem(f);
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

    public void onMeasure(int i, int j)
    {
        int k = android.view.View.MeasureSpec.getMode(i);
        boolean flag;
        int l;
        int i1;
        int j1;
        if (k == 0x40000000)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        setFillViewport(flag);
        l = d.getChildCount();
        if (l > 1 && (k == 0x40000000 || k == 0x80000000))
        {
            if (l > 2)
            {
                e = (int)(0.4F * (float)android.view.View.MeasureSpec.getSize(i));
            } else
            {
                e = android.view.View.MeasureSpec.getSize(i) / 2;
            }
        } else
        {
            e = -1;
        }
        i1 = getMeasuredWidth();
        super.onMeasure(i, j);
        j1 = getMeasuredWidth();
        if (flag && i1 != j1)
        {
            setCurrentItem(f);
        }
    }

    public void setCurrentItem(int i)
    {
        f = i;
        int j = d.getChildCount();
        int k = 0;
        while (k < j) 
        {
            View view = d.getChildAt(k);
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
            view1 = d.getChildAt(i);
            if (b != null)
            {
                removeCallbacks(b);
            }
            b = new G(this, view1);
            post(b);
            k++;
        }
    }

    public void setOnTabReselectedListener(OnTabReselectedListener ontabreselectedlistener)
    {
        g = ontabreselectedlistener;
    }

}
