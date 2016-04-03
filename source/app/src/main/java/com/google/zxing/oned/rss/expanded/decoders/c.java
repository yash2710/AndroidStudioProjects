// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing.oned.rss.expanded.decoders;

import com.google.zxing.NotFoundException;
import com.google.zxing.common.BitArray;

// Referenced classes of package com.google.zxing.oned.rss.expanded.decoders:
//            h, s, o

final class c extends h
{

    c(BitArray bitarray)
    {
        super(bitarray);
    }

    public final String parseInformation()
    {
        if (getInformation().getSize() < 48)
        {
            throw NotFoundException.getNotFoundInstance();
        } else
        {
            StringBuilder stringbuilder = new StringBuilder();
            encodeCompressedGtin(stringbuilder, 8);
            int i = getGeneralDecoder().a(48, 2);
            stringbuilder.append("(392");
            stringbuilder.append(i);
            stringbuilder.append(')');
            stringbuilder.append(getGeneralDecoder().a(50, null).a());
            return stringbuilder.toString();
        }
    }
}
