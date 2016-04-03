// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.config;

import android.content.Context;
import android.os.Build;
import android.os.Environment;
import com.flipkart.android.init.FlipkartApplication;
import com.flipkart.android.utils.HashUtils;
import com.flipkart.android.utils.StringUtils;
import com.flipkart.logging.FkLogger;
import java.lang.reflect.Field;

// Referenced classes of package com.flipkart.android.config:
//            FlipkartPreferenceManager, FlipkartPropertiesReader

public class FlipkartDeviceInfoProvider
{

    private static String a = null;

    public FlipkartDeviceInfoProvider()
    {
    }

    public static int getAndroidSDKVersion()
    {
        return android.os.Build.VERSION.SDK_INT;
    }

    public static String getAppVersionCode()
    {
        return FlipkartPreferenceManager.instance().getAppVersionCode();
    }

    public static int getAppVersionNumber()
    {
        return FlipkartPreferenceManager.instance().getAppVersionNumber();
    }

    public static String getDeviceId()
    {
        if (a == null)
        {
            try
            {
                Context context = FlipkartApplication.getAppContext();
                context.getSystemService("phone");
                a = HashUtils.md5((new StringBuilder()).append(android.provider.Settings.Secure.getString(context.getContentResolver(), "android_id")).toString());
            }
            catch (Exception exception) { }
        }
        return a;
    }

    public static String getMakeAndModel()
    {
        String s = getManufacturer();
        String s1 = getModel();
        StringBuilder stringbuilder = new StringBuilder();
        if (!StringUtils.isNullOrEmpty(s))
        {
            stringbuilder.append(s);
        }
        if (!StringUtils.isNullOrEmpty(s1))
        {
            if (stringbuilder.length() > 0)
            {
                stringbuilder.append(":");
            }
            stringbuilder.append(s1);
        }
        if (stringbuilder.length() > 0)
        {
            return stringbuilder.toString();
        } else
        {
            return "";
        }
    }

    public static String getMakeModelAndPreburn()
    {
        StringBuilder stringbuilder;
        stringbuilder = new StringBuilder();
        stringbuilder.append(getMakeAndModel());
        if (stringbuilder.length() <= 0) goto _L2; else goto _L1
_L1:
        if (!FlipkartPreferenceManager.instance().isPreburnApp().booleanValue()) goto _L4; else goto _L3
_L3:
        stringbuilder.append(":Preburn");
_L5:
        return stringbuilder.toString();
_L4:
        try
        {
            if ((new FlipkartPropertiesReader()).getAppOEM(FlipkartApplication.getAppContext()) == FlipkartPropertiesReader.ConfigOEM.PREBURN)
            {
                stringbuilder.append(":Preburn");
            }
        }
        catch (Exception exception) { }
        if (true) goto _L5; else goto _L2
_L2:
        return "";
    }

    public static String getManufacturer()
    {
        return Build.MANUFACTURER;
    }

    public static String getModel()
    {
        return Build.MODEL;
    }

    public static String getOsName()
    {
        Field afield[];
        int i;
        int j;
        afield = android/os/Build$VERSION_CODES.getFields();
        i = afield.length;
        j = 0;
_L5:
        if (j >= i) goto _L2; else goto _L1
_L1:
        Field field;
        String s;
        int k;
        field = afield[j];
        s = field.getName();
        k = -1;
        int l = field.getInt(new Object());
        k = l;
_L4:
        if (k == android.os.Build.VERSION.SDK_INT)
        {
            return s;
        }
        continue; /* Loop/switch isn't completed */
        IllegalArgumentException illegalargumentexception;
        illegalargumentexception;
        FkLogger.printStackTrace(illegalargumentexception);
        continue; /* Loop/switch isn't completed */
        IllegalAccessException illegalaccessexception;
        illegalaccessexception;
        FkLogger.printStackTrace(illegalaccessexception);
        continue; /* Loop/switch isn't completed */
        NullPointerException nullpointerexception;
        nullpointerexception;
        FkLogger.printStackTrace(nullpointerexception);
        if (true) goto _L4; else goto _L3
_L3:
        j++;
          goto _L5
_L2:
        return "";
    }

    public static String getOsVersion()
    {
        return android.os.Build.VERSION.RELEASE;
    }

    public static String getRawDeviceId()
    {
        String s;
        try
        {
            s = android.provider.Settings.Secure.getString(FlipkartApplication.getAppContext().getContentResolver(), "android_id");
        }
        catch (Exception exception)
        {
            return "";
        }
        return s;
    }

    public static String getReadableDeviceId()
    {
        return (new StringBuilder()).append(Build.MANUFACTURER).append(":").append(Build.MODEL).append(" ").append(Build.ID).toString();
    }

    public static Boolean is2dot3Device()
    {
        boolean flag;
        if (getAndroidSDKVersion() < 11)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        return Boolean.valueOf(flag);
    }

    public static boolean isSDCardInstalled()
    {
        return Environment.getExternalStorageState().equals("mounted");
    }

}
