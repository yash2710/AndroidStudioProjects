// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing.oned;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.ChecksumException;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.BitArray;
import java.util.Arrays;
import java.util.Map;

// Referenced classes of package com.google.zxing.oned:
//            OneDReader

public final class Code39Reader extends OneDReader
{

    static final int a[];
    private static final char b[] = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. *$/+%".toCharArray();
    private static final int c;
    private final boolean d;
    private final boolean e;
    private final StringBuilder f;
    private final int g[];

    public Code39Reader()
    {
        this(false);
    }

    public Code39Reader(boolean flag)
    {
        this(flag, false);
    }

    public Code39Reader(boolean flag, boolean flag1)
    {
        d = flag;
        e = flag1;
        f = new StringBuilder(20);
        g = new int[9];
    }

    private static int a(int ai[])
    {
        int i = ai.length;
        int j = 0;
        do
        {
            int k = 0x7fffffff;
            int l = ai.length;
            for (int i1 = 0; i1 < l; i1++)
            {
                int j3 = ai[i1];
                if (j3 < k && j3 > j)
                {
                    k = j3;
                }
            }

            int j1 = 0;
            int k1 = 0;
            int l1 = 0;
            int i2 = 0;
            for (; j1 < i; j1++)
            {
                int i3 = ai[j1];
                if (i3 > k)
                {
                    k1 |= 1 << i - 1 - j1;
                    i2++;
                    l1 += i3;
                }
            }

            if (i2 == 3)
            {
                int j2 = i2;
                int k2 = 0;
                do
                {
label0:
                    {
                        if (k2 < i && j2 > 0)
                        {
                            int l2 = ai[k2];
                            if (l2 <= k)
                            {
                                break label0;
                            }
                            j2--;
                            if (l2 << 1 < l1)
                            {
                                break label0;
                            }
                            k1 = -1;
                        }
                        return k1;
                    }
                    k2++;
                } while (true);
            }
            if (i2 <= 3)
            {
                return -1;
            }
            j = k;
        } while (true);
    }

