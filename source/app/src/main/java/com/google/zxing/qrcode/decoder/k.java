// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing.qrcode.decoder;


// Referenced classes of package com.google.zxing.qrcode.decoder:
//            c

final class k extends c
{

    private k()
    {
        super((byte)0);
    }

    k(byte byte0)
    {
        this();
    }

    final boolean a(int i, int j)
    {
        return (1 & (1 & i + j) + (i * j) % 3) == 0;
    }
}
