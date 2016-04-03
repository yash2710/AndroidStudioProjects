// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.volley;

import android.net.TrafficStats;
import android.os.Process;
import java.util.concurrent.BlockingQueue;

// Referenced classes of package com.android.volley:
//            VolleyError, Request, ResponseDelivery, Network, 
//            NetworkResponse, VolleyLog, Response, Cache

public class NetworkDispatcher extends Thread
{

    private final BlockingQueue a;
    private final Network b;
    private final Cache c;
    private final ResponseDelivery d;
    private volatile boolean e;

    public NetworkDispatcher(BlockingQueue blockingqueue, Network network, Cache cache, ResponseDelivery responsedelivery)
    {
        e = false;
        a = blockingqueue;
        b = network;
        c = cache;
        d = responsedelivery;
    }

    public void quit()
    {
        e = true;
        interrupt();
    }

    public void run()
    {
        Process.setThreadPriority(10);
_L2:
        Request request;
        VolleyError volleyerror;
        VolleyError volleyerror1;
        try
        {
            request = (Request)a.take();
        }
        catch (InterruptedException interruptedexception)
        {
            if (e)
            {
                return;
            }
            continue; /* Loop/switch isn't completed */
        }
        request.addMarker("network-queue-take");
        if (request.isCanceled())
        {
            request.a("network-discard-cancelled");
            continue; /* Loop/switch isn't completed */
        }
        if (android.os.Build.VERSION.SDK_INT >= 14)
        {
            TrafficStats.setThreadStatsTag(request.getTrafficStatsTag());
        }
        NetworkResponse networkresponse = b.performRequest(request);
        request.addMarker("network-http-complete");
        Exception exception;
        Object aobj[];
        if (networkresponse.notModified && request.hasHadResponseDelivered())
        {
            request.a("not-modified");
            continue; /* Loop/switch isn't completed */
        }
        try
        {
            Response response = request.parseNetworkResponse(networkresponse);
            request.addMarker("network-parse-complete");
            if (request.shouldCache() && response.cacheEntry != null)
            {
                c.put(request.getCacheKey(), response.cacheEntry);
                request.addMarker("network-cache-written");
            }
            request.markDelivered();
            d.postResponse(request, response);
        }
        // Misplaced declaration of an exception variable
        catch (VolleyError volleyerror)
        {
            volleyerror1 = request.parseNetworkError(volleyerror);
            d.postError(request, volleyerror1);
        }
        // Misplaced declaration of an exception variable
        catch (Exception exception)
        {
            aobj = new Object[1];
            aobj[0] = exception.toString();
            VolleyLog.e(exception, "Unhandled exception %s", aobj);
            d.postError(request, new VolleyError(exception));
        }
        if (true) goto _L2; else goto _L1
_L1:
    }
}
