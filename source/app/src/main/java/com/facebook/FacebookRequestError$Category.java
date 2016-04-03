// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.facebook;


public final class  extends Enum
{

    private static final .VALUES $VALUES[];
    public static final .VALUES AUTHENTICATION_REOPEN_SESSION;
    public static final .VALUES AUTHENTICATION_RETRY;
    public static final .VALUES BAD_REQUEST;
    public static final .VALUES CLIENT;
    public static final .VALUES OTHER;
    public static final .VALUES PERMISSION;
    public static final .VALUES SERVER;
    public static final .VALUES THROTTLING;

    public static  valueOf(String s)
    {
        return ()Enum.valueOf(com/facebook/FacebookRequestError$Category, s);
    }

    public static [] values()
    {
        return ([])$VALUES.clone();
    }

    static 
    {
        AUTHENTICATION_RETRY = new <init>("AUTHENTICATION_RETRY", 0);
        AUTHENTICATION_REOPEN_SESSION = new <init>("AUTHENTICATION_REOPEN_SESSION", 1);
        PERMISSION = new <init>("PERMISSION", 2);
        SERVER = new <init>("SERVER", 3);
        THROTTLING = new <init>("THROTTLING", 4);
        OTHER = new <init>("OTHER", 5);
        BAD_REQUEST = new <init>("BAD_REQUEST", 6);
        CLIENT = new <init>("CLIENT", 7);
        y_3B_.clone aclone[] = new <init>[8];
        aclone[0] = AUTHENTICATION_RETRY;
        aclone[1] = AUTHENTICATION_REOPEN_SESSION;
        aclone[2] = PERMISSION;
        aclone[3] = SERVER;
        aclone[4] = THROTTLING;
        aclone[5] = OTHER;
        aclone[6] = BAD_REQUEST;
        aclone[7] = CLIENT;
        $VALUES = aclone;
    }

    private (String s, int i)
    {
        super(s, i);
    }
}
