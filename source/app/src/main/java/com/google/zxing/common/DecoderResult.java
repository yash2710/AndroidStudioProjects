// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing.common;

import java.util.List;

public final class DecoderResult
{

    private final byte a[];
    private final String b;
    private final List c;
    private final String d;
    private Integer e;
    private Integer f;
    private Object g;

    public DecoderResult(byte abyte0[], String s, List list, String s1)
    {
        a = abyte0;
        b = s;
        c = list;
        d = s1;
    }

    public final List getByteSegments()
    {
        return c;
    }

    public final String getECLevel()
    {
        return d;
    }

    public final Integer getErasures()
    {
        return f;
    }

    public final Integer getErrorsCorrected()
    {
        return e;
    }

    public final Object getOther()
    {
        return g;
    }

    public final byte[] getRawBytes()
    {
        return a;
    }

    public final String getText()
    {
        return b;
    }

    public final void setErasures(Integer integer)
    {
        f = integer;
    }

    public final void setErrorsCorrected(Integer integer)
    {
        e = integer;
    }

    public final void setOther(Object obj)
    {
        g = obj;
    }
}
