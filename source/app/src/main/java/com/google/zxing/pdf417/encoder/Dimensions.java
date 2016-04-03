// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing.pdf417.encoder;


public final class Dimensions
{

    private final int a;
    private final int b;
    private final int c;
    private final int d;

    public Dimensions(int i, int j, int k, int l)
    {
        a = i;
        b = j;
        c = k;
        d = l;
    }

    public final int getMaxCols()
    {
        return b;
    }

    public final int getMaxRows()
    {
        return d;
    }

    public final int getMinCols()
    {
        return a;
    }

    public final int getMinRows()
    {
        return c;
    }
}
