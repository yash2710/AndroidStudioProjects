// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.newrelic.agent.android.tracing;

import com.newrelic.agent.android.Measurements;
import com.newrelic.agent.android.TaskQueue;
import com.newrelic.agent.android.activity.NamedActivity;
import com.newrelic.agent.android.harvest.ActivitySighting;
import com.newrelic.agent.android.harvest.ConnectInformation;
import com.newrelic.agent.android.harvest.type.HarvestableArray;
import com.newrelic.agent.android.logging.AgentLog;
import com.newrelic.agent.android.logging.AgentLogManager;
import com.newrelic.com.google.gson.Gson;
import com.newrelic.com.google.gson.JsonArray;
import com.newrelic.com.google.gson.JsonObject;
import com.newrelic.com.google.gson.JsonPrimitive;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

// Referenced classes of package com.newrelic.agent.android.tracing:
//            a, b, c, Trace, 
//            Sample

public class ActivityTrace extends HarvestableArray
{

    public static final int MAX_TRACES = 2000;
    public static final String TRACE_VERSION = "1.0";
    private static final HashMap j = new a();
    private static final HashMap k = new b();
    private static final HashMap l = new c();
    private final ConcurrentHashMap a;
    private int b;
    private final Set c;
    private NamedActivity d;
    private long e;
    private boolean f;
    private final HashMap g;
    private Map h;
    private final AgentLog i;
    public long lastUpdatedAt;
    public ActivitySighting previousActivity;
    public Trace rootTrace;
    public long startedAt;

    public ActivityTrace()
    {
        a = new ConcurrentHashMap();
        b = 0;
        c = Collections.synchronizedSet(new HashSet());
        e = 0L;
        f = false;
        g = new HashMap();
        i = AgentLogManager.getAgentLog();
    }

    public ActivityTrace(Trace trace)
    {
        a = new ConcurrentHashMap();
        b = 0;
        c = Collections.synchronizedSet(new HashSet());
        e = 0L;
        f = false;
        g = new HashMap();
        i = AgentLogManager.getAgentLog();
        rootTrace = trace;
        lastUpdatedAt = trace.entryTimestamp;
        startedAt = lastUpdatedAt;
        g.put("traceVersion", "1.0");
        g.put("type", "ACTIVITY");
        d = (NamedActivity)Measurements.startActivity(trace.displayName);
        d.setStartTime(trace.entryTimestamp);
    }

    private JsonArray a()
    {
        JsonArray jsonarray = new JsonArray();
        jsonarray.add((new Gson()).toJsonTree(k, GSON_STRING_MAP_TYPE));
        JsonObject jsonobject = new JsonObject();
        if (h != null)
        {
            java.util.Map.Entry entry;
            JsonArray jsonarray1;
label0:
            for (Iterator iterator = h.entrySet().iterator(); iterator.hasNext(); jsonobject.add(((Sample.SampleType)entry.getKey()).toString(), jsonarray1))
            {
                entry = (java.util.Map.Entry)iterator.next();
                jsonarray1 = new JsonArray();
                Iterator iterator1 = ((Collection)entry.getValue()).iterator();
                do
                {
                    if (!iterator1.hasNext())
                    {
                        continue label0;
                    }
                    Sample sample = (Sample)iterator1.next();
                    if (sample.getTimestamp() <= lastUpdatedAt)
                    {
                        jsonarray1.add(sample.asJsonArray());
                    }
                } while (true);
            }

        }
        jsonarray.add(jsonobject);
        return jsonarray;
    }

    private JsonArray a(Trace trace)
    {
        JsonArray jsonarray = new JsonArray();
        trace.prepareForSerialization();
        jsonarray.add((new Gson()).toJsonTree(trace.getParams(), GSON_STRING_MAP_TYPE));
        jsonarray.add(new JsonPrimitive(Long.valueOf(trace.entryTimestamp)));
        jsonarray.add(new JsonPrimitive(Long.valueOf(trace.exitTimestamp)));
        jsonarray.add(new JsonPrimitive(trace.displayName));
        JsonArray jsonarray1 = new JsonArray();
        jsonarray1.add(new JsonPrimitive(Long.valueOf(trace.threadId)));
        jsonarray1.add(new JsonPrimitive(trace.threadName));
        jsonarray.add(jsonarray1);
        if (trace.getChildren().isEmpty())
        {
            jsonarray.add(new JsonArray());
            return jsonarray;
        }
        JsonArray jsonarray2 = new JsonArray();
        Iterator iterator = trace.getChildren().iterator();
        do
        {
            if (!iterator.hasNext())
            {
                break;
            }
            UUID uuid = (UUID)iterator.next();
            Trace trace1 = (Trace)a.get(uuid);
            if (trace1 != null)
            {
                jsonarray2.add(a(trace1));
            }
        } while (true);
        jsonarray.add(jsonarray2);
        return jsonarray;
    }

