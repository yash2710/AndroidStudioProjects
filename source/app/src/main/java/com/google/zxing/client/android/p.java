// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing.client.android;

import android.app.Activity;
import android.os.AsyncTask;
import com.flipkart.logging.FkLogger;
import com.newrelic.agent.android.api.v2.TraceFieldInterface;
import com.newrelic.agent.android.tracing.Trace;
import com.newrelic.agent.android.tracing.TraceMachine;

// Referenced classes of package com.google.zxing.client.android:
//            n

final class p extends AsyncTask
    implements TraceFieldInterface
{

    public Trace _nr_trace;
    private n a;

    private p(n n1)
    {
        a = n1;
        super();
    }

    p(n n1, byte byte0)
    {
        this(n1);
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

    protected final transient Object doInBackground(Object aobj[])
    {
        TraceMachine.enterMethod(_nr_trace, "p#doInBackground", null);
_L1:
        NoSuchFieldError nosuchfielderror;
        try
        {
            Thread.sleep(30000L);
            FkLogger.info(n.c(), "Finishing activity due to inactivity");
            n.b(a).finish();
        }
        catch (InterruptedException interruptedexception) { }
        TraceMachine.exitMethod();
        TraceMachine.unloadTraceContext(this);
        return null;
        nosuchfielderror;
        TraceMachine.enterMethod(null, "p#doInBackground", null);
          goto _L1
    }
}
