// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.AsyncTask;
import android.os.Handler;
import android.webkit.WebView;
import com.newrelic.agent.android.api.v2.TraceFieldInterface;
import com.newrelic.agent.android.tracing.Trace;
import com.newrelic.agent.android.tracing.TraceMachine;

// Referenced classes of package com.google.android.gms.internal:
//            do, eu

public final class pQ extends AsyncTask
    implements TraceFieldInterface
{

    public Trace _nr_trace;
    private final WebView pQ;
    private Bitmap pR;
    final do pS;

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

    protected final transient Boolean a(Void avoid[])
    {
        this;
        JVM INSTR monitorenter ;
        int i;
        int j;
        i = pR.getWidth();
        j = pR.getHeight();
        if (i != 0 && j != 0) goto _L2; else goto _L1
_L1:
        Boolean boolean1 = Boolean.valueOf(false);
        Boolean boolean2 = boolean1;
_L7:
        this;
        JVM INSTR monitorexit ;
        return boolean2;
_L2:
        int k;
        int l;
        k = 0;
        l = 0;
_L9:
        if (k >= i) goto _L4; else goto _L3
_L3:
        int i1 = 0;
_L8:
        if (i1 >= j) goto _L6; else goto _L5
_L5:
        if (pR.getPixel(k, i1) != 0)
        {
            l++;
        }
        continue; /* Loop/switch isn't completed */
_L4:
        boolean flag;
        Boolean boolean3;
        if ((double)l / ((double)(i * j) / 100D) > 0.10000000000000001D)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        boolean3 = Boolean.valueOf(flag);
        boolean2 = boolean3;
          goto _L7
        Exception exception;
        exception;
        throw exception;
          goto _L8
_L6:
        k += 10;
          goto _L9
    }

    protected final void a(Boolean boolean1)
    {
        com.google.android.gms.internal.do.c(pS);
        if (boolean1.booleanValue() || pS.bq() || com.google.android.gms.internal.do.d(pS) <= 0L)
        {
            pS.pP = boolean1.booleanValue();
            com.google.android.gms.internal.do.e(pS).a(pS.lN);
        } else
        if (com.google.android.gms.internal.do.d(pS) > 0L)
        {
            if (eu.p(2))
            {
                eu.z("Ad not detected, scheduling another run.");
            }
            com.google.android.gms.internal.do.g(pS).postDelayed(pS, com.google.android.gms.internal.do.f(pS));
            return;
        }
    }

    protected final Object doInBackground(Object aobj[])
    {
        TraceMachine.enterMethod(_nr_trace, "do$a#doInBackground", null);
_L1:
        Boolean boolean1 = a((Void[])aobj);
        TraceMachine.exitMethod();
        TraceMachine.unloadTraceContext(this);
        return boolean1;
        NoSuchFieldError nosuchfielderror;
        nosuchfielderror;
        TraceMachine.enterMethod(null, "do$a#doInBackground", null);
          goto _L1
    }

    protected final void onPostExecute(Object obj)
    {
        TraceMachine.enterMethod(_nr_trace, "do$a#onPostExecute", null);
_L1:
        a((Boolean)obj);
        TraceMachine.exitMethod();
        return;
        NoSuchFieldError nosuchfielderror;
        nosuchfielderror;
        TraceMachine.enterMethod(null, "do$a#onPostExecute", null);
          goto _L1
    }

    protected final void onPreExecute()
    {
        this;
        JVM INSTR monitorenter ;
        pR = Bitmap.createBitmap(com.google.android.gms.internal.do.a(pS), com.google.android.gms.internal.do.b(pS), android.graphics.p.Config.ARGB_8888);
        pQ.setVisibility(0);
        pQ.measure(android.view.MeasureSpec.makeMeasureSpec(com.google.android.gms.internal.do.a(pS), 0), android.view.MeasureSpec.makeMeasureSpec(com.google.android.gms.internal.do.b(pS), 0));
        pQ.layout(0, 0, com.google.android.gms.internal.do.a(pS), com.google.android.gms.internal.do.b(pS));
        Canvas canvas = new Canvas(pR);
        pQ.draw(canvas);
        pQ.invalidate();
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public ace(do do1, WebView webview)
    {
        pS = do1;
        super();
        pQ = webview;
    }
}
