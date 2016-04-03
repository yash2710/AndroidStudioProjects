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
import android.util.FloatMath;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;

// Referenced classes of package com.viewpagerindicator:
//            PageIndicator

public class LinePageIndicator extends View
    implements PageIndicator
{

    private final Paint a;
    private final Paint b;
    private ViewPager c;
    private android.support.v4.view.ViewPager.OnPageChangeListener d;
    private int e;
    private boolean f;
    private float g;
    private float h;
    private int i;
    private float j;
    private int k;
    private boolean l;

    public LinePageIndicator(Context context)
    {
        this(context, null);
    }

    public LinePageIndicator(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, R.attr.vpiLinePageIndicatorStyle);
    }

    public LinePageIndicator(Context context, AttributeSet attributeset, int i1)
    {
        super(context, attributeset, i1);
        a = new Paint(1);
        b = new Paint(1);
        j = -1F;
        k = -1;
        if (isInEditMode())
        {
            return;
        }
        Resources resources = getResources();
        int j1 = resources.getColor(R.color.default_line_indicator_selected_color);
        int k1 = resources.getColor(R.color.default_line_indicator_unselected_color);
        float f1 = resources.getDimension(R.dimen.default_line_indicator_line_width);
        float f2 = resources.getDimension(R.dimen.default_line_indicator_gap_width);
        float f3 = resources.getDimension(R.dimen.default_line_indicator_stroke_width);
        boolean flag = resources.getBoolean(R.bool.default_line_indicator_centered);
        TypedArray typedarray = context.obtainStyledAttributes(attributeset, R.styleable.LinePageIndicator, i1, 0);
        f = typedarray.getBoolean(1, flag);
        g = typedarray.getDimension(5, f1);
        h = typedarray.getDimension(6, f2);
        setStrokeWidth(typedarray.getDimension(3, f3));
        a.setColor(typedarray.getColor(4, k1));
        b.setColor(typedarray.getColor(2, j1));
        android.graphics.drawable.Drawable drawable = typedarray.getDrawable(0);
        if (drawable != null)
        {
            setBackgroundDrawable(drawable);
        }
        typedarray.recycle();
        i = ViewConfigurationCompat.getScaledPagingTouchSlop(ViewConfiguration.get(context));
    }

    public float getGapWidth()
    {
        return h;
    }

    public float getLineWidth()
    {
        return g;
    }

    public int getSelectedColor()
    {
        return b.getColor();
    }

    public float getStrokeWidth()
    {
        return b.getStrokeWidth();
    }

    public int getUnselectedColor()
    {
        return a.getColor();
    }

    public boolean isCentered()
    {
        return f;
    }

    public void notifyDataSetChanged()
    {
        invalidate();
    }

    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        if (c != null) goto _L2; else goto _L1
_L1:
        int i1;
        return;
_L2:
        if ((i1 = c.getAdapter().getCount()) != 0)
        {
            if (e >= i1)
            {
                setCurrentItem(i1 - 1);
                return;
            }
            float f1 = g + h;
            float f2 = f1 * (float)i1 - h;
            float f3 = getPaddingTop();
            float f4 = getPaddingLeft();
            float f5 = getPaddingRight();
            float f6 = f3 + ((float)getHeight() - f3 - (float)getPaddingBottom()) / 2.0F;
            float f7;
            int j1;
            if (f)
            {
                f7 = f4 + (((float)getWidth() - f4 - f5) / 2.0F - f2 / 2.0F);
            } else
            {
                f7 = f4;
            }
            j1 = 0;
            while (j1 < i1) 
            {
                float f8 = f7 + f1 * (float)j1;
                float f9 = f8 + g;
                Paint paint;
                if (j1 == e)
                {
                    paint = b;
                } else
                {
                    paint = a;
                }
                canvas.drawLine(f8, f6, f9, f6, paint);
                j1++;
            }
        }
        if (true) goto _L1; else goto _L3
