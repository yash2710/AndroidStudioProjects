// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.viewpagerindicator;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.Parcelable;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewConfigurationCompat;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import java.util.ArrayList;

// Referenced classes of package com.viewpagerindicator:
//            PageIndicator, k

public class TitlePageIndicator extends View
    implements PageIndicator
{

    private OnCenterItemClickListener A;
    private ViewPager a;
    private android.support.v4.view.ViewPager.OnPageChangeListener b;
    private int c;
    private float d;
    private int e;
    private final Paint f;
    private boolean g;
    private int h;
    private int i;
    private Path j;
    private final Rect k;
    private final Paint l;
    private IndicatorStyle m;
    private LinePosition n;
    private final Paint o;
    private float p;
    private float q;
    private float r;
    private float s;
    private float t;
    private float u;
    private float v;
    private int w;
    private float x;
    private int y;
    private boolean z;

    public TitlePageIndicator(Context context)
    {
        this(context, null);
    }

    public TitlePageIndicator(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, R.attr.vpiTitlePageIndicatorStyle);
    }

    public TitlePageIndicator(Context context, AttributeSet attributeset, int i1)
    {
        super(context, attributeset, i1);
        c = -1;
        f = new Paint();
        j = new Path();
        k = new Rect();
        l = new Paint();
        o = new Paint();
        x = -1F;
        y = -1;
        if (isInEditMode())
        {
            return;
        }
        Resources resources = getResources();
        int j1 = resources.getColor(R.color.default_title_indicator_footer_color);
        float f1 = resources.getDimension(R.dimen.default_title_indicator_footer_line_height);
        int k1 = resources.getInteger(R.integer.default_title_indicator_footer_indicator_style);
        float f2 = resources.getDimension(R.dimen.default_title_indicator_footer_indicator_height);
        float f3 = resources.getDimension(R.dimen.default_title_indicator_footer_indicator_underline_padding);
        float f4 = resources.getDimension(R.dimen.default_title_indicator_footer_padding);
        int l1 = resources.getInteger(R.integer.default_title_indicator_line_position);
        int i2 = resources.getColor(R.color.default_title_indicator_selected_color);
        boolean flag = resources.getBoolean(R.bool.default_title_indicator_selected_bold);
        int j2 = resources.getColor(R.color.default_title_indicator_text_color);
        float f5 = resources.getDimension(R.dimen.default_title_indicator_text_size);
        float f6 = resources.getDimension(R.dimen.default_title_indicator_title_padding);
        float f7 = resources.getDimension(R.dimen.default_title_indicator_clip_padding);
        float f8 = resources.getDimension(R.dimen.default_title_indicator_top_padding);
        TypedArray typedarray = context.obtainStyledAttributes(attributeset, R.styleable.TitlePageIndicator, i1, 0);
        v = typedarray.getDimension(6, f1);
        m = IndicatorStyle.fromValue(typedarray.getInteger(7, k1));
        p = typedarray.getDimension(8, f2);
        q = typedarray.getDimension(9, f3);
        r = typedarray.getDimension(10, f4);
        n = LinePosition.fromValue(typedarray.getInteger(11, l1));
        t = typedarray.getDimension(14, f8);
        s = typedarray.getDimension(13, f6);
        u = typedarray.getDimension(4, f7);
        i = typedarray.getColor(3, i2);
        h = typedarray.getColor(1, j2);
        g = typedarray.getBoolean(12, flag);
        float f9 = typedarray.getDimension(0, f5);
        int k2 = typedarray.getColor(5, j1);
        f.setTextSize(f9);
        f.setAntiAlias(true);
        l.setStyle(android.graphics.Paint.Style.FILL_AND_STROKE);
        l.setStrokeWidth(v);
        l.setColor(k2);
        o.setStyle(android.graphics.Paint.Style.FILL_AND_STROKE);
        o.setColor(k2);
        android.graphics.drawable.Drawable drawable = typedarray.getDrawable(2);
        if (drawable != null)
        {
            setBackgroundDrawable(drawable);
        }
        typedarray.recycle();
        w = ViewConfigurationCompat.getScaledPagingTouchSlop(ViewConfiguration.get(context));
    }

    private CharSequence a(int i1)
    {
        Object obj = a.getAdapter().getPageTitle(i1);
        if (obj == null)
        {
            obj = "";
        }
        return ((CharSequence) (obj));
    }

    private void a(Rect rect, float f1, int i1)
    {
        rect.right = (int)((float)i1 - u);
        rect.left = (int)((float)rect.right - f1);
    }

    private void b(Rect rect, float f1, int i1)
    {
        rect.left = (int)((float)i1 + u);
        rect.right = (int)(f1 + u);
    }

    public float getClipPadding()
    {
        return u;
    }

    public int getFooterColor()
    {
        return l.getColor();
    }

    public float getFooterIndicatorHeight()
    {
        return p;
    }

    public float getFooterIndicatorPadding()
    {
        return r;
    }

    public IndicatorStyle getFooterIndicatorStyle()
    {
        return m;
    }

    public float getFooterLineHeight()
    {
        return v;
    }

    public LinePosition getLinePosition()
    {
        return n;
    }

    public int getSelectedColor()
    {
        return i;
    }

    public int getTextColor()
    {
        return h;
    }

    public float getTextSize()
    {
        return f.getTextSize();
    }

    public float getTitlePadding()
    {
        return s;
    }

    public float getTopPadding()
    {
        return t;
    }

    public Typeface getTypeface()
    {
        return f.getTypeface();
    }

    public boolean isSelectedBold()
    {
        return g;
    }

    public void notifyDataSetChanged()
    {
        invalidate();
    }

    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        int i1;
        if (a != null)
        {
            if ((i1 = a.getAdapter().getCount()) != 0)
            {
                if (c == -1 && a != null)
                {
                    c = a.getCurrentItem();
                }
                Paint paint = f;
                ArrayList arraylist = new ArrayList();
                int j1 = a.getAdapter().getCount();
                int k1 = getWidth();
                int l1 = k1 / 2;
                for (int i2 = 0; i2 < j1; i2++)
                {
                    Rect rect = new Rect();
                    CharSequence charsequence = a(i2);
                    rect.right = (int)paint.measureText(charsequence, 0, charsequence.length());
                    rect.bottom = (int)(paint.descent() - paint.ascent());
                    int j2 = rect.right - rect.left;
                    int k2 = rect.bottom - rect.top;
                    rect.left = (int)(((float)l1 - (float)j2 / 2.0F) + ((float)(i2 - c) - d) * (float)k1);
                    rect.right = j2 + rect.left;
                    rect.top = 0;
                    rect.bottom = k2;
                    arraylist.add(rect);
                }

                int l2 = arraylist.size();
                if (c >= l2)
                {
                    setCurrentItem(l2 - 1);
                    return;
                }
                int i3 = i1 - 1;
                float f1 = (float)getWidth() / 2.0F;
                int j3 = getLeft();
                float f2 = (float)j3 + u;
                int k3 = getWidth();
                int l3 = getHeight();
                int i4 = j3 + k3;
                float f3 = (float)i4 - u;
                int j4 = c;
                int l4;
                float f5;
                boolean flag;
                boolean flag1;
                float f6;
                Rect rect1;
                float f7;
                if ((double)d <= 0.5D)
                {
                    float f18 = d;
                    l4 = j4;
                    f5 = f18;
                } else
                {
                    int k4 = j4 + 1;
                    float f4 = 1.0F - d;
                    l4 = k4;
                    f5 = f4;
                }
                if (f5 <= 0.25F)
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                if (f5 <= 0.05F)
                {
                    flag1 = true;
                } else
                {
                    flag1 = false;
                }
                f6 = (0.25F - f5) / 0.25F;
                rect1 = (Rect)arraylist.get(c);
                f7 = rect1.right - rect1.left;
                if ((float)rect1.left < f2)
                {
                    b(rect1, f7, j3);
                }
                if ((float)rect1.right > f3)
                {
                    a(rect1, f7, i4);
                }
                if (c > 0)
                {
                    for (int k6 = -1 + c; k6 >= 0; k6--)
                    {
                        Rect rect7 = (Rect)arraylist.get(k6);
                        if ((float)rect7.left >= f2)
                        {
                            continue;
                        }
                        int l6 = rect7.right - rect7.left;
                        b(rect7, l6, j3);
                        Rect rect8 = (Rect)arraylist.get(k6 + 1);
                        if ((float)rect7.right + s > (float)rect8.left)
                        {
                            rect7.left = (int)((float)(rect8.left - l6) - s);
                            rect7.right = l6 + rect7.left;
                        }
                    }

                }
                if (c < i3)
                {
                    for (int i6 = 1 + c; i6 < i1; i6++)
                    {
                        Rect rect5 = (Rect)arraylist.get(i6);
                        if ((float)rect5.right <= f3)
                        {
                            continue;
                        }
                        int j6 = rect5.right - rect5.left;
                        a(rect5, j6, i4);
                        Rect rect6 = (Rect)arraylist.get(i6 - 1);
                        if ((float)rect5.left - s < (float)rect6.right)
                        {
                            rect5.left = (int)((float)rect6.right + s);
                            rect5.right = j6 + rect5.left;
                        }
                    }

                }
                int i5 = h >>> 24;
                int j5 = 0;
                while (j5 < i1) 
                {
                    Rect rect3 = (Rect)arraylist.get(j5);
                    if ((rect3.left <= j3 || rect3.left >= i4) && (rect3.right <= j3 || rect3.right >= i4))
                    {
                        continue;
                    }
                    boolean flag2;
                    CharSequence charsequence1;
                    Paint paint1;
                    boolean flag3;
                    if (j5 == l4)
                    {
                        flag2 = true;
                    } else
                    {
                        flag2 = false;
                    }
                    charsequence1 = a(j5);
                    paint1 = f;
                    if (flag2 && flag1 && g)
                    {
                        flag3 = true;
                    } else
                    {
                        flag3 = false;
                    }
                    paint1.setFakeBoldText(flag3);
                    f.setColor(h);
                    if (flag2 && flag)
                    {
                        f.setAlpha(i5 - (int)(f6 * (float)i5));
                    }
                    if (j5 < l2 - 1)
                    {
                        Rect rect4 = (Rect)arraylist.get(j5 + 1);
                        if ((float)rect3.right + s > (float)rect4.left)
                        {
                            int l5 = rect3.right - rect3.left;
                            rect3.left = (int)((float)(rect4.left - l5) - s);
                            rect3.right = l5 + rect3.left;
                        }
                    }
                    canvas.drawText(charsequence1, 0, charsequence1.length(), rect3.left, (float)rect3.bottom + t, f);
                    if (flag2 && flag)
                    {
                        f.setColor(i);
                        f.setAlpha((int)(f6 * (float)(i >>> 24)));
                        canvas.drawText(charsequence1, 0, charsequence1.length(), rect3.left, (float)rect3.bottom + t, f);
                    }
                    j5++;
                }
                float f8 = v;
                float f9 = p;
                int k5;
                float f10;
                float f11;
                float f12;
                if (n == LinePosition.Top)
                {
                    k5 = 0;
                    float f16 = -f8;
                    float f17 = -f9;
                    f11 = f16;
                    f10 = f17;
                } else
                {
                    k5 = l3;
                    f10 = f9;
                    f11 = f8;
                }
                j.reset();
                j.moveTo(0.0F, (float)k5 - f11 / 2.0F);
                j.lineTo(k3, (float)k5 - f11 / 2.0F);
                j.close();
                canvas.drawPath(j, l);
                f12 = (float)k5 - f11;
                switch (k.a[m.ordinal()])
                {
                default:
                    return;

                case 1: // '\001'
                    j.reset();
                    j.moveTo(f1, f12 - f10);
                    j.lineTo(f1 + f10, f12);
                    j.lineTo(f1 - f10, f12);
                    j.close();
                    canvas.drawPath(j, o);
                    return;

                case 2: // '\002'
                    break;
                }
                if (flag && l4 < l2)
                {
                    Rect rect2 = (Rect)arraylist.get(l4);
                    float f13 = (float)rect2.right + q;
                    float f14 = (float)rect2.left - q;
                    float f15 = f12 - f10;
                    j.reset();
                    j.moveTo(f14, f12);
                    j.lineTo(f13, f12);
                    j.lineTo(f13, f15);
                    j.lineTo(f14, f15);
                    j.close();
                    o.setAlpha((int)(255F * f6));
                    canvas.drawPath(j, o);
                    o.setAlpha(255);
                    return;
                }
            }
        }
    }

    protected void onMeasure(int i1, int j1)
    {
        int k1 = android.view.View.MeasureSpec.getSize(i1);
        if (android.view.View.MeasureSpec.getMode(j1) != 0x40000000) goto _L2; else goto _L1
_L1:
        float f1 = android.view.View.MeasureSpec.getSize(j1);
_L4:
        setMeasuredDimension(k1, (int)f1);
        return;
_L2:
        k.setEmpty();
        k.bottom = (int)(f.descent() - f.ascent());
        f1 = (float)(k.bottom - k.top) + v + r + t;
        if (m != IndicatorStyle.None)
        {
            f1 += p;
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    public void onPageScrollStateChanged(int i1)
    {
        e = i1;
        if (b != null)
        {
            b.onPageScrollStateChanged(i1);
        }
    }

    public void onPageScrolled(int i1, float f1, int j1)
    {
        c = i1;
        d = f1;
        invalidate();
        if (b != null)
        {
            b.onPageScrolled(i1, f1, j1);
        }
    }

    public void onPageSelected(int i1)
    {
        if (e == 0)
        {
            c = i1;
            invalidate();
        }
        if (b != null)
        {
            b.onPageSelected(i1);
        }
    }

    public void onRestoreInstanceState(Parcelable parcelable)
    {
        SavedState savedstate = (SavedState)parcelable;
        super.onRestoreInstanceState(savedstate.getSuperState());
        c = savedstate.a;
        requestLayout();
    }

    public Parcelable onSaveInstanceState()
    {
        SavedState savedstate = new SavedState(super.onSaveInstanceState());
        savedstate.a = c;
        return savedstate;
    }

    public boolean onTouchEvent(MotionEvent motionevent)
    {
        if (!super.onTouchEvent(motionevent)) goto _L2; else goto _L1
_L1:
        return true;
_L2:
        int i1;
        if (a == null || a.getAdapter().getCount() == 0)
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
    //                   5 387
    //                   6 415;
           goto _L3 _L4 _L5 _L6 _L5 _L3 _L7 _L8
_L3:
        return true;
_L4:
        y = MotionEventCompat.getPointerId(motionevent, 0);
        x = motionevent.getX();
        return true;
_L6:
        float f6 = MotionEventCompat.getX(motionevent, MotionEventCompat.findPointerIndex(motionevent, y));
        float f7 = f6 - x;
        if (!z && Math.abs(f7) > (float)w)
        {
            z = true;
        }
        if (z)
        {
            x = f6;
            if (a.isFakeDragging() || a.beginFakeDrag())
            {
                a.fakeDragBy(f7);
                return true;
            }
        }
          goto _L1
_L5:
        int i2;
        float f3;
        float f4;
        float f5;
        if (z)
        {
            break MISSING_BLOCK_LABEL_358;
        }
        i2 = a.getAdapter().getCount();
        int j2 = getWidth();
        float f1 = (float)j2 / 2.0F;
        float f2 = (float)j2 / 6F;
        f3 = f1 - f2;
        f4 = f2 + f1;
        f5 = motionevent.getX();
        if (f5 >= f3) goto _L10; else goto _L9
_L9:
        if (c <= 0)
        {
            break MISSING_BLOCK_LABEL_358;
        }
        if (i1 != 3)
        {
            a.setCurrentItem(-1 + c);
            return true;
        }
          goto _L1
_L10:
        if (f5 <= f4) goto _L12; else goto _L11
_L11:
        if (c >= i2 - 1)
        {
            break MISSING_BLOCK_LABEL_358;
        }
        if (i1 != 3)
        {
            a.setCurrentItem(1 + c);
            return true;
        }
          goto _L1
_L12:
        if (A != null && i1 != 3)
        {
            A.onCenterItemClick(c);
        }
        z = false;
        y = -1;
        if (a.isFakeDragging())
        {
            a.endFakeDrag();
            return true;
        }
          goto _L1
_L7:
        int l1 = MotionEventCompat.getActionIndex(motionevent);
        x = MotionEventCompat.getX(motionevent, l1);
        y = MotionEventCompat.getPointerId(motionevent, l1);
        return true;
_L8:
        int j1 = MotionEventCompat.getActionIndex(motionevent);
        if (MotionEventCompat.getPointerId(motionevent, j1) == y)
        {
            int k1 = 0;
            if (j1 == 0)
            {
                k1 = 1;
            }
            y = MotionEventCompat.getPointerId(motionevent, k1);
        }
        x = MotionEventCompat.getX(motionevent, MotionEventCompat.findPointerIndex(motionevent, y));
        return true;
    }

    public void setClipPadding(float f1)
    {
        u = f1;
        invalidate();
    }

    public void setCurrentItem(int i1)
    {
        if (a == null)
        {
            throw new IllegalStateException("ViewPager has not been bound.");
        } else
        {
            a.setCurrentItem(i1);
            c = i1;
            invalidate();
            return;
        }
    }

    public void setFooterColor(int i1)
    {
        l.setColor(i1);
        o.setColor(i1);
        invalidate();
    }

    public void setFooterIndicatorHeight(float f1)
    {
        p = f1;
        invalidate();
    }

    public void setFooterIndicatorPadding(float f1)
    {
        r = f1;
        invalidate();
    }

    public void setFooterIndicatorStyle(IndicatorStyle indicatorstyle)
    {
        m = indicatorstyle;
        invalidate();
    }

    public void setFooterLineHeight(float f1)
    {
        v = f1;
        l.setStrokeWidth(v);
        invalidate();
    }

    public void setLinePosition(LinePosition lineposition)
    {
        n = lineposition;
        invalidate();
    }

    public void setOnCenterItemClickListener(OnCenterItemClickListener oncenteritemclicklistener)
    {
        A = oncenteritemclicklistener;
    }

    public void setOnPageChangeListener(android.support.v4.view.ViewPager.OnPageChangeListener onpagechangelistener)
    {
        b = onpagechangelistener;
    }

    public void setSelectedBold(boolean flag)
    {
        g = flag;
        invalidate();
    }

    public void setSelectedColor(int i1)
    {
        i = i1;
        invalidate();
    }

    public void setTextColor(int i1)
    {
        f.setColor(i1);
        h = i1;
        invalidate();
    }

    public void setTextSize(float f1)
    {
        f.setTextSize(f1);
        invalidate();
    }

    public void setTitlePadding(float f1)
    {
        s = f1;
        invalidate();
    }

    public void setTopPadding(float f1)
    {
        t = f1;
        invalidate();
    }

    public void setTypeface(Typeface typeface)
    {
        f.setTypeface(typeface);
        invalidate();
    }

    public void setViewPager(ViewPager viewpager)
    {
        if (a == viewpager)
        {
            return;
        }
        if (a != null)
        {
            a.setOnPageChangeListener(null);
        }
        if (viewpager.getAdapter() == null)
        {
            throw new IllegalStateException("ViewPager does not have adapter instance.");
        } else
        {
            a = viewpager;
            a.setOnPageChangeListener(this);
            invalidate();
            return;
        }
    }

    public void setViewPager(ViewPager viewpager, int i1)
    {
        setViewPager(viewpager);
        setCurrentItem(i1);
    }

    private class IndicatorStyle extends Enum
    {

        public static final IndicatorStyle None;
        public static final IndicatorStyle Triangle;
        public static final IndicatorStyle Underline;
        private static final IndicatorStyle a[];
        public final int value;

        public static IndicatorStyle fromValue(int i1)
        {
            IndicatorStyle aindicatorstyle[] = values();
            int j1 = aindicatorstyle.length;
            for (int k1 = 0; k1 < j1; k1++)
            {
                IndicatorStyle indicatorstyle = aindicatorstyle[k1];
                if (indicatorstyle.value == i1)
                {
                    return indicatorstyle;
                }
            }

            return null;
        }

        public static IndicatorStyle valueOf(String s1)
        {
            return (IndicatorStyle)Enum.valueOf(com/viewpagerindicator/TitlePageIndicator$IndicatorStyle, s1);
        }

        public static IndicatorStyle[] values()
        {
            return (IndicatorStyle[])a.clone();
        }

        static 
        {
            None = new IndicatorStyle("None", 0, 0);
            Triangle = new IndicatorStyle("Triangle", 1, 1);
            Underline = new IndicatorStyle("Underline", 2, 2);
            IndicatorStyle aindicatorstyle[] = new IndicatorStyle[3];
            aindicatorstyle[0] = None;
            aindicatorstyle[1] = Triangle;
            aindicatorstyle[2] = Underline;
            a = aindicatorstyle;
        }

        private IndicatorStyle(String s1, int i1, int j1)
        {
            super(s1, i1);
            value = j1;
        }
    }


    private class LinePosition extends Enum
    {

        public static final LinePosition Bottom;
        public static final LinePosition Top;
        private static final LinePosition a[];
        public final int value;

        public static LinePosition fromValue(int i1)
        {
            LinePosition alineposition[] = values();
            int j1 = alineposition.length;
            for (int k1 = 0; k1 < j1; k1++)
            {
                LinePosition lineposition = alineposition[k1];
                if (lineposition.value == i1)
                {
                    return lineposition;
                }
            }

            return null;
        }

        public static LinePosition valueOf(String s1)
        {
            return (LinePosition)Enum.valueOf(com/viewpagerindicator/TitlePageIndicator$LinePosition, s1);
        }

        public static LinePosition[] values()
        {
            return (LinePosition[])a.clone();
        }

        static 
        {
            Bottom = new LinePosition("Bottom", 0, 0);
            Top = new LinePosition("Top", 1, 1);
            LinePosition alineposition[] = new LinePosition[2];
            alineposition[0] = Bottom;
            alineposition[1] = Top;
            a = alineposition;
        }

        private LinePosition(String s1, int i1, int j1)
        {
            super(s1, i1);
            value = j1;
        }
    }


    private class SavedState extends android.view.View.BaseSavedState
    {

        public static final android.os.Parcelable.Creator CREATOR = new l();
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


    private class OnCenterItemClickListener
    {

        public abstract void onCenterItemClick(int i1);
    }

}
