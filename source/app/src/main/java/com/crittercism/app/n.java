// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.crittercism.app;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import crittercism.android.i;
import crittercism.android.k;
import crittercism.android.m;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Vector;
import org.json.JSONObject;

// Referenced classes of package com.crittercism.app:
//            Crittercism

final class n
    implements Thread.UncaughtExceptionHandler
{

    private Thread.UncaughtExceptionHandler a;

    public n(Crittercism crittercism, Thread.UncaughtExceptionHandler uncaughtexceptionhandler)
    {
        a = uncaughtexceptionhandler;
    }

    public final void uncaughtException(Thread thread, Throwable throwable)
    {
        Exception exception1;
        Exception exception2;
        k k1;
        Exception exception3;
        android.content.SharedPreferences.Editor editor;
        String s;
        JSONObject jsonobject;
        String s1;
        String s2;
        m m1;
        Exception exception4;
        android.content.SharedPreferences.Editor editor1;
        String s3;
        JSONObject jsonobject1;
        String s4;
        String s5;
        i l;
        Exception exception5;
        android.content.SharedPreferences.Editor editor2;
        String s6;
        JSONObject jsonobject2;
        String s7;
        String s8;
        try
        {
            StringWriter stringwriter = new StringWriter();
            throwable.printStackTrace(new PrintWriter(stringwriter));
            if (!Crittercism.getOptOutStatus())
            {
                Log.e("Crittercism", stringwriter.toString());
            }
        }
        catch (Exception exception) { }
        if (Crittercism.a() == null)
        {
            throw new Exception("Failed to log error to Crittercism.");
        }
          goto _L1
        exception2;
        Log.w("Crittercism", "Failed to log error with Crittercism.  Please contact us at support@crittercism.com.");
        (new StringBuilder("Did not log error to Crittercism.  EXCEPTION: ")).append(exception2.getClass().getName());
        if (a != null && !(a instanceof n))
        {
            a.uncaughtException(thread, throwable);
        }
_L13:
        return;
_L1:
        if (Crittercism.getOptOutStatus())
        {
            continue; /* Loop/switch isn't completed */
        }
        Crittercism.A();
        Crittercism.a().a(throwable);
        Crittercism.a().i();
        if (Crittercism.a().q().e().size() <= 0) goto _L3; else goto _L2
_L2:
        i j = i.d();
        j.a(Crittercism.a().q().e());
        Crittercism.a().a(j);
        l = Crittercism.a().q();
        editor2 = Crittercism.a().n().getSharedPreferences("com.crittercism.loads", 0).edit();
        editor2.remove(i.a());
        s6 = i.a();
        jsonobject2 = l.c();
        if (jsonobject2 instanceof JSONObject) goto _L5; else goto _L4
_L4:
        s8 = jsonobject2.toString();
_L14:
        editor2.putString(s6, s8);
        if (!editor2.commit())
        {
            throw new Exception("commit failed");
        }
          goto _L3
        exception5;
_L3:
        if (Crittercism.a().r().e().size() <= 0) goto _L7; else goto _L6
_L6:
        m1 = Crittercism.a().r();
        editor1 = Crittercism.a().n().getSharedPreferences("com.crittercism.crashes", 0).edit();
        editor1.remove(m.a());
        s3 = m.a();
        jsonobject1 = m1.b();
        if (jsonobject1 instanceof JSONObject) goto _L9; else goto _L8
_L8:
        s5 = jsonobject1.toString();
_L15:
        editor1.putString(s3, s5);
        if (!editor1.commit())
        {
            throw new Exception("commit failed");
        }
          goto _L7
        exception4;
_L7:
        if (Crittercism.a().s().e().size() <= 0)
        {
            continue; /* Loop/switch isn't completed */
        }
        k1 = Crittercism.a().s();
        editor = Crittercism.a().n().getSharedPreferences("com.crittercism.exceptions", 0).edit();
        editor.remove(k.a());
        s = k.a();
        jsonobject = k1.b();
        if (jsonobject instanceof JSONObject) goto _L11; else goto _L10
_L10:
        s2 = jsonobject.toString();
_L16:
        editor.putString(s, s2);
        if (!editor.commit())
        {
            throw new Exception("commit failed");
        }
        continue; /* Loop/switch isn't completed */
        exception3;
        if (a == null || (a instanceof n)) goto _L13; else goto _L12
_L12:
        a.uncaughtException(thread, throwable);
        return;
_L5:
        s7 = JSONObjectInstrumentation.toString((JSONObject)jsonobject2);
        s8 = s7;
          goto _L14
_L9:
        s4 = JSONObjectInstrumentation.toString((JSONObject)jsonobject1);
        s5 = s4;
          goto _L15
_L11:
        s1 = JSONObjectInstrumentation.toString((JSONObject)jsonobject);
        s2 = s1;
          goto _L16
        exception1;
        if (a != null && !(a instanceof n))
        {
            a.uncaughtException(thread, throwable);
        }
        throw exception1;
          goto _L14
    }
}
