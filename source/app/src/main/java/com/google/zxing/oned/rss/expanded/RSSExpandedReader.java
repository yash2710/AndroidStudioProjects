// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing.oned.rss.expanded;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.BitArray;
import com.google.zxing.oned.rss.AbstractRSSReader;
import com.google.zxing.oned.rss.DataCharacter;
import com.google.zxing.oned.rss.FinderPattern;
import com.google.zxing.oned.rss.RSSUtils;
import com.google.zxing.oned.rss.expanded.decoders.AbstractExpandedDecoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

// Referenced classes of package com.google.zxing.oned.rss.expanded:
//            a, b

public final class RSSExpandedReader extends AbstractRSSReader
{

    private static final int a[] = {
        7, 5, 4, 3, 1
    };
    private static final int b[] = {
        4, 20, 52, 104, 204
    };
    private static final int c[] = {
        0, 348, 1388, 2948, 3988
    };
    private static final int d[][] = {
        {
            1, 8, 4, 1
        }, {
            3, 6, 4, 1
        }, {
            3, 4, 6, 1
        }, {
            3, 2, 8, 1
        }, {
            2, 6, 5, 1
        }, {
            2, 2, 9, 1
        }
    };
    private static final int e[][] = {
        {
            1, 3, 9, 27, 81, 32, 96, 77
        }, {
            20, 60, 180, 118, 143, 7, 21, 63
        }, {
            189, 145, 13, 39, 117, 140, 209, 205
        }, {
            193, 157, 49, 147, 19, 57, 171, 91
        }, {
            62, 186, 136, 197, 169, 85, 44, 132
        }, {
            185, 133, 188, 142, 4, 12, 36, 108
        }, {
            113, 128, 173, 97, 80, 29, 87, 50
        }, {
            150, 28, 84, 41, 123, 158, 52, 156
        }, {
            46, 138, 203, 187, 139, 206, 196, 166
        }, {
            76, 17, 51, 153, 37, 111, 122, 155
        }, {
            43, 129, 176, 106, 107, 110, 119, 146
        }, {
            16, 48, 144, 10, 30, 90, 59, 177
        }, {
            109, 116, 137, 200, 178, 112, 125, 164
        }, {
            70, 210, 208, 202, 184, 130, 179, 115
        }, {
            134, 191, 151, 31, 93, 68, 204, 190
        }, {
            148, 22, 66, 198, 172, 94, 71, 2
        }, {
            6, 18, 54, 162, 64, 192, 154, 40
        }, {
            120, 149, 25, 75, 14, 42, 126, 167
        }, {
            79, 26, 78, 23, 69, 207, 199, 175
        }, {
            103, 98, 83, 38, 114, 131, 182, 124
        }, {
            161, 61, 183, 127, 170, 88, 53, 159
        }, {
            55, 165, 73, 8, 24, 72, 5, 15
        }, {
            45, 135, 194, 160, 58, 174, 100, 89
        }
    };
    private static final int f[][] = {
        {
            0, 0
        }, {
            0, 1, 1
        }, {
            0, 2, 1, 3
        }, {
            0, 4, 1, 3, 2
        }, {
            0, 4, 1, 3, 3, 5
        }, {
            0, 4, 1, 3, 4, 5, 5
        }, {
            0, 0, 1, 1, 2, 2, 3, 3
        }, {
            0, 0, 1, 1, 2, 2, 3, 4, 4
        }, {
            0, 0, 1, 1, 2, 2, 3, 4, 5, 5
        }, {
            0, 0, 1, 1, 2, 3, 3, 4, 4, 5, 
            5
        }
    };
    private final List g = new ArrayList(11);
    private final List h = new ArrayList();
    private final int i[] = new int[2];
    private boolean j;

    public RSSExpandedReader()
    {
        j = false;
    }

