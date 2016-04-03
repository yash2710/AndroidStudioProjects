// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.newrelic.agent.android.stats;

import com.newrelic.agent.android.TaskQueue;
import com.newrelic.agent.android.harvest.HarvestAdapter;
import com.newrelic.agent.android.metric.Metric;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class StatsEngine extends HarvestAdapter
{

    public static final StatsEngine INSTANCE = new StatsEngine();
    private ConcurrentHashMap a;
    public boolean enabled;

    private StatsEngine()
    {
        enabled = true;
        a = new ConcurrentHashMap();
    }

    private Metric a(String s)
    {
        Metric metric = (Metric)a.get(s);
        if (metric == null)
        {
            metric = new Metric(s);
            if (enabled)
            {
                a.put(s, metric);
            }
        }
        return metric;
    }

    public static void disable()
    {
        com/newrelic/agent/android/stats/StatsEngine;
        JVM INSTR monitorenter ;
        INSTANCE.enabled = false;
        com/newrelic/agent/android/stats/StatsEngine;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public static void enable()
    {
        com/newrelic/agent/android/stats/StatsEngine;
        JVM INSTR monitorenter ;
        INSTANCE.enabled = true;
        com/newrelic/agent/android/stats/StatsEngine;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public static StatsEngine get()
    {
        return INSTANCE;
    }

    public static void populateMetrics()
    {
        for (Iterator iterator = INSTANCE.a.entrySet().iterator(); iterator.hasNext(); TaskQueue.queue((Metric)((java.util.Map.Entry)iterator.next()).getValue())) { }
    }

    public static void reset()
    {
        INSTANCE.a.clear();
    }

    public void inc(String s)
    {
        synchronized (a(s))
        {
            metric.increment();
        }
    }

    public void inc(String s, long l)
    {
        synchronized (a(s))
        {
            metric.increment(l);
        }
    }

    public void onHarvest()
    {
        populateMetrics();
        reset();
    }

    public void sample(String s, float f)
    {
        Metric metric = a(s);
        metric;
        JVM INSTR monitorenter ;
        double d = f;
        metric.sample(d);
        metric;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        metric;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public void sampleTimeMs(String s, long l)
    {
        sample(s, (float)l / 1000F);
    }

}
