// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package ru.truba.touchgallery.TouchView;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.FloatMath;
import android.view.ScaleGestureDetector;
import android.widget.ImageView;
import java.util.Timer;

// Referenced classes of package ru.truba.touchgallery.TouchView:
//            WrapMotionEvent, e, c, b

public class TouchImageView extends ImageView
{

    private Bitmap A;
    private float B;
    private float C;
    private Context D;
    private Timer E;
    private android.view.View.OnClickListener F;
    private Object G;
    private Handler H;
    private boolean I;
    Matrix a;
    Matrix b;
    int c;
    float d;
    float e;
    float f;
    float g;
    float h;
    float i;
    float j;
    float k;
    PointF l;
    PointF m;
    PointF n;
    float o[];
    public boolean onBottomSide;
    public boolean onLeftSide;
    public boolean onRightSide;
    public boolean onTopSide;
    float p;
    float q;
    float r;
    float s;
    float t;
    float u;
    PointF v;
    float w;
    long x;
    long y;
    boolean z;

    public TouchImageView(Context context)
    {
        super(context);
        a = new Matrix();
        b = new Matrix();
        c = 0;
        l = new PointF();
        m = new PointF();
        n = new PointF();
        r = 1.0F;
        s = 1.0F;
        t = 3F;
        u = 1.0F;
        v = new PointF(0.0F, 0.0F);
        w = 0.0F;
        x = 0L;
        y = 0L;
        z = false;
        H = null;
        I = false;
        onLeftSide = false;
        onTopSide = false;
        onRightSide = false;
        onBottomSide = false;
        super.setClickable(true);
        D = context;
        init();
    }

    public TouchImageView(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        a = new Matrix();
        b = new Matrix();
        c = 0;
        l = new PointF();
        m = new PointF();
        n = new PointF();
        r = 1.0F;
        s = 1.0F;
        t = 3F;
        u = 1.0F;
        v = new PointF(0.0F, 0.0F);
        w = 0.0F;
        x = 0L;
        y = 0L;
        z = false;
        H = null;
        I = false;
        onLeftSide = false;
        onTopSide = false;
        onRightSide = false;
        onBottomSide = false;
        super.setClickable(true);
        D = context;
        init();
    }

    static double a(TouchImageView touchimageview, PointF pointf, PointF pointf1)
    {
        return Math.sqrt(Math.pow(pointf.x - pointf1.x, 2D) + Math.pow(pointf.y - pointf1.y, 2D));
    }

    static float a(TouchImageView touchimageview, WrapMotionEvent wrapmotionevent)
    {
        float f1 = wrapmotionevent.getX(0) - wrapmotionevent.getX(1);
        float f2 = wrapmotionevent.getY(0) - wrapmotionevent.getY(1);
        return FloatMath.sqrt(f1 * f1 + f2 * f2);
    }

    static Object a(TouchImageView touchimageview)
    {
        return touchimageview.G;
    }

    static Timer a(TouchImageView touchimageview, Timer timer)
    {
        touchimageview.E = timer;
        return timer;
    }

    private void a()
    {
        c();
        float f1 = Math.round(h * r);
        float f2 = Math.round(i * r);
        onBottomSide = false;
        onTopSide = false;
        onRightSide = false;
        onLeftSide = false;
        if (-p < 10F)
        {
            onLeftSide = true;
        }
        if (f1 >= j && (f1 + p) - j < 10F || f1 <= j && f1 + -p <= j)
        {
            onRightSide = true;
        }
        if (-q < 10F)
        {
            onTopSide = true;
        }
        if (Math.abs((-q + k) - f2) < 10F)
        {
            onBottomSide = true;
        }
    }

    private void a(float f1, float f2)
    {
label0:
        {
            {
                float f3 = Math.round(h * r);
                float f4 = Math.round(i * r);
                c();
                if (f3 >= j)
                {
                    break label0;
                }
                if (f2 + q > 0.0F)
                {
                    f2 = -q;
                    f1 = 0.0F;
                } else
                if (f2 + q < -g)
                {
                    f2 = -(q + g);
                    f1 = 0.0F;
                } else
                {
                    f1 = 0.0F;
                }
            }
            a.postTranslate(f1, f2);
            a();
            return;
        }
        if (f4 < k)
        {
            if (f1 + p > 0.0F)
            {
                f1 = -p;
                f2 = 0.0F;
            } else
            if (f1 + p < -f)
            {
                f1 = -(p + f);
                f2 = 0.0F;
            } else
            {
                f2 = 0.0F;
            }
            continue; /* Loop/switch isn't completed */
        }
        if (f1 + p <= 0.0F) goto _L2; else goto _L1
_L1:
        f1 = -p;
_L4:
        if (f2 + q <= 0.0F)
        {
            break; /* Loop/switch isn't completed */
        }
        f2 = -q;
        continue; /* Loop/switch isn't completed */
_L2:
        if (f1 + p < -f)
        {
            f1 = -(p + f);
        }
        if (true) goto _L4; else goto _L3
_L3:
        if (f2 + q < -g)
        {
            f2 = -(q + g);
        }
        if (true) goto _L6; else goto _L5
_L5:
        break MISSING_BLOCK_LABEL_264;
_L6:
        break MISSING_BLOCK_LABEL_61;
    }

