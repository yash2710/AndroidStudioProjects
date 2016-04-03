// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing.aztec.detector;

import com.google.zxing.NotFoundException;
import com.google.zxing.ResultPoint;
import com.google.zxing.aztec.AztecDetectorResult;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.GridSampler;
import com.google.zxing.common.detector.MathUtils;
import com.google.zxing.common.detector.WhiteRectangleDetector;
import com.google.zxing.common.reedsolomon.GenericGF;
import com.google.zxing.common.reedsolomon.ReedSolomonDecoder;
import com.google.zxing.common.reedsolomon.ReedSolomonException;

// Referenced classes of package com.google.zxing.aztec.detector:
//            a

public final class Detector
{

    private final BitMatrix a;
    private boolean b;
    private int c;
    private int d;
    private int e;
    private int f;

    public Detector(BitMatrix bitmatrix)
    {
        a = bitmatrix;
    }

    private int a(a a1, a a2)
    {
        float f1 = b(a1, a2);
        float f2 = (float)(a2.b() - a1.b()) / f1;
        float f3 = (float)(a2.c() - a1.c()) / f1;
        float f4 = a1.b();
        float f5 = a1.c();
        boolean flag = a.get(a1.b(), a1.c());
        int i = 0;
        float f6 = f4;
        float f7 = f5;
        for (int j = 0; (float)j < f1; j++)
        {
            f6 += f2;
            f7 += f3;
            if (a.get(MathUtils.round(f6), MathUtils.round(f7)) != flag)
            {
                i++;
            }
        }

        float f8 = (float)i / f1;
        if (f8 > 0.1F && f8 < 0.9F)
        {
            return 0;
        }
        boolean flag1;
        if (f8 <= 0.1F)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        return flag1 != flag ? -1 : 1;
    }

    private a a()
    {
        ResultPoint resultpoint;
        ResultPoint resultpoint1;
        ResultPoint resultpoint2;
        ResultPoint resultpoint3;
        int k;
        int l;
        ResultPoint resultpoint4;
        ResultPoint resultpoint5;
        ResultPoint resultpoint6;
        ResultPoint resultpoint7;
        try
        {
            ResultPoint aresultpoint1[] = (new WhiteRectangleDetector(a)).detect();
            resultpoint = aresultpoint1[0];
            resultpoint1 = aresultpoint1[1];
            resultpoint2 = aresultpoint1[2];
            resultpoint3 = aresultpoint1[3];
        }
        catch (NotFoundException notfoundexception)
        {
            int i = a.getWidth() / 2;
            int j = a.getHeight() / 2;
            resultpoint = a(new a(i + 7, j - 7), false, 1, -1).a();
            resultpoint1 = a(new a(i + 7, j + 7), false, 1, 1).a();
            resultpoint2 = a(new a(i - 7, j + 7), false, -1, 1).a();
            resultpoint3 = a(new a(i - 7, j - 7), false, -1, -1).a();
        }
        k = MathUtils.round((resultpoint.getX() + resultpoint3.getX() + resultpoint1.getX() + resultpoint2.getX()) / 4F);
        l = MathUtils.round((resultpoint.getY() + resultpoint3.getY() + resultpoint1.getY() + resultpoint2.getY()) / 4F);
        try
        {
            ResultPoint aresultpoint[] = (new WhiteRectangleDetector(a, 15, k, l)).detect();
            resultpoint4 = aresultpoint[0];
            resultpoint5 = aresultpoint[1];
            resultpoint6 = aresultpoint[2];
            resultpoint7 = aresultpoint[3];
        }
        catch (NotFoundException notfoundexception1)
        {
            resultpoint4 = a(new a(k + 7, l - 7), false, 1, -1).a();
            resultpoint5 = a(new a(k + 7, l + 7), false, 1, 1).a();
            resultpoint6 = a(new a(k - 7, l + 7), false, -1, 1).a();
            resultpoint7 = a(new a(k - 7, l - 7), false, -1, -1).a();
        }
        return new a(MathUtils.round((resultpoint4.getX() + resultpoint7.getX() + resultpoint5.getX() + resultpoint6.getX()) / 4F), MathUtils.round((resultpoint4.getY() + resultpoint7.getY() + resultpoint5.getY() + resultpoint6.getY()) / 4F));
    }

