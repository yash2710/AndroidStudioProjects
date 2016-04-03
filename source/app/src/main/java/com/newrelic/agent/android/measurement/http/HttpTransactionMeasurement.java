// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.newrelic.agent.android.measurement.http;

import com.newrelic.agent.android.measurement.BaseMeasurement;
import com.newrelic.agent.android.measurement.MeasurementType;
import com.newrelic.agent.android.tracing.TraceMachine;
import com.newrelic.agent.android.util.Util;

public class HttpTransactionMeasurement extends BaseMeasurement
{

    private String a;
    private double b;
    private int c;
    private int d;
    private long e;
    private long f;
    private String g;

    public HttpTransactionMeasurement(String s, int i, int j, long l, double d1, 
            long l1, long l2, String s1)
    {
        this(s, i, l, d1, l1, l2, s1);
        d = j;
    }

    public HttpTransactionMeasurement(String s, int i, long l, double d1, long l1, long l2, String s1)
    {
        super(MeasurementType.Network);
        String s2 = Util.sanitizeUrl(s);
        setName(s2);
        setScope(TraceMachine.getCurrentScope());
        setStartTime(l);
        setEndTime(l + (long)(int)d1);
        setExclusiveTime((int)(1000D * d1));
        c = i;
        a = s2;
        e = l1;
        f = l2;
        b = d1;
        g = s1;
    }

    public double asDouble()
    {
        return b;
    }

    public String getAppData()
    {
        return g;
    }

    public long getBytesReceived()
    {
        return f;
    }

    public long getBytesSent()
    {
        return e;
    }

    public int getErrorCode()
    {
        return d;
    }

    public int getStatusCode()
    {
        return c;
    }

    public double getTotalTime()
    {
        return b;
    }

    public String getUrl()
    {
        return a;
    }

    public void setUrl(String s)
    {
        a = s;
    }

    public String toString()
    {
        return (new StringBuilder("HttpTransactionMeasurement{url='")).append(a).append('\'').append(", totalTime=").append(b).append(", statusCode=").append(c).append(", errorCode=").append(d).append(", bytesSent=").append(e).append(", bytesReceived=").append(f).append(", appData='").append(g).append('\'').append('}').toString();
    }
}
