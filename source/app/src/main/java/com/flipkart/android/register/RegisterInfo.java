// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.register;

import com.flipkart.android.config.FlipkartDeviceInfoProvider;
import com.google.mygson.JsonObject;

public class RegisterInfo
{

    private Long a;

    public RegisterInfo(Long long1)
    {
        FlipkartDeviceInfoProvider.getAndroidSDKVersion();
        FlipkartDeviceInfoProvider.getAppVersionNumber();
        FlipkartDeviceInfoProvider.getReadableDeviceId();
        a = long1;
    }

    public String getTimeStamp()
    {
        JsonObject jsonobject = new JsonObject();
        jsonobject.addProperty("timestamp", a);
        return jsonobject.toString();
    }

    public String toPostString()
    {
        return getTimeStamp();
    }
}
