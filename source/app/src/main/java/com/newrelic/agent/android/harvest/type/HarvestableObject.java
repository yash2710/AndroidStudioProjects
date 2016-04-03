// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.newrelic.agent.android.harvest.type;

import com.newrelic.com.google.gson.JsonObject;
import java.util.Map;

// Referenced classes of package com.newrelic.agent.android.harvest.type:
//            BaseHarvestable, c

public abstract class HarvestableObject extends BaseHarvestable
{

    public HarvestableObject()
    {
        super(Harvestable.Type.OBJECT);
    }

    public static HarvestableObject fromMap(Map map)
    {
        return new c(map);
    }

    public abstract JsonObject asJsonObject();
}
