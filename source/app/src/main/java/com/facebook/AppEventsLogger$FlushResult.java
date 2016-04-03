// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.facebook;


final class  extends Enum
{

    private static final .VALUES $VALUES[];
    public static final .VALUES NO_CONNECTIVITY;
    public static final .VALUES SERVER_ERROR;
    public static final .VALUES SUCCESS;
    public static final .VALUES UNKNOWN_ERROR;

    public static  valueOf(String s)
    {
        return ()Enum.valueOf(com/facebook/AppEventsLogger$FlushResult, s);
    }

    public static [] values()
    {
        return ([])$VALUES.clone();
    }

    static 
    {
        SUCCESS = new <init>("SUCCESS", 0);
        SERVER_ERROR = new <init>("SERVER_ERROR", 1);
        NO_CONNECTIVITY = new <init>("NO_CONNECTIVITY", 2);
        UNKNOWN_ERROR = new <init>("UNKNOWN_ERROR", 3);
        t_3B_.clone aclone[] = new <init>[4];
        aclone[0] = SUCCESS;
        aclone[1] = SERVER_ERROR;
        aclone[2] = NO_CONNECTIVITY;
        aclone[3] = UNKNOWN_ERROR;
        $VALUES = aclone;
    }

    private (String s, int i)
    {
        super(s, i);
    }
}
