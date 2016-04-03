// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.newrelic.agent.android.measurement.producer;

import com.newrelic.agent.android.measurement.Measurement;
import com.newrelic.agent.android.measurement.MeasurementType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

// Referenced classes of package com.newrelic.agent.android.measurement.producer:
//            MeasurementProducer

public class BaseMeasurementProducer
    implements MeasurementProducer
{

    private final MeasurementType a;
    private final ArrayList b = new ArrayList();

    public BaseMeasurementProducer(MeasurementType measurementtype)
    {
        a = measurementtype;
    }

    public Collection drainMeasurements()
    {
        if (b.size() == 0)
        {
            return Collections.emptyList();
        }
        this;
        JVM INSTR monitorenter ;
        ArrayList arraylist;
        arraylist = new ArrayList(b);
        b.clear();
        this;
        JVM INSTR monitorexit ;
        return arraylist;
        Exception exception;
        exception;
        throw exception;
    }

    public MeasurementType getMeasurementType()
    {
        return a;
    }

    public void produceMeasurement(Measurement measurement)
    {
        synchronized (b)
        {
            b.add(measurement);
        }
    }

    public void produceMeasurements(Collection collection)
    {
        synchronized (b)
        {
            b.addAll(collection);
        }
    }
}
