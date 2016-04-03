// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing.pdf417.decoder;

import com.google.zxing.pdf417.PDF417Common;
import java.lang.reflect.Array;

final class k
{

    private static final float a[][];

    static int a(int ai[])
    {
        float f = PDF417Common.getBitCountSum(ai);
        int ai1[] = new int[8];
        int i = 0;
        int j = 0;
        for (int l = 0; l < 17; l++)
        {
            float f4 = f / 34F + (f * (float)l) / 17F;
            if ((float)(i + ai[j]) <= f4)
            {
                i += ai[j];
                j++;
            }
            ai1[j] = 1 + ai1[j];
        }

        long l1 = 0L;
        for (int i1 = 0; i1 < ai1.length; i1++)
        {
            int l2 = 0;
            while (l2 < ai1[i1]) 
            {
                long l3 = l1 << 1;
                int i3;
                long l4;
                if (i1 % 2 == 0)
                {
                    i3 = 1;
                } else
                {
                    i3 = 0;
                }
                l4 = l3 | (long)i3;
                l2++;
                l1 = l4;
            }
        }

        int j1 = (int)l1;
        if (PDF417Common.getCodeword(j1) == -1)
        {
            j1 = -1;
        }
        if (j1 == -1)
        {
            int k1 = PDF417Common.getBitCountSum(ai);
            float af[] = new float[8];
            for (int i2 = 0; i2 < 8; i2++)
            {
                af[i2] = (float)ai[i2] / (float)k1;
            }

            float f1 = 3.402823E+38F;
            j1 = -1;
            int j2 = 0;
            while (j2 < a.length) 
            {
                float f2 = 0.0F;
                for (int k2 = 0; k2 < 8; k2++)
                {
                    float f3 = a[j2][k2] - af[k2];
                    f2 += f3 * f3;
                }

                if (f2 < f1)
                {
                    j1 = PDF417Common.SYMBOL_TABLE[j2];
                } else
                {
                    f2 = f1;
                }
                j2++;
                f1 = f2;
            }
        }
        return j1;
    }

    static 
    {
        int ai[] = {
            PDF417Common.SYMBOL_TABLE.length, 8
        };
        a = (float[][])Array.newInstance(Float.TYPE, ai);
        for (int i = 0; i < PDF417Common.SYMBOL_TABLE.length; i++)
        {
            int j = PDF417Common.SYMBOL_TABLE[i];
            int l = j & 1;
            int i1 = 0;
            int j1 = l;
            for (; i1 < 8; i1++)
            {
                float f = 0.0F;
                for (; (j & 1) == j1; j >>= 1)
                {
                    f++;
                }

                j1 = j & 1;
                a[i][-1 + (8 - i1)] = f / 17F;
            }

        }

    }
}
