// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing.pdf417.encoder;

import java.lang.reflect.Array;

// Referenced classes of package com.google.zxing.pdf417.encoder:
//            a

public final class BarcodeMatrix
{

    private final a a[];
    private int b;
    private final int c;
    private final int d;

    BarcodeMatrix(int i, int j)
    {
        a = new a[i + 2];
        int k = a.length;
        for (int l = 0; l < k; l++)
        {
            a[l] = new a(1 + 17 * (j + 4));
        }

        d = j * 17;
        c = i + 2;
        b = 0;
    }

    final void a()
    {
        b = 1 + b;
    }

    final a b()
    {
        return a[b];
    }

    public final byte[][] getMatrix()
    {
        return getScaledMatrix(1, 1);
    }

    public final byte[][] getScaledMatrix(int i)
    {
        return getScaledMatrix(i, i);
    }

    public final byte[][] getScaledMatrix(int i, int j)
    {
        int ai[] = {
            j * c, i * d
        };
        byte abyte0[][] = (byte[][])Array.newInstance(Byte.TYPE, ai);
        int k = j * c;
        for (int l = 0; l < k; l++)
        {
            abyte0[-1 + (k - l)] = a[l / j].a(i);
        }

        return abyte0;
    }
}
