// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing.datamatrix.decoder;

import com.google.zxing.FormatException;
import com.google.zxing.common.BitMatrix;

// Referenced classes of package com.google.zxing.datamatrix.decoder:
//            Version

class a
{

    private final BitMatrix a;
    private final BitMatrix b;
    private final Version c;

    a(BitMatrix bitmatrix)
    {
        int i = bitmatrix.getHeight();
        if (i < 8 || i > 144 || (i & 1) != 0)
        {
            throw FormatException.getFormatInstance();
        } else
        {
            c = Version.getVersionForDimensions(bitmatrix.getHeight(), bitmatrix.getWidth());
            a = a(bitmatrix);
            b = new BitMatrix(a.getWidth(), a.getHeight());
            return;
        }
    }

    private BitMatrix a(BitMatrix bitmatrix)
    {
        int i = c.getSymbolSizeRows();
        int j = c.getSymbolSizeColumns();
        if (bitmatrix.getHeight() != i)
        {
            throw new IllegalArgumentException("Dimension of bitMarix must match the version size");
        }
        int k = c.getDataRegionSizeRows();
        int l = c.getDataRegionSizeColumns();
        int i1 = i / k;
        int j1 = j / l;
        int k1 = i1 * k;
        BitMatrix bitmatrix1 = new BitMatrix(j1 * l, k1);
        for (int l1 = 0; l1 < i1; l1++)
        {
            int i2 = l1 * k;
            for (int j2 = 0; j2 < j1; j2++)
            {
                int k2 = j2 * l;
                for (int l2 = 0; l2 < k; l2++)
                {
                    int i3 = l2 + (1 + l1 * (k + 2));
                    int j3 = i2 + l2;
                    for (int k3 = 0; k3 < l; k3++)
                    {
                        if (bitmatrix.get(k3 + (1 + j2 * (l + 2)), i3))
                        {
                            bitmatrix1.set(k2 + k3, j3);
                        }
                    }

                }

            }

        }

        return bitmatrix1;
    }

    private boolean a(int i, int j, int k, int l)
    {
        int i1;
        int j1;
        if (i < 0)
        {
            j1 = i + k;
            i1 = j + (4 - (7 & k + 4));
        } else
        {
            i1 = j;
            j1 = i;
        }
        if (i1 < 0)
        {
            i1 += l;
            j1 += 4 - (7 & l + 4);
        }
        b.set(i1, j1);
        return a.get(i1, j1);
    }

    private int b(int i, int j, int k, int l)
    {
        boolean flag = a(i - 2, j - 2, k, l);
        int i1 = 0;
        if (flag)
        {
            i1 = 1;
        }
        int j1 = i1 << 1;
        if (a(i - 2, j - 1, k, l))
        {
            j1 |= 1;
        }
        int k1 = j1 << 1;
        if (a(i - 1, j - 2, k, l))
        {
            k1 |= 1;
        }
        int l1 = k1 << 1;
        if (a(i - 1, j - 1, k, l))
        {
            l1 |= 1;
        }
        int i2 = l1 << 1;
        if (a(i - 1, j, k, l))
        {
            i2 |= 1;
        }
        int j2 = i2 << 1;
        if (a(i, j - 2, k, l))
        {
            j2 |= 1;
        }
        int k2 = j2 << 1;
        if (a(i, j - 1, k, l))
        {
            k2 |= 1;
        }
        int l2 = k2 << 1;
        if (a(i, j, k, l))
        {
            l2 |= 1;
        }
        return l2;
    }

    final Version a()
    {
        return c;
    }

