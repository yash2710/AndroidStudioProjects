// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.facebook;


final class  extends Enum
{

    private static final .VALUES $VALUES[];
    public static final .VALUES CLOSED_CATEGORY;
    public static final .VALUES CREATED_CATEGORY;
    public static final .VALUES OPENED_CATEGORY;

    public static  valueOf(String s)
    {
        return ()Enum.valueOf(com/facebook/SessionState$Category, s);
    }

    public static [] values()
    {
        return ([])$VALUES.clone();
    }

    static 
    {
        CREATED_CATEGORY = new <init>("CREATED_CATEGORY", 0);
        OPENED_CATEGORY = new <init>("OPENED_CATEGORY", 1);
        CLOSED_CATEGORY = new <init>("CLOSED_CATEGORY", 2);
        y_3B_.clone aclone[] = new <init>[3];
        aclone[0] = CREATED_CATEGORY;
        aclone[1] = OPENED_CATEGORY;
        aclone[2] = CLOSED_CATEGORY;
        $VALUES = aclone;
    }

    private (String s, int i)
    {
        super(s, i);
    }
}
