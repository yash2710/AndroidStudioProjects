// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing.aztec.detector;

import com.google.zxing.ResultPoint;

final class a
{

    private final int a;
    private final int b;

    a(int i, int j)
    {
        a = i;
        b = j;
    }

    final ResultPoint a()
    {
        return new ResultPoint(a, b);
    }

    final int b()
    {
        return a;
    }

    final int c()
    {
        return b;
    }
}
