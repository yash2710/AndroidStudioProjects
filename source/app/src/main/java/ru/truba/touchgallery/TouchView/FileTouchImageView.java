// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package ru.truba.touchgallery.TouchView;

import android.content.Context;
import android.os.AsyncTask;
import android.util.AttributeSet;
import com.newrelic.agent.android.instrumentation.AsyncTaskInstrumentation;

// Referenced classes of package ru.truba.touchgallery.TouchView:
//            UrlTouchImageView

public class FileTouchImageView extends UrlTouchImageView
{

    public FileTouchImageView(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
    }

    public FileTouchImageView(Context context, android.view.View.OnClickListener onclicklistener)
    {
        super(context, onclicklistener, null);
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

    private class ImageLoadTask extends UrlTouchImageView.ImageLoadTask
    {

        static void a(ImageLoadTask imageloadtask, Object aobj[])
        {
            imageloadtask.publishProgress(aobj);
        }

        protected transient Bitmap doInBackground(String as[])
        {
            String s = as[0];
            InputStreamWrapper inputstreamwrapper;
            Bitmap bitmap1;
            File file = new File(s);
            inputstreamwrapper = new InputStreamWrapper(new FileInputStream(file), 8192, file.length());
            inputstreamwrapper.setProgressListener(new a(this));
            bitmap1 = BitmapFactoryInstrumentation.decodeStream(inputstreamwrapper);
            Bitmap bitmap = bitmap1;
            inputstreamwrapper.close();
            return bitmap;
            Exception exception;
            exception;
            Exception exception1;
            bitmap = null;
            exception1 = exception;
_L2:
            FkLogger.printStackTrace(exception1);
            return bitmap;
            exception1;
            if (true) goto _L2; else goto _L1
_L1:
        }

        protected volatile Object doInBackground(Object aobj[])
        {
            return doInBackground((String[])aobj);
        }

        public ImageLoadTask()
        {
        }
    }

}
