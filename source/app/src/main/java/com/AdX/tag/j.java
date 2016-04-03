// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.AdX.tag;

import android.content.Context;
import android.os.AsyncTask;
import com.newrelic.agent.android.api.v2.TraceFieldInterface;
import com.newrelic.agent.android.tracing.Trace;
import com.newrelic.agent.android.tracing.TraceMachine;

// Referenced classes of package com.AdX.tag:
//            AdXConnect, AdXURLConnection

final class j extends AsyncTask
    implements TraceFieldInterface
{

    public Trace _nr_trace;
    private Context a;
    private String b;
    private AdXConnect c;

    public j(AdXConnect adxconnect, Context context, String s)
    {
        c = adxconnect;
        super();
        a = context;
        b = s;
    }

    public void _nr_setTrace(Trace trace)
    {
        try
        {
            _nr_trace = trace;
            return;
        }
        catch (Exception exception)
        {
            return;
        }
    }

    protected final transient Boolean doInBackground(Void avoid[])
    {
        String s = AdXConnect.h(c);
        AdXConnect.a(c, a);
        AdXConnect adxconnect = c;
        AdXConnect.a(adxconnect, (new StringBuilder(String.valueOf(AdXConnect.a(adxconnect)))).append("&idfa=").append(AdXConnect.b(c)).append("&isLAT=").append(AdXConnect.c(c)).toString());
        String s1 = AdXConnect.a(c);
        AdXConnect.d(c);
        if (AdXConnect.h(c) == "" || AdXConnect.h(c) == null)
        {
            s = "none";
        }
        String s2 = AdXURLConnection.postToURL((new StringBuilder(String.valueOf(AdXConnect.e(c)))).append(AdXConnect.f(c)).append("/API/RetargetEvent/").append(AdXConnect.g(c)).append("/").append(s).append("/").append(AdXConnect.i(c)).append("?event=adx_v3&platform=Android&").toString(), s1, "payload", b);
        boolean flag;
        if (s2 != null && s2.length() > 0)
        {
            flag = AdXConnect.b(c, s2);
        } else
        {
            flag = false;
        }
        return Boolean.valueOf(flag);
    }

    protected final volatile transient Object doInBackground(Object aobj[])
    {
        TraceMachine.enterMethod(_nr_trace, "j#doInBackground", null);
_L1:
        Boolean boolean1 = doInBackground((Void[])aobj);
        TraceMachine.exitMethod();
        TraceMachine.unloadTraceContext(this);
        return boolean1;
        NoSuchFieldError nosuchfielderror;
        nosuchfielderror;
        TraceMachine.enterMethod(null, "j#doInBackground", null);
          goto _L1
    }
}
