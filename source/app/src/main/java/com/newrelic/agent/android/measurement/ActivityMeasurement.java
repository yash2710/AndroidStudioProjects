// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.newrelic.agent.android.measurement;


// Referenced classes of package com.newrelic.agent.android.measurement:
//            BaseMeasurement, MeasurementType

public class ActivityMeasurement extends BaseMeasurement
{

    public ActivityMeasurement(String s, long l, long l1)
    {
        super(MeasurementType.Activity);
        setName(s);
        setStartTime(l);
        setEndTime(l1);
    }
}
