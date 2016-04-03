// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.logging;

import android.util.Log;

public class FkLogger
{

    public static final String DEFAULT_TAG = "Retail";
    public static final boolean JVM = false;
    public static final int LOG_LEVEL = 2;

    public FkLogger()
    {
    }

    public static int debug(String s)
    {
        return debug("Retail", s);
    }

    public static int debug(String s, String s1)
    {
        return Log.d(s, s1);
    }

    public static int error(String s, String s1)
    {
        return Log.e(s, s1);
    }

    public static int error(String s, String s1, Throwable throwable)
    {
        return Log.e(s, s1, throwable);
    }

    public static void getCallingMethodParams(int i)
    {
        StackTraceElement astacktraceelement[] = Thread.currentThread().getStackTrace();
        astacktraceelement[i + 4].getMethodName();
        astacktraceelement[i + 4].getClassName();
    }

    public static int info(String s)
    {
        return info("Retail", s);
    }

    public static int info(String s, String s1)
    {
        return Log.i(s, s1);
    }

    public static int methodEnd()
    {
        return 0;
    }

    public static int methodEnd(String s)
    {
        return 0;
    }

    public static int methodStart()
    {
        return 0;
    }

    public static int methodStart(String s)
    {
        return 0;
    }

    public static int monitor(boolean flag, String s, int i)
    {
        return 0;
    }

    public static void printStackTrace(Throwable throwable)
    {
        throwable.printStackTrace();
    }

    public static int startMonitor(String s)
    {
        return 0;
    }

    public static int stopMonitor(String s)
    {
        return 0;
    }

    public static int verbose(String s)
    {
        return verbose("Retail", s);
    }

    public static int verbose(String s, String s1)
    {
        return Log.v(s, s1);
    }

    public static int warn(String s)
    {
        return warn("Retail", s);
    }

    public static int warn(String s, String s1)
    {
        return Log.w(s, s1);
    }

    public static int warn(String s, String s1, Throwable throwable)
    {
        return Log.w(s, s1, throwable);
    }

    public static int warn(String s, Throwable throwable)
    {
        return warn("Retail", s, throwable);
    }
}