    private static Result a(List list)
    {
        int k = -1 + (list.size() << 1);
        int l;
        BitArray bitarray;
        int i1;
        int j1;
        int k1;
        int l1;
        String s;
        ResultPoint aresultpoint[];
        ResultPoint aresultpoint1[];
        ResultPoint aresultpoint2[];
        if (((a)list.get(-1 + list.size())).b() == null)
        {
            l = k - 1;
        } else
        {
            l = k;
        }
        bitarray = new BitArray(l * 12);
        i1 = ((a)list.get(0)).b().getValue();
        j1 = 11;
        int i4;
        for (k1 = 0; j1 >= 0; k1 = i4)
        {
            if ((i1 & 1 << j1) != 0)
            {
                bitarray.set(k1);
            }
            i4 = k1 + 1;
            j1--;
        }

        l1 = 1;
        int l2;
        for (int i2 = k1; l1 < list.size(); i2 = l2)
        {
            a a1 = (a)list.get(l1);
            int j2 = a1.a().getValue();
            for (int k2 = 11; k2 >= 0;)
            {
                if ((j2 & 1 << k2) != 0)
                {
                    bitarray.set(i2);
                }
                int l3 = i2 + 1;
                k2--;
                i2 = l3;
            }

            if (a1.b() != null)
            {
                int i3 = a1.b().getValue();
                l2 = i2;
                for (int j3 = 11; j3 >= 0;)
                {
                    if ((i3 & 1 << j3) != 0)
                    {
                        bitarray.set(l2);
                    }
                    int k3 = l2 + 1;
                    j3--;
                    l2 = k3;
                }

            } else
            {
                l2 = i2;
            }
            l1++;
        }

        s = AbstractExpandedDecoder.createDecoder(bitarray).parseInformation();
        aresultpoint = ((a)list.get(0)).c().getResultPoints();
        aresultpoint1 = ((a)list.get(-1 + list.size())).c().getResultPoints();
        aresultpoint2 = new ResultPoint[4];
        aresultpoint2[0] = aresultpoint[0];
        aresultpoint2[1] = aresultpoint[1];
        aresultpoint2[2] = aresultpoint1[0];
        aresultpoint2[3] = aresultpoint1[1];
        return new Result(s, null, aresultpoint2, BarcodeFormat.RSS_EXPANDED);
    }

