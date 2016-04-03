// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.crashlytics.android.internal;

import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import org.json.JSONObject;

// Referenced classes of package com.crashlytics.android.internal:
//            v, ab, aY, q, 
//            aV, aN, ah, aX, 
//            ba, aZ

public class aW
{

    private aZ a;
    private final aY b;
    private final ah c;
    private final aN d;
    private final ba e;

    public aW(aZ az, ah ah1, aY ay, aN an, ba ba1)
    {
        a = az;
        c = ah1;
        b = ay;
        d = an;
        e = ba1;
    }

    private void a(JSONObject jsonobject, String s)
    {
        JSONObject jsonobject1;
        q q1;
        StringBuilder stringbuilder;
        String s1;
        if (!ab.e(v.a().getContext()))
        {
            jsonobject1 = b.a(jsonobject);
        } else
        {
            jsonobject1 = jsonobject;
        }
        q1 = v.a().b();
        stringbuilder = (new StringBuilder()).append(s);
        if (!(jsonobject1 instanceof JSONObject))
        {
            s1 = jsonobject1.toString();
        } else
        {
            s1 = JSONObjectInstrumentation.toString((JSONObject)jsonobject1);
        }
        q1.a("Crashlytics", stringbuilder.append(s1).toString());
    }

    private aX b(aV av)
    {
        aX ax = null;
        boolean flag = aV.b.equals(av);
        ax = null;
        if (flag) goto _L2; else goto _L1
_L1:
        JSONObject jsonobject = d.a();
        if (jsonobject == null) goto _L4; else goto _L3
_L3:
        aX ax1 = b.a(c, jsonobject);
        if (ax1 == null) goto _L6; else goto _L5
_L5:
        long l;
        a(jsonobject, "Loaded cached settings: ");
        l = c.a();
        if (aV.c.equals(av)) goto _L8; else goto _L7
_L7:
        long l1 = ax1.f;
        boolean flag1;
        if (l1 < l)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        if (flag1) goto _L9; else goto _L8
_L8:
        v.a().b().a("Crashlytics", "Returning cached settings.");
        ax = ax1;
_L2:
        return ax;
_L9:
        v.a().b().a("Crashlytics", "Cached settings have expired.");
        return null;
        Exception exception;
        exception;
_L11:
        v.a().b().a("Crashlytics", "Failed to get cached settings", exception);
        return ax;
_L6:
        v.a().b().a("Crashlytics", "Failed to transform cached settings data.", null);
        return null;
_L4:
        v.a().b().a("Crashlytics", "No cached settings data found.");
        return null;
        Exception exception1;
        exception1;
        ax = ax1;
        exception = exception1;
        if (true) goto _L11; else goto _L10
_L10:
    }

    public aX a()
    {
        return a(aV.a);
    }

    public aX a(aV av)
    {
        boolean flag = v.a().f();
        aX ax1;
        ax1 = null;
        if (flag)
        {
            break MISSING_BLOCK_LABEL_27;
        }
        aX ax2 = b(av);
        ax1 = ax2;
        Exception exception1;
        if (ax1 != null)
        {
            break MISSING_BLOCK_LABEL_89;
        }
        Exception exception;
        aX ax;
        aX ax3;
        JSONObject jsonobject;
        try
        {
            jsonobject = e.a(a);
        }
        catch (Exception exception2)
        {
            ax = ax1;
            exception1 = exception2;
            continue; /* Loop/switch isn't completed */
        }
        if (jsonobject == null)
        {
            break MISSING_BLOCK_LABEL_89;
        }
        ax1 = b.a(c, jsonobject);
        d.a(ax1.f, jsonobject);
        a(jsonobject, "Loaded settings: ");
        ax = ax1;
        if (ax != null)
        {
            break MISSING_BLOCK_LABEL_108;
        }
        ax3 = b(aV.c);
        ax = ax3;
        return ax;
        exception;
        ax = null;
        exception1 = exception;
_L2:
        v.a().b().a("Crashlytics", "Unknown error while loading Crashlytics settings. Crashes will be cached until settings can be retrieved.", exception1);
        return ax;
        exception1;
        if (true) goto _L2; else goto _L1
_L1:
    }
}
