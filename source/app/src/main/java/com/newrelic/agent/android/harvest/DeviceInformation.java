// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.newrelic.agent.android.harvest;

import com.newrelic.agent.android.harvest.type.HarvestableArray;
import com.newrelic.com.google.gson.Gson;
import com.newrelic.com.google.gson.JsonArray;
import com.newrelic.com.google.gson.JsonPrimitive;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class DeviceInformation extends HarvestableArray
{

    private String a;
    private String b;
    private String c;
    private String d;
    private String e;
    private String f;
    private String g;
    private String h;
    private String i;
    private Map j;

    public DeviceInformation()
    {
        j = new HashMap();
    }

    public void addMisc(String s, String s1)
    {
        j.put(s, s1);
    }

    public JsonArray asJsonArray()
    {
        JsonArray jsonarray = new JsonArray();
        notEmpty(a);
        jsonarray.add(new JsonPrimitive(a));
        notEmpty(b);
        jsonarray.add(new JsonPrimitive(b));
        notEmpty(i);
        notEmpty(c);
        jsonarray.add(new JsonPrimitive((new StringBuilder()).append(i).append(" ").append(c).toString()));
        notEmpty(d);
        jsonarray.add(new JsonPrimitive(d));
        notEmpty(e);
        jsonarray.add(new JsonPrimitive(e));
        notEmpty(f);
        jsonarray.add(new JsonPrimitive(f));
        jsonarray.add(new JsonPrimitive(optional(g)));
        jsonarray.add(new JsonPrimitive(optional(h)));
        jsonarray.add(new JsonPrimitive(i));
        if (j == null || j.isEmpty())
        {
            j = Collections.emptyMap();
        }
        jsonarray.add((new Gson()).toJsonTree(j, GSON_STRING_MAP_TYPE));
        return jsonarray;
    }

    public String getAgentName()
    {
        return d;
    }

    public String getAgentVersion()
    {
        return e;
    }

    public String getCountryCode()
    {
        return g;
    }

    public String getDeviceId()
    {
        return f;
    }

    public String getManufacturer()
    {
        return i;
    }

    public String getModel()
    {
        return c;
    }

    public String getOsName()
    {
        return a;
    }

    public String getOsVersion()
    {
        return b;
    }

    public String getRegionCode()
    {
        return h;
    }

    public void setAgentName(String s)
    {
        d = s;
    }

    public void setAgentVersion(String s)
    {
        e = s;
    }

    public void setCountryCode(String s)
    {
        g = s;
    }

    public void setDeviceId(String s)
    {
        f = s;
    }

    public void setManufacturer(String s)
    {
        i = s;
    }

    public void setMisc(Map map)
    {
        j = new HashMap(map);
    }

    public void setModel(String s)
    {
        c = s;
    }

    public void setOsName(String s)
    {
        a = s;
    }

    public void setOsVersion(String s)
    {
        b = s;
    }

    public void setRegionCode(String s)
    {
        h = s;
    }

    public String toJsonString()
    {
        return (new StringBuilder("DeviceInformation{manufacturer='")).append(i).append('\'').append(", osName='").append(a).append('\'').append(", osVersion='").append(b).append('\'').append(", model='").append(c).append('\'').append(", agentName='").append(d).append('\'').append(", agentVersion='").append(e).append('\'').append(", deviceId='").append(f).append('\'').append(", countryCode='").append(g).append('\'').append(", regionCode='").append(h).append('\'').append('}').toString();
    }
}
