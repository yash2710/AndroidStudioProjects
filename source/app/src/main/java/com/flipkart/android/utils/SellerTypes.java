// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.utils;


public final class SellerTypes extends Enum
{

    public static final SellerTypes NONE;
    public static final SellerTypes NWSR_MULTIPLE;
    public static final SellerTypes NWSR_SINGLE;
    public static final SellerTypes WSR_MULTIPLE;
    public static final SellerTypes WSR_SINGLE;
    private static final SellerTypes a[];

    private SellerTypes(String s, int i)
    {
        super(s, i);
    }

    public static SellerTypes valueOf(String s)
    {
        return (SellerTypes)Enum.valueOf(com/flipkart/android/utils/SellerTypes, s);
    }

    public static SellerTypes[] values()
    {
        return (SellerTypes[])a.clone();
    }

    static 
    {
        WSR_SINGLE = new SellerTypes("WSR_SINGLE", 0);
        NWSR_SINGLE = new SellerTypes("NWSR_SINGLE", 1);
        WSR_MULTIPLE = new SellerTypes("WSR_MULTIPLE", 2);
        NWSR_MULTIPLE = new SellerTypes("NWSR_MULTIPLE", 3);
        NONE = new SellerTypes("NONE", 4);
        SellerTypes asellertypes[] = new SellerTypes[5];
        asellertypes[0] = WSR_SINGLE;
        asellertypes[1] = NWSR_SINGLE;
        asellertypes[2] = WSR_MULTIPLE;
        asellertypes[3] = NWSR_MULTIPLE;
        asellertypes[4] = NONE;
        a = asellertypes;
    }
}
