// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.viewpagerindicator;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.Locale;

// Referenced classes of package com.viewpagerindicator:
//            f, e, d

public class SmoothTabPageIndicator extends HorizontalScrollView
{

    private static final int a[] = {
        0x1010095, 0x1010098
    };
    private int A;
    private int B;
    private int C;
    private Locale D;
    private PageChangedListener E;
    private android.widget.LinearLayout.LayoutParams b;
    private android.widget.LinearLayout.LayoutParams c;
    private final f d;
    public android.support.v4.view.ViewPager.OnPageChangeListener delegatePageListener;
    private LinearLayout e;
    private ViewPager f;
    private boolean g;
    private int h;
    private int i;
    private float j;
    private Paint k;
    private Paint l;
    private int m;
    private int n;
    private int o;
    private boolean p;
    private boolean q;
    private int r;
    private int s;
    private int t;
    private int u;
    private int v;
    private int w;
    private int x;
    private int y;
    private Typeface z;

    public SmoothTabPageIndicator(Context context, AttributeSet attributeset, int i1, PageChangedListener pagechangedlistener)
    {
        super(context, attributeset, i1);
        d = new f(this, (byte)0);
        g = false;
        i = 0;
        j = 0.0F;
        m = 0xff666666;
        n = 0x1a000000;
        o = 0x1a000000;
        p = false;
        q = true;
        r = 12;
        s = 8;
        t = 2;
        u = 12;
        v = 24;
        w = 1;
        x = 12;
        y = 0xff666666;
        z = null;
        A = 1;
        B = 0;
        C = R.drawable.smooth_tab_background;
        setFillViewport(true);
        setWillNotDraw(false);
        E = pagechangedlistener;
        e = new LinearLayout(context);
        e.setOrientation(0);
        e.setLayoutParams(new android.widget.FrameLayout.LayoutParams(-1, -1));
        addView(e);
        android.util.DisplayMetrics displaymetrics = getResources().getDisplayMetrics();
        r = (int)TypedValue.applyDimension(1, r, displaymetrics);
        s = (int)TypedValue.applyDimension(1, s, displaymetrics);
        t = (int)TypedValue.applyDimension(1, t, displaymetrics);
        u = (int)TypedValue.applyDimension(1, u, displaymetrics);
        v = (int)TypedValue.applyDimension(1, v, displaymetrics);
        w = (int)TypedValue.applyDimension(1, w, displaymetrics);
        x = (int)TypedValue.applyDimension(2, x, displaymetrics);
        TypedArray typedarray = context.obtainStyledAttributes(attributeset, a);
        x = typedarray.getDimensionPixelSize(0, x);
        y = typedarray.getColor(1, y);
        typedarray.recycle();
        TypedArray typedarray1 = context.obtainStyledAttributes(attributeset, R.styleable.SmoothTabPageIndicator);
        m = typedarray1.getColor(0, m);
        n = typedarray1.getColor(1, n);
        o = typedarray1.getColor(2, o);
        s = typedarray1.getDimensionPixelSize(3, s);
        t = typedarray1.getDimensionPixelSize(4, t);
        u = typedarray1.getDimensionPixelSize(5, u);
        v = typedarray1.getDimensionPixelSize(6, v);
        C = typedarray1.getResourceId(8, C);
        p = typedarray1.getBoolean(9, p);
        r = typedarray1.getDimensionPixelSize(7, r);
        q = typedarray1.getBoolean(10, q);
        typedarray1.recycle();
        k = new Paint();
        k.setAntiAlias(true);
        k.setStyle(android.graphics.Paint.Style.FILL);
        l = new Paint();
        l.setAntiAlias(true);
        l.setStrokeWidth(w);
        b = new android.widget.LinearLayout.LayoutParams(-2, -1);
        c = new android.widget.LinearLayout.LayoutParams(0, -1, 1.0F);
        if (D == null)
        {
            D = getResources().getConfiguration().locale;
        }
    }

    public SmoothTabPageIndicator(Context context, AttributeSet attributeset, PageChangedListener pagechangedlistener)
    {
        this(context, attributeset, 0, pagechangedlistener);
    }

    public SmoothTabPageIndicator(Context context, PageChangedListener pagechangedlistener)
    {
        this(context, null, pagechangedlistener);
    }

    static float a(SmoothTabPageIndicator smoothtabpageindicator, float f1)
    {
        smoothtabpageindicator.j = f1;
        return f1;
    }

