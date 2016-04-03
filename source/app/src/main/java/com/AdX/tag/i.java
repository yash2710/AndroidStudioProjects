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

final class i extends AsyncTask
    implements TraceFieldInterface
{

    public Trace _nr_trace;
    private Context a;
    private AdXConnect b;

    public i(AdXConnect adxconnect, Context context)
    {
        b = adxconnect;
        super();
        a = context;
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
        AdXConnect.a(b, a);
        AdXConnect adxconnect = b;
        AdXConnect.a(adxconnect, (new StringBuilder(String.valueOf(AdXConnect.a(adxconnect)))).append("&idfa=").append(AdXConnect.b(b)).append("&isLAT=").append(AdXConnect.c(b)).toString());
        String s = AdXConnect.a(b);
        AdXConnect.d(b);
        if (!AdXConnect.a().equals(""))
        {
            s = (new StringBuilder(String.valueOf(s))).append("&").append(AdXConnect.a()).toString();
        }
        String s1 = AdXURLConnection.connectToURL((new StringBuilder(String.valueOf(AdXConnect.e(b)))).append(AdXConnect.f(b)).append("/atrk/andrdapp?").toString(), s);
        boolean flag;
        if (s1 != null)
        {
            flag = AdXConnect.b(b, s1);
        } else
        {
            flag = false;
        }
        return Boolean.valueOf(flag);
    }

    protected final volatile transient Object doInBackground(Object aobj[])
    {
        TraceMachine.enterMethod(_nr_trace, "i#doInBackground", null);
_L1:
        Boolean boolean1 = doInBackground((Void[])aobj);
        TraceMachine.exitMethod();
        TraceMachine.unloadTraceContext(this);
        return boolean1;
        NoSuchFieldError nosuchfielderror;
        nosuchfielderror;
        TraceMachine.enterMethod(null, "i#doInBackground", null);
          goto _L1
    }
}
