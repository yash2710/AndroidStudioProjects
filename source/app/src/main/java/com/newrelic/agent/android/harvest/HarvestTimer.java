// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.newrelic.agent.android.harvest;

import com.newrelic.agent.android.logging.AgentLog;
import com.newrelic.agent.android.logging.AgentLogManager;
import com.newrelic.agent.android.stats.TicToc;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

// Referenced classes of package com.newrelic.agent.android.harvest:
//            AgentHealth, Harvester, a

public class HarvestTimer
    implements Runnable
{

    private final ScheduledExecutorService a = Executors.newSingleThreadScheduledExecutor();
    private final AgentLog b = AgentLogManager.getAgentLog();
    private ScheduledFuture c;
    private long d;
    private long e;
    protected final Harvester harvester;
    protected long lastTickTime;

    public HarvestTimer(Harvester harvester1)
    {
        d = 60000L;
        harvester = harvester1;
    }

    public boolean isRunning()
    {
        return c != null;
    }

    public void run()
    {
        this;
        JVM INSTR monitorenter ;
        long l = timeSinceLastTick();
        if (1000L + l >= d || l == -1L) goto _L2; else goto _L1
_L1:
        b.debug((new StringBuilder("HarvestTimer: Tick is too soon (")).append(l).append(" delta) Last tick time: ").append(lastTickTime).append(" . Skipping.").toString());
_L3:
        this;
        JVM INSTR monitorexit ;
        return;
_L2:
        long l1;
        b.debug((new StringBuilder("HarvestTimer: time since last tick: ")).append(l).toString());
        l1 = System.currentTimeMillis();
        tick();
_L4:
        lastTickTime = l1;
        b.debug((new StringBuilder("Set last tick time to: ")).append(lastTickTime).toString());
          goto _L3
        Exception exception1;
        exception1;
        b.error((new StringBuilder("HarvestTimer: Exception in timer tick: ")).append(exception1.getMessage()).toString());
        exception1.printStackTrace();
        AgentHealth.noticeException(exception1);
          goto _L3
        Exception exception;
        exception;
        throw exception;
        Exception exception2;
        exception2;
        b.error((new StringBuilder("HarvestTimer: Exception in timer tick: ")).append(exception2.getMessage()).toString());
        exception2.printStackTrace();
        AgentHealth.noticeException(exception2);
          goto _L4
    }

    public void setPeriod(long l)
    {
        d = l;
    }

    public void start()
    {
        if (isRunning())
        {
            b.warning("HarvestTimer: Attempting to start while already running");
            return;
        }
        if (d <= 0L)
        {
            b.error("HarvestTimer: Refusing to start with a period of 0 ms");
            return;
        } else
        {
            b.debug((new StringBuilder("HarvestTimer: Starting with a period of ")).append(d).append("ms").toString());
            e = System.currentTimeMillis();
            c = a.scheduleAtFixedRate(this, 0L, d, TimeUnit.MILLISECONDS);
            harvester.start();
            return;
        }
    }

    public void stop()
    {
        if (!isRunning())
        {
            b.warning("HarvestTimer: Attempting to stop when not running");
            return;
        } else
        {
            b.debug("HarvestTimer: Stopped.");
            e = 0L;
            harvester.stop();
            c.cancel(true);
            c = null;
            return;
        }
    }

    protected void tick()
    {
        b.debug("Harvest: tick");
        TicToc tictoc = new TicToc();
        tictoc.tic();
        long l;
        try
        {
            harvester.execute();
        }
        catch (Exception exception)
        {
            b.error((new StringBuilder("HarvestTimer: Exception in harvest execute: ")).append(exception.getMessage()).toString());
            exception.printStackTrace();
            AgentHealth.noticeException(exception);
        }
        b.debug("Harvest: executed");
        if (harvester.isDisabled())
        {
            stop();
        }
        l = tictoc.toc();
        b.debug((new StringBuilder("HarvestTimer tick took ")).append(l).append("ms").toString());
    }

    public void tickNow()
    {
        ScheduledFuture scheduledfuture = a.schedule(new a(this, this), 0L, TimeUnit.SECONDS);
        try
        {
            scheduledfuture.get();
            return;
        }
        catch (Exception exception)
        {
            b.error((new StringBuilder("Exception waiting for tickNow to finish: ")).append(exception.getMessage()).toString());
            exception.printStackTrace();
            AgentHealth.noticeException(exception);
            return;
        }
    }

    public long timeSinceLastTick()
    {
        if (lastTickTime == 0L)
        {
            return -1L;
        } else
        {
            return System.currentTimeMillis() - lastTickTime;
        }
    }

    public long timeSinceStart()
    {
        if (e == 0L)
        {
            return 0L;
        } else
        {
            return System.currentTimeMillis() - e;
        }
    }
}
