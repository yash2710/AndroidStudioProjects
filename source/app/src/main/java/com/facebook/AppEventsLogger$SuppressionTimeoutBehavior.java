// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.facebook;


final class  extends Enum
{

    private static final .VALUES $VALUES[];
    public static final .VALUES RESET_TIMEOUT_WHEN_LOG_ATTEMPTED;
    public static final .VALUES RESET_TIMEOUT_WHEN_LOG_SUCCESSFUL;

    public static  valueOf(String s)
    {
        return ()Enum.valueOf(com/facebook/AppEventsLogger$SuppressionTimeoutBehavior, s);
    }

    public static [] values()
    {
        return ([])$VALUES.clone();
    }

    static 
    {
        RESET_TIMEOUT_WHEN_LOG_SUCCESSFUL = new <init>("RESET_TIMEOUT_WHEN_LOG_SUCCESSFUL", 0);
        RESET_TIMEOUT_WHEN_LOG_ATTEMPTED = new <init>("RESET_TIMEOUT_WHEN_LOG_ATTEMPTED", 1);
        r_3B_.clone aclone[] = new <init>[2];
        aclone[0] = RESET_TIMEOUT_WHEN_LOG_SUCCESSFUL;
        aclone[1] = RESET_TIMEOUT_WHEN_LOG_ATTEMPTED;
        $VALUES = aclone;
    }

    private (String s, int i)
    {
        super(s, i);
    }
}
