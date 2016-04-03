// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package net.simonvt.menudrawer;

import android.view.animation.Interpolator;

public class SinusoidalInterpolator
    implements Interpolator
{

    public SinusoidalInterpolator()
    {
    }

    public float getInterpolation(float f)
    {
        return (float)(0.5D + 0.5D * Math.sin(3.1415926535897931D * (double)f - 1.5707963267948966D));
    }
}
