// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.login;


public final class  extends Enum
{

    public static final a EventTypeAuthTokenReceived;
    private static final a a[];

    public static  valueOf(String s)
    {
        return ()Enum.valueOf(com/flipkart/android/login/onFacebookTokenFetchedListener$FacebookTokenFetcherEventType, s);
    }

    public static [] values()
    {
        return ([])a.clone();
    }

    static 
    {
        EventTypeAuthTokenReceived = new <init>("EventTypeAuthTokenReceived", 0);
        e_3B_.clone aclone[] = new <init>[1];
        aclone[0] = EventTypeAuthTokenReceived;
        a = aclone;
    }

    private (String s, int i)
    {
        super(s, 0);
    }
}
