// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing.pdf417.decoder;

import com.google.zxing.ChecksumException;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.DecoderResult;
import com.google.zxing.pdf417.PDF417Common;
import com.google.zxing.pdf417.decoder.ec.ErrorCorrection;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Formatter;
import java.util.List;

// Referenced classes of package com.google.zxing.pdf417.decoder:
//            e, j, c, k, 
//            d, h, a, i, 
//            b

public final class PDF417ScanningDecoder
{

    private static final ErrorCorrection a = new ErrorCorrection();

    private PDF417ScanningDecoder()
    {
    }

    private static DecoderResult a(int l, int ai[], int ai1[], int ai2[], int ai3[][])
    {
        int ai4[];
        int i1;
        ai4 = new int[ai2.length];
        i1 = 100;
_L6:
        int j1 = i1 - 1;
        if (i1 <= 0) goto _L2; else goto _L1
_L1:
        for (int k1 = 0; k1 < ai4.length; k1++)
        {
            ai[ai2[k1]] = ai3[k1][ai4[k1]];
        }

        ChecksumException checksumexception;
        if (ai.length == 0)
        {
            throw FormatException.getFormatInstance();
        }
        int i2 = 1 << l + 1;
        if (ai1 == null) goto _L4; else goto _L3
_L3:
        if (ai1.length > 3 + i2 / 2) goto _L5; else goto _L4
_L5:
        throw ChecksumException.getChecksumInstance();
_L8:
        int j2;
        int k2;
        j2 = a.decode(ai, i2, ai1);
        if (ai.length < 4)
        {
            throw FormatException.getFormatInstance();
        }
        k2 = ai[0];
        if (k2 > ai.length)
        {
            throw FormatException.getFormatInstance();
        }
        if (k2 != 0)
        {
            break MISSING_BLOCK_LABEL_163;
        }
        if (i2 >= ai.length)
        {
            break MISSING_BLOCK_LABEL_196;
        }
        ai[0] = ai.length - i2;
        DecoderResult decoderresult;
        decoderresult = e.a(ai, String.valueOf(l));
        decoderresult.setErrorsCorrected(Integer.valueOf(j2));
        decoderresult.setErasures(Integer.valueOf(ai1.length));
        return decoderresult;
        int l1;
        try
        {
            throw FormatException.getFormatInstance();
        }
        // Misplaced declaration of an exception variable
        catch (ChecksumException checksumexception)
        {
            if (ai4.length == 0)
            {
                throw ChecksumException.getChecksumInstance();
            }
        }
        l1 = 0;
_L7:
label0:
        {
            if (l1 >= ai4.length)
            {
                break MISSING_BLOCK_LABEL_272;
            }
            if (ai4[l1] >= -1 + ai3[l1].length)
            {
                break label0;
            }
            ai4[l1] = 1 + ai4[l1];
            i1 = j1;
        }
          goto _L6
        ai4[l1] = 0;
        if (l1 == -1 + ai4.length)
        {
            throw ChecksumException.getChecksumInstance();
        }
        l1++;
          goto _L7
        i1 = j1;
          goto _L6
_L2:
        throw ChecksumException.getChecksumInstance();
_L4:
        if (i2 >= 0 && i2 <= 512) goto _L8; else goto _L5
    }

    private static c a(j j1)
    {
        if (j1 == null)
        {
            return null;
        }
        int ai[] = j1.c();
        int l = ai.length;
        int i1 = -1;
        for (int k1 = 0; k1 < l;)
        {
            int l4 = Math.max(i1, ai[k1]);
            k1++;
            i1 = l4;
        }

        int l1 = ai.length;
        int i2 = 0;
        int j2 = 0;
        do
        {
            if (i2 >= l1)
            {
                break;
            }
            int k4 = ai[i2];
            j2 += i1 - k4;
            if (k4 > 0)
            {
                break;
            }
            i2++;
        } while (true);
        d ad[] = j1.b();
        int k2 = j2;
        for (int l2 = 0; k2 > 0 && ad[l2] == null; l2++)
        {
            k2--;
        }

        int i3 = -1 + ai.length;
        int j3 = 0;
        int k3 = i3;
        do
        {
            if (k3 < 0)
            {
                break;
            }
            j3 += i1 - ai[k3];
            if (ai[k3] > 0)
            {
                break;
            }
            k3--;
        } while (true);
        int l3 = -1 + ad.length;
        int i4 = j3;
        for (int j4 = l3; i4 > 0 && ad[j4] == null; j4--)
        {
            i4--;
        }

        return j1.a().a(k2, i4, j1.e());
    }

