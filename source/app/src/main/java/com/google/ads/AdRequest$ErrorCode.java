// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.ads;


public final class a extends Enum
{

    public static final b INTERNAL_ERROR;
    public static final b INVALID_REQUEST;
    public static final b NETWORK_ERROR;
    public static final b NO_FILL;
    private static final b b[];
    private final String a;

    public static a valueOf(String s)
    {
        return (a)Enum.valueOf(com/google/ads/AdRequest$ErrorCode, s);
    }

    public static a[] values()
    {
        return (a[])b.clone();
    }

    public final String toString()
    {
        return a;
    }

    static 
    {
        INVALID_REQUEST = new <init>("INVALID_REQUEST", 0, "Invalid Ad request.");
        NO_FILL = new <init>("NO_FILL", 1, "Ad request successful, but no ad returned due to lack of ad inventory.");
        NETWORK_ERROR = new <init>("NETWORK_ERROR", 2, "A network error occurred.");
        INTERNAL_ERROR = new <init>("INTERNAL_ERROR", 3, "There was an internal error.");
        a aa[] = new <init>[4];
        aa[0] = INVALID_REQUEST;
        aa[1] = NO_FILL;
        aa[2] = NETWORK_ERROR;
        aa[3] = INTERNAL_ERROR;
        b = aa;
    }

    private (String s, int i, String s1)
    {
        super(s, i);
        a = s1;
    }
}
