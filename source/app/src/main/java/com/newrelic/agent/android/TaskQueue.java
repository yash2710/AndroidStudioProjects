// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.newrelic.agent.android;

import com.newrelic.agent.android.harvest.AgentHealth;
import com.newrelic.agent.android.harvest.AgentHealthException;
import com.newrelic.agent.android.harvest.Harvest;
import com.newrelic.agent.android.harvest.HarvestAdapter;
import com.newrelic.agent.android.measurement.http.HttpTransactionMeasurement;
import com.newrelic.agent.android.metric.Metric;
import com.newrelic.agent.android.tracing.ActivityTrace;
import com.newrelic.agent.android.tracing.Trace;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

// Referenced classes of package com.newrelic.agent.android:
//            d, Measurements

public class TaskQueue extends HarvestAdapter
{

    private static final ScheduledExecutorService a = Executors.newSingleThreadScheduledExecutor();
    private static final ConcurrentLinkedQueue b = new ConcurrentLinkedQueue();
    private static final Runnable c = new d();
    private static Future d;

    public TaskQueue()
    {
    }

    static void a()
    {
        if (b.size() == 0)
        {
            break MISSING_BLOCK_LABEL_133;
        }
        Measurements.setBroadcastNewMeasurements(false);
_L2:
        if (b.isEmpty())
        {
            break; /* Loop/switch isn't completed */
        }
        Exception exception;
        Object obj;
        obj = b.remove();
        if (obj instanceof ActivityTrace)
        {
            Harvest.addActivityTrace((ActivityTrace)obj);
            continue; /* Loop/switch isn't completed */
        }
        if (obj instanceof Metric)
        {
            Harvest.addMetric((Metric)obj);
            continue; /* Loop/switch isn't completed */
        }
        if (obj instanceof AgentHealthException)
        {
            Harvest.addAgentHealthException((AgentHealthException)obj);
            continue; /* Loop/switch isn't completed */
        }
        if (obj instanceof Trace)
        {
            Measurements.addTracedMethod((Trace)obj);
            continue; /* Loop/switch isn't completed */
        }
        try
        {
            if (obj instanceof HttpTransactionMeasurement)
            {
                Measurements.addHttpTransaction((HttpTransactionMeasurement)obj);
            }
        }
        // Misplaced declaration of an exception variable
        catch (Exception exception)
        {
            exception.printStackTrace();
            AgentHealth.noticeException(exception);
        }
        if (true) goto _L2; else goto _L1
_L1:
        Measurements.broadcast();
        Measurements.setBroadcastNewMeasurements(true);
    }

    public static void backgroundDequeue()
    {
        a.execute(c);
    }

    public static void clear()
    {
        b.clear();
    }

    public static void queue(Object obj)
    {
        b.add(obj);
    }

    public static int size()
    {
        return b.size();
    }

    public static void start()
    {
        if (d != null)
        {
            return;
        } else
        {
            d = a.scheduleAtFixedRate(c, 0L, 1000L, TimeUnit.MILLISECONDS);
            return;
        }
    }

    public static void stop()
    {
        if (d == null)
        {
            return;
        } else
        {
            d.cancel(true);
            d = null;
            return;
        }
    }

    public static void synchronousDequeue()
    {
        Future future = a.submit(c);
        try
        {
            future.get();
            return;
        }
        catch (InterruptedException interruptedexception)
        {
            interruptedexception.printStackTrace();
            return;
        }
        catch (ExecutionException executionexception)
        {
            executionexception.printStackTrace();
        }
    }

}