    public void addCompletedTrace(Trace trace)
    {
        trace.traceMachine = null;
        c.remove(trace.myUUID);
        if (b > 2000)
        {
            i.debug((new StringBuilder("Maximum trace limit reached, discarding trace ")).append(trace.myUUID).toString());
            return;
        }
        a.put(trace.myUUID, trace);
        b = 1 + b;
        if (trace.exitTimestamp > rootTrace.exitTimestamp)
        {
            rootTrace.exitTimestamp = trace.exitTimestamp;
        }
        if (i.getLevel() == 5)
        {
            i.debug((new StringBuilder("Added trace ")).append(trace.myUUID.toString()).append(" missing children: ").append(c.size()).toString());
        }
        lastUpdatedAt = System.currentTimeMillis();
    }

    public void addTrace(Trace trace)
    {
        c.add(trace.myUUID);
        lastUpdatedAt = System.currentTimeMillis();
    }

    public JsonArray asJsonArray()
    {
        JsonArray jsonarray = new JsonArray();
        if (!f)
        {
            i.debug((new StringBuilder("Attempted to serialize trace ")).append(rootTrace.myUUID.toString()).append(" but it has yet to be finalized").toString());
            return null;
        }
        jsonarray.add((new Gson()).toJsonTree(g, GSON_STRING_MAP_TYPE));
        jsonarray.add(new JsonPrimitive(Long.valueOf(rootTrace.entryTimestamp)));
        jsonarray.add(new JsonPrimitive(Long.valueOf(rootTrace.exitTimestamp)));
        jsonarray.add(new JsonPrimitive(rootTrace.displayName));
        JsonArray jsonarray1 = new JsonArray();
        JsonArray jsonarray2 = new JsonArray();
        jsonarray2.add((new Gson()).toJsonTree(j, GSON_STRING_MAP_TYPE));
        jsonarray2.addAll((new ConnectInformation()).asJsonArray());
        HashMap hashmap = new HashMap();
        hashmap.put("size", "NORMAL");
        jsonarray2.add((new Gson()).toJsonTree(hashmap, GSON_STRING_MAP_TYPE));
        jsonarray1.add(jsonarray2);
        jsonarray1.add(a(rootTrace));
        jsonarray1.add(a());
        if (previousActivity != null)
        {
            JsonArray jsonarray3 = new JsonArray();
            jsonarray3.add((new Gson()).toJsonTree(l, GSON_STRING_MAP_TYPE));
            jsonarray3.addAll(previousActivity.asJsonArray());
            jsonarray1.add(jsonarray3);
        }
        jsonarray.add(jsonarray1);
        return jsonarray;
    }

    public void complete()
    {
        if (i.getLevel() == 5)
        {
            i.debug((new StringBuilder("Completing trace of ")).append(rootTrace.displayName).append(":").append(rootTrace.myUUID.toString()).append("(").append(a.size()).append(" traces)").toString());
        }
        if (rootTrace.exitTimestamp == 0L)
        {
            rootTrace.exitTimestamp = System.currentTimeMillis();
        }
        if (a.isEmpty())
        {
            rootTrace.traceMachine = null;
            f = true;
            Measurements.endActivityWithoutMeasurement(d);
            return;
        } else
        {
            d.setEndTime(rootTrace.exitTimestamp);
            Measurements.endActivity(d);
            rootTrace.traceMachine = null;
            f = true;
            TaskQueue.queue(this);
            return;
        }
    }

    public void discard()
    {
        if (i.getLevel() == 5)
        {
            i.debug((new StringBuilder("Discarding trace of ")).append(rootTrace.displayName).append(":").append(rootTrace.myUUID.toString()).append("(").append(a.size()).append(" traces)").toString());
        }
        rootTrace.traceMachine = null;
        f = true;
        Measurements.endActivityWithoutMeasurement(d);
    }

    public String getId()
    {
        if (rootTrace == null)
        {
            return null;
        } else
        {
            return rootTrace.myUUID.toString();
        }
    }

    public long getLastUpdatedAt()
    {
        return lastUpdatedAt;
    }

    public long getReportAttemptCount()
    {
        return e;
    }

    public Map getTraces()
    {
        return a;
    }

    public boolean hasMissingChildren()
    {
        return !c.isEmpty();
    }

    public void incrementReportAttemptCount()
    {
        e = 1L + e;
    }

    public boolean isComplete()
    {
        return f;
    }

    public void setLastUpdatedAt(long l1)
    {
        lastUpdatedAt = l1;
    }

    public void setVitals(Map map)
    {
        h = map;
    }

}
