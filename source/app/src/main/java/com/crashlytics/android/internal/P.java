// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.crashlytics.android.internal;

import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

// Referenced classes of package com.crashlytics.android.internal:
//            Z, ba, ax, aZ, 
//            ab, ay, v, q, 
//            av

final class P extends Z
    implements ba
{

    public P(String s, String s1, av av)
    {
        this(s, s1, av, ax.a);
    }

    private P(String s, String s1, av av, ax ax1)
    {
        super(s, s1, av, ax1);
    }

    public final JSONObject a(aZ az)
    {
        HashMap hashmap;
        ay ay2;
        hashmap = new HashMap();
        hashmap.put("build_version", az.e);
        hashmap.put("display_version", az.d);
        hashmap.put("source", Integer.toString(az.f));
        if (az.g != null)
        {
            hashmap.put("icon_hash", az.g);
        }
        String s = az.c;
        if (!ab.e(s))
        {
            hashmap.put("instance", s);
        }
        ay2 = a(((Map) (hashmap)));
        ay ay1 = ay2;
        JSONObject jsonobject1;
        ay1 = ay1.a("X-CRASHLYTICS-API-KEY", az.a).a("X-CRASHLYTICS-API-CLIENT-TYPE", "android").a("X-CRASHLYTICS-D", az.b).a("X-CRASHLYTICS-API-CLIENT-VERSION", v.a().getVersion()).a("Accept", "application/json");
        v.a().b().a("Crashlytics", (new StringBuilder("Requesting settings from ")).append(a()).toString());
        v.a().b().a("Crashlytics", (new StringBuilder("Settings query params were: ")).append(hashmap).toString());
        JVM INSTR new #140 <Class JSONObject>;
        jsonobject1 = JSONObjectInstrumentation.init(ay1.c());
        JSONObject jsonobject;
        jsonobject = jsonobject1;
        if (ay1 != null)
        {
            v.a().b().a("Crashlytics", (new StringBuilder("Settings request ID: ")).append(ay1.a("X-REQUEST-ID")).toString());
        }
_L2:
        return jsonobject;
        Exception exception2;
        exception2;
        ay1 = null;
_L5:
        v.a().b().a("Crashlytics", (new StringBuilder("Failed to retrieve settings from ")).append(a()).toString(), exception2);
        jsonobject = null;
        if (ay1 == null) goto _L2; else goto _L1
_L1:
        v.a().b().a("Crashlytics", (new StringBuilder("Settings request ID: ")).append(ay1.a("X-REQUEST-ID")).toString());
        return null;
        Exception exception;
        exception;
        Exception exception1;
        ay1 = null;
        exception1 = exception;
_L4:
        if (ay1 != null)
        {
            v.a().b().a("Crashlytics", (new StringBuilder("Settings request ID: ")).append(ay1.a("X-REQUEST-ID")).toString());
        }
        throw exception1;
        exception1;
        if (true) goto _L4; else goto _L3
_L3:
        exception2;
          goto _L5
    }
}
