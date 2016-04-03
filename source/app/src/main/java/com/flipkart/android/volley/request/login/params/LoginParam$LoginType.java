// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.volley.request.login.params;


public final class  extends Enum
{

    public static final a FACEBOOK;
    public static final a FLIPKART;
    public static final a GOOGLE;
    private static final a a[];

    public static  valueOf(String s)
    {
        return ()Enum.valueOf(com/flipkart/android/volley/request/login/params/LoginParam$LoginType, s);
    }

    public static [] values()
    {
        return ([])a.clone();
    }

    static 
    {
        FLIPKART = new <init>("FLIPKART", 0);
        GOOGLE = new <init>("GOOGLE", 1);
        FACEBOOK = new <init>("FACEBOOK", 2);
        e_3B_.clone aclone[] = new <init>[3];
        aclone[0] = FLIPKART;
        aclone[1] = GOOGLE;
        aclone[2] = FACEBOOK;
        a = aclone;
    }

    private (String s, int i)
    {
        super(s, i);
    }
}
