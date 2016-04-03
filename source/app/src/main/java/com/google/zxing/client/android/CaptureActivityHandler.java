// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing.client.android;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import com.flipkart.logging.FkLogger;
import com.google.zxing.Result;
import com.google.zxing.client.android.camera.CameraManager;
import com.newrelic.agent.android.instrumentation.BitmapFactoryInstrumentation;
import java.util.Collection;
import java.util.Map;

// Referenced classes of package com.google.zxing.client.android:
//            i, s, CaptureActivity, e

public final class CaptureActivityHandler extends Handler
{

    private static final String a = com/google/zxing/client/android/CaptureActivityHandler.getSimpleName();
    private final CaptureActivity b;
    private final i c;
    private e d;
    private final CameraManager e;

    CaptureActivityHandler(CaptureActivity captureactivity, Collection collection, Map map, String s1, CameraManager cameramanager)
    {
        b = captureactivity;
        c = new i(captureactivity, collection, map, s1, new s(captureactivity.a()));
        c.start();
        d = e.b;
        e = cameramanager;
        cameramanager.startPreview();
        a();
    }

    private void a()
    {
        if (d == e.b)
        {
            d = e.a;
            e.requestPreviewFrame(c.a(), R.id.decode);
            b.drawViewfinder();
        }
    }

    public final void handleMessage(Message message)
    {
        if (message.what == R.id.restart_preview)
        {
            a();
        } else
        {
            if (message.what == R.id.decode_succeeded)
            {
                d = e.b;
                Bundle bundle = message.getData();
                float f;
                Bitmap bitmap;
                if (bundle != null)
                {
                    byte abyte0[] = bundle.getByteArray("barcode_bitmap");
                    String s1;
                    Intent intent;
                    ResolveInfo resolveinfo;
                    ActivityInfo activityinfo;
                    String s2;
                    ActivityNotFoundException activitynotfoundexception;
                    Bitmap bitmap1;
                    if (abyte0 != null)
                    {
                        bitmap1 = BitmapFactoryInstrumentation.decodeByteArray(abyte0, 0, abyte0.length, null).copy(android.graphics.Bitmap.Config.ARGB_8888, true);
                    } else
                    {
                        bitmap1 = null;
                    }
                    f = bundle.getFloat("barcode_scaled_factor");
                    bitmap = bitmap1;
                } else
                {
                    f = 1.0F;
                    bitmap = null;
                }
                b.handleDecode((Result)message.obj, bitmap, f);
                return;
            }
            if (message.what == R.id.decode_failed)
            {
                d = e.a;
                e.requestPreviewFrame(c.a(), R.id.decode);
                return;
            }
            if (message.what == R.id.return_scan_result)
            {
                b.setResult(-1, (Intent)message.obj);
                b.finish();
                return;
            }
            if (message.what == R.id.launch_product_query)
            {
                s1 = (String)message.obj;
                intent = new Intent("android.intent.action.VIEW");
                intent.addFlags(0x80000);
                intent.setData(Uri.parse(s1));
                resolveinfo = b.getPackageManager().resolveActivity(intent, 0x10000);
                activityinfo = resolveinfo.activityInfo;
                s2 = null;
                if (activityinfo != null)
                {
                    s2 = resolveinfo.activityInfo.packageName;
                }
                if ("com.android.browser".equals(s2) || "com.android.chrome".equals(s2))
                {
                    intent.setPackage(s2);
                    intent.addFlags(0x10000000);
                    intent.putExtra("com.android.browser.application_id", s2);
                }
                try
                {
                    b.startActivity(intent);
                    return;
                }
                // Misplaced declaration of an exception variable
                catch (ActivityNotFoundException activitynotfoundexception)
                {
                    FkLogger.warn(a, (new StringBuilder("Can't find anything to handle VIEW of URI ")).append(s1).toString());
                }
                return;
            }
        }
    }

    public final void quitSynchronously()
    {
        d = e.c;
        e.stopPreview();
        Message.obtain(c.a(), R.id.quit).sendToTarget();
        try
        {
            c.join(500L);
        }
        catch (InterruptedException interruptedexception) { }
        removeMessages(R.id.decode_succeeded);
        removeMessages(R.id.decode_failed);
    }

}
