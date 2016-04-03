// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.newrelic.agent.android.harvest;

import com.newrelic.agent.android.harvest.type.HarvestableArray;
import com.newrelic.com.google.gson.JsonArray;
import com.newrelic.com.google.gson.JsonPrimitive;

public class HttpTransaction extends HarvestableArray
{

    private String a;
    private String b;
    private double c;
    private int d;
    private int e;
    private long f;
    private long g;
    private String h;
    private Long i;

    public HttpTransaction()
    {
    }

    public JsonArray asJsonArray()
    {
        JsonArray jsonarray = new JsonArray();
        jsonarray.add(new JsonPrimitive(a));
        jsonarray.add(new JsonPrimitive(b));
        jsonarray.add(new JsonPrimitive(Double.valueOf(c)));
        jsonarray.add(new JsonPrimitive(Integer.valueOf(d)));
        jsonarray.add(new JsonPrimitive(Integer.valueOf(e)));
        jsonarray.add(new JsonPrimitive(Long.valueOf(f)));
        jsonarray.add(new JsonPrimitive(Long.valueOf(g)));
        Object obj;
        if (h == null)
        {
            obj = null;
        } else
        {
            obj = new JsonPrimitive(h);
        }
        jsonarray.add(((com.newrelic.com.google.gson.JsonElement) (obj)));
        return jsonarray;
    }

    public String getAppData()
    {
        return h;
    }

    public long getBytesReceived()
    {
        return g;
    }

    public long getBytesSent()
    {
        return f;
    }

    public String getCarrier()
    {
        return b;
    }

    public int getErrorCode()
    {
        return e;
    }

    public int getStatusCode()
    {
        return d;
    }

    public Long getTimestamp()
    {
        return i;
    }

    public double getTotalTime()
    {
        return c;
    }

    public String getUrl()
    {
        return a;
    }

    public void setAppData(String s)
    {
        h = s;
    }

    public void setBytesReceived(long l)
    {
        g = l;
    }

    public void setBytesSent(long l)
    {
        f = l;
    }

    public void setCarrier(String s)
    {
        b = s;
    }

    public void setErrorCode(int j)
    {
        e = j;
    }

    public void setStatusCode(int j)
    {
        d = j;
    }

    public void setTimestamp(Long long1)
    {
        i = long1;
    }

    public void setTotalTime(double d1)
    {
        c = d1;
    }

    public void setUrl(String s)
    {
        a = s;
    }

    public String toString()
    {
        return (new StringBuilder("HttpTransaction{url='")).append(a).append('\'').append(", carrier='").append(b).append('\'').append(", totalTime=").append(c).append(", statusCode=").append(d).append(", errorCode=").append(e).append(", bytesSent=").append(f).append(", bytesReceived=").append(g).append(", appData='").append(h).append('\'').append(", timestamp=").append(i).append('}').toString();
    }
}
