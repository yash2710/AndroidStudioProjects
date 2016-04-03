// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.analytics;


public final class ProductListViewType extends Enum
{

    public static final ProductListViewType Default;
    public static final ProductListViewType Full;
    public static final ProductListViewType Grid;
    public static final ProductListViewType List;
    private static final ProductListViewType a[];

    private ProductListViewType(String s, int i)
    {
        super(s, i);
    }

    public static ProductListViewType valueOf(String s)
    {
        return (ProductListViewType)Enum.valueOf(com/flipkart/android/analytics/ProductListViewType, s);
    }

    public static ProductListViewType[] values()
    {
        return (ProductListViewType[])a.clone();
    }

    static 
    {
        List = new ProductListViewType("List", 0);
        Grid = new ProductListViewType("Grid", 1);
        Full = new ProductListViewType("Full", 2);
        Default = new ProductListViewType("Default", 3);
        ProductListViewType aproductlistviewtype[] = new ProductListViewType[4];
        aproductlistviewtype[0] = List;
        aproductlistviewtype[1] = Grid;
        aproductlistviewtype[2] = Full;
        aproductlistviewtype[3] = Default;
        a = aproductlistviewtype;
    }
}
