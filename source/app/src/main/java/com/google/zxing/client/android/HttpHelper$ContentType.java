// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing.client.android;


public final class  extends Enum
{

    public static final a HTML;
    public static final a JSON;
    public static final a TEXT;
    public static final a XML;
    private static final a a[];

    public static  valueOf(String s)
    {
        return ()Enum.valueOf(com/google/zxing/client/android/HttpHelper$ContentType, s);
    }

    public static [] values()
    {
        return ([])a.clone();
    }

    static 
    {
        HTML = new <init>("HTML", 0);
        JSON = new <init>("JSON", 1);
        XML = new <init>("XML", 2);
        TEXT = new <init>("TEXT", 3);
        e_3B_.clone aclone[] = new <init>[4];
        aclone[0] = HTML;
        aclone[1] = JSON;
        aclone[2] = XML;
        aclone[3] = TEXT;
        a = aclone;
    }

    private (String s, int i)
    {
        super(s, i);
    }
}
