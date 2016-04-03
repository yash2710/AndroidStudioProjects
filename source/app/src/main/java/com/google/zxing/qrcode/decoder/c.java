// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing.qrcode.decoder;


// Referenced classes of package com.google.zxing.qrcode.decoder:
//            d, e, f, g, 
//            h, i, j, k

abstract class c
{

    private static final c a[];

    private c()
    {
    }

    c(byte byte0)
    {
        this();
    }

    static c a(int l)
    {
        if (l < 0 || l > 7)
        {
            throw new IllegalArgumentException();
        } else
        {
            return a[l];
        }
    }

    abstract boolean a(int l, int i1);

    static 
    {
        c ac[] = new c[8];
        ac[0] = new d((byte)0);
        ac[1] = new e((byte)0);
        ac[2] = new f((byte)0);
        ac[3] = new g((byte)0);
        ac[4] = new h((byte)0);
        ac[5] = new i((byte)0);
        ac[6] = new j((byte)0);
        ac[7] = new k((byte)0);
        a = ac;
    }
}
