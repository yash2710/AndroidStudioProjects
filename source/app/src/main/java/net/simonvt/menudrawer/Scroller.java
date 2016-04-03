// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package net.simonvt.menudrawer;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.util.FloatMath;
import android.view.ViewConfiguration;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;

public class Scroller
{

    private static final float u = (float)(Math.log(0.75D) / Math.log(0.90000000000000002D));
    private static final float v[];
    private static float y = 8F;
    private static float z = 1.0F;
    private int a;
    private int b;
    private int c;
    private int d;
    private int e;
    private int f;
    private int g;
    private int h;
    private int i;
    private int j;
    private int k;
    private long l;
    private int m;
    private float n;
    private float o;
    private float p;
    private boolean q;
    private Interpolator r;
    private boolean s;
    private float t;
    private float w;
    private final float x;

    public Scroller(Context context)
    {
        this(context, null);
    }

    public Scroller(Context context, Interpolator interpolator)
    {
        boolean flag;
        if (context.getApplicationInfo().targetSdkVersion >= 11)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        this(context, interpolator, flag);
    }

    public Scroller(Context context, Interpolator interpolator, boolean flag)
    {
        q = true;
        r = interpolator;
        x = 160F * context.getResources().getDisplayMetrics().density;
        w = a(ViewConfiguration.getScrollFriction());
        s = flag;
    }

    private float a(float f1)
    {
        return f1 * (386.0878F * x);
    }

    private static float b(float f1)
    {
        float f2 = f1 * y;
        float f3;
        if (f2 < 1.0F)
        {
            f3 = f2 - (1.0F - (float)Math.exp(-f2));
        } else
        {
            f3 = 0.3678795F + 0.6321205F * (1.0F - (float)Math.exp(1.0F - f2));
        }
        return f3 * z;
    }

    public void abortAnimation()
    {
        j = d;
        k = e;
        q = true;
    }

    public boolean computeScrollOffset()
    {
        int i1;
        if (q)
        {
            return false;
        }
        i1 = (int)(AnimationUtils.currentAnimationTimeMillis() - l);
        if (i1 >= m) goto _L2; else goto _L1
_L1:
        a;
        JVM INSTR tableswitch 0 1: default 52
    //                   0 54
    //                   1 134;
           goto _L3 _L4 _L5
_L3:
        return true;
_L4:
        float f7 = (float)i1 * n;
        float f8;
        if (r == null)
        {
            f8 = b(f7);
        } else
        {
            f8 = r.getInterpolation(f7);
        }
        j = b + Math.round(f8 * o);
        k = c + Math.round(f8 * p);
        continue; /* Loop/switch isn't completed */
_L5:
        float f1 = (float)i1 / (float)m;
        int j1 = (int)(100F * f1);
        float f2 = (float)j1 / 100F;
        float f3 = (float)(j1 + 1) / 100F;
        float f4 = v[j1];
        float f5 = v[j1 + 1];
        float f6 = f4 + ((f1 - f2) / (f3 - f2)) * (f5 - f4);
        j = b + Math.round(f6 * (float)(d - b));
        j = Math.min(j, g);
        j = Math.max(j, f);
        k = c + Math.round(f6 * (float)(e - c));
        k = Math.min(k, i);
        k = Math.max(k, h);
        if (j == d && k == e)
        {
            q = true;
        }
        continue; /* Loop/switch isn't completed */
_L2:
        j = d;
        k = e;
        q = true;
        if (true) goto _L3; else goto _L6
_L6:
    }

    public void extendDuration(int i1)
    {
        m = i1 + timePassed();
        n = 1.0F / (float)m;
        q = false;
    }

