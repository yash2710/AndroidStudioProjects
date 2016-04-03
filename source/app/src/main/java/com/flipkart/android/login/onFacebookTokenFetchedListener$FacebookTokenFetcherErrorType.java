// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.login;


public final class  extends Enum
{

    public static final a ErrorCancel;
    public static final a ErrorFacebook;
    public static final a ErrorUnknown;
    private static final a a[];

    public static  valueOf(String s)
    {
        return ()Enum.valueOf(com/flipkart/android/login/onFacebookTokenFetchedListener$FacebookTokenFetcherErrorType, s);
    }

    public static [] values()
    {
        return ([])a.clone();
    }

    static 
    {
        ErrorUnknown = new <init>("ErrorUnknown", 0);
        ErrorCancel = new <init>("ErrorCancel", 1);
        ErrorFacebook = new <init>("ErrorFacebook", 2);
        e_3B_.clone aclone[] = new <init>[3];
        aclone[0] = ErrorUnknown;
        aclone[1] = ErrorCancel;
        aclone[2] = ErrorFacebook;
        a = aclone;
    }

    private (String s, int i)
    {
        super(s, i);
    }
}
