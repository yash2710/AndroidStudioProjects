// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.newrelic.agent.android.harvest.type;

import com.newrelic.com.google.gson.JsonPrimitive;

// Referenced classes of package com.newrelic.agent.android.harvest.type:
//            BaseHarvestable

public abstract class HarvestableValue extends BaseHarvestable
{

    public HarvestableValue()
    {
        super(Harvestable.Type.VALUE);
    }

    public abstract JsonPrimitive asJsonPrimitive();
}