    static int a(SmoothTabPageIndicator smoothtabpageindicator, int i1)
    {
        smoothtabpageindicator.i = i1;
        return i1;
    }

    static ViewPager a(SmoothTabPageIndicator smoothtabpageindicator)
    {
        return smoothtabpageindicator.f;
    }

    private void a()
    {
        int i1 = 0;
        while (i1 < h) 
        {
            View view = e.getChildAt(i1);
            view.setBackgroundResource(C);
            if (!(view instanceof TextView))
            {
                continue;
            }
            TextView textview = (TextView)view;
            textview.setTextSize(0, x);
            textview.setTypeface(z, A);
            textview.setTextColor(y);
            if (q)
            {
                if (android.os.Build.VERSION.SDK_INT >= 14)
                {
                    textview.setAllCaps(true);
                } else
                {
                    textview.setText(textview.getText().toString().toUpperCase(D));
                }
            }
            i1++;
        }
    }

    private void a(int i1, View view)
    {
        view.setFocusable(true);
        view.setOnClickListener(new e(this, i1));
        view.setPadding(v, 0, v, 0);
        LinearLayout linearlayout = e;
        android.widget.LinearLayout.LayoutParams layoutparams;
        if (p)
        {
            layoutparams = c;
        } else
        {
            layoutparams = b;
        }
        linearlayout.addView(view, i1, layoutparams);
    }

    static void a(SmoothTabPageIndicator smoothtabpageindicator, int i1, int j1)
    {
        if (smoothtabpageindicator.h != 0)
        {
            int k1;
            if (smoothtabpageindicator.e != null && smoothtabpageindicator.e.getChildAt(i1) != null)
            {
                k1 = j1 + smoothtabpageindicator.e.getChildAt(i1).getLeft();
            } else
            {
                k1 = 0;
            }
            if (i1 > 0 || j1 > 0)
            {
                k1 -= smoothtabpageindicator.r;
            }
            if (k1 != smoothtabpageindicator.B)
            {
                smoothtabpageindicator.B = k1;
                smoothtabpageindicator.scrollTo(k1, 0);
            }
        }
    }

    static int b(SmoothTabPageIndicator smoothtabpageindicator)
    {
        return smoothtabpageindicator.i;
    }

    static LinearLayout c(SmoothTabPageIndicator smoothtabpageindicator)
    {
        return smoothtabpageindicator.e;
    }

    static PageChangedListener d(SmoothTabPageIndicator smoothtabpageindicator)
    {
        return smoothtabpageindicator.E;
    }

    static boolean e(SmoothTabPageIndicator smoothtabpageindicator)
    {
        return smoothtabpageindicator.g;
    }

    public int getDividerColor()
    {
        return o;
    }

    public int getDividerPadding()
    {
        return u;
    }

    public int getIndicatorColor()
    {
        return m;
    }

    public int getIndicatorHeight()
    {
        return s;
    }

    public int getScrollOffset()
    {
        return r;
    }

    public boolean getShouldExpand()
    {
        return p;
    }

    public int getTabBackground()
    {
        return C;
    }

    public int getTabPaddingLeftRight()
    {
        return v;
    }

    public int getTextColor()
    {
        return y;
    }

    public int getTextSize()
    {
        return x;
    }

    public int getUnderlineColor()
    {
        return n;
    }

    public int getUnderlineHeight()
    {
        return t;
    }

    public boolean ifCyclicEnabeled(boolean flag)
    {
        return g;
    }

    public boolean isTextAllCaps()
    {
        return q;
    }

