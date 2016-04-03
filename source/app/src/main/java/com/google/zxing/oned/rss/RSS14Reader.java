// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing.oned.rss;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.DecodeHintType;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.ResultPoint;
import com.google.zxing.ResultPointCallback;
import com.google.zxing.common.BitArray;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

// Referenced classes of package com.google.zxing.oned.rss:
//            AbstractRSSReader, FinderPattern, RSSUtils, DataCharacter, 
//            a

public final class RSS14Reader extends AbstractRSSReader
{

    private static final int a[] = {
        1, 10, 34, 70, 126
    };
    private static final int b[] = {
        4, 20, 48, 81
    };
    private static final int c[] = {
        0, 161, 961, 2015, 2715
    };
    private static final int d[] = {
        0, 336, 1036, 1516
    };
    private static final int e[] = {
        8, 6, 4, 3, 1
    };
    private static final int f[] = {
        2, 4, 6, 8
    };
    private static final int g[][] = {
        {
            3, 8, 2, 1
        }, {
            3, 5, 5, 1
        }, {
            3, 3, 7, 1
        }, {
            3, 1, 9, 1
        }, {
            2, 7, 4, 1
        }, {
            2, 5, 6, 1
        }, {
            2, 3, 8, 1
        }, {
            1, 5, 7, 1
        }, {
            1, 3, 9, 1
        }
    };
    private final List h = new ArrayList();
    private final List i = new ArrayList();

    public RSS14Reader()
    {
    }

