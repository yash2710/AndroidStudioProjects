// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.log.perf;

import java.util.HashMap;

public class ApiPerfMetrics
{

    private String a;
    private String b;
    private String c;
    private String d;
    private String e;
    private String f;
    private int g;
    private int h;
    private int i;
    private String j;
    private int k;
    private long l;
    private int m;
    private String n;

    public ApiPerfMetrics()
    {
    }

    public String getApi()
    {
        return a;
    }

    public int getAppVersion()
    {
        return i;
    }

    public String getClient()
    {
        return n;
    }

    public String getCustomerId()
    {
        return f;
    }

    public String getDeviceId()
    {
        return d;
    }

    public String getDeviceName()
    {
        return e;
    }

    public int getHeaderTime()
    {
        return k;
    }

    public long getLastByteTime()
    {
        return l;
    }

    public String getNetworkType()
    {
        return c;
    }

    public String getOsVersion()
    {
        return j;
    }

    public int getPayloadSize()
    {
        return m;
    }

    public int getRetryCount()
    {
        return g;
    }

    public int getStatusCode()
    {
        return h;
    }

    public String getUrl()
    {
        return b;
    }

    public void setApi(String s)
    {
        a = s;
    }

    public void setAppVersion(int i1)
    {
        i = i1;
    }

    public void setClient(String s)
    {
        n = s;
    }

    public void setCustomerId(String s)
    {
        f = s;
    }

    public void setDeviceId(String s)
    {
        d = s;
    }

    public void setDeviceName(String s)
    {
        e = s;
    }

    public void setHeaderTime(int i1)
    {
        k = i1;
    }

    public void setLastByteTime(long l1)
    {
        l = l1;
    }

    public void setNetworkType(String s)
    {
        c = s;
    }

    public void setOsVersion(String s)
    {
        j = s;
    }

    public void setPayloadSize(int i1)
    {
        m = i1;
    }

    public void setRetryCount(int i1)
    {
        g = i1;
    }

    public void setStatusCode(int i1)
    {
        h = i1;
    }

    public void setUrl(String s)
    {
        b = s;
    }

    public HashMap toMap()
    {
        HashMap hashmap = new HashMap();
        hashmap.put("api", a);
        hashmap.put("url", b);
        hashmap.put("networkType", c);
        hashmap.put("deviceId", d);
        hashmap.put("deviceName", e);
        hashmap.put("customerId", f);
        hashmap.put("retryCount", (new StringBuilder()).append(g).toString());
        hashmap.put("statusCode", (new StringBuilder()).append(h).toString());
        hashmap.put("appVersion", (new StringBuilder()).append(i).toString());
        hashmap.put("osVersion", j);
        hashmap.put("headerTime", (new StringBuilder()).append(k).toString());
        hashmap.put("lastByteTime", (new StringBuilder()).append(l).toString());
        hashmap.put("payloadSize", (new StringBuilder()).append(m).toString());
        hashmap.put("client", n);
        return hashmap;
    }
}
