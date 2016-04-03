// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.newrelic.agent.android.tracing;

import com.newrelic.agent.android.Measurements;
import com.newrelic.agent.android.TaskQueue;
import com.newrelic.agent.android.api.v2.TraceFieldInterface;
import com.newrelic.agent.android.api.v2.TraceMachineInterface;
import com.newrelic.agent.android.harvest.ActivityHistory;
import com.newrelic.agent.android.harvest.ActivitySighting;
import com.newrelic.agent.android.harvest.AgentHealth;
import com.newrelic.agent.android.harvest.Harvest;
import com.newrelic.agent.android.harvest.HarvestAdapter;
import com.newrelic.agent.android.logging.AgentLog;
import com.newrelic.agent.android.logging.AgentLogManager;
import com.newrelic.agent.android.stats.StatsEngine;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

// Referenced classes of package com.newrelic.agent.android.tracing:
//            ActivityTrace, TracingInactiveException, Trace, d, 
//            TraceLifecycleAware, TraceType

public class TraceMachine extends HarvestAdapter
{

    public static final String ACTIVITY_BACKGROUND_METRIC_PREFIX = "Mobile/Activity/Background/Name/";
    public static final String ACTIVITY_METRIC_PREFIX = "Mobile/Activity/Name/";
    public static final String ACTIVTY_DISPLAY_NAME_PREFIX = "Display ";
    public static final int HEALTHY_TRACE_TIMEOUT = 500;
    public static final String NR_TRACE_FIELD = "_nr_trace";
    public static final String NR_TRACE_TYPE = "Lcom/newrelic/agent/android/tracing/Trace;";
    public static final int UNHEALTHY_TRACE_TIMEOUT = 60000;
    private static final AgentLog a = AgentLogManager.getAgentLog();
    private static final Collection b = new CopyOnWriteArrayList();
    private static final ThreadLocal c = new ThreadLocal();
    private static final ThreadLocal d = new ThreadLocal();
    public static final AtomicBoolean disabled = new AtomicBoolean(false);
    private static final List e = new CopyOnWriteArrayList();
    private static TraceMachine f = null;
    private static TraceMachineInterface g;
    private ActivityTrace h;

    protected TraceMachine(Trace trace)
    {
        h = new ActivityTrace(trace);
        Harvest.addHarvestListener(this);
    }

    private static Trace a(String s)
    {
        if (isTracingInactive())
        {
            a.debug("Tried to register a new trace but tracing is inactive!");
            throw new TracingInactiveException();
        }
        Trace trace = getCurrentTrace();
        Trace trace1 = new Trace(s, trace.myUUID, f);
        try
        {
            f.h.addTrace(trace1);
        }
        catch (Exception exception)
        {
            throw new TracingInactiveException();
        }
        if (a.getLevel() == 5)
        {
            a.debug((new StringBuilder("Registering trace of ")).append(s).append(" with parent ").append(trace.displayName).toString());
        }
        trace.addChild(trace1);
        return trace1;
    }

