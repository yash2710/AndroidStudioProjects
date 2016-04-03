// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.newrelic.agent.android.harvest;

import com.newrelic.agent.android.harvest.type.HarvestableArray;
import com.newrelic.com.google.gson.JsonArray;
import com.newrelic.com.google.gson.JsonElement;
import com.newrelic.com.google.gson.JsonPrimitive;

public class ActivitySighting extends HarvestableArray
{

    private final String a;
    private final long b;
    private long c;

    public ActivitySighting(long l, String s)
    {
        c = 0L;
        b = l;
        a = s;
    }

    public static ActivitySighting newFromJson(JsonArray jsonarray)
    {
        return new ActivitySighting(jsonarray.get(0).getAsLong(), jsonarray.get(1).getAsString());
    }

    public JsonArray asJsonArray()
    {
        JsonArray jsonarray = new JsonArray();
        jsonarray.add(new JsonPrimitive(a));
        jsonarray.add(new JsonPrimitive(Long.valueOf(b)));
        jsonarray.add(new JsonPrimitive(Long.valueOf(c)));
        return jsonarray;
    }

    public void end(long l)
    {
        c = l - b;
    }

    public long getDuration()
    {
        return c;
    }

    public String getName()
    {
        return a;
    }

    public long getTimestampMs()
    {
        return b;
    }
}
