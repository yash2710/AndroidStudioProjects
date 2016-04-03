// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing.aztec.encoder;

import com.google.zxing.common.BitMatrix;

public final class AztecCode
{

    private boolean a;
    private int b;
    private int c;
    private int d;
    private BitMatrix e;

    public AztecCode()
    {
    }

    public final int getCodeWords()
    {
        return d;
    }

    public final int getLayers()
    {
        return c;
    }

    public final BitMatrix getMatrix()
    {
        return e;
    }

    public final int getSize()
    {
        return b;
    }

    public final boolean isCompact()
    {
        return a;
    }

    public final void setCodeWords(int i)
    {
        d = i;
    }

    public final void setCompact(boolean flag)
    {
        a = flag;
    }

    public final void setLayers(int i)
    {
        c = i;
    }

    public final void setMatrix(BitMatrix bitmatrix)
    {
        e = bitmatrix;
    }

    public final void setSize(int i)
    {
        b = i;
    }
}
