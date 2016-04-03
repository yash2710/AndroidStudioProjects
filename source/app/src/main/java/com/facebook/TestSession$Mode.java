// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.facebook;


final class  extends Enum
{

    private static final .VALUES $VALUES[];
    public static final .VALUES PRIVATE;
    public static final .VALUES SHARED;

    public static  valueOf(String s)
    {
        return ()Enum.valueOf(com/facebook/TestSession$Mode, s);
    }

    public static [] values()
    {
        return ([])$VALUES.clone();
    }

    static 
    {
        PRIVATE = new <init>("PRIVATE", 0);
        SHARED = new <init>("SHARED", 1);
        e_3B_.clone aclone[] = new <init>[2];
        aclone[0] = PRIVATE;
        aclone[1] = SHARED;
        $VALUES = aclone;
    }

    private (String s, int i)
    {
        super(s, i);
    }
}
