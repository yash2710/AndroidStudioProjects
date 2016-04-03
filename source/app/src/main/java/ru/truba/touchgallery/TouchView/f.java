// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package ru.truba.touchgallery.TouchView;

import android.os.AsyncTask;
import com.newrelic.agent.android.api.v2.TraceFieldInterface;
import com.newrelic.agent.android.tracing.Trace;
import com.newrelic.agent.android.tracing.TraceMachine;
import java.io.InputStream;
import java.net.HttpURLConnection;

// Referenced classes of package ru.truba.touchgallery.TouchView:
//            UrlTouchImageView

final class f extends AsyncTask
    implements TraceFieldInterface
{

    public Trace _nr_trace;
    private UrlTouchImageView a;

    f(UrlTouchImageView urltouchimageview)
    {
        a = urltouchimageview;
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
        TraceMachine.enterMethod(_nr_trace, "f#doInBackground", null);
_L1:
        Void void1 = doInBackground((Void[])aobj);
        TraceMachine.exitMethod();
        TraceMachine.unloadTraceContext(this);
        return void1;
        NoSuchFieldError nosuchfielderror;
        nosuchfielderror;
        TraceMachine.enterMethod(null, "f#doInBackground", null);
          goto _L1
    }

    protected final transient Void doInBackground(Void avoid[])
    {
        UrlTouchImageView.a(a).disconnect();
        try
        {
            if (UrlTouchImageView.b(a) != null)
            {
                UrlTouchImageView.b(a).close();
            }
        }
        catch (Exception exception) { }
        return null;
    }
}
