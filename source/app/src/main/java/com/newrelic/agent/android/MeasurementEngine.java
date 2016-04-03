// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.newrelic.agent.android;

import com.newrelic.agent.android.activity.MeasuredActivity;
import com.newrelic.agent.android.activity.NamedActivity;
import com.newrelic.agent.android.measurement.MeasurementException;
import com.newrelic.agent.android.measurement.MeasurementPool;
import com.newrelic.agent.android.measurement.consumer.MeasurementConsumer;
import com.newrelic.agent.android.measurement.producer.MeasurementProducer;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MeasurementEngine
{

    private final Map a = new ConcurrentHashMap();
    private final MeasurementPool b = new MeasurementPool();

    public MeasurementEngine()
    {
    }

    public void addMeasurementConsumer(MeasurementConsumer measurementconsumer)
    {
        b.addMeasurementConsumer(measurementconsumer);
    }

    public void addMeasurementProducer(MeasurementProducer measurementproducer)
    {
        b.addMeasurementProducer(measurementproducer);
    }

    public void broadcastMeasurements()
    {
        b.broadcastMeasurements();
    }

    public void clear()
    {
        a.clear();
    }

    public MeasuredActivity endActivity(String s)
    {
        MeasuredActivity measuredactivity = (MeasuredActivity)a.get(s);
        if (measuredactivity == null)
        {
            throw new MeasurementException((new StringBuilder("Activity '")).append(s).append("' has not been started.").toString());
        } else
        {
            endActivity(measuredactivity);
            return measuredactivity;
        }
    }

    public void endActivity(MeasuredActivity measuredactivity)
    {
        b.removeMeasurementConsumer(measuredactivity.getMeasurementPool());
        a.remove(measuredactivity.getName());
        measuredactivity.finish();
    }

    public void removeMeasurementConsumer(MeasurementConsumer measurementconsumer)
    {
        b.removeMeasurementConsumer(measurementconsumer);
    }

    public void removeMeasurementProducer(MeasurementProducer measurementproducer)
    {
        b.removeMeasurementProducer(measurementproducer);
    }

    public void renameActivity(String s, String s1)
    {
        MeasuredActivity measuredactivity = (MeasuredActivity)a.remove(s);
        if (measuredactivity != null && (measuredactivity instanceof NamedActivity))
        {
            a.put(s1, measuredactivity);
            ((NamedActivity)measuredactivity).rename(s1);
        }
    }

    public MeasuredActivity startActivity(String s)
    {
        if (a.containsKey(s))
        {
            throw new MeasurementException((new StringBuilder("An activity with the name '")).append(s).append("' has already started.").toString());
        } else
        {
            NamedActivity namedactivity = new NamedActivity(s);
            a.put(s, namedactivity);
            MeasurementPool measurementpool = new MeasurementPool();
            namedactivity.setMeasurementPool(measurementpool);
            b.addMeasurementConsumer(measurementpool);
            return namedactivity;
        }
    }
}
