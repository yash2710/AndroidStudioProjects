// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.newrelic.agent.android.activity.config;

import com.newrelic.agent.android.logging.AgentLog;
import com.newrelic.agent.android.logging.AgentLogManager;
import com.newrelic.com.google.gson.JsonArray;
import com.newrelic.com.google.gson.JsonDeserializationContext;
import com.newrelic.com.google.gson.JsonDeserializer;
import com.newrelic.com.google.gson.JsonElement;
import com.newrelic.com.google.gson.JsonPrimitive;
import java.lang.reflect.Type;

// Referenced classes of package com.newrelic.agent.android.activity.config:
//            ActivityTraceConfiguration

public class ActivityTraceConfigurationDeserializer
    implements JsonDeserializer
{

    private final AgentLog a = AgentLogManager.getAgentLog();

    public ActivityTraceConfigurationDeserializer()
    {
    }

    private void a(String s)
    {
        a.error((new StringBuilder("ActivityTraceConfigurationDeserializer: ")).append(s).toString());
    }

    public ActivityTraceConfiguration deserialize(JsonElement jsonelement, Type type, JsonDeserializationContext jsondeserializationcontext)
    {
        ActivityTraceConfiguration activitytraceconfiguration = new ActivityTraceConfiguration();
        if (!jsonelement.isJsonArray())
        {
            a("Expected root element to be an array.");
        } else
        {
            JsonArray jsonarray = jsonelement.getAsJsonArray();
            if (jsonarray.size() != 2)
            {
                a("Root array must contain 2 elements.");
                return null;
            }
            JsonElement jsonelement1 = jsonarray.get(0);
            Integer integer;
            if (!jsonelement1.isJsonPrimitive())
            {
                a("Expected an integer.");
                integer = null;
            } else
            {
                JsonPrimitive jsonprimitive = jsonelement1.getAsJsonPrimitive();
                if (!jsonprimitive.isNumber())
                {
                    a("Expected an integer.");
                    integer = null;
                } else
                {
                    int i = jsonprimitive.getAsInt();
                    if (i < 0)
                    {
                        a("Integer value must not be negative");
                        integer = null;
                    } else
                    {
                        integer = Integer.valueOf(i);
                    }
                }
            }
            if (integer != null)
            {
                if (integer.intValue() < 0)
                {
                    a("The first element of the root array must not be negative.");
                    return null;
                } else
                {
                    activitytraceconfiguration.setMaxTotalTraceCount(integer.intValue());
                    return activitytraceconfiguration;
                }
            }
        }
        return null;
    }

    public volatile Object deserialize(JsonElement jsonelement, Type type, JsonDeserializationContext jsondeserializationcontext)
    {
        return deserialize(jsonelement, type, jsondeserializationcontext);
    }
}
