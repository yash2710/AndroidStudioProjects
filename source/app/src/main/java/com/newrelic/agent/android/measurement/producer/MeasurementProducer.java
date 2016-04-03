// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.newrelic.agent.android.measurement.producer;

import com.newrelic.agent.android.measurement.Measurement;
import com.newrelic.agent.android.measurement.MeasurementType;
import java.util.Collection;

public interface MeasurementProducer
{

    public abstract Collection drainMeasurements();

    public abstract MeasurementType getMeasurementType();

    public abstract void produceMeasurement(Measurement measurement);

    public abstract void produceMeasurements(Collection collection);
}
