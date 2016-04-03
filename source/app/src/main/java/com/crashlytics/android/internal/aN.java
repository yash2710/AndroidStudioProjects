// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.crashlytics.android.internal;

import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import org.json.JSONObject;

// Referenced classes of package com.crashlytics.android.internal:
//            v, q, ab

public class aN
{

    aN()
    {
    }

    public JSONObject a()
    {
        v.a().b().a("Crashlytics", "Reading cached settings...");
        File file = new File(v.a().h(), "com.crashlytics.settings.json");
        if (!file.exists()) goto _L2; else goto _L1
_L1:
        FileInputStream fileinputstream = new FileInputStream(file);
        JSONObject jsonobject;
        String s = ab.a(fileinputstream);
        JVM INSTR new #57  <Class JSONObject>;
        jsonobject = JSONObjectInstrumentation.init(s);
        JSONObject jsonobject1 = jsonobject;
_L4:
        ab.a(fileinputstream, "Error while closing settings cache file.");
        return jsonobject1;
_L2:
        v.a().b().a("Crashlytics", "No cached settings found.");
        jsonobject1 = null;
        fileinputstream = null;
        if (true) goto _L4; else goto _L3
_L3:
        Exception exception2;
        exception2;
        Object obj = null;
_L8:
        v.a().b().a("Crashlytics", "Failed to fetch cached settings", exception2);
        ab.a(((java.io.Closeable) (obj)), "Error while closing settings cache file.");
        return null;
        Exception exception;
        exception;
        Exception exception1;
        obj = null;
        exception1 = exception;
_L6:
        ab.a(((java.io.Closeable) (obj)), "Error while closing settings cache file.");
        throw exception1;
        exception1;
        obj = fileinputstream;
        continue; /* Loop/switch isn't completed */
        exception1;
        if (true) goto _L6; else goto _L5
_L5:
        Exception exception3;
        exception3;
        obj = fileinputstream;
        exception2 = exception3;
        if (true) goto _L8; else goto _L7
_L7:
    }

    public void a(long l, JSONObject jsonobject)
    {
        v.a().b().a("Crashlytics", "Writing settings to cache file...");
        if (jsonobject == null) goto _L2; else goto _L1
_L1:
        FileWriter filewriter;
        jsonobject.put("expires_at", l);
        filewriter = new FileWriter(new File(v.a().h(), "com.crashlytics.settings.json"));
        if (jsonobject instanceof JSONObject) goto _L4; else goto _L3
_L3:
        String s1 = jsonobject.toString();
_L5:
        filewriter.write(s1);
        filewriter.flush();
        ab.a(filewriter, "Failed to close settings writer.");
_L2:
        return;
_L4:
        String s = JSONObjectInstrumentation.toString((JSONObject)jsonobject);
        s1 = s;
          goto _L5
        Exception exception1;
        exception1;
        filewriter = null;
_L9:
        v.a().b().a("Crashlytics", "Failed to cache settings", exception1);
        ab.a(filewriter, "Failed to close settings writer.");
        return;
        Exception exception;
        exception;
        filewriter = null;
_L7:
        ab.a(filewriter, "Failed to close settings writer.");
        throw exception;
        exception;
        if (true) goto _L7; else goto _L6
_L6:
        exception1;
        if (true) goto _L9; else goto _L8
_L8:
    }
}
