// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing.oned;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.ResultMetadataType;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.BitArray;
import java.util.EnumMap;
import java.util.Map;

// Referenced classes of package com.google.zxing.oned:
//            UPCEANReader

final class b
{

    private final int a[] = new int[4];
    private final StringBuilder b = new StringBuilder();

    b()
    {
    }

    final Result a(int i, BitArray bitarray, int ai[])
    {
        StringBuilder stringbuilder = b;
        stringbuilder.setLength(0);
        int ai1[] = a;
        ai1[0] = 0;
        ai1[1] = 0;
        ai1[2] = 0;
        ai1[3] = 0;
        int j = bitarray.getSize();
        int k = ai[1];
        int l = 0;
        int k1;
        for (int i1 = 0; i1 < 2 && k < j; k = k1)
        {
            int j1 = UPCEANReader.a(bitarray, ai1, k, UPCEANReader.e);
            stringbuilder.append((char)(48 + j1 % 10));
            k1 = k;
            for (int l1 = 0; l1 < 4;)
            {
                int i2 = k1 + ai1[l1];
                l1++;
                k1 = i2;
            }

            if (j1 >= 10)
            {
                l |= 1 << 1 - i1;
            }
            if (i1 != 1)
            {
                k1 = bitarray.getNextUnset(bitarray.getNextSet(k1));
            }
            i1++;
        }

        if (stringbuilder.length() != 2)
        {
            throw NotFoundException.getNotFoundInstance();
        }
        if (Integer.parseInt(stringbuilder.toString()) % 4 != l)
        {
            throw NotFoundException.getNotFoundInstance();
        }
        String s = stringbuilder.toString();
        Object obj;
        ResultPoint aresultpoint[];
        Result result;
        if (s.length() != 2)
        {
            obj = null;
        } else
        {
            obj = new EnumMap(com/google/zxing/ResultMetadataType);
            ((Map) (obj)).put(ResultMetadataType.ISSUE_NUMBER, Integer.valueOf(s));
        }
        aresultpoint = new ResultPoint[2];
        aresultpoint[0] = new ResultPoint((float)(ai[0] + ai[1]) / 2.0F, i);
        aresultpoint[1] = new ResultPoint(k, i);
        result = new Result(s, null, aresultpoint, BarcodeFormat.UPC_EAN_EXTENSION);
        if (obj != null)
        {
            result.putAllMetadata(((Map) (obj)));
        }
        return result;
    }
}