    public void notifyDataSetChanged()
    {
        e.removeAllViews();
        h = f.getAdapter().getCount();
        int i1 = 0;
        while (i1 < h) 
        {
            if (f.getAdapter() instanceof IconTabProvider)
            {
                int j1 = ((IconTabProvider)f.getAdapter()).getPageIconResId(i1);
                ImageButton imagebutton = new ImageButton(getContext());
                imagebutton.setImageResource(j1);
                a(i1, imagebutton);
            } else
            {
                String s1 = f.getAdapter().getPageTitle(i1).toString();
                TextView textview = new TextView(getContext());
                textview.setText(s1);
                textview.setGravity(17);
                textview.setContentDescription((new StringBuilder("content_")).append(i1).toString());
                textview.setSingleLine();
                a(i1, textview);
            }
            i1++;
        }
        a();
        getViewTreeObserver().addOnGlobalLayoutListener(new d(this));
    }

    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        if (!isInEditMode() && h != 0)
        {
            int i1 = getHeight();
            k.setColor(m);
            View view = e.getChildAt(i);
            float f1 = view.getLeft();
            float f2 = view.getRight();
            if (j > 0.0F && i < -1 + h)
            {
                View view2 = e.getChildAt(1 + i);
                float f3 = view2.getLeft();
                float f4 = view2.getRight();
                f1 = f3 * j + f1 * (1.0F - j);
                f2 = f4 * j + f2 * (1.0F - j);
            }
            canvas.drawRect(f1, i1 - s, f2, i1, k);
            k.setColor(n);
            canvas.drawRect(0.0F, i1 - t, e.getWidth(), i1, k);
            l.setColor(o);
            int j1 = 0;
            while (j1 < -1 + h) 
            {
                View view1 = e.getChildAt(j1);
                canvas.drawLine(view1.getRight(), u, view1.getRight(), i1 - u, l);
                j1++;
            }
        }
    }

    public void onRestoreInstanceState(Parcelable parcelable)
    {
        SavedState savedstate = (SavedState)parcelable;
        super.onRestoreInstanceState(savedstate.getSuperState());
        i = savedstate.a;
        requestLayout();
    }

    public Parcelable onSaveInstanceState()
    {
        SavedState savedstate = new SavedState(super.onSaveInstanceState());
        savedstate.a = i;
        return savedstate;
    }

    public void setAllCaps(boolean flag)
    {
        q = flag;
    }

    public void setCyclicEnabeled(boolean flag)
    {
        g = flag;
    }

    public void setDividerColor(int i1)
    {
        o = i1;
        invalidate();
    }

    public void setDividerColorResource(int i1)
    {
        o = getResources().getColor(i1);
        invalidate();
    }

    public void setDividerPadding(int i1)
    {
        u = i1;
        invalidate();
    }

    public void setIndicatorColor(int i1)
    {
        m = i1;
        invalidate();
    }

    public void setIndicatorColorResource(int i1)
    {
        m = getResources().getColor(i1);
        invalidate();
    }

    public void setIndicatorHeight(int i1)
    {
        s = i1;
        invalidate();
    }

    public void setOnPageChangeListener(android.support.v4.view.ViewPager.OnPageChangeListener onpagechangelistener)
    {
        delegatePageListener = onpagechangelistener;
    }

    public void setScrollOffset(int i1)
    {
        r = i1;
        invalidate();
    }

    public void setShouldExpand(boolean flag)
    {
        p = flag;
        requestLayout();
    }

    public void setTabBackground(int i1)
    {
        C = i1;
    }

    public void setTabPaddingLeftRight(int i1)
    {
        v = i1;
        a();
    }

    public void setTextColor(int i1)
    {
        y = i1;
        a();
    }

    public void setTextColorResource(int i1)
    {
        y = getResources().getColor(i1);
        a();
    }

    public void setTextSize(int i1)
    {
        x = i1;
        a();
    }

    public void setTypeface(Typeface typeface, int i1)
    {
        z = typeface;
        A = i1;
        a();
    }

    public void setUnderlineColor(int i1)
    {
        n = i1;
        invalidate();
    }

    public void setUnderlineColorResource(int i1)
    {
        n = getResources().getColor(i1);
        invalidate();
    }

    public void setUnderlineHeight(int i1)
    {
        t = i1;
        invalidate();
    }

    public void setViewPager(ViewPager viewpager)
    {
        f = viewpager;
        if (viewpager.getAdapter() == null)
        {
            throw new IllegalStateException("ViewPager does not have adapter instance.");
        } else
        {
            viewpager.setOnPageChangeListener(d);
            notifyDataSetChanged();
            return;
        }
    }


    private class IconTabProvider
    {

        public abstract int getPageIconResId(int i1);
    }


    private class SavedState extends android.view.View.BaseSavedState
    {

        public static final android.os.Parcelable.Creator CREATOR = new g();
        int a;

        public void writeToParcel(Parcel parcel, int i1)
        {
            super.writeToParcel(parcel, i1);
            parcel.writeInt(a);
        }


        private SavedState(Parcel parcel)
        {
            super(parcel);
            a = parcel.readInt();
        }

        SavedState(Parcel parcel, byte byte0)
        {
            this(parcel);
        }

        public SavedState(Parcelable parcelable)
        {
            super(parcelable);
        }
    }

}
