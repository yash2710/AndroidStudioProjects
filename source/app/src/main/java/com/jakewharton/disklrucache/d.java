// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.jakewharton.disklrucache;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

// Referenced classes of package com.jakewharton.disklrucache:
//            DiskLruCache

final class d
{

    private final String a;
    private final long b[];
    private boolean c;
    private DiskLruCache.Editor d;
    private long e;
    private DiskLruCache f;

    private d(DiskLruCache disklrucache, String s)
    {
        f = disklrucache;
        super();
        a = s;
        b = new long[DiskLruCache.e(disklrucache)];
    }

    d(DiskLruCache disklrucache, String s, byte byte0)
    {
        this(disklrucache, s);
    }

    static long a(d d1, long l)
    {
        d1.e = l;
        return l;
    }

    static DiskLruCache.Editor a(d d1)
    {
        return d1.d;
    }

    static DiskLruCache.Editor a(d d1, DiskLruCache.Editor editor)
    {
        d1.d = editor;
        return editor;
    }

    private static IOException a(String as[])
    {
        throw new IOException((new StringBuilder("unexpected journal line: ")).append(Arrays.toString(as)).toString());
    }

    static void a(d d1, String as[])
    {
        if (as.length != DiskLruCache.e(d1.f))
        {
            throw a(as);
        }
        int i = 0;
        do
        {
            try
            {
                if (i >= as.length)
                {
                    break;
                }
                d1.b[i] = Long.parseLong(as[i]);
            }
            catch (NumberFormatException numberformatexception)
            {
                throw a(as);
            }
            i++;
        } while (true);
    }

    static boolean a(d d1, boolean flag)
    {
        d1.c = true;
        return true;
    }

    static long[] b(d d1)
    {
        return d1.b;
    }

    static String c(d d1)
    {
        return d1.a;
    }

    static boolean d(d d1)
    {
        return d1.c;
    }

    static long e(d d1)
    {
        return d1.e;
    }

    public final File getCleanFile(int i)
    {
        return new File(DiskLruCache.f(f), (new StringBuilder()).append(a).append(".").append(i).toString());
    }

    public final File getDirtyFile(int i)
    {
        return new File(DiskLruCache.f(f), (new StringBuilder()).append(a).append(".").append(i).append(".tmp").toString());
    }

    public final String getLengths()
    {
        StringBuilder stringbuilder = new StringBuilder();
        long al[] = b;
        int i = al.length;
        for (int j = 0; j < i; j++)
        {
            long l = al[j];
            stringbuilder.append(' ').append(l);
        }

        return stringbuilder.toString();
    }
}
