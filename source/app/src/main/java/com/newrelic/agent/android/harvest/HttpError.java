// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.newrelic.agent.android.harvest;

import com.newrelic.agent.android.Agent;
import com.newrelic.agent.android.harvest.type.HarvestableArray;
import com.newrelic.agent.android.harvest.type.HarvestableObject;
import com.newrelic.agent.android.logging.AgentLog;
import com.newrelic.agent.android.logging.AgentLogManager;
import com.newrelic.agent.android.measurement.http.HttpErrorMeasurement;
import com.newrelic.agent.android.util.Encoder;
import com.newrelic.com.google.gson.JsonArray;
import com.newrelic.com.google.gson.JsonObject;
import com.newrelic.com.google.gson.JsonPrimitive;
import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collections;
import java.util.Map;

// Referenced classes of package com.newrelic.agent.android.harvest:
//            Harvest, HarvestConfiguration

public class HttpError extends HarvestableArray
{

    private static final AgentLog a = AgentLogManager.getAgentLog();
    private String b;
    private int c;
    private long d;
    private String e;
    private String f;
    private Map g;
    private String h;
    private String i;
    private Long j;

    public HttpError()
    {
    }

    public HttpError(HttpErrorMeasurement httperrormeasurement)
    {
        this(httperrormeasurement.getUrl(), httperrormeasurement.getHttpStatusCode(), httperrormeasurement.getResponseBody(), httperrormeasurement.getStackTrace(), httperrormeasurement.getParams());
        setTimestamp(Long.valueOf(httperrormeasurement.getStartTime()));
    }

    public HttpError(String s, int k, String s1, String s2, Map map)
    {
        b = s;
        c = k;
        e = s1;
        f = s2;
        g = map;
        d = 1L;
        i = a();
    }

    private String a()
    {
        MessageDigest messagedigest;
        try
        {
            messagedigest = MessageDigest.getInstance("SHA-1");
        }
        catch (NoSuchAlgorithmException nosuchalgorithmexception)
        {
            a.error("Unable to initialize SHA-1 hash algorithm");
            return null;
        }
        messagedigest.update(b.getBytes());
        messagedigest.update(ByteBuffer.allocate(8).putInt(c).array());
        if (f != null && f.length() > 0)
        {
            messagedigest.update(f.getBytes());
        }
        return new String(messagedigest.digest());
    }

    public JsonArray asJsonArray()
    {
        int k = Harvest.getHarvestConfiguration().getResponse_body_limit();
        JsonArray jsonarray = new JsonArray();
        jsonarray.add(new JsonPrimitive(b));
        jsonarray.add(new JsonPrimitive(Integer.valueOf(c)));
        jsonarray.add(new JsonPrimitive(Long.valueOf(d)));
        String s = optional(e);
        if (s.length() > k)
        {
            a.warning((new StringBuilder("HTTP Error response body is too large. Truncating to ")).append(k).append(" bytes.").toString());
            s = s.substring(0, k);
        }
        jsonarray.add(new JsonPrimitive(Agent.getEncoder().encode(s.getBytes())));
        jsonarray.add(new JsonPrimitive(optional(f)));
        JsonObject jsonobject = new JsonObject();
        if (g == null)
        {
            g = Collections.emptyMap();
        }
        jsonobject.add("custom_params", HarvestableObject.fromMap(g).asJson());
        jsonarray.add(jsonobject);
        jsonarray.add(new JsonPrimitive(optional(h)));
        return jsonarray;
    }

    public void digest()
    {
        i = a();
    }

    public String getHash()
    {
        return i;
    }

    public Long getTimestamp()
    {
        return j;
    }

    public void incrementCount()
    {
        d = 1L + d;
    }

    public void setAppData(String s)
    {
        h = s;
    }

    public void setCount(long l)
    {
        d = l;
    }

    public void setHttpStatusCode(int k)
    {
        c = k;
    }

    public void setParams(Map map)
    {
        g = map;
    }

    public void setResponseBody(String s)
    {
        e = s;
    }

    public void setStackTrace(String s)
    {
        f = s;
    }

    public void setTimestamp(Long long1)
    {
        j = long1;
    }

    public void setUrl(String s)
    {
        b = s;
    }

}
