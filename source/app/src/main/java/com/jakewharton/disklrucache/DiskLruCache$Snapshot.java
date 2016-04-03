// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.jakewharton.disklrucache;

import java.io.Closeable;
import java.io.InputStream;

// Referenced classes of package com.jakewharton.disklrucache:
//            Util, DiskLruCache

public final class <init>
    implements Closeable
{

    private final String a;
    private final long b;
    private final InputStream c[];
    private final long d[];
    private DiskLruCache e;

    public final void close()
    {
        InputStream ainputstream[] = c;
        int i = ainputstream.length;
        for (int j = 0; j < i; j++)
        {
            Util.a(ainputstream[j]);
        }

    }

    public final c edit()
    {
        return DiskLruCache.a(e, a, b);
    }

    public final InputStream getInputStream(int i)
    {
        return c[i];
    }

    public final long getLength(int i)
    {
        return d[i];
    }

    public final String getString(int i)
    {
        return DiskLruCache.a(getInputStream(i));
    }

    private (DiskLruCache disklrucache, String s, long l, InputStream ainputstream[], long al[])
    {
        e = disklrucache;
        super();
        a = s;
        b = l;
        c = ainputstream;
        d = al;
    }

    d(DiskLruCache disklrucache, String s, long l, InputStream ainputstream[], long al[], byte byte0)
    {
        this(disklrucache, s, l, ainputstream, al);
    }
}
