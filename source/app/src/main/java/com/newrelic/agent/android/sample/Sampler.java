// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.newrelic.agent.android.sample;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Process;
import com.newrelic.agent.android.harvest.AgentHealth;
import com.newrelic.agent.android.logging.AgentLog;
import com.newrelic.agent.android.logging.AgentLogManager;
import com.newrelic.agent.android.tracing.ActivityTrace;
import com.newrelic.agent.android.tracing.Sample;
import com.newrelic.agent.android.tracing.TraceLifecycleAware;
import com.newrelic.agent.android.tracing.TraceMachine;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

public class Sampler
    implements TraceLifecycleAware, Runnable
{

    private static final int a[];
    private static final AgentLog b = AgentLogManager.getAgentLog();
    private static final ReentrantLock c = new ReentrantLock();
    private static Sampler d;
    private static boolean e = false;
    private final ActivityManager f;
    private final EnumMap g = new EnumMap(com/newrelic/agent/android/tracing/Sample$SampleType);
    private final ScheduledExecutorService h = Executors.newSingleThreadScheduledExecutor();
    private final AtomicBoolean i = new AtomicBoolean(false);
    private ScheduledFuture j;
    private Long k;
    private Long l;
    private RandomAccessFile m;
    private RandomAccessFile n;

    private Sampler(Context context)
    {
        f = (ActivityManager)context.getSystemService("activity");
        g.put(com.newrelic.agent.android.tracing.Sample.SampleType.MEMORY, new ArrayList());
        g.put(com.newrelic.agent.android.tracing.Sample.SampleType.CPU, new ArrayList());
    }

    private Collection a(com.newrelic.agent.android.tracing.Sample.SampleType sampletype)
    {
        return (Collection)g.get(sampletype);
    }

    private void a()
    {
        for (Iterator iterator = g.values().iterator(); iterator.hasNext(); ((Collection)iterator.next()).clear()) { }
    }

    private void a(boolean flag)
    {
        c.lock();
        if (!i.get())
        {
            c.unlock();
            return;
        }
        i.set(false);
        j.cancel(flag);
        k = null;
        l = null;
        if (n != null && m != null)
        {
            try
            {
                n.close();
                m.close();
                n = null;
                m = null;
            }
            catch (IOException ioexception)
            {
                b.debug((new StringBuilder("Exception hit while resetting CPU sampler: ")).append(ioexception.getMessage()).toString());
                AgentHealth.noticeException(ioexception);
            }
        }
        c.unlock();
        b.debug("Sampler stopped");
    }

    public static Map copySamples()
    {
        c.lock();
        if (d == null)
        {
            c.unlock();
            return new HashMap();
        }
        EnumMap enummap = new EnumMap(d.g);
        com.newrelic.agent.android.tracing.Sample.SampleType sampletype;
        for (Iterator iterator = d.g.keySet().iterator(); iterator.hasNext(); enummap.put(sampletype, new ArrayList((Collection)d.g.get(sampletype))))
        {
            sampletype = (com.newrelic.agent.android.tracing.Sample.SampleType)iterator.next();
        }

        c.unlock();
        return Collections.unmodifiableMap(enummap);
    }

    public static void init(Context context)
    {
        c.lock();
        d = new Sampler(context);
        c.unlock();
        TraceMachine.addTraceListener(d);
        b.debug("Sampler Initialized");
    }

    public static boolean isRunning()
    {
        while (d == null || d.j.isDone()) 
        {
            return false;
        }
        return true;
    }

    public static Sample sampleMemory()
    {
        int i1;
        if (d != null)
        {
            if ((i1 = d.f.getProcessMemoryInfo(a)[0].getTotalPss()) >= 0)
            {
                Sample sample = new Sample(com.newrelic.agent.android.tracing.Sample.SampleType.MEMORY);
                sample.setSampleValue((double)i1 / 1024D);
                return sample;
            }
        }
        return null;
    }

    public static void shutdown()
    {
        c.lock();
        if (d == null)
        {
            c.unlock();
            return;
        } else
        {
            TraceMachine.removeTraceListener(d);
            stop();
            d = null;
            c.unlock();
            return;
        }
    }

    public static void start()
    {
        c.lock();
        if (d == null)
        {
            c.unlock();
            return;
        }
        Sampler sampler = d;
        if (!sampler.i.get())
        {
            sampler.a();
            sampler.i.set(true);
            sampler.j = sampler.h.scheduleAtFixedRate(sampler, 0L, 100L, TimeUnit.MILLISECONDS);
        }
        c.unlock();
        b.debug("Sampler started");
    }

    public static void stop()
    {
        c.lock();
        if (d == null)
        {
            c.unlock();
            return;
        } else
        {
            d.a(false);
            c.unlock();
            return;
        }
    }

    public static void stopNow()
    {
        c.lock();
        if (d == null)
        {
            c.unlock();
            return;
        } else
        {
            d.a(true);
            c.unlock();
            return;
        }
    }

    public void onEnterMethod()
    {
        if (i.get())
        {
            return;
        } else
        {
            start();
            return;
        }
    }

    public void onExitMethod()
    {
    }

    public void onTraceComplete(ActivityTrace activitytrace)
    {
        stop();
        activitytrace.setVitals(copySamples());
        a();
    }

    public void onTraceStart(ActivityTrace activitytrace)
    {
        start();
    }

    public void run()
    {
        if (!i.get())
        {
            break MISSING_BLOCK_LABEL_97;
        }
        c.lock();
        Sample sample = sampleMemory();
        if (sample == null)
        {
            break MISSING_BLOCK_LABEL_38;
        }
        a(com.newrelic.agent.android.tracing.Sample.SampleType.MEMORY).add(sample);
        Sample sample1 = sampleCpu();
        if (sample1 == null)
        {
            break MISSING_BLOCK_LABEL_64;
        }
        a(com.newrelic.agent.android.tracing.Sample.SampleType.CPU).add(sample1);
        Exception exception1;
        try
        {
            c.unlock();
            return;
        }
        catch (Exception exception)
        {
            b.error("Caught exception while running the sampler", exception);
            AgentHealth.noticeException(exception);
        }
        break MISSING_BLOCK_LABEL_97;
        exception1;
        c.unlock();
        throw exception1;
    }

    public Sample sampleCpu()
    {
        if (e)
        {
            return null;
        }
        if (m != null && n != null)
        {
            break MISSING_BLOCK_LABEL_266;
        }
        m = new RandomAccessFile("/proc/stat", "r");
        n = new RandomAccessFile((new StringBuilder("/proc/")).append(a[0]).append("/stat").toString(), "r");
_L1:
        long l1;
        long l2;
        String s = m.readLine();
        String s1 = n.readLine();
        String as[] = s.split(" ");
        String as1[] = s1.split(" ");
        l1 = Long.parseLong(as[2]) + Long.parseLong(as[3]) + Long.parseLong(as[4]) + Long.parseLong(as[5]) + Long.parseLong(as[6]) + Long.parseLong(as[7]) + Long.parseLong(as[8]);
        l2 = Long.parseLong(as1[13]) + Long.parseLong(as1[14]);
        if (k != null || l != null)
        {
            break MISSING_BLOCK_LABEL_285;
        }
        k = Long.valueOf(l1);
        l = Long.valueOf(l2);
        Exception exception;
        return null;
        try
        {
            m.seek(0L);
            n.seek(0L);
        }
        // Misplaced declaration of an exception variable
        catch (Exception exception)
        {
            e = true;
            b.debug((new StringBuilder("Exception hit while CPU sampling: ")).append(exception.getMessage()).toString());
            AgentHealth.noticeException(exception);
            return null;
        }
          goto _L1
        Sample sample;
        sample = new Sample(com.newrelic.agent.android.tracing.Sample.SampleType.CPU);
        sample.setSampleValue(100D * ((double)(l2 - l.longValue()) / (double)(l1 - k.longValue())));
        k = Long.valueOf(l1);
        l = Long.valueOf(l2);
        return sample;
    }

    static 
    {
        int ai[] = new int[1];
        ai[0] = Process.myPid();
        a = ai;
    }
}
