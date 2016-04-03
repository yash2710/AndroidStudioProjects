// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing.qrcode.decoder;


public final class b
{

    private final int a;
    private final getNumBlocks b[];

    public final b[] getECBlocks()
    {
        return b;
    }

    public final int getECCodewordsPerBlock()
    {
        return a;
    }

    public final int getNumBlocks()
    {
        int i = 0;
        a aa[] = b;
        int j = aa.length;
        int k = 0;
        for (; i < j; i++)
        {
            k += aa[i].unt();
        }

        return k;
    }

    public final int getTotalECCodewords()
    {
        return a * getNumBlocks();
    }

    transient (int i,  a1[])
    {
        a = i;
        b = a1;
    }
}
