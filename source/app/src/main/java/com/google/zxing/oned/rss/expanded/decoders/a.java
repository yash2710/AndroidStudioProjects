// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing.oned.rss.expanded.decoders;

import com.google.zxing.common.BitArray;

// Referenced classes of package com.google.zxing.oned.rss.expanded.decoders:
//            f

final class a extends f
{

    a(BitArray bitarray)
    {
        super(bitarray);
    }

    protected final void addWeightCode(StringBuilder stringbuilder, int i)
    {
        stringbuilder.append("(3103)");
    }

    protected final int checkWeight(int i)
    {
        return i;
    }
}
