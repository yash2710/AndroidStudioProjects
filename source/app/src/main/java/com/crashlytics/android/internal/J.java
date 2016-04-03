// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.crashlytics.android.internal;

import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;

// Referenced classes of package com.crashlytics.android.internal:
//            H, I

final class J
{

    J()
    {
    }

    private static JSONObject a(Map map)
    {
        JSONObject jsonobject = new JSONObject();
        java.util.Map.Entry entry;
        for (Iterator iterator = map.entrySet().iterator(); iterator.hasNext(); jsonobject.put((String)entry.getKey(), entry.getValue()))
        {
            entry = (java.util.Map.Entry)iterator.next();
        }

        return jsonobject;
    }

    public final byte[] a(H h)
    {
        JSONObject jsonobject;
        jsonobject = new JSONObject();
        jsonobject.put("appBundleId", h.a);
        jsonobject.put("executionId", h.b);
        jsonobject.put("installationId", h.c);
        jsonobject.put("androidId", h.d);
        jsonobject.put("osVersion", h.e);
        jsonobject.put("deviceModel", h.f);
        jsonobject.put("appVersionCode", h.g);
        jsonobject.put("appVersionName", h.h);
        jsonobject.put("timestamp", h.i);
        jsonobject.put("type", h.j.toString());
        jsonobject.put("details", a(h.k));
        if (jsonobject instanceof JSONObject) goto _L2; else goto _L1
_L1:
        String s1 = jsonobject.toString();
_L3:
        return s1.getBytes("UTF-8");
_L2:
        String s = JSONObjectInstrumentation.toString((JSONObject)jsonobject);
        s1 = s;
          goto _L3
        JSONException jsonexception;
        jsonexception;
        throw new IOException(jsonexception.getMessage());
    }
}
