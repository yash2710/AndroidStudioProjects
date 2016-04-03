// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.newrelic.agent.android;

import android.content.Context;
import com.newrelic.agent.android.logging.AgentLog;
import com.newrelic.agent.android.logging.AgentLogManager;
import com.newrelic.agent.android.logging.AndroidAgentLog;
import com.newrelic.agent.android.logging.NullAgentLog;
import com.newrelic.agent.android.measurement.http.HttpTransactionMeasurement;
import com.newrelic.agent.android.metric.MetricUnit;
import com.newrelic.agent.android.tracing.ActivityTrace;
import com.newrelic.agent.android.tracing.TraceMachine;
import com.newrelic.agent.android.tracing.TracingInactiveException;
import com.newrelic.agent.android.util.NetworkFailure;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;
import org.apache.http.Header;
import org.apache.http.HttpResponse;

// Referenced classes of package com.newrelic.agent.android:
//            TaskQueue, Measurements, Agent, AgentImpl, 
//            NullAgentImpl

public class NewRelic
{

    private static final AgentLog a = AgentLogManager.getAgentLog();
    private static boolean b = false;
    private final String c;
    private String d;
    private boolean e;
    private boolean f;
    private boolean g;
    private int h;

    private NewRelic(String s)
    {
        d = "mobile-collector.newrelic.com";
        e = true;
        f = true;
        g = false;
        h = 3;
        c = s;
    }

    private static void a(int i, String s)
    {
        if (i < 0)
        {
            throw new IllegalArgumentException(s);
        } else
        {
            return;
        }
    }

    private static void a(Object obj, String s)
    {
        if (obj == null)
        {
            throw new IllegalArgumentException(s);
        } else
        {
            return;
        }
    }

    private static void a(String s, int i, long l, long l1, long l2, 
            long l3, String s1, Map map, String s2)
    {
        a(s, "noticeHttpTransaction: url must not be empty.");
        double d1;
        try
        {
            new URL(s);
        }
        catch (MalformedURLException malformedurlexception)
        {
            throw new IllegalArgumentException((new StringBuilder("noticeHttpTransaction: URL is malformed: ")).append(s).toString());
        }
        d1 = l1 - l;
        a((int)d1, "noticeHttpTransaction: the startTimeMs is later than the endTimeMs, resulting in a negative total time.");
        TaskQueue.queue(new HttpTransactionMeasurement(s, i, 0, l, d1 / 1000D, l2, l3, s2));
        if ((long)i >= 400L)
        {
            Measurements.addHttpError(s, i, s1, map);
        }
    }

    private static void a(String s, String s1)
    {
        a(s, s1);
        if (s.length() == 0)
        {
            throw new IllegalArgumentException(s1);
        } else
        {
            return;
        }
    }

    public static void endInteraction(String s)
    {
        TraceMachine.endTrace(s);
    }

    public static boolean isStarted()
    {
        return b;
    }

    public static void noticeHttpTransaction(String s, int i, long l, long l1, long l2, 
            long l3)
    {
        a(s, i, l, l1, l2, l3, null, null, null);
    }

    public static void noticeHttpTransaction(String s, int i, long l, long l1, long l2, 
            long l3, String s1)
    {
        a(s, i, l, l1, l2, l3, s1, null, null);
    }

    public static void noticeHttpTransaction(String s, int i, long l, long l1, long l2, 
            long l3, String s1, Map map)
    {
        a(s, i, l, l1, l2, l3, s1, map, null);
    }

    public static void noticeHttpTransaction(String s, int i, long l, long l1, long l2, 
            long l3, String s1, Map map, String s2)
    {
        a(s, i, l, l1, l2, l3, s1, map, s2);
    }

    public static void noticeHttpTransaction(String s, int i, long l, long l1, long l2, 
            long l3, String s1, Map map, URLConnection urlconnection)
    {
        if (urlconnection != null)
        {
            String s2 = urlconnection.getHeaderField("X-NewRelic-ID");
            if (s2 != null && s2.length() > 0)
            {
                a(s, i, l, l1, l2, l3, s1, map, s2);
                return;
            }
        }
        a(s, i, l, l1, l2, l3, s1, map, null);
    }

    public static void noticeHttpTransaction(String s, int i, long l, long l1, long l2, 
            long l3, String s1, Map map, HttpResponse httpresponse)
    {
        if (httpresponse != null)
        {
            Header header = httpresponse.getFirstHeader("X-NewRelic-ID");
            if (header != null && header.getValue() != null && header.getValue().length() > 0)
            {
                a(s, i, l, l1, l2, l3, s1, map, header.getValue());
                return;
            }
        }
        a(s, i, l, l1, l2, l3, s1, map, null);
    }