_L3:
    }

    protected void onMeasure(int i1, int j1)
    {
        int k1 = android.view.View.MeasureSpec.getMode(i1);
        int l1 = android.view.View.MeasureSpec.getSize(i1);
        float f1;
        int i2;
        int j2;
        int k2;
        float f2;
        if (k1 == 0x40000000 || c == null)
        {
            f1 = l1;
        } else
        {
            int l2 = c.getAdapter().getCount();
            f1 = (float)(getPaddingLeft() + getPaddingRight()) + (float)l2 * g + (float)(l2 - 1) * h;
            if (k1 == 0x80000000)
            {
                f1 = Math.min(f1, l1);
            }
        }
        i2 = (int)FloatMath.ceil(f1);
        j2 = android.view.View.MeasureSpec.getMode(j1);
        k2 = android.view.View.MeasureSpec.getSize(j1);
        if (j2 == 0x40000000)
        {
            f2 = k2;
        } else
        {
            f2 = b.getStrokeWidth() + (float)getPaddingTop() + (float)getPaddingBottom();
            if (j2 == 0x80000000)
            {
                f2 = Math.min(f2, k2);
            }
        }
        setMeasuredDimension(i2, (int)FloatMath.ceil(f2));
    }

    public void onPageScrollStateChanged(int i1)
    {
        if (d != null)
        {
            d.onPageScrollStateChanged(i1);
        }
    }

    public void onPageScrolled(int i1, float f1, int j1)
    {
        if (d != null)
        {
            d.onPageScrolled(i1, f1, j1);
        }
    }

    public void onPageSelected(int i1)
    {
        e = i1;
        invalidate();
        if (d != null)
        {
            d.onPageSelected(i1);
        }
    }

    public void onRestoreInstanceState(Parcelable parcelable)
    {
        SavedState savedstate = (SavedState)parcelable;
        super.onRestoreInstanceState(savedstate.getSuperState());
        e = savedstate.a;
        requestLayout();
    }

    public Parcelable onSaveInstanceState()
    {
        SavedState savedstate = new SavedState(super.onSaveInstanceState());
        savedstate.a = e;
        return savedstate;
    }

    public boolean onTouchEvent(MotionEvent motionevent)
    {
        if (!super.onTouchEvent(motionevent)) goto _L2; else goto _L1
_L1:
        return true;
_L2:
        int i1;
        if (c == null || c.getAdapter().getCount() == 0)
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
        k = MotionEventCompat.getPointerId(motionevent, 0);
        j = motionevent.getX();
        return true;
_L6:
        float f3 = MotionEventCompat.getX(motionevent, MotionEventCompat.findPointerIndex(motionevent, k));
        float f4 = f3 - j;
        if (!l && Math.abs(f4) > (float)i)
        {
            l = true;
        }
        if (l)
        {
            j = f3;
            if (c.isFakeDragging() || c.beginFakeDrag())
            {
                c.fakeDragBy(f4);
                return true;
            }
        }
        continue; /* Loop/switch isn't completed */
_L5:
        if (l)
        {
            break; /* Loop/switch isn't completed */
        }
        int i2 = c.getAdapter().getCount();
        int j2 = getWidth();
        float f1 = (float)j2 / 2.0F;
        float f2 = (float)j2 / 6F;
        if (e > 0 && motionevent.getX() < f1 - f2)
        {
            if (i1 != 3)
            {
                c.setCurrentItem(-1 + e);
                return true;
            }
            continue; /* Loop/switch isn't completed */
        }
        if (e >= i2 - 1 || motionevent.getX() <= f2 + f1)
        {
            break; /* Loop/switch isn't completed */
        }
        if (i1 != 3)
        {
            c.setCurrentItem(1 + e);
            return true;
        }
        if (true) goto _L1; else goto _L9
_L9:
        l = false;
        k = -1;
        if (c.isFakeDragging())
        {
            c.endFakeDrag();
            return true;
        }
          goto _L1
_L7:
        int l1 = MotionEventCompat.getActionIndex(motionevent);
        j = MotionEventCompat.getX(motionevent, l1);
        k = MotionEventCompat.getPointerId(motionevent, l1);
        return true;
_L8:
        int j1 = MotionEventCompat.getActionIndex(motionevent);
        if (MotionEventCompat.getPointerId(motionevent, j1) == k)
        {
            int k1 = 0;
            if (j1 == 0)
            {
                k1 = 1;
            }
            k = MotionEventCompat.getPointerId(motionevent, k1);
        }
        j = MotionEventCompat.getX(motionevent, MotionEventCompat.findPointerIndex(motionevent, k));
        return true;
    }

    public void setCentered(boolean flag)
    {
        f = flag;
        invalidate();
    }

    public void setCurrentItem(int i1)
    {
        if (c == null)
        {
            throw new IllegalStateException("ViewPager has not been bound.");
        } else
        {
            c.setCurrentItem(i1);
            e = i1;
            invalidate();
            return;
        }
    }

    public void setGapWidth(float f1)
    {
        h = f1;
        invalidate();
    }

    public void setLineWidth(float f1)
    {
        g = f1;
        invalidate();
    }

    public void setOnPageChangeListener(android.support.v4.view.ViewPager.OnPageChangeListener onpagechangelistener)
    {
        d = onpagechangelistener;
    }

    public void setSelectedColor(int i1)
    {
        b.setColor(i1);
        invalidate();
    }

    public void setStrokeWidth(float f1)
    {
        b.setStrokeWidth(f1);
        a.setStrokeWidth(f1);
        invalidate();
    }

    public void setUnselectedColor(int i1)
    {
        a.setColor(i1);
        invalidate();
    }

    public void setViewPager(ViewPager viewpager)
    {
        if (c == viewpager)
        {
            return;
        }
        if (c != null)
        {
            c.setOnPageChangeListener(null);
        }
        if (viewpager.getAdapter() == null)
        {
            throw new IllegalStateException("ViewPager does not have adapter instance.");
        } else
        {
            c = viewpager;
            c.setOnPageChangeListener(this);
            invalidate();
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

        public static final android.os.Parcelable.Creator CREATOR = new c();
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
