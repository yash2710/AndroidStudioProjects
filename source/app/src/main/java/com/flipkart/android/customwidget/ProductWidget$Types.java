// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.customwidget;


final class  extends Enum
{

    private static final .VALUES $VALUES[];
    public static final .VALUES FreebieOrDiscount;
    public static final .VALUES LimitedStock;
    public static final .VALUES SubTitle;
    public static final .VALUES Title;
    public static final .VALUES UpcomingTitle;

    public static  valueOf(String s)
    {
        return ()Enum.valueOf(com/flipkart/android/customwidget/ProductWidget$Types, s);
    }

    public static [] values()
    {
        return ([])$VALUES.clone();
    }

    static 
    {
        Title = new <init>("Title", 0);
        FreebieOrDiscount = new <init>("FreebieOrDiscount", 1);
        SubTitle = new <init>("SubTitle", 2);
        UpcomingTitle = new <init>("UpcomingTitle", 3);
        LimitedStock = new <init>("LimitedStock", 4);
        s_3B_.clone aclone[] = new <init>[5];
        aclone[0] = Title;
        aclone[1] = FreebieOrDiscount;
        aclone[2] = SubTitle;
        aclone[3] = UpcomingTitle;
        aclone[4] = LimitedStock;
        $VALUES = aclone;
    }

    private (String s, int i)
    {
        super(s, i);
    }
}