    private DataCharacter a(BitArray bitarray, FinderPattern finderpattern, boolean flag, boolean flag1)
    {
        int ai1[];
        int ai2[];
        int k1;
        int l1;
        int i2;
        boolean flag2;
        boolean flag3;
        boolean flag4;
        boolean flag5;
        boolean flag6;
        boolean flag7;
        int ai[] = getDataCharacterCounters();
        ai[0] = 0;
        ai[1] = 0;
        ai[2] = 0;
        ai[3] = 0;
        ai[4] = 0;
        ai[5] = 0;
        ai[6] = 0;
        ai[7] = 0;
        float f1;
        float f2;
        if (flag1)
        {
            recordPatternInReverse(bitarray, finderpattern.getStartEnd()[0], ai);
        } else
        {
            recordPattern(bitarray, finderpattern.getStartEnd()[1], ai);
            int k = 0;
            int l = -1 + ai.length;
            while (k < l) 
            {
                int i1 = ai[k];
                ai[k] = ai[l];
                ai[l] = i1;
                k++;
                l--;
            }
        }
        f1 = (float)count(ai) / 17F;
        f2 = (float)(finderpattern.getStartEnd()[1] - finderpattern.getStartEnd()[0]) / 15F;
        if (Math.abs(f1 - f2) / f2 > 0.3F)
        {
            throw NotFoundException.getNotFoundInstance();
        }
        ai1 = getOddCounts();
        ai2 = getEvenCounts();
        float af[] = getOddRoundingErrors();
        float af1[] = getEvenRoundingErrors();
        int j1 = 0;
        while (j1 < ai.length) 
        {
            float f3 = (1.0F * (float)ai[j1]) / f1;
            int i7 = (int)(0.5F + f3);
            int j7;
            if (i7 <= 0)
            {
                if (f3 < 0.3F)
                {
                    throw NotFoundException.getNotFoundInstance();
                }
                i7 = 1;
            } else
            if (i7 > 8)
            {
                if (f3 > 8.7F)
                {
                    throw NotFoundException.getNotFoundInstance();
                }
                i7 = 8;
            }
            j7 = j1 >> 1;
            if ((j1 & 1) == 0)
            {
                ai1[j7] = i7;
                af[j7] = f3 - (float)i7;
            } else
            {
                ai2[j7] = i7;
                af1[j7] = f3 - (float)i7;
            }
            j1++;
        }
        k1 = count(getOddCounts());
        l1 = count(getEvenCounts());
        i2 = -17 + (k1 + l1);
        if ((k1 & 1) == 1)
        {
            flag2 = true;
        } else
        {
            flag2 = false;
        }
        if ((l1 & 1) == 0)
        {
            flag3 = true;
        } else
        {
            flag3 = false;
        }
        flag4 = false;
        if (k1 <= 13) goto _L2; else goto _L1
_L1:
        flag5 = true;
_L6:
        flag6 = false;
        if (l1 > 13)
        {
            flag7 = true;
        } else
        {
            flag7 = false;
            flag6 = false;
            if (l1 < 4)
            {
                flag6 = true;
                flag7 = false;
            }
        }
        if (i2 == 1)
        {
            if (flag2)
            {
                if (flag3)
                {
                    throw NotFoundException.getNotFoundInstance();
                }
                flag5 = true;
            } else
            {
                if (!flag3)
                {
                    throw NotFoundException.getNotFoundInstance();
                }
                flag7 = true;
            }
        } else
        if (i2 == -1)
        {
            if (flag2)
            {
                if (flag3)
                {
                    throw NotFoundException.getNotFoundInstance();
                }
                flag4 = true;
            } else
            {
                if (!flag3)
                {
                    throw NotFoundException.getNotFoundInstance();
                }
                flag6 = true;
            }
        } else
        if (i2 == 0)
        {
            if (!flag2)
            {
                continue; /* Loop/switch isn't completed */
            }
            if (!flag3)
            {
                throw NotFoundException.getNotFoundInstance();
            }
            if (k1 < l1)
            {
                flag4 = true;
                flag7 = true;
            } else
            {
                flag5 = true;
                flag6 = true;
            }
        } else
        {
            throw NotFoundException.getNotFoundInstance();
        }
          goto _L3
_L2:
        flag5 = false;
        flag4 = false;
        if (k1 < 4)
        {
            flag4 = true;
            flag5 = false;
        }
        continue; /* Loop/switch isn't completed */
        if (!flag3) goto _L3; else goto _L4
_L4:
        throw NotFoundException.getNotFoundInstance();
_L3:
        if (flag4)
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
        if (flag6)
        {
            if (flag7)
            {
                throw NotFoundException.getNotFoundInstance();
            }
            increment(getEvenCounts(), getOddRoundingErrors());
        }
        if (flag7)
        {
            decrement(getEvenCounts(), getEvenRoundingErrors());
        }
        int j2 = 4 * finderpattern.getValue();
        int k2;
        int l2;
        int i3;
        int j3;
        int k3;
        int l3;
        int i4;
        int j4;
        if (flag)
        {
            k2 = 0;
        } else
        {
            k2 = 2;
        }
        l2 = j2 + k2;
        if (flag1)
        {
            i3 = 0;
        } else
        {
            i3 = 1;
        }
        j3 = -1 + (i3 + l2);
        k3 = -1 + ai1.length;
        l3 = 0;
        i4 = k3;
        j4 = 0;
        while (i4 >= 0) 
        {
            if (a(finderpattern, flag, flag1))
            {
                j4 += e[j3][i4 * 2] * ai1[i4];
            }
            int l6 = l3 + ai1[i4];
            i4--;
            l3 = l6;
        }
        int k4 = 0;
        for (int l4 = -1 + ai2.length; l4 >= 0; l4--)
        {
            if (a(finderpattern, flag, flag1))
            {
                k4 += e[j3][1 + l4 * 2] * ai2[l4];
            }
        }

        int i5 = j4 + k4;
        if ((l3 & 1) != 0 || l3 > 13 || l3 < 4)
        {
            throw NotFoundException.getNotFoundInstance();
        }
        int j5 = (13 - l3) / 2;
        int k5 = a[j5];
        int l5 = 9 - k5;
        int i6 = RSSUtils.getRSSvalue(ai1, k5, true);
        int j6 = RSSUtils.getRSSvalue(ai2, l5, false);
        int k6 = b[j5];
        return new DataCharacter(c[j5] + (j6 + i6 * k6), i5);
        if (true) goto _L6; else goto _L5
_L5:
    }

