// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.newrelic.agent.android.measurement.consumer;

import com.newrelic.agent.android.harvest.HarvestAdapter;
import com.newrelic.agent.android.measurement.Measurement;
import com.newrelic.agent.android.measurement.MeasurementType;
import java.util.Collection;
import java.util.Iterator;

// Referenced classes of package com.newrelic.agent.android.measurement.consumer:
//            MeasurementConsumer

public class BaseMeasurementConsumer extends HarvestAdapter
    implements MeasurementConsumer
{

    private final MeasurementType a;

    public BaseMeasurementConsumer(MeasurementType measurementtype)
    {
        a = measurementtype;
    }

    public void consumeMeasurement(Measurement measurement)
    {
    }

    public void consumeMeasurements(Collection collection)
    {
        for (Iterator iterator = collection.iterator(); iterator.hasNext(); consumeMeasurement((Measurement)iterator.next())) { }
    }

    public MeasurementType getMeasurementType()
    {
        return a;
    }
}
