// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing.oned.rss.expanded.decoders;

import com.google.zxing.common.BitArray;

// Referenced classes of package com.google.zxing.oned.rss.expanded.decoders:
//            AbstractExpandedDecoder, s

abstract class h extends AbstractExpandedDecoder
{

    h(BitArray bitarray)
    {
        super(bitarray);
    }

    protected final void encodeCompressedGtin(StringBuilder stringbuilder, int i)
    {
        stringbuilder.append("(01)");
        int j = stringbuilder.length();
        stringbuilder.append('9');
        encodeCompressedGtinWithoutAI(stringbuilder, i, j);
    }

    protected final void encodeCompressedGtinWithoutAI(StringBuilder stringbuilder, int i, int j)
    {
        for (int k = 0; k < 4; k++)
        {
            int l1 = getGeneralDecoder().a(i + k * 10, 10);
            if (l1 / 100 == 0)
            {
                stringbuilder.append('0');
            }
            if (l1 / 10 == 0)
            {
                stringbuilder.append('0');
            }
            stringbuilder.append(l1);
        }

        int l = 0;
        int i1 = 0;
        for (; l < 13; l++)
        {
            int k1 = -48 + stringbuilder.charAt(l + j);
            if ((l & 1) == 0)
            {
                k1 *= 3;
            }
            i1 += k1;
        }

        int j1 = 10 - i1 % 10;
        if (j1 == 10)
        {
            j1 = 0;
        }
        stringbuilder.append(j1);
    }
}
