// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing.common.detector;

import com.google.zxing.NotFoundException;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.BitMatrix;

// Referenced classes of package com.google.zxing.common.detector:
//            MathUtils

public final class WhiteRectangleDetector
{

    private final BitMatrix a;
    private final int b;
    private final int c;
    private final int d;
    private final int e;
    private final int f;
    private final int g;

    public WhiteRectangleDetector(BitMatrix bitmatrix)
    {
        a = bitmatrix;
        b = bitmatrix.getHeight();
        c = bitmatrix.getWidth();
        d = -30 + c >> 1;
        e = 30 + c >> 1;
        g = -30 + b >> 1;
        f = 30 + b >> 1;
        if (g < 0 || d < 0 || f >= b || e >= c)
        {
            throw NotFoundException.getNotFoundInstance();
        } else
        {
            return;
        }
    }

    public WhiteRectangleDetector(BitMatrix bitmatrix, int i, int j, int k)
    {
        a = bitmatrix;
        b = bitmatrix.getHeight();
        c = bitmatrix.getWidth();
        int l = i >> 1;
        d = j - l;
        e = j + l;
        g = k - l;
        f = l + k;
        if (g < 0 || d < 0 || f >= b || e >= c)
        {
            throw NotFoundException.getNotFoundInstance();
        } else
        {
            return;
        }
    }

    private ResultPoint a(float f1, float f2, float f3, float f4)
    {
        int i = MathUtils.round(MathUtils.distance(f1, f2, f3, f4));
        float f5 = (f3 - f1) / (float)i;
        float f6 = (f4 - f2) / (float)i;
        for (int j = 0; j < i; j++)
        {
            int k = MathUtils.round(f1 + f5 * (float)j);
            int l = MathUtils.round(f2 + f6 * (float)j);
            if (a.get(k, l))
            {
                return new ResultPoint(k, l);
            }
        }

        return null;
    }

    private boolean a(int i, int j, int k, boolean flag)
    {
        if (!flag) goto _L2; else goto _L1
_L1:
        for (; i <= j; i++)
        {
            if (a.get(i, k))
            {
                return true;
            }
        }

          goto _L3
_L4:
        i++;
_L2:
        if (i > j)
        {
            break; /* Loop/switch isn't completed */
        }
        if (a.get(k, i))
        {
            return true;
        }
        if (true) goto _L4; else goto _L3
_L3:
        return false;
    }

