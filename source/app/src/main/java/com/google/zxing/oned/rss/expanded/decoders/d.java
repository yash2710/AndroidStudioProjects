// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing.oned.rss.expanded.decoders;

import com.google.zxing.NotFoundException;
import com.google.zxing.common.BitArray;

// Referenced classes of package com.google.zxing.oned.rss.expanded.decoders:
//            h, s, o

final class d extends h
{

    d(BitArray bitarray)
    {
        super(bitarray);
    }

    public final String parseInformation()
    {
        if (getInformation().getSize() < 48)
        {
            throw NotFoundException.getNotFoundInstance();
        }
        StringBuilder stringbuilder = new StringBuilder();
        encodeCompressedGtin(stringbuilder, 8);
        int i = getGeneralDecoder().a(48, 2);
        stringbuilder.append("(393");
        stringbuilder.append(i);
        stringbuilder.append(')');
        int j = getGeneralDecoder().a(50, 10);
        if (j / 100 == 0)
        {
            stringbuilder.append('0');
        }
        if (j / 10 == 0)
        {
            stringbuilder.append('0');
        }
        stringbuilder.append(j);
        stringbuilder.append(getGeneralDecoder().a(60, null).a());
        return stringbuilder.toString();
    }
}
