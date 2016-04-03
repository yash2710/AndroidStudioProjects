// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing.oned.rss.expanded.decoders;

import com.google.zxing.NotFoundException;
import com.google.zxing.common.BitArray;

// Referenced classes of package com.google.zxing.oned.rss.expanded.decoders:
//            i, s

final class e extends i
{

    private final String a;
    private final String b;

    e(BitArray bitarray, String s1, String s2)
    {
        super(bitarray);
        a = s2;
        b = s1;
    }

    protected final void addWeightCode(StringBuilder stringbuilder, int j)
    {
        int k = j / 0x186a0;
        stringbuilder.append('(');
        stringbuilder.append(b);
        stringbuilder.append(k);
        stringbuilder.append(')');
    }

    protected final int checkWeight(int j)
    {
        return j % 0x186a0;
    }

    public final String parseInformation()
    {
        if (getInformation().getSize() != 84)
        {
            throw NotFoundException.getNotFoundInstance();
        }
        StringBuilder stringbuilder = new StringBuilder();
        encodeCompressedGtin(stringbuilder, 8);
        encodeCompressedWeight(stringbuilder, 48, 20);
        int j = getGeneralDecoder().a(68, 16);
        if (j != 38400)
        {
            stringbuilder.append('(');
            stringbuilder.append(a);
            stringbuilder.append(')');
            int k = j % 32;
            int l = j / 32;
            int i1 = 1 + l % 12;
            int j1 = l / 12;
            if (j1 / 10 == 0)
            {
                stringbuilder.append('0');
            }
            stringbuilder.append(j1);
            if (i1 / 10 == 0)
            {
                stringbuilder.append('0');
            }
            stringbuilder.append(i1);
            if (k / 10 == 0)
            {
                stringbuilder.append('0');
            }
            stringbuilder.append(k);
        }
        return stringbuilder.toString();
    }
}
