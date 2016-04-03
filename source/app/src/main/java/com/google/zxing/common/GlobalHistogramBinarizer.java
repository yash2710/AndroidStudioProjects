// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing.common;

import com.google.zxing.Binarizer;
import com.google.zxing.LuminanceSource;
import com.google.zxing.NotFoundException;

// Referenced classes of package com.google.zxing.common:
//            BitMatrix, BitArray

public class GlobalHistogramBinarizer extends Binarizer
{

    private static final byte a[] = new byte[0];
    private byte b[];
    private final int c[] = new int[32];

    public GlobalHistogramBinarizer(LuminanceSource luminancesource)
    {
        super(luminancesource);
        b = a;
    }

    private static int a(int ai[])
    {
        int i = ai.length;
        int j = 0;
        int k = 0;
        int l = 0;
        int i1 = 0;
        for (; j < i; j++)
        {
            if (ai[j] > k)
            {
                k = ai[j];
                l = j;
            }
            if (ai[j] > i1)
            {
                i1 = ai[j];
            }
        }

        int j1 = 0;
        int k1 = 0;
        int l1 = 0;
        while (j1 < i) 
        {
            int i4 = j1 - l;
            int j4 = i4 * (i4 * ai[j1]);
            int i2;
            int j2;
            int k2;
            int l2;
            int i3;
            int j3;
            int k3;
            int l3;
            if (j4 > l1)
            {
                k1 = j1;
            } else
            {
                j4 = l1;
            }
            j1++;
            l1 = j4;
        }
        if (l > k1)
        {
            i2 = l;
            j2 = k1;
        } else
        {
            i2 = k1;
            j2 = l;
        }
        if (i2 - j2 <= i >> 4)
        {
            throw NotFoundException.getNotFoundInstance();
        }
        k2 = i2 - 1;
        l2 = -1;
        i3 = i2 - 1;
        while (i3 > j2) 
        {
            j3 = i3 - j2;
            k3 = j3 * j3 * (i2 - i3) * (i1 - ai[i3]);
            if (k3 > l2)
            {
                l3 = i3;
            } else
            {
                k3 = l2;
                l3 = k2;
            }
            i3--;
            k2 = l3;
            l2 = k3;
        }
        return k2 << 3;
    }

    private void a(int i)
    {
        if (b.length < i)
        {
            b = new byte[i];
        }
        for (int j = 0; j < 32; j++)
        {
            c[j] = 0;
        }

    }

    public Binarizer createBinarizer(LuminanceSource luminancesource)
    {
        return new GlobalHistogramBinarizer(luminancesource);
    }

    public BitMatrix getBlackMatrix()
    {
        LuminanceSource luminancesource = getLuminanceSource();
        int i = luminancesource.getWidth();
        int j = luminancesource.getHeight();
        BitMatrix bitmatrix = new BitMatrix(i, j);
        a(i);
        int ai[] = c;
        for (int k = 1; k < 5; k++)
        {
            byte abyte1[] = luminancesource.getRow((j * k) / 5, b);
            int l1 = (i << 2) / 5;
            for (int i2 = i / 5; i2 < l1; i2++)
            {
                int j2 = (0xff & abyte1[i2]) >> 3;
                ai[j2] = 1 + ai[j2];
            }

        }

        int l = a(ai);
        byte abyte0[] = luminancesource.getMatrix();
        for (int i1 = 0; i1 < j; i1++)
        {
            int j1 = i1 * i;
            for (int k1 = 0; k1 < i; k1++)
            {
                if ((0xff & abyte0[j1 + k1]) < l)
                {
                    bitmatrix.set(k1, i1);
                }
            }

        }

        return bitmatrix;
    }

    public BitArray getBlackRow(int i, BitArray bitarray)
    {
        int j = 1;
        LuminanceSource luminancesource = getLuminanceSource();
        int k = luminancesource.getWidth();
        byte abyte0[];
        int ai[];
        if (bitarray == null || bitarray.getSize() < k)
        {
            bitarray = new BitArray(k);
        } else
        {
            bitarray.clear();
        }
        a(k);
        abyte0 = luminancesource.getRow(i, b);
        ai = c;
        for (int l = 0; l < k; l++)
        {
            int i2 = (0xff & abyte0[l]) >> 3;
            ai[i2] = 1 + ai[i2];
        }

        int i1 = a(ai);
        int j1 = 0xff & abyte0[0];
        int l1;
        for (int k1 = 0xff & abyte0[j]; j < k - 1; k1 = l1)
        {
            l1 = 0xff & abyte0[j + 1];
            if ((k1 << 2) - j1 - l1 >> 1 < i1)
            {
                bitarray.set(j);
            }
            j++;
            j1 = k1;
        }

        return bitarray;
    }

}
