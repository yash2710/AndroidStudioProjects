// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.config;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

// Referenced classes of package com.flipkart.android.config:
//            FlipkartPropertiesReader, c

public class FlipkartConfigurationProvider
{

    public FlipkartConfigurationProvider()
    {
    }

    public static String getAppVersionCode(Context context)
    {
        return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
    }

    public static int getAppVersionNumber(Context context)
    {
        int i;
        try
        {
            i = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        }
        catch (Exception exception)
        {
            return 0;
        }
        return i;
    }

    public static String getCrittercismKey(Context context)
    {
        FlipkartPropertiesReader.AppEnvironment appenvironment = (new FlipkartPropertiesReader()).getAppEnvironment(context);
        switch (c.a[appenvironment.ordinal()])
        {
        default:
            return "";

        case 1: // '\001'
            return "51e8f419d0d8f73146000006";

        case 2: // '\002'
            return "5211dd1246b7c223ce000008";

        case 3: // '\003'
            return "5211d86be432f51add000007";

        case 4: // '\004'
            return "531e994a8633a41394000004";
        }
    }

    public static FlipkartPropertiesReader.AppEnvironment getEnvironment(Context context)
    {
        return (new FlipkartPropertiesReader()).getAppEnvironment(context);
    }
}
