// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.volley;


public final class  extends Enum
{

    public static final a HIGH;
    public static final a IMMEDIATE;
    public static final a LOW;
    public static final a NORMAL;
    private static final a a[];

    public static  valueOf(String s)
    {
        return ()Enum.valueOf(com/android/volley/Request$Priority, s);
    }

    public static [] values()
    {
        return ([])a.clone();
    }

    static 
    {
        LOW = new <init>("LOW", 0);
        NORMAL = new <init>("NORMAL", 1);
        HIGH = new <init>("HIGH", 2);
        IMMEDIATE = new <init>("IMMEDIATE", 3);
        y_3B_.clone aclone[] = new <init>[4];
        aclone[0] = LOW;
        aclone[1] = NORMAL;
        aclone[2] = HIGH;
        aclone[3] = IMMEDIATE;
        a = aclone;
    }

    private (String s, int i)
    {
        super(s, i);
    }
}
