// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.config;


public final class  extends Enum
{

    public static final a DEFAULT;
    public static final a PREBURN;
    public static final a SELFHOST;
    private static final a a[];

    public static  valueOf(String s)
    {
        return ()Enum.valueOf(com/flipkart/android/config/FlipkartPropertiesReader$ConfigOEM, s);
    }

    public static [] values()
    {
        return ([])a.clone();
    }

    static 
    {
        DEFAULT = new <init>("DEFAULT", 0);
        PREBURN = new <init>("PREBURN", 1);
        SELFHOST = new <init>("SELFHOST", 2);
        M_3B_.clone aclone[] = new <init>[3];
        aclone[0] = DEFAULT;
        aclone[1] = PREBURN;
        aclone[2] = SELFHOST;
        a = aclone;
    }

    private (String s, int i)
    {
        super(s, i);
    }
}
