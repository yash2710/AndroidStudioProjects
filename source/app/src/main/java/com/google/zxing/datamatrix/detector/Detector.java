// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing.datamatrix.detector;

import com.google.zxing.NotFoundException;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.DetectorResult;
import com.google.zxing.common.GridSampler;
import com.google.zxing.common.detector.MathUtils;
import com.google.zxing.common.detector.WhiteRectangleDetector;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

// Referenced classes of package com.google.zxing.datamatrix.detector:
//            a, b

public final class Detector
{

    private final BitMatrix a;
    private final WhiteRectangleDetector b;

    public Detector(BitMatrix bitmatrix)
    {
        a = bitmatrix;
        b = new WhiteRectangleDetector(bitmatrix);
    }

    private static int a(ResultPoint resultpoint, ResultPoint resultpoint1)
    {
        return MathUtils.round(ResultPoint.distance(resultpoint, resultpoint1));
    }

    private static BitMatrix a(BitMatrix bitmatrix, ResultPoint resultpoint, ResultPoint resultpoint1, ResultPoint resultpoint2, ResultPoint resultpoint3, int i, int j)
    {
        return GridSampler.getInstance().sampleGrid(bitmatrix, i, j, 0.5F, 0.5F, (float)i - 0.5F, 0.5F, (float)i - 0.5F, (float)j - 0.5F, 0.5F, (float)j - 0.5F, resultpoint.getX(), resultpoint.getY(), resultpoint3.getX(), resultpoint3.getY(), resultpoint2.getX(), resultpoint2.getY(), resultpoint1.getX(), resultpoint1.getY());
    }

    private static void a(Map map, ResultPoint resultpoint)
    {
        Integer integer = (Integer)map.get(resultpoint);
        int i;
        if (integer == null)
        {
            i = 1;
        } else
        {
            i = 1 + integer.intValue();
        }
        map.put(resultpoint, Integer.valueOf(i));
    }

    private boolean a(ResultPoint resultpoint)
    {
        return resultpoint.getX() >= 0.0F && resultpoint.getX() < (float)a.getWidth() && resultpoint.getY() > 0.0F && resultpoint.getY() < (float)a.getHeight();
    }