    public final ResultPoint[] detect()
    {
        int i;
        int j;
        int k;
        int l;
        boolean flag;
        boolean flag1;
        i = d;
        j = e;
        k = g;
        l = f;
        flag = true;
        flag1 = false;
_L1:
        int i1;
        int k1;
        int l1;
        int i2;
        boolean flag2;
        int j2;
        ResultPoint resultpoint;
        if (!flag)
        {
            break MISSING_BLOCK_LABEL_816;
        }
        flag = false;
        boolean flag3 = true;
        do
        {
            if (!flag3 || j >= c)
            {
                break;
            }
            flag3 = a(k, l, j, false);
            if (flag3)
            {
                j++;
                flag = true;
            }
        } while (true);
        int k2;
        if (j >= c)
        {
            flag2 = true;
            i1 = j;
            int j4 = k;
            k1 = i;
            l1 = l;
            i2 = j4;
        } else
        {
            boolean flag4 = true;
            do
            {
                if (!flag4 || l >= b)
                {
                    break;
                }
                flag4 = a(i, j, l, true);
                if (flag4)
                {
                    l++;
                    flag = true;
                }
            } while (true);
            if (l >= b)
            {
                flag2 = true;
                i1 = j;
                int i4 = k;
                k1 = i;
                l1 = l;
                i2 = i4;
            } else
            {
                boolean flag5 = true;
                do
                {
                    if (!flag5 || i < 0)
                    {
                        break;
                    }
                    flag5 = a(k, l, i, false);
                    if (flag5)
                    {
                        i--;
                        flag = true;
                    }
                } while (true);
                if (i < 0)
                {
                    flag2 = true;
                    i1 = j;
                    int l3 = k;
                    k1 = i;
                    l1 = l;
                    i2 = l3;
                } else
                {
label0:
                    {
                        boolean flag6 = true;
                        do
                        {
                            if (!flag6 || k < 0)
                            {
                                break;
                            }
                            flag6 = a(i, j, k, true);
                            if (flag6)
                            {
                                k--;
                                flag = true;
                            }
                        } while (true);
                        if (k >= 0)
                        {
                            break label0;
                        }
                        flag2 = true;
                        i1 = j;
                        int k3 = k;
                        k1 = i;
                        l1 = l;
                        i2 = k3;
                    }
                }
            }
        }
_L2:
        if (flag2 || !flag1)
        {
            break MISSING_BLOCK_LABEL_812;
        }
        j2 = i1 - k1;
        resultpoint = null;
        k2 = 1;
        do
        {
            if (k2 >= j2)
            {
                break;
            }
            resultpoint = a(k1, l1 - k2, k1 + k2, l1);
            if (resultpoint != null)
            {
                break;
            }
            k2++;
        } while (true);
        break MISSING_BLOCK_LABEL_381;
        if (flag)
        {
            flag1 = true;
        }
          goto _L1
        ResultPoint resultpoint1 = resultpoint;
        if (resultpoint1 == null)
        {
            throw NotFoundException.getNotFoundInstance();
        }
        ResultPoint resultpoint2 = null;
        int l2 = 1;
        do
        {
            if (l2 >= j2)
            {
                break;
            }
            resultpoint2 = a(k1, i2 + l2, k1 + l2, i2);
            if (resultpoint2 != null)
            {
                break;
            }
            l2++;
        } while (true);
        ResultPoint resultpoint3 = resultpoint2;
        if (resultpoint3 == null)
        {
            throw NotFoundException.getNotFoundInstance();
        }
        ResultPoint resultpoint4 = null;
        int i3 = 1;
        do
        {
            if (i3 >= j2)
            {
                break;
            }
            resultpoint4 = a(i1, i2 + i3, i1 - i3, i2);
            if (resultpoint4 != null)
            {
                break;
            }
            i3++;
        } while (true);
        ResultPoint resultpoint5 = resultpoint4;
        if (resultpoint5 == null)
        {
            throw NotFoundException.getNotFoundInstance();
        }
        ResultPoint resultpoint6 = null;
        int j3 = 1;
        do
        {
            if (j3 >= j2)
            {
                break;
            }
            resultpoint6 = a(i1, l1 - j3, i1 - j3, l1);
            if (resultpoint6 != null)
            {
                break;
            }
            j3++;
        } while (true);
        if (resultpoint6 == null)
        {
            throw NotFoundException.getNotFoundInstance();
        }
        float f1 = resultpoint6.getX();
        float f2 = resultpoint6.getY();
        float f3 = resultpoint1.getX();
        float f4 = resultpoint1.getY();
        float f5 = resultpoint5.getX();
        float f6 = resultpoint5.getY();
        float f7 = resultpoint3.getX();
        float f8 = resultpoint3.getY();
        if (f1 < (float)c / 2.0F)
        {
            ResultPoint aresultpoint1[] = new ResultPoint[4];
            aresultpoint1[0] = new ResultPoint(f7 - 1.0F, f8 + 1.0F);
            aresultpoint1[1] = new ResultPoint(f3 + 1.0F, f4 + 1.0F);
            aresultpoint1[2] = new ResultPoint(f5 - 1.0F, f6 - 1.0F);
            aresultpoint1[3] = new ResultPoint(f1 + 1.0F, f2 - 1.0F);
            return aresultpoint1;
        } else
        {
            ResultPoint aresultpoint[] = new ResultPoint[4];
            aresultpoint[0] = new ResultPoint(f7 + 1.0F, f8 + 1.0F);
            aresultpoint[1] = new ResultPoint(f3 + 1.0F, f4 - 1.0F);
            aresultpoint[2] = new ResultPoint(f5 - 1.0F, f6 + 1.0F);
            aresultpoint[3] = new ResultPoint(f1 - 1.0F, f2 - 1.0F);
            return aresultpoint;
        }
        throw NotFoundException.getNotFoundInstance();
        i1 = j;
        int j1 = k;
        k1 = i;
        l1 = l;
        i2 = j1;
        flag2 = false;
          goto _L2
    }
}
