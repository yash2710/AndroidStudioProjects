// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.newrelic.agent.android.harvest;

import com.newrelic.agent.android.harvest.type.HarvestableArray;
import com.newrelic.agent.android.tracing.ActivityTrace;
import com.newrelic.com.google.gson.JsonArray;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class ActivityTraces extends HarvestableArray
{

    private final Collection a = new ArrayList();

    public ActivityTraces()
    {
    }

    public void add(ActivityTrace activitytrace)
    {
        this;
        JVM INSTR monitorenter ;
        a.add(activitytrace);
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public JsonArray asJsonArray()
    {
        JsonArray jsonarray = new JsonArray();
        for (Iterator iterator = a.iterator(); iterator.hasNext(); jsonarray.add(((ActivityTrace)iterator.next()).asJson())) { }
        return jsonarray;
    }

    public void clear()
    {
        a.clear();
    }

    public int count()
    {
        return a.size();
    }

    public Collection getActivityTraces()
    {
        return a;
    }

    public void remove(ActivityTrace activitytrace)
    {
        this;
        JVM INSTR monitorenter ;
        a.remove(activitytrace);
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }
}
