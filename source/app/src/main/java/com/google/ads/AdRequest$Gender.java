// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.ads;


public final class  extends Enum
{

    public static final a FEMALE;
    public static final a MALE;
    public static final a UNKNOWN;
    private static final a a[];

    public static  valueOf(String s)
    {
        return ()Enum.valueOf(com/google/ads/AdRequest$Gender, s);
    }

    public static [] values()
    {
        return ([])a.clone();
    }

    static 
    {
        UNKNOWN = new <init>("UNKNOWN", 0);
        MALE = new <init>("MALE", 1);
        FEMALE = new <init>("FEMALE", 2);
        r_3B_.clone aclone[] = new <init>[3];
        aclone[0] = UNKNOWN;
        aclone[1] = MALE;
        aclone[2] = FEMALE;
        a = aclone;
    }

    private (String s, int i)
    {
        super(s, i);
    }
}
