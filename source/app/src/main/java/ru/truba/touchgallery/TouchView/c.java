// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package ru.truba.touchgallery.TouchView;

import android.graphics.Matrix;
import android.view.ScaleGestureDetector;

// Referenced classes of package ru.truba.touchgallery.TouchView:
//            TouchImageView

final class c extends android.view.ScaleGestureDetector.SimpleOnScaleGestureListener
{

    private TouchImageView a;

    private c(TouchImageView touchimageview)
    {
        a = touchimageview;
        super();
    }

    c(TouchImageView touchimageview, byte byte0)
    {
        this(touchimageview);
    }

    public final boolean onScale(ScaleGestureDetector scalegesturedetector)
    {
        float f = (float)Math.min(Math.max(0.95F, scalegesturedetector.getScaleFactor()), 1.05D);
        float f1 = a.r;
        TouchImageView touchimageview = a;
        touchimageview.r = f * touchimageview.r;
        if (a.r > a.t)
        {
            a.r = a.t;
            f = a.t / f1;
        } else
        if (a.r < a.s)
        {
            a.r = a.s;
            f = a.s / f1;
        }
        a.f = a.j * a.r - a.j - 2.0F * a.d * a.r;
        a.g = a.k * a.r - a.k - 2.0F * a.e * a.r;
        if (a.h * a.r <= a.j || a.i * a.r <= a.k)
        {
            a.a.postScale(f, f, a.j / 2.0F, a.k / 2.0F);
            if (f < 1.0F)
            {
                a.a.getValues(a.o);
                float f2 = a.o[2];
                float f3 = a.o[5];
                if (f < 1.0F)
                {
                    if ((float)Math.round(a.h * a.r) < a.j)
                    {
                        if (f3 < -a.g)
                        {
                            a.a.postTranslate(0.0F, -(f3 + a.g));
                        } else
                        if (f3 > 0.0F)
                        {
                            a.a.postTranslate(0.0F, -f3);
                        }
                    } else
                    if (f2 < -a.f)
                    {
                        a.a.postTranslate(-(f2 + a.f), 0.0F);
                    } else
                    if (f2 > 0.0F)
                    {
                        a.a.postTranslate(-f2, 0.0F);
                    }
                }
            }
        } else
        {
            a.a.postScale(f, f, scalegesturedetector.getFocusX(), scalegesturedetector.getFocusY());
            a.a.getValues(a.o);
            float f4 = a.o[2];
            float f5 = a.o[5];
            if (f < 1.0F)
            {
                if (f4 < -a.f)
                {
                    a.a.postTranslate(-(f4 + a.f), 0.0F);
                } else
                if (f4 > 0.0F)
                {
                    a.a.postTranslate(-f4, 0.0F);
                }
                if (f5 < -a.g)
                {
                    a.a.postTranslate(0.0F, -(f5 + a.g));
                } else
                if (f5 > 0.0F)
                {
                    a.a.postTranslate(0.0F, -f5);
                }
            }
        }
        return true;
    }

    public final boolean onScaleBegin(ScaleGestureDetector scalegesturedetector)
    {
        a.c = 2;
        return true;
    }
}