    static void a(TouchImageView touchimageview, float f1, float f2)
    {
        touchimageview.a(f1, f2);
    }

    static void a(TouchImageView touchimageview, PointF pointf, WrapMotionEvent wrapmotionevent)
    {
        float f1 = wrapmotionevent.getX(0) + wrapmotionevent.getX(1);
        float f2 = wrapmotionevent.getY(0) + wrapmotionevent.getY(1);
        pointf.set(f1 / 2.0F, f2 / 2.0F);
    }

    static PointF b(TouchImageView touchimageview, WrapMotionEvent wrapmotionevent)
    {
        float f1 = wrapmotionevent.getX(0) + wrapmotionevent.getX(1);
        float f2 = wrapmotionevent.getY(0) + wrapmotionevent.getY(1);
        return new PointF(f1 / 2.0F, f2 / 2.0F);
    }

    private void b()
    {
        f = j * r - j - 2.0F * d * r;
        g = k * r - k - 2.0F * e * r;
    }

    static void b(TouchImageView touchimageview)
    {
        touchimageview.c();
    }

    static Timer c(TouchImageView touchimageview)
    {
        return touchimageview.E;
    }

    private void c()
    {
        a.getValues(o);
        p = o[2];
        q = o[5];
    }

    private void d()
    {
        if (Math.abs(p + f / 2.0F) > 0.5F)
        {
            a.postTranslate(-(p + f / 2.0F), 0.0F);
        }
        if (Math.abs(q + g / 2.0F) > 0.5F)
        {
            a.postTranslate(0.0F, -(q + g / 2.0F));
        }
    }

    static void d(TouchImageView touchimageview)
    {
        touchimageview.b();
    }

    static void e(TouchImageView touchimageview)
    {
        touchimageview.d();
    }

    static void f(TouchImageView touchimageview)
    {
        touchimageview.a();
    }

    static Handler g(TouchImageView touchimageview)
    {
        return touchimageview.H;
    }

    static android.view.View.OnClickListener h(TouchImageView touchimageview)
    {
        return touchimageview.F;
    }

    public void destroyView()
    {
        if (E != null)
        {
            E.cancel();
        }
        H = null;
        setImageBitmap(null);
    }

    public Bitmap getBitmap()
    {
        return A;
    }

    protected void init()
    {
        H = new e(this);
        a.setTranslate(1.0F, 1.0F);
        o = new float[9];
        setImageMatrix(a);
        setScaleType(android.widget.ImageView.ScaleType.MATRIX);
        if (android.os.Build.VERSION.SDK_INT >= 8)
        {
            G = new ScaleGestureDetector(D, new c(this, (byte)0));
        }
        setOnTouchListener(new b(this));
    }

    public boolean isActionModeZoom()
    {
        return c == 2;
    }

    public boolean isZoomToOriginalSize()
    {
        return I;
    }

    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        if (z)
        {
            float f1 = v.x * w;
            float f2 = v.y * w;
            if (f1 <= j && f2 <= k)
            {
                w = 0.9F * w;
                if ((double)Math.abs(f1) >= 0.10000000000000001D || (double)Math.abs(f2) >= 0.10000000000000001D)
                {
                    a(f1, f2);
                    setImageMatrix(a);
                    return;
                }
            }
        }
    }

    protected void onMeasure(int i1, int j1)
    {
        super.onMeasure(i1, j1);
        j = android.view.View.MeasureSpec.getSize(i1);
        k = android.view.View.MeasureSpec.getSize(j1);
        float f1 = Math.min(j / B, k / C);
        a.setScale(f1, f1);
        setImageMatrix(a);
        r = 1.0F;
        e = k - f1 * C;
        d = j - f1 * B;
        e = e / 2.0F;
        d = d / 2.0F;
        a.postTranslate(d, e);
        h = j - 2.0F * d;
        i = k - 2.0F * e;
        b();
        setImageMatrix(a);
    }

    public boolean pagerCanScroll()
    {
        while (c != 0 || r != s) 
        {
            return false;
        }
        return true;
    }

    public void resetScale()
    {
        c();
        a.postScale(s / r, s / r, j / 2.0F, k / 2.0F);
        r = s;
        b();
        a(0.0F, 0.0F);
        d();
        setImageMatrix(a);
        invalidate();
    }

    public void setImageBitmap(Bitmap bitmap)
    {
        super.setImageBitmap(bitmap);
        A = bitmap;
        if (bitmap != null)
        {
            B = bitmap.getWidth();
            C = bitmap.getHeight();
        }
    }

    public void setOnClickListener(android.view.View.OnClickListener onclicklistener)
    {
        F = onclicklistener;
    }

    public void setZoomToOriginalSize(boolean flag)
    {
        I = flag;
    }
}
