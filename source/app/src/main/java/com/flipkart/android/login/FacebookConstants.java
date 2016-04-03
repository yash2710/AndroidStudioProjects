// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.login;


public final class FacebookConstants extends Enum
{

    public static String AppId = "383167535118598";
    public static String LoginPermissionEmail = "email";
    public static String LoginPermissionPublishStream = "publish_stream";
    private static final FacebookConstants a[];
    public static final FacebookConstants instance;

    private FacebookConstants(String s, int i)
    {
        super(s, 0);
    }

    public static FacebookConstants valueOf(String s)
    {
        return (FacebookConstants)Enum.valueOf(com/flipkart/android/login/FacebookConstants, s);
    }

    public static FacebookConstants[] values()
    {
        return (FacebookConstants[])a.clone();
    }

    static 
    {
        instance = new FacebookConstants("instance", 0);
        FacebookConstants afacebookconstants[] = new FacebookConstants[1];
        afacebookconstants[0] = instance;
        a = afacebookconstants;
    }
}