    final byte[] b()
    {
        byte abyte0[];
        int i;
        int j;
        int k;
        int l;
        int i1;
        boolean flag;
        boolean flag1;
        boolean flag2;
        boolean flag3;
        abyte0 = new byte[c.getTotalCodewords()];
        i = 4;
        j = a.getHeight();
        k = a.getWidth();
        l = 0;
        i1 = 0;
        flag = false;
        flag1 = false;
        flag2 = false;
        flag3 = false;
_L5:
        int i4;
        boolean flag5;
        boolean flag6;
        boolean flag7;
        int l4;
        boolean flag8;
        if (i == j && l == 0 && !flag)
        {
            i4 = i1 + 1;
            boolean flag17 = a(j - 1, 0, j, k);
            int l11 = 0;
            if (flag17)
            {
                l11 = 1;
            }
            int i12 = l11 << 1;
            if (a(j - 1, 1, j, k))
            {
                i12 |= 1;
            }
            int j12 = i12 << 1;
            if (a(j - 1, 2, j, k))
            {
                j12 |= 1;
            }
            int k12 = j12 << 1;
            if (a(0, k - 2, j, k))
            {
                k12 |= 1;
            }
            int l12 = k12 << 1;
            if (a(0, k - 1, j, k))
            {
                l12 |= 1;
            }
            int i13 = l12 << 1;
            if (a(1, k - 1, j, k))
            {
                i13 |= 1;
            }
            int j13 = i13 << 1;
            if (a(2, k - 1, j, k))
            {
                j13 |= 1;
            }
            int k13 = j13 << 1;
            if (a(3, k - 1, j, k))
            {
                k13 |= 1;
            }
            abyte0[i1] = (byte)k13;
            i -= 2;
            l4 = l + 2;
            boolean flag18 = flag3;
            flag5 = flag2;
            flag6 = flag1;
            flag7 = true;
            flag8 = flag18;
        } else
        if (i == j - 2 && l == 0 && (k & 3) != 0 && !flag1)
        {
            i4 = i1 + 1;
            boolean flag14 = a(j - 3, 0, j, k);
            int k9 = 0;
            if (flag14)
            {
                k9 = 1;
            }
            int l9 = k9 << 1;
            if (a(j - 2, 0, j, k))
            {
                l9 |= 1;
            }
            int i10 = l9 << 1;
            if (a(j - 1, 0, j, k))
            {
                i10 |= 1;
            }
            int j10 = i10 << 1;
            if (a(0, k - 4, j, k))
            {
                j10 |= 1;
            }
            int k10 = j10 << 1;
            if (a(0, k - 3, j, k))
            {
                k10 |= 1;
            }
            int l10 = k10 << 1;
            if (a(0, k - 2, j, k))
            {
                l10 |= 1;
            }
            int i11 = l10 << 1;
            if (a(0, k - 1, j, k))
            {
                i11 |= 1;
            }
            int j11 = i11 << 1;
            if (a(1, k - 1, j, k))
            {
                j11 |= 1;
            }
            abyte0[i1] = (byte)j11;
            i -= 2;
            int k11 = l + 2;
            boolean flag15 = flag3;
            flag5 = flag2;
            flag6 = true;
            flag8 = flag15;
            boolean flag16 = flag;
            l4 = k11;
            flag7 = flag16;
        } else
        if (i == j + 4 && l == 2 && (k & 7) == 0 && !flag2)
        {
            i4 = i1 + 1;
            boolean flag11 = a(j - 1, 0, j, k);
            int j7 = 0;
            if (flag11)
            {
                j7 = 1;
            }
            int k7 = j7 << 1;
            if (a(j - 1, k - 1, j, k))
            {
                k7 |= 1;
            }
            int l7 = k7 << 1;
            if (a(0, k - 3, j, k))
            {
                l7 |= 1;
            }
            int i8 = l7 << 1;
            if (a(0, k - 2, j, k))
            {
                i8 |= 1;
            }
            int j8 = i8 << 1;
            if (a(0, k - 1, j, k))
            {
                j8 |= 1;
            }
            int k8 = j8 << 1;
            if (a(1, k - 3, j, k))
            {
                k8 |= 1;
            }
            int l8 = k8 << 1;
            if (a(1, k - 2, j, k))
            {
                l8 |= 1;
            }
            int i9 = l8 << 1;
            if (a(1, k - 1, j, k))
            {
                i9 |= 1;
            }
            abyte0[i1] = (byte)i9;
            i -= 2;
            int j9 = l + 2;
            boolean flag12 = flag3;
            flag5 = true;
            flag8 = flag12;
            boolean flag13 = flag1;
            flag7 = flag;
            l4 = j9;
            flag6 = flag13;
        } else
        {
            if (i != j - 2 || l != 0 || (k & 7) != 4 || flag3)
            {
                break MISSING_BLOCK_LABEL_1467;
            }
            i4 = i1 + 1;
            boolean flag9 = a(j - 3, 0, j, k);
            int i5 = 0;
            if (flag9)
            {
                i5 = 1;
            }
            int j5 = i5 << 1;
            if (a(j - 2, 0, j, k))
            {
                j5 |= 1;
            }
            int k5 = j5 << 1;
            if (a(j - 1, 0, j, k))
            {
                k5 |= 1;
            }
            int l5 = k5 << 1;
            if (a(0, k - 2, j, k))
            {
                l5 |= 1;
            }
            int i6 = l5 << 1;
            if (a(0, k - 1, j, k))
            {
                i6 |= 1;
            }
            int j6 = i6 << 1;
            if (a(1, k - 1, j, k))
            {
                j6 |= 1;
            }
            int k6 = j6 << 1;
            if (a(2, k - 1, j, k))
            {
                k6 |= 1;
            }
            int l6 = k6 << 1;
            if (a(3, k - 1, j, k))
            {
                l6 |= 1;
            }
            abyte0[i1] = (byte)l6;
            i -= 2;
            int i7 = l + 2;
            flag8 = true;
            boolean flag10 = flag2;
            flag6 = flag1;
            flag7 = flag;
            l4 = i7;
            flag5 = flag10;
        }
          goto _L1
_L3:
        int j1;
        int l1;
        int j2;
        l1 = k2;
        j1 = j2;
_L7:
        int i2;
        int l2;
        int i3;
        int j3;
        int k3;
        int l3;
        int j4;
        int k4;
        boolean flag4;
        if (i2 < j && l1 >= 0 && !b.get(l1, i2))
        {
            j2 = j1 + 1;
            abyte0[j1] = (byte)b(i2, l1, j, k);
        } else
        {
            j2 = j1;
        }
        i2 -= 2;
        k2 = l1 + 2;
        if (i2 >= 0 && k2 < k) goto _L3; else goto _L2
_L2:
        l2 = i2 + 1;
        i3 = k2 + 3;
        j3 = l2;
        k3 = i3;
        l3 = j2;
_L6:
        if (j3 >= 0 && k3 < k && !b.get(k3, j3))
        {
            i4 = l3 + 1;
            abyte0[l3] = (byte)b(j3, k3, j, k);
        } else
        {
            i4 = l3;
        }
        j3 += 2;
        j4 = k3 - 2;
        if (j3 < j && j4 >= 0)
        {
            break; /* Loop/switch isn't completed */
        }
        i = j3 + 3;
        k4 = j4 + 1;
        flag4 = flag3;
        flag5 = flag2;
        flag6 = flag1;
        flag7 = flag;
        l4 = k4;
        flag8 = flag4;
_L1:
        int k2;
        if (i >= j && l4 >= k)
        {
            if (i4 != c.getTotalCodewords())
            {
                throw FormatException.getFormatInstance();
            } else
            {
                return abyte0;
            }
        }
        l = l4;
        i1 = i4;
        flag = flag7;
        flag1 = flag6;
        flag2 = flag5;
        flag3 = flag8;
        if (true) goto _L5; else goto _L4
_L4:
        k3 = j4;
        l3 = i4;
          goto _L6
        j1 = i1;
        int k1 = i;
        l1 = l;
        i2 = k1;
          goto _L7
    }
}
