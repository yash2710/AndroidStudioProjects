// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.newrelic.agent.android.harvest;

import com.newrelic.agent.android.harvest.type.HarvestableArray;
import com.newrelic.com.google.gson.JsonArray;
import com.newrelic.com.google.gson.JsonObject;
import com.newrelic.com.google.gson.JsonPrimitive;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;

public class AgentHealthException extends HarvestableArray
{

    private String a;
    private String b;
    private String c;
    private StackTraceElement d[];
    private final AtomicLong e;
    private Map f;

    public AgentHealthException(Exception exception)
    {
        this(exception, Thread.currentThread().getName());
    }

    public AgentHealthException(Exception exception, String s)
    {
        this(exception.getClass().getName(), exception.getMessage(), s, exception.getStackTrace());
    }

    public AgentHealthException(String s, String s1, String s2, StackTraceElement astacktraceelement[])
    {
        this(s, s1, s2, astacktraceelement, null);
    }

    public AgentHealthException(String s, String s1, String s2, StackTraceElement astacktraceelement[], Map map)
    {
        e = new AtomicLong(1L);
        a = s;
        b = s1;
        c = s2;
        d = astacktraceelement;
        f = map;
    }

    public JsonArray asJsonArray()
    {
        JsonArray jsonarray = new JsonArray();
        jsonarray.add(new JsonPrimitive(a));
        String s;
        JsonArray jsonarray1;
        StackTraceElement astacktraceelement[];
        int i;
        if (b == null)
        {
            s = "";
        } else
        {
            s = b;
        }
        jsonarray.add(new JsonPrimitive(s));
        jsonarray.add(new JsonPrimitive(c));
        jsonarray1 = new JsonArray();
        astacktraceelement = d;
        i = astacktraceelement.length;
        for (int j = 0; j < i; j++)
        {
            jsonarray1.add(new JsonPrimitive(astacktraceelement[j].toString()));
        }

        jsonarray.add(jsonarray1);
        jsonarray.add(new JsonPrimitive(Long.valueOf(e.get())));
        JsonObject jsonobject = new JsonObject();
        if (f != null)
        {
            java.util.Map.Entry entry;
            for (Iterator iterator = f.entrySet().iterator(); iterator.hasNext(); jsonobject.add((String)entry.getKey(), new JsonPrimitive((String)entry.getValue())))
            {
                entry = (java.util.Map.Entry)iterator.next();
            }

        }
        jsonarray.add(jsonobject);
        return jsonarray;
    }

    public long getCount()
    {
        return e.get();
    }

    public String getExceptionClass()
    {
        return a;
    }

    public Map getExtras()
    {
        return f;
    }

    public String getMessage()
    {
        return b;
    }

    public String getSourceClass()
    {
        return d[0].getClassName();
    }

    public String getSourceMethod()
    {
        return d[0].getMethodName();
    }

    public StackTraceElement[] getStackTrace()
    {
        return d;
    }

    public String getThreadName()
    {
        return c;
    }

    public void increment()
    {
        e.getAndIncrement();
    }

    public void increment(long l)
    {
        e.getAndAdd(l);
    }
}
