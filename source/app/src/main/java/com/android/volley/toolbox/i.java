// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.volley.toolbox;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import com.newrelic.agent.android.api.v2.TraceFieldInterface;
import com.newrelic.agent.android.tracing.Trace;
import com.newrelic.agent.android.tracing.TraceMachine;

// Referenced classes of package com.android.volley.toolbox:
//            ImageLoader

final class i extends AsyncTask
    implements TraceFieldInterface
{

    public Trace _nr_trace;
    private String a;
    private Bitmap b;
    private ImageLoader c;

    i(ImageLoader imageloader, String s, Bitmap bitmap)
    {
        c = imageloader;
        a = s;
        b = bitmap;
        super();
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

    protected final volatile Object doInBackground(Object aobj[])
    {
        TraceMachine.enterMethod(_nr_trace, "i#doInBackground", null);
_L1:
        Void void1 = doInBackground((Void[])aobj);
        TraceMachine.exitMethod();
        TraceMachine.unloadTraceContext(this);
        return void1;
        NoSuchFieldError nosuchfielderror;
        nosuchfielderror;
        TraceMachine.enterMethod(null, "i#doInBackground", null);
          goto _L1
    }

    protected final transient Void doInBackground(Void avoid[])
    {
        ImageLoader.c(c).putBitmap(a, b);
        return null;
    }
}