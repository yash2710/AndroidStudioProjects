// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.newrelic.agent.android.harvest.type;

import com.newrelic.com.google.gson.JsonPrimitive;

// Referenced classes of package com.newrelic.agent.android.harvest.type:
//            HarvestableValue

public class HarvestableDouble extends HarvestableValue
{

    private double a;

    public HarvestableDouble()
    {
    }

    public HarvestableDouble(double d)
    {
        this();
        a = d;
    }

    public JsonPrimitive asJsonPrimitive()
    {
        return new JsonPrimitive(Double.valueOf(a));
    }
}