    private DataCharacter a(BitArray bitarray, FinderPattern finderpattern, boolean flag)
    {
        int ai1[];
        int ai2[];
        int k1;
        int l1;
        int i2;
        boolean flag1;
        boolean flag2;
        boolean flag3;
        boolean flag4;
        boolean flag5;
        boolean flag6;
        int ai[] = getDataCharacterCounters();
        ai[0] = 0;
        ai[1] = 0;
        ai[2] = 0;
        ai[3] = 0;
        ai[4] = 0;
        ai[5] = 0;
        ai[6] = 0;
        ai[7] = 0;
        int i1;
        float f1;
        float af[];
        float af1[];
        int j1;
        if (flag)
        {
            recordPatternInReverse(bitarray, finderpattern.getStartEnd()[0], ai);
        } else
        {
            recordPattern(bitarray, 1 + finderpattern.getStartEnd()[1], ai);
            int j = 0;
            int k = -1 + ai.length;
            while (j < k) 
            {
                int l = ai[j];
                ai[j] = ai[k];
                ai[k] = l;
                j++;
                k--;
            }
        }
        if (flag)
        {
            i1 = 16;
        } else
        {
            i1 = 15;
        }
        f1 = (float)count(ai) / (float)i1;
        ai1 = getOddCounts();
        ai2 = getEvenCounts();
        af = getOddRoundingErrors();
        af1 = getEvenRoundingErrors();
        j1 = 0;
        do
        {
            if (j1 >= ai.length)
            {
                break;
            }
            float f2 = (float)ai[j1] / f1;
            int i8 = (int)(0.5F + f2);
            int j8;
            if (i8 <= 0)
            {
                i8 = 1;
            } else
            if (i8 > 8)
            {
                i8 = 8;
            }
            j8 = j1 >> 1;
            if ((j1 & 1) == 0)
            {
                ai1[j8] = i8;
                af[j8] = f2 - (float)i8;
            } else
            {
                ai2[j8] = i8;
                af1[j8] = f2 - (float)i8;
            }
            j1++;
        } while (true);
        k1 = count(getOddCounts());
        l1 = count(getEvenCounts());
        i2 = (k1 + l1) - i1;
        int j2 = k1 & 1;
        int k2;
        if (flag)
        {
            k2 = 1;
        } else
        {
            k2 = 0;
        }
        if (j2 == k2)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        if ((l1 & 1) == 1)
        {
            flag2 = true;
        } else
        {
            flag2 = false;
        }
        flag3 = false;
        flag4 = false;
        if (!flag) goto _L2; else goto _L1
_L1:
        if (k1 <= 12) goto _L4; else goto _L3
_L3:
        flag5 = true;
_L9:
        if (l1 > 12)
        {
            flag6 = true;
        } else
        {
            flag6 = false;
            flag4 = false;
            if (l1 < 4)
            {
                flag4 = true;
                flag6 = false;
            }
        }
_L6:
        if (i2 == 1)
        {
            if (flag1)
            {
                if (flag2)
                {
                    throw NotFoundException.getNotFoundInstance();
                }
                flag5 = true;
            } else
            {
                if (!flag2)
                {
                    throw NotFoundException.getNotFoundInstance();
                }
                flag6 = true;
            }
        } else
        if (i2 == -1)
        {
            if (flag1)
            {
                if (flag2)
                {
                    throw NotFoundException.getNotFoundInstance();
                }
                flag3 = true;
            } else
            {
                if (!flag2)
                {
                    throw NotFoundException.getNotFoundInstance();
                }
                flag4 = true;
            }
        } else
        if (i2 == 0)
        {
            if (!flag1)
            {
                continue; /* Loop/switch isn't completed */
            }
            if (!flag2)
            {
                throw NotFoundException.getNotFoundInstance();
            }
            if (k1 < l1)
            {
                flag3 = true;
                flag6 = true;
            } else
            {
                flag5 = true;
                flag4 = true;
            }
        } else
        {
            throw NotFoundException.getNotFoundInstance();
        }
          goto _L5
_L4:
        flag5 = false;
        flag3 = false;
        if (k1 < 4)
        {
            flag3 = true;
            flag5 = false;
        }
        continue; /* Loop/switch isn't completed */
_L2:
        if (k1 > 11)
        {
            flag5 = true;
        } else
        {
            flag5 = false;
            flag3 = false;
            if (k1 < 5)
            {
                flag3 = true;
                flag5 = false;
            }
        }
        if (l1 > 10)
        {
            flag6 = true;
            flag4 = false;
        } else
        {
            flag6 = false;
            flag4 = false;
            if (l1 < 4)
            {
                flag4 = true;
                flag6 = false;
            }
        }
          goto _L6
        if (!flag2) goto _L5; else goto _L7
_L7:
        throw NotFoundException.getNotFoundInstance();
_L5:
        if (flag3)
        {
            if (flag5)
            {
                throw NotFoundException.getNotFoundInstance();
            }
            increment(getOddCounts(), getOddRoundingErrors());
        }
        if (flag5)
        {
            decrement(getOddCounts(), getOddRoundingErrors());
        }
        if (flag4)
        {
            if (flag6)
            {
                throw NotFoundException.getNotFoundInstance();
            }
            increment(getEvenCounts(), getOddRoundingErrors());
        }
        if (flag6)
        {
            decrement(getEvenCounts(), getEvenRoundingErrors());
        }
        int l2 = -1 + ai1.length;
        int i3 = 0;
        int j3;
        int l7;
        for (j3 = 0; l2 >= 0; j3 = l7)
        {
            int k7 = i3 * 9 + ai1[l2];
            l7 = j3 + ai1[l2];
            l2--;
            i3 = k7;
        }

        int k3 = 0;
        int l3 = 0;
        for (int i4 = -1 + ai2.length; i4 >= 0; i4--)
        {
            k3 = k3 * 9 + ai2[i4];
            l3 += ai2[i4];
        }

        int j4 = i3 + k3 * 3;
        if (flag)
        {
            if ((j3 & 1) != 0 || j3 > 12 || j3 < 4)
            {
                throw NotFoundException.getNotFoundInstance();
            } else
            {
                int i6 = (12 - j3) / 2;
                int j6 = e[i6];
                int k6 = 9 - j6;
                int l6 = RSSUtils.getRSSvalue(ai1, j6, false);
                int i7 = RSSUtils.getRSSvalue(ai2, k6, true);
                int j7 = a[i6];
                return new DataCharacter(c[i6] + (i7 + l6 * j7), j4);
            }
        }
        if ((l3 & 1) != 0 || l3 > 10 || l3 < 4)
        {
            throw NotFoundException.getNotFoundInstance();
        }
        int k4 = (10 - l3) / 2;
        int l4 = f[k4];
        int i5 = 9 - l4;
        int j5 = RSSUtils.getRSSvalue(ai1, l4, true);
        int k5 = RSSUtils.getRSSvalue(ai2, i5, false);
        int l5 = b[k4];
        return new DataCharacter(d[k4] + (j5 + k5 * l5), j4);
        if (true) goto _L9; else goto _L8
_L8:
    }

