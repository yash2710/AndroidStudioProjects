// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.volley.request.pushnotification.params;

import com.flipkart.android.config.FlipkartDeviceInfoProvider;
import com.flipkart.android.utils.StringUtils;
import java.net.URLEncoder;

public class NotificationParams
{

    private String a;
    private String b;

    public NotificationParams(String s, String s1)
    {
        a = s;
        b = s1;
    }

    public byte[] generateToByteArray()
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append(getString("state", b, false));
        stringbuilder.append((new StringBuilder("&")).append(getString("optIn", "true", false)).toString());
        stringbuilder.append((new StringBuilder("&")).append(getString("appType", "Retail", false)).toString());
        stringbuilder.append((new StringBuilder("&")).append(getString("appVersion", Integer.toString(FlipkartDeviceInfoProvider.getAppVersionNumber()), false)).toString());
        stringbuilder.append((new StringBuilder("&")).append(getString("appPlatform", "android", false)).toString());
        stringbuilder.append((new StringBuilder("&")).append(getString("deviceId", FlipkartDeviceInfoProvider.getDeviceId(), false)).toString());
        stringbuilder.append((new StringBuilder("&")).append(getString("rawDeviceId", a, false)).toString());
        stringbuilder.append((new StringBuilder("&")).append(getString("osName", FlipkartDeviceInfoProvider.getOsName(), true)).toString());
        stringbuilder.append((new StringBuilder("&")).append(getString("osVersion", FlipkartDeviceInfoProvider.getOsVersion(), true)).toString());
        stringbuilder.append((new StringBuilder("&")).append(getString("deviceManufacturer", FlipkartDeviceInfoProvider.getManufacturer(), true)).toString());
        stringbuilder.append((new StringBuilder("&")).append(getString("deviceModel", FlipkartDeviceInfoProvider.getModel(), true)).toString());
        String s = stringbuilder.toString();
        if (!StringUtils.isNullOrEmpty(s))
        {
            return s.getBytes();
        } else
        {
            return null;
        }
    }

    public String getString(String s, String s1, boolean flag)
    {
        if (StringUtils.isNullOrEmpty(s))
        {
            return "";
        }
        if (StringUtils.isNullOrEmpty(s1))
        {
            s1 = "";
        }
        if (flag)
        {
            return (new StringBuilder()).append(s).append("=").append(s1).toString();
        }
        String s2;
        try
        {
            s2 = (new StringBuilder()).append(s).append("=").append(URLEncoder.encode(s1, "UTF-8")).toString();
        }
        catch (Exception exception)
        {
            return "";
        }
        return s2;
    }
}
