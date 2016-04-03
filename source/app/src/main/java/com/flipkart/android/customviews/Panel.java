// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.customviews;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.View;
import android.view.animation.Interpolator;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import com.flipkart.logging.FkLogger;

// Referenced classes of package com.flipkart.android.customviews:
//            y, z, A, B, 
//            D, C

public class Panel extends LinearLayout
{

    public static final int BOTTOM = 1;
    public static final int LEFT = 2;
    public static final int RIGHT = 3;
    public static final int TOP;
    private android.view.animation.Animation.AnimationListener A;
    Runnable a;
    private boolean b;
    private int c;
    private int d;
    private boolean e;
    private int f;
    private int g;
    private View h;
    private View i;
    private Drawable j;
    private Drawable k;
    private float l;
    private float m;
    private float n;
    private OnPanelListener o;
    private D p;
    private Interpolator q;
    private GestureDetector r;
    private int s;
    private int t;
    private int u;
    private float v;
    private C w;
    private boolean x;
    private android.view.View.OnTouchListener y;
    private android.view.View.OnClickListener z;

    public Panel(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        y = new y(this);
        z = new z(this);
        a = new A(this);
        A = new B(this);
        TypedArray typedarray = context.obtainStyledAttributes(attributeset, com.flipkart.android.R.styleable.Panel);
        d = typedarray.getInteger(0, 750);
        c = typedarray.getInteger(3, 1);
        e = typedarray.getBoolean(4, false);
        v = typedarray.getFraction(5, 0, 1, 0.0F);
        if (v < 0.0F || v > 1.0F)
        {
            v = 0.0F;
            FkLogger.warn("Panel", (new StringBuilder()).append(typedarray.getPositionDescription()).append(": weight must be > 0 and <= 1").toString());
        }
        j = typedarray.getDrawable(6);
        k = typedarray.getDrawable(7);
        f = typedarray.getResourceId(2, 0);
        int i1 = f;
        IllegalArgumentException illegalargumentexception = null;
        if (i1 == 0)
        {
            illegalargumentexception = new IllegalArgumentException((new StringBuilder()).append(typedarray.getPositionDescription()).append(": The handle attribute is required and must refer to a valid child.").toString());
        }
        g = typedarray.getResourceId(1, 0);
        if (g == 0)
        {
            illegalargumentexception = new IllegalArgumentException((new StringBuilder()).append(typedarray.getPositionDescription()).append(": The content attribute is required and must refer to a valid child.").toString());
        }
        typedarray.recycle();
        if (illegalargumentexception != null)
        {
            throw illegalargumentexception;
        }
        int j1;
        if (c == 0 || c == 1)
        {
            j1 = 1;
        } else
        {
            j1 = 0;
        }
        u = j1;
        setOrientation(u);
        p = D.c;
        w = new C(this);
        r = new GestureDetector(w);
        r.setIsLongpressEnabled(false);
        setBaselineAligned(false);
    }

    static float a(Panel panel, float f1)
    {
        panel.l = f1;
        return f1;
    }

    static float a(Panel panel, float f1, int i1, int j1)
    {
        return Math.min(Math.max(f1, i1), j1);
    }

    static D a(Panel panel)
    {
        return panel.p;
    }

    static D a(Panel panel, D d1)
    {
        panel.p = d1;
        return d1;
    }

    private void a()
    {
        if (!b || k == null) goto _L2; else goto _L1
_L1:
        h.setBackgroundDrawable(k);
_L4:
        if (o != null)
        {
            if (!b)
            {
                break; /* Loop/switch isn't completed */
            }
            o.onPanelClosed(this);
        }
        return;
_L2:
        if (!b && j != null)
        {
            h.setBackgroundDrawable(j);
        }
        if (true) goto _L4; else goto _L3
_L3:
        o.onPanelOpened(this);
        return;
    }

    static boolean a(Panel panel, boolean flag)
    {
        panel.b = flag;
        return flag;
    }

    static float b(Panel panel, float f1)
    {
        panel.m = f1;
        return f1;
    }

    static boolean b(Panel panel)
    {
        return panel.x;
    }

    static float c(Panel panel, float f1)
    {
        panel.n = f1;
        return f1;
    }

    static View c(Panel panel)
    {
        return panel.i;
    }

    static int d(Panel panel)
    {
        return panel.u;
    }

    static int e(Panel panel)
    {
        return panel.c;
    }

    static int f(Panel panel)
    {
        return panel.t;
    }

    static int g(Panel panel)
    {
        return panel.s;
    }

    static C h(Panel panel)
    {
        return panel.w;
    }

    static GestureDetector i(Panel panel)
    {
        return panel.r;
    }

    static float j(Panel panel)
    {
        return panel.n;
    }

    static boolean k(Panel panel)
    {
        return panel.b;
    }

