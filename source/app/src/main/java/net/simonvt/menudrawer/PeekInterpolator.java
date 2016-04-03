// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package net.simonvt.menudrawer;

import android.view.animation.Interpolator;

// Referenced classes of package net.simonvt.menudrawer:
//            SinusoidalInterpolator

public class PeekInterpolator
    implements Interpolator
{

    private static final SinusoidalInterpolator a = new SinusoidalInterpolator();

    public PeekInterpolator()
    {
    }

    public float getInterpolation(float f)
    {
        float f1 = 1.0F;
        if (f < 0.3333333F)
        {
            f1 = a.getInterpolation(f * 3F);
        } else
        if (f > 0.6666667F)
        {
            float f2 = 3F * ((f + 0.3333333F) - f1);
            return f1 - a.getInterpolation(f2);
        }
        return f1;
    }

}
