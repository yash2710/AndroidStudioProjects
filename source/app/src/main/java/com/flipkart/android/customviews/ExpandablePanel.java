// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.customviews;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

// Referenced classes of package com.flipkart.android.customviews:
//            o, q

public class ExpandablePanel extends LinearLayout
{

    private final int a;
    private final int b;
    private View c;
    private View d;
    private boolean e;
    private int f;
    private int g;
    private int h;
    private OnExpandListener i;

    public ExpandablePanel(Context context)
    {
        this(context, null);
    }

    public ExpandablePanel(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        e = false;
        f = 0;
        g = 0;
        h = 0;
        i = new o(this, (byte)0);
        TypedArray typedarray = context.obtainStyledAttributes(attributeset, com.flipkart.android.R.styleable.ExpandablePanel, 0, 0);
        f = (int)typedarray.getDimension(1, 0.0F);
        h = typedarray.getInteger(0, 500);
        int j = typedarray.getResourceId(3, 0);
        if (j == 0)
        {
            throw new IllegalArgumentException("The handle attribute is required and must refer to a valid child.");
        }
        int k = typedarray.getResourceId(2, 0);
        if (k == 0)
        {
            throw new IllegalArgumentException("The content attribute is required and must refer to a valid child.");
        } else
        {
            a = j;
            b = k;
            typedarray.recycle();
            return;
        }
    }

    static boolean a(ExpandablePanel expandablepanel)
    {
        return expandablepanel.e;
    }

    static boolean a(ExpandablePanel expandablepanel, boolean flag)
    {
        expandablepanel.e = flag;
        return flag;
    }

    static int b(ExpandablePanel expandablepanel)
    {
        return expandablepanel.g;
    }

    static int c(ExpandablePanel expandablepanel)
    {
        return expandablepanel.f;
    }

    static View d(ExpandablePanel expandablepanel)
    {
        return expandablepanel.c;
    }

    static View e(ExpandablePanel expandablepanel)
    {
        return expandablepanel.d;
    }

    static OnExpandListener f(ExpandablePanel expandablepanel)
    {
        return expandablepanel.i;
    }

    static int g(ExpandablePanel expandablepanel)
    {
        return expandablepanel.h;
    }

    public View getmContent()
    {
        return d;
    }

    public View getmHandle()
    {
        return c;
    }

    protected void onFinishInflate()
    {
        super.onFinishInflate();
        c = findViewById(a);
        if (c == null)
        {
            throw new IllegalArgumentException("The handle attribute is must refer to an existing child.");
        }
        d = findViewById(b);
        if (d == null)
        {
            throw new IllegalArgumentException("The content attribute must refer to an existing child.");
        } else
        {
            android.view.ViewGroup.LayoutParams layoutparams = d.getLayoutParams();
            layoutparams.height = f;
            d.setLayoutParams(layoutparams);
            c.setOnClickListener(new q(this, (byte)0));
            g = -1;
            return;
        }
    }

    protected void onMeasure(int j, int k)
    {
        d.measure(j, 0);
        super.onMeasure(j, k);
    }

    public void setAnimationDuration(int j)
    {
        h = j;
    }

    public void setCollapsedHeight(int j)
    {
        f = j;
    }

    public void setOnExpandListener(OnExpandListener onexpandlistener)
    {
        i = onexpandlistener;
    }

    public void setmContent(View view)
    {
        d = view;
    }

    public void setmHandle(View view)
    {
        c = view;
    }
}
