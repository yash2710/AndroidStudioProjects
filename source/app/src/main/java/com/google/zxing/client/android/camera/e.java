// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing.client.android.camera;

import android.graphics.Point;
import android.hardware.Camera;
import android.os.Handler;
import android.os.Message;

// Referenced classes of package com.google.zxing.client.android.camera:
//            b

final class e
    implements android.hardware.Camera.PreviewCallback
{

    private final b a;
    private Handler b;
    private int c;

    e(b b1)
    {
        a = b1;
    }

    final void a(Handler handler, int i)
    {
        b = handler;
        c = i;
    }

    public final void onPreviewFrame(byte abyte0[], Camera camera)
    {
        Point point = a.a();
        Handler handler = b;
        if (point != null && handler != null)
        {
            handler.obtainMessage(c, point.x, point.y, abyte0).sendToTarget();
            b = null;
        }
    }

    static 
    {
        com/google/zxing/client/android/camera/e.getSimpleName();
    }
}
