// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.newrelic.agent.android.background;

import com.newrelic.agent.android.logging.AgentLog;
import com.newrelic.agent.android.logging.AgentLogManager;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

// Referenced classes of package com.newrelic.agent.android.background:
//            a, ApplicationStateEvent, ApplicationStateListener

public class ApplicationStateMonitor
    implements Runnable
{

    private static final AgentLog a = AgentLogManager.getAgentLog();
    private static ApplicationStateMonitor i;
    private long b;
    private long c;
    private final Object d;
    private final int e;
    private final ArrayList f;
    private boolean g;
    private final Object h;

    private ApplicationStateMonitor()
    {
        this(5, 5, TimeUnit.SECONDS, 5000);
    }

    private ApplicationStateMonitor(int j, int k, TimeUnit timeunit, int l)
    {
        b = 0L;
        c = 0L;
        d = new Object();
        f = new ArrayList();
        g = true;
        h = new Object();
        ScheduledThreadPoolExecutor scheduledthreadpoolexecutor = new ScheduledThreadPoolExecutor(1, new a(this));
        e = 5000;
        scheduledthreadpoolexecutor.scheduleAtFixedRate(this, 5L, 5L, timeunit);
        a.info("Application state monitor has started");
    }

    private long a()
    {
        Object obj = h;
        obj;
        JVM INSTR monitorenter ;
        synchronized (d)
        {
            if (c != 0L)
            {
                break MISSING_BLOCK_LABEL_29;
            }
        }
        obj;
        JVM INSTR monitorexit ;
        return 0L;
        long l = System.currentTimeMillis() - c;
        obj1;
        JVM INSTR monitorexit ;
        obj;
        JVM INSTR monitorexit ;
        return l;
        Exception exception;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
        exception1;
        obj1;
        JVM INSTR monitorexit ;
        throw exception1;
    }

    public static ApplicationStateMonitor getInstance()
    {
        if (i == null)
        {
            i = new ApplicationStateMonitor();
        }
        return i;
    }

    public void activityStarted()
    {
        Object obj = h;
        obj;
        JVM INSTR monitorenter ;
        synchronized (d)
        {
            b = 1L + b;
            if (b == 1L)
            {
                c = 0L;
            }
        }
        if (g)
        {
            break MISSING_BLOCK_LABEL_154;
        }
        a.verbose("Application appears to be in the foreground");
        ArrayList arraylist1;
        synchronized (f)
        {
            arraylist1 = new ArrayList(f);
        }
        ApplicationStateEvent applicationstateevent = new ApplicationStateEvent(this);
        for (Iterator iterator = arraylist1.iterator(); iterator.hasNext(); ((ApplicationStateListener)iterator.next()).applicationForegrounded(applicationstateevent)) { }
        break MISSING_BLOCK_LABEL_149;
        Exception exception;
        exception;
        throw exception;
        exception1;
        obj1;
        JVM INSTR monitorexit ;
        throw exception1;
        exception2;
        arraylist;
        JVM INSTR monitorexit ;
        throw exception2;
        g = true;
        obj;
        JVM INSTR monitorexit ;
    }

    public void activityStopped()
    {
        Object obj = h;
        obj;
        JVM INSTR monitorenter ;
        synchronized (d)
        {
            b = b - 1L;
            if (b == 0L)
            {
                c = System.currentTimeMillis();
            }
        }
        obj;
        JVM INSTR monitorexit ;
        return;
        exception1;
        obj1;
        JVM INSTR monitorexit ;
        throw exception1;
        Exception exception;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public void addApplicationStateListener(ApplicationStateListener applicationstatelistener)
    {
        synchronized (f)
        {
            f.add(applicationstatelistener);
        }
    }

    public void removeApplicationStateListener(ApplicationStateListener applicationstatelistener)
    {
        synchronized (f)
        {
            f.remove(applicationstatelistener);
        }
    }

    public void run()
    {
        Object obj = h;
        obj;
        JVM INSTR monitorenter ;
        if (a() < (long)e || !g)
        {
            break MISSING_BLOCK_LABEL_123;
        }
        a.verbose("Application appears to have gone to the background");
        ArrayList arraylist1;
        synchronized (f)
        {
            arraylist1 = new ArrayList(f);
        }
        ApplicationStateEvent applicationstateevent = new ApplicationStateEvent(this);
        for (Iterator iterator = arraylist1.iterator(); iterator.hasNext(); ((ApplicationStateListener)iterator.next()).applicationBackgrounded(applicationstateevent)) { }
        break MISSING_BLOCK_LABEL_118;
        Exception exception;
        exception;
        throw exception;
        exception1;
        arraylist;
        JVM INSTR monitorexit ;
        throw exception1;
        g = false;
        obj;
        JVM INSTR monitorexit ;
    }

}
