// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.newrelic.agent.android.harvest;

import com.newrelic.agent.android.harvest.type.HarvestableObject;
import com.newrelic.com.google.gson.JsonArray;
import com.newrelic.com.google.gson.JsonObject;
import com.newrelic.com.google.gson.JsonPrimitive;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

// Referenced classes of package com.newrelic.agent.android.harvest:
//            AgentHealthException

public class AgentHealthExceptions extends HarvestableObject
{

    private static final JsonArray a = new JsonArray();
    private final Map b = new ConcurrentHashMap();

    public AgentHealthExceptions()
    {
        a.add(new JsonPrimitive("ExceptionClass"));
        a.add(new JsonPrimitive("Message"));
        a.add(new JsonPrimitive("ThreadName"));
        a.add(new JsonPrimitive("CallStack"));
        a.add(new JsonPrimitive("Count"));
        a.add(new JsonPrimitive("Extras"));
    }

    public void add(AgentHealthException agenthealthexception)
    {
        String s = (new StringBuilder()).append(agenthealthexception.getExceptionClass()).append(agenthealthexception.getStackTrace()[0].toString()).toString();
        Map map = b;
        map;
        JVM INSTR monitorenter ;
        AgentHealthException agenthealthexception1 = (AgentHealthException)b.get(s);
        if (agenthealthexception1 != null)
        {
            break MISSING_BLOCK_LABEL_72;
        }
        b.put(s, agenthealthexception);
_L2:
        map;
        JVM INSTR monitorexit ;
        return;
        agenthealthexception1.increment();
        if (true) goto _L2; else goto _L1
_L1:
        Exception exception;
        exception;
        throw exception;
    }

    public JsonObject asJsonObject()
    {
        JsonObject jsonobject = new JsonObject();
        JsonArray jsonarray = new JsonArray();
        for (Iterator iterator = b.values().iterator(); iterator.hasNext(); jsonarray.add(((AgentHealthException)iterator.next()).asJsonArray())) { }
        jsonobject.add("Type", new JsonPrimitive("AgentErrors"));
        jsonobject.add("Keys", a);
        jsonobject.add("Data", jsonarray);
        return jsonobject;
    }

    public void clear()
    {
        synchronized (b)
        {
            b.clear();
        }
    }

    public Map getAgentHealthExceptions()
    {
        return b;
    }

    public boolean isEmpty()
    {
        return b.isEmpty();
    }

}
