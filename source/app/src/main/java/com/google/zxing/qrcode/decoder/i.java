// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing.qrcode.decoder;


// Referenced classes of package com.google.zxing.qrcode.decoder:
//            c

final class i extends c
{

    private i()
    {
        super((byte)0);
    }

    i(byte byte0)
    {
        this();
    }

    final boolean a(int j, int k)
    {
        int l = j * k;
        return (l & 1) + l % 3 == 0;
    }
}
