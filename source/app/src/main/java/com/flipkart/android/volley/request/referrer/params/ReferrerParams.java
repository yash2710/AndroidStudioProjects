// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.volley.request.referrer.params;

import com.flipkart.android.config.FlipkartDeviceInfoProvider;

public class ReferrerParams
{

    private String a;
    private String b;
    private long c;

    public ReferrerParams(String s, String s1, long l)
    {
        a = s;
        b = s1;
        c = l;
    }

    public byte[] getBytes()
    {
        return (new StringBuilder("value=")).append(a).append("&deviceId=").append(FlipkartDeviceInfoProvider.getDeviceId()).append("&rawDeviceId=").append(FlipkartDeviceInfoProvider.getRawDeviceId()).append("&channel=").append(b).append("&firstLaunch=").append(c).toString().getBytes();
    }
}
