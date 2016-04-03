// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.j256.ormlite.misc;

import com.j256.ormlite.logger.Logger;
import com.j256.ormlite.logger.LoggerFactory;

public class VersionUtils
{

    private static final String CORE_VERSION = "VERSION__4.48__";
    private static String coreVersion = "VERSION__4.48__";
    private static Logger logger;
    private static boolean thrownOnErrors = false;

    private VersionUtils()
    {
    }

    public static final void checkCoreVersusAndroidVersions(String s)
    {
        logVersionWarnings("core", coreVersion, "android", s);
    }

    public static final void checkCoreVersusJdbcVersions(String s)
    {
        logVersionWarnings("core", coreVersion, "jdbc", s);
    }

    public static String getCoreVersion()
    {
        return coreVersion;
    }

    private static Logger getLogger()
    {
        if (logger == null)
        {
            logger = LoggerFactory.getLogger(com/j256/ormlite/misc/VersionUtils);
        }
        return logger;
    }

    private static void logVersionWarnings(String s, String s1, String s2, String s3)
    {
        if (s1 == null)
        {
            if (s3 != null)
            {
                warning(null, "Unknown version", " for {}, version for {} is '{}'", new Object[] {
                    s, s2, s3
                });
            }
        } else
        {
            if (s3 == null)
            {
                warning(null, "Unknown version", " for {}, version for {} is '{}'", new Object[] {
                    s2, s, s1
                });
                return;
            }
            if (!s1.equals(s3))
            {
                warning(null, "Mismatched versions", ": {} is '{}', while {} is '{}'", new Object[] {
                    s, s1, s2, s3
                });
                return;
            }
        }
    }

    static void setThrownOnErrors(boolean flag)
    {
        thrownOnErrors = flag;
    }

    private static void warning(Throwable throwable, String s, String s1, Object aobj[])
    {
        getLogger().warn(throwable, (new StringBuilder()).append(s).append(s1).toString(), aobj);
        if (thrownOnErrors)
        {
            throw new IllegalStateException((new StringBuilder("See error log for details:")).append(s).toString());
        } else
        {
            return;
        }
    }

}
