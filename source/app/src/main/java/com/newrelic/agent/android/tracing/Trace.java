// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.newrelic.agent.android.tracing;

import com.newrelic.agent.android.instrumentation.MetricCategory;
import com.newrelic.agent.android.logging.AgentLog;
import com.newrelic.agent.android.logging.AgentLogManager;
import com.newrelic.agent.android.util.Util;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

// Referenced classes of package com.newrelic.agent.android.tracing:
//            TraceType, TraceMachine, TracingInactiveException

public class Trace
{

    private static final AgentLog a = AgentLogManager.getAgentLog();
    private volatile Map b;
    private List c;
    public long childExclusiveTime;
    private volatile Set d;
    public String displayName;
    private TraceType e;
    public long entryTimestamp;
    public long exclusiveTime;
    public long exitTimestamp;
    private boolean f;
    public String metricBackgroundName;
    public String metricName;
    public final UUID myUUID;
    public final UUID parentUUID;
    public String scope;
    public long threadId;
    public String threadName;
    public TraceMachine traceMachine;

    public Trace()
    {
        myUUID = new UUID(Util.getRandom().nextLong(), Util.getRandom().nextLong());
        entryTimestamp = 0L;
        exitTimestamp = 0L;
        exclusiveTime = 0L;
        childExclusiveTime = 0L;
        threadId = 0L;
        threadName = "main";
        e = TraceType.TRACE;
        f = false;
        parentUUID = null;
    }

    public Trace(String s, UUID uuid, TraceMachine tracemachine)
    {
        myUUID = new UUID(Util.getRandom().nextLong(), Util.getRandom().nextLong());
        entryTimestamp = 0L;
        exitTimestamp = 0L;
        exclusiveTime = 0L;
        childExclusiveTime = 0L;
        threadId = 0L;
        threadName = "main";
        e = TraceType.TRACE;
        f = false;
        displayName = s;
        parentUUID = uuid;
        traceMachine = tracemachine;
    }

    private static Object a(String s, String s1)
    {
        Class class1;
        try
        {
            class1 = Class.forName(s);
        }
        catch (ClassNotFoundException classnotfoundexception)
        {
            a.error((new StringBuilder("Unable to resolve parameter class in enterMethod: ")).append(classnotfoundexception.getMessage()).toString(), classnotfoundexception);
            return null;
        }
        if (com/newrelic/agent/android/instrumentation/MetricCategory == class1)
        {
            s1 = MetricCategory.valueOf(s1);
        } else
        if (java/lang/String != class1)
        {
            return null;
        }
        return s1;
    }

    public void addChild(Trace trace)
    {
        if (d != null) goto _L2; else goto _L1
_L1:
        this;
        JVM INSTR monitorenter ;
        if (d == null)
        {
            d = Collections.synchronizedSet(new HashSet());
        }
        this;
        JVM INSTR monitorexit ;
_L2:
        d.add(trace.myUUID);
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public void complete()
    {
        if (f)
        {
            a.warning((new StringBuilder("Attempted to double complete trace ")).append(myUUID.toString()).toString());
            return;
        }
        if (exitTimestamp == 0L)
        {
            exitTimestamp = System.currentTimeMillis();
        }
        exclusiveTime = getDuration() - childExclusiveTime;
        f = true;
        try
        {
            traceMachine.storeCompletedTrace(this);
            return;
        }
        catch (NullPointerException nullpointerexception)
        {
            throw new TracingInactiveException();
        }
    }

    public Map getAnnotationParams()
    {
        HashMap hashmap = new HashMap();
        if (c != null && c.size() > 0)
        {
            Iterator iterator = c.iterator();
            do
            {
                if (!iterator.hasNext())
                {
                    break;
                }
                String s = (String)iterator.next();
                Object obj = a((String)iterator.next(), (String)iterator.next());
                if (obj != null)
                {
                    hashmap.put(s, obj);
                }
            } while (true);
        }
        return hashmap;
    }

    public MetricCategory getCategory()
    {
        if (!getAnnotationParams().containsKey("category"))
        {
            return null;
        }
        Object obj = getAnnotationParams().get("category");
        if (!(obj instanceof MetricCategory))
        {
            a.error("Category annotation parameter is not of type MetricCategory");
            return null;
        } else
        {
            return (MetricCategory)obj;
        }
    }

    public Set getChildren()
    {
        if (d != null) goto _L2; else goto _L1
_L1:
        this;
        JVM INSTR monitorenter ;
        if (d == null)
        {
            d = Collections.synchronizedSet(new HashSet());
        }
        this;
        JVM INSTR monitorexit ;
_L2:
        return d;
        Exception exception;
        exception;
        throw exception;
    }

    public long getDuration()
    {
        return exitTimestamp - entryTimestamp;
    }

    public Map getParams()
    {
        if (b != null) goto _L2; else goto _L1
_L1:
        this;
        JVM INSTR monitorenter ;
        if (b == null)
        {
            b = new ConcurrentHashMap();
        }
        this;
        JVM INSTR monitorexit ;
_L2:
        return b;
        Exception exception;
        exception;
        throw exception;
    }

    public TraceType getType()
    {
        return e;
    }

    public boolean isComplete()
    {
        return f;
    }

    public void prepareForSerialization()
    {
        getParams().put("type", e.toString());
    }

    public void setAnnotationParams(List list)
    {
        c = list;
    }

    public void setType(TraceType tracetype)
    {
        e = tracetype;
    }

}
