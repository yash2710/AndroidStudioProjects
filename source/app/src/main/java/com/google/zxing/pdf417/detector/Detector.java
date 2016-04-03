// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing.pdf417.detector;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.BitArray;
import com.google.zxing.common.BitMatrix;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

// Referenced classes of package com.google.zxing.pdf417.detector:
//            PDF417DetectorResult

public final class Detector
{

    private static final int a[] = {
        0, 4, 1, 5
    };
    private static final int b[] = {
        6, 2, 7, 3
    };
    private static final int c[] = {
        8, 1, 1, 1, 1, 1, 1, 3
    };
    private static final int d[] = {
        7, 1, 1, 3, 1, 1, 1, 2, 1
    };

    private Detector()
    {
    }

    private static int a(int ai[], int ai1[], int i)
    {
        int j;
        int l;
        int i1;
        j = ai.length;
        int k = 0;
        l = 0;
        i1 = 0;
        while (k < j) 
        {
            int i3 = l + ai[k];
            int j3 = i1 + ai1[k];
            k++;
            i1 = j3;
            l = i3;
        }
        if (l >= i1) goto _L2; else goto _L1
_L1:
        return 0x7fffffff;
_L2:
        int i2;
        int j1 = (l << 8) / i1;
        int k1 = j1 * 204 >> 8;
        int l1 = 0;
        i2 = 0;
        do
        {
label0:
            {
                if (l1 >= j)
                {
                    break label0;
                }
                int j2 = ai[l1] << 8;
                int k2 = j1 * ai1[l1];
                int l2;
                if (j2 > k2)
                {
                    l2 = j2 - k2;
                } else
                {
                    l2 = k2 - j2;
                }
                if (l2 > k1)
                {
                    continue; /* Loop/switch isn't completed */
                }
                i2 += l2;
                l1++;
            }
        } while (true);
        if (true) goto _L1; else goto _L3
_L3:
        return i2 / l;
    }

    private static BitArray a(BitArray bitarray, BitArray bitarray1)
    {
        bitarray1.clear();
        int i = bitarray.getSize();
        for (int j = 0; j < i; j++)
        {
            if (bitarray.get(j))
            {
                bitarray1.set(i - 1 - j);
            }
        }

        return bitarray1;
    }

    private static List a(boolean flag, BitMatrix bitmatrix)
    {
        ArrayList arraylist = new ArrayList();
        int i = 0;
        int j = 0;
        boolean flag1 = false;
        do
        {
            if (i >= bitmatrix.getHeight())
            {
                break;
            }
            int k = bitmatrix.getHeight();
            int l = bitmatrix.getWidth();
            ResultPoint aresultpoint[] = new ResultPoint[8];
            a(aresultpoint, a(bitmatrix, k, l, i, j, c), a);
            int i1;
            int j1;
            if (aresultpoint[4] != null)
            {
                i1 = (int)aresultpoint[4].getX();
                j1 = (int)aresultpoint[4].getY();
            } else
            {
                i1 = j;
                j1 = i;
            }
            a(aresultpoint, a(bitmatrix, k, l, j1, i1, d), b);
            if (aresultpoint[0] == null && aresultpoint[3] == null)
            {
                if (!flag1)
                {
                    break;
                }
                Iterator iterator = arraylist.iterator();
                int k1 = i;
                do
                {
                    if (!iterator.hasNext())
                    {
                        break;
                    }
                    ResultPoint aresultpoint1[] = (ResultPoint[])iterator.next();
                    if (aresultpoint1[1] != null)
                    {
                        k1 = (int)Math.max(k1, aresultpoint1[1].getY());
                    }
                    if (aresultpoint1[3] != null)
                    {
                        k1 = Math.max(k1, (int)aresultpoint1[3].getY());
                    }
                } while (true);
                i = k1 + 5;
                j = 0;
                flag1 = false;
                continue;
            }
            arraylist.add(aresultpoint);
            if (!flag)
            {
                break;
            }
            if (aresultpoint[2] != null)
            {
                j = (int)aresultpoint[2].getX();
                i = (int)aresultpoint[2].getY();
                flag1 = true;
            } else
            {
                j = (int)aresultpoint[4].getX();
                i = (int)aresultpoint[4].getY();
                flag1 = true;
            }
        } while (true);
        return arraylist;
    }

    private static void a(ResultPoint aresultpoint[], ResultPoint aresultpoint1[], int ai[])
    {
        for (int i = 0; i < ai.length; i++)
        {
            aresultpoint[ai[i]] = aresultpoint1[i];
        }

    }

