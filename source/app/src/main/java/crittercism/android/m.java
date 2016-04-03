// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package crittercism.android;

import android.content.Context;
import android.content.SharedPreferences;
import com.crittercism.app.Crittercism;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

// Referenced classes of package crittercism.android:
//            j, b, r

public final class m extends j
{

    public String c;
    public JSONArray d;
    public JSONArray e;
    public JSONObject f;

    public m()
    {
        c = new String();
        d = new JSONArray();
        e = new JSONArray();
        f = new JSONObject();
        a = a.c.d;
        b = new Vector();
    }

    private static m a(JSONObject jsonobject)
    {
        m m1;
        JSONObject jsonobject1;
        JSONArray jsonarray;
        m1 = new m();
        jsonobject1 = new JSONObject();
        jsonarray = new JSONArray();
        JSONObject jsonobject2;
        if (!jsonobject.has("requestData"))
        {
            break MISSING_BLOCK_LABEL_44;
        }
        jsonobject2 = jsonobject.getJSONObject("requestData");
        jsonobject1 = jsonobject2;
_L1:
        JSONArray jsonarray2;
        if (!jsonobject1.has("crashes"))
        {
            break MISSING_BLOCK_LABEL_150;
        }
        jsonarray2 = jsonobject1.getJSONArray("crashes");
        JSONArray jsonarray1 = jsonarray2;
_L2:
        int i = 0;
        while (i < jsonarray1.length()) 
        {
            Exception exception;
            Exception exception1;
            try
            {
                m1.a(jsonarray1.getJSONObject(i));
            }
            catch (Exception exception2)
            {
                (new StringBuilder("Exception in SdkCrashes.fromJSON: ")).append(exception2.getClass().getName());
            }
            i++;
        }
        break MISSING_BLOCK_LABEL_148;
        exception;
        jsonobject1 = new JSONObject();
          goto _L1
        exception1;
        jsonarray1 = new JSONArray();
          goto _L2
        return m1;
        jsonarray1 = jsonarray;
          goto _L2
    }

    public static String a()
    {
        String s;
        s = new String();
        if (Crittercism.a() == null)
        {
            break MISSING_BLOCK_LABEL_23;
        }
        String s1 = Crittercism.a().m();
        s = s1;
_L2:
        return (new StringBuilder("critter_pendingcrashes_")).append(s).toString();
        Exception exception;
        exception;
        s = new String();
        if (true) goto _L2; else goto _L1
_L1:
    }

    public static m c()
    {
        m m1;
        JSONObject jsonobject;
        m1 = new m();
        jsonobject = new JSONObject();
        SharedPreferences sharedpreferences;
        String s;
        JSONObject jsonobject1;
        sharedpreferences = Crittercism.a().n().getSharedPreferences("com.crittercism.crashes", 0);
        s = a();
        jsonobject1 = new JSONObject();
        if (jsonobject1 instanceof JSONObject) goto _L2; else goto _L1
_L1:
        String s2 = jsonobject1.toString();
_L4:
        String s3 = sharedpreferences.getString(s, s2);
        JVM INSTR new #29  <Class JSONObject>;
        jsonobject = JSONObjectInstrumentation.init(s3);
        android.content.SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.remove(a());
        if (!editor.commit())
        {
            throw new Exception("failed to remove crashes from Shared Preferences");
        }
          goto _L3
        Exception exception;
        exception;
        (new StringBuilder("Exception in SdkCrashes.readFromDisk(): ")).append(exception.getClass().getName());
_L3:
        m m2;
        String s1;
        try
        {
            m2 = a(jsonobject);
        }
        catch (Exception exception1)
        {
            return m1;
        }
        return m2;
_L2:
        s1 = JSONObjectInstrumentation.toString((JSONObject)jsonobject1);
        s2 = s1;
          goto _L4
    }