    private a a(BitArray bitarray, boolean flag, int j, Map map)
    {
        int k = 0;
        int ai[];
        int l;
        boolean flag1;
        int i1;
        boolean flag2;
        int j1;
        int ai1[];
        boolean flag3;
        int k1;
        int l1;
        int i2;
        int ai2[];
        int j2;
        int k2;
        int l2;
        int ai3[];
        FinderPattern finderpattern;
        ResultPointCallback resultpointcallback;
        ResultPointCallback resultpointcallback1;
        float f1;
        DataCharacter datacharacter;
        DataCharacter datacharacter1;
        int i3;
        int j3;
        boolean flag4;
        int k3;
        boolean flag5;
        boolean flag6;
        boolean flag7;
        int l3;
        boolean flag8;
        try
        {
            ai = getDecodeFinderCounters();
            ai[0] = 0;
            ai[1] = 0;
            ai[2] = 0;
            ai[3] = 0;
            l = bitarray.getSize();
        }
        catch (NotFoundException notfoundexception)
        {
            return null;
        }
        flag1 = false;
        if (k >= l) goto _L2; else goto _L1
_L19:
        if (i1 >= l) goto _L4; else goto _L3
_L3:
        if (!(flag2 ^ bitarray.get(i1))) goto _L6; else goto _L5
_L5:
        ai[j1] = 1 + ai[j1];
        flag8 = flag2;
        k3 = k;
        flag6 = flag8;
          goto _L7
_L6:
        if (j1 != 3)
        {
            break MISSING_BLOCK_LABEL_580;
        }
        if (!isFinderPattern(ai)) goto _L9; else goto _L8
_L8:
        ai1 = (new int[] {
            k, i1
        });
        flag3 = bitarray.get(ai1[0]);
        k1 = -1 + ai1[0];
_L11:
        if (k1 < 0)
        {
            break; /* Loop/switch isn't completed */
        }
        if (!(flag3 ^ bitarray.get(k1)))
        {
            break; /* Loop/switch isn't completed */
        }
        k1--;
        if (true) goto _L11; else goto _L10
_L9:
        i3 = k + (ai[0] + ai[1]);
        ai[0] = ai[2];
        ai[1] = ai[3];
        ai[2] = 0;
        ai[3] = 0;
        j3 = j1 - 1;
_L20:
        ai[j3] = 1;
          goto _L12
_L4:
        throw NotFoundException.getNotFoundInstance();
_L10:
        l1 = k1 + 1;
        i2 = ai1[0] - l1;
        ai2 = getDecodeFinderCounters();
        System.arraycopy(ai2, 0, ai2, 1, -1 + ai2.length);
        ai2[0] = i2;
        j2 = parseFinderValue(ai2, g);
        k2 = ai1[1];
        if (!flag) goto _L14; else goto _L13
_L13:
        l2 = (-1 + bitarray.getSize()) - l1;
        k2 = (-1 + bitarray.getSize()) - k2;
_L18:
        ai3 = new int[2];
        ai3[0] = l1;
        ai3[1] = ai1[1];
        finderpattern = new FinderPattern(j2, ai3, l2, k2, j);
        if (map != null) goto _L16; else goto _L15
_L15:
        resultpointcallback1 = null;
_L17:
        if (resultpointcallback1 == null)
        {
            break MISSING_BLOCK_LABEL_424;
        }
        f1 = (float)(ai1[0] + ai1[1]) / 2.0F;
        if (!flag)
        {
            break MISSING_BLOCK_LABEL_406;
        }
        f1 = (float)(-1 + bitarray.getSize()) - f1;
        resultpointcallback1.foundPossibleResultPoint(new ResultPoint(f1, j));
        datacharacter = a(bitarray, finderpattern, true);
        datacharacter1 = a(bitarray, finderpattern, false);
        return new a(1597 * datacharacter.getValue() + datacharacter1.getValue(), datacharacter.getChecksumPortion() + 4 * datacharacter1.getChecksumPortion(), finderpattern);
_L16:
        resultpointcallback = (ResultPointCallback)map.get(DecodeHintType.NEED_RESULT_POINT_CALLBACK);
        resultpointcallback1 = resultpointcallback;
        if (true) goto _L17; else goto _L14
_L14:
        l2 = l1;
          goto _L18
_L1:
        if (!bitarray.get(k))
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        if (flag != flag1)
        {
            k++;
            break MISSING_BLOCK_LABEL_38;
        }
_L2:
        i1 = k;
        flag2 = flag1;
        j1 = 0;
          goto _L19
_L7:
        i1++;
        flag7 = flag6;
        k = k3;
        flag2 = flag7;
          goto _L19
_L12:
        if (!flag2)
        {
            flag4 = true;
        } else
        {
            flag4 = false;
        }
        k3 = i3;
        flag5 = flag4;
        j1 = j3;
        flag6 = flag5;
          goto _L7
        l3 = j1 + 1;
        i3 = k;
        j3 = l3;
          goto _L20
    }