    private a a(a a1, boolean flag, int i, int j)
    {
        int k = i + a1.b();
        int l;
        for (l = j + a1.c(); a(k, l) && a.get(k, l) == flag; l += j)
        {
            k += i;
        }

        int i1 = k - i;
        int j1;
        for (j1 = l - j; a(i1, j1) && a.get(i1, j1) == flag; i1 += i) { }
        int k1 = i1 - i;
        int l1;
        for (l1 = j1; a(k1, l1) && a.get(k1, l1) == flag; l1 += j) { }
        return new a(k1, l1 - j);
    }

    private static void a(boolean aflag[], boolean flag)
    {
        byte byte0;
        byte byte1;
        int i;
        int ai[];
        if (flag)
        {
            byte0 = 7;
            byte1 = 2;
        } else
        {
            byte0 = 10;
            byte1 = 4;
        }
        i = byte0 - byte1;
        ai = new int[byte0];
        for (int j = 0; j < byte0; j++)
        {
            int k1 = 1;
            int l1 = 1;
            for (; k1 <= 4; k1++)
            {
                if (aflag[(4 + j * 4) - k1])
                {
                    ai[j] = l1 + ai[j];
                }
                l1 <<= 1;
            }

        }

        int k;
        try
        {
            (new ReedSolomonDecoder(GenericGF.AZTEC_PARAM)).decode(ai, i);
        }
        catch (ReedSolomonException reedsolomonexception)
        {
            throw NotFoundException.getNotFoundInstance();
        }
        for (k = 0; k < byte1; k++)
        {
            int l = 1;
            int i1 = 1;
            while (l <= 4) 
            {
                int j1 = (4 + (k << 2)) - l;
                boolean flag1;
                if ((i1 & ai[k]) == i1)
                {
                    flag1 = true;
                } else
                {
                    flag1 = false;
                }
                aflag[j1] = flag1;
                i1 <<= 1;
                l++;
            }
        }

    }

    private boolean a(int i, int j)
    {
        return i >= 0 && i < a.getWidth() && j > 0 && j < a.getHeight();
    }

    private boolean[] a(a a1, a a2, int i)
    {
        boolean aflag[] = new boolean[i];
        float f1 = b(a1, a2);
        float f2 = f1 / (float)(i - 1);
        float f3 = (f2 * (float)(a2.b() - a1.b())) / f1;
        float f4 = (f2 * (float)(a2.c() - a1.c())) / f1;
        float f5 = a1.b();
        float f6 = a1.c();
        for (int j = 0; j < i; j++)
        {
            aflag[j] = a.get(MathUtils.round(f5), MathUtils.round(f6));
            f5 += f3;
            f6 += f4;
        }

        return aflag;
    }

    private static float b(a a1, a a2)
    {
        return MathUtils.distance(a1.b(), a1.c(), a2.b(), a2.c());
    }

