// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.volley;

import android.os.Process;
import java.util.concurrent.BlockingQueue;

// Referenced classes of package com.android.volley:
//            VolleyLog, Cache, Request, NetworkResponse, 
//            ResponseDelivery, Response, a

public class CacheDispatcher extends Thread
{

    private static final boolean a;
    private final BlockingQueue b;
    private final BlockingQueue c;
    private final Cache d;
    private final ResponseDelivery e;
    private volatile boolean f;

    public CacheDispatcher(BlockingQueue blockingqueue, BlockingQueue blockingqueue1, Cache cache, ResponseDelivery responsedelivery)
    {
        f = false;
        b = blockingqueue;
        c = blockingqueue1;
        d = cache;
        e = responsedelivery;
    }

    static BlockingQueue a(CacheDispatcher cachedispatcher)
    {
        return cachedispatcher.c;
    }

    public void quit()
    {
        f = true;
        interrupt();
    }

    public void run()
    {
        if (a)
        {
            VolleyLog.v("start new dispatcher", new Object[0]);
        }
        Process.setThreadPriority(10);
        d.initialize();
_L1:
        Request request;
label0:
        {
            do
            {
                try
                {
                    do
                    {
                        request = (Request)b.take();
                        request.addMarker("cache-queue-take");
                        if (!request.isCanceled())
                        {
                            break label0;
                        }
                        request.a("cache-discard-canceled");
                    } while (true);
                }
                catch (InterruptedException interruptedexception) { }
            } while (!f);
            return;
        }
        Cache.Entry entry = d.get(request.getCacheKey());
        if (entry != null)
        {
            break MISSING_BLOCK_LABEL_110;
        }
        request.addMarker("cache-miss");
        c.put(request);
          goto _L1
label1:
        {
            if (!entry.isExpired())
            {
                break label1;
            }
            request.addMarker("cache-hit-expired");
            request.setCacheEntry(entry);
            c.put(request);
        }
          goto _L1
        Response response;
label2:
        {
            request.addMarker("cache-hit");
            response = request.parseNetworkResponse(new NetworkResponse(entry.data, entry.responseHeaders));
            request.addMarker("cache-hit-parsed");
            if (entry.refreshNeeded())
            {
                break label2;
            }
            e.postResponse(request, response);
        }
          goto _L1
        request.addMarker("cache-hit-refresh-needed");
        request.setCacheEntry(entry);
        response.intermediate = true;
        e.postResponse(request, response, new a(this, request));
          goto _L1
    }

    static 
    {
        a = VolleyLog.DEBUG;
    }
}
