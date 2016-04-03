// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.newrelic.agent.android.tracing;

import com.newrelic.agent.android.harvest.type.HarvestableArray;
import com.newrelic.com.google.gson.JsonArray;
import com.newrelic.com.google.gson.JsonPrimitive;

// Referenced classes of package com.newrelic.agent.android.tracing:
//            SampleValue

public class Sample extends HarvestableArray
{

    private long a;
    private SampleValue b;
    private SampleType c;

    public Sample(long l)
    {
        setTimestamp(l);
    }

    public Sample(long l, SampleValue samplevalue)
    {
        setTimestamp(l);
        setSampleValue(samplevalue);
    }

    public Sample(SampleType sampletype)
    {
        setSampleType(sampletype);
        setTimestamp(System.currentTimeMillis());
    }

    public JsonArray asJsonArray()
    {
        JsonArray jsonarray = new JsonArray();
        jsonarray.add(new JsonPrimitive(Long.valueOf(a)));
        jsonarray.add(new JsonPrimitive(b.getValue()));
        return jsonarray;
    }

    public SampleType getSampleType()
    {
        return c;
    }

    public SampleValue getSampleValue()
    {
        return b;
    }

    public long getTimestamp()
    {
        return a;
    }

    public Number getValue()
    {
        return b.getValue();
    }

    public void setSampleType(SampleType sampletype)
    {
        c = sampletype;
    }

    public void setSampleValue(double d)
    {
        b = new SampleValue(d);
    }

    public void setSampleValue(long l)
    {
        b = new SampleValue(l);
    }

    public void setSampleValue(SampleValue samplevalue)
    {
        b = samplevalue;
    }

    public void setTimestamp(long l)
    {
        a = l;
    }
}
