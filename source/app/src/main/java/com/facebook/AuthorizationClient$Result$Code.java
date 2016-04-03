// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.facebook;


final class loggingValue extends Enum
{

    private static final .VALUES $VALUES[];
    public static final .VALUES CANCEL;
    public static final .VALUES ERROR;
    public static final .VALUES SUCCESS;
    private final String loggingValue;

    public static loggingValue valueOf(String s)
    {
        return (loggingValue)Enum.valueOf(com/facebook/AuthorizationClient$Result$Code, s);
    }

    public static loggingValue[] values()
    {
        return (loggingValue[])$VALUES.clone();
    }

    final String getLoggingValue()
    {
        return loggingValue;
    }

    static 
    {
        SUCCESS = new <init>("SUCCESS", 0, "success");
        CANCEL = new <init>("CANCEL", 1, "cancel");
        ERROR = new <init>("ERROR", 2, "error");
        loggingValue aloggingvalue[] = new <init>[3];
        aloggingvalue[0] = SUCCESS;
        aloggingvalue[1] = CANCEL;
        aloggingvalue[2] = ERROR;
        $VALUES = aloggingvalue;
    }

    private (String s, int i, String s1)
    {
        super(s, i);
        loggingValue = s1;
    }
}
