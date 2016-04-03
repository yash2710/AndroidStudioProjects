// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing.datamatrix.encoder;


// Referenced classes of package com.google.zxing.datamatrix.encoder:
//            SymbolInfo

final class d extends SymbolInfo
{

    d()
    {
        super(false, 1558, 620, 22, 22, 36);
        c = -1;
        d = 62;
    }

    public final int getDataLengthForInterleavedBlock(int i)
    {
        return i > 8 ? 155 : 156;
    }

    public final int getInterleavedBlockCount()
    {
        return 10;
    }
}
