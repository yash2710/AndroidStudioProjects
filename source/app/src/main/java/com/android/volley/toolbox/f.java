// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.volley.toolbox;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import com.android.volley.RequestQueue;
import com.newrelic.agent.android.api.v2.TraceFieldInterface;
import com.newrelic.agent.android.tracing.Trace;
import com.newrelic.agent.android.tracing.TraceMachine;
import java.util.HashMap;

// Referenced classes of package com.android.volley.toolbox:
//            ImageLoader, k, ImageRequest, g, 
//            h

final class f extends AsyncTask
    implements TraceFieldInterface
{

    public Trace _nr_trace;
    final String a;
    final String b;
    final ImageLoader c;
    private ImageLoader.ImageContainer d;
    private ImageLoader.ImageListener e;
    private int f;
    private int g;

    f(ImageLoader imageloader, ImageLoader.ImageContainer imagecontainer, ImageLoader.ImageListener imagelistener, String s, String s1, int i, int j)
    {
        c = imageloader;
        d = imagecontainer;
        e = imagelistener;
        a = s;
        b = s1;
        f = i;
        g = j;
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

    protected final transient Bitmap doInBackground(String as[])
    {
        return ImageLoader.c(c).getBitmap(as[0]);
    }

    protected final volatile Object doInBackground(Object aobj[])
    {
        TraceMachine.enterMethod(_nr_trace, "f#doInBackground", null);
_L1:
        Bitmap bitmap = doInBackground((String[])aobj);
        TraceMachine.exitMethod();
        TraceMachine.unloadTraceContext(this);
        return bitmap;
        NoSuchFieldError nosuchfielderror;
        nosuchfielderror;
        TraceMachine.enterMethod(null, "f#doInBackground", null);
          goto _L1
    }

    protected final void onPostExecute(Bitmap bitmap)
    {
        if (bitmap != null)
        {
            ImageLoader.ImageContainer.a(d, bitmap);
            e.onResponse(d, true);
            return;
        }
        k k1 = (k)ImageLoader.a(c).get(a);
        if (k1 != null)
        {
            k1.addContainer(d);
            return;
        } else
        {
            ImageRequest imagerequest = new ImageRequest(b, new g(this), f, g, android.graphics.Bitmap.Config.RGB_565, new h(this));
            ImageLoader.b(c).add(imagerequest);
            ImageLoader.a(c).put(a, new k(c, imagerequest, d));
            return;
        }
    }

    protected final volatile void onPostExecute(Object obj)
    {
        TraceMachine.enterMethod(_nr_trace, "f#onPostExecute", null);
_L1:
        onPostExecute((Bitmap)obj);
        TraceMachine.exitMethod();
        return;
        NoSuchFieldError nosuchfielderror;
        nosuchfielderror;
        TraceMachine.enterMethod(null, "f#onPostExecute", null);
          goto _L1
    }
}