    public static void noticeNetworkFailure(String s, long l, long l1, NetworkFailure networkfailure)
    {
        TaskQueue.queue(new HttpTransactionMeasurement(s, 0, networkfailure.getErrorCode(), l, l1, 0L, 0L, null));
    }

    public static void noticeNetworkFailure(String s, long l, long l1, Exception exception)
    {
        a(s, "noticeHttpException: url must not be empty.");
        noticeNetworkFailure(s, l, l1, NetworkFailure.exceptionToNetworkFailure(exception));
    }

    public static void recordMetric(String s, String s1, double d1)
    {
        recordMetric(s, s1, 1, d1, d1, null, null);
    }

    public static void recordMetric(String s, String s1, int i, double d1, double d2)
    {
        recordMetric(s, s1, i, d1, d2, null, null);
    }

    public static void recordMetric(String s, String s1, int i, double d1, double d2, MetricUnit metricunit, 
            MetricUnit metricunit1)
    {
        a(s1, "recordMetric: category must not be null. If no MetricCategory is applicable, use MetricCategory.NONE.");
        a(s, "recordMetric: name must not be empty.");
        a(i, "recordMetric: count must not be negative.");
        Measurements.addCustomMetric(s, s1, i, d1, d2, metricunit, metricunit1);
    }

    public static void setInteractionName(String s)
    {
        TraceMachine.setRootDisplayName(s);
    }

    public static void shutdown()
    {
        if (!b)
        {
            break MISSING_BLOCK_LABEL_24;
        }
        Agent.getImpl().stop();
        Agent.setImpl(NullAgentImpl.instance);
        b = false;
        return;
        Exception exception;
        exception;
        Agent.setImpl(NullAgentImpl.instance);
        b = false;
        throw exception;
    }

    public static String startInteraction(Context context, String s)
    {
        a(context, "startInteraction: context must be an Activity instance.");
        a(s, "startInteraction: actionName must be an action/method name.");
        TraceMachine.startTracing((new StringBuilder()).append(context.getClass().getSimpleName()).append("#").append(s.replace("/", ".")).toString());
        String s1;
        try
        {
            s1 = TraceMachine.getActivityTrace().getId();
        }
        catch (TracingInactiveException tracinginactiveexception)
        {
            return null;
        }
        return s1;
    }

    public static String startInteraction(Context context, String s, boolean flag)
    {
        if (TraceMachine.isTracingActive() && !flag)
        {
            a.warning("startInteraction: An interaction is already being traced, and invalidateActiveTrace is false. This interaction will not be traced.");
            return null;
        } else
        {
            return startInteraction(context, s);
        }
    }

    public static String startInteraction(String s)
    {
        a(s, "startInteraction: actionName must be an action/method name.");
        TraceMachine.startTracing(s.replace("/", "."), true);
        String s1;
        try
        {
            s1 = TraceMachine.getActivityTrace().getId();
        }
        catch (TracingInactiveException tracinginactiveexception)
        {
            return null;
        }
        return s1;
    }

    public static NewRelic withApplicationToken(String s)
    {
        return new NewRelic(s);
    }

    public void start(Context context)
    {
        if (b)
        {
            a.debug("NewRelic is already running.");
            return;
        }
        Object obj;
        if (!f)
        {
            break MISSING_BLOCK_LABEL_75;
        }
        obj = new AndroidAgentLog();
_L1:
        Throwable throwable;
        AgentLogManager.setAgentLog(((AgentLog) (obj)));
        a.setLevel(h);
        a.error("Failed to detect New Relic instrumentation.  Something likely went wrong during your build process and you should contact support@newrelic.com.");
        return;
        try
        {
            obj = new NullAgentLog();
        }
        // Misplaced declaration of an exception variable
        catch (Throwable throwable)
        {
            a.error("Error occurred while starting the New Relic agent!", throwable);
            return;
        }
          goto _L1
    }

    public NewRelic usingCollectorAddress(String s)
    {
        d = s;
        return this;
    }

    public NewRelic usingSsl(boolean flag)
    {
        e = flag;
        return this;
    }

    public NewRelic withLocationServiceEnabled(boolean flag)
    {
        g = flag;
        return this;
    }

    public NewRelic withLogLevel(int i)
    {
        h = i;
        return this;
    }

    public NewRelic withLoggingEnabled(boolean flag)
    {
        f = flag;
        return this;
    }

}
