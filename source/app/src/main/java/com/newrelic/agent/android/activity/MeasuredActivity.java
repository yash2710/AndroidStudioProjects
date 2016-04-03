// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.newrelic.agent.android.activity;

import com.newrelic.agent.android.measurement.Measurement;
import com.newrelic.agent.android.measurement.MeasurementPool;
import com.newrelic.agent.android.measurement.ThreadInfo;

public interface MeasuredActivity
{

    public abstract void finish();

    public abstract String getBackgroundMetricName();

    public abstract long getEndTime();

    public abstract Measurement getEndingMeasurement();

    public abstract ThreadInfo getEndingThread();

    public abstract MeasurementPool getMeasurementPool();

    public abstract String getMetricName();

    public abstract String getName();

    public abstract long getStartTime();

    public abstract Measurement getStartingMeasurement();

    public abstract ThreadInfo getStartingThread();

    public abstract boolean isAutoInstrumented();

    public abstract boolean isFinished();

    public abstract void setName(String s);
}
