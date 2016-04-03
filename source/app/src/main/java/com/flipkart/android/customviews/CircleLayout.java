// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.customviews;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Xfermode;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class CircleLayout extends ViewGroup
{

    public static final int LAYOUT_NORMAL = 1;
    public static final int LAYOUT_PIE = 2;
    private int a;
    private Drawable b;
    private float c;
    private float d;
    private float e;
    private int f;
    private Paint g;
    private Paint h;
    private RectF i;
    private Bitmap j;
    private Bitmap k;
    private Canvas l;
    private Canvas m;
    private Xfermode n;
    private Paint o;
    private View p;
    private Bitmap q;
    private Canvas r;
    private Set s;
    private boolean t;

    public CircleLayout(Context context)
    {
        this(context, null);
    }

    public CircleLayout(Context context, AttributeSet attributeset)
    {
        TypedArray typedarray;
        super(context, attributeset);
        a = 1;
        i = new RectF();
        s = new HashSet();
        t = false;
        g = new Paint(1);
        h = new Paint(1);
        typedarray = context.getTheme().obtainStyledAttributes(attributeset, com.flipkart.android.R.styleable.CircleLayout, 0, 0);
        int i1 = typedarray.getColor(1, 0x1060000);
        b = typedarray.getDrawable(2);
        if (b instanceof ColorDrawable)
        {
            int j1 = typedarray.getColor(2, 0x106000b);
            h.setColor(j1);
        }
        g.setColor(i1);
        c = typedarray.getFloat(3, 90F);
        d = typedarray.getFloat(4, 360F);
        e = typedarray.getDimensionPixelSize(6, 1);
        f = typedarray.getDimensionPixelSize(0, 80);
        a = typedarray.getColor(5, 1);
        typedarray.recycle();
        g.setStrokeWidth(e);
        n = new PorterDuffXfermode(android.graphics.PorterDuff.Mode.SRC_IN);
        o = new Paint(1);
        if (android.os.Build.VERSION.SDK_INT >= 11)
        {
            setLayerType(1, null);
        }
        return;
        Exception exception;
        exception;
        typedarray.recycle();
        throw exception;
    }

    private void a(Canvas canvas, float f1, float f2)
    {
label0:
        {
            if (b != null)
            {
                if (b instanceof ColorDrawable)
                {
                    break label0;
                }
                b.setBounds((int)f1 - f, (int)f2 - f, (int)f1 + f, (int)f2 + f);
                b.draw(canvas);
            }
            return;
        }
        canvas.drawCircle(f1, f2, f, h);
    }

    private void a(Canvas canvas, float f1, float f2, float f3)
    {
        int i1 = getChildCount();
        if (i1 >= 2)
        {
            int j1 = 0;
            while (j1 < i1) 
            {
                LayoutParams layoutparams = (LayoutParams)getChildAt(j1).getLayoutParams();
                canvas.drawLine(f1, f2, f1 + f3 * (float)Math.cos(Math.toRadians(LayoutParams.a(layoutparams))), f2 + f3 * (float)Math.sin(Math.toRadians(LayoutParams.a(layoutparams))), g);
                if (j1 == i1 - 1)
                {
                    canvas.drawLine(f1, f2, f1 + f3 * (float)Math.cos(Math.toRadians(LayoutParams.b(layoutparams))), f2 + f3 * (float)Math.sin(Math.toRadians(LayoutParams.b(layoutparams))), g);
                }
                j1++;
            }
        }
    }

    private void a(Canvas canvas, View view, LayoutParams layoutparams)
    {
        l.drawColor(0, android.graphics.PorterDuff.Mode.CLEAR);
        m.drawColor(0, android.graphics.PorterDuff.Mode.CLEAR);
        l.save();
        int i1 = view.getLeft();
        int j1 = view.getTop();
        int k1 = view.getRight();
        int l1 = view.getBottom();
        l.clipRect(i1, j1, k1, l1, android.graphics.Region.Op.REPLACE);
        l.translate(i1, j1);
        view.draw(l);
        l.restore();
        o.setXfermode(null);
        o.setColor(0xff000000);
        float f1 = (LayoutParams.b(layoutparams) - LayoutParams.a(layoutparams)) % 361F;
        m.drawArc(i, LayoutParams.a(layoutparams), f1, true, o);
        o.setXfermode(n);
        m.drawBitmap(k, 0.0F, 0.0F, o);
        canvas.drawBitmap(j, 0.0F, 0.0F, null);
    }

    protected boolean checkLayoutParams(android.view.ViewGroup.LayoutParams layoutparams)
    {
        return layoutparams instanceof LayoutParams;
    }

    protected void dispatchDraw(Canvas canvas)
    {
        if (a == 1)
        {
            super.dispatchDraw(canvas);
        } else
        if (k != null && j != null && !k.isRecycled() && !j.isRecycled())
        {
            int i1 = getChildCount();
            float f1 = (float)getWidth() / 2.0F;
            float f2 = (float)getHeight() / 2.0F;
            float f3;
            if (f1 > f2)
            {
                f3 = f2;
            } else
            {
                f3 = f1;
            }
            if (t && q != null && !q.isRecycled() && s.size() < i1 / 2)
            {
                canvas.drawBitmap(q, 0.0F, 0.0F, null);
                View view1;
                for (Iterator iterator = s.iterator(); iterator.hasNext(); a(canvas, view1, (LayoutParams)view1.getLayoutParams()))
                {
                    view1 = (View)iterator.next();
                }

                if (p != null)
                {
                    a(canvas, p, (LayoutParams)p.getLayoutParams());
                }
                a(canvas, f1, f2, f3);
                a(canvas, f1, f2);
                return;
            }
            t = false;
            Canvas canvas1;
            Drawable drawable;
            int j1;
            if (r != null)
            {
                canvas1 = r;
            } else
            {
                canvas1 = canvas;
                canvas = null;
            }
            drawable = getBackground();
            j1 = 0;
            if (drawable != null)
            {
                drawable.draw(canvas1);
            }
            for (; j1 < i1; j1++)
            {
                View view = getChildAt(j1);
                a(canvas1, view, (LayoutParams)view.getLayoutParams());
            }

            a(canvas1, f1, f2, f3);
            a(canvas1, f1, f2);
            if (r != null)
            {
                canvas.drawBitmap(q, 0.0F, 0.0F, null);
                s.clear();
                t = true;
                return;
            }
        }
    }

    public boolean dispatchTouchEvent(MotionEvent motionevent)
    {
        if (a == 1)
        {
            return super.dispatchTouchEvent(motionevent);
        }
        int i1 = motionevent.getAction();
        float f1 = motionevent.getX() - (float)getWidth() / 2.0F;
        float f2 = motionevent.getY() - (float)getHeight() / 2.0F;
        if (i1 == 0)
        {
            if (p != null)
            {
                MotionEvent motionevent1 = MotionEvent.obtain(motionevent);
                motionevent1.setAction(3);
                motionevent1.offsetLocation(-p.getLeft(), -p.getTop());
                p.dispatchTouchEvent(motionevent1);
                motionevent1.recycle();
                p = null;
            }
            float f3 = (float)Math.sqrt(f1 * f1 + f2 * f2);
            if (f3 < (float)f || f3 > (float)getWidth() / 2.0F || f3 > (float)getHeight() / 2.0F)
            {
                return false;
            }
            float f4 = (float)Math.toDegrees(Math.atan2(f2, f1));
            float f5;
            int j1;
            int k1;
            if (f4 < 0.0F)
            {
                f5 = f4 + d;
            } else
            {
                f5 = f4;
            }
            j1 = getChildCount();
            k1 = 0;
            while (k1 < j1) 
            {
                View view = getChildAt(k1);
                LayoutParams layoutparams = (LayoutParams)view.getLayoutParams();
                float f6 = LayoutParams.a(layoutparams) % d;
                float f7 = LayoutParams.b(layoutparams) % d;
                float f8;
                if (f6 > f7)
                {
                    if (f5 < f6 && f5 < f7)
                    {
                        f8 = f5 + d;
                    } else
                    {
                        f8 = f5;
                    }
                    f7 += d;
                } else
                {
                    f8 = f5;
                }
                if (f6 <= f8 && f7 >= f8)
                {
                    motionevent.offsetLocation(-view.getLeft(), -view.getTop());
                    if (view.dispatchTouchEvent(motionevent))
                    {
                        p = view;
                        return true;
                    } else
                    {
                        motionevent.setLocation(0.0F, 0.0F);
                        return onTouchEvent(motionevent);
                    }
                }
                k1++;
            }
        } else
        if (p != null)
        {
            motionevent.offsetLocation(-p.getLeft(), -p.getTop());
            p.dispatchTouchEvent(motionevent);
            if (i1 == 1 || i1 == 3)
            {
                p = null;
            }
        }
        return onTouchEvent(motionevent);
    }

    protected volatile android.view.ViewGroup.LayoutParams generateDefaultLayoutParams()
    {
        return generateDefaultLayoutParams();
    }

    protected LayoutParams generateDefaultLayoutParams()
    {
        return new LayoutParams(-2, -2);
    }

    public volatile android.view.ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeset)
    {
        return generateLayoutParams(attributeset);
    }

    protected volatile android.view.ViewGroup.LayoutParams generateLayoutParams(android.view.ViewGroup.LayoutParams layoutparams)
    {
        return generateLayoutParams(layoutparams);
    }

    public LayoutParams generateLayoutParams(AttributeSet attributeset)
    {
        return new LayoutParams(getContext(), attributeset);
    }

    protected LayoutParams generateLayoutParams(android.view.ViewGroup.LayoutParams layoutparams)
    {
        LayoutParams layoutparams1 = new LayoutParams(layoutparams.width, layoutparams.height);
        if (layoutparams instanceof android.widget.LinearLayout.LayoutParams)
        {
            layoutparams1.weight = ((android.widget.LinearLayout.LayoutParams)layoutparams).weight;
        }
        return layoutparams1;
    }

    public float getAngleOffset()
    {
        return c;
    }

    public void getCenter(PointF pointf)
    {
        pointf.set((float)getWidth() / 2.0F, getHeight() / 2);
    }

    public Drawable getInnerCircle()
    {
        return b;
    }

    public int getInnerRadius()
    {
        return f;
    }

    public int getLayoutMode()
    {
        return a;
    }

    public int getRadius()
    {
        int i1 = getWidth();
        int j1 = getHeight();
        float f1;
        if (i1 > j1)
        {
            f1 = j1;
        } else
        {
            f1 = i1;
        }
        return (int)((f1 - (float)f) / 2.0F);
    }

    protected void onLayout(boolean flag, int i1, int j1, int k1, int l1)
    {
        int i2 = getChildCount();
        float f1 = 0.0F;
        for (int j2 = 0; j2 < i2;)
        {
            float f9 = f1 + ((LayoutParams)getChildAt(j2).getLayoutParams()).weight;
            j2++;
            f1 = f9;
        }

        int k2 = getWidth();
        int l2 = getHeight();
        float f2;
        float f3;
        float f4;
        int i3;
        float f5;
        if (k2 > l2)
        {
            f2 = l2;
        } else
        {
            f2 = k2;
        }
        f3 = (f2 - (float)f) / 2.0F;
        i.set((float)(k2 / 2) - f2 / 2.0F, (float)(l2 / 2) - f2 / 2.0F, (float)(k2 / 2) + f2 / 2.0F, (float)(l2 / 2) + f2 / 2.0F);
        f4 = c;
        i3 = 0;
        f5 = f4;
        while (i3 < i2) 
        {
            View view = getChildAt(i3);
            LayoutParams layoutparams = (LayoutParams)view.getLayoutParams();
            float f6 = (d / f1) * layoutparams.weight;
            float f7 = f5 + f6 / 2.0F;
            int j3;
            int k3;
            int l3;
            int i4;
            int j4;
            int k4;
            int l4;
            int i5;
            float f8;
            if (i2 > 1)
            {
                j3 = (int)((double)f3 * Math.cos(Math.toRadians(f7))) + k2 / 2;
                k3 = (int)((double)f3 * Math.sin(Math.toRadians(f7))) + l2 / 2;
            } else
            {
                j3 = k2 / 2;
                k3 = l2 / 2;
            }
            l3 = view.getMeasuredWidth() / 2;
            i4 = view.getMeasuredHeight() / 2;
            if (layoutparams.width != -1)
            {
                j4 = j3 - l3;
            } else
            {
                j4 = 0;
            }
            if (layoutparams.height != -1)
            {
                k4 = k3 - i4;
            } else
            {
                k4 = 0;
            }
            if (layoutparams.width != -1)
            {
                l4 = j3 + l3;
            } else
            {
                l4 = k2;
            }
            if (layoutparams.height != -1)
            {
                i5 = k3 + i4;
            } else
            {
                i5 = l2;
            }
            view.layout(j4, k4, l4, i5);
            if (j4 != view.getLeft() || k4 != view.getTop() || l4 != view.getRight() || i5 != view.getBottom() || LayoutParams.a(layoutparams) != f5 || LayoutParams.b(layoutparams) != f5 + f6)
            {
                t = false;
            }
            LayoutParams.a(layoutparams, f5);
            f8 = f5 + f6;
            LayoutParams.b(layoutparams, f8);
            i3++;
            f5 = f8;
        }
        invalidate();
    }

    protected void onMeasure(int i1, int j1)
    {
        int k1 = 0;
        int l1 = getChildCount();
        int i2 = 0;
        int j2 = 0;
        for (; i2 < l1; i2++)
        {
            View view = getChildAt(i2);
            if (view.getVisibility() != 8)
            {
                measureChild(view, i1, j1);
                k1 = Math.max(k1, view.getMeasuredWidth());
                j2 = Math.max(j2, view.getMeasuredHeight());
            }
        }

        int k2 = Math.max(j2, getSuggestedMinimumHeight());
        int l2 = resolveSize(Math.max(k1, getSuggestedMinimumWidth()), i1);
        int i3 = resolveSize(k2, j1);
        setMeasuredDimension(l2, i3);
        if (k != null && (k.getWidth() != l2 || k.getHeight() != i3))
        {
            j.recycle();
            k.recycle();
            q.recycle();
            j = null;
            k = null;
            q = null;
        }
        if (k == null)
        {
            k = Bitmap.createBitmap(l2, i3, android.graphics.Bitmap.Config.ARGB_8888);
            j = Bitmap.createBitmap(l2, i3, android.graphics.Bitmap.Config.ARGB_8888);
            q = Bitmap.createBitmap(l2, i3, android.graphics.Bitmap.Config.ARGB_8888);
            l = new Canvas(k);
            m = new Canvas(j);
            r = new Canvas(q);
        }
    }

    public void setAngleOffset(float f1)
    {
        c = f1;
        requestLayout();
        invalidate();
    }

    public void setInnerCircle(int i1)
    {
        b = getContext().getResources().getDrawable(i1);
        requestLayout();
        invalidate();
    }

    public void setInnerCircle(Drawable drawable)
    {
        b = drawable;
        requestLayout();
        invalidate();
    }

    public void setInnerCircleColor(int i1)
    {
        b = new ColorDrawable(i1);
        requestLayout();
        invalidate();
    }

    public void setInnerCircleDrawable(Drawable drawable)
    {
        b = drawable;
        requestLayout();
        invalidate();
    }

    public void setInnerRadius(int i1)
    {
        f = i1;
        requestLayout();
        invalidate();
    }

    public void setLayoutMode(int i1)
    {
        a = i1;
        requestLayout();
        invalidate();
    }

    private class LayoutParams extends android.view.ViewGroup.LayoutParams
    {

        private float a;
        private float b;
        public float weight;

        static float a(LayoutParams layoutparams)
        {
            return layoutparams.a;
        }

        static float a(LayoutParams layoutparams, float f1)
        {
            layoutparams.a = f1;
            return f1;
        }

        static float b(LayoutParams layoutparams)
        {
            return layoutparams.b;
        }

        static float b(LayoutParams layoutparams, float f1)
        {
            layoutparams.b = f1;
            return f1;
        }

        public LayoutParams(int i1, int j1)
        {
            super(i1, j1);
            weight = 1.0F;
        }

        public LayoutParams(Context context, AttributeSet attributeset)
        {
            super(context, attributeset);
            weight = 1.0F;
        }
    }

}