    public void fling(int i1, int j1, int k1, int l1, int i2, int j2, int k2, 
            int l2)
    {
        if (s && !q)
        {
            float f4 = getCurrVelocity();
            float f5 = d - b;
            float f6 = e - c;
            float f7 = FloatMath.sqrt(f5 * f5 + f6 * f6);
            float f8 = f5 / f7;
            float f9 = f6 / f7;
            float f10 = f8 * f4;
            float f11 = f4 * f9;
            if (Math.signum(k1) == Math.signum(f10) && Math.signum(l1) == Math.signum(f11))
            {
                k1 = (int)(f10 + (float)k1);
                l1 = (int)(f11 + (float)l1);
            }
        }
        a = 1;
        q = false;
        float f1 = FloatMath.sqrt(k1 * k1 + l1 * l1);
        t = f1;
        double d1 = Math.log((0.4F * f1) / 800F);
        m = (int)(1000D * Math.exp(d1 / ((double)u - 1.0D)));
        l = AnimationUtils.currentAnimationTimeMillis();
        b = i1;
        c = j1;
        float f2;
        float f3;
        int i3;
        if (f1 == 0.0F)
        {
            f2 = 1.0F;
        } else
        {
            f2 = (float)k1 / f1;
        }
        if (f1 == 0.0F)
        {
            f3 = 1.0F;
        } else
        {
            f3 = (float)l1 / f1;
        }
        i3 = (int)(800D * Math.exp(d1 * ((double)u / ((double)u - 1.0D))));
        f = i2;
        g = j2;
        h = k2;
        i = l2;
        d = i1 + Math.round(f2 * (float)i3);
        d = Math.min(d, g);
        d = Math.max(d, f);
        e = j1 + Math.round(f3 * (float)i3);
        e = Math.min(e, i);
        e = Math.max(e, h);
    }

    public final void forceFinished(boolean flag)
    {
        q = flag;
    }

    public float getCurrVelocity()
    {
        return t - (w * (float)timePassed()) / 2000F;
    }

    public final int getCurrX()
    {
        return j;
    }

    public final int getCurrY()
    {
        return k;
    }

    public final int getDuration()
    {
        return m;
    }

    public final int getFinalX()
    {
        return d;
    }

    public final int getFinalY()
    {
        return e;
    }

    public final int getStartX()
    {
        return b;
    }

    public final int getStartY()
    {
        return c;
    }

    public final boolean isFinished()
    {
        return q;
    }

    public boolean isScrollingInDirection(float f1, float f2)
    {
        return !q && Math.signum(f1) == Math.signum(d - b) && Math.signum(f2) == Math.signum(e - c);
    }

    public void setFinalX(int i1)
    {
        d = i1;
        o = d - b;
        q = false;
    }

    public void setFinalY(int i1)
    {
        e = i1;
        p = e - c;
        q = false;
    }

    public final void setFriction(float f1)
    {
        w = a(f1);
    }

    public void startScroll(int i1, int j1, int k1, int l1)
    {
        startScroll(i1, j1, k1, l1, 250);
    }

    public void startScroll(int i1, int j1, int k1, int l1, int i2)
    {
        a = 0;
        q = false;
        m = i2;
        l = AnimationUtils.currentAnimationTimeMillis();
        b = i1;
        c = j1;
        d = i1 + k1;
        e = j1 + l1;
        o = k1;
        p = l1;
        n = 1.0F / (float)m;
    }

    public int timePassed()
    {
        return (int)(AnimationUtils.currentAnimationTimeMillis() - l);
    }

    static 
    {
        v = new float[101];
        float f1 = 0.0F;
        for (int i1 = 0; i1 <= 100;)
        {
            float f2 = (float)i1 / 100F;
            float f3 = 1.0F;
            float f4 = f1;
            float f5;
            float f6;
            do
            {
                f5 = f4 + (f3 - f4) / 2.0F;
                f6 = 3F * f5 * (1.0F - f5);
                float f7 = f6 * (0.4F * (1.0F - f5) + 0.6F * f5) + f5 * (f5 * f5);
                if ((double)Math.abs(f7 - f2) < 1.0000000000000001E-05D)
                {
                    break;
                }
                if (f7 > f2)
                {
                    f3 = f5;
                } else
                {
                    f4 = f5;
                }
            } while (true);
            float f8 = f6 + f5 * (f5 * f5);
            v[i1] = f8;
            i1++;
            f1 = f4;
        }

        v[100] = 1.0F;
        z = 1.0F / b(1.0F);
    }
}
