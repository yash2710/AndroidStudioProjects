// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package ru.truba.touchgallery.TouchView;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.AttributeSet;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import com.flipkart.logging.FkLogger;
import com.newrelic.agent.android.instrumentation.AsyncTaskInstrumentation;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;

// Referenced classes of package ru.truba.touchgallery.TouchView:
//            f, TouchImageView

public class UrlTouchImageView extends RelativeLayout
{

    private android.view.View.OnClickListener a;
    private android.view.View.OnCreateContextMenuListener b;
    private HttpURLConnection c;
    private boolean d;
    private InputStream e;
    protected Context mContext;
    protected TouchImageView mImageView;
    protected ProgressBar mProgressBar;

    public UrlTouchImageView(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        a = null;
        c = null;
        d = false;
        e = null;
        mContext = context;
        init();
    }

    public UrlTouchImageView(Context context, android.view.View.OnClickListener onclicklistener, android.view.View.OnCreateContextMenuListener oncreatecontextmenulistener)
    {
        super(context);
        a = null;
        c = null;
        d = false;
        e = null;
        a = onclicklistener;
        b = oncreatecontextmenulistener;
        mContext = context;
        init();
    }

    static File a(UrlTouchImageView urltouchimageview, Context context)
    {
        boolean flag = Environment.getExternalStorageState().equals("mounted");
        File file = null;
        if (flag)
        {
            file = new File(Environment.getExternalStorageDirectory(), "data/fka/largeimages");
        }
        if (file != null && !file.exists())
        {
            file.mkdirs();
        }
        return file;
    }

    static InputStream a(UrlTouchImageView urltouchimageview, InputStream inputstream)
    {
        urltouchimageview.e = inputstream;
        return inputstream;
    }

    static HttpURLConnection a(UrlTouchImageView urltouchimageview)
    {
        return urltouchimageview.c;
    }

    static HttpURLConnection a(UrlTouchImageView urltouchimageview, HttpURLConnection httpurlconnection)
    {
        urltouchimageview.c = httpurlconnection;
        return httpurlconnection;
    }

    static void a(UrlTouchImageView urltouchimageview, Bitmap bitmap, File file)
    {
        FileOutputStream fileoutputstream = new FileOutputStream(file);
        if (bitmap == null)
        {
            break MISSING_BLOCK_LABEL_24;
        }
        bitmap.compress(android.graphics.Bitmap.CompressFormat.PNG, 80, fileoutputstream);
        fileoutputstream.close();
_L2:
        return;
        Exception exception1;
        exception1;
        fileoutputstream = null;
_L5:
        FkLogger.printStackTrace(exception1);
        if (fileoutputstream == null) goto _L2; else goto _L1
_L1:
        try
        {
            fileoutputstream.close();
            return;
        }
        catch (Exception exception4)
        {
            return;
        }
        Exception exception2;
        exception2;
        fileoutputstream = null;
_L4:
        Exception exception;
        if (fileoutputstream != null)
        {
            try
            {
                fileoutputstream.close();
            }
            catch (Exception exception3) { }
        }
        throw exception2;
        exception;
        return;
        exception2;
        if (true) goto _L4; else goto _L3
_L3:
        exception1;
          goto _L5
    }

    static InputStream b(UrlTouchImageView urltouchimageview)
    {
        return urltouchimageview.e;
    }

    static boolean c(UrlTouchImageView urltouchimageview)
    {
        return urltouchimageview.d;
    }

    public void cancelOngoingRequest()
    {
        if (c != null)
        {
            f f1 = new f(this);
            Void avoid[] = new Void[0];
            if (!(f1 instanceof AsyncTask))
            {
                f1.execute(avoid);
            } else
            {
                AsyncTaskInstrumentation.execute((AsyncTask)f1, avoid);
            }
        }
        d = true;
    }

    public TouchImageView getImageView()
    {
        return mImageView;
    }

    protected void init()
    {
        mImageView = new TouchImageView(mContext);
        android.widget.RelativeLayout.LayoutParams layoutparams = new android.widget.RelativeLayout.LayoutParams(-1, -1);
        mImageView.setLayoutParams(layoutparams);
        addView(mImageView);
        mImageView.setVisibility(8);
        mImageView.setOnClickListener(a);
        mImageView.setOnCreateContextMenuListener(b);
        mProgressBar = new ProgressBar(mContext, null, 0x1010078);
        android.widget.RelativeLayout.LayoutParams layoutparams1 = new android.widget.RelativeLayout.LayoutParams(-1, -2);
        layoutparams1.addRule(15);
        layoutparams1.setMargins(30, 0, 30, 0);
        mProgressBar.setLayoutParams(layoutparams1);
        mProgressBar.setIndeterminate(false);
        mProgressBar.setMax(100);
        addView(mProgressBar);
    }

    public void setUrl(String s)
    {
        ImageLoadTask imageloadtask = new ImageLoadTask();
        String as[] = {
            s
        };
        if (!(imageloadtask instanceof AsyncTask))
        {
            imageloadtask.execute(as);
            return;
        } else
        {
            AsyncTaskInstrumentation.execute((AsyncTask)imageloadtask, as);
            return;
        }
    }

    private class ImageLoadTask extends AsyncTask
        implements TraceFieldInterface
    {

        public Trace _nr_trace;
        private UrlTouchImageView a;

        static void a(ImageLoadTask imageloadtask, Object aobj[])
        {
            imageloadtask.publishProgress(aobj);
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
            BitmapFileWriterTask bitmapfilewritertask;
            String as1[];
            bitmapfilewritertask = a. new BitmapFileWriterTask(bitmap, file);
            as1 = new String[0];
            if (bitmapfilewritertask instanceof AsyncTask) goto _L4; else goto _L3
_L3:
            bitmapfilewritertask.execute(as1);
_L2:
            inputstreamwrapper.close();
            UrlTouchImageView.b(a).close();
            break MISSING_BLOCK_LABEL_291;
_L4:
            try
            {
                AsyncTaskInstrumentation.execute((AsyncTask)bitmapfilewritertask, as1);
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
                a.mImageView.setScaleType(android.widget.ImageView.ScaleType.CENTER);
                Bitmap bitmap1 = BitmapFactoryInstrumentation.decodeResource(a.getResources(), ru.truba.touchgallery.R.drawable.no_photo);
                a.mImageView.setImageBitmap(bitmap1);
            } else
            {
                a.mImageView.setScaleType(android.widget.ImageView.ScaleType.MATRIX);
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

        public ImageLoadTask()
        {
            a = UrlTouchImageView.this;
            super();
        }

        private class BitmapFileWriterTask extends AsyncTask
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

            public BitmapFileWriterTask(Bitmap bitmap, File file)
            {
                c = UrlTouchImageView.this;
                super();
                a = file;
                b = bitmap;
            }
        }

    }

}
