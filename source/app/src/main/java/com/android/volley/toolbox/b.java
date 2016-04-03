// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.volley.toolbox;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

// Referenced classes of package com.android.volley.toolbox:
//            DiskBasedCache

class b
{

    public long a;
    public String b;
    public String c;
    public long d;
    public long e;
    public long f;
    public Map g;

    private b()
    {
    }

    public b(String s, com.android.volley.Cache.Entry entry)
    {
        b = s;
        a = entry.data.length;
        c = entry.etag;
        d = entry.serverDate;
        e = entry.ttl;
        f = entry.softTtl;
        g = entry.responseHeaders;
    }

    public static b readHeader$218c1f58(InputStream inputstream)
    {
        b b1 = new b();
        if (DiskBasedCache.a(inputstream) != 0x20120504)
        {
            throw new IOException();
        }
        b1.b = DiskBasedCache.c(inputstream);
        b1.c = DiskBasedCache.c(inputstream);
        if (b1.c.equals(""))
        {
            b1.c = null;
        }
        b1.d = DiskBasedCache.b(inputstream);
        b1.e = DiskBasedCache.b(inputstream);
        b1.f = DiskBasedCache.b(inputstream);
        b1.g = DiskBasedCache.d(inputstream);
        return b1;
    }

    public com.android.volley.Cache.Entry toCacheEntry(byte abyte0[])
    {
        com.android.volley.Cache.Entry entry = new com.android.volley.Cache.Entry();
        entry.data = abyte0;
        entry.etag = c;
        entry.serverDate = d;
        entry.ttl = e;
        entry.softTtl = f;
        entry.responseHeaders = g;
        return entry;
    }

    public boolean writeHeader(OutputStream outputstream)
    {
        DiskBasedCache.a(outputstream, 0x20120504);
        DiskBasedCache.a(outputstream, b);
        if (c != null)
        {
            break MISSING_BLOCK_LABEL_67;
        }
        String s = "";
_L1:
        try
        {
            DiskBasedCache.a(outputstream, s);
            DiskBasedCache.a(outputstream, d);
            DiskBasedCache.a(outputstream, e);
            DiskBasedCache.a(outputstream, f);
            DiskBasedCache.a(g, outputstream);
            outputstream.flush();
        }
        catch (IOException ioexception)
        {
            return false;
        }
        return true;
        s = c;
          goto _L1
    }
}
