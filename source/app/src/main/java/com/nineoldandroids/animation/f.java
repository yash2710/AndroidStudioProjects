// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.nineoldandroids.animation;

import android.view.animation.Interpolator;
import java.util.ArrayList;

// Referenced classes of package com.nineoldandroids.animation:
//            k, h, Keyframe, TypeEvaluator

final class f extends k
{

    private float e;
    private float f;
    private float g;
    private boolean h;

    public transient f(h ah[])
    {
        super(ah);
        h = true;
    }

    public final f clone()
    {
        ArrayList arraylist = c;
        int i = c.size();
        h ah[] = new h[i];
        for (int j = 0; j < i; j++)
        {
            ah[j] = (h)((Keyframe)arraylist.get(j)).clone();
        }

        return new f(ah);
    }

    public final volatile k clone()
    {
        return clone();
    }

    public final volatile Object clone()
    {
        return clone();
    }

    public final float getFloatValue(float f1)
    {
        int i = 1;
        if (a == 2)
        {
            if (h)
            {
                h = false;
                e = ((h)c.get(0)).getFloatValue();
                f = ((h)c.get(i)).getFloatValue();
                g = f - e;
            }
            if (b != null)
            {
                f1 = b.getInterpolation(f1);
            }
            if (d == null)
            {
                return e + f1 * g;
            } else
            {
                return ((Number)d.evaluate(f1, Float.valueOf(e), Float.valueOf(f))).floatValue();
            }
        }
        if (f1 <= 0.0F)
        {
            h h5 = (h)c.get(0);
            h h6 = (h)c.get(i);
            float f10 = h5.getFloatValue();
            float f11 = h6.getFloatValue();
            float f12 = h5.getFraction();
            float f13 = h6.getFraction();
            Interpolator interpolator2 = h6.getInterpolator();
            if (interpolator2 != null)
            {
                f1 = interpolator2.getInterpolation(f1);
            }
            float f14 = (f1 - f12) / (f13 - f12);
            if (d == null)
            {
                return f10 + f14 * (f11 - f10);
            } else
            {
                return ((Number)d.evaluate(f14, Float.valueOf(f10), Float.valueOf(f11))).floatValue();
            }
        }
        if (f1 >= 1.0F)
        {
            h h3 = (h)c.get(-2 + a);
            h h4 = (h)c.get(-1 + a);
            float f5 = h3.getFloatValue();
            float f6 = h4.getFloatValue();
            float f7 = h3.getFraction();
            float f8 = h4.getFraction();
            Interpolator interpolator1 = h4.getInterpolator();
            if (interpolator1 != null)
            {
                f1 = interpolator1.getInterpolation(f1);
            }
            float f9 = (f1 - f7) / (f8 - f7);
            if (d == null)
            {
                return f5 + f9 * (f6 - f5);
            } else
            {
                return ((Number)d.evaluate(f9, Float.valueOf(f5), Float.valueOf(f6))).floatValue();
            }
        }
        h h2;
        for (h h1 = (h)c.get(0); i < a; h1 = h2)
        {
            h2 = (h)c.get(i);
            if (f1 < h2.getFraction())
            {
                Interpolator interpolator = h2.getInterpolator();
                if (interpolator != null)
                {
                    f1 = interpolator.getInterpolation(f1);
                }
                float f2 = (f1 - h1.getFraction()) / (h2.getFraction() - h1.getFraction());
                float f3 = h1.getFloatValue();
                float f4 = h2.getFloatValue();
                if (d == null)
                {
                    return f3 + f2 * (f4 - f3);
                } else
                {
                    return ((Number)d.evaluate(f2, Float.valueOf(f3), Float.valueOf(f4))).floatValue();
                }
            }
            i++;
        }

        return ((Number)((Keyframe)c.get(-1 + a)).getValue()).floatValue();
    }

    public final Object getValue(float f1)
    {
        return Float.valueOf(getFloatValue(f1));
    }
}
