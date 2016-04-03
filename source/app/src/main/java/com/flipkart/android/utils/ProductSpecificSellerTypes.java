// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.utils;


public final class ProductSpecificSellerTypes extends Enum
{

    public static final ProductSpecificSellerTypes NONE;
    public static final ProductSpecificSellerTypes NPref_Cheap_NWSR;
    public static final ProductSpecificSellerTypes NPref_Cheap_WSR;
    public static final ProductSpecificSellerTypes NPref_NCheap_NWSR;
    public static final ProductSpecificSellerTypes NPref_NCheap_WSR;
    public static final ProductSpecificSellerTypes NWSR_SINGLE;
    public static final ProductSpecificSellerTypes Pref_Cheap_NWSR;
    public static final ProductSpecificSellerTypes Pref_Cheap_WSR;
    public static final ProductSpecificSellerTypes Pref_NCheap_NWSR;
    public static final ProductSpecificSellerTypes Pref_NCheap_WSR;
    public static final ProductSpecificSellerTypes WSR_SINGLE;
    private static final ProductSpecificSellerTypes a[];

    private ProductSpecificSellerTypes(String s, int i)
    {
        super(s, i);
    }

    public static ProductSpecificSellerTypes valueOf(String s)
    {
        return (ProductSpecificSellerTypes)Enum.valueOf(com/flipkart/android/utils/ProductSpecificSellerTypes, s);
    }

    public static ProductSpecificSellerTypes[] values()
    {
        return (ProductSpecificSellerTypes[])a.clone();
    }

    static 
    {
        WSR_SINGLE = new ProductSpecificSellerTypes("WSR_SINGLE", 0);
        NWSR_SINGLE = new ProductSpecificSellerTypes("NWSR_SINGLE", 1);
        Pref_Cheap_WSR = new ProductSpecificSellerTypes("Pref_Cheap_WSR", 2);
        NPref_Cheap_WSR = new ProductSpecificSellerTypes("NPref_Cheap_WSR", 3);
        Pref_NCheap_WSR = new ProductSpecificSellerTypes("Pref_NCheap_WSR", 4);
        NPref_NCheap_WSR = new ProductSpecificSellerTypes("NPref_NCheap_WSR", 5);
        Pref_Cheap_NWSR = new ProductSpecificSellerTypes("Pref_Cheap_NWSR", 6);
        NPref_Cheap_NWSR = new ProductSpecificSellerTypes("NPref_Cheap_NWSR", 7);
        Pref_NCheap_NWSR = new ProductSpecificSellerTypes("Pref_NCheap_NWSR", 8);
        NPref_NCheap_NWSR = new ProductSpecificSellerTypes("NPref_NCheap_NWSR", 9);
        NONE = new ProductSpecificSellerTypes("NONE", 10);
        ProductSpecificSellerTypes aproductspecificsellertypes[] = new ProductSpecificSellerTypes[11];
        aproductspecificsellertypes[0] = WSR_SINGLE;
        aproductspecificsellertypes[1] = NWSR_SINGLE;
        aproductspecificsellertypes[2] = Pref_Cheap_WSR;
        aproductspecificsellertypes[3] = NPref_Cheap_WSR;
        aproductspecificsellertypes[4] = Pref_NCheap_WSR;
        aproductspecificsellertypes[5] = NPref_NCheap_WSR;
        aproductspecificsellertypes[6] = Pref_Cheap_NWSR;
        aproductspecificsellertypes[7] = NPref_Cheap_NWSR;
        aproductspecificsellertypes[8] = Pref_NCheap_NWSR;
        aproductspecificsellertypes[9] = NPref_NCheap_NWSR;
        aproductspecificsellertypes[10] = NONE;
        a = aproductspecificsellertypes;
    }
}
