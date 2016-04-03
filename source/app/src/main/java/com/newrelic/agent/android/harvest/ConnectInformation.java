// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.newrelic.agent.android.harvest;

import com.newrelic.agent.android.Agent;
import com.newrelic.agent.android.harvest.type.HarvestableArray;
import com.newrelic.com.google.gson.JsonArray;

// Referenced classes of package com.newrelic.agent.android.harvest:
//            ApplicationInformation, DeviceInformation

public class ConnectInformation extends HarvestableArray
{

    private ApplicationInformation a;
    private DeviceInformation b;

    public ConnectInformation()
    {
        setApplicationInformation(Agent.getApplicationInformation());
        setDeviceInformation(Agent.getDeviceInformation());
    }

    public JsonArray asJsonArray()
    {
        JsonArray jsonarray = new JsonArray();
        notNull(a);
        jsonarray.add(a.asJsonArray());
        notNull(b);
        jsonarray.add(b.asJsonArray());
        return jsonarray;
    }

    public void setApplicationInformation(ApplicationInformation applicationinformation)
    {
        a = applicationinformation;
    }

    public void setDeviceInformation(DeviceInformation deviceinformation)
    {
        b = deviceinformation;
    }
}
