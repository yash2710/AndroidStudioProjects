// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing.qrcode.decoder;


// Referenced classes of package com.google.zxing.qrcode.decoder:
//            c

final class h extends c
{

    private h()
    {
        super((byte)0);
    }

    h(byte byte0)
    {
        this();
    }

    final boolean a(int i, int j)
    {
        return (1 & (i >>> 1) + j / 3) == 0;
    }
}
