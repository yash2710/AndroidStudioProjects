// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.crashlytics.android.internal;

import java.io.InputStream;
import java.io.RandomAccessFile;

// Referenced classes of package com.crashlytics.android.internal:
//            X, aq

final class Y extends InputStream
{

    private int a;
    private int b;
    private aq c;

    private Y(aq aq1, X x)
    {
        c = aq1;
        super();
        a = aq.a(aq1, 4 + x.b);
        b = x.c;
    }

    Y(aq aq1, X x, byte byte0)
    {
        this(aq1, x);
    }

    public final int read()
    {
        if (b == 0)
        {
            return -1;
        } else
        {
            aq.a(c).seek(a);
            int i = aq.a(c).read();
            a = aq.a(c, 1 + a);
            b = -1 + b;
            return i;
        }
    }

    public final int read(byte abyte0[], int i, int j)
    {
        aq.a(abyte0, "buffer");
        if ((i | j) < 0 || j > abyte0.length - i)
        {
            throw new ArrayIndexOutOfBoundsException();
        }
        if (b > 0)
        {
            if (j > b)
            {
                j = b;
            }
            aq.a(c, a, abyte0, i, j);
            a = aq.a(c, j + a);
            b = b - j;
            return j;
        } else
        {
            return -1;
        }
    }
}
