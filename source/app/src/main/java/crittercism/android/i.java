// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package crittercism.android;

import android.content.Context;
import android.content.SharedPreferences;
import com.crittercism.app.Crittercism;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import java.util.Vector;
import org.json.JSONArray;
import org.json.JSONObject;

// Referenced classes of package crittercism.android:
//            j, b

public final class i extends j
{

    public i()
    {
        a = a.c.b;
        b = new Vector();
    }

    private static i a(JSONObject jsonobject)
    {
        i k;
        JSONObject jsonobject1;
        JSONArray jsonarray;
        k = new i();
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
        if (!jsonobject1.has("app_loads"))
        {
            break MISSING_BLOCK_LABEL_150;
        }
        jsonarray2 = jsonobject1.getJSONArray("app_loads");
        JSONArray jsonarray1 = jsonarray2;
_L2:
        int l = 0;
        while (l < jsonarray1.length()) 
        {
            Exception exception;
            Exception exception1;
            try
            {
                k.a(jsonarray1.getJSONObject(l));
            }
            catch (Exception exception2)
            {
                (new StringBuilder("Exception in AppLoads.fromJSON: ")).append(exception2.getClass().getName());
            }
            l++;
        }
        break MISSING_BLOCK_LABEL_148;
        exception;
        jsonobject1 = new JSONObject();
          goto _L1
        exception1;
        jsonarray1 = new JSONArray();
          goto _L2
        return k;
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
        return (new StringBuilder("critter_pendingapploads_")).append(s).toString();
        Exception exception;
        exception;
        s = new String();
        if (true) goto _L2; else goto _L1
_L1:
    }

    public static i d()
    {
        i k;
        JSONObject jsonobject;
        k = new i();
        jsonobject = new JSONObject();
        SharedPreferences sharedpreferences;
        String s;
        JSONObject jsonobject1;
        sharedpreferences = Crittercism.a().n().getSharedPreferences("com.crittercism.loads", 0);
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
            throw new Exception("failed to remove app loads from Shared Preferences");
        }
          goto _L3
        Exception exception;
        exception;
        (new StringBuilder("Exception in AppLoads.readFromDisk(): ")).append(exception.getClass().getName());
_L3:
        i l;
        String s1;
        try
        {
            l = a(jsonobject);
        }
        catch (Exception exception1)
        {
            return k;
        }
        return l;
_L2:
        s1 = JSONObjectInstrumentation.toString((JSONObject)jsonobject1);
        s2 = s1;
          goto _L4
    }

    public final void b()
    {
        JSONObject jsonobject = new JSONObject();
        new JSONObject();
        JSONObject jsonobject1;
        try
        {
            jsonobject1 = Crittercism.a().o().a(new boolean[] {
                false
            });
            jsonobject1.put("ts", Crittercism.z());
        }
        catch (Exception exception)
        {
            jsonobject1 = new JSONObject();
        }
        try
        {
            jsonobject.put("app_state", jsonobject1);
            jsonobject.put("type", a.a.e);
        }
        catch (Exception exception1)
        {
            jsonobject = null;
        }
        if (jsonobject == null)
        {
            break MISSING_BLOCK_LABEL_77;
        }
        b.add(jsonobject);
        return;
        Exception exception2;
        exception2;
    }

    public final JSONObject c()
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
            jsonobject1.put("app_loads", jsonarray1);
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
}