    private FinderPattern a(BitArray bitarray, int k, boolean flag)
    {
        int l;
        int i1;
        int j1;
        int ai[];
        int k1;
        if (flag)
        {
            int l1;
            for (l1 = -1 + i[0]; l1 >= 0 && !bitarray.get(l1); l1--) { }
            l = l1 + 1;
            j1 = i[0] - l;
            i1 = i[1];
        } else
        {
            l = i[0];
            i1 = bitarray.getNextUnset(1 + i[1]);
            j1 = i1 - i[1];
        }
        ai = getDecodeFinderCounters();
        System.arraycopy(ai, 0, ai, 1, -1 + ai.length);
        ai[0] = j1;
        try
        {
            k1 = parseFinderValue(ai, d);
        }
        catch (NotFoundException notfoundexception)
        {
            return null;
        }
        return new FinderPattern(k1, new int[] {
            l, i1
        }, l, i1, k);
    }

    private a a(BitArray bitarray, List list, int k)
    {
        boolean flag;
        boolean flag1;
        boolean flag2;
        int l;
        int ai[];
        int i1;
        int j1;
        boolean flag3;
        int k1;
        boolean flag4;
        if (list.size() % 2 == 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        int l1;
        boolean flag5;
        int i2;
        int j2;
        FinderPattern finderpattern;
        int k2;
        boolean flag6;
        DataCharacter datacharacter;
        NotFoundException notfoundexception;
        DataCharacter datacharacter1;
        DataCharacter datacharacter2;
        int l2;
        if (j)
        {
            boolean flag7;
            if (!flag)
            {
                flag7 = true;
            } else
            {
                flag7 = false;
            }
            flag1 = flag7;
        } else
        {
            flag1 = flag;
        }
        flag2 = true;
        l = -1;
        ai = getDecodeFinderCounters();
        ai[0] = 0;
        ai[1] = 0;
        ai[2] = 0;
        ai[3] = 0;
        i1 = bitarray.getSize();
        if (l >= 0)
        {
            j1 = l;
        } else
        if (list.isEmpty())
        {
            j1 = 0;
        } else
        {
            j1 = ((a)list.get(-1 + list.size())).c().getStartEnd()[1];
        }
        if (list.size() % 2 != 0)
        {
            flag3 = true;
        } else
        {
            flag3 = false;
        }
        if (j)
        {
            if (!flag3)
            {
                flag3 = true;
            } else
            {
                flag3 = false;
            }
        }
        k1 = j1;
        flag4 = false;
        do
        {
            if (k1 >= i1)
            {
                break;
            }
            if (!bitarray.get(k1))
            {
                flag4 = true;
            } else
            {
                flag4 = false;
            }
            if (!flag4)
            {
                break;
            }
            k1++;
        } while (true);
        l1 = k1;
        flag5 = flag4;
        i2 = k1;
        j2 = 0;
_L5:
        if (l1 >= i1) goto _L2; else goto _L1
_L1:
        if (!(flag5 ^ bitarray.get(l1))) goto _L4; else goto _L3
_L3:
        ai[j2] = 1 + ai[j2];
_L13:
        l1++;
          goto _L5
_L4:
        if (j2 != 3) goto _L7; else goto _L6
_L6:
        if (flag3)
        {
            a(ai);
        }
        if (!isFinderPattern(ai)) goto _L9; else goto _L8
_L8:
        i[0] = i2;
        i[1] = l1;
        finderpattern = a(bitarray, k, flag1);
        if (finderpattern == null)
        {
            l2 = i[0];
            if (bitarray.get(l2))
            {
                k2 = bitarray.getNextSet(bitarray.getNextUnset(l2));
            } else
            {
                k2 = bitarray.getNextUnset(bitarray.getNextSet(l2));
            }
            flag6 = flag2;
        } else
        {
            k2 = l;
            flag6 = false;
        }
        if (flag6) goto _L11; else goto _L10
_L10:
        datacharacter = a(bitarray, finderpattern, flag1, true);
        if (!list.isEmpty() && ((a)list.get(-1 + list.size())).mustBeLast())
        {
            throw NotFoundException.getNotFoundInstance();
        }
          goto _L12
_L9:
        if (flag3)
        {
            a(ai);
        }
        i2 += ai[0] + ai[1];
        ai[0] = ai[2];
        ai[1] = ai[3];
        ai[2] = 0;
        ai[3] = 0;
        j2--;
_L14:
        ai[j2] = 1;
        if (!flag5)
        {
            flag5 = true;
        } else
        {
            flag5 = false;
        }
          goto _L13
_L7:
        j2++;
          goto _L14
_L2:
        throw NotFoundException.getNotFoundInstance();
_L12:
        datacharacter2 = a(bitarray, finderpattern, flag1, false);
        datacharacter1 = datacharacter2;
_L15:
        return new a(datacharacter, datacharacter1, finderpattern, true);
        notfoundexception;
        datacharacter1 = null;
        if (true) goto _L15; else goto _L11
_L11:
        flag2 = flag6;
        l = k2;
        if (false)
        {
        } else
        {
            break MISSING_BLOCK_LABEL_39;
        }
    }

    private List a(int k, BitArray bitarray)
    {
        try
        {
            do
            {
                a a3 = a(bitarray, g, k);
                g.add(a3);
            } while (true);
        }
        catch (NotFoundException notfoundexception)
        {
            if (g.isEmpty())
            {
                throw notfoundexception;
            }
        }
        if (!a()) goto _L2; else goto _L1
_L1:
        List list = g;
_L8:
        return list;
_L2:
        boolean flag1;
        int l;
        boolean flag2;
        b b3;
        boolean flag;
        if (!h.isEmpty())
        {
            flag = true;
        } else
        {
            flag = false;
        }
        flag1 = false;
        l = 0;
_L10:
        if (l >= h.size())
        {
            break MISSING_BLOCK_LABEL_543;
        }
        b3 = (b)h.get(l);
        if (b3.b() <= k) goto _L4; else goto _L3
_L3:
        flag2 = b3.a(g);
_L38:
        if (!flag2 && !flag1) goto _L6; else goto _L5
_L5:
        if (!flag)
        {
            break; /* Loop/switch isn't completed */
        }
        list = a(false);
        if (list != null) goto _L8; else goto _L7
_L7:
        list = a(true);
        if (list != null) goto _L8; else goto _L9
_L9:
        throw NotFoundException.getNotFoundInstance();
_L4:
        boolean flag8 = b3.a(g);
        l++;
        flag1 = flag8;
          goto _L10
_L6:
        List list1;
        Iterator iterator;
        list1 = g;
        iterator = h.iterator();
_L20:
        if (!iterator.hasNext()) goto _L12; else goto _L11
_L11:
        b b2;
        Iterator iterator4;
        b2 = (b)iterator.next();
        iterator4 = list1.iterator();
_L18:
        a a2;
        Iterator iterator5;
        if (!iterator4.hasNext())
        {
            break MISSING_BLOCK_LABEL_537;
        }
        a2 = (a)iterator4.next();
        iterator5 = b2.a().iterator();
_L16:
        if (!iterator5.hasNext()) goto _L14; else goto _L13
_L13:
        if (!a2.equals((a)iterator5.next())) goto _L16; else goto _L15
_L15:
        boolean flag7 = true;
_L36:
        if (flag7) goto _L18; else goto _L17
_L17:
        boolean flag6 = false;
_L37:
        if (!flag6) goto _L20; else goto _L19
_L19:
        boolean flag3 = true;
_L33:
        if (flag3) goto _L5; else goto _L21
_L21:
        List list2;
        Iterator iterator1;
        h.add(l, new b(g, k, false));
        list2 = g;
        iterator1 = h.iterator();
_L24:
        if (!iterator1.hasNext()) goto _L5; else goto _L22
_L22:
        b b1 = (b)iterator1.next();
        if (b1.a().size() == list2.size()) goto _L24; else goto _L23
_L23:
        Iterator iterator2 = b1.a().iterator();
_L32:
        if (!iterator2.hasNext()) goto _L26; else goto _L25
_L25:
        a a1;
        Iterator iterator3;
        a1 = (a)iterator2.next();
        iterator3 = list2.iterator();
_L30:
        if (!iterator3.hasNext()) goto _L28; else goto _L27
_L27:
        if (!a1.equals((a)iterator3.next())) goto _L30; else goto _L29
_L29:
        boolean flag5 = true;
_L34:
        if (flag5) goto _L32; else goto _L31
_L31:
        boolean flag4 = false;
_L35:
        if (flag4)
        {
            iterator1.remove();
        }
          goto _L24
_L12:
        flag3 = false;
          goto _L33
_L28:
        flag5 = false;
          goto _L34
_L26:
        flag4 = true;
          goto _L35
_L14:
        flag7 = false;
          goto _L36
        flag6 = true;
          goto _L37
        flag2 = false;
          goto _L38
    }

    private List a(List list, int k)
    {
_L10:
        if (k >= h.size()) goto _L2; else goto _L1
_L1:
        b b1;
        List list1;
        int ai[][];
        int j1;
        b1 = (b)h.get(k);
        g.clear();
        int l = list.size();
        for (int i1 = 0; i1 < l; i1++)
        {
            g.addAll(((b)list.get(i1)).a());
        }

        g.addAll(b1.a());
        list1 = g;
        ai = f;
        j1 = 0;
_L9:
        if (j1 >= 10) goto _L4; else goto _L3
_L3:
        int ai1[];
        int l1;
        ai1 = ai[j1];
        if (list1.size() > ai1.length)
        {
            continue; /* Loop/switch isn't completed */
        }
        l1 = 0;
_L8:
        if (l1 >= list1.size())
        {
            break MISSING_BLOCK_LABEL_283;
        }
        if (((a)list1.get(l1)).c().getValue() == ai1[l1]) goto _L6; else goto _L5
_L5:
        boolean flag1 = false;
_L11:
        boolean flag;
        if (!flag1)
        {
            continue; /* Loop/switch isn't completed */
        }
        flag = true;
          goto _L7
_L6:
        l1++;
          goto _L8
        j1++;
          goto _L9
_L4:
        flag = false;
_L7:
        ArrayList arraylist;
        int k1;
        if (!flag)
        {
            continue; /* Loop/switch isn't completed */
        }
        if (a())
        {
            return g;
        }
        arraylist = new ArrayList();
        arraylist.addAll(list);
        arraylist.add(b1);
        k1 = k + 1;
        List list2 = a(((List) (arraylist)), k1);
        return list2;
        NotFoundException notfoundexception;
        notfoundexception;
        k++;
          goto _L10
_L2:
        throw NotFoundException.getNotFoundInstance();
        flag1 = true;
          goto _L11
    }

    private List a(boolean flag)
    {
        List list = null;
        if (h.size() <= 25) goto _L2; else goto _L1
_L1:
        h.clear();
_L4:
        return list;
_L2:
        g.clear();
        if (flag)
        {
            Collections.reverse(h);
        }
        List list1 = a(((List) (new ArrayList())), 0);
        list = list1;
_L5:
        if (flag)
        {
            Collections.reverse(h);
            return list;
        }
        if (true) goto _L4; else goto _L3
_L3:
        NotFoundException notfoundexception;
        notfoundexception;
        list = null;
          goto _L5
    }

    private static void a(int ai[])
    {
        int k = ai.length;
        for (int l = 0; l < k / 2; l++)
        {
            int i1 = ai[l];
            ai[l] = ai[-1 + (k - l)];
            ai[-1 + (k - l)] = i1;
        }

    }

    private boolean a()
    {
        boolean flag = true;
        a a1 = (a)g.get(0);
        DataCharacter datacharacter = a1.a();
        DataCharacter datacharacter1 = a1.b();
        if (datacharacter1 == null)
        {
            flag = false;
        } else
        {
            int k = datacharacter1.getChecksumPortion();
            int l = 2;
            int i1 = k;
            for (int j1 = ((flag) ? 1 : 0); j1 < g.size(); j1++)
            {
                a a2 = (a)g.get(j1);
                i1 += a2.a().getChecksumPortion();
                l++;
                DataCharacter datacharacter2 = a2.b();
                if (datacharacter2 != null)
                {
                    i1 += datacharacter2.getChecksumPortion();
                    l++;
                }
            }

            if (i1 % 211 + 211 * (l - 4) != datacharacter.getValue())
            {
                return false;
            }
        }
        return flag;
    }

    private static boolean a(FinderPattern finderpattern, boolean flag, boolean flag1)
    {
        return finderpattern.getValue() != 0 || !flag || !flag1;
    }

    public final Result decodeRow(int k, BitArray bitarray, Map map)
    {
        g.clear();
        j = false;
        Result result;
        try
        {
            result = a(a(k, bitarray));
        }
        catch (NotFoundException notfoundexception)
        {
            g.clear();
            j = true;
            return a(a(k, bitarray));
        }
        return result;
    }

    public final void reset()
    {
        g.clear();
        h.clear();
    }

}
