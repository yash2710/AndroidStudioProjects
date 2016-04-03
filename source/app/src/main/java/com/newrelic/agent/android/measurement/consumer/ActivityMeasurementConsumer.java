// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.newrelic.agent.android.measurement.consumer;

import com.newrelic.agent.android.measurement.MeasurementType;

// Referenced classes of package com.newrelic.agent.android.measurement.consumer:
//            MetricMeasurementConsumer

public class ActivityMeasurementConsumer extends MetricMeasurementConsumer
{

    public ActivityMeasurementConsumer()
    {
        super(MeasurementType.Activity);
    }

    protected String formatMetricName(String s)
    {
        return s;
    }
}
