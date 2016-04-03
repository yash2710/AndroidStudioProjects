// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.newrelic.agent.android.measurement;

import com.newrelic.agent.android.logging.AgentLog;
import com.newrelic.agent.android.logging.AgentLogManager;

// Referenced classes of package com.newrelic.agent.android.measurement:
//            Measurement, MeasurementException, MeasurementType, ThreadInfo

public class BaseMeasurement
    implements Measurement
{

    private static final AgentLog a = AgentLogManager.getAgentLog();
    private MeasurementType b;
    private String c;
    private String d;
    private long e;
    private long f;
    private long g;
    private ThreadInfo h;
    private boolean i;

    public BaseMeasurement(Measurement measurement)
    {
        a(measurement.getType());
        setName(measurement.getName());
        setScope(measurement.getScope());
        setStartTime(measurement.getStartTime());
        setEndTime(measurement.getEndTime());
        setExclusiveTime(measurement.getExclusiveTime());
        setThreadInfo(measurement.getThreadInfo());
        i = measurement.isFinished();
    }

    public BaseMeasurement(MeasurementType measurementtype)
    {
        a(measurementtype);
    }

    private void a()
    {
        if (i)
        {
            throw new MeasurementException("Attempted to modify finished Measurement");
        } else
        {
            return;
        }
    }

    private void a(MeasurementType measurementtype)
    {
        a();
        b = measurementtype;
    }

    public double asDouble()
    {
        throw new UnsupportedOperationException();
    }

    public void finish()
    {
        if (i)
        {
            throw new MeasurementException("Finish called on already finished Measurement");
        } else
        {
            i = true;
            return;
        }
    }

    public long getEndTime()
    {
        return f;
    }

    public double getEndTimeInSeconds()
    {
        return (double)f / 1000D;
    }

    public long getExclusiveTime()
    {
        return g;
    }

    public double getExclusiveTimeInSeconds()
    {
        return (double)g / 1000D;
    }

    public String getName()
    {
        return c;
    }

    public String getScope()
    {
        return d;
    }

    public long getStartTime()
    {
        return e;
    }

    public double getStartTimeInSeconds()
    {
        return (double)e / 1000D;
    }

    public ThreadInfo getThreadInfo()
    {
        return h;
    }

    public MeasurementType getType()
    {
        return b;
    }

    public boolean isFinished()
    {
        return i;
    }

    public boolean isInstantaneous()
    {
        return f == 0L;
    }

    public void setEndTime(long l)
    {
        a();
        if (l < e)
        {
            a.error((new StringBuilder("Measurement end time must not precede start time - startTime: ")).append(e).append(" endTime: ").append(l).toString());
            return;
        } else
        {
            f = l;
            return;
        }
    }

    public void setExclusiveTime(long l)
    {
        a();
        g = l;
    }

    public void setName(String s)
    {
        a();
        c = s;
    }

    public void setScope(String s)
    {
        a();
        d = s;
    }

    public void setStartTime(long l)
    {
        a();
        e = l;
    }

    public void setThreadInfo(ThreadInfo threadinfo)
    {
        h = threadinfo;
    }

    public String toString()
    {
        return (new StringBuilder("BaseMeasurement{type=")).append(b).append(", name='").append(c).append('\'').append(", scope='").append(d).append('\'').append(", startTime=").append(e).append(", endTime=").append(f).append(", exclusiveTime=").append(g).append(", threadInfo=").append(h).append(", finished=").append(i).append('}').toString();
    }

}
