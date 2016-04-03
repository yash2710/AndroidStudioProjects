// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.analytics;


public final class OfferType extends Enum
{

    public static final OfferType PRODUCT;
    public static final OfferType STORE;
    private static final OfferType a[];

    private OfferType(String s, int i)
    {
        super(s, i);
    }

    public static OfferType valueOf(String s)
    {
        return (OfferType)Enum.valueOf(com/flipkart/android/analytics/OfferType, s);
    }

    public static OfferType[] values()
    {
        return (OfferType[])a.clone();
    }

    static 
    {
        PRODUCT = new OfferType("PRODUCT", 0);
        STORE = new OfferType("STORE", 1);
        OfferType aoffertype[] = new OfferType[2];
        aoffertype[0] = PRODUCT;
        aoffertype[1] = STORE;
        a = aoffertype;
    }
}
