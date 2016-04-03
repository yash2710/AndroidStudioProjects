// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.newrelic.agent.android.tracing;


public class SampleValue
{

    private Double a;
    private boolean b;

    public SampleValue(double d)
    {
        a = Double.valueOf(0.0D);
        setValue(d);
    }

    public SampleValue(long l)
    {
        a = Double.valueOf(0.0D);
        setValue(l);
    }

    public Double asDouble()
    {
        return a;
    }

    public Long asLong()
    {
        return Long.valueOf(a.longValue());
    }

    public Number getValue()
    {
        if (b)
        {
            return asDouble();
        } else
        {
            return asLong();
        }
    }

    public boolean isDouble()
    {
        return b;
    }

    public void setDouble(boolean flag)
    {
        b = flag;
    }

    public void setValue(double d)
    {
        a = Double.valueOf(d);
        b = true;
    }

    public void setValue(long l)
    {
        a = Double.valueOf(l);
        b = false;
    }
}
