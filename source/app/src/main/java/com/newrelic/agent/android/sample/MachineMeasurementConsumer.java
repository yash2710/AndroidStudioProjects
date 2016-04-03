// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.newrelic.agent.android.sample;

import com.newrelic.agent.android.measurement.Measurement;
import com.newrelic.agent.android.measurement.MeasurementType;
import com.newrelic.agent.android.measurement.consumer.MetricMeasurementConsumer;
import com.newrelic.agent.android.metric.Metric;
import com.newrelic.agent.android.tracing.Sample;

// Referenced classes of package com.newrelic.agent.android.sample:
//            Sampler

public class MachineMeasurementConsumer extends MetricMeasurementConsumer
{

    public MachineMeasurementConsumer()
    {
        super(MeasurementType.Machine);
    }

    public void consumeMeasurement(Measurement measurement)
    {
    }

    protected String formatMetricName(String s)
    {
        return s;
    }

    public void onHarvest()
    {
        Sample sample = Sampler.sampleMemory();
        if (sample != null)
        {
            Metric metric = new Metric("Memory/Used");
            metric.sample(sample.getValue().doubleValue());
            addMetric(metric);
        }
        super.onHarvest();
    }
}
