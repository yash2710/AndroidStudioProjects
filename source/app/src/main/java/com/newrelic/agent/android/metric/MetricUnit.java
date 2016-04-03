// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.newrelic.agent.android.metric;


public final class MetricUnit extends Enum
{

    public static final MetricUnit BYTES;
    public static final MetricUnit BYTES_PER_SECOND;
    public static final MetricUnit OPERATIONS;
    public static final MetricUnit PERCENT;
    public static final MetricUnit SECONDS;
    private static final MetricUnit b[];
    private String a;

    private MetricUnit(String s, int i, String s1)
    {
        super(s, i);
        a = s1;
    }

    public static MetricUnit valueOf(String s)
    {
        return (MetricUnit)Enum.valueOf(com/newrelic/agent/android/metric/MetricUnit, s);
    }

    public static MetricUnit[] values()
    {
        return (MetricUnit[])b.clone();
    }

    public final String getLabel()
    {
        return a;
    }

    public final void setLabel(String s)
    {
        a = s;
    }

    static 
    {
        PERCENT = new MetricUnit("PERCENT", 0, "%");
        BYTES = new MetricUnit("BYTES", 1, "bytes");
        SECONDS = new MetricUnit("SECONDS", 2, "sec");
        BYTES_PER_SECOND = new MetricUnit("BYTES_PER_SECOND", 3, "bytes/second");
        OPERATIONS = new MetricUnit("OPERATIONS", 4, "op");
        MetricUnit ametricunit[] = new MetricUnit[5];
        ametricunit[0] = PERCENT;
        ametricunit[1] = BYTES;
        ametricunit[2] = SECONDS;
        ametricunit[3] = BYTES_PER_SECOND;
        ametricunit[4] = OPERATIONS;
        b = ametricunit;
    }
}
