// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing.oned.rss.expanded.decoders;

import com.google.zxing.common.BitArray;

// Referenced classes of package com.google.zxing.oned.rss.expanded.decoders:
//            h, s

final class g extends h
{

    g(BitArray bitarray)
    {
        super(bitarray);
    }

    public final String parseInformation()
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("(01)");
        int i = stringbuilder.length();
        stringbuilder.append(getGeneralDecoder().a(4, 4));
        encodeCompressedGtinWithoutAI(stringbuilder, 8, i);
        return getGeneralDecoder().a(stringbuilder, 48);
    }
}
