// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.crashlytics.android;

import android.os.AsyncTask;
import com.newrelic.agent.android.api.v2.TraceFieldInterface;
import com.newrelic.agent.android.tracing.Trace;
import com.newrelic.agent.android.tracing.TraceMachine;

// Referenced classes of package com.crashlytics.android:
//            CrashTest

final class N extends AsyncTask
    implements TraceFieldInterface
{

    public Trace _nr_trace;
    private long a;
    private CrashTest b;

    N(CrashTest crashtest, long l)
    {
        b = crashtest;
        a = l;
        super();
    }

    private transient Void a()
    {
        try
        {
            Thread.sleep(a);
        }
        catch (InterruptedException interruptedexception) { }
        b.throwRuntimeException("Background thread crash");
        return null;
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

    protected final Object doInBackground(Object aobj[])
    {
        TraceMachine.enterMethod(_nr_trace, "N#doInBackground", null);
_L1:
        Void void1 = a();
        TraceMachine.exitMethod();
        TraceMachine.unloadTraceContext(this);
        return void1;
        NoSuchFieldError nosuchfielderror;
        nosuchfielderror;
        TraceMachine.enterMethod(null, "N#doInBackground", null);
          goto _L1
    }
}
