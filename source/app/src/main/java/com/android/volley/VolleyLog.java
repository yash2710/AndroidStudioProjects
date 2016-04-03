// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.volley;

import android.util.Log;

public class VolleyLog
{

    public static boolean DEBUG = Log.isLoggable("Volley", 2);
    public static String TAG = "Volley";

    public VolleyLog()
    {
    }

    public static transient void d(String s, Object aobj[])
    {
    }

    public static transient void e(String s, Object aobj[])
    {
    }

    public static transient void e(Throwable throwable, String s, Object aobj[])
    {
    }

    public static void setTag(String s)
    {
        d("Changing log tag to %s", new Object[] {
            s
        });
        TAG = s;
        DEBUG = Log.isLoggable(s, 2);
    }

    public static transient void v(String s, Object aobj[])
    {
        boolean _tmp = DEBUG;
    }

    public static transient void wtf(String s, Object aobj[])
    {
    }

    public static transient void wtf(Throwable throwable, String s, Object aobj[])
    {
    }

}
