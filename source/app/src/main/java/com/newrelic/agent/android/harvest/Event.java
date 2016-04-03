// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.newrelic.agent.android.harvest;

import com.newrelic.agent.android.harvest.type.HarvestableArray;
import com.newrelic.com.google.gson.Gson;
import com.newrelic.com.google.gson.JsonArray;
import com.newrelic.com.google.gson.JsonPrimitive;
import java.util.HashMap;
import java.util.Map;

public class Event extends HarvestableArray
{

    private long a;
    private long b;
    private Map c;

    public Event()
    {
        c = new HashMap();
    }

    public JsonArray asJsonArray()
    {
        JsonArray jsonarray = new JsonArray();
        jsonarray.add(new JsonPrimitive(Long.valueOf(a)));
        jsonarray.add(new JsonPrimitive(Long.valueOf(b)));
        jsonarray.add((new Gson()).toJsonTree(c, GSON_STRING_MAP_TYPE));
        return jsonarray;
    }

    public long getEventName()
    {
        return b;
    }

    public Map getParams()
    {
        return c;
    }

    public long getTimestamp()
    {
        return a;
    }

    public void setEventName(long l)
    {
        b = l;
    }

    public void setParams(Map map)
    {
        c = map;
    }

    public void setTimestamp(long l)
    {
        a = l;
    }
}
