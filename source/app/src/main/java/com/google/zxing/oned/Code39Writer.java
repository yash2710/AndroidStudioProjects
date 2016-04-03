// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing.oned;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.common.BitMatrix;
import java.util.Map;

// Referenced classes of package com.google.zxing.oned:
//            OneDimensionalCodeWriter, Code39Reader

public final class Code39Writer extends OneDimensionalCodeWriter
{

    public Code39Writer()
    {
    }

    private static void a(int i, int ai[])
    {
        int j = 0;
        while (j < 9) 
        {
            int k;
            if ((i & 1 << j) == 0)
            {
                k = 1;
            } else
            {
                k = 2;
            }
            ai[j] = k;
            j++;
        }
    }

    public final BitMatrix encode(String s, BarcodeFormat barcodeformat, int i, int j, Map map)
    {
        if (barcodeformat != BarcodeFormat.CODE_39)
        {
            throw new IllegalArgumentException((new StringBuilder("Can only encode CODE_39, but got ")).append(barcodeformat).toString());
        } else
        {
            return super.encode(s, barcodeformat, i, j, map);
        }
    }

    public final boolean[] encode(String s)
    {
        int i = s.length();
        if (i > 80)
        {
            throw new IllegalArgumentException((new StringBuilder("Requested contents should be less than 80 digits long, but got ")).append(i).toString());
        }
        int ai[] = new int[9];
        int j = i + 25;
        for (int k = 0; k < i;)
        {
            int i2 = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. *$/+%".indexOf(s.charAt(k));
            a(Code39Reader.a[i2], ai);
            int j2 = j;
            for (int k2 = 0; k2 < 9; k2++)
            {
                j2 += ai[k2];
            }

            k++;
            j = j2;
        }

        boolean aflag[] = new boolean[j];
        a(Code39Reader.a[39], ai);
        int l = appendPattern(aflag, 0, ai, true);
        int ai1[] = {
            1
        };
        int i1 = l + appendPattern(aflag, l, ai1, false);
        for (int j1 = i - 1; j1 >= 0; j1--)
        {
            int k1 = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. *$/+%".indexOf(s.charAt(j1));
            a(Code39Reader.a[k1], ai);
            int l1 = i1 + appendPattern(aflag, i1, ai, true);
            i1 = l1 + appendPattern(aflag, l1, ai1, false);
        }

        a(Code39Reader.a[39], ai);
        appendPattern(aflag, i1, ai, true);
        return aflag;
    }
}
