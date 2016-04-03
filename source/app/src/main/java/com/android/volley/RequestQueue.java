// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.volley;

import android.os.Handler;
import android.os.Looper;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

// Referenced classes of package com.android.volley:
//            ExecutorDelivery, NetworkDispatcher, Request, VolleyLog, 
//            e, CacheDispatcher, Cache, Network, 
//            ResponseDelivery

public class RequestQueue
{

    private AtomicInteger a;
    private final Map b;
    private final Set c;
    private final PriorityBlockingQueue d;
    private final PriorityBlockingQueue e;
    private final Cache f;
    private final Network g;
    private final ResponseDelivery h;
    private NetworkDispatcher i[];
    private CacheDispatcher j;

    public RequestQueue(Cache cache, Network network)
    {
        this(cache, network, 6);
    }

    public RequestQueue(Cache cache, Network network, int k)
    {
        this(cache, network, k, ((ResponseDelivery) (new ExecutorDelivery(new Handler(Looper.getMainLooper())))));
    }

    public RequestQueue(Cache cache, Network network, int k, ResponseDelivery responsedelivery)
    {
        a = new AtomicInteger();
        b = new HashMap();
        c = new HashSet();
        d = new PriorityBlockingQueue();
        e = new PriorityBlockingQueue();
        f = cache;
        g = network;
        i = new NetworkDispatcher[k];
        h = responsedelivery;
    }

    final void a(Request request)
    {
        Map map;
        String s;
        Queue queue;
        synchronized (c)
        {
            c.remove(request);
        }
        if (!request.shouldCache()) goto _L2; else goto _L1
_L1:
        map = b;
        map;
        JVM INSTR monitorenter ;
        s = request.getCacheKey();
        queue = (Queue)b.remove(s);
        if (queue == null)
        {
            break MISSING_BLOCK_LABEL_112;
        }
        if (VolleyLog.DEBUG)
        {
            Object aobj[] = new Object[2];
            aobj[0] = Integer.valueOf(queue.size());
            aobj[1] = s;
            VolleyLog.v("Releasing %d waiting requests for cacheKey=%s.", aobj);
        }
        d.addAll(queue);
        map;
        JVM INSTR monitorexit ;
_L2:
        return;
        Exception exception1;
        exception1;
        throw exception1;
    }

    public Request add(Request request)
    {
        request.setRequestQueue(this);
        synchronized (c)
        {
            c.add(request);
        }
        request.setSequence(getSequenceNumber());
        request.addMarker("add-to-queue");
        if (!request.shouldCache())
        {
            e.add(request);
            return request;
        }
        Map map = b;
        map;
        JVM INSTR monitorenter ;
        String s;
        Object obj;
        s = request.getCacheKey();
        if (!b.containsKey(s))
        {
            break MISSING_BLOCK_LABEL_177;
        }
        obj = (Queue)b.get(s);
        if (obj != null)
        {
            break MISSING_BLOCK_LABEL_121;
        }
        obj = new LinkedList();
        ((Queue) (obj)).add(request);
        b.put(s, obj);
        if (VolleyLog.DEBUG)
        {
            VolleyLog.v("Request for cacheKey=%s is in flight, putting on hold.", new Object[] {
                s
            });
        }
_L1:
        map;
        JVM INSTR monitorexit ;
        return request;
        Exception exception1;
        exception1;
        throw exception1;
        b.put(s, null);
        d.add(request);
          goto _L1
    }

    public void cancelAll()
    {
        Set set = c;
        set;
        JVM INSTR monitorenter ;
        for (Iterator iterator = c.iterator(); iterator.hasNext(); ((Request)iterator.next()).cancel()) { }
        break MISSING_BLOCK_LABEL_46;
        Exception exception;
        exception;
        throw exception;
        set;
        JVM INSTR monitorexit ;
    }

    public void cancelAll(RequestFilter requestfilter)
    {
        Set set = c;
        set;
        JVM INSTR monitorenter ;
        Iterator iterator = c.iterator();
        do
        {
            if (!iterator.hasNext())
            {
                break;
            }
            Request request = (Request)iterator.next();
            if (requestfilter.apply(request))
            {
                request.cancel();
            }
        } while (true);
        break MISSING_BLOCK_LABEL_64;
        Exception exception;
        exception;
        throw exception;
        set;
        JVM INSTR monitorexit ;
    }

    public void cancelAll(Object obj)
    {
        if (obj == null)
        {
            throw new IllegalArgumentException("Cannot cancelAll with a null tag");
        } else
        {
            cancelAll(((RequestFilter) (new e(this, obj))));
            return;
        }
    }

    public Cache getCache()
    {
        return f;
    }

    public int getSequenceNumber()
    {
        return a.incrementAndGet();
    }

    public void start()
    {
        stop();
        j = new CacheDispatcher(d, e, f, h);
        j.start();
        for (int k = 0; k < i.length; k++)
        {
            NetworkDispatcher networkdispatcher = new NetworkDispatcher(e, g, f, h);
            i[k] = networkdispatcher;
            networkdispatcher.start();
        }

    }

    public void stop()
    {
        if (j != null)
        {
            j.quit();
        }
        for (int k = 0; k < i.length; k++)
        {
            if (i[k] != null)
            {
                i[k].quit();
            }
        }

    }

    private class RequestFilter
    {

        public abstract boolean apply(Request request);
    }

}
