// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing.oned;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.Writer;
import com.google.zxing.common.BitMatrix;
import java.util.Map;

public abstract class OneDimensionalCodeWriter
    implements Writer
{

    public OneDimensionalCodeWriter()
    {
    }

    private static BitMatrix a(boolean aflag[], int i, int j, int k)
    {
        int l = aflag.length;
        int i1 = l + k;
        int j1 = Math.max(i, i1);
        int k1 = Math.max(1, j);
        int l1 = j1 / i1;
        int i2 = (j1 - l * l1) / 2;
        BitMatrix bitmatrix = new BitMatrix(j1, k1);
        int j2 = i2;
        for (int k2 = 0; k2 < l;)
        {
            if (aflag[k2])
            {
                bitmatrix.setRegion(j2, 0, l1, k1);
            }
            k2++;
            j2 += l1;
        }

        return bitmatrix;
    }

    protected static int appendPattern(boolean aflag[], int i, int ai[], boolean flag)
    {
        int j = ai.length;
        int k = 0;
        int l = 0;
        int i1 = i;
        while (k < j) 
        {
            int j1 = ai[k];
            int k1 = i1;
            for (int l1 = 0; l1 < j1;)
            {
                int i2 = k1 + 1;
                aflag[k1] = flag;
                l1++;
                k1 = i2;
            }

            l += j1;
            boolean flag1;
            if (!flag)
            {
                flag1 = true;
            } else
            {
                flag1 = false;
            }
            k++;
            flag = flag1;
            i1 = k1;
        }
        return l;
    }

    public final BitMatrix encode(String s, BarcodeFormat barcodeformat, int i, int j)
    {
        return encode(s, barcodeformat, i, j, null);
    }

    public BitMatrix encode(String s, BarcodeFormat barcodeformat, int i, int j, Map map)
    {
        int k;
        if (s.length() == 0)
        {
            throw new IllegalArgumentException("Found empty contents");
        }
        if (i < 0 || j < 0)
        {
            throw new IllegalArgumentException((new StringBuilder("Negative size is not allowed. Input: ")).append(i).append('x').append(j).toString());
        }
        k = getDefaultMargin();
        if (map == null) goto _L2; else goto _L1
_L1:
        Integer integer = (Integer)map.get(EncodeHintType.MARGIN);
        if (integer == null) goto _L2; else goto _L3
_L3:
        int l = integer.intValue();
_L5:
        return a(encode(s), i, j, l);
_L2:
        l = k;
        if (true) goto _L5; else goto _L4
_L4:
    }

    public abstract boolean[] encode(String s);

    public int getDefaultMargin()
    {
        return 10;
    }
}
