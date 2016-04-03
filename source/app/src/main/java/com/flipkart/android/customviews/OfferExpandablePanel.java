// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.customviews;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.flipkart.android.config.FlipkartPreferenceManager;
import com.flipkart.android.utils.StringUtils;

// Referenced classes of package com.flipkart.android.customviews:
//            u, v, w

public class OfferExpandablePanel extends LinearLayout
{

    private final int a;
    private final int b;
    private final int c;
    private TextView d;
    private LinearLayout e;
    private View f;
    private View g;
    private boolean h;
    private int i;
    private int j;
    private int k;
    private boolean l;
    private String m;
    private OnExpandListener n;

    public OfferExpandablePanel(Context context)
    {
        this(context, null);
    }

    public OfferExpandablePanel(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        h = false;
        i = 0;
        j = 0;
        k = 0;
        l = true;
        m = null;
        n = new u(this, (byte)0);
        TypedArray typedarray = context.obtainStyledAttributes(attributeset, com.flipkart.android.R.styleable.OfferExpandablePanel, 0, 0);
        i = (int)typedarray.getDimension(1, 0.0F);
        k = typedarray.getInteger(0, 500);
        l = typedarray.getString(4).equals("true");
        int i1 = typedarray.getResourceId(3, 0);
        if (i1 == 0)
        {
            throw new IllegalArgumentException("The handle attribute is required and must refer to a valid child.");
        }
        int j1 = typedarray.getResourceId(2, 0);
        if (j1 == 0)
        {
            throw new IllegalArgumentException("The content attribute is required and must refer to a valid child.");
        }
        int k1 = typedarray.getResourceId(5, 0);
        a = i1;
        b = j1;
        c = k1;
        m = (String)getTag();
        if (m != null && m.contains("specifications"))
        {
            i = FlipkartPreferenceManager.instance().getFadeOutViewHeight();
        }
        typedarray.recycle();
    }

    static boolean a(OfferExpandablePanel offerexpandablepanel)
    {
        return offerexpandablepanel.h;
    }

    static boolean a(OfferExpandablePanel offerexpandablepanel, boolean flag)
    {
        offerexpandablepanel.h = flag;
        return flag;
    }

    static View b(OfferExpandablePanel offerexpandablepanel)
    {
        return offerexpandablepanel.g;
    }

    static int c(OfferExpandablePanel offerexpandablepanel)
    {
        return offerexpandablepanel.j;
    }

    static int d(OfferExpandablePanel offerexpandablepanel)
    {
        return offerexpandablepanel.i;
    }

    static TextView e(OfferExpandablePanel offerexpandablepanel)
    {
        return offerexpandablepanel.d;
    }

    static LinearLayout f(OfferExpandablePanel offerexpandablepanel)
    {
        return offerexpandablepanel.e;
    }

    static OnExpandListener g(OfferExpandablePanel offerexpandablepanel)
    {
        return offerexpandablepanel.n;
    }

    static int h(OfferExpandablePanel offerexpandablepanel)
    {
        return offerexpandablepanel.k;
    }

    static boolean i(OfferExpandablePanel offerexpandablepanel)
    {
        return offerexpandablepanel.l;
    }

    static String j(OfferExpandablePanel offerexpandablepanel)
    {
        return offerexpandablepanel.m;
    }

    public void collapse()
    {
        if (h)
        {
            v v1 = new v(this, j, i);
            n.onCollapse(d, e);
            boolean flag;
            boolean flag1;
            if (l)
            {
                d.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0x7f0200dd, 0);
            } else
            if (m != null && m.contains("see_full_offer"))
            {
                d.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0x7f0200dd, 0);
                d.setText(StringUtils.getHyperLinkedText("see full offer"));
            } else
            {
                d.setCompoundDrawablesWithIntrinsicBounds(0x7f02011c, 0, 0x7f0200dd, 0);
            }
            v1.setDuration(k);
            e.startAnimation(v1);
            flag = h;
            flag1 = false;
            if (!flag)
            {
                flag1 = true;
            }
            h = flag1;
        }
    }

    protected void onFinishInflate()
    {
        super.onFinishInflate();
        g = findViewById(0x7f0a01d3);
        d = (TextView)findViewById(a);
        if (d == null)
        {
            throw new IllegalArgumentException("The handle attribute is must refer to an existing child.");
        }
        e = (LinearLayout)findViewById(b);
        if (e == null)
        {
            throw new IllegalArgumentException("The content attribute must refer to an existing child.");
        }
        f = findViewById(c);
        android.view.ViewGroup.LayoutParams layoutparams = e.getLayoutParams();
        layoutparams.height = i;
        e.setLayoutParams(layoutparams);
        if (f != null)
        {
            f.setOnClickListener(new w(this, (byte)0));
        } else
        {
            d.setOnClickListener(new w(this, (byte)0));
        }
        if (m != null)
        {
            if (m.contains("product_list"))
            {
                d.setCompoundDrawablesWithIntrinsicBounds(0x7f02011c, 0, 0x7f020145, 0);
            } else
            if (m.contains("product_page_offers"))
            {
                d.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0x7f020091, 0);
                return;
            }
        }
    }

    protected void onMeasure(int i1, int j1)
    {
        e.measure(i1, 0);
        j = e.getMeasuredHeight();
        if (j < i)
        {
            d.setVisibility(8);
        } else
        {
            d.setVisibility(0);
        }
        super.onMeasure(i1, j1);
    }

    public void readjustHeight()
    {
        v v1 = new v(this, j, i);
        v1.setDuration(k);
        e.startAnimation(v1);
    }

    public void setAnimationDuration(int i1)
    {
        k = i1;
    }

    public void setCollapsedHeight(int i1)
    {
        i = i1;
    }

    public void setOnExpandListener(OnExpandListener onexpandlistener)
    {
        n = onexpandlistener;
    }

    private class OnExpandListener
    {

        public abstract void onCollapse(View view, View view1);

        public abstract void onExpand(View view, View view1);
    }

}