    private a b(ResultPoint resultpoint, ResultPoint resultpoint1)
    {
        int i = (int)resultpoint.getX();
        int j = (int)resultpoint.getY();
        int k = (int)resultpoint1.getX();
        int l = (int)resultpoint1.getY();
        boolean flag;
        int k1;
        int l1;
        int i2;
        int j2;
        int k2;
        int l2;
        BitMatrix bitmatrix;
        int i3;
        int j3;
        boolean flag1;
        int k3;
        int l3;
        if (Math.abs(l - j) > Math.abs(k - i))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            int i1 = l;
            l = k;
            k = i1;
            int j1 = j;
            j = i;
            i = j1;
        }
        k1 = Math.abs(l - j);
        l1 = Math.abs(k - i);
        i2 = -k1 >> 1;
        if (i < k)
        {
            j2 = 1;
        } else
        {
            j2 = -1;
        }
        if (j < l)
        {
            k2 = 1;
        } else
        {
            k2 = -1;
        }
        l2 = 0;
        bitmatrix = a;
        if (flag)
        {
            i3 = i;
        } else
        {
            i3 = j;
        }
        if (flag)
        {
            j3 = j;
        } else
        {
            j3 = i;
        }
        flag1 = bitmatrix.get(i3, j3);
        k3 = i2;
        l3 = i;
        while (j != l) 
        {
            BitMatrix bitmatrix1 = a;
            int i4;
            int j4;
            boolean flag2;
            int k4;
            if (flag)
            {
                i4 = l3;
            } else
            {
                i4 = j;
            }
            if (flag)
            {
                j4 = j;
            } else
            {
                j4 = l3;
            }
            flag2 = bitmatrix1.get(i4, j4);
            if (flag2 != flag1)
            {
                l2++;
                flag1 = flag2;
            }
            k4 = k3 + l1;
            if (k4 > 0)
            {
                if (l3 == k)
                {
                    break;
                }
                l3 += j2;
                k4 -= k1;
            }
            j += k2;
            k3 = k4;
        }
        return new a(resultpoint, resultpoint1, l2, (byte)0);
    }

    public final DetectorResult detect()
    {
        ResultPoint resultpoint7;
        ResultPoint resultpoint8;
        ResultPoint resultpoint9;
        ResultPoint resultpoint10;
        int k;
        int l;
        ResultPoint resultpoint11;
        ResultPoint resultpoint12;
        BitMatrix bitmatrix;
        ResultPoint aresultpoint[] = b.detect();
        ResultPoint resultpoint = aresultpoint[0];
        ResultPoint resultpoint1 = aresultpoint[1];
        ResultPoint resultpoint2 = aresultpoint[2];
        ResultPoint resultpoint3 = aresultpoint[3];
        ArrayList arraylist = new ArrayList(4);
        arraylist.add(b(resultpoint, resultpoint1));
        arraylist.add(b(resultpoint, resultpoint2));
        arraylist.add(b(resultpoint1, resultpoint3));
        arraylist.add(b(resultpoint2, resultpoint3));
        Collections.sort(arraylist, new b((byte)0));
        a a1 = (a)arraylist.get(0);
        a a2 = (a)arraylist.get(1);
        HashMap hashmap = new HashMap();
        a(hashmap, a1.a());
        a(hashmap, a1.b());
        a(hashmap, a2.a());
        a(hashmap, a2.b());
        Iterator iterator = hashmap.entrySet().iterator();
        ResultPoint resultpoint4 = null;
        ResultPoint resultpoint5 = null;
        ResultPoint resultpoint6 = null;
        while (iterator.hasNext()) 
        {
            java.util.Map.Entry entry = (java.util.Map.Entry)iterator.next();
            ResultPoint resultpoint14 = (ResultPoint)entry.getKey();
            if (((Integer)entry.getValue()).intValue() == 2)
            {
                resultpoint4 = resultpoint14;
            } else
            if (resultpoint5 == null)
            {
                resultpoint5 = resultpoint14;
            } else
            {
                resultpoint6 = resultpoint14;
            }
        }
        if (resultpoint5 == null || resultpoint4 == null || resultpoint6 == null)
        {
            throw NotFoundException.getNotFoundInstance();
        }
        ResultPoint aresultpoint1[] = {
            resultpoint5, resultpoint4, resultpoint6
        };
        ResultPoint.orderBestPatterns(aresultpoint1);
        resultpoint7 = aresultpoint1[0];
        resultpoint8 = aresultpoint1[1];
        resultpoint9 = aresultpoint1[2];
        int i;
        int j;
        float f;
        int i1;
        float f1;
        float f2;
        float f3;
        int j1;
        float f4;
        float f5;
        int k1;
        int l1;
        if (!hashmap.containsKey(resultpoint))
        {
            resultpoint10 = resultpoint;
        } else
        if (!hashmap.containsKey(resultpoint1))
        {
            resultpoint10 = resultpoint1;
        } else
        if (!hashmap.containsKey(resultpoint2))
        {
            resultpoint10 = resultpoint2;
        } else
        {
            resultpoint10 = resultpoint3;
        }
        i = b(resultpoint9, resultpoint10).getTransitions();
        j = b(resultpoint7, resultpoint10).getTransitions();
        if ((i & 1) == 1)
        {
            i++;
        }
        k = i + 2;
        if ((j & 1) == 1)
        {
            j++;
        }
        l = j + 2;
        if (k * 4 < l * 7 && l * 4 < k * 7) goto _L2; else goto _L1
_L1:
        f = (float)a(resultpoint8, resultpoint7) / (float)k;
        i1 = a(resultpoint9, resultpoint10);
        f1 = (resultpoint10.getX() - resultpoint9.getX()) / (float)i1;
        f2 = (resultpoint10.getY() - resultpoint9.getY()) / (float)i1;
        resultpoint11 = new ResultPoint(resultpoint10.getX() + f1 * f, resultpoint10.getY() + f * f2);
        f3 = (float)a(resultpoint8, resultpoint9) / (float)l;
        j1 = a(resultpoint7, resultpoint10);
        f4 = (resultpoint10.getX() - resultpoint7.getX()) / (float)j1;
        f5 = (resultpoint10.getY() - resultpoint7.getY()) / (float)j1;
        resultpoint12 = new ResultPoint(resultpoint10.getX() + f4 * f3, resultpoint10.getY() + f3 * f5);
        if (a(resultpoint11)) goto _L4; else goto _L3
_L3:
        if (a(resultpoint12)) goto _L6; else goto _L5
_L5:
        resultpoint11 = null;
_L7:
        if (resultpoint11 == null)
        {
            resultpoint11 = resultpoint10;
        }
        k1 = b(resultpoint9, resultpoint11).getTransitions();
        l1 = b(resultpoint7, resultpoint11).getTransitions();
        if ((k1 & 1) == 1)
        {
            k1++;
        }
        if ((l1 & 1) == 1)
        {
            l1++;
        }
        bitmatrix = a(a, resultpoint9, resultpoint8, resultpoint7, resultpoint11, k1, l1);
_L12:
        return new DetectorResult(bitmatrix, new ResultPoint[] {
            resultpoint9, resultpoint8, resultpoint7, resultpoint11
        });
_L4:
        if (!a(resultpoint12) || Math.abs(k - b(resultpoint9, resultpoint11).getTransitions()) + Math.abs(l - b(resultpoint7, resultpoint11).getTransitions()) <= Math.abs(k - b(resultpoint9, resultpoint12).getTransitions()) + Math.abs(l - b(resultpoint7, resultpoint12).getTransitions())) goto _L7; else goto _L6
_L6:
        resultpoint11 = resultpoint12;
          goto _L7
_L2:
        ResultPoint resultpoint13;
        int i2 = Math.min(l, k);
        float f6 = (float)a(resultpoint8, resultpoint7) / (float)i2;
        int j2 = a(resultpoint9, resultpoint10);
        float f7 = (resultpoint10.getX() - resultpoint9.getX()) / (float)j2;
        float f8 = (resultpoint10.getY() - resultpoint9.getY()) / (float)j2;
        resultpoint11 = new ResultPoint(resultpoint10.getX() + f7 * f6, resultpoint10.getY() + f6 * f8);
        float f9 = (float)a(resultpoint8, resultpoint9) / (float)i2;
        int k2 = a(resultpoint7, resultpoint10);
        float f10 = (resultpoint10.getX() - resultpoint7.getX()) / (float)k2;
        float f11 = (resultpoint10.getY() - resultpoint7.getY()) / (float)k2;
        resultpoint13 = new ResultPoint(resultpoint10.getX() + f10 * f9, resultpoint10.getY() + f9 * f11);
        if (a(resultpoint11)) goto _L9; else goto _L8
_L8:
        if (a(resultpoint13)) goto _L11; else goto _L10
_L10:
        resultpoint11 = null;
_L13:
        if (resultpoint11 == null)
        {
            resultpoint11 = resultpoint10;
        }
        int l2 = 1 + Math.max(b(resultpoint9, resultpoint11).getTransitions(), b(resultpoint7, resultpoint11).getTransitions());
        if ((l2 & 1) == 1)
        {
            l2++;
        }
        bitmatrix = a(a, resultpoint9, resultpoint8, resultpoint7, resultpoint11, l2, l2);
          goto _L12
_L9:
        if (!a(resultpoint13) || Math.abs(b(resultpoint9, resultpoint11).getTransitions() - b(resultpoint7, resultpoint11).getTransitions()) <= Math.abs(b(resultpoint9, resultpoint13).getTransitions() - b(resultpoint7, resultpoint13).getTransitions())) goto _L13; else goto _L11
_L11:
        resultpoint11 = resultpoint13;
          goto _L13
    }
}
