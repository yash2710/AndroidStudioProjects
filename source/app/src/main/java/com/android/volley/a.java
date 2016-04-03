// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.volley;

import java.util.concurrent.BlockingQueue;

// Referenced classes of package com.android.volley:
//            CacheDispatcher, Request

final class a
    implements Runnable
{

    private Request a;
    private CacheDispatcher b;

    a(CacheDispatcher cachedispatcher, Request request)
    {
        b = cachedispatcher;
        a = request;
        super();
    }

    public final void run()
    {
        try
        {
            CacheDispatcher.a(b).put(a);
            return;
        }
        catch (InterruptedException interruptedexception)
        {
            return;
        }
    }
}
