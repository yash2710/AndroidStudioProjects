// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.log;


public final class LoggerTag extends Enum
{

    public static final LoggerTag ApiCall;
    public static final LoggerTag Database;
    public static final LoggerTag GsonException;
    public static final LoggerTag Init;
    public static final LoggerTag Perf;
    public static final LoggerTag Session;
    public static final LoggerTag Webview;
    public static final LoggerTag Widget;
    private static final LoggerTag a[];

    private LoggerTag(String s, int i)
    {
        super(s, i);
    }

    public static LoggerTag valueOf(String s)
    {
        return (LoggerTag)Enum.valueOf(com/flipkart/android/log/LoggerTag, s);
    }

    public static LoggerTag[] values()
    {
        return (LoggerTag[])a.clone();
    }

    static 
    {
        Webview = new LoggerTag("Webview", 0);
        Init = new LoggerTag("Init", 1);
        ApiCall = new LoggerTag("ApiCall", 2);
        Database = new LoggerTag("Database", 3);
        Session = new LoggerTag("Session", 4);
        Widget = new LoggerTag("Widget", 5);
        Perf = new LoggerTag("Perf", 6);
        GsonException = new LoggerTag("GsonException", 7);
        LoggerTag aloggertag[] = new LoggerTag[8];
        aloggertag[0] = Webview;
        aloggertag[1] = Init;
        aloggertag[2] = ApiCall;
        aloggertag[3] = Database;
        aloggertag[4] = Session;
        aloggertag[5] = Widget;
        aloggertag[6] = Perf;
        aloggertag[7] = GsonException;
        a = aloggertag;
    }
}