    private static d a(BitMatrix bitmatrix, int l, int i1, boolean flag, int j1, int k1, int l1, int i2)
    {
        int j2;
        int k2;
        boolean flag1;
        int l2;
        int ai[];
        int i3;
        int k3;
        boolean flag2;
        int l5;
        byte byte0;
        if (flag)
        {
            byte0 = -1;
        } else
        {
            byte0 = 1;
        }
        j2 = 0;
        k2 = byte0;
        flag1 = flag;
        l2 = j1;
_L8:
        if (j2 >= 2) goto _L2; else goto _L1
_L1:
        l5 = l2;
_L7:
        if ((!flag1 || l5 < l) && (flag1 || l5 >= i1) || flag1 != bitmatrix.get(l5, k1)) goto _L4; else goto _L3
_L3:
        if (Math.abs(j1 - l5) <= 2) goto _L6; else goto _L5
_L5:
        ai = new int[8];
        i3 = 0;
        int j3;
        boolean flag5;
        if (flag)
        {
            j3 = 1;
        } else
        {
            j3 = -1;
        }
        k3 = j1;
        flag2 = flag;
_L9:
        while ((flag && k3 < i1 || !flag && k3 >= l) && i3 < 8) 
        {
            if (bitmatrix.get(k3, k1) == flag2)
            {
                ai[i3] = 1 + ai[i3];
                k3 += j3;
            } else
            {
                int k5 = i3 + 1;
                boolean flag4;
                if (!flag2)
                {
                    flag4 = true;
                } else
                {
                    flag4 = false;
                }
                flag2 = flag4;
                i3 = k5;
            }
        }
        break MISSING_BLOCK_LABEL_256;
_L6:
        l5 += k2;
          goto _L7
_L4:
        k2 = -k2;
        if (!flag1)
        {
            flag5 = true;
        } else
        {
            flag5 = false;
        }
        j2++;
        flag1 = flag5;
        l2 = l5;
          goto _L8
_L2:
        j1 = l2;
          goto _L5
        if (i3 != 8 && ((!flag || k3 != i1) && (flag || k3 != l) || i3 != 7))
        {
            ai = null;
        }
        if (ai == null)
        {
            return null;
        }
        int l3 = PDF417Common.getBitCountSum(ai);
        int k4;
        boolean flag3;
        if (flag)
        {
            k4 = j1 + l3;
        } else
        {
            for (int i4 = 0; i4 < ai.length >> 1; i4++)
            {
                int j5 = ai[i4];
                ai[i4] = ai[(-1 + ai.length) - i4];
                ai[(-1 + ai.length) - i4] = j5;
            }

            int j4 = j1 - l3;
            k4 = j1;
            j1 = j4;
        }
        if (l1 - 2 <= l3 && l3 <= i2 + 2)
        {
            flag3 = true;
        } else
        {
            flag3 = false;
        }
        if (!flag3)
        {
            return null;
        }
        int l4 = k.a(ai);
        int i5 = PDF417Common.getCodeword(l4);
        if (i5 == -1)
        {
            return null;
        }
        int ai1[] = a(l4);
        return new d(j1, k4, (9 + (((ai1[0] - ai1[2]) + ai1[4]) - ai1[6])) % 9, i5);
          goto _L9
    }

