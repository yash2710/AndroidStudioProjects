// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.login;


public final class  extends Enum
{

    public static final a EEventAuthCodeReceived;
    public static final a EEventPageLoadFinished;
    public static final a EEventPageLoadStarted;
    private static final a a[];

    public static  valueOf(String s)
    {
        return ()Enum.valueOf(com/flipkart/android/login/onGoogleLoginWebViewClientListener$TGoogleLoginWebClientEventType, s);
    }

    public static [] values()
    {
        return ([])a.clone();
    }

    static 
    {
        EEventAuthCodeReceived = new <init>("EEventAuthCodeReceived", 0);
        EEventPageLoadStarted = new <init>("EEventPageLoadStarted", 1);
        EEventPageLoadFinished = new <init>("EEventPageLoadFinished", 2);
        e_3B_.clone aclone[] = new <init>[3];
        aclone[0] = EEventAuthCodeReceived;
        aclone[1] = EEventPageLoadStarted;
        aclone[2] = EEventPageLoadFinished;
        a = aclone;
    }

    private (String s, int i)
    {
        super(s, i);
    }
}
