// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.jakewharton.disklrucache;

import java.util.concurrent.Callable;

// Referenced classes of package com.jakewharton.disklrucache:
//            DiskLruCache

final class a
    implements Callable
{

    private DiskLruCache a;

    a(DiskLruCache disklrucache)
    {
        a = disklrucache;
        super();
    }

    public final volatile Object call()
    {
        return call();
    }

    public final Void call()
    {
label0:
        {
            synchronized (a)
            {
                if (DiskLruCache.a(a) != null)
                {
                    break label0;
                }
            }
            return null;
        }
        DiskLruCache.b(a);
        if (DiskLruCache.c(a))
        {
            DiskLruCache.d(a);
            DiskLruCache.a(a, 0);
        }
        disklrucache;
        JVM INSTR monitorexit ;
        return null;
        exception;
        throw exception;
    }
}
