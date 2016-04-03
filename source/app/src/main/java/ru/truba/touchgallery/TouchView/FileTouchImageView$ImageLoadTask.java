// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package ru.truba.touchgallery.TouchView;

import android.graphics.Bitmap;
import com.flipkart.logging.FkLogger;
import com.newrelic.agent.android.instrumentation.BitmapFactoryInstrumentation;
import java.io.File;
import java.io.FileInputStream;

// Referenced classes of package ru.truba.touchgallery.TouchView:
//            InputStreamWrapper, a, FileTouchImageView

public class init> extends init>
{

    static void a(init> init>, Object aobj[])
    {
        init>.publishProgress(aobj);
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

    public tation(FileTouchImageView filetouchimageview)
    {
        super(filetouchimageview);
    }
}
