// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing.pdf417.decoder;


final class g extends Enum
{

    public static final g a;
    public static final g b;
    public static final g c;
    public static final g d;
    public static final g e;
    public static final g f;
    private static final g g[];

    private g(String s, int i)
    {
        super(s, i);
    }

    public static g valueOf(String s)
    {
        return (g)Enum.valueOf(com/google/zxing/pdf417/decoder/g, s);
    }

    public static g[] values()
    {
        return (g[])g.clone();
    }

    static 
    {
        a = new g("ALPHA", 0);
        b = new g("LOWER", 1);
        c = new g("MIXED", 2);
        d = new g("PUNCT", 3);
        e = new g("ALPHA_SHIFT", 4);
        f = new g("PUNCT_SHIFT", 5);
        g ag[] = new g[6];
        ag[0] = a;
        ag[1] = b;
        ag[2] = c;
        ag[3] = d;
        ag[4] = e;
        ag[5] = f;
        g = ag;
    }
}
