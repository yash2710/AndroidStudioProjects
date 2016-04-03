// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.viewpagerindicator;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Parcelable;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewConfigurationCompat;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;

// Referenced classes of package com.viewpagerindicator:
//            PageIndicator, m, n

public class UnderlinePageIndicator extends View
    implements PageIndicator
{

    private final Paint a;
    private boolean b;
    private int c;
    private int d;
    private int e;
    private ViewPager f;
    private android.support.v4.view.ViewPager.OnPageChangeListener g;
    private int h;
    private int i;
    private float j;
    private int k;
    private float l;
    private int m;
    private boolean n;
    private final Runnable o;

    public UnderlinePageIndicator(Context context)
    {
        this(context, null);
    }

    public UnderlinePageIndicator(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, R.attr.vpiUnderlinePageIndicatorStyle);
    }

    public UnderlinePageIndicator(Context context, AttributeSet attributeset, int i1)
    {
        super(context, attributeset, i1);
        a = new Paint(1);
        l = -1F;
        m = -1;
        o = new m(this);
        if (isInEditMode())
        {
            return;
        }
        Resources resources = getResources();
        boolean flag = resources.getBoolean(R.bool.default_underline_indicator_fades);
        int j1 = resources.getInteger(R.integer.default_underline_indicator_fade_delay);
        int k1 = resources.getInteger(R.integer.default_underline_indicator_fade_length);
        int l1 = resources.getColor(R.color.default_underline_indicator_selected_color);
        TypedArray typedarray = context.obtainStyledAttributes(attributeset, R.styleable.UnderlinePageIndicator, i1, 0);
        setFades(typedarray.getBoolean(2, flag));
        setSelectedColor(typedarray.getColor(1, l1));
        setFadeDelay(typedarray.getInteger(3, j1));
        setFadeLength(typedarray.getInteger(4, k1));
        android.graphics.drawable.Drawable drawable = typedarray.getDrawable(0);
        if (drawable != null)
        {
            setBackgroundDrawable(drawable);
        }
        typedarray.recycle();
        k = ViewConfigurationCompat.getScaledPagingTouchSlop(ViewConfiguration.get(context));
    }

    static boolean a(UnderlinePageIndicator underlinepageindicator)
    {
        return underlinepageindicator.b;
    }

    static Paint b(UnderlinePageIndicator underlinepageindicator)
    {
        return underlinepageindicator.a;
    }

    static int c(UnderlinePageIndicator underlinepageindicator)
    {
        return underlinepageindicator.e;
    }

    static Runnable d(UnderlinePageIndicator underlinepageindicator)
    {
        return underlinepageindicator.o;
    }

    public int getFadeDelay()
    {
        return c;
    }

    public int getFadeLength()
    {
        return d;
    }

    public boolean getFades()
    {
        return b;
    }

    public int getSelectedColor()
    {
        return a.getColor();
    }

    public void notifyDataSetChanged()
    {
        invalidate();
    }

    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        int i1;
        if (f != null)
        {
            if ((i1 = f.getAdapter().getCount()) != 0)
            {
                if (i >= i1)
                {
                    setCurrentItem(i1 - 1);
                    return;
                } else
                {
                    int j1 = getPaddingLeft();
                    float f1 = (float)(getWidth() - j1 - getPaddingRight()) / (1.0F * (float)i1);
                    float f2 = (float)j1 + f1 * ((float)i + j);
                    float f3 = f2 + f1;
                    canvas.drawRect(f2, getPaddingTop(), f3, getHeight() - getPaddingBottom(), a);
                    return;
                }
            }
        }
    }

    public void onPageScrollStateChanged(int i1)
    {
        h = i1;
        if (g != null)
        {
            g.onPageScrollStateChanged(i1);
        }
    }

    public void onPageScrolled(int i1, float f1, int j1)
    {
        i = i1;
        j = f1;
        if (b)
        {
            if (j1 > 0)
            {
                removeCallbacks(o);
                a.setAlpha(255);
            } else
            {
                int _tmp = h;
            }
        }
        invalidate();
        if (g != null)
        {
            g.onPageScrolled(i1, f1, j1);
        }
    }

    public void onPageSelected(int i1)
    {
        if (h == 0)
        {
            i = i1;
            j = 0.0F;
            invalidate();
            o.run();
        }
        if (g != null)
        {
            g.onPageSelected(i1);
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

    public boolean onTouchEvent(MotionEvent motionevent)
    {
        if (!super.onTouchEvent(motionevent)) goto _L2; else goto _L1
_L1:
        return true;
_L2:
        int i1;
        if (f == null || f.getAdapter().getCount() == 0)
        {
            return false;
        }
        i1 = 0xff & motionevent.getAction();
        i1;
        JVM INSTR tableswitch 0 6: default 84
    //                   0 86
    //                   1 198
    //                   2 105
    //                   3 198
    //                   4 84
    //                   5 352
    //                   6 380;
           goto _L3 _L4 _L5 _L6 _L5 _L3 _L7 _L8
_L3:
        return true;
_L4:
        m = MotionEventCompat.getPointerId(motionevent, 0);
        l = motionevent.getX();
        return true;
_L6:
        float f3 = MotionEventCompat.getX(motionevent, MotionEventCompat.findPointerIndex(motionevent, m));
        float f4 = f3 - l;
        if (!n && Math.abs(f4) > (float)k)
        {
            n = true;
        }
        if (n)
        {
            l = f3;
            if (f.isFakeDragging() || f.beginFakeDrag())
            {
                f.fakeDragBy(f4);
                return true;
            }
        }
        continue; /* Loop/switch isn't completed */
_L5:
        if (n)
        {
            break; /* Loop/switch isn't completed */
        }
        int i2 = f.getAdapter().getCount();
        int j2 = getWidth();
        float f1 = (float)j2 / 2.0F;
        float f2 = (float)j2 / 6F;
        if (i > 0 && motionevent.getX() < f1 - f2)
        {
            if (i1 != 3)
            {
                f.setCurrentItem(-1 + i);
                return true;
            }
            continue; /* Loop/switch isn't completed */
        }
        if (i >= i2 - 1 || motionevent.getX() <= f2 + f1)
        {
            break; /* Loop/switch isn't completed */
        }
        if (i1 != 3)
        {
            f.setCurrentItem(1 + i);
            return true;
        }
        if (true) goto _L1; else goto _L9
_L9:
        n = false;
        m = -1;
        if (f.isFakeDragging())
        {
            f.endFakeDrag();
            return true;
        }
          goto _L1
_L7:
        int l1 = MotionEventCompat.getActionIndex(motionevent);
        l = MotionEventCompat.getX(motionevent, l1);
        m = MotionEventCompat.getPointerId(motionevent, l1);
        return true;
_L8:
        int j1 = MotionEventCompat.getActionIndex(motionevent);
        if (MotionEventCompat.getPointerId(motionevent, j1) == m)
        {
            int k1 = 0;
            if (j1 == 0)
            {
                k1 = 1;
            }
            m = MotionEventCompat.getPointerId(motionevent, k1);
        }
        l = MotionEventCompat.getX(motionevent, MotionEventCompat.findPointerIndex(motionevent, m));
        return true;
    }

    public void setCurrentItem(int i1)
    {
        if (f == null)
        {
            throw new IllegalStateException("ViewPager has not been bound.");
        } else
        {
            f.setCurrentItem(i1);
            i = i1;
            invalidate();
            return;
        }
    }

    public void setFadeDelay(int i1)
    {
        c = i1;
    }

    public void setFadeLength(int i1)
    {
        d = i1;
        e = 255 / (d / 30);
    }

    public void setFades(boolean flag)
    {
label0:
        {
            if (flag != b)
            {
                b = flag;
                if (!flag)
                {
                    break label0;
                }
                post(o);
            }
            return;
        }
        removeCallbacks(o);
        a.setAlpha(255);
        invalidate();
    }

    public void setOnPageChangeListener(android.support.v4.view.ViewPager.OnPageChangeListener onpagechangelistener)
    {
        g = onpagechangelistener;
    }

    public void setSelectedColor(int i1)
    {
        a.setColor(i1);
        invalidate();
    }

    public void setViewPager(ViewPager viewpager)
    {
        if (f == viewpager)
        {
            return;
        }
        if (f != null)
        {
            f.setOnPageChangeListener(null);
        }
        if (viewpager.getAdapter() == null)
        {
            throw new IllegalStateException("ViewPager does not have adapter instance.");
        } else
        {
            f = viewpager;
            f.setOnPageChangeListener(this);
            invalidate();
            post(new n(this));
            return;
        }
    }

    public void setViewPager(ViewPager viewpager, int i1)
    {
        setViewPager(viewpager);
        setCurrentItem(i1);
    }

    private class SavedState extends android.view.View.BaseSavedState
    {

        public static final android.os.Parcelable.Creator CREATOR = new o();
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
