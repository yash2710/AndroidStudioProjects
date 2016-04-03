// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing.common;

import com.google.zxing.Binarizer;
import com.google.zxing.LuminanceSource;
import java.lang.reflect.Array;

// Referenced classes of package com.google.zxing.common:
//            GlobalHistogramBinarizer, BitMatrix

public final class HybridBinarizer extends GlobalHistogramBinarizer
{

    private BitMatrix a;

    public HybridBinarizer(LuminanceSource luminancesource)
    {
        super(luminancesource);
    }

    private static int a(int i, int j, int k)
    {
        if (i < 2)
        {
            k = 2;
        } else
        if (i <= k)
        {
            return i;
        }
        return k;
    }

    public final Binarizer createBinarizer(LuminanceSource luminancesource)
    {
        return new HybridBinarizer(luminancesource);
    }

    public final BitMatrix getBlackMatrix()
    {
        if (a != null)
        {
            return a;
        }
        LuminanceSource luminancesource = getLuminanceSource();
        int i = luminancesource.getWidth();
        int j = luminancesource.getHeight();
        if (i < 40 || j < 40)
        {
            break MISSING_BLOCK_LABEL_772;
        }
        byte abyte0[] = luminancesource.getMatrix();
        int k = i >> 3;
        int l;
        int i1;
        int j1;
        int ai[];
        int ai1[][];
        int k1;
        BitMatrix bitmatrix;
        int l1;
        int i2;
        int j2;
        int k2;
        int l2;
        int i3;
        int j3;
        int k3;
        int l3;
        int i4;
        int j4;
        int k4;
        int l4;
        int i5;
        int j5;
        int k5;
        int ai2[];
        int l5;
        int i6;
        int j6;
        int k6;
        int l6;
        int i7;
        int j7;
        int k7;
        int l7;
        int i8;
        int j8;
        int k8;
        int l8;
        int i9;
        int j9;
        int k9;
        int l9;
        int i10;
        int j10;
        int k10;
        int l10;
        int i11;
        int j11;
        if ((i & 7) != 0)
        {
            l = k + 1;
        } else
        {
            l = k;
        }
        i1 = j >> 3;
        if ((j & 7) != 0)
        {
            j1 = i1 + 1;
        } else
        {
            j1 = i1;
        }
        ai = (new int[] {
            j1, l
        });
        ai1 = (int[][])Array.newInstance(Integer.TYPE, ai);
        k1 = 0;
        if (k1 >= j1) goto _L2; else goto _L1
_L1:
        l5 = k1 << 3;
        i6 = j - 8;
        if (l5 <= i6)
        {
            i6 = l5;
        }
        j6 = 0;
        if (j6 >= l)
        {
            continue; /* Loop/switch isn't completed */
        }
        k6 = j6 << 3;
        l6 = i - 8;
        if (k6 <= l6)
        {
            l6 = k6;
        }
        i7 = 0;
        j7 = 255;
        k7 = 0;
        l7 = 0;
        i8 = l6 + i6 * i;
        while (l7 < 8) 
        {
            l8 = 0;
            while (l8 < 8) 
            {
                l10 = 0xff & abyte0[i8 + l8];
                i11 = i7 + l10;
                if (l10 < j7)
                {
                    j11 = l10;
                } else
                {
                    j11 = j7;
                }
                if (l10 <= k7)
                {
                    l10 = k7;
                }
                l8++;
                j7 = j11;
                k7 = l10;
                i7 = i11;
            }
            if (k7 - j7 > 24)
            {
                i10 = l7 + 1;
                i9 = i8 + i;
                j9 = i10;
                for (k9 = i7; j9 < 8; k9 = j10)
                {
                    j10 = k9;
                    for (k10 = 0; k10 < 8; k10++)
                    {
                        j10 += 0xff & abyte0[i9 + k10];
                    }

                    j9++;
                    i9 += i;
                }

            } else
            {
                i9 = i8;
                j9 = l7;
                k9 = i7;
            }
            l9 = j9 + 1;
            i8 = i9 + i;
            i7 = k9;
            l7 = l9;
        }
        j8 = i7 >> 6;
        if (k7 - j7 <= 24)
        {
            k8 = j7 >> 1;
            if (k1 <= 0 || j6 <= 0)
            {
                break MISSING_BLOCK_LABEL_797;
            }
            j8 = ai1[k1 - 1][j6] + 2 * ai1[k1][j6 - 1] + ai1[k1 - 1][j6 - 1] >> 2;
            if (j7 >= j8)
            {
                break MISSING_BLOCK_LABEL_797;
            }
        }
        ai1[k1][j6] = j8;
        j6++;
        if (true)
        {
            break MISSING_BLOCK_LABEL_141;
        }
        k1++;
        break MISSING_BLOCK_LABEL_112;
_L2:
        bitmatrix = new BitMatrix(i, j);
        l1 = 0;
        while (l1 < j1) 
        {
            i2 = l1 << 3;
            j2 = j - 8;
            if (i2 > j2)
            {
                k2 = j2;
            } else
            {
                k2 = i2;
            }
            l2 = 0;
            while (l2 < l) 
            {
                i3 = l2 << 3;
                j3 = i - 8;
                if (i3 <= j3)
                {
                    j3 = i3;
                }
                k3 = a(l2, 2, l - 3);
                l3 = a(l1, 2, j1 - 3);
                i4 = 0;
                for (j4 = -2; j4 <= 2; j4++)
                {
                    ai2 = ai1[l3 + j4];
                    i4 += ai2[k3 - 2] + ai2[k3 - 1] + ai2[k3] + ai2[k3 + 1] + ai2[k3 + 2];
                }

                k4 = i4 / 25;
                l4 = j3 + k2 * i;
                i5 = 0;
                for (j5 = l4; i5 < 8; j5 += i)
                {
                    for (k5 = 0; k5 < 8; k5++)
                    {
                        if ((0xff & abyte0[j5 + k5]) <= k4)
                        {
                            bitmatrix.set(j3 + k5, k2 + i5);
                        }
                    }

                    i5++;
                }

                l2++;
            }
            l1++;
        }
        a = bitmatrix;
        return a;
        a = super.getBlackMatrix();
        if (true)
        {
            break MISSING_BLOCK_LABEL_767;
        }
        j8 = k8;
        break MISSING_BLOCK_LABEL_469;
    }
}
