// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing.client.android.camera.open;

import android.hardware.Camera;
import com.flipkart.logging.FkLogger;

// Referenced classes of package com.google.zxing.client.android.camera.open:
//            OpenCameraInterface

public final class GingerbreadOpenCameraInterface
    implements OpenCameraInterface
{

    public GingerbreadOpenCameraInterface()
    {
    }

    public final Camera open()
    {
        int i = Camera.getNumberOfCameras();
        if (i == 0)
        {
            FkLogger.warn("GingerbreadOpenCamera", "No cameras!");
            return null;
        }
        int j = 0;
        do
        {
            if (j >= i)
            {
                break;
            }
            android.hardware.Camera.CameraInfo camerainfo = new android.hardware.Camera.CameraInfo();
            Camera.getCameraInfo(j, camerainfo);
            if (camerainfo.facing == 0)
            {
                break;
            }
            j++;
        } while (true);
        if (j < i)
        {
            FkLogger.info("GingerbreadOpenCamera", (new StringBuilder("Opening camera #")).append(j).toString());
            return Camera.open(j);
        } else
        {
            FkLogger.info("GingerbreadOpenCamera", "No camera facing back; returning camera #0");
            return Camera.open(0);
        }
    }
}
