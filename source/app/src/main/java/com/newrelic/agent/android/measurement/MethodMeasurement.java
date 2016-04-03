// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.newrelic.agent.android.measurement;

import com.newrelic.agent.android.instrumentation.MetricCategory;

// Referenced classes of package com.newrelic.agent.android.measurement:
//            CategorizedMeasurement, MeasurementType

public class MethodMeasurement extends CategorizedMeasurement
{

    public MethodMeasurement(String s, String s1, long l, long l1, long l2, MetricCategory metriccategory)
    {
        super(MeasurementType.Method);
        setName(s);
        setScope(s1);
        setStartTime(l);
        setEndTime(l1);
        setExclusiveTime(l2);
        setCategory(metriccategory);
    }
}
