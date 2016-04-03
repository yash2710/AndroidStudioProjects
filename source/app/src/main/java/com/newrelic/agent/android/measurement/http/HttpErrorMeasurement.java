// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.newrelic.agent.android.measurement.http;

import com.newrelic.agent.android.measurement.BaseMeasurement;
import com.newrelic.agent.android.measurement.MeasurementType;
import java.util.Map;

public class HttpErrorMeasurement extends BaseMeasurement
{

    private String a;
    private int b;
    private String c;
    private String d;
    private Map e;

    public HttpErrorMeasurement(String s, int i)
    {
        super(MeasurementType.HttpError);
        setUrl(s);
        setName(s);
        setHttpStatusCode(i);
        setStartTime(System.currentTimeMillis());
    }

    public int getHttpStatusCode()
    {
        return b;
    }

    public Map getParams()
    {
        return e;
    }

    public String getResponseBody()
    {
        return c;
    }

    public String getStackTrace()
    {
        return d;
    }

    public String getUrl()
    {
        return a;
    }

    public void setHttpStatusCode(int i)
    {
        b = i;
    }

    public void setParams(Map map)
    {
        e = map;
    }

    public void setResponseBody(String s)
    {
        c = s;
    }

    public void setStackTrace(String s)
    {
        d = s;
    }

    public void setUrl(String s)
    {
        a = s;
    }
}
