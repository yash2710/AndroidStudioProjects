// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.customviews;


public final class  extends Enum
{

    public static final a BOTH;
    public static final a END;
    public static final a START;
    private static final a a[];

    public static  valueOf(String s)
    {
        return ()Enum.valueOf(com/flipkart/android/customviews/EnhancedListView$SwipeDirection, s);
    }

    public static [] values()
    {
        return ([])a.clone();
    }

    static 
    {
        BOTH = new <init>("BOTH", 0);
        START = new <init>("START", 1);
        END = new <init>("END", 2);
        n_3B_.clone aclone[] = new <init>[3];
        aclone[0] = BOTH;
        aclone[1] = START;
        aclone[2] = END;
        a = aclone;
    }

    private (String s, int i)
    {
        super(s, i);
    }
}