    private static int[] a(BitMatrix bitmatrix, int i, int j, int k, boolean flag, int ai[], int ai1[])
    {
        Arrays.fill(ai1, 0, ai1.length, 0);
        int l = ai.length;
        boolean flag1 = false;
        int i1 = 0;
        do
        {
            if (!bitmatrix.get(i, j) || i <= 0)
            {
                break;
            }
            int l1 = i1 + 1;
            if (i1 >= 3)
            {
                break;
            }
            i--;
            i1 = l1;
        } while (true);
        int j1 = 0;
        int k1 = i;
        while (i < k) 
        {
            if (flag1 ^ bitmatrix.get(i, j))
            {
                ai1[j1] = 1 + ai1[j1];
            } else
            {
                if (j1 == l - 1)
                {
                    if (a(ai1, ai, 204) < 107)
                    {
                        return (new int[] {
                            k1, i
                        });
                    }
                    k1 += ai1[0] + ai1[1];
                    System.arraycopy(ai1, 2, ai1, 0, l - 2);
                    ai1[l - 2] = 0;
                    ai1[l - 1] = 0;
                    j1--;
                } else
                {
                    j1++;
                }
                ai1[j1] = 1;
                if (!flag1)
                {
                    flag1 = true;
                } else
                {
                    flag1 = false;
                }
            }
            i++;
        }
        if (j1 == l - 1 && a(ai1, ai, 204) < 107)
        {
            int ai2[] = new int[2];
            ai2[0] = k1;
            ai2[1] = i - 1;
            return ai2;
        } else
        {
            return null;
        }
    }

    private static ResultPoint[] a(BitMatrix bitmatrix, int i, int j, int k, int l, int ai[])
    {
        ResultPoint aresultpoint[];
        int ai1[];
        int i1;
        aresultpoint = new ResultPoint[4];
        ai1 = new int[ai.length];
        i1 = k;
_L10:
        int ai4[];
        if (i1 >= i)
        {
            break MISSING_BLOCK_LABEL_377;
        }
        ai4 = a(bitmatrix, l, i1, j, false, ai, ai1);
        if (ai4 == null) goto _L2; else goto _L1
_L1:
        int j1;
        boolean flag;
        int ai5[];
        int l2;
label0:
        {
            ai5 = ai4;
            l2 = i1;
            int i3;
            do
            {
                if (l2 <= 0)
                {
                    break label0;
                }
                i3 = l2 - 1;
                int ai6[] = a(bitmatrix, l, i3, j, false, ai, ai1);
                if (ai6 == null)
                {
                    break;
                }
                ai5 = ai6;
                l2 = i3;
            } while (true);
            l2 = i3 + 1;
        }
        aresultpoint[0] = new ResultPoint(ai5[0], l2);
        aresultpoint[1] = new ResultPoint(ai5[1], l2);
        flag = true;
        j1 = l2;
_L13:
        int k1 = j1 + 1;
        if (!flag) goto _L4; else goto _L3
_L3:
        int ai2[];
        int i2;
        int j2;
        ai2 = new int[2];
        ai2[0] = (int)aresultpoint[0].getX();
        ai2[1] = (int)aresultpoint[1].getX();
        i2 = k1;
        j2 = 0;
_L9:
        if (i2 >= i) goto _L6; else goto _L5
_L5:
        int ai3[] = a(bitmatrix, ai2[0], i2, j, false, ai, ai1);
        if (ai3 == null || Math.abs(ai2[0] - ai3[0]) >= 5 || Math.abs(ai2[1] - ai3[1]) >= 5) goto _L8; else goto _L7
_L7:
        int k2 = 0;
_L12:
        i2++;
        j2 = k2;
        ai2 = ai3;
          goto _L9
_L2:
        i1 += 5;
          goto _L10
_L8:
        if (j2 > 25) goto _L6; else goto _L11
_L11:
        k2 = j2 + 1;
        ai3 = ai2;
          goto _L12
_L6:
        k1 = i2 - (j2 + 1);
        aresultpoint[2] = new ResultPoint(ai2[0], k1);
        aresultpoint[3] = new ResultPoint(ai2[1], k1);
_L4:
        if (k1 - j1 < 10)
        {
            for (int l1 = 0; l1 < 4; l1++)
            {
                aresultpoint[l1] = null;
            }

        }
        return aresultpoint;
        j1 = i1;
        flag = false;
          goto _L13
    }

    public static PDF417DetectorResult detect(BinaryBitmap binarybitmap, Map map, boolean flag)
    {
        BitMatrix bitmatrix = binarybitmap.getBlackMatrix();
        List list = a(flag, bitmatrix);
        if (list.isEmpty())
        {
            int i = bitmatrix.getWidth();
            int j = bitmatrix.getHeight();
            BitArray bitarray = new BitArray(i);
            BitArray bitarray1 = new BitArray(i);
            BitArray bitarray2 = new BitArray(i);
            for (int k = 0; k < j + 1 >> 1; k++)
            {
                bitarray = bitmatrix.getRow(k, bitarray);
                bitmatrix.setRow(k, a(bitmatrix.getRow(j - 1 - k, bitarray1), bitarray2));
                bitmatrix.setRow(j - 1 - k, a(bitarray, bitarray2));
            }

            list = a(flag, bitmatrix);
        }
        return new PDF417DetectorResult(bitmatrix, list);
    }

}
