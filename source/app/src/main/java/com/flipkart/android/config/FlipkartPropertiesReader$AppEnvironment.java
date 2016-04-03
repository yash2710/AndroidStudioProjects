// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.config;


public final class I extends Enum
{

    public static final a DEVELOPMENT;
    public static final a INTERNAL;
    public static final a RELEASE;
    public static final a TESTING;
    private static final a a[];

    public static I valueOf(String s)
    {
        return (I)Enum.valueOf(com/flipkart/android/config/FlipkartPropertiesReader$AppEnvironment, s);
    }

    public static I[] values()
    {
        return (I[])a.clone();
    }

    static 
    {
        DEVELOPMENT = new <init>("DEVELOPMENT", 0);
        INTERNAL = new <init>("INTERNAL", 1);
        RELEASE = new <init>("RELEASE", 2);
        TESTING = new <init>("TESTING", 3);
        t_3B_.clone aclone[] = new <init>[4];
        aclone[0] = DEVELOPMENT;
        aclone[1] = INTERNAL;
        aclone[2] = RELEASE;
        aclone[3] = TESTING;
        a = aclone;
    }

    private I(String s, int i)
    {
        super(s, i);
    }
}
