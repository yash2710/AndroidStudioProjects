// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing.qrcode.detector;

import com.google.zxing.NotFoundException;
import com.google.zxing.ResultPointCallback;
import com.google.zxing.common.BitMatrix;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

// Referenced classes of package com.google.zxing.qrcode.detector:
//            AlignmentPattern

class a
{

    private final BitMatrix a;
    private final List b = new ArrayList(5);
    private final int c;
    private final int d;
    private final int e;
    private final int f;
    private final float g;
    private final int h[] = new int[3];
    private final ResultPointCallback i;

    a(BitMatrix bitmatrix, int j, int k, int l, int i1, float f1, ResultPointCallback resultpointcallback)
    {
        a = bitmatrix;
        c = j;
        d = k;
        e = l;
        f = i1;
        g = f1;
        i = resultpointcallback;
    }

    private static float a(int ai[], int j)
    {
        return (float)(j - ai[2]) - (float)ai[1] / 2.0F;
    }

    private AlignmentPattern a(int ai[], int j, int k)
    {
        int l = ai[0] + ai[1] + ai[2];
        float f1 = a(ai, k);
        int i1 = (int)f1;
        int j1 = 2 * ai[1];
        BitMatrix bitmatrix = a;
        int k1 = bitmatrix.getHeight();
        int ai1[] = h;
        ai1[0] = 0;
        ai1[1] = 0;
        ai1[2] = 0;
        int l1;
        for (l1 = j; l1 >= 0 && bitmatrix.get(i1, l1) && ai1[1] <= j1; l1--)
        {
            ai1[1] = 1 + ai1[1];
        }

        float f2;
        if (l1 < 0 || ai1[1] > j1)
        {
            f2 = (0.0F / 0.0F);
        } else
        {
            for (; l1 >= 0 && !bitmatrix.get(i1, l1) && ai1[0] <= j1; l1--)
            {
                ai1[0] = 1 + ai1[0];
            }

            if (ai1[0] > j1)
            {
                f2 = (0.0F / 0.0F);
            } else
            {
                int i2;
                for (i2 = j + 1; i2 < k1 && bitmatrix.get(i1, i2) && ai1[1] <= j1; i2++)
                {
                    ai1[1] = 1 + ai1[1];
                }

                if (i2 == k1 || ai1[1] > j1)
                {
                    f2 = (0.0F / 0.0F);
                } else
                {
                    for (; i2 < k1 && !bitmatrix.get(i1, i2) && ai1[2] <= j1; i2++)
                    {
                        ai1[2] = 1 + ai1[2];
                    }

                    if (ai1[2] > j1)
                    {
                        f2 = (0.0F / 0.0F);
                    } else
                    if (5 * Math.abs((ai1[0] + ai1[1] + ai1[2]) - l) >= l * 2)
                    {
                        f2 = (0.0F / 0.0F);
                    } else
                    if (a(ai1))
                    {
                        f2 = a(ai1, i2);
                    } else
                    {
                        f2 = (0.0F / 0.0F);
                    }
                }
            }
        }
        if (!Float.isNaN(f2))
        {
            float f3 = (float)(ai[0] + ai[1] + ai[2]) / 3F;
            for (Iterator iterator = b.iterator(); iterator.hasNext();)
            {
                AlignmentPattern alignmentpattern1 = (AlignmentPattern)iterator.next();
                if (alignmentpattern1.a(f3, f2, f1))
                {
                    return alignmentpattern1.b(f2, f1, f3);
                }
            }

            AlignmentPattern alignmentpattern = new AlignmentPattern(f1, f2, f3);
            b.add(alignmentpattern);
            if (i != null)
            {
                i.foundPossibleResultPoint(alignmentpattern);
            }
        }
        return null;
    }

    private boolean a(int ai[])
    {
        float f1 = g;
        float f2 = f1 / 2.0F;
        for (int j = 0; j < 3; j++)
        {
            if (Math.abs(f1 - (float)ai[j]) >= f2)
            {
                return false;
            }
        }

        return true;
    }

    final AlignmentPattern a()
    {
        int j;
        int k;
        int l;
        int i1;
        int ai[];
        int j1;
        j = c;
        k = f;
        l = j + e;
        i1 = d + (k >> 1);
        ai = new int[3];
        j1 = 0;
_L17:
        if (j1 >= k) goto _L2; else goto _L1
_L1:
        int l1;
        int j2;
        int k2;
        int k1;
        int i2;
        if ((j1 & 1) == 0)
        {
            k1 = j1 + 1 >> 1;
        } else
        {
            k1 = -(j1 + 1 >> 1);
        }
        l1 = i1 + k1;
        ai[0] = 0;
        ai[1] = 0;
        ai[2] = 0;
        for (i2 = j; i2 < l && !a.get(i2, l1); i2++) { }
        j2 = i2;
        k2 = 0;
_L14:
        if (j2 >= l) goto _L4; else goto _L3
_L3:
        if (!a.get(j2, l1)) goto _L6; else goto _L5
_L5:
        if (k2 == 1) goto _L8; else goto _L7
_L7:
        if (k2 != 2) goto _L10; else goto _L9
_L9:
        if (!a(ai)) goto _L12; else goto _L11
_L11:
        AlignmentPattern alignmentpattern = a(ai, l1, j2);
        if (alignmentpattern == null) goto _L12; else goto _L13
_L13:
        return alignmentpattern;
_L12:
        ai[0] = ai[2];
        ai[1] = 1;
        ai[2] = 0;
        k2 = 1;
_L15:
        j2++;
          goto _L14
_L10:
        k2++;
        ai[k2] = 1 + ai[k2];
          goto _L15
_L6:
        if (k2 == 1)
        {
            k2++;
        }
_L8:
        ai[k2] = 1 + ai[k2];
          goto _L15
_L4:
        if (!a(ai))
        {
            continue; /* Loop/switch isn't completed */
        }
        alignmentpattern = a(ai, l1, l);
        if (alignmentpattern != null) goto _L13; else goto _L16
_L16:
        j1++;
          goto _L17
_L2:
        if (!b.isEmpty())
        {
            return (AlignmentPattern)b.get(0);
        } else
        {
            throw NotFoundException.getNotFoundInstance();
        }
    }
}
