// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing.client.android;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.PlanarYUVLuminanceSource;
import com.google.zxing.ReaderException;
import com.google.zxing.client.android.camera.CameraManager;
import com.google.zxing.common.HybridBinarizer;
import java.io.ByteArrayOutputStream;
import java.util.Map;

// Referenced classes of package com.google.zxing.client.android:
//            CaptureActivity

final class g extends Handler
{

    private final CaptureActivity a;
    private final MultiFormatReader b = new MultiFormatReader();
    private boolean c;

    g(CaptureActivity captureactivity, Map map)
    {
        c = true;
        b.setHints(map);
        a = captureactivity;
    }

    public final void handleMessage(Message message)
    {
        if (c) goto _L2; else goto _L1
_L1:
        return;
_L2:
        PlanarYUVLuminanceSource planaryuvluminancesource;
        BinaryBitmap binarybitmap;
        if (message.what != R.id.decode)
        {
            continue; /* Loop/switch isn't completed */
        }
        byte abyte0[] = (byte[])message.obj;
        int i = message.arg1;
        int j = message.arg2;
        System.currentTimeMillis();
        planaryuvluminancesource = a.b().buildLuminanceSource(abyte0, i, j);
        if (planaryuvluminancesource == null)
        {
            break; /* Loop/switch isn't completed */
        }
        binarybitmap = new BinaryBitmap(new HybridBinarizer(planaryuvluminancesource));
        com.google.zxing.Result result1 = b.decodeWithState(binarybitmap);
        com.google.zxing.Result result;
        result = result1;
        b.reset();
_L4:
        Handler handler;
        handler = a.getHandler();
        if (result == null)
        {
            continue; /* Loop/switch isn't completed */
        }
        System.currentTimeMillis();
        if (handler != null)
        {
            Message message1 = Message.obtain(handler, R.id.decode_succeeded, result);
            Bundle bundle = new Bundle();
            int ai[] = planaryuvluminancesource.renderThumbnail();
            int k = planaryuvluminancesource.getThumbnailWidth();
            Bitmap bitmap = Bitmap.createBitmap(ai, 0, k, k, planaryuvluminancesource.getThumbnailHeight(), android.graphics.Bitmap.Config.ARGB_8888);
            ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream();
            bitmap.compress(android.graphics.Bitmap.CompressFormat.JPEG, 50, bytearrayoutputstream);
            bundle.putByteArray("barcode_bitmap", bytearrayoutputstream.toByteArray());
            bundle.putFloat("barcode_scaled_factor", (float)k / (float)planaryuvluminancesource.getWidth());
            message1.setData(bundle);
            message1.sendToTarget();
            return;
        }
        if (true) goto _L1; else goto _L3
        ReaderException readerexception;
        readerexception;
        b.reset();
        result = null;
          goto _L4
        Exception exception;
        exception;
        b.reset();
        throw exception;
        if (handler == null) goto _L1; else goto _L5
_L5:
        Message.obtain(handler, R.id.decode_failed).sendToTarget();
        return;
        if (message.what != R.id.quit) goto _L1; else goto _L6
_L6:
        c = false;
        Looper.myLooper().quit();
        return;
_L3:
        result = null;
          goto _L4
    }

    static 
    {
        com/google/zxing/client/android/g.getSimpleName();
    }
}
