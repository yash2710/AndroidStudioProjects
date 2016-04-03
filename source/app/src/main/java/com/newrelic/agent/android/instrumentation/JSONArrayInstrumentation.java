// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.newrelic.agent.android.instrumentation;

import com.newrelic.agent.android.tracing.TraceMachine;
import java.util.ArrayList;
import java.util.Arrays;
import org.json.JSONArray;
import org.json.JSONException;

// Referenced classes of package com.newrelic.agent.android.instrumentation:
//            MetricCategory

public class JSONArrayInstrumentation
{

    private static final ArrayList a;

    JSONArrayInstrumentation()
    {
    }

    public static JSONArray init(String s)
    {
        JSONArray jsonarray;
        try
        {
            TraceMachine.enterMethod("JSONArray#<init>", a);
            jsonarray = new JSONArray(s);
            TraceMachine.exitMethod();
        }
        catch (JSONException jsonexception)
        {
            TraceMachine.exitMethod();
            throw jsonexception;
        }
        return jsonarray;
    }

    public static String toString(JSONArray jsonarray)
    {
        TraceMachine.enterMethod("JSONArray#toString", a);
        String s = jsonarray.toString();
        TraceMachine.exitMethod();
        return s;
    }

    public static String toString(JSONArray jsonarray, int i)
    {
        String s;
        try
        {
            TraceMachine.enterMethod("JSONArray#toString", a);
            s = jsonarray.toString(i);
            TraceMachine.exitMethod();
        }
        catch (JSONException jsonexception)
        {
            TraceMachine.exitMethod();
            throw jsonexception;
        }
        return s;
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