    public final void a(Throwable throwable)
    {
        int i = throwable.toString().indexOf(":");
        String s;
        StringWriter stringwriter;
        PrintWriter printwriter;
        if (i >= 0)
        {
            s = throwable.toString().substring(0, i);
        } else
        {
            s = throwable.getClass().getName();
        }
        c = s;
        stringwriter = new StringWriter();
        printwriter = new PrintWriter(stringwriter);
        throwable.printStackTrace(printwriter);
        for (Throwable throwable1 = throwable.getCause(); throwable1 != null; throwable1 = throwable1.getCause())
        {
            c = throwable1.getClass().getName();
            throwable1.printStackTrace(printwriter);
        }

        String s1 = stringwriter.toString();
        d = new JSONArray();
        String as[] = s1.split("\n");
        for (int k = 0; k < as.length; k++)
        {
            d.put(as[k]);
        }

    }

    public final JSONObject b()
    {
        JSONObject jsonobject;
        jsonobject = new JSONObject();
        new JSONObject();
        new JSONArray();
        JSONArray jsonarray = new JSONArray(b);
        JSONArray jsonarray1 = jsonarray;
_L1:
        JSONObject jsonobject1;
        Exception exception2;
        try
        {
            jsonobject1 = Crittercism.a().o().c();
            jsonobject1.put("crashes", jsonarray1);
        }
        catch (Exception exception)
        {
            jsonobject1 = new JSONObject();
        }
        try
        {
            jsonobject.put("requestUrl", a);
            jsonobject.put("requestData", jsonobject1);
        }
        catch (Exception exception1)
        {
            return new JSONObject();
        }
        return jsonobject;
        exception2;
        jsonarray1 = new JSONArray();
          goto _L1
    }

    public final void d()
    {
        Iterator iterator = Thread.getAllStackTraces().entrySet().iterator();
_L4:
        java.util.Map.Entry entry;
        JSONObject jsonobject;
        Thread thread;
        if (!iterator.hasNext())
        {
            break; /* Loop/switch isn't completed */
        }
        entry = (java.util.Map.Entry)iterator.next();
        jsonobject = new JSONObject();
        thread = (Thread)entry.getKey();
        JSONArray jsonarray;
        StackTraceElement astacktraceelement[];
        int i;
        if (thread.getId() == Thread.currentThread().getId())
        {
            continue; /* Loop/switch isn't completed */
        }
        jsonobject.put("name", thread.getName());
        jsonobject.put("id", thread.getId());
        jsonobject.put("state", thread.getState().name());
        jsonarray = new JSONArray();
        astacktraceelement = (StackTraceElement[])entry.getValue();
        i = astacktraceelement.length;
        int k = 0;
_L2:
        if (k >= i)
        {
            break; /* Loop/switch isn't completed */
        }
        StackTraceElement stacktraceelement = astacktraceelement[k];
        (new StringBuilder("  ")).append(stacktraceelement);
        jsonarray.put(stacktraceelement);
        k++;
        if (true) goto _L2; else goto _L1
_L1:
        try
        {
            jsonobject.put("stacktrace", jsonarray);
            e.put(jsonobject);
        }
        catch (Exception exception)
        {
            (new StringBuilder("Problem with setBackgroundThreads(): ")).append(exception.getClass().getName());
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    public final void g()
    {
        FutureTask futuretask;
        ExecutorService executorservice;
        futuretask = new FutureTask(new r(this));
        executorservice = Executors.newFixedThreadPool(10);
        executorservice.execute(futuretask);
        String s = null;
        try
        {
            while (!futuretask.isDone()) 
            {
                s = (String)futuretask.get(2500L, TimeUnit.MILLISECONDS);
            }
        }
        catch (TimeoutException timeoutexception)
        {
            s = null;
        }
        catch (Exception exception)
        {
            (new StringBuilder("Exception in setBreadcrumbs: ")).append(exception.getClass().getName());
            s = null;
        }
        if (s != null)
        {
            try
            {
                JVM INSTR new #29  <Class JSONObject>;
                f = JSONObjectInstrumentation.init(s);
            }
            catch (Exception exception1)
            {
                f = new JSONObject();
                (new StringBuilder("Exception making breadcrumbs in SdkCrashes.setBreadcrumbs: ")).append(exception1.getClass().getName());
            }
        }
        if (f.has("current_session"))
        {
            break MISSING_BLOCK_LABEL_129;
        }
        f.put("current_session", new JSONArray());
        f.put("previous_session", new JSONArray());
        return;
        JSONException jsonexception;
        jsonexception;
        f = new JSONObject();
        return;
    }
}
