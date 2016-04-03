// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing.oned;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.common.BitMatrix;
import java.util.Map;

// Referenced classes of package com.google.zxing.oned:
//            UPCEANWriter, UPCEANReader

public final class EAN8Writer extends UPCEANWriter
{

    public EAN8Writer()
    {
    }

    public final BitMatrix encode(String s, BarcodeFormat barcodeformat, int i, int j, Map map)
    {
        if (barcodeformat != BarcodeFormat.EAN_8)
        {
            throw new IllegalArgumentException((new StringBuilder("Can only encode EAN_8, but got ")).append(barcodeformat).toString());
        } else
        {
            return super.encode(s, barcodeformat, i, j, map);
        }
    }

    public final boolean[] encode(String s)
    {
        if (s.length() != 8)
        {
            throw new IllegalArgumentException((new StringBuilder("Requested contents should be 8 digits long, but got ")).append(s.length()).toString());
        }
        boolean aflag[] = new boolean[67];
        int i = 0 + appendPattern(aflag, 0, UPCEANReader.b, true);
        for (int j = 0; j <= 3; j++)
        {
            int j1 = Integer.parseInt(s.substring(j, j + 1));
            i += appendPattern(aflag, i, UPCEANReader.d[j1], false);
        }

        int k = i + appendPattern(aflag, i, UPCEANReader.c, false);
        for (int l = 4; l <= 7; l++)
        {
            int i1 = Integer.parseInt(s.substring(l, l + 1));
            k += appendPattern(aflag, k, UPCEANReader.d[i1], true);
        }

        appendPattern(aflag, k, UPCEANReader.b, true);
        return aflag;
    }
}
