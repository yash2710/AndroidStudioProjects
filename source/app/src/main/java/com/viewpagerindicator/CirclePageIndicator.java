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
//            PageIndicator

public class CirclePageIndicator extends View
    implements PageIndicator
{

    private static final int INVALID_POINTER = -1;
    private int mActivePointerId;
    private boolean mCentered;
    private int mCurrentPage;
    private boolean mIsDragging;
    private float mLastMotionX;
    private android.support.v4.view.ViewPager.OnPageChangeListener mListener;
    private int mOrientation;
    android.support.v4.view.ViewPager.OnPageChangeListener mPageChangeListener;
    private float mPageOffset;
    private final Paint mPaintFill;
    private final Paint mPaintPageFill;
    private final Paint mPaintStroke;
    private float mRadius;
    private int mScrollState;
    private boolean mSnap;
    private int mSnapPage;
    private int mTouchSlop;
    private ViewPager mViewPager;

    public CirclePageIndicator(Context context)
    {
        this(context, null);
    }

    public CirclePageIndicator(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, R.attr.vpiCirclePageIndicatorStyle);
    }

    public CirclePageIndicator(Context context, AttributeSet attributeset, int i)
    {
        super(context, attributeset, i);
        mPaintPageFill = new Paint(1);
        mPaintStroke = new Paint(1);
        mPaintFill = new Paint(1);
        mLastMotionX = -1F;
        mActivePointerId = -1;
        if (isInEditMode())
        {
            return;
        }
        Resources resources = getResources();
        int j = resources.getColor(R.color.default_circle_indicator_page_color);
        int k = resources.getColor(R.color.default_circle_indicator_fill_color);
        int l = resources.getInteger(R.integer.default_circle_indicator_orientation);
        resources.getColor(R.color.default_circle_indicator_stroke_color);
        float f = resources.getDimension(R.dimen.default_circle_indicator_stroke_width);
        float f1 = resources.getDimension(R.dimen.default_circle_indicator_radius);
        boolean flag = resources.getBoolean(R.bool.default_circle_indicator_centered);
        boolean flag1 = resources.getBoolean(R.bool.default_circle_indicator_snap);
        TypedArray typedarray = context.obtainStyledAttributes(attributeset, R.styleable.CirclePageIndicator, i, 0);
        mCentered = typedarray.getBoolean(2, flag);
        mOrientation = typedarray.getInt(0, l);
        mPaintPageFill.setStyle(android.graphics.Paint.Style.FILL);
        mPaintPageFill.setColor(typedarray.getColor(5, j));
        mPaintFill.setStyle(android.graphics.Paint.Style.FILL);
        mPaintFill.setStrokeWidth(f);
        mPaintFill.setColor(typedarray.getColor(4, k));
        mRadius = typedarray.getDimension(6, f1);
        mSnap = typedarray.getBoolean(7, flag1);
        android.graphics.drawable.Drawable drawable = typedarray.getDrawable(1);
        if (drawable != null)
        {
            setBackgroundDrawable(drawable);
        }
        typedarray.recycle();
        mTouchSlop = ViewConfigurationCompat.getScaledPagingTouchSlop(ViewConfiguration.get(context));
    }

    private int measureLong(int i)
    {
        int j = android.view.View.MeasureSpec.getMode(i);
        int k = android.view.View.MeasureSpec.getSize(i);
        int l;
        if (j == 0x40000000 || mViewPager == null)
        {
            l = k;
        } else
        {
            int i1 = mViewPager.getAdapter().getCount();
            l = (int)(1.0F + ((float)(getPaddingLeft() + getPaddingRight()) + (float)(i1 << 1) * mRadius + (float)(i1 - 1) * mRadius));
            if (j == 0x80000000)
            {
                return Math.min(l, k);
            }
        }
        return l;
    }

    private int measureShort(int i)
    {
        int j = android.view.View.MeasureSpec.getMode(i);
        int k = android.view.View.MeasureSpec.getSize(i);
        if (j == 0x40000000)
        {
            return k;
        }
        int l = (int)(1.0F + (2.0F * mRadius + (float)getPaddingTop() + (float)getPaddingBottom()));
        if (j == 0x80000000)
        {
            return Math.min(l, k);
        } else
        {
            return l;
        }
    }

    public int getFillColor()
    {
        return mPaintFill.getColor();
    }

    public int getOrientation()
    {
        return mOrientation;
    }

    public android.support.v4.view.ViewPager.OnPageChangeListener getPageChangeListener()
    {
        return mPageChangeListener;
    }

    public int getPageColor()
    {
        return mPaintPageFill.getColor();
    }

    public float getRadius()
    {
        return mRadius;
    }

    public int getStrokeColor()
    {
        return mPaintStroke.getColor();
    }

    public float getStrokeWidth()
    {
        return mPaintStroke.getStrokeWidth();
    }

    public boolean isCentered()
    {
        return mCentered;
    }

    public boolean isSnap()
    {
        return mSnap;
    }

    public void notifyDataSetChanged()
    {
        invalidate();
    }

    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        int i;
        if (mViewPager != null)
        {
            if ((i = mViewPager.getAdapter().getCount()) != 0)
            {
                if (mCurrentPage >= i)
                {
                    setCurrentItem(i - 1);
                    return;
                }
                int j;
                int k;
                int l;
                int i1;
                float f;
                float f1;
                float f2;
                float f3;
                int j1;
                if (mOrientation == 0)
                {
                    j = getWidth();
                    k = getPaddingLeft();
                    l = getPaddingRight();
                    i1 = getPaddingTop();
                } else
                {
                    j = getHeight();
                    k = getPaddingTop();
                    l = getPaddingBottom();
                    i1 = getPaddingLeft();
                }
                f = 3F * mRadius;
                f1 = (float)i1 + mRadius;
                f2 = (float)k + mRadius;
                if (mCentered)
                {
                    f2 += (float)(j - k - l) / 2.0F - (f * (float)i) / 2.0F;
                }
                f3 = mRadius;
                if (mPaintStroke.getStrokeWidth() > 0.0F)
                {
                    f3 -= mPaintStroke.getStrokeWidth() / 2.0F;
                }
                j1 = 0;
                while (j1 < i) 
                {
                    float f7 = f2 + f * (float)j1;
                    float f8;
                    if (mOrientation == 0)
                    {
                        f8 = f7;
                        f7 = f1;
                    } else
                    {
                        f8 = f1;
                    }
                    if (mPaintPageFill.getAlpha() > 0)
                    {
                        canvas.drawCircle(f8, f7, f3, mPaintPageFill);
                    }
                    if (f3 != mRadius)
                    {
                        canvas.drawCircle(f8, f7, mRadius, mPaintStroke);
                    }
                    j1++;
                }
                int k1;
                float f4;
                float f6;
                if (mSnap)
                {
                    k1 = mSnapPage;
                } else
                {
                    k1 = mCurrentPage;
                }
                f4 = f * (float)k1;
                if (!mSnap)
                {
                    f4 += f * mPageOffset;
                }
                if (mOrientation == 0)
                {
                    f6 = f2 + f4;
                } else
                {
                    float f5 = f2 + f4;
                    f6 = f1;
                    f1 = f5;
                }
                canvas.drawCircle(f6, f1, mRadius - getStrokeWidth(), mPaintFill);
                return;
            }
        }
    }

    protected void onMeasure(int i, int j)
    {
        if (mOrientation == 0)
        {
            setMeasuredDimension(measureLong(i), measureShort(j));
            return;
        } else
        {
            setMeasuredDimension(measureShort(i), measureLong(j));
            return;
        }
    }

    public void onPageScrollStateChanged(int i)
    {
        if (mPageChangeListener != null)
        {
            mPageChangeListener.onPageScrollStateChanged(i);
        }
        mScrollState = i;
        if (mListener != null)
        {
            mListener.onPageScrollStateChanged(i);
        }
    }

    public void onPageScrolled(int i, float f, int j)
    {
        if (mPageChangeListener != null)
        {
            mPageChangeListener.onPageScrolled(i, f, j);
        }
        mCurrentPage = i;
        mPageOffset = f;
        invalidate();
        if (mListener != null)
        {
            mListener.onPageScrolled(i, f, j);
        }
    }

    public void onPageSelected(int i)
    {
        if (mPageChangeListener != null)
        {
            mPageChangeListener.onPageSelected(i);
        }
        if (mSnap || mScrollState == 0)
        {
            mCurrentPage = i;
            mSnapPage = i;
            invalidate();
        }
        if (mListener != null)
        {
            mListener.onPageSelected(i);
        }
    }

    public void onRestoreInstanceState(Parcelable parcelable)
    {
        SavedState savedstate = (SavedState)parcelable;
        super.onRestoreInstanceState(savedstate.getSuperState());
        mCurrentPage = savedstate.a;
        mSnapPage = savedstate.a;
        requestLayout();
    }

    public Parcelable onSaveInstanceState()
    {
        SavedState savedstate = new SavedState(super.onSaveInstanceState());
        savedstate.a = mCurrentPage;
        return savedstate;
    }

    public boolean onTouchEvent(MotionEvent motionevent)
    {
        if (!super.onTouchEvent(motionevent)) goto _L2; else goto _L1
_L1:
        return true;
_L2:
        int i;
        if (mViewPager == null || mViewPager.getAdapter().getCount() == 0)
        {
            return false;
        }
        i = 0xff & motionevent.getAction();
        i;
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
        mActivePointerId = MotionEventCompat.getPointerId(motionevent, 0);
        mLastMotionX = motionevent.getX();
        return true;
_L6:
        float f2 = MotionEventCompat.getX(motionevent, MotionEventCompat.findPointerIndex(motionevent, mActivePointerId));
        float f3 = f2 - mLastMotionX;
        if (!mIsDragging && Math.abs(f3) > (float)mTouchSlop)
        {
            mIsDragging = true;
        }
        if (mIsDragging)
        {
            mLastMotionX = f2;
            if (mViewPager.isFakeDragging() || mViewPager.beginFakeDrag())
            {
                mViewPager.fakeDragBy(f3);
                return true;
            }
        }
        continue; /* Loop/switch isn't completed */
_L5:
        if (mIsDragging)
        {
            break; /* Loop/switch isn't completed */
        }
        int i1 = mViewPager.getAdapter().getCount();
        int j1 = getWidth();
        float f = (float)j1 / 2.0F;
        float f1 = (float)j1 / 6F;
        if (mCurrentPage > 0 && motionevent.getX() < f - f1)
        {
            if (i != 3)
            {
                mViewPager.setCurrentItem(-1 + mCurrentPage);
                return true;
            }
            continue; /* Loop/switch isn't completed */
        }
        if (mCurrentPage >= i1 - 1 || motionevent.getX() <= f1 + f)
        {
            break; /* Loop/switch isn't completed */
        }
        if (i != 3)
        {
            mViewPager.setCurrentItem(1 + mCurrentPage);
            return true;
        }
        if (true) goto _L1; else goto _L9
_L9:
        mIsDragging = false;
        mActivePointerId = -1;
        if (mViewPager.isFakeDragging())
        {
            mViewPager.endFakeDrag();
            return true;
        }
          goto _L1
_L7:
        int l = MotionEventCompat.getActionIndex(motionevent);
        mLastMotionX = MotionEventCompat.getX(motionevent, l);
        mActivePointerId = MotionEventCompat.getPointerId(motionevent, l);
        return true;
_L8:
        int j = MotionEventCompat.getActionIndex(motionevent);
        if (MotionEventCompat.getPointerId(motionevent, j) == mActivePointerId)
        {
            int k = 0;
            if (j == 0)
            {
                k = 1;
            }
            mActivePointerId = MotionEventCompat.getPointerId(motionevent, k);
        }
        mLastMotionX = MotionEventCompat.getX(motionevent, MotionEventCompat.findPointerIndex(motionevent, mActivePointerId));
        return true;
    }

    public void setCentered(boolean flag)
    {
        mCentered = flag;
        invalidate();
    }

    public void setCurrentItem(int i)
    {
        if (mViewPager == null)
        {
            throw new IllegalStateException("ViewPager has not been bound.");
        } else
        {
            mViewPager.setCurrentItem(i);
            mCurrentPage = i;
            invalidate();
            return;
        }
    }

    public void setFillColor(int i)
    {
        mPaintFill.setColor(i);
        invalidate();
    }

    public void setOnPageChangeListener(android.support.v4.view.ViewPager.OnPageChangeListener onpagechangelistener)
    {
        mListener = onpagechangelistener;
    }

    public void setOrientation(int i)
    {
        switch (i)
        {
        default:
            throw new IllegalArgumentException("Orientation must be either HORIZONTAL or VERTICAL.");

        case 0: // '\0'
        case 1: // '\001'
            mOrientation = i;
            break;
        }
        requestLayout();
    }

    public void setPageChangeListener(android.support.v4.view.ViewPager.OnPageChangeListener onpagechangelistener)
    {
        mPageChangeListener = onpagechangelistener;
    }

    public void setPageColor(int i)
    {
        mPaintPageFill.setColor(i);
        invalidate();
    }

    public void setRadius(float f)
    {
        mRadius = f;
        invalidate();
    }

    public void setSnap(boolean flag)
    {
        mSnap = flag;
        invalidate();
    }

    public void setStrokeColor(int i)
    {
        mPaintStroke.setColor(i);
        invalidate();
    }

    public void setStrokeWidth(float f)
    {
        mPaintStroke.setStrokeWidth(f);
        invalidate();
    }

    public void setViewPager(ViewPager viewpager)
    {
        if (mViewPager == viewpager)
        {
            return;
        }
        if (mViewPager != null)
        {
            mViewPager.setOnPageChangeListener(null);
        }
        if (viewpager.getAdapter() == null)
        {
            throw new IllegalStateException("ViewPager does not have adapter instance.");
        } else
        {
            mViewPager = viewpager;
            mViewPager.setOnPageChangeListener(this);
            invalidate();
            return;
        }
    }

    public void setViewPager(ViewPager viewpager, int i)
    {
        setViewPager(viewpager);
        setCurrentItem(i);
    }

    private class SavedState extends android.view.View.BaseSavedState
    {

        public static final android.os.Parcelable.Creator CREATOR = new a();
        int a;

        public void writeToParcel(Parcel parcel, int i)
        {
            super.writeToParcel(parcel, i);
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
