// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.newrelic.agent.android.harvest.type;

import com.newrelic.com.google.gson.JsonPrimitive;

// Referenced classes of package com.newrelic.agent.android.harvest.type:
//            HarvestableValue

public class HarvestableLong extends HarvestableValue
{

    private long a;

    public HarvestableLong()
    {
    }

    public HarvestableLong(long l)
    {
        this();
        a = l;
    }

    public JsonPrimitive asJsonPrimitive()
    {
        return new JsonPrimitive(Long.valueOf(a));
    }
}