    private static j a(BitMatrix bitmatrix, c c1, ResultPoint resultpoint, boolean flag, int l, int i1)
    {
        j j1 = new j(c1, flag);
        for (int k1 = 0; k1 < 2; k1++)
        {
            int l1;
            int i2;
            int j2;
            if (k1 == 0)
            {
                l1 = 1;
            } else
            {
                l1 = -1;
            }
            i2 = (int)resultpoint.getX();
            j2 = (int)resultpoint.getY();
            while (j2 <= c1.d() && j2 >= c1.c()) 
            {
                d d1 = a(bitmatrix, 0, bitmatrix.getWidth(), flag, i2, j2, l, i1);
                if (d1 != null)
                {
                    j1.a(j2, d1);
                    if (flag)
                    {
                        i2 = d1.d();
                    } else
                    {
                        i2 = d1.e();
                    }
                }
                j2 += l1;
            }
        }

        return j1;
    }

    private static boolean a(h h1, int l)
    {
        return l >= 0 && l <= 1 + h1.b();
    }

    private static int[] a(int l)
    {
        int ai[] = new int[8];
        int i1 = 0;
        int j1 = 7;
        do
        {
            if ((l & 1) != i1)
            {
                i1 = l & 1;
                if (--j1 < 0)
                {
                    break;
                }
            }
            ai[j1] = 1 + ai[j1];
            l >>= 1;
        } while (true);
        return ai;
    }

