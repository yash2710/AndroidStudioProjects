// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing.pdf417.encoder;


final class a
{

    private final byte a[];
    private int b;

    a(int i)
    {
        a = new byte[i];
        b = 0;
    }

    final void a(boolean flag, int i)
    {
        int j = 0;
        while (j < i) 
        {
            int k = b;
            b = k + 1;
            byte abyte0[] = a;
            int l;
            if (flag)
            {
                l = 1;
            } else
            {
                l = 0;
            }
            abyte0[k] = (byte)l;
            j++;
        }
    }

    final byte[] a(int i)
    {
        byte abyte0[] = new byte[i * a.length];
        for (int j = 0; j < abyte0.length; j++)
        {
            abyte0[j] = a[j / i];
        }

        return abyte0;
    }
}