    public final AztecDetectorResult detect()
    {
        a a1 = a();
        boolean flag = true;
        e = 1;
        a a2 = a1;
        a a3 = a1;
        a a4 = a1;
        while (e < 9) 
        {
            a a5 = a(a4, flag, 1, -1);
            a a6 = a(a3, flag, 1, 1);
            a a7 = a(a2, flag, -1, 1);
            a a8 = a(a1, flag, -1, -1);
            if (e > 2)
            {
                float f15 = (b(a8, a5) * (float)e) / (b(a1, a4) * (float)(2 + e));
                if ((double)f15 < 0.75D || (double)f15 > 1.25D)
                {
                    break;
                }
                a a9 = new a(-3 + a5.b(), 3 + a5.c());
                a a10 = new a(-3 + a6.b(), -3 + a6.c());
                a a11 = new a(3 + a7.b(), -3 + a7.c());
                a a12 = new a(3 + a8.b(), 3 + a8.c());
                int k10 = a(a12, a9);
                boolean flag2;
                if (k10 != 0 && a(a9, a10) == k10 && a(a10, a11) == k10 && a(a11, a12) == k10)
                {
                    flag2 = true;
                } else
                {
                    flag2 = false;
                }
                if (!flag2)
                {
                    break;
                }
            }
            if (!flag)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            e = 1 + e;
            a1 = a8;
            a2 = a7;
            a3 = a6;
            a4 = a5;
        }
        if (e != 5 && e != 7)
        {
            throw NotFoundException.getNotFoundInstance();
        }
        boolean flag1;
        float f1;
        int i;
        int j;
        int k;
        int l;
        int i1;
        int j1;
        int k1;
        int l1;
        int i2;
        int j2;
        int k2;
        int l2;
        if (e == 5)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        b = flag1;
        f1 = 1.5F / (float)(-3 + 2 * e);
        i = a4.b() - a2.b();
        j = a4.c() - a2.c();
        k = MathUtils.round((float)a2.b() - f1 * (float)i);
        l = MathUtils.round((float)a2.c() - f1 * (float)j);
        i1 = MathUtils.round((float)a4.b() + f1 * (float)i);
        j1 = MathUtils.round((float)a4.c() + f1 * (float)j);
        k1 = a3.b() - a1.b();
        l1 = a3.c() - a1.c();
        i2 = MathUtils.round((float)a1.b() - f1 * (float)k1);
        j2 = MathUtils.round((float)a1.c() - f1 * (float)l1);
        k2 = MathUtils.round((float)a3.b() + f1 * (float)k1);
        l2 = MathUtils.round((float)a3.c() + f1 * (float)l1);
        if (!a(i1, j1) || !a(k2, l2) || !a(k, l) || !a(i2, j2))
        {
            throw NotFoundException.getNotFoundInstance();
        }
        a aa[] = new a[4];
        aa[0] = new a(i1, j1);
        aa[1] = new a(k2, l2);
        aa[2] = new a(k, l);
        aa[3] = new a(i2, j2);
        int i3 = 2 * e;
        boolean aflag[] = a(aa[0], aa[1], i3 + 1);
        boolean aflag1[] = a(aa[1], aa[2], i3 + 1);
        boolean aflag2[] = a(aa[2], aa[3], i3 + 1);
        boolean aflag3[] = a(aa[3], aa[0], i3 + 1);
        if (aflag[0] && aflag[i3])
        {
            f = 0;
        } else
        if (aflag1[0] && aflag1[i3])
        {
            f = 1;
        } else
        if (aflag2[0] && aflag2[i3])
        {
            f = 2;
        } else
        if (aflag3[0] && aflag3[i3])
        {
            f = 3;
        } else
        {
            throw NotFoundException.getNotFoundInstance();
        }
        boolean aflag5[];
        if (b)
        {
            boolean aflag6[] = new boolean[28];
            for (int i10 = 0; i10 < 7; i10++)
            {
                aflag6[i10] = aflag[i10 + 2];
                aflag6[i10 + 7] = aflag1[i10 + 2];
                aflag6[i10 + 14] = aflag2[i10 + 2];
                aflag6[i10 + 21] = aflag3[i10 + 2];
            }

            aflag5 = new boolean[28];
            for (int j10 = 0; j10 < 28; j10++)
            {
                aflag5[j10] = aflag6[(j10 + 7 * f) % 28];
            }

        } else
        {
            boolean aflag4[] = new boolean[40];
            for (int j3 = 0; j3 < 11; j3++)
            {
                if (j3 < 5)
                {
                    aflag4[j3] = aflag[j3 + 2];
                    aflag4[j3 + 10] = aflag1[j3 + 2];
                    aflag4[j3 + 20] = aflag2[j3 + 2];
                    aflag4[j3 + 30] = aflag3[j3 + 2];
                }
                if (j3 > 5)
                {
                    aflag4[j3 - 1] = aflag[j3 + 2];
                    aflag4[j3 + 9] = aflag1[j3 + 2];
                    aflag4[j3 + 19] = aflag2[j3 + 2];
                    aflag4[j3 + 29] = aflag3[j3 + 2];
                }
            }

            aflag5 = new boolean[40];
            for (int k3 = 0; k3 < 40; k3++)
            {
                aflag5[k3] = aflag4[(k3 + 10 * f) % 40];
            }

        }
        a(aflag5, b);
        byte byte0;
        byte byte1;
        int l3;
        if (b)
        {
            byte0 = 2;
            byte1 = 6;
        } else
        {
            byte0 = 5;
            byte1 = 11;
        }
        for (l3 = 0; l3 < byte0; l3++)
        {
            c = c << 1;
            if (aflag5[l3])
            {
                c = 1 + c;
            }
        }

        for (int i4 = byte0; i4 < byte0 + byte1; i4++)
        {
            d = d << 1;
            if (aflag5[i4])
            {
                d = 1 + d;
            }
        }

        c = 1 + c;
        d = 1 + d;
        int j4 = 2 * c;
        int k4;
        float f2;
        int l4;
        int i5;
        int j5;
        int k5;
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
        if (c > 4)
        {
            k4 = 1;
        } else
        {
            k4 = 0;
        }
        f2 = (float)(k4 + j4 + (-4 + c) / 8) / (2.0F * (float)e);
        l4 = aa[0].b() - aa[2].b();
        if (l4 > 0)
        {
            i5 = 1;
        } else
        {
            i5 = -1;
        }
        j5 = l4 + i5;
        k5 = aa[0].c() - aa[2].c();
        if (k5 > 0)
        {
            l5 = 1;
        } else
        {
            l5 = -1;
        }
        i6 = l5 + k5;
        j6 = MathUtils.round((float)aa[2].b() - f2 * (float)j5);
        k6 = MathUtils.round((float)aa[2].c() - f2 * (float)i6);
        l6 = MathUtils.round((float)aa[0].b() + f2 * (float)j5);
        i7 = MathUtils.round((float)aa[0].c() + f2 * (float)i6);
        j7 = aa[1].b() - aa[3].b();
        if (j7 > 0)
        {
            k7 = 1;
        } else
        {
            k7 = -1;
        }
        l7 = j7 + k7;
        i8 = aa[1].c() - aa[3].c();
        if (i8 > 0)
        {
            j8 = 1;
        } else
        {
            j8 = -1;
        }
        k8 = j8 + i8;
        l8 = MathUtils.round((float)aa[3].b() - f2 * (float)l7);
        i9 = MathUtils.round((float)aa[3].c() - f2 * (float)k8);
        j9 = MathUtils.round((float)aa[1].b() + f2 * (float)l7);
        k9 = MathUtils.round((float)aa[1].c() + f2 * (float)k8);
        if (!a(l6, i7) || !a(j9, k9) || !a(j6, k6) || !a(l8, i9))
        {
            throw NotFoundException.getNotFoundInstance();
        }
        ResultPoint aresultpoint[] = new ResultPoint[4];
        aresultpoint[0] = new ResultPoint(l6, i7);
        aresultpoint[1] = new ResultPoint(j9, k9);
        aresultpoint[2] = new ResultPoint(j6, k6);
        aresultpoint[3] = new ResultPoint(l8, i9);
        BitMatrix bitmatrix = a;
        ResultPoint resultpoint = aresultpoint[f % 4];
        ResultPoint resultpoint1 = aresultpoint[(3 + f) % 4];
        ResultPoint resultpoint2 = aresultpoint[(2 + f) % 4];
        ResultPoint resultpoint3 = aresultpoint[(1 + f) % 4];
        int l9;
        GridSampler gridsampler;
        float f3;
        float f4;
        float f5;
        float f6;
        float f7;
        float f8;
        float f9;
        float f10;
        float f11;
        float f12;
        float f13;
        float f14;
        if (b)
        {
            l9 = 11 + 4 * c;
        } else
        if (c <= 4)
        {
            l9 = 15 + 4 * c;
        } else
        {
            l9 = 15 + (4 * c + 2 * (1 + (-4 + c) / 8));
        }
        gridsampler = GridSampler.getInstance();
        f3 = (float)l9 - 0.5F;
        f4 = (float)l9 - 0.5F;
        f5 = (float)l9 - 0.5F;
        f6 = (float)l9 - 0.5F;
        f7 = resultpoint.getX();
        f8 = resultpoint.getY();
        f9 = resultpoint3.getX();
        f10 = resultpoint3.getY();
        f11 = resultpoint2.getX();
        f12 = resultpoint2.getY();
        f13 = resultpoint1.getX();
        f14 = resultpoint1.getY();
        return new AztecDetectorResult(gridsampler.sampleGrid(bitmatrix, l9, l9, 0.5F, 0.5F, f3, 0.5F, f4, f5, 0.5F, f6, f7, f8, f9, f10, f11, f12, f13, f14), aresultpoint, b, d, c);
    }
}
