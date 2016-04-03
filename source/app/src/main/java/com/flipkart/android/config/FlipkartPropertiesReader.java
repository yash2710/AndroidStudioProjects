// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.config;

import android.content.Context;
import android.content.res.Resources;
import com.flipkart.android.log.ApiLogger;
import com.flipkart.android.log.LoggerTag;
import com.flipkart.logging.FkLogger;
import java.util.Properties;

public class FlipkartPropertiesReader
{

    private static final String a = com/flipkart/android/config/FlipkartPropertiesReader.getSimpleName();
    private String b;
    private String c;

    public FlipkartPropertiesReader()
    {
        b = "Environment";
        c = "Config.OEM";
    }

    public AppEnvironment getAppEnvironment(Context context)
    {
        AppEnvironment appenvironment = AppEnvironment.DEVELOPMENT;
        AppEnvironment appenvironment1;
        try
        {
            Properties properties = new Properties();
            properties.load(context.getResources().openRawResource(0x7f060001));
            appenvironment1 = AppEnvironment.valueOf(properties.getProperty(b));
        }
        catch (Exception exception)
        {
            FkLogger.debug(a, "Unable to read flipkart.properties file");
            ApiLogger.logException(LoggerTag.Init, "Not able to read flipkart.properties", exception, null);
            return appenvironment;
        }
        return appenvironment1;
    }

    public ConfigOEM getAppOEM(Context context)
    {
        ConfigOEM configoem = ConfigOEM.DEFAULT;
        ConfigOEM configoem1;
        try
        {
            Properties properties = new Properties();
            properties.load(context.getResources().openRawResource(0x7f060001));
            configoem1 = ConfigOEM.valueOf(properties.getProperty(c));
        }
        catch (Exception exception)
        {
            FkLogger.debug(a, "Unable to read flipkart.properties file");
            ApiLogger.logException(LoggerTag.Init, "Not able to read flipkart.properties", exception, null);
            return configoem;
        }
        return configoem1;
    }


    private class AppEnvironment extends Enum
    {

        public static final AppEnvironment DEVELOPMENT;
        public static final AppEnvironment INTERNAL;
        public static final AppEnvironment RELEASE;
        public static final AppEnvironment TESTING;
        private static final AppEnvironment a[];

        public static AppEnvironment valueOf(String s)
        {
            return (AppEnvironment)Enum.valueOf(com/flipkart/android/config/FlipkartPropertiesReader$AppEnvironment, s);
        }

        public static AppEnvironment[] values()
        {
            return (AppEnvironment[])a.clone();
        }

        static 
        {
            DEVELOPMENT = new AppEnvironment("DEVELOPMENT", 0);
            INTERNAL = new AppEnvironment("INTERNAL", 1);
            RELEASE = new AppEnvironment("RELEASE", 2);
            TESTING = new AppEnvironment("TESTING", 3);
            AppEnvironment aappenvironment[] = new AppEnvironment[4];
            aappenvironment[0] = DEVELOPMENT;
            aappenvironment[1] = INTERNAL;
            aappenvironment[2] = RELEASE;
            aappenvironment[3] = TESTING;
            a = aappenvironment;
        }

        private AppEnvironment(String s, int i)
        {
            super(s, i);
        }
    }


    private class ConfigOEM extends Enum
    {

        public static final ConfigOEM DEFAULT;
        public static final ConfigOEM PREBURN;
        public static final ConfigOEM SELFHOST;
        private static final ConfigOEM a[];

        public static ConfigOEM valueOf(String s)
        {
            return (ConfigOEM)Enum.valueOf(com/flipkart/android/config/FlipkartPropertiesReader$ConfigOEM, s);
        }

        public static ConfigOEM[] values()
        {
            return (ConfigOEM[])a.clone();
        }

        static 
        {
            DEFAULT = new ConfigOEM("DEFAULT", 0);
            PREBURN = new ConfigOEM("PREBURN", 1);
            SELFHOST = new ConfigOEM("SELFHOST", 2);
            ConfigOEM aconfigoem[] = new ConfigOEM[3];
            aconfigoem[0] = DEFAULT;
            aconfigoem[1] = PREBURN;
            aconfigoem[2] = SELFHOST;
            a = aconfigoem;
        }

        private ConfigOEM(String s, int i)
        {
            super(s, i);
        }
    }

}
