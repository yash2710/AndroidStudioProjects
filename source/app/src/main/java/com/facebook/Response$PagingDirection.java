// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.facebook;


public final class  extends Enum
{

    private static final .VALUES $VALUES[];
    public static final .VALUES NEXT;
    public static final .VALUES PREVIOUS;

    public static  valueOf(String s)
    {
        return ()Enum.valueOf(com/facebook/Response$PagingDirection, s);
    }

    public static [] values()
    {
        return ([])$VALUES.clone();
    }

    static 
    {
        NEXT = new <init>("NEXT", 0);
        PREVIOUS = new <init>("PREVIOUS", 1);
        n_3B_.clone aclone[] = new <init>[2];
        aclone[0] = NEXT;
        aclone[1] = PREVIOUS;
        $VALUES = aclone;
    }

    private (String s, int i)
    {
        super(s, i);
    }
}
