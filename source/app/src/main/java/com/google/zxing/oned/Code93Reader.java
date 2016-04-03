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

public final class Code93Reader extends OneDReader
{

    private static final char a[] = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. $/+%abcd*".toCharArray();
    private static final int b[];
    private static final int c;
    private final StringBuilder d = new StringBuilder(20);
    private final int e[] = new int[6];

    public Code93Reader()
    {
    }

    private static int a(int ai[])
    {
        int i = ai.length;
        int j = ai.length;
        int k = 0;
        int l;
        int l2;
        for (l = 0; k < j; l = l2)
        {
            l2 = l + ai[k];
            k++;
        }

        int i1 = 0;
        int j1 = 0;
        do
        {
label0:
            {
                if (i1 < i)
                {
                    int k1 = (9 * (ai[i1] << 8)) / l;
                    int l1 = k1 >> 8;
                    int i2;
                    int j2;
                    int k2;
                    if ((k1 & 0xff) > 127)
                    {
                        i2 = l1 + 1;
                    } else
                    {
                        i2 = l1;
                    }
                    if (i2 > 0 && i2 <= 4)
                    {
                        break label0;
                    }
                    j1 = -1;
                }
                return j1;
            }
            if ((i1 & 1) == 0)
            {
                for (j2 = 0; j2 < i2;)
                {
                    k2 = 1 | j1 << 1;
                    j2++;
                    j1 = k2;
                }

            } else
            {
                j1 <<= i2;
            }
            i1++;
        } while (true);
    }

    private static void a(CharSequence charsequence, int i, int j)
    {
        int k = i - 1;
        int l = 1;
        int i1 = k;
        int j1;
        int k1;
        for (j1 = 0; i1 >= 0; j1 = k1)
        {
            k1 = j1 + l * "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. $/+%abcd*".indexOf(charsequence.charAt(i1));
            int l1 = l + 1;
            if (l1 > j)
            {
                l1 = 1;
            }
            i1--;
            l = l1;
        }

        if (charsequence.charAt(i) != a[j1 % 47])
        {
            throw ChecksumException.getChecksumInstance();
        } else
        {
            return;
        }
    }

