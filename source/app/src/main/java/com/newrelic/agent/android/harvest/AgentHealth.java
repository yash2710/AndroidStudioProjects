// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.newrelic.agent.android.harvest;

import com.newrelic.agent.android.TaskQueue;
import com.newrelic.agent.android.harvest.type.HarvestableArray;
import com.newrelic.agent.android.stats.StatsEngine;
import com.newrelic.com.google.gson.JsonArray;
import java.text.MessageFormat;

// Referenced classes of package com.newrelic.agent.android.harvest:
//            AgentHealthExceptions, AgentHealthException

public class AgentHealth extends HarvestableArray
{

    private final AgentHealthExceptions a = new AgentHealthExceptions();

    public AgentHealth()
    {
    }

    public static void noticeException(AgentHealthException agenthealthexception)
    {
        StatsEngine statsengine = StatsEngine.get();
        Object aobj[] = new Object[3];
        aobj[0] = agenthealthexception.getSourceClass();
        aobj[1] = agenthealthexception.getSourceMethod();
        aobj[2] = agenthealthexception.getExceptionClass();
        statsengine.inc(MessageFormat.format("Supportability/AgentHealth/Exception/{0}/{1}/{2}", aobj));
        TaskQueue.queue(agenthealthexception);
    }

    public static void noticeException(Exception exception)
    {
        noticeException(new AgentHealthException(exception));
    }

    public void addException(AgentHealthException agenthealthexception)
    {
        a.add(agenthealthexception);
    }

    public JsonArray asJsonArray()
    {
        JsonArray jsonarray = new JsonArray();
        if (!a.isEmpty())
        {
            jsonarray.add(a.asJsonObject());
        }
        return jsonarray;
    }

    public void clear()
    {
        a.clear();
    }
}
