// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.log;

import com.crashlytics.android.Crashlytics;
import com.flipkart.android.utils.AppConfigUtils;

// Referenced classes of package com.flipkart.android.log:
//            LoggerStack

public class CrashLoggerUtils
{

    private static LoggerStack a = null;

    public CrashLoggerUtils()
    {
    }

    public static LoggerStack getLoggerStack()
    {
        return a;
    }

    public static void init()
    {
        if (a == null)
        {
            a = new LoggerStack(15);
        }
    }

    public static void pushAndUpdate(String s)
    {
        if (AppConfigUtils.getInstance().isEnableCrashlyticsBreadCrumbs())
        {
            a.add(s);
            Crashlytics.setString("last 15 user activity", a.toString());
        }
    }

}
