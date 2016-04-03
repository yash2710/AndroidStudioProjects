// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing.client.android;

import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;
import android.preference.PreferenceManager;
import com.flipkart.logging.FkLogger;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.DecodeHintType;
import com.google.zxing.ResultPointCallback;
import java.util.Collection;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

// Referenced classes of package com.google.zxing.client.android:
//            f, g, CaptureActivity

final class i extends Thread
{

    private final CaptureActivity a;
    private final Map b = new EnumMap(com/google/zxing/DecodeHintType);
    private Handler c;
    private final CountDownLatch d = new CountDownLatch(1);

    i(CaptureActivity captureactivity, Collection collection, Map map, String s, ResultPointCallback resultpointcallback)
    {
        a = captureactivity;
        if (map != null)
        {
            b.putAll(map);
        }
        if (collection == null || collection.isEmpty())
        {
            SharedPreferences sharedpreferences = PreferenceManager.getDefaultSharedPreferences(captureactivity);
            collection = EnumSet.noneOf(com/google/zxing/BarcodeFormat);
            if (sharedpreferences.getBoolean("preferences_decode_1D", false))
            {
                collection.addAll(f.b);
            }
            if (sharedpreferences.getBoolean("preferences_decode_QR", false))
            {
                collection.addAll(f.c);
            }
            if (sharedpreferences.getBoolean("preferences_decode_Data_Matrix", false))
            {
                collection.addAll(f.d);
            }
        }
        b.put(DecodeHintType.POSSIBLE_FORMATS, collection);
        if (s != null)
        {
            b.put(DecodeHintType.CHARACTER_SET, s);
        }
        b.put(DecodeHintType.NEED_RESULT_POINT_CALLBACK, resultpointcallback);
        FkLogger.info("DecodeThread", (new StringBuilder("Hints: ")).append(b).toString());
    }

    final Handler a()
    {
        try
        {
            d.await();
        }
        catch (InterruptedException interruptedexception) { }
        return c;
    }

    public final void run()
    {
        Looper.prepare();
        c = new g(a, b);
        d.countDown();
        Looper.loop();
    }
}
