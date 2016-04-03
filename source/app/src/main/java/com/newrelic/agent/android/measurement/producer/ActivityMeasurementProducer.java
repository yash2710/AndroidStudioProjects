// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.newrelic.agent.android.measurement.producer;

import com.newrelic.agent.android.activity.MeasuredActivity;
import com.newrelic.agent.android.measurement.ActivityMeasurement;
import com.newrelic.agent.android.measurement.MeasurementType;

// Referenced classes of package com.newrelic.agent.android.measurement.producer:
//            BaseMeasurementProducer

public class ActivityMeasurementProducer extends BaseMeasurementProducer
{

    public ActivityMeasurementProducer()
    {
        super(MeasurementType.Activity);
    }

    public void produceMeasurement(MeasuredActivity measuredactivity)
    {
        super.produceMeasurement(new ActivityMeasurement(measuredactivity.getMetricName(), measuredactivity.getStartTime(), measuredactivity.getEndTime()));
        super.produceMeasurement(new ActivityMeasurement(measuredactivity.getBackgroundMetricName(), measuredactivity.getStartTime(), measuredactivity.getEndTime()));
    }
}
