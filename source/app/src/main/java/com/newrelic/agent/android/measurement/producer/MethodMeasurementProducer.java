// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.newrelic.agent.android.measurement.producer;

import com.newrelic.agent.android.measurement.MeasurementType;
import com.newrelic.agent.android.measurement.MethodMeasurement;
import com.newrelic.agent.android.tracing.Trace;

// Referenced classes of package com.newrelic.agent.android.measurement.producer:
//            BaseMeasurementProducer

public class MethodMeasurementProducer extends BaseMeasurementProducer
{

    public MethodMeasurementProducer()
    {
        super(MeasurementType.Method);
    }

    public void produceMeasurement(Trace trace)
    {
        produceMeasurement(((com.newrelic.agent.android.measurement.Measurement) (new MethodMeasurement(trace.displayName, trace.scope, trace.entryTimestamp, trace.exitTimestamp, trace.exclusiveTime, trace.getCategory()))));
    }
}
