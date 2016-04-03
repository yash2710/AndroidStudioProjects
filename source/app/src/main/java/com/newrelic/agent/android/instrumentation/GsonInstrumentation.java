// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.newrelic.agent.android.instrumentation;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.newrelic.agent.android.tracing.TraceMachine;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;

// Referenced classes of package com.newrelic.agent.android.instrumentation:
//            MetricCategory

public class GsonInstrumentation
{

    private static final ArrayList a;

    public GsonInstrumentation()
    {
    }

    public static Object fromJson(Gson gson, JsonElement jsonelement, Class class1)
    {
        TraceMachine.enterMethod("Gson#fromJson", a);
        Object obj = gson.fromJson(jsonelement, class1);
        TraceMachine.exitMethod();
        return obj;
    }

    public static Object fromJson(Gson gson, JsonElement jsonelement, Type type)
    {
        TraceMachine.enterMethod("Gson#fromJson", a);
        Object obj = gson.fromJson(jsonelement, type);
        TraceMachine.exitMethod();
        return obj;
    }

    public static Object fromJson(Gson gson, JsonReader jsonreader, Type type)
    {
        TraceMachine.enterMethod("Gson#fromJson", a);
        Object obj = gson.fromJson(jsonreader, type);
        TraceMachine.exitMethod();
        return obj;
    }

    public static Object fromJson(Gson gson, Reader reader, Class class1)
    {
        TraceMachine.enterMethod("Gson#fromJson", a);
        Object obj = gson.fromJson(reader, class1);
        TraceMachine.exitMethod();
        return obj;
    }

    public static Object fromJson(Gson gson, Reader reader, Type type)
    {
        TraceMachine.enterMethod("Gson#fromJson", a);
        Object obj = gson.fromJson(reader, type);
        TraceMachine.exitMethod();
        return obj;
    }

    public static Object fromJson(Gson gson, String s, Class class1)
    {
        TraceMachine.enterMethod("Gson#fromJson", a);
        Object obj = gson.fromJson(s, class1);
        TraceMachine.exitMethod();
        return obj;
    }

    public static Object fromJson(Gson gson, String s, Type type)
    {
        TraceMachine.enterMethod("Gson#fromJson", a);
        Object obj = gson.fromJson(s, type);
        TraceMachine.exitMethod();
        return obj;
    }

    public static String toJson(Gson gson, JsonElement jsonelement)
    {
        TraceMachine.enterMethod("Gson#toJson", a);
        String s = gson.toJson(jsonelement);
        TraceMachine.exitMethod();
        return s;
    }

    public static String toJson(Gson gson, Object obj)
    {
        TraceMachine.enterMethod("Gson#toJson", a);
        String s = gson.toJson(obj);
        TraceMachine.exitMethod();
        return s;
    }

    public static String toJson(Gson gson, Object obj, Type type)
    {
        TraceMachine.enterMethod("Gson#toJson", a);
        String s = gson.toJson(obj, type);
        TraceMachine.exitMethod();
        return s;
    }

    public static void toJson(Gson gson, JsonElement jsonelement, JsonWriter jsonwriter)
    {
        TraceMachine.enterMethod("Gson#toJson", a);
        gson.toJson(jsonelement, jsonwriter);
        TraceMachine.exitMethod();
    }

    public static void toJson(Gson gson, JsonElement jsonelement, Appendable appendable)
    {
        TraceMachine.enterMethod("Gson#toJson", a);
        gson.toJson(jsonelement, appendable);
        TraceMachine.exitMethod();
    }

    public static void toJson(Gson gson, Object obj, Appendable appendable)
    {
        TraceMachine.enterMethod("Gson#toJson", a);
        gson.toJson(obj, appendable);
        TraceMachine.exitMethod();
    }

    public static void toJson(Gson gson, Object obj, Type type, JsonWriter jsonwriter)
    {
        TraceMachine.enterMethod("Gson#toJson", a);
        gson.toJson(obj, type, jsonwriter);
        TraceMachine.exitMethod();
    }

    public static void toJson(Gson gson, Object obj, Type type, Appendable appendable)
    {
        TraceMachine.enterMethod("Gson#toJson", a);
        gson.toJson(obj, type, appendable);
        TraceMachine.exitMethod();
    }

    static 
    {
        String as[] = new String[3];
        as[0] = "category";
        as[1] = com/newrelic/agent/android/instrumentation/MetricCategory.getName();
        as[2] = "JSON";
        a = new ArrayList(Arrays.asList(as));
    }
}
