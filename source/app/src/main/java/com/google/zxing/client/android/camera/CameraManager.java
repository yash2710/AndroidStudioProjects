// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing.client.android.camera;

import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.hardware.Camera;
import android.os.Handler;
import android.view.SurfaceHolder;
import com.flipkart.logging.FkLogger;
import com.google.zxing.PlanarYUVLuminanceSource;
import com.google.zxing.client.android.camera.open.OpenCameraInterface;
import com.google.zxing.client.android.camera.open.OpenCameraManager;
import java.io.IOException;

// Referenced classes of package com.google.zxing.client.android.camera:
//            b, e, a

public final class CameraManager
{

    private static final String a = com/google/zxing/client/android/camera/CameraManager.getSimpleName();
    private final Context b;
    private final b c;
    private Camera d;
    private a e;
    private Rect f;
    private Rect g;
    private boolean h;
    private boolean i;
    private int j;
    private int k;
    private final e l;

    public CameraManager(Context context)
    {
        b = context;
        c = new b(context);
        l = new e(c);
    }

    private static int a(int i1, int j1, int k1)
    {
        int l1 = i1 / 2;
        if (l1 < 240)
        {
            k1 = 240;
        } else
        if (l1 <= k1)
        {
            return l1;
        }
        return k1;
    }

    public final PlanarYUVLuminanceSource buildLuminanceSource(byte abyte0[], int i1, int j1)
    {
        Rect rect = getFramingRectInPreview();
        if (rect == null)
        {
            return null;
        } else
        {
            return new PlanarYUVLuminanceSource(abyte0, i1, j1, rect.left, rect.top, rect.width(), rect.height(), false);
        }
    }

