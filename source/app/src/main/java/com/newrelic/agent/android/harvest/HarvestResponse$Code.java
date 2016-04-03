// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.newrelic.agent.android.harvest;


public final class a extends Enum
{

    public static final b ENTITY_TOO_LARGE;
    public static final b FORBIDDEN;
    public static final b INTERNAL_SERVER_ERROR;
    public static final b INVALID_AGENT_ID;
    public static final b OK;
    public static final b UNAUTHORIZED;
    public static final b UNKNOWN;
    public static final b UNSUPPORTED_MEDIA_TYPE;
    private static final b b[];
    private int a;

    public static a valueOf(String s)
    {
        return (a)Enum.valueOf(com/newrelic/agent/android/harvest/HarvestResponse$Code, s);
    }

    public static a[] values()
    {
        return (a[])b.clone();
    }

    public final int getStatusCode()
    {
        return a;
    }

    public final boolean isError()
    {
        return this != OK;
    }

    public final boolean isOK()
    {
        return !isError();
    }

    static 
    {
        OK = new <init>("OK", 0, 200);
        UNAUTHORIZED = new <init>("UNAUTHORIZED", 1, 401);
        FORBIDDEN = new <init>("FORBIDDEN", 2, 403);
        ENTITY_TOO_LARGE = new <init>("ENTITY_TOO_LARGE", 3, 413);
        INVALID_AGENT_ID = new <init>("INVALID_AGENT_ID", 4, 450);
        UNSUPPORTED_MEDIA_TYPE = new <init>("UNSUPPORTED_MEDIA_TYPE", 5, 415);
        INTERNAL_SERVER_ERROR = new <init>("INTERNAL_SERVER_ERROR", 6, 500);
        UNKNOWN = new <init>("UNKNOWN", 7, -1);
        isError aiserror[] = new <init>[8];
        aiserror[0] = OK;
        aiserror[1] = UNAUTHORIZED;
        aiserror[2] = FORBIDDEN;
        aiserror[3] = ENTITY_TOO_LARGE;
        aiserror[4] = INVALID_AGENT_ID;
        aiserror[5] = UNSUPPORTED_MEDIA_TYPE;
        aiserror[6] = INTERNAL_SERVER_ERROR;
        aiserror[7] = UNKNOWN;
        b = aiserror;
    }

    private (String s, int i, int j)
    {
        super(s, i);
        a = j;
    }
}
