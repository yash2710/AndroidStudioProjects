// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package ru.truba.touchgallery.TouchView;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import com.flipkart.logging.FkLogger;
import com.newrelic.agent.android.api.v2.TraceFieldInterface;
import com.newrelic.agent.android.tracing.Trace;
import com.newrelic.agent.android.tracing.TraceMachine;
import java.io.File;

// Referenced classes of package ru.truba.touchgallery.TouchView:
//            UrlTouchImageView

public class b extends AsyncTask
    implements TraceFieldInterface
{

    public Trace _nr_trace;
    private File a;
    private Bitmap b;
    private UrlTouchImageView c;

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

    protected transient Boolean doInBackground(String as[])
    {
        try
        {
            UrlTouchImageView.a(c, b, a);
        }
        catch (Exception exception)
        {
            FkLogger.printStackTrace(exception);
            return Boolean.valueOf(false);
        }
        return Boolean.valueOf(true);
    }

    protected volatile Object doInBackground(Object aobj[])
    {
        TraceMachine.enterMethod(_nr_trace, "UrlTouchImageView$BitmapFileWriterTask#doInBackground", null);
_L1:
        Boolean boolean1 = doInBackground((String[])aobj);
        TraceMachine.exitMethod();
        TraceMachine.unloadTraceContext(this);
        return boolean1;
        NoSuchFieldError nosuchfielderror;
        nosuchfielderror;
        TraceMachine.enterMethod(null, "UrlTouchImageView$BitmapFileWriterTask#doInBackground", null);
          goto _L1
    }

    public (UrlTouchImageView urltouchimageview, Bitmap bitmap, File file)
    {
        c = urltouchimageview;
        super();
        a = file;
        b = bitmap;
    }
}
