// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing.oned.rss.expanded.decoders;

import com.google.zxing.common.BitArray;

// Referenced classes of package com.google.zxing.oned.rss.expanded.decoders:
//            f

final class b extends f
{

    b(BitArray bitarray)
    {
        super(bitarray);
    }

    protected final void addWeightCode(StringBuilder stringbuilder, int i)
    {
        if (i < 10000)
        {
            stringbuilder.append("(3202)");
            return;
        } else
        {
            stringbuilder.append("(3203)");
            return;
        }
    }

    protected final int checkWeight(int i)
    {
        if (i < 10000)
        {
            return i;
        } else
        {
            return i - 10000;
        }
    }
}
