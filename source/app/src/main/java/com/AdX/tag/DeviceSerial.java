// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.AdX.tag;

import android.os.Build;

// Referenced classes of package com.AdX.tag:
//            k

public class DeviceSerial
{

    private DeviceSerial()
    {
    }

    DeviceSerial(byte byte0)
    {
        this();
    }

    public static String getDeviceSerial()
    {
        return Build.SERIAL;
    }

    public static DeviceSerial getInstance()
    {
        return k.a();
    }
}
