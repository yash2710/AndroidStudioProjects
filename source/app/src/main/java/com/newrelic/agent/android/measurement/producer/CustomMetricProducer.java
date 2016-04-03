// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.newrelic.agent.android.measurement.producer;

import com.newrelic.agent.android.measurement.CustomMetricMeasurement;
import com.newrelic.agent.android.measurement.MeasurementType;
import com.newrelic.agent.android.metric.MetricUnit;

// Referenced classes of package com.newrelic.agent.android.measurement.producer:
//            BaseMeasurementProducer

public class CustomMetricProducer extends BaseMeasurementProducer
{

    public CustomMetricProducer()
    {
        super(MeasurementType.Custom);
    }

    public void produceMeasurement(String s, String s1, int i, double d, double d1)
    {
        produceMeasurement(s1, s, i, d, d1, null, null);
    }

    public void produceMeasurement(String s, String s1, int i, double d, double d1, 
            MetricUnit metricunit, MetricUnit metricunit1)
    {
        StringBuffer stringbuffer = new StringBuffer();
        stringbuffer.append(s1.replaceAll("[/\\[\\]|*]", ""));
        stringbuffer.append("/");
        stringbuffer.append(s.replaceAll("[/\\[\\]|*]", ""));
        if (metricunit != null || metricunit1 != null)
        {
            stringbuffer.append("[");
            if (metricunit1 != null)
            {
                stringbuffer.append(metricunit1.getLabel());
            }
            if (metricunit != null)
            {
                stringbuffer.append("|");
                stringbuffer.append(metricunit.getLabel());
            }
            stringbuffer.append("]");
        }
        produceMeasurement(((com.newrelic.agent.android.measurement.Measurement) (new CustomMetricMeasurement(stringbuffer.toString(), i, d, d1))));
    }
}
