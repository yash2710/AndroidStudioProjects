// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing.client.android.camera;

import android.os.AsyncTask;
import com.newrelic.agent.android.api.v2.TraceFieldInterface;
import com.newrelic.agent.android.tracing.Trace;
import com.newrelic.agent.android.tracing.TraceMachine;

// Referenced classes of package com.google.zxing.client.android.camera:
//            a

final class c extends AsyncTask
    implements TraceFieldInterface
{

    public Trace _nr_trace;
    private a a;

    private c(a a1)
    {
        a = a1;
        super();
    }

    c(a a1, byte byte0)
    {
        this(a1);
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
        TraceMachine.enterMethod(_nr_trace, "c#doInBackground", null);
_L1:
        NoSuchFieldError nosuchfielderror;
        try
        {
            Thread.sleep(2000L);
        }
        catch (InterruptedException interruptedexception) { }
        synchronized (a)
        {
            if (com.google.zxing.client.android.camera.a.a(a))
            {
                a.a();
            }
        }
        TraceMachine.exitMethod();
        TraceMachine.unloadTraceContext(this);
        return null;
        nosuchfielderror;
        TraceMachine.enterMethod(null, "c#doInBackground", null);
          goto _L1
        exception;
        TraceMachine.exitMethod();
        TraceMachine.unloadTraceContext(this);
        throw exception;
    }
}
