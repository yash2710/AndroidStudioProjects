// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing.common.detector;


public final class MathUtils
{

    private MathUtils()
    {
    }

    public static float distance(float f, float f1, float f2, float f3)
    {
        float f4 = f - f2;
        float f5 = f1 - f3;
        return (float)Math.sqrt(f4 * f4 + f5 * f5);
    }

    public static float distance(int i, int j, int k, int l)
    {
        int i1 = i - k;
        int j1 = j - l;
        return (float)Math.sqrt(i1 * i1 + j1 * j1);
    }

    public static int round(float f)
    {
        return (int)(0.5F + f);
    }
}
