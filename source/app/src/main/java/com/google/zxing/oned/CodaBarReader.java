// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing.oned;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.BitArray;
import java.util.Arrays;
import java.util.Map;

// Referenced classes of package com.google.zxing.oned:
//            OneDReader

public final class CodaBarReader extends OneDReader
{

    static final char a[] = "0123456789-$:/.+ABCD".toCharArray();
    static final int b[] = {
        3, 6, 9, 96, 18, 66, 33, 36, 48, 72, 
        12, 24, 69, 81, 84, 21, 26, 41, 11, 14
    };
    private static final char c[] = {
        'A', 'B', 'C', 'D'
    };
    private final StringBuilder d = new StringBuilder(20);
    private int e[];
    private int f;

    public CodaBarReader()
    {
        e = new int[80];
        f = 0;
    }

    private void a(int i)
    {
        e[f] = i;
        f = 1 + f;
        if (f >= e.length)
        {
            int ai[] = new int[f << 1];
            System.arraycopy(e, 0, ai, 0, f);
            e = ai;
        }
    }

    static boolean a(char ac[], char c1)
    {
        boolean flag = false;
        if (ac == null) goto _L2; else goto _L1
_L1:
        int i;
        int j;
        i = ac.length;
        j = 0;
_L7:
        flag = false;
        if (j >= i) goto _L2; else goto _L3
_L3:
        if (ac[j] != c1) goto _L5; else goto _L4
_L4:
        flag = true;
_L2:
        return flag;
_L5:
        j++;
        if (true) goto _L7; else goto _L6
_L6:
    }

    private int b(int i)
    {
        int j;
        int k;
        j = 0x7fffffff;
        k = i + 7;
        if (k < f) goto _L2; else goto _L1
_L1:
        int k3 = -1;
_L4:
        return k3;
_L2:
        int ai[] = e;
        int l = i;
        int i1 = j;
        int j1 = 0;
        while (l < k) 
        {
            int k4 = ai[l];
            if (k4 < i1)
            {
                i1 = k4;
            }
            int k1;
            int l1;
            int i2;
            int j2;
            int k2;
            int l2;
            int i3;
            int j3;
            int l3;
            int i4;
            int j4;
            if (k4 <= j1)
            {
                k4 = j1;
            }
            l += 2;
            j1 = k4;
        }
        k1 = (i1 + j1) / 2;
        l1 = i + 1;
        i2 = 0;
        j2 = l1;
        while (j2 < k) 
        {
            j4 = ai[j2];
            if (j4 < j)
            {
                j = j4;
            }
            if (j4 <= i2)
            {
                j4 = i2;
            }
            j2 += 2;
            i2 = j4;
        }
        k2 = (j + i2) / 2;
        l2 = 128;
        i3 = 0;
        j3 = 0;
        while (i3 < 7) 
        {
            if ((i3 & 1) == 0)
            {
                l3 = k1;
            } else
            {
                l3 = k2;
            }
            l2 >>= 1;
            if (ai[i + i3] > l3)
            {
                i4 = j3 | l2;
            } else
            {
                i4 = j3;
            }
            i3++;
            j3 = i4;
        }
        k3 = 0;
label0:
        do
        {
label1:
            {
                b;
                if (k3 >= 20)
                {
                    break label1;
                }
                if (b[k3] == j3)
                {
                    break label0;
                }
                k3++;
            }
        } while (true);
        if (true) goto _L4; else goto _L3
_L3:
        return -1;
    }

