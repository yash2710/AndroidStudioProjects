// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.newrelic.agent.android.harvest;

import com.newrelic.agent.android.harvest.type.HarvestableArray;
import com.newrelic.com.google.gson.JsonArray;
import com.newrelic.com.google.gson.JsonPrimitive;

public class DataToken extends HarvestableArray
{

    private long a;
    private long b;

    public DataToken()
    {
    }

    public DataToken(long l, long l1)
    {
        a = l;
        b = l1;
    }

    public JsonArray asJsonArray()
    {
        JsonArray jsonarray = new JsonArray();
        jsonarray.add(new JsonPrimitive(Long.valueOf(a)));
        jsonarray.add(new JsonPrimitive(Long.valueOf(b)));
        return jsonarray;
    }

    public void clear()
    {
        a = 0L;
        b = 0L;
    }

    public long getAccountId()
    {
        return a;
    }

    public long getAgentId()
    {
        return b;
    }

    public boolean isValid()
    {
        return a > 0L && b > 0L;
    }

    public void setAccountId(long l)
    {
        a = l;
    }

    public void setAgentId(long l)
    {
        b = l;
    }

    public String toString()
    {
        return (new StringBuilder("DataToken{accountId=")).append(a).append(", agentId=").append(b).append('}').toString();
    }
}
