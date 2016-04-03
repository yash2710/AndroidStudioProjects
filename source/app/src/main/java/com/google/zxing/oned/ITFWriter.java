// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing.oned;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.common.BitMatrix;
import java.util.Map;

// Referenced classes of package com.google.zxing.oned:
//            OneDimensionalCodeWriter, ITFReader

public final class ITFWriter extends OneDimensionalCodeWriter
{

    private static final int a[] = {
        1, 1, 1, 1
    };
    private static final int b[] = {
        3, 1, 1
    };

    public ITFWriter()
    {
    }

    public final BitMatrix encode(String s, BarcodeFormat barcodeformat, int i, int j, Map map)
    {
        if (barcodeformat != BarcodeFormat.ITF)
        {
            throw new IllegalArgumentException((new StringBuilder("Can only encode ITF, but got ")).append(barcodeformat).toString());
        } else
        {
            return super.encode(s, barcodeformat, i, j, map);
        }
    }

    public final boolean[] encode(String s)
    {
        int i = s.length();
        if (i % 2 != 0)
        {
            throw new IllegalArgumentException("The lenght of the input should be even");
        }
        if (i > 80)
        {
            throw new IllegalArgumentException((new StringBuilder("Requested contents should be less than 80 digits long, but got ")).append(i).toString());
        }
        boolean aflag[] = new boolean[9 + i * 9];
        int j = appendPattern(aflag, 0, a, true);
        int k = 0;
        int l = j;
        for (; k < i; k += 2)
        {
            int i1 = Character.digit(s.charAt(k), 10);
            int j1 = Character.digit(s.charAt(k + 1), 10);
            int ai[] = new int[18];
            for (int k1 = 0; k1 < 5; k1++)
            {
                ai[k1 << 1] = ITFReader.a[i1][k1];
                ai[1 + (k1 << 1)] = ITFReader.a[j1][k1];
            }

            l += appendPattern(aflag, l, ai, true);
        }

        appendPattern(aflag, l, b, true);
        return aflag;
    }

}
