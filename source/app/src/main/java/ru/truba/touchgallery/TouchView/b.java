// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package ru.truba.touchgallery.TouchView;

import android.graphics.Matrix;
import android.graphics.PointF;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import java.util.Timer;

// Referenced classes of package ru.truba.touchgallery.TouchView:
//            WrapMotionEvent, TouchImageView, d

final class b
    implements android.view.View.OnTouchListener
{

    private TouchImageView a;

    b(TouchImageView touchimageview)
    {
        a = touchimageview;
        super();
    }

    public final boolean onTouch(View view, MotionEvent motionevent)
    {
        WrapMotionEvent wrapmotionevent;
        PointF pointf;
        wrapmotionevent = WrapMotionEvent.wrap(motionevent);
        if (TouchImageView.a(a) != null)
        {
            ((ScaleGestureDetector)TouchImageView.a(a)).onTouchEvent(motionevent);
        }
        TouchImageView.b(a);
        pointf = new PointF(wrapmotionevent.getX(), wrapmotionevent.getY());
        0xff & wrapmotionevent.getAction();
        JVM INSTR tableswitch 0 6: default 104
    //                   0 127
    //                   1 269
    //                   2 692
    //                   3 104
    //                   4 104
    //                   5 198
    //                   6 641;
           goto _L1 _L2 _L3 _L4 _L1 _L1 _L5 _L6
_L1:
        a.setImageMatrix(a.a);
        a.invalidate();
        return false;
_L2:
        a.z = false;
        a.b.set(a.a);
        a.l.set(wrapmotionevent.getX(), wrapmotionevent.getY());
        a.n.set(a.l);
        a.c = 1;
        continue; /* Loop/switch isn't completed */
_L5:
        a.u = TouchImageView.a(a, wrapmotionevent);
        if (a.u > 10F)
        {
            a.b.set(a.a);
            TouchImageView.a(a, a.m, wrapmotionevent);
            a.c = 2;
        }
        continue; /* Loop/switch isn't completed */
_L3:
        a.z = true;
        a.c = 0;
        int i = (int)Math.abs(wrapmotionevent.getX() - a.n.x);
        int j = (int)Math.abs(wrapmotionevent.getY() - a.n.y);
        if (i < 10 && j < 10)
        {
            long l1 = System.currentTimeMillis();
            if (l1 - a.x <= 600L)
            {
                if (TouchImageView.c(a) != null)
                {
                    TouchImageView.c(a).cancel();
                }
                if (a.r == 1.0F)
                {
                    float f5 = a.t / a.r;
                    a.a.postScale(f5, f5, a.n.x, a.n.y);
                    a.r = a.t;
                } else
                {
                    a.a.postScale(a.s / a.r, a.s / a.r, a.j / 2.0F, a.k / 2.0F);
                    a.r = a.s;
                }
                TouchImageView.d(a);
                TouchImageView.a(a, 0.0F, 0.0F);
                a.x = 0L;
            } else
            {
                a.x = l1;
                TouchImageView.a(a, new Timer());
                TouchImageView.c(a).schedule(new d(a, (byte)0), 300L);
            }
            if (a.r == a.s)
            {
                TouchImageView.e(a);
            }
        }
        continue; /* Loop/switch isn't completed */
_L6:
        a.c = 0;
        a.w = 0.0F;
        a.b.set(a.a);
        a.u = TouchImageView.a(a, wrapmotionevent);
        continue; /* Loop/switch isn't completed */
_L4:
        float f1;
        a.z = false;
        if (a.c == 1)
        {
            float f3 = pointf.x - a.l.x;
            float f4 = pointf.y - a.l.y;
            long l = System.currentTimeMillis();
            a.w = 0.9F * ((float)TouchImageView.a(a, pointf, a.l) / (float)(l - a.y));
            a.y = l;
            TouchImageView.a(a, f3, f4);
            a.v.set(f3, f4);
            a.l.set(pointf.x, pointf.y);
            continue; /* Loop/switch isn't completed */
        }
        if (TouchImageView.a(a) != null || a.c != 2)
        {
            continue; /* Loop/switch isn't completed */
        }
        float f = TouchImageView.a(a, wrapmotionevent);
        if (motionevent.getPointerCount() < 2 || 10F > Math.abs(a.u - f) || Math.abs(a.u - f) > 50F)
        {
            continue; /* Loop/switch isn't completed */
        }
        f1 = f / a.u;
        a.u = f;
        float f2 = a.r;
        TouchImageView touchimageview = a;
        touchimageview.r = f1 * touchimageview.r;
        if (a.r > a.t)
        {
            a.r = a.t;
            f1 = a.t / f2;
        } else
        if (a.r < a.s)
        {
            a.r = a.s;
            f1 = a.s / f2;
        }
        TouchImageView.d(a);
        if (a.h * a.r > a.j && a.i * a.r > a.k)
        {
            break; /* Loop/switch isn't completed */
        }
        a.a.postScale(f1, f1, a.j / 2.0F, a.k / 2.0F);
        if (f1 < 1.0F)
        {
            TouchImageView.b(a);
            if (f1 < 1.0F)
            {
                TouchImageView.e(a);
            }
        }
_L8:
        TouchImageView.f(a);
        if (true) goto _L1; else goto _L7
_L7:
        PointF pointf1 = TouchImageView.b(a, wrapmotionevent);
        a.a.postScale(f1, f1, pointf1.x, pointf1.y);
        TouchImageView.b(a);
        if (f1 < 1.0F)
        {
            if (a.p < -a.f)
            {
                a.a.postTranslate(-(a.p + a.f), 0.0F);
            } else
            if (a.p > 0.0F)
            {
                a.a.postTranslate(-a.p, 0.0F);
            }
            if (a.q < -a.g)
            {
                a.a.postTranslate(0.0F, -(a.q + a.g));
            } else
            if (a.q > 0.0F)
            {
                a.a.postTranslate(0.0F, -a.q);
            }
        }
          goto _L8
        if (true) goto _L1; else goto _L9
_L9:
    }
}
