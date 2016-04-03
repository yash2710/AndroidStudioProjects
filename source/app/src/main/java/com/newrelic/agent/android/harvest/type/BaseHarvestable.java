// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.newrelic.agent.android.harvest.type;

import com.newrelic.com.google.gson.JsonArray;
import com.newrelic.com.google.gson.JsonElement;
import com.newrelic.com.google.gson.JsonObject;
import com.newrelic.com.google.gson.JsonPrimitive;
import java.lang.reflect.Type;

// Referenced classes of package com.newrelic.agent.android.harvest.type:
//            Harvestable, a, b

public class BaseHarvestable
    implements Harvestable
{

    protected static final Type GSON_STRING_MAP_TYPE = (new a()).getType();
    private final Harvestable.Type a;

    public BaseHarvestable(Harvestable.Type type)
    {
        a = type;
    }

    public JsonElement asJson()
    {
        switch (b.a[a.ordinal()])
        {
        default:
            return null;

        case 1: // '\001'
            return asJsonObject();

        case 2: // '\002'
            return asJsonArray();

        case 3: // '\003'
            return asJsonPrimitive();
        }
    }

    public JsonArray asJsonArray()
    {
        return null;
    }

    public JsonObject asJsonObject()
    {
        return null;
    }

    public JsonPrimitive asJsonPrimitive()
    {
        return null;
    }

    public Harvestable.Type getType()
    {
        return a;
    }

    protected void notEmpty(String s)
    {
        if (s == null || s.length() == 0)
        {
            throw new IllegalArgumentException("Missing Harvestable field.");
        } else
        {
            return;
        }
    }

    protected void notNull(Object obj)
    {
        if (obj == null)
        {
            throw new IllegalArgumentException("Null field in Harvestable object");
        } else
        {
            return;
        }
    }

    protected String optional(String s)
    {
        if (s == null)
        {
            s = "";
        }
        return s;
    }

    public String toJsonString()
    {
        return asJson().toString();
    }

}
