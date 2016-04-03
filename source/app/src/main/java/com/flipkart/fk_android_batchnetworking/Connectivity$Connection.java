// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.fk_android_batchnetworking;


public final class a extends Enum
{

    public static final b MOBILE;
    public static final b NO_CONNECTIVITY;
    public static final b WIFI;
    private static final b b[];
    private String a;

    public static a valueOf(String s)
    {
        return (a)Enum.valueOf(com/flipkart/fk_android_batchnetworking/Connectivity$Connection, s);
    }

    public static a[] values()
    {
        return (a[])b.clone();
    }

    public final String getName()
    {
        return a;
    }

    static 
    {
        NO_CONNECTIVITY = new <init>("NO_CONNECTIVITY", 0, "no_connection");
        MOBILE = new <init>("MOBILE", 1, "mobile");
        WIFI = new <init>("WIFI", 2, "wifi");
        a aa[] = new <init>[3];
        aa[0] = NO_CONNECTIVITY;
        aa[1] = MOBILE;
        aa[2] = WIFI;
        b = aa;
    }

    private (String s, int i, String s1)
    {
        super(s, i);
        a = s1;
    }
}