    private static void a(Collection collection, a a1)
    {
        if (a1 != null) goto _L2; else goto _L1
_L1:
        return;
_L2:
        boolean flag;
        Iterator iterator = collection.iterator();
        a a2;
        do
        {
            if (!iterator.hasNext())
            {
                break; /* Loop/switch isn't completed */
            }
            a2 = (a)iterator.next();
        } while (a2.getValue() != a1.getValue());
        a2.c();
        flag = true;
_L4:
        if (!flag)
        {
            collection.add(a1);
            return;
        }
        if (true) goto _L1; else goto _L3
_L3:
        flag = false;
          goto _L4
        if (true) goto _L1; else goto _L5
_L5:
    }

    public final Result decodeRow(int j, BitArray bitarray, Map map)
    {
        a a1 = a(bitarray, false, j, map);
        a(h, a1);
        bitarray.reverse();
        a a2 = a(bitarray, true, j, map);
        a(i, a2);
        bitarray.reverse();
        int k = h.size();
        for (int l = 0; l < k; l++)
        {
            a a3 = (a)h.get(l);
            if (a3.b() <= 1)
            {
                continue;
            }
            int i1 = i.size();
            for (int j1 = 0; j1 < i1; j1++)
            {
                a a4 = (a)i.get(j1);
                if (a4.b() <= 1)
                {
                    continue;
                }
                int k1 = (a3.getChecksumPortion() + 16 * a4.getChecksumPortion()) % 79;
                int l1 = 9 * a3.a().getValue() + a4.a().getValue();
                if (l1 > 72)
                {
                    l1--;
                }
                if (l1 > 8)
                {
                    l1--;
                }
                boolean flag;
                String s;
                StringBuilder stringbuilder;
                if (k1 == l1)
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                if (!flag)
                {
                    continue;
                }
                s = String.valueOf(0x453af5L * (long)a3.getValue() + (long)a4.getValue());
                stringbuilder = new StringBuilder(14);
                for (int i2 = 13 - s.length(); i2 > 0; i2--)
                {
                    stringbuilder.append('0');
                }

                stringbuilder.append(s);
                int j2 = 0;
                for (int k2 = 0; k2 < 13;)
                {
                    int i3 = -48 + stringbuilder.charAt(k2);
                    if ((k2 & 1) == 0)
                    {
                        i3 *= 3;
                    }
                    int j3 = i3 + j2;
                    k2++;
                    j2 = j3;
                }

                int l2 = 10 - j2 % 10;
                if (l2 == 10)
                {
                    l2 = 0;
                }
                stringbuilder.append(l2);
                ResultPoint aresultpoint[] = a3.a().getResultPoints();
                ResultPoint aresultpoint1[] = a4.a().getResultPoints();
                String s1 = String.valueOf(stringbuilder.toString());
                ResultPoint aresultpoint2[] = new ResultPoint[4];
                aresultpoint2[0] = aresultpoint[0];
                aresultpoint2[1] = aresultpoint[1];
                aresultpoint2[2] = aresultpoint1[0];
                aresultpoint2[3] = aresultpoint1[1];
                return new Result(s1, null, aresultpoint2, BarcodeFormat.RSS_14);
            }

        }

        throw NotFoundException.getNotFoundInstance();
    }

    public final void reset()
    {
        h.clear();
        i.clear();
    }

}
