// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.newrelic.agent.android.harvest;

import com.newrelic.agent.android.Agent;
import com.newrelic.agent.android.harvest.type.HarvestableArray;
import com.newrelic.agent.android.stats.StatsEngine;
import com.newrelic.com.google.gson.JsonArray;
import com.newrelic.com.google.gson.JsonElement;
import com.newrelic.com.google.gson.JsonPrimitive;

// Referenced classes of package com.newrelic.agent.android.harvest:
//            DataToken, HttpTransactions, HttpErrors, ActivityTraces, 
//            MachineMeasurements, AgentHealth, DeviceInformation, Harvest, 
//            HarvestConfiguration

public class HarvestData extends HarvestableArray
{

    private DataToken a;
    private DeviceInformation b;
    private double c;
    private HttpTransactions d;
    private MachineMeasurements e;
    private HttpErrors f;
    private ActivityTraces g;
    private AgentHealth h;

    public HarvestData()
    {
        a = new DataToken();
        d = new HttpTransactions();
        f = new HttpErrors();
        g = new ActivityTraces();
        e = new MachineMeasurements();
        b = Agent.getDeviceInformation();
        h = new AgentHealth();
    }

    public JsonArray asJsonArray()
    {
        JsonArray jsonarray = new JsonArray();
        jsonarray.add(a.asJson());
        jsonarray.add(b.asJson());
        jsonarray.add(new JsonPrimitive(Double.valueOf(c)));
        jsonarray.add(d.asJson());
        jsonarray.add(e.asJson());
        jsonarray.add(f.asJson());
        JsonElement jsonelement = g.asJson();
        String s = jsonelement.toString();
        if (s.length() < Harvest.getHarvestConfiguration().getActivity_trace_max_size())
        {
            jsonarray.add(jsonelement);
        } else
        {
            StatsEngine.get().sample("Supportability/AgentHealth/BigActivityTracesDropped", s.length());
        }
        jsonarray.add(h.asJson());
        return jsonarray;
    }

    public ActivityTraces getActivityTraces()
    {
        return g;
    }

    public AgentHealth getAgentHealth()
    {
        return h;
    }

    public DataToken getDataToken()
    {
        return a;
    }

    public DeviceInformation getDeviceInformation()
    {
        return b;
    }

    public HttpErrors getHttpErrors()
    {
        return f;
    }

    public HttpTransactions getHttpTransactions()
    {
        return d;
    }

    public MachineMeasurements getMetrics()
    {
        return e;
    }

    public void reset()
    {
        f.clear();
        d.clear();
        g.clear();
        e.clear();
        h.clear();
    }

    public void setActivityTraces(ActivityTraces activitytraces)
    {
        g = activitytraces;
    }

    public void setDataToken(DataToken datatoken)
    {
        if (datatoken == null)
        {
            return;
        } else
        {
            a = datatoken;
            return;
        }
    }

    public void setDeviceInformation(DeviceInformation deviceinformation)
    {
        b = deviceinformation;
    }

    public void setHarvestTimeDelta(double d1)
    {
        c = d1;
    }

    public void setHttpErrors(HttpErrors httperrors)
    {
        f = httperrors;
    }

    public void setHttpTransactions(HttpTransactions httptransactions)
    {
        d = httptransactions;
    }

    public void setMachineMeasurements(MachineMeasurements machinemeasurements)
    {
        e = machinemeasurements;
    }

    public String toString()
    {
        return (new StringBuilder("HarvestData{dataToken=")).append(a).append(", deviceInformation=").append(b).append(", harvestTimeDelta=").append(c).append(", httpTransactions=").append(d).append(", machineMeasurements=").append(e).append(", httpErrors=").append(f).append(", activityTraces=").append(g).append('}').toString();
    }
}
