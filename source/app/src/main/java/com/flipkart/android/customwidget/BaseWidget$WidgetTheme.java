// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.customwidget;


public final class  extends Enum
{

    private static final .VALUES $VALUES[];
    public static final .VALUES dark;
    public static final .VALUES light;

    public static  valueOf(String s)
    {
        return ()Enum.valueOf(com/flipkart/android/customwidget/BaseWidget$WidgetTheme, s);
    }

    public static [] values()
    {
        return ([])$VALUES.clone();
    }

    static 
    {
        dark = new <init>("dark", 0);
        light = new <init>("light", 1);
        e_3B_.clone aclone[] = new <init>[2];
        aclone[0] = dark;
        aclone[1] = light;
        $VALUES = aclone;
    }

    private (String s, int i)
    {
        super(s, i);
    }
}
