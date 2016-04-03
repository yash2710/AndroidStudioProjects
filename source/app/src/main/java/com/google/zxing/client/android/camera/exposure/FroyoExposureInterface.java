// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing.client.android.camera.exposure;

import com.flipkart.logging.FkLogger;

// Referenced classes of package com.google.zxing.client.android.camera.exposure:
//            ExposureInterface

public final class FroyoExposureInterface
    implements ExposureInterface
{

    private static final String a = com/google/zxing/client/android/camera/exposure/FroyoExposureInterface.getSimpleName();

    public FroyoExposureInterface()
    {
    }

    public final void setExposure(android.hardware.Camera.Parameters parameters, boolean flag)
    {
        int i = parameters.getMinExposureCompensation();
        int j = parameters.getMaxExposureCompensation();
        if (i != 0 || j != 0)
        {
            float f = parameters.getExposureCompensationStep();
            int k;
            if (flag)
            {
                k = Math.max((int)(0.0F / f), i);
            } else
            {
                k = Math.min((int)(1.5F / f), j);
            }
            FkLogger.info(a, (new StringBuilder("Setting exposure compensation to ")).append(k).append(" / ").append(f * (float)k).toString());
            parameters.setExposureCompensation(k);
            return;
        } else
        {
            FkLogger.info(a, "Camera does not support exposure compensation");
            return;
        }
    }

}