    public static DecoderResult decode(BitMatrix bitmatrix, ResultPoint resultpoint, ResultPoint resultpoint1, ResultPoint resultpoint2, ResultPoint resultpoint3, int l, int i1)
    {
        j j1;
        int k1;
        c c2;
        h h1;
        j j2;
        c c1 = new c(bitmatrix, resultpoint, resultpoint1, resultpoint2, resultpoint3);
        j1 = null;
        k1 = 0;
        c2 = c1;
        h1 = null;
        j2 = null;
_L8:
        h h2;
        j j3;
        if (k1 >= 2)
        {
            break; /* Loop/switch isn't completed */
        }
        int l1;
        boolean flag;
        int i2;
        int k2;
        int l2;
        b ab[][];
        int i3;
        int k3;
        i ai[];
        int l3;
        int i4;
        int ai1[];
        int j4;
        ArrayList arraylist;
        int ai2[];
        ArrayList arraylist1;
        ArrayList arraylist2;
        int k4;
        int ai3[][];
        int l4;
        int i5;
        int ai4[];
        int j5;
        i k5;
        d ad[];
        int l5;
        int i6;
        d d1;
        int j6;
        int k6;
        boolean flag1;
        Object obj;
        int l6;
        int i7;
        int j7;
        boolean flag2;
        d d2;
        d d3;
        int k7;
        int l7;
        int i8;
        int j8;
        d d4;
        int k8;
        d ad1[];
        int l8;
        int i9;
        d d5;
        int j9;
        j j10;
        j j11;
        a a1;
        c c3;
        a a2;
        a a3;
        if (resultpoint != null)
        {
            j10 = a(bitmatrix, c2, resultpoint, true, l, i1);
        } else
        {
            j10 = j2;
        }
        if (resultpoint2 != null)
        {
            j11 = a(bitmatrix, c2, resultpoint2, false, l, i1);
        } else
        {
            j11 = j1;
        }
        if (j10 != null || j11 != null) goto _L2; else goto _L1
_L1:
        h1 = null;
_L5:
        if (h1 == null)
        {
            throw NotFoundException.getNotFoundInstance();
        }
        break MISSING_BLOCK_LABEL_264;
_L2:
        if (j10 != null && j10.d() != null) goto _L4; else goto _L3
_L3:
        if (j11 == null)
        {
            a1 = null;
        } else
        {
            a1 = j11.d();
        }
_L6:
        if (a1 == null)
        {
            h1 = null;
        } else
        {
            h1 = new h(a1, c.a(a(j10), a(j11)));
        }
        if (true) goto _L5; else goto _L4
_L4:
        if (j11 == null || j11.d() == null)
        {
            if (j10 == null)
            {
                a1 = null;
            } else
            {
                a1 = j10.d();
            }
        } else
        {
            a2 = j10.d();
            a3 = j11.d();
            if (a2.a() != a3.a() && a2.b() != a3.b() && a2.c() != a3.c())
            {
                a1 = null;
            } else
            {
                a1 = a2;
            }
        }
          goto _L6
label0:
        {
            if (k1 != 0 || h1.e().c() >= c2.c() && h1.e().d() <= c2.d())
            {
                break label0;
            }
            c3 = h1.e();
            k1++;
            j1 = j11;
            j2 = j10;
            c2 = c3;
        }
        if (true) goto _L8; else goto _L7
        h1.setBoundingBox(c2);
        h2 = h1;
        j3 = j11;
        j2 = j10;
_L17:
        l1 = 1 + h2.b();
        h2.a(0, j2);
        h2.a(l1, j3);
        if (j2 != null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        i2 = 1;
        k2 = i1;
        l2 = l;
_L14:
        if (i2 > l1) goto _L10; else goto _L9
_L9:
        if (flag)
        {
            k6 = i2;
        } else
        {
            k6 = l1 - i2;
        }
        if (h2.a(k6) != null)
        {
            continue; /* Loop/switch isn't completed */
        }
        if (k6 == 0 || k6 == l1)
        {
            if (k6 == 0)
            {
                flag1 = true;
            } else
            {
                flag1 = false;
            }
            obj = new j(c2, flag1);
        } else
        {
            obj = new i(c2);
        }
        h2.a(k6, ((i) (obj)));
        l6 = c2.c();
        i7 = -1;
        if (l6 > c2.d())
        {
            continue; /* Loop/switch isn't completed */
        }
        if (flag)
        {
            j7 = 1;
        } else
        {
            j7 = -1;
        }
        flag2 = a(h2, k6 - j7);
        d2 = null;
        if (flag2)
        {
            d2 = h2.a(k6 - j7).c(l6);
        }
        if (d2 != null)
        {
            if (flag)
            {
                i8 = d2.e();
            } else
            {
                i8 = d2.d();
            }
        } else
        {
            d3 = h2.a(k6).a(l6);
            if (d3 != null)
            {
                if (flag)
                {
                    i8 = d3.d();
                } else
                {
                    i8 = d3.e();
                }
            } else
            {
label1:
                {
                    if (a(h2, k6 - j7))
                    {
                        d3 = h2.a(k6 - j7).a(l6);
                    }
                    if (d3 == null)
                    {
                        break label1;
                    }
                    if (flag)
                    {
                        i8 = d3.e();
                    } else
                    {
                        i8 = d3.d();
                    }
                }
            }
        }
_L11:
        if (i8 < 0 || i8 > c2.b())
        {
            if (i7 == -1)
            {
                break MISSING_BLOCK_LABEL_1492;
            }
            i8 = i7;
        }
        d4 = a(bitmatrix, c2.a(), c2.b(), flag, i8, l6, l2, k2);
        if (d4 == null)
        {
            break MISSING_BLOCK_LABEL_1492;
        }
        ((i) (obj)).a(l6, d4);
        l2 = Math.min(l2, d4.c());
        k2 = Math.max(k2, d4.c());
        j8 = i8;
_L15:
        l6++;
        i7 = j8;
        break MISSING_BLOCK_LABEL_468;
        k7 = 0;
        l7 = k6;
_L13:
        if (!a(h2, l7 - j7))
        {
            break MISSING_BLOCK_LABEL_928;
        }
        k8 = l7 - j7;
        ad1 = h2.a(k8).b();
        l8 = ad1.length;
        i9 = 0;
_L12:
label2:
        {
            if (i9 >= l8)
            {
                break MISSING_BLOCK_LABEL_918;
            }
            d5 = ad1[i9];
            if (d5 == null)
            {
                break label2;
            }
            if (flag)
            {
                j9 = d5.e();
            } else
            {
                j9 = d5.d();
            }
            i8 = j9 + j7 * k7 * (d5.e() - d5.d());
        }
          goto _L11
        i9++;
          goto _L12
        k7++;
        l7 = k8;
          goto _L13
        if (flag)
        {
            i8 = h2.e().a();
        } else
        {
            i8 = h2.e().b();
        }
          goto _L11
        i2++;
          goto _L14
_L10:
        ab = (b[][])Array.newInstance(com/google/zxing/pdf417/decoder/b, new int[] {
            h2.c(), 2 + h2.b()
        });
        for (i3 = 0; i3 < ab.length; i3++)
        {
            for (j6 = 0; j6 < ab[i3].length; j6++)
            {
                ab[i3][j6] = new b();
            }

        }

        k3 = -1;
        ai = h2.a();
        l3 = ai.length;
        for (i4 = 0; i4 < l3; i4++)
        {
            k5 = ai[i4];
            k3++;
            if (k5 == null)
            {
                continue;
            }
            ad = k5.b();
            l5 = ad.length;
            for (i6 = 0; i6 < l5; i6++)
            {
                d1 = ad[i6];
                if (d1 != null && d1.h() != -1)
                {
                    ab[d1.h()][k3].a(d1.g());
                }
            }

        }

        ai1 = ab[0][1].a();
        j4 = h2.b() * h2.c() - (2 << h2.d());
        if (ai1.length == 0)
        {
            if (j4 <= 0 || j4 > 928)
            {
                throw NotFoundException.getNotFoundInstance();
            }
            ab[0][1].a(j4);
        } else
        if (ai1[0] != j4)
        {
            ab[0][1].a(j4);
        }
        arraylist = new ArrayList();
        ai2 = new int[h2.c() * h2.b()];
        arraylist1 = new ArrayList();
        arraylist2 = new ArrayList();
        for (k4 = 0; k4 < h2.c(); k4++)
        {
            i5 = 0;
            while (i5 < h2.b()) 
            {
                ai4 = ab[k4][i5 + 1].a();
                j5 = i5 + k4 * h2.b();
                if (ai4.length == 0)
                {
                    arraylist.add(Integer.valueOf(j5));
                } else
                if (ai4.length == 1)
                {
                    ai2[j5] = ai4[0];
                } else
                {
                    arraylist2.add(Integer.valueOf(j5));
                    arraylist1.add(ai4);
                }
                i5++;
            }
        }

        ai3 = new int[arraylist1.size()][];
        for (l4 = 0; l4 < ai3.length; l4++)
        {
            ai3[l4] = (int[])arraylist1.get(l4);
        }

        return a(h2.d(), ai2, PDF417Common.toIntArray(arraylist), PDF417Common.toIntArray(arraylist2), ai3);
        j8 = i7;
          goto _L15
_L7:
        h2 = h1;
        j3 = j1;
        if (true) goto _L17; else goto _L16
_L16:
    }

    public static String toString(b ab[][])
    {
        Formatter formatter = new Formatter();
        for (int l = 0; l < ab.length; l++)
        {
            Object aobj[] = new Object[1];
            aobj[0] = Integer.valueOf(l);
            formatter.format("Row %2d: ", aobj);
            int i1 = 0;
            while (i1 < ab[l].length) 
            {
                b b1 = ab[l][i1];
                if (b1.a().length == 0)
                {
                    formatter.format("        ", null);
                } else
                {
                    Object aobj1[] = new Object[2];
                    aobj1[0] = Integer.valueOf(b1.a()[0]);
                    aobj1[1] = b1.getConfidence(b1.a()[0]);
                    formatter.format("%4d(%2d)", aobj1);
                }
                i1++;
            }
            formatter.format("\n", new Object[0]);
        }

        String s = formatter.toString();
        formatter.close();
        return s;
    }

}