    public final Result decodeRow(int i, BitArray bitarray, Map map)
    {
        int j1;
        Arrays.fill(e, 0);
        f = 0;
        int j = bitarray.getNextUnset(0);
        int k = bitarray.getSize();
        if (j >= k)
        {
            throw NotFoundException.getNotFoundInstance();
        }
        boolean flag = true;
        int l = 0;
        int i1 = j;
        while (i1 < k) 
        {
            boolean flag2;
            if (flag ^ bitarray.get(i1))
            {
                l++;
                flag2 = flag;
            } else
            {
                a(l);
                boolean flag1;
                if (!flag)
                {
                    flag1 = true;
                } else
                {
                    flag1 = false;
                }
                flag2 = flag1;
                l = 1;
            }
            i1++;
            flag = flag2;
        }
        a(l);
        j1 = 1;
_L3:
        if (j1 >= f) goto _L2; else goto _L1
_L1:
        int j2;
        int k1 = b(j1);
        if (k1 == -1 || !a(c, a[k1]))
        {
            continue; /* Loop/switch isn't completed */
        }
        int l1 = 0;
        for (int i2 = j1; i2 < j1 + 7; i2++)
        {
            l1 += e[i2];
        }

        if (j1 != 1 && e[j1 - 1] < l1 / 2)
        {
            continue; /* Loop/switch isn't completed */
        }
        d.setLength(0);
        j2 = j1;
_L4:
        int k2;
        k2 = b(j2);
        if (k2 == -1)
        {
            throw NotFoundException.getNotFoundInstance();
        }
        break MISSING_BLOCK_LABEL_257;
        j1 += 2;
          goto _L3
_L2:
        throw NotFoundException.getNotFoundInstance();
        d.append((char)k2);
        j2 += 8;
        if (d.length() > 1 && a(c, a[k2]) || j2 >= f)
        {
            int l2 = e[j2 - 1];
            int i3 = 0;
            for (int j3 = -8; j3 < -1; j3++)
            {
                i3 += e[j2 + j3];
            }

            if (j2 < f && l2 < i3 / 2)
            {
                throw NotFoundException.getNotFoundInstance();
            }
            int ai[] = {
                0, 0, 0, 0
            };
            int ai1[] = {
                0, 0, 0, 0
            };
            int k3 = -1 + d.length();
            int l3 = 0;
            int i4 = j1;
            do
            {
                int j4 = b[d.charAt(l3)];
                for (int k4 = 6; k4 >= 0; k4--)
                {
                    int j8 = (k4 & 1) + ((j4 & 1) << 1);
                    ai[j8] = ai[j8] + e[i4 + k4];
                    ai1[j8] = 1 + ai1[j8];
                    j4 >>= 1;
                }

                if (l3 >= k3)
                {
                    break;
                }
                i4 += 8;
                l3++;
            } while (true);
            int ai2[] = new int[4];
            int ai3[] = new int[4];
            for (int l4 = 0; l4 < 2; l4++)
            {
                ai3[l4] = 0;
                ai3[l4 + 2] = (ai[l4] << 8) / ai1[l4] + (ai[l4 + 2] << 8) / ai1[l4 + 2] >> 1;
                ai2[l4] = ai3[l4 + 2];
                ai2[l4 + 2] = (384 + (ai[l4 + 2] << 9)) / ai1[l4 + 2];
            }

            int i5 = 0;
            int j5 = j1;
            do
            {
                int k5 = b[d.charAt(i5)];
                for (int l5 = 6; l5 >= 0; l5--)
                {
                    int l7 = (l5 & 1) + ((k5 & 1) << 1);
                    int i8 = e[j5 + l5] << 8;
                    if (i8 < ai3[l7] || i8 > ai2[l7])
                    {
                        throw NotFoundException.getNotFoundInstance();
                    }
                    k5 >>= 1;
                }

                if (i5 >= k3)
                {
                    break;
                }
                j5 += 8;
                i5++;
            } while (true);
            for (int i6 = 0; i6 < d.length(); i6++)
            {
                d.setCharAt(i6, a[d.charAt(i6)]);
            }

            char c1 = d.charAt(0);
            if (!a(c, c1))
            {
                throw NotFoundException.getNotFoundInstance();
            }
            char c2 = d.charAt(-1 + d.length());
            if (!a(c, c2))
            {
                throw NotFoundException.getNotFoundInstance();
            }
            if (d.length() <= 3)
            {
                throw NotFoundException.getNotFoundInstance();
            }
            d.deleteCharAt(-1 + d.length());
            d.deleteCharAt(0);
            int j6 = 0;
            for (int k6 = 0; k6 < j1;)
            {
                int k7 = j6 + e[k6];
                k6++;
                j6 = k7;
            }

            float f1 = j6;
            int l6 = j6;
            int i7 = j1;
            int j7 = l6;
            for (; i7 < j2 - 1; i7++)
            {
                j7 += e[i7];
            }

            float f2 = j7;
            String s = d.toString();
            ResultPoint aresultpoint[] = new ResultPoint[2];
            aresultpoint[0] = new ResultPoint(f1, i);
            aresultpoint[1] = new ResultPoint(f2, i);
            return new Result(s, null, aresultpoint, BarcodeFormat.CODABAR);
        }
          goto _L4
    }

}
