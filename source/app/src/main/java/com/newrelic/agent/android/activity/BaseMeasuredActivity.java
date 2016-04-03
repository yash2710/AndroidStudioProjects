// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.newrelic.agent.android.activity;

import com.newrelic.agent.android.measurement.Measurement;
import com.newrelic.agent.android.measurement.MeasurementException;
import com.newrelic.agent.android.measurement.MeasurementPool;
import com.newrelic.agent.android.measurement.ThreadInfo;
import com.newrelic.agent.android.tracing.TraceMachine;

// Referenced classes of package com.newrelic.agent.android.activity:
//            MeasuredActivity

public class BaseMeasuredActivity
    implements MeasuredActivity
{

    private String a;
    private long b;
    private long c;
    private ThreadInfo d;
    private ThreadInfo e;
    private boolean f;
    private Measurement g;
    private Measurement h;
    private MeasurementPool i;
    private boolean j;

    public BaseMeasuredActivity()
    {
    }

    private void a()
    {
        if (j)
        {
            throw new MeasurementException("Cannot modify finished Activity");
        } else
        {
            return;
        }
    }

    public void finish()
    {
        j = true;
    }

    public String getBackgroundMetricName()
    {
        return TraceMachine.formatActivityBackgroundMetricName(a);
    }

    public long getEndTime()
    {
        return c;
    }

    public Measurement getEndingMeasurement()
    {
        return h;
    }

    public ThreadInfo getEndingThread()
    {
        return e;
    }

    public MeasurementPool getMeasurementPool()
    {
        return i;
    }

    public String getMetricName()
    {
        return TraceMachine.formatActivityMetricName(a);
    }

    public String getName()
    {
        return a;
    }

    public long getStartTime()
    {
        return b;
    }

    public Measurement getStartingMeasurement()
    {
        return g;
    }

    public ThreadInfo getStartingThread()
    {
        return d;
    }

    public boolean isAutoInstrumented()
    {
        return f;
    }

    public boolean isFinished()
    {
        return j;
    }

    public void setAutoInstrumented(boolean flag)
    {
        a();
        f = flag;
    }

    public void setEndTime(long l)
    {
        a();
        c = l;
    }

    public void setEndingMeasurement(Measurement measurement)
    {
        a();
        h = measurement;
    }

    public void setEndingThread(ThreadInfo threadinfo)
    {
        a();
        e = threadinfo;
    }

    public void setMeasurementPool(MeasurementPool measurementpool)
    {
        a();
        i = measurementpool;
    }

    public void setName(String s)
    {
        a();
        a = s;
    }

    public void setStartTime(long l)
    {
        a();
        b = l;
    }

    public void setStartingMeasurement(Measurement measurement)
    {
        a();
        g = measurement;
    }

    public void setStartingThread(ThreadInfo threadinfo)
    {
        a();
        d = threadinfo;
    }
}
