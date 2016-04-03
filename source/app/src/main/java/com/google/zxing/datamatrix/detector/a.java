// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing.datamatrix.detector;

import com.google.zxing.ResultPoint;

final class a
{

    private final ResultPoint a;
    private final ResultPoint b;
    private final int c;

    private a(ResultPoint resultpoint, ResultPoint resultpoint1, int i)
    {
        a = resultpoint;
        b = resultpoint1;
        c = i;
    }

    a(ResultPoint resultpoint, ResultPoint resultpoint1, int i, byte byte0)
    {
        this(resultpoint, resultpoint1, i);
    }

    final ResultPoint a()
    {
        return a;
    }

    final ResultPoint b()
    {
        return b;
    }

    public final int getTransitions()
    {
        return c;
    }

    public final String toString()
    {
        return (new StringBuilder()).append(a).append("/").append(b).append('/').append(c).toString();
    }
}
