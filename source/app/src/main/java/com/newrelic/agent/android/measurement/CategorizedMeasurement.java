// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.newrelic.agent.android.measurement;

import com.newrelic.agent.android.instrumentation.MetricCategory;

// Referenced classes of package com.newrelic.agent.android.measurement:
//            BaseMeasurement, MeasurementType

public class CategorizedMeasurement extends BaseMeasurement
{

    private MetricCategory a;

    public CategorizedMeasurement(MeasurementType measurementtype)
    {
        super(measurementtype);
    }

    public MetricCategory getCategory()
    {
        return a;
    }

    public void setCategory(MetricCategory metriccategory)
    {
        a = metriccategory;
    }
}
