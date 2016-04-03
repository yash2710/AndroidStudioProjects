// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.j256.ormlite.logger;


public final class level extends Enum
{

    private static final .VALUES $VALUES[];
    public static final .VALUES DEBUG;
    public static final .VALUES ERROR;
    public static final .VALUES FATAL;
    public static final .VALUES INFO;
    public static final .VALUES TRACE;
    public static final .VALUES WARNING;
    private int level;

    public static level valueOf(String s)
    {
        return (level)Enum.valueOf(com/j256/ormlite/logger/Log$Level, s);
    }

    public static level[] values()
    {
        return (level[])$VALUES.clone();
    }

    public final boolean isEnabled(l_3B_.clone clone)
    {
        return level <= clone.level;
    }

    static 
    {
        TRACE = new <init>("TRACE", 0, 1);
        DEBUG = new <init>("DEBUG", 1, 2);
        INFO = new <init>("INFO", 2, 3);
        WARNING = new <init>("WARNING", 3, 4);
        ERROR = new <init>("ERROR", 4, 5);
        FATAL = new <init>("FATAL", 5, 6);
        level alevel[] = new <init>[6];
        alevel[0] = TRACE;
        alevel[1] = DEBUG;
        alevel[2] = INFO;
        alevel[3] = WARNING;
        alevel[4] = ERROR;
        alevel[5] = FATAL;
        $VALUES = alevel;
    }

    private (String s, int i, int j)
    {
        super(s, i);
        level = j;
    }
}
