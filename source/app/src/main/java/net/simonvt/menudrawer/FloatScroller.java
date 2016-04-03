// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package net.simonvt.menudrawer;

import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;

public class FloatScroller
{

    private float a;
    private float b;
    private float c;
    private long d;
    private int e;
    private float f;
    private float g;
    private boolean h;
    private Interpolator i;

    public FloatScroller(Interpolator interpolator)
    {
        h = true;
        i = interpolator;
    }

    public void abortAnimation()
    {
        c = b;
        h = true;
    }

    public boolean computeScrollOffset()
    {
        if (h)
        {
            return false;
        }
        int j = (int)(AnimationUtils.currentAnimationTimeMillis() - d);
        if (j < e)
        {
            float f1 = (float)j * f;
            float f2 = i.getInterpolation(f1);
            c = a + f2 * g;
            return true;
        } else
        {
            c = b;
            h = true;
            return true;
        }
    }

    public void extendDuration(int j)
    {
        e = j + timePassed();
        f = 1.0F / (float)e;
        h = false;
    }

    public final void forceFinished(boolean flag)
    {
        h = flag;
    }

    public final float getCurr()
    {
        return c;
    }

    public final int getDuration()
    {
        return e;
    }

    public final float getFinal()
    {
        return b;
    }

    public final float getStart()
    {
        return a;
    }

    public final boolean isFinished()
    {
        return h;
    }

    public void setFinal(float f1)
    {
        b = f1;
        g = b - a;
        h = false;
    }

    public void startScroll(float f1, float f2, int j)
    {
        h = false;
        e = j;
        d = AnimationUtils.currentAnimationTimeMillis();
        a = f1;
        b = f1 + f2;
        g = f2;
        f = 1.0F / (float)e;
    }

    public int timePassed()
    {
        return (int)(AnimationUtils.currentAnimationTimeMillis() - d);
    }
}
