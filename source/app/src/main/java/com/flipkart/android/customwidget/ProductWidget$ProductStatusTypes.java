// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.customwidget;


public final class  extends Enum
{

    private static final .VALUES $VALUES[];
    public static final .VALUES SoldOut;
    public static final .VALUES Upcoming;

    public static  valueOf(String s)
    {
        return ()Enum.valueOf(com/flipkart/android/customwidget/ProductWidget$ProductStatusTypes, s);
    }

    public static [] values()
    {
        return ([])$VALUES.clone();
    }

    static 
    {
        Upcoming = new <init>("Upcoming", 0);
        SoldOut = new <init>("SoldOut", 1);
        s_3B_.clone aclone[] = new <init>[2];
        aclone[0] = Upcoming;
        aclone[1] = SoldOut;
        $VALUES = aclone;
    }

    private (String s, int i)
    {
        super(s, i);
    }
}
