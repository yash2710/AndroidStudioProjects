// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.login;


public final class LoginType extends Enum
{

    public static final LoginType Direct;
    public static final LoginType Facebook;
    public static final LoginType Google;
    public static final LoginType Unknown;
    private static final LoginType a[];

    private LoginType(String s, int i)
    {
        super(s, i);
    }

    public static LoginType valueOf(String s)
    {
        return (LoginType)Enum.valueOf(com/flipkart/android/login/LoginType, s);
    }

    public static LoginType[] values()
    {
        return (LoginType[])a.clone();
    }

    static 
    {
        Direct = new LoginType("Direct", 0);
        Google = new LoginType("Google", 1);
        Facebook = new LoginType("Facebook", 2);
        Unknown = new LoginType("Unknown", 3);
        LoginType alogintype[] = new LoginType[4];
        alogintype[0] = Direct;
        alogintype[1] = Google;
        alogintype[2] = Facebook;
        alogintype[3] = Unknown;
        a = alogintype;
    }
}
