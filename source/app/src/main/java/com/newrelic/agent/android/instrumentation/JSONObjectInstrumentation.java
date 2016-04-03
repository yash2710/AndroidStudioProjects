// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.newrelic.agent.android.instrumentation;

import com.newrelic.agent.android.tracing.TraceMachine;
import java.util.ArrayList;
import java.util.Arrays;
import org.json.JSONException;
import org.json.JSONObject;

// Referenced classes of package com.newrelic.agent.android.instrumentation:
//            MetricCategory

public class JSONObjectInstrumentation
{

    private static final ArrayList a;

    JSONObjectInstrumentation()
    {
    }

    public static JSONObject init(String s)
    {
        JSONObject jsonobject;
        try
        {
            TraceMachine.enterMethod(null, "JSONObject#<init>", a);
            jsonobject = new JSONObject(s);
            TraceMachine.exitMethod();
        }
        catch (JSONException jsonexception)
        {
            TraceMachine.exitMethod();
            throw jsonexception;
        }
        return jsonobject;
    }

    public static String toString(JSONObject jsonobject)
    {
        TraceMachine.enterMethod(null, "JSONObject#toString", a);
        String s = jsonobject.toString();
        TraceMachine.exitMethod();
        return s;
    }

    public static String toString(JSONObject jsonobject, int i)
    {
        TraceMachine.enterMethod(null, "JSONObject#toString", a);
        String s;
        try
        {
            s = jsonobject.toString(i);
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