    private static void a(Trace trace)
    {
        d d1;
        if (isTracingInactive() || trace == null)
        {
            return;
        }
        d1 = (d)d.get();
        if (!d1.empty()) goto _L2; else goto _L1
_L1:
        d1.push(trace);
_L4:
        c.set(trace);
        return;
_L2:
        if (d1.peek() != trace)
        {
            d1.push(trace);
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    public static void addTraceListener(TraceLifecycleAware tracelifecycleaware)
    {
        b.add(tracelifecycleaware);
    }

    public static void clearActivityHistory()
    {
        e.clear();
    }

    public static void endLastActivitySighting()
    {
        ActivitySighting activitysighting = getLastActivitySighting();
        if (activitysighting != null)
        {
            activitysighting.end(System.currentTimeMillis());
        }
    }

    public static void endTrace()
    {
        f.completeActivityTrace();
    }

    public static void endTrace(String s)
    {
        try
        {
            if (getActivityTrace().rootTrace.myUUID.toString().equals(s))
            {
                f.completeActivityTrace();
            }
            return;
        }
        catch (TracingInactiveException tracinginactiveexception)
        {
            return;
        }
    }

    public static void enterMethod(Trace trace, String s, ArrayList arraylist)
    {
        long l;
        long l1;
        long l2;
        Trace trace1;
        Trace trace2;
        try
        {
            if (isTracingInactive())
            {
                return;
            }
        }
        catch (TracingInactiveException tracinginactiveexception)
        {
            return;
        }
        catch (Exception exception)
        {
            a.error("Caught error while calling enterMethod()", exception);
            AgentHealth.noticeException(exception);
            return;
        }
        l = System.currentTimeMillis();
        l1 = f.h.lastUpdatedAt;
        l2 = f.h.startedAt;
        if (l1 + 500L >= l)
        {
            break MISSING_BLOCK_LABEL_75;
        }
        if (!f.h.hasMissingChildren())
        {
            a.debug("Completing activity trace after hitting healthy timeout (500ms)");
            f.completeActivityTrace();
            return;
        }
        if (60000L + l2 >= l)
        {
            break MISSING_BLOCK_LABEL_121;
        }
        a.debug("Completing activity trace after hitting unhealthy timeout (60000ms)");
        f.completeActivityTrace();
        return;
        if (isTracingInactive()) goto _L2; else goto _L1
_L1:
        if (c.get() != null) goto _L4; else goto _L3
_L3:
        c.set(trace);
        d.set(new d((byte)0));
        if (trace == null) goto _L2; else goto _L5
_L5:
        ((d)d.get()).push(trace);
_L7:
        if (a.getLevel() == 5)
        {
            a.debug((new StringBuilder("Trace ")).append(trace.myUUID.toString()).append(" is now active").toString());
        }
_L2:
        trace1 = a(s);
        a(trace1);
        trace1.scope = getCurrentScope();
        trace1.setAnnotationParams(arraylist);
        for (Iterator iterator = b.iterator(); iterator.hasNext(); ((TraceLifecycleAware)iterator.next()).onEnterMethod()) { }
        break MISSING_BLOCK_LABEL_370;
_L4:
        if (trace != null) goto _L7; else goto _L6
_L6:
label0:
        {
            if (!((d)d.get()).isEmpty())
            {
                break label0;
            }
            if (a.getLevel() == 5)
            {
                a.debug("No context to load!");
            }
            c.set(null);
        }
          goto _L2
        trace2 = (Trace)((d)d.get()).peek();
        c.set(trace2);
        trace = trace2;
          goto _L7
        trace1.entryTimestamp = System.currentTimeMillis();
        return;
    }

    public static void enterMethod(String s)
    {
        enterMethod(null, s, null);
    }

    public static void enterMethod(String s, ArrayList arraylist)
    {
        enterMethod(null, s, arraylist);
    }

    public static void enterNetworkSegment(String s)
    {
        try
        {
            if (isTracingInactive())
            {
                return;
            }
        }
        catch (TracingInactiveException tracinginactiveexception)
        {
            return;
        }
        catch (Exception exception)
        {
            a.error("Caught error while calling enterNetworkSegment()", exception);
            AgentHealth.noticeException(exception);
            return;
        }
        if (getCurrentTrace().getType() == TraceType.NETWORK)
        {
            exitMethod();
        }
        enterMethod(null, s, null);
        getCurrentTrace().setType(TraceType.NETWORK);
        return;
    }

    public static void exitMethod()
    {
        Trace trace;
        try
        {
            if (isTracingInactive())
            {
                return;
            }
        }
        catch (Exception exception)
        {
            a.error("Caught error while calling exitMethod()", exception);
            AgentHealth.noticeException(exception);
            return;
        }
        trace = (Trace)c.get();
        if (trace != null)
        {
            break MISSING_BLOCK_LABEL_51;
        }
        a.debug("threadLocalTrace is null");
        return;
        trace.exitTimestamp = System.currentTimeMillis();
        if (trace.threadId == 0L && g != null)
        {
            trace.threadId = g.getCurrentThreadId();
            trace.threadName = g.getCurrentThreadName();
        }
        for (Iterator iterator = b.iterator(); iterator.hasNext(); ((TraceLifecycleAware)iterator.next()).onExitMethod()) { }
        trace.complete();
        ((d)d.get()).pop();
        if (!((d)d.get()).empty())
        {
            break MISSING_BLOCK_LABEL_214;
        }
        c.set(null);
_L1:
        if (trace.getType() == TraceType.TRACE)
        {
            TaskQueue.queue(trace);
            return;
        }
        break MISSING_BLOCK_LABEL_257;
        TracingInactiveException tracinginactiveexception;
        tracinginactiveexception;
        c.remove();
        d.remove();
        if (trace.getType() == TraceType.TRACE)
        {
            TaskQueue.queue(trace);
            return;
        }
        break MISSING_BLOCK_LABEL_257;
        Trace trace1 = (Trace)((d)d.get()).peek();
        c.set(trace1);
        trace1.childExclusiveTime = trace1.childExclusiveTime + trace.getDuration();
          goto _L1
    }

    public static String formatActivityBackgroundMetricName(String s)
    {
        return (new StringBuilder("Mobile/Activity/Background/Name/")).append(s).toString();
    }

    public static String formatActivityDisplayName(String s)
    {
        return (new StringBuilder("Display ")).append(s).toString();
    }

    public static String formatActivityMetricName(String s)
    {
        return (new StringBuilder("Mobile/Activity/Name/")).append(s).toString();
    }

    public static ActivityHistory getActivityHistory()
    {
        return new ActivityHistory(e);
    }

    public static ActivityTrace getActivityTrace()
    {
        ActivityTrace activitytrace;
        try
        {
            activitytrace = f.h;
        }
        catch (NullPointerException nullpointerexception)
        {
            throw new TracingInactiveException();
        }
        return activitytrace;
    }

    public static String getCurrentScope()
    {
        if (isTracingInactive())
        {
            return null;
        }
        String s;
        try
        {
            if (g == null || g.isUIThread())
            {
                return f.h.rootTrace.metricName;
            }
            s = f.h.rootTrace.metricBackgroundName;
        }
        catch (Exception exception)
        {
            a.error("Caught error while calling getCurrentScope()", exception);
            AgentHealth.noticeException(exception);
            return null;
        }
        return s;
    }

    public static Trace getCurrentTrace()
    {
        if (isTracingInactive())
        {
            throw new TracingInactiveException();
        }
        Trace trace = (Trace)c.get();
        if (trace != null)
        {
            return trace;
        } else
        {
            return getRootTrace();
        }
    }

    public static Map getCurrentTraceParams()
    {
        return getCurrentTrace().getParams();
    }

    public static ActivitySighting getLastActivitySighting()
    {
        if (e.isEmpty())
        {
            return null;
        } else
        {
            return (ActivitySighting)e.get(-1 + e.size());
        }
    }

    public static Trace getRootTrace()
    {
        Trace trace;
        try
        {
            trace = f.h.rootTrace;
        }
        catch (NullPointerException nullpointerexception)
        {
            throw new TracingInactiveException();
        }
        return trace;
    }

    public static TraceMachine getTraceMachine()
    {
        return f;
    }

    public static void haltTracing()
    {
        if (isTracingInactive())
        {
            return;
        } else
        {
            TraceMachine tracemachine = f;
            f = null;
            tracemachine.h.discard();
            endLastActivitySighting();
            Harvest.removeHarvestListener(tracemachine);
            c.remove();
            d.remove();
            return;
        }
    }

    public static boolean isTracingActive()
    {
        return f != null;
    }

    public static boolean isTracingInactive()
    {
        return f == null;
    }

    public static void removeTraceListener(TraceLifecycleAware tracelifecycleaware)
    {
        b.remove(tracelifecycleaware);
    }

    public static void setCurrentDisplayName(String s)
    {
        if (isTracingInactive())
        {
            return;
        }
        try
        {
            getCurrentTrace().displayName = s;
            return;
        }
        catch (TracingInactiveException tracinginactiveexception)
        {
            return;
        }
    }

    public static void setCurrentTraceParam(String s, Object obj)
    {
        if (isTracingInactive())
        {
            return;
        }
        try
        {
            getCurrentTrace().getParams().put(s, obj);
            return;
        }
        catch (TracingInactiveException tracinginactiveexception)
        {
            return;
        }
    }

    public static void setRootDisplayName(String s)
    {
        if (isTracingInactive())
        {
            return;
        }
        try
        {
            Trace trace = getRootTrace();
            Measurements.renameActivity(trace.displayName, s);
            trace.metricName = formatActivityMetricName(s);
            trace.metricBackgroundName = formatActivityBackgroundMetricName(s);
            trace.displayName = s;
            getCurrentTrace().scope = getCurrentScope();
            return;
        }
        catch (TracingInactiveException tracinginactiveexception)
        {
            return;
        }
    }

    public static void setTraceMachineInterface(TraceMachineInterface tracemachineinterface)
    {
        g = tracemachineinterface;
    }

    public static void startTracing(String s)
    {
        startTracing(s, false);
    }

    public static void startTracing(String s, boolean flag)
    {
        if (disabled.get())
        {
            break MISSING_BLOCK_LABEL_288;
        }
        if (!Harvest.shouldCollectActivityTraces())
        {
            return;
        }
        Trace trace;
        try
        {
            if (isTracingActive())
            {
                f.completeActivityTrace();
            }
            c.remove();
            d.set(new d((byte)0));
            trace = new Trace();
            trace.metricName = formatActivityMetricName(s);
            trace.metricBackgroundName = formatActivityBackgroundMetricName(s);
        }
        catch (Exception exception)
        {
            a.error("Caught error while initializing TraceMachine, shutting it down", exception);
            AgentHealth.noticeException(exception);
            f = null;
            c.remove();
            d.remove();
            return;
        }
        if (!flag) goto _L2; else goto _L1
_L1:
        trace.displayName = s;
_L4:
        trace.entryTimestamp = System.currentTimeMillis();
        if (a.getLevel() == 5)
        {
            a.debug((new StringBuilder("Started trace of ")).append(s).append(":").append(trace.myUUID.toString()).toString());
        }
        f = new TraceMachine(trace);
        trace.traceMachine = f;
        a(trace);
        f.h.previousActivity = getLastActivitySighting();
        e.add(new ActivitySighting(trace.entryTimestamp, trace.displayName));
        for (Iterator iterator = b.iterator(); iterator.hasNext(); ((TraceLifecycleAware)iterator.next()).onTraceStart(f.h)) { }
        break MISSING_BLOCK_LABEL_288;
_L2:
        trace.displayName = formatActivityDisplayName(s);
        if (true) goto _L4; else goto _L3
_L3:
    }

    public static void unloadTraceContext(Object obj)
    {
        if (isTracingInactive())
        {
            return;
        }
        try
        {
            if (g == null || !g.isUIThread())
            {
                if (c.get() != null && a.getLevel() == 5)
                {
                    a.debug((new StringBuilder("Trace ")).append(((Trace)c.get()).myUUID.toString()).append(" is now inactive").toString());
                }
                c.remove();
                d.remove();
                ((TraceFieldInterface)obj)._nr_setTrace(null);
                return;
            }
        }
        catch (Exception exception)
        {
            a.error("Caught error while calling unloadTraceContext()", exception);
            AgentHealth.noticeException(exception);
        }
        return;
    }

    public void completeActivityTrace()
    {
        if (isTracingInactive())
        {
            return;
        }
        TraceMachine tracemachine = f;
        f = null;
        tracemachine.h.complete();
        endLastActivitySighting();
        for (Iterator iterator = b.iterator(); iterator.hasNext(); ((TraceLifecycleAware)iterator.next()).onTraceComplete(tracemachine.h)) { }
        Harvest.removeHarvestListener(tracemachine);
    }

    public void onHarvestBefore()
    {
        long l;
        long l1;
        long l2;
        if (!isTracingActive())
        {
            break MISSING_BLOCK_LABEL_112;
        }
        l = System.currentTimeMillis();
        l1 = f.h.lastUpdatedAt;
        l2 = f.h.startedAt;
        if (l1 + 500L >= l || f.h.hasMissingChildren()) goto _L2; else goto _L1
_L1:
        a.debug("Completing activity trace after hitting healthy timeout (500ms)");
        completeActivityTrace();
        StatsEngine.get().inc("Supportability/AgentHealth/HealthyActivityTraces");
_L4:
        return;
_L2:
        if (60000L + l2 >= l) goto _L4; else goto _L3
_L3:
        a.debug("Completing activity trace after hitting unhealthy timeout (60000ms)");
        completeActivityTrace();
        StatsEngine.get().inc("Supportability/AgentHealth/UnhealthyActivityTraces");
        return;
        a.debug("TraceMachine is inactive");
        return;
    }

    public void onHarvestSendFailed()
    {
        try
        {
            f.h.incrementReportAttemptCount();
            return;
        }
        catch (NullPointerException nullpointerexception)
        {
            return;
        }
    }

    public void storeCompletedTrace(Trace trace)
    {
        try
        {
            if (isTracingInactive())
            {
                a.debug("Attempted to store a completed trace with no trace machine!");
                return;
            }
        }
        catch (Exception exception)
        {
            a.error("Caught error while calling storeCompletedTrace()", exception);
            AgentHealth.noticeException(exception);
            return;
        }
        h.addCompletedTrace(trace);
        return;
    }

}
