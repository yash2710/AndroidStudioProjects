// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing.oned;

import com.google.zxing.ReaderException;
import com.google.zxing.Result;
import com.google.zxing.common.BitArray;

// Referenced classes of package com.google.zxing.oned:
//            b, c, UPCEANReader

final class d
{

    private static final int a[] = {
        1, 1, 2
    };
    private final b b = new b();
    private final c c = new c();

    d()
    {
    }

    final Result a(int i, BitArray bitarray, int j)
    {
        int ai[] = UPCEANReader.a(bitarray, j, false, a);
        Result result;
        try
        {
            result = c.a(i, bitarray, ai);
        }
        catch (ReaderException readerexception)
        {
            return b.a(i, bitarray, ai);
        }
        return result;
    }

}