    public final void closeDriver()
    {
        this;
        JVM INSTR monitorenter ;
        if (d != null)
        {
            d.release();
            d = null;
            f = null;
            g = null;
        }
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public final Rect getFramingRect()
    {
        this;
        JVM INSTR monitorenter ;
        if (f != null) goto _L2; else goto _L1
_L1:
        Camera camera = d;
        Rect rect = null;
        if (camera != null) goto _L4; else goto _L3
_L3:
        this;
        JVM INSTR monitorexit ;
        return rect;
_L4:
        Point point = c.b();
        rect = null;
        if (point == null)
        {
            continue; /* Loop/switch isn't completed */
        }
        int i1 = a(point.x, 240, 960);
        int j1 = a(point.y, 240, 540);
        int k1 = (point.x - i1) / 2;
        int l1 = (point.y - j1) / 2;
        f = new Rect(k1, l1, i1 + k1, j1 + l1);
_L2:
        rect = f;
        if (true) goto _L3; else goto _L5
_L5:
        Exception exception;
        exception;
        throw exception;
    }

    public final Rect getFramingRectInPreview()
    {
        this;
        JVM INSTR monitorenter ;
        if (g != null) goto _L2; else goto _L1
_L1:
        Rect rect1 = getFramingRect();
        Rect rect = null;
        if (rect1 != null) goto _L4; else goto _L3
_L3:
        this;
        JVM INSTR monitorexit ;
        return rect;
_L4:
        Rect rect2;
        Point point;
        Point point1;
        rect2 = new Rect(rect1);
        point = c.a();
        point1 = c.b();
        rect = null;
        if (point == null)
        {
            continue; /* Loop/switch isn't completed */
        }
        rect = null;
        if (point1 == null)
        {
            continue; /* Loop/switch isn't completed */
        }
        rect2.left = (rect2.left * point.x) / point1.x;
        rect2.right = (rect2.right * point.x) / point1.x;
        rect2.top = (rect2.top * point.y) / point1.y;
        rect2.bottom = (rect2.bottom * point.y) / point1.y;
        g = rect2;
_L2:
        rect = g;
        if (true) goto _L3; else goto _L5
_L5:
        Exception exception;
        exception;
        throw exception;
    }

    public final boolean isOpen()
    {
        this;
        JVM INSTR monitorenter ;
        Camera camera = d;
        boolean flag;
        if (camera != null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        this;
        JVM INSTR monitorexit ;
        return flag;
        Exception exception;
        exception;
        throw exception;
    }

    public final void openDriver(SurfaceHolder surfaceholder)
    {
        this;
        JVM INSTR monitorenter ;
        Camera camera = d;
        if (camera != null)
        {
            break MISSING_BLOCK_LABEL_52;
        }
        camera = ((OpenCameraInterface)(new OpenCameraManager()).build()).open();
        if (camera != null)
        {
            break MISSING_BLOCK_LABEL_47;
        }
        throw new IOException();
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
        d = camera;
        Camera camera1 = camera;
        android.hardware.Camera.Parameters parameters;
        camera1.setPreviewDisplay(surfaceholder);
        if (!h)
        {
            h = true;
            c.a(camera1);
            if (j > 0 && k > 0)
            {
                setManualFramingRect(j, k);
                j = 0;
                k = 0;
            }
        }
        parameters = camera1.getParameters();
        if (parameters != null) goto _L2; else goto _L1
_L1:
        String s = null;
_L3:
        c.a(camera1, false);
_L5:
        this;
        JVM INSTR monitorexit ;
        return;
_L2:
        s = parameters.flatten();
          goto _L3
        RuntimeException runtimeexception;
        runtimeexception;
        FkLogger.warn(a, "Camera rejected parameters. Setting only minimal safe-mode parameters");
        FkLogger.info(a, (new StringBuilder("Resetting to saved camera params: ")).append(s).toString());
        if (s == null) goto _L5; else goto _L4
_L4:
        android.hardware.Camera.Parameters parameters1;
        parameters1 = camera1.getParameters();
        parameters1.unflatten(s);
        camera1.setParameters(parameters1);
        c.a(camera1, true);
          goto _L5
        RuntimeException runtimeexception1;
        runtimeexception1;
        FkLogger.warn(a, "Camera rejected even safe-mode parameters! No configuration");
          goto _L5
    }

    public final void requestPreviewFrame(Handler handler, int i1)
    {
        this;
        JVM INSTR monitorenter ;
        Camera camera = d;
        if (camera == null)
        {
            break MISSING_BLOCK_LABEL_38;
        }
        if (i)
        {
            l.a(handler, i1);
            camera.setOneShotPreviewCallback(l);
        }
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public final void setManualFramingRect(int i1, int j1)
    {
        this;
        JVM INSTR monitorenter ;
        if (!h) goto _L2; else goto _L1
_L1:
        Point point = c.b();
        if (i1 > point.x)
        {
            i1 = point.x;
        }
        if (j1 > point.y)
        {
            j1 = point.y;
        }
        int k1 = (point.x - i1) / 2;
        int l1 = (point.y - j1) / 2;
        f = new Rect(k1, l1, k1 + i1, l1 + j1);
        g = null;
_L4:
        this;
        JVM INSTR monitorexit ;
        return;
_L2:
        j = i1;
        k = j1;
        if (true) goto _L4; else goto _L3
_L3:
        Exception exception;
        exception;
        throw exception;
    }

    public final void setTorch(boolean flag)
    {
        this;
        JVM INSTR monitorenter ;
        Camera camera;
        c;
        camera = d;
        boolean flag1 = false;
        if (camera == null) goto _L2; else goto _L1
_L1:
        android.hardware.Camera.Parameters parameters = camera.getParameters();
        flag1 = false;
        if (parameters == null) goto _L2; else goto _L3
_L3:
        String s = camera.getParameters().getFlashMode();
        flag1 = false;
        if (s == null) goto _L2; else goto _L4
_L4:
        if ("on".equals(s)) goto _L6; else goto _L5
_L5:
        boolean flag2 = "torch".equals(s);
        flag1 = false;
        if (!flag2) goto _L2; else goto _L6
_L2:
        if (flag == flag1)
        {
            break MISSING_BLOCK_LABEL_137;
        }
        if (d != null)
        {
            if (e != null)
            {
                e.b();
            }
            c.b(d, flag);
            if (e != null)
            {
                e.a();
            }
        }
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
_L6:
        flag1 = true;
        if (true) goto _L2; else goto _L7
_L7:
    }

    public final void startPreview()
    {
        this;
        JVM INSTR monitorenter ;
        Camera camera = d;
        if (camera == null)
        {
            break MISSING_BLOCK_LABEL_46;
        }
        if (!i)
        {
            camera.startPreview();
            i = true;
            e = new a(b, d);
        }
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public final void stopPreview()
    {
        this;
        JVM INSTR monitorenter ;
        if (e != null)
        {
            e.b();
            e = null;
        }
        if (d != null && i)
        {
            d.stopPreview();
            l.a(null, 0);
            i = false;
        }
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

}
