// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing.oned;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.FormatException;
import com.google.zxing.common.BitMatrix;
import java.util.Map;

// Referenced classes of package com.google.zxing.oned:
//            UPCEANWriter, UPCEANReader, EAN13Reader

public final class EAN13Writer extends UPCEANWriter
{

    public EAN13Writer()
    {
    }

    public final BitMatrix encode(String s, BarcodeFormat barcodeformat, int i, int j, Map map)
    {
        if (barcodeformat != BarcodeFormat.EAN_13)
        {
            throw new IllegalArgumentException((new StringBuilder("Can only encode EAN_13, but got ")).append(barcodeformat).toString());
        } else
        {
            return super.encode(s, barcodeformat, i, j, map);
        }
    }

    public final boolean[] encode(String s)
    {
        if (s.length() != 13)
        {
            throw new IllegalArgumentException((new StringBuilder("Requested contents should be 13 digits long, but got ")).append(s.length()).toString());
        }
        try
        {
            if (!UPCEANReader.a(s))
            {
                throw new IllegalArgumentException("Contents do not pass checksum");
            }
        }
        catch (FormatException formatexception)
        {
            throw new IllegalArgumentException("Illegal contents");
        }
        int i = Integer.parseInt(s.substring(0, 1));
        int j = EAN13Reader.a[i];
        boolean aflag[] = new boolean[95];
        int k = 0 + appendPattern(aflag, 0, UPCEANReader.b, true);
        int l = 1;
        int i1 = k;
        for (; l <= 6; l++)
        {
            int i2 = Integer.parseInt(s.substring(l, l + 1));
            if ((1 & j >> 6 - l) == 1)
            {
                i2 += 10;
            }
            i1 += appendPattern(aflag, i1, UPCEANReader.e[i2], false);
        }

        int j1 = i1 + appendPattern(aflag, i1, UPCEANReader.c, false);
        for (int k1 = 7; k1 <= 12; k1++)
        {
            int l1 = Integer.parseInt(s.substring(k1, k1 + 1));
            j1 += appendPattern(aflag, j1, UPCEANReader.d[l1], true);
        }

        appendPattern(aflag, j1, UPCEANReader.b, true);
        return aflag;
    }
}
