// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing.oned;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.NotFoundException;
import com.google.zxing.common.BitArray;

// Referenced classes of package com.google.zxing.oned:
//            UPCEANReader

public final class EAN13Reader extends UPCEANReader
{

    static final int a[] = {
        0, 11, 13, 14, 19, 25, 28, 21, 22, 26
    };
    private final int f[] = new int[4];

    public EAN13Reader()
    {
    }

    final BarcodeFormat a()
    {
        return BarcodeFormat.EAN_13;
    }

    protected final int decodeMiddle(BitArray bitarray, int ai[], StringBuilder stringbuilder)
    {
        int ai1[];
        int i;
        int j;
        int l;
        int i1;
        ai1 = f;
        ai1[0] = 0;
        ai1[1] = 0;
        ai1[2] = 0;
        ai1[3] = 0;
        i = bitarray.getSize();
        j = ai[1];
        int k = 0;
        l = 0;
        for (; k < 6 && j < i; k++)
        {
            int j2 = a(bitarray, ai1, j, e);
            stringbuilder.append((char)(48 + j2 % 10));
            for (int k2 = 0; k2 < 4; k2++)
            {
                j += ai1[k2];
            }

            if (j2 >= 10)
            {
                l |= 1 << 5 - k;
            }
        }

        i1 = 0;
_L8:
        if (i1 >= 10) goto _L2; else goto _L1
_L1:
        if (l != a[i1]) goto _L4; else goto _L3
_L3:
        int j1;
        int k1;
        stringbuilder.insert(0, (char)(i1 + 48));
        j1 = a(bitarray, j, true, c)[1];
        k1 = 0;
_L6:
        if (k1 >= 6 || j1 >= i)
        {
            break; /* Loop/switch isn't completed */
        }
        stringbuilder.append((char)(48 + a(bitarray, ai1, j1, d)));
        for (int l1 = 0; l1 < 4;)
        {
            int i2 = j1 + ai1[l1];
            l1++;
            j1 = i2;
        }

        k1++;
        continue; /* Loop/switch isn't completed */
_L4:
        i1++;
        continue; /* Loop/switch isn't completed */
_L2:
        throw NotFoundException.getNotFoundInstance();
        if (true) goto _L6; else goto _L5
_L5:
        return j1;
        if (true) goto _L8; else goto _L7
_L7:
    }

}
