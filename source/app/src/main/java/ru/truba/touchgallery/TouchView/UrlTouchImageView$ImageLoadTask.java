// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package ru.truba.touchgallery.TouchView;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.widget.ProgressBar;
import com.flipkart.logging.FkLogger;
import com.newrelic.agent.android.api.v2.TraceFieldInterface;
import com.newrelic.agent.android.instrumentation.AsyncTaskInstrumentation;
import com.newrelic.agent.android.instrumentation.BitmapFactoryInstrumentation;
import com.newrelic.agent.android.instrumentation.HttpInstrumentation;
import com.newrelic.agent.android.tracing.Trace;
import com.newrelic.agent.android.tracing.TraceMachine;
import java.io.File;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

// Referenced classes of package ru.truba.touchgallery.TouchView:
//            UrlTouchImageView, InputStreamWrapper, g, TouchImageView

public class a extends AsyncTask
    implements TraceFieldInterface
{

    public Trace _nr_trace;
    private UrlTouchImageView a;

    static void a(a a1, Object aobj[])
    {
        a1.publishProgress(aobj);
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

    protected transient Bitmap doInBackground(String as[])
    {
        String s;
        File file;
        Bitmap bitmap;
        s = as[0];
        String s1 = String.valueOf(s.hashCode());
        file = new File(UrlTouchImageView.a(a, a.mContext), s1);
        boolean flag = file.exists();
        bitmap = null;
        if (flag)
        {
            bitmap = BitmapFactoryInstrumentation.decodeFile(file.getPath());
        }
        if (bitmap != null)
        {
            break MISSING_BLOCK_LABEL_291;
        }
        InputStreamWrapper inputstreamwrapper;
        if (UrlTouchImageView.c(a))
        {
            break MISSING_BLOCK_LABEL_291;
        }
        URL url = new URL(s);
        UrlTouchImageView.a(a, (HttpURLConnection)HttpInstrumentation.openConnection(url.openConnection()));
        UrlTouchImageView.a(a).connect();
        UrlTouchImageView.a(a, UrlTouchImageView.a(a).getInputStream());
        int i = UrlTouchImageView.a(a).getContentLength();
        inputstreamwrapper = new InputStreamWrapper(UrlTouchImageView.b(a), 8192, i);
        inputstreamwrapper.setProgressListener(new g(this));
        bitmap = BitmapFactoryInstrumentation.decodeStream(inputstreamwrapper);
        if (bitmap == null) goto _L2; else goto _L1
_L1:
        erTask ertask;
        String as1[];
        ertask = new erTask(a, bitmap, file);
        as1 = new String[0];
        if (ertask instanceof AsyncTask) goto _L4; else goto _L3
_L3:
        ertask.execute(as1);
_L2:
        inputstreamwrapper.close();
        UrlTouchImageView.b(a).close();
        break MISSING_BLOCK_LABEL_291;
_L4:
        try
        {
            AsyncTaskInstrumentation.execute((AsyncTask)ertask, as1);
        }
        catch (OutOfMemoryError outofmemoryerror)
        {
            Bitmap bitmap2 = bitmap;
            outofmemoryerror.printStackTrace();
            return bitmap2;
        }
        catch (Exception exception)
        {
            Bitmap bitmap1 = bitmap;
            FkLogger.printStackTrace(exception);
            return bitmap1;
        }
        if (true) goto _L2; else goto _L5
_L5:
        return bitmap;
    }

    protected volatile Object doInBackground(Object aobj[])
    {
        TraceMachine.enterMethod(_nr_trace, "UrlTouchImageView$ImageLoadTask#doInBackground", null);
_L1:
        Bitmap bitmap = doInBackground((String[])aobj);
        TraceMachine.exitMethod();
        TraceMachine.unloadTraceContext(this);
        return bitmap;
        NoSuchFieldError nosuchfielderror;
        nosuchfielderror;
        TraceMachine.enterMethod(null, "UrlTouchImageView$ImageLoadTask#doInBackground", null);
          goto _L1
    }

    protected void onPostExecute(Bitmap bitmap)
    {
        if (bitmap == null)
        {
            a.mImageView.setScaleType(android.widget.setScaleType);
            Bitmap bitmap1 = BitmapFactoryInstrumentation.decodeResource(a.getResources(), ru.truba.touchgallery.esources);
            a.mImageView.setImageBitmap(bitmap1);
        } else
        {
            a.mImageView.setScaleType(android.widget.setScaleType);
            a.mImageView.setImageBitmap(bitmap);
        }
        a.mImageView.setVisibility(0);
        a.mProgressBar.setVisibility(8);
    }

    protected volatile void onPostExecute(Object obj)
    {
        TraceMachine.enterMethod(_nr_trace, "UrlTouchImageView$ImageLoadTask#onPostExecute", null);
_L1:
        onPostExecute((Bitmap)obj);
        TraceMachine.exitMethod();
        return;
        NoSuchFieldError nosuchfielderror;
        nosuchfielderror;
        TraceMachine.enterMethod(null, "UrlTouchImageView$ImageLoadTask#onPostExecute", null);
          goto _L1
    }

    protected transient void onProgressUpdate(Integer ainteger[])
    {
        a.mProgressBar.setProgress(ainteger[0].intValue());
    }

    protected volatile void onProgressUpdate(Object aobj[])
    {
        onProgressUpdate((Integer[])aobj);
    }

    public ion(UrlTouchImageView urltouchimageview)
    {
        a = urltouchimageview;
        super();
    }
}
