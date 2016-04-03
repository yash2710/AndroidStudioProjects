// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.newrelic.agent.android.harvest.type;

import com.newrelic.com.google.gson.Gson;
import com.newrelic.com.google.gson.JsonObject;
import java.util.Map;

// Referenced classes of package com.newrelic.agent.android.harvest.type:
//            HarvestableObject

final class c extends HarvestableObject
{

    private Map a;

    c(Map map)
    {
        a = map;
        super();
    }

    public final JsonObject asJsonObject()
    {
        return (JsonObject)(new Gson()).toJsonTree(a, GSON_STRING_MAP_TYPE);
    }
}
