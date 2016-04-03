// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing.oned;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.common.BitArray;

// Referenced classes of package com.google.zxing.oned:
//            UPCEANReader

public final class EAN8Reader extends UPCEANReader
{

    private final int a[] = new int[4];

    public EAN8Reader()
    {
    }

    final BarcodeFormat a()
    {
        return BarcodeFormat.EAN_8;
    }

    protected final int decodeMiddle(BitArray bitarray, int ai[], StringBuilder stringbuilder)
    {
        int ai1[] = a;
        ai1[0] = 0;
        ai1[1] = 0;
        ai1[2] = 0;
        ai1[3] = 0;
        int i = bitarray.getSize();
        int j = ai[1];
        int l1;
        for (int k = 0; k < 4 && j < i; j = l1)
        {
            stringbuilder.append((char)(48 + a(bitarray, ai1, j, d)));
            l1 = j;
            for (int i2 = 0; i2 < 4; i2++)
            {
                l1 += ai1[i2];
            }

            k++;
        }

        int l = a(bitarray, j, true, c)[1];
        int j1;
        for (int i1 = 0; i1 < 4 && l < i; l = j1)
        {
            stringbuilder.append((char)(48 + a(bitarray, ai1, l, d)));
            j1 = l;
            for (int k1 = 0; k1 < 4; k1++)
            {
                j1 += ai1[k1];
            }

            i1++;
        }

        return l;
    }
}