    public final Result decodeRow(int i, BitArray bitarray, Map map)
    {
        int ai[];
        StringBuilder stringbuilder;
        int j;
        int k;
        int l;
        boolean flag;
        int i1;
        int j1;
        ai = g;
        Arrays.fill(ai, 0);
        stringbuilder = f;
        stringbuilder.setLength(0);
        j = bitarray.getSize();
        k = bitarray.getNextSet(0);
        l = 0;
        flag = false;
        i1 = ai.length;
        j1 = k;
_L5:
        if (j1 >= j) goto _L2; else goto _L1
_L1:
        if (!(flag ^ bitarray.get(j1))) goto _L4; else goto _L3
_L3:
        ai[l] = 1 + ai[l];
_L11:
        j1++;
          goto _L5
_L4:
        if (l != i1 - 1) goto _L7; else goto _L6
_L6:
        if (a(ai) != c || !bitarray.isRange(Math.max(0, k - (j1 - k >> 1)), k, false)) goto _L9; else goto _L8
_L8:
        int ai1[];
        int l1;
        int i2;
        ai1 = (new int[] {
            k, j1
        });
        int k1 = bitarray.getNextSet(ai1[1]);
        l1 = bitarray.getSize();
        i2 = k1;
_L25:
        char c1;
        int j3;
        recordPattern(bitarray, i2, ai);
        int j2 = a(ai);
        if (j2 < 0)
        {
            throw NotFoundException.getNotFoundInstance();
        }
        int i3;
label0:
        {
            int k2 = 0;
            do
            {
                a;
                if (k2 >= 44)
                {
                    break;
                }
                if (a[k2] == j2)
                {
                    c1 = b[k2];
                    stringbuilder.append(c1);
                    int l2 = 0;
                    i3 = i2;
                    for (; l2 < 9; l2++)
                    {
                        i3 += ai[l2];
                    }

                    break label0;
                }
                k2++;
            } while (true);
            throw NotFoundException.getNotFoundInstance();
        }
        j3 = bitarray.getNextSet(i3);
          goto _L10
_L9:
        k += ai[0] + ai[1];
        System.arraycopy(ai, 2, ai, 0, i1 - 2);
        ai[i1 - 2] = 0;
        ai[i1 - 1] = 0;
        l--;
_L12:
        ai[l] = 1;
        if (!flag)
        {
            flag = true;
        } else
        {
            flag = false;
        }
          goto _L11
_L7:
        l++;
          goto _L12
_L2:
        throw NotFoundException.getNotFoundInstance();
_L10:
        if (c1 != '*') goto _L14; else goto _L13
_L13:
        stringbuilder.setLength(-1 + stringbuilder.length());
        int k3 = 0;
        for (int l3 = 0; l3 < 9; l3++)
        {
            k3 += ai[l3];
        }

        int i4 = j3 - i2 - k3;
        if (j3 != l1 && i4 >> 1 < k3)
        {
            throw NotFoundException.getNotFoundInstance();
        }
        if (d)
        {
            int i5 = -1 + stringbuilder.length();
            int j5 = 0;
            for (int k5 = 0; k5 < i5; k5++)
            {
                j5 += "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. *$/+%".indexOf(f.charAt(k5));
            }

            if (stringbuilder.charAt(i5) != b[j5 % 43])
            {
                throw ChecksumException.getChecksumInstance();
            }
            stringbuilder.setLength(i5);
        }
        if (stringbuilder.length() == 0)
        {
            throw NotFoundException.getNotFoundInstance();
        }
        if (!e) goto _L16; else goto _L15
_L15:
        int j4;
        StringBuilder stringbuilder1;
        int k4;
        j4 = stringbuilder.length();
        stringbuilder1 = new StringBuilder(j4);
        k4 = 0;
_L22:
        char c2;
        char c3;
        char c4;
        if (k4 >= j4)
        {
            break MISSING_BLOCK_LABEL_865;
        }
        c2 = stringbuilder.charAt(k4);
        if (c2 != '+' && c2 != '$' && c2 != '%' && c2 != '/')
        {
            break MISSING_BLOCK_LABEL_850;
        }
        c3 = stringbuilder.charAt(k4 + 1);
        c4 = '\0';
        c2;
        JVM INSTR lookupswitch 4: default 672
    //                   36: 724
    //                   37: 753
    //                   43: 695
    //                   47: 807;
           goto _L17 _L18 _L19 _L20 _L21
_L17:
        int l4;
        stringbuilder1.append(c4);
        l4 = k4 + 1;
_L23:
        k4 = l4 + 1;
          goto _L22
_L20:
        if (c3 >= 'A' && c3 <= 'Z')
        {
            c4 = (char)(c3 + 32);
        } else
        {
            throw FormatException.getFormatInstance();
        }
          goto _L17
_L18:
        if (c3 >= 'A' && c3 <= 'Z')
        {
            c4 = (char)(c3 - 64);
        } else
        {
            throw FormatException.getFormatInstance();
        }
          goto _L17
_L19:
        if (c3 >= 'A' && c3 <= 'E')
        {
            c4 = (char)(c3 - 38);
        } else
        if (c3 >= 'F' && c3 <= 'W')
        {
            c4 = (char)(c3 - 11);
        } else
        {
            throw FormatException.getFormatInstance();
        }
          goto _L17
_L21:
        if (c3 >= 'A' && c3 <= 'O')
        {
            c4 = (char)(c3 - 32);
        } else
        if (c3 == 'Z')
        {
            c4 = ':';
        } else
        {
            throw FormatException.getFormatInstance();
        }
          goto _L17
        stringbuilder1.append(c2);
        l4 = k4;
          goto _L23
        String s = stringbuilder1.toString();
_L24:
        float f1 = (float)(ai1[1] + ai1[0]) / 2.0F;
        float f2 = (float)(j3 + i2) / 2.0F;
        ResultPoint aresultpoint[] = new ResultPoint[2];
        aresultpoint[0] = new ResultPoint(f1, i);
        aresultpoint[1] = new ResultPoint(f2, i);
        return new Result(s, null, aresultpoint, BarcodeFormat.CODE_39);
_L16:
        s = stringbuilder.toString();
        if (true) goto _L24; else goto _L14
_L14:
        i2 = j3;
          goto _L25
    }

    static 
    {
        int ai[] = {
            52, 289, 97, 352, 49, 304, 112, 37, 292, 100, 
            265, 73, 328, 25, 280, 88, 13, 268, 76, 28, 
            259, 67, 322, 19, 274, 82, 7, 262, 70, 22, 
            385, 193, 448, 145, 400, 208, 133, 388, 196, 148, 
            168, 162, 138, 42
        };
        a = ai;
        c = ai[39];
    }
}