    public final Result decodeRow(int i, BitArray bitarray, Map map)
    {
        int j;
        int k;
        int ai[];
        boolean flag;
        int l;
        int i1;
        j = bitarray.getSize();
        k = bitarray.getNextSet(0);
        Arrays.fill(e, 0);
        ai = e;
        flag = false;
        l = 0;
        i1 = k;
_L5:
        if (i1 >= j) goto _L2; else goto _L1
_L1:
        if (!(flag ^ bitarray.get(i1))) goto _L4; else goto _L3
_L3:
        ai[l] = 1 + ai[l];
_L10:
        i1++;
          goto _L5
_L4:
        if (l != 5) goto _L7; else goto _L6
_L6:
        if (a(ai) != c) goto _L9; else goto _L8
_L8:
        int ai1[];
        int j1;
        int k1;
        int ai2[];
        StringBuilder stringbuilder;
        ai1 = (new int[] {
            k, i1
        });
        j1 = bitarray.getNextSet(ai1[1]);
        k1 = bitarray.getSize();
        ai2 = e;
        Arrays.fill(ai2, 0);
        stringbuilder = d;
        stringbuilder.setLength(0);
_L13:
        int l1;
        char c1;
        l1 = j1;
        recordPattern(bitarray, l1, ai2);
        int i2 = a(ai2);
        if (i2 < 0)
        {
            throw NotFoundException.getNotFoundInstance();
        }
        int l2;
label0:
        {
            int j2 = 0;
            do
            {
                b;
                if (j2 >= 48)
                {
                    break;
                }
                if (b[j2] == i2)
                {
                    c1 = a[j2];
                    stringbuilder.append(c1);
                    int k2 = 0;
                    l2 = l1;
                    for (; k2 < 6; k2++)
                    {
                        l2 += ai2[k2];
                    }

                    break label0;
                }
                j2++;
            } while (true);
            throw NotFoundException.getNotFoundInstance();
        }
        j1 = bitarray.getNextSet(l2);
        continue; /* Loop/switch isn't completed */
_L9:
        k += ai[0] + ai[1];
        System.arraycopy(ai, 2, ai, 0, 4);
        ai[4] = 0;
        ai[5] = 0;
        l--;
_L11:
        ai[l] = 1;
        if (!flag)
        {
            flag = true;
        } else
        {
            flag = false;
        }
          goto _L10
_L7:
        l++;
          goto _L11
_L2:
        throw NotFoundException.getNotFoundInstance();
        if (c1 != '*') goto _L13; else goto _L12
_L12:
        int j3;
        StringBuilder stringbuilder1;
        int k3;
        stringbuilder.deleteCharAt(-1 + stringbuilder.length());
        if (j1 == k1 || !bitarray.get(j1))
        {
            throw NotFoundException.getNotFoundInstance();
        }
        if (stringbuilder.length() < 2)
        {
            throw NotFoundException.getNotFoundInstance();
        }
        int i3 = stringbuilder.length();
        a(stringbuilder, i3 - 2, 20);
        a(stringbuilder, i3 - 1, 15);
        stringbuilder.setLength(-2 + stringbuilder.length());
        j3 = stringbuilder.length();
        stringbuilder1 = new StringBuilder(j3);
        k3 = 0;
_L19:
        char c2;
        char c3;
        char c4;
        if (k3 >= j3)
        {
            break MISSING_BLOCK_LABEL_741;
        }
        c2 = stringbuilder.charAt(k3);
        if (c2 < 'a' || c2 > 'd')
        {
            break MISSING_BLOCK_LABEL_726;
        }
        if (k3 >= j3 - 1)
        {
            throw FormatException.getFormatInstance();
        }
        c3 = stringbuilder.charAt(k3 + 1);
        c4 = '\0';
        c2;
        JVM INSTR tableswitch 97 100: default 548
    //                   97 600
    //                   98 629
    //                   99 683
    //                   100 571;
           goto _L14 _L15 _L16 _L17 _L18
_L14:
        int l3;
        stringbuilder1.append(c4);
        l3 = k3 + 1;
_L20:
        k3 = l3 + 1;
          goto _L19
_L18:
        if (c3 >= 'A' && c3 <= 'Z')
        {
            c4 = (char)(c3 + 32);
        } else
        {
            throw FormatException.getFormatInstance();
        }
          goto _L14
_L15:
        if (c3 >= 'A' && c3 <= 'Z')
        {
            c4 = (char)(c3 - 64);
        } else
        {
            throw FormatException.getFormatInstance();
        }
          goto _L14
_L16:
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
          goto _L14
_L17:
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
          goto _L14
        stringbuilder1.append(c2);
        l3 = k3;
          goto _L20
        String s = stringbuilder1.toString();
        float f = (float)(ai1[1] + ai1[0]) / 2.0F;
        float f1 = (float)(j1 + l1) / 2.0F;
        ResultPoint aresultpoint[] = new ResultPoint[2];
        aresultpoint[0] = new ResultPoint(f, i);
        aresultpoint[1] = new ResultPoint(f1, i);
        return new Result(s, null, aresultpoint, BarcodeFormat.CODE_93);
          goto _L10
    }

    static 
    {
        int ai[] = {
            276, 328, 324, 322, 296, 292, 290, 336, 274, 266, 
            424, 420, 418, 404, 402, 394, 360, 356, 354, 308, 
            282, 344, 332, 326, 300, 278, 436, 434, 428, 422, 
            406, 410, 364, 358, 310, 314, 302, 468, 466, 458, 
            366, 374, 430, 294, 474, 470, 306, 350
        };
        b = ai;
        c = ai[47];
    }
}
