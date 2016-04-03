// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing.oned;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.DecodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.BitArray;
import java.util.Map;

// Referenced classes of package com.google.zxing.oned:
//            OneDReader

public final class ITFReader extends OneDReader
{

    static final int a[][] = {
        {
            1, 1, 3, 3, 1
        }, {
            3, 1, 1, 1, 3
        }, {
            1, 3, 1, 1, 3
        }, {
            3, 3, 1, 1, 1
        }, {
            1, 1, 3, 1, 3
        }, {
            3, 1, 3, 1, 1
        }, {
            1, 3, 3, 1, 1
        }, {
            1, 1, 1, 3, 3
        }, {
            3, 1, 1, 3, 1
        }, {
            1, 3, 1, 3, 1
        }
    };
    private static final int b[] = {
        48, 44, 24, 20, 18, 16, 14, 12, 10, 8, 
        6
    };
    private static final int d[] = {
        1, 1, 1, 1
    };
    private static final int e[] = {
        1, 1, 3
    };
    private int c;

    public ITFReader()
    {
        c = -1;
    }

    private static int a(BitArray bitarray)
    {
        int i = bitarray.getSize();
        int j = bitarray.getNextSet(0);
        if (j == i)
        {
            throw NotFoundException.getNotFoundInstance();
        } else
        {
            return j;
        }
    }

    private static int a(int ai[])
    {
        int i = 107;
        int j = -1;
        int[][] _tmp = a;
        int k = 0;
        while (k < 10) 
        {
            int l = patternMatchVariance(ai, a[k], 204);
            if (l < i)
            {
                j = k;
            } else
            {
                l = i;
            }
            k++;
            i = l;
        }
        if (j >= 0)
        {
            return j;
        } else
        {
            throw NotFoundException.getNotFoundInstance();
        }
    }

    private void a(BitArray bitarray, int i)
    {
        int j = 10 * c;
        for (int k = i - 1; j > 0 && k >= 0 && !bitarray.get(k); k--)
        {
            j--;
        }

        if (j != 0)
        {
            throw NotFoundException.getNotFoundInstance();
        } else
        {
            return;
        }
    }

    private static void a(BitArray bitarray, int i, int j, StringBuilder stringbuilder)
    {
        int ai[] = new int[10];
        int ai1[] = new int[5];
        int ai2[] = new int[5];
        int i1;
        for (int k = i; k < j; k = i1)
        {
            recordPattern(bitarray, k, ai);
            for (int l = 0; l < 5; l++)
            {
                int k1 = l << 1;
                ai1[l] = ai[k1];
                ai2[l] = ai[k1 + 1];
            }

            stringbuilder.append((char)(48 + a(ai1)));
            stringbuilder.append((char)(48 + a(ai2)));
            i1 = k;
            for (int j1 = 0; j1 < 10; j1++)
            {
                i1 += ai[j1];
            }

        }

    }

    private static int[] a(BitArray bitarray, int i, int ai[])
    {
        int j = ai.length;
        int ai1[] = new int[j];
        int k = bitarray.getSize();
        int l = i;
        int i1 = 0;
        boolean flag = false;
        while (i < k) 
        {
            if (flag ^ bitarray.get(i))
            {
                ai1[i1] = 1 + ai1[i1];
            } else
            {
                if (i1 == j - 1)
                {
                    if (patternMatchVariance(ai1, ai, 204) < 107)
                    {
                        return (new int[] {
                            l, i
                        });
                    }
                    l += ai1[0] + ai1[1];
                    System.arraycopy(ai1, 2, ai1, 0, j - 2);
                    ai1[j - 2] = 0;
                    ai1[j - 1] = 0;
                    i1--;
                } else
                {
                    i1++;
                }
                ai1[i1] = 1;
                if (!flag)
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
            }
            i++;
        }
        throw NotFoundException.getNotFoundInstance();
    }

    private int[] b(BitArray bitarray)
    {
        bitarray.reverse();
        int ai[];
        ai = a(bitarray, a(bitarray), e);
        a(bitarray, ai[0]);
        int i = ai[0];
        ai[0] = bitarray.getSize() - ai[1];
        ai[1] = bitarray.getSize() - i;
        bitarray.reverse();
        return ai;
        Exception exception;
        exception;
        bitarray.reverse();
        throw exception;
    }

    public final Result decodeRow(int i, BitArray bitarray, Map map)
    {
        int ai[] = a(bitarray, a(bitarray), d);
        c = ai[1] - ai[0] >> 2;
        a(bitarray, ai[0]);
        int ai1[] = b(bitarray);
        StringBuilder stringbuilder = new StringBuilder(20);
        a(bitarray, ai[1], ai1[0], stringbuilder);
        String s = stringbuilder.toString();
        int ai2[];
        int j;
        int k;
        int l;
        boolean flag;
        ResultPoint aresultpoint[];
        if (map != null)
        {
            ai2 = (int[])map.get(DecodeHintType.ALLOWED_LENGTHS);
        } else
        {
            ai2 = null;
        }
        if (ai2 == null)
        {
            ai2 = b;
        }
        j = s.length();
        k = ai2.length;
        l = 0;
        if (l >= k)
        {
            break MISSING_BLOCK_LABEL_212;
        }
        if (j != ai2[l]) goto _L2; else goto _L1
_L1:
        flag = true;
_L4:
        if (!flag)
        {
            throw FormatException.getFormatInstance();
        } else
        {
            aresultpoint = new ResultPoint[2];
            aresultpoint[0] = new ResultPoint(ai[1], i);
            aresultpoint[1] = new ResultPoint(ai1[0], i);
            return new Result(s, null, aresultpoint, BarcodeFormat.ITF);
        }
_L2:
        l++;
        break MISSING_BLOCK_LABEL_119;
        flag = false;
        if (true) goto _L4; else goto _L3
_L3:
    }

}