    static float l(Panel panel)
    {
        return panel.m;
    }

    static boolean m(Panel panel)
    {
        return panel.e;
    }

    static int n(Panel panel)
    {
        return panel.d;
    }

    static float o(Panel panel)
    {
        return panel.l;
    }

    static void p(Panel panel)
    {
        panel.a();
    }

    static android.view.animation.Animation.AnimationListener q(Panel panel)
    {
        return panel.A;
    }

    static Interpolator r(Panel panel)
    {
        return panel.q;
    }

    protected void dispatchDraw(Canvas canvas)
    {
        if (p == D.a && !b)
        {
            int i1;
            if (u == 1)
            {
                i1 = s;
            } else
            {
                i1 = t;
            }
            if (c == 2 || c == 0)
            {
                i1 = -i1;
            }
            if (u == 1)
            {
                canvas.translate(0.0F, i1);
            } else
            {
                canvas.translate(i1, 0.0F);
            }
        }
        if (p == D.d || p == D.e)
        {
            canvas.translate(l, m);
        }
        super.dispatchDraw(canvas);
    }

    public View getContent()
    {
        return i;
    }

    public View getHandle()
    {
        return h;
    }

    public boolean initChange()
    {
        if (p != D.c)
        {
            return false;
        }
        p = D.a;
        boolean flag;
        if (i.getVisibility() == 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        b = flag;
        if (!b)
        {
            i.setVisibility(0);
        }
        return true;
    }

    public boolean isOpen()
    {
        return i.getVisibility() == 0;
    }

    protected void onAttachedToWindow()
    {
        super.onAttachedToWindow();
        android.view.ViewParent viewparent = getParent();
        if (viewparent != null && (viewparent instanceof FrameLayout))
        {
            x = true;
        }
    }

    protected void onFinishInflate()
    {
        super.onFinishInflate();
        h = findViewById(f);
        if (h == null)
        {
            String s2 = getResources().getResourceEntryName(f);
            throw new RuntimeException((new StringBuilder("Your Panel must have a child View whose id attribute is 'R.id.")).append(s2).append("'").toString());
        }
        h.setOnTouchListener(y);
        h.setOnClickListener(z);
        i = findViewById(g);
        if (i == null)
        {
            String s1 = getResources().getResourceEntryName(f);
            throw new RuntimeException((new StringBuilder("Your Panel must have a child View whose id attribute is 'R.id.")).append(s1).append("'").toString());
        }
        removeView(h);
        removeView(i);
        if (c == 0 || c == 2)
        {
            addView(i);
            addView(h);
        } else
        {
            addView(h);
            addView(i);
        }
        if (k != null)
        {
            h.setBackgroundDrawable(k);
        }
        i.setClickable(true);
        i.setVisibility(8);
        if (v > 0.0F)
        {
            android.view.ViewGroup.LayoutParams layoutparams = i.getLayoutParams();
            if (u == 1)
            {
                layoutparams.height = -1;
            } else
            {
                layoutparams.width = -1;
            }
            i.setLayoutParams(layoutparams);
        }
    }

    protected void onLayout(boolean flag, int i1, int j1, int k1, int l1)
    {
        super.onLayout(flag, i1, j1, k1, l1);
        t = i.getWidth();
        s = i.getHeight();
    }

    protected void onMeasure(int i1, int j1)
    {
        if (v > 0.0F && i.getVisibility() == 0)
        {
            View view = (View)getParent();
            if (view != null)
            {
                if (u == 1)
                {
                    j1 = android.view.View.MeasureSpec.makeMeasureSpec((int)((float)view.getHeight() * v), 0x40000000);
                } else
                {
                    i1 = android.view.View.MeasureSpec.makeMeasureSpec((int)((float)view.getWidth() * v), 0x40000000);
                }
            }
        }
        super.onMeasure(i1, j1);
    }

    public void setInterpolator(Interpolator interpolator)
    {
        q = interpolator;
    }

    public void setOnPanelListener(OnPanelListener onpanellistener)
    {
        o = onpanellistener;
    }

    public boolean setOpen(boolean flag, boolean flag1)
    {
        if (p == D.c && flag ^ isOpen())
        {
            boolean flag2;
            if (!flag)
            {
                flag2 = true;
            } else
            {
                flag2 = false;
            }
            b = flag2;
            if (flag1)
            {
                p = D.a;
                if (!b)
                {
                    i.setVisibility(0);
                }
                post(a);
                return true;
            }
            View view = i;
            int i1 = 0;
            if (!flag)
            {
                i1 = 8;
            }
            view.setVisibility(i1);
            a();
            return true;
        } else
        {
            return false;
        }
    }

    private class OnPanelListener
    {

        public abstract void onPanelClosed(Panel panel);

        public abstract void onPanelOpened(Panel panel);
    }

}
