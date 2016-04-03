// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.newrelic.agent.android.measurement;


// Referenced classes of package com.newrelic.agent.android.measurement:
//            ThreadInfo, MeasurementType

public interface Measurement
{

    public abstract double asDouble();

    public abstract void finish();

    public abstract long getEndTime();

    public abstract double getEndTimeInSeconds();

    public abstract long getExclusiveTime();

    public abstract double getExclusiveTimeInSeconds();

    public abstract String getName();

    public abstract String getScope();

    public abstract long getStartTime();

    public abstract double getStartTimeInSeconds();

    public abstract ThreadInfo getThreadInfo();

    public abstract MeasurementType getType();

    public abstract boolean isFinished();

    public abstract boolean isInstantaneous();
}
