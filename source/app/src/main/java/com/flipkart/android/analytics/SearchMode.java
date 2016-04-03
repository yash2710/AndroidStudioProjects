// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.analytics;


public final class SearchMode extends Enum
{

    public static final SearchMode Barcode;
    public static final SearchMode CLP;
    public static final SearchMode Direct;
    public static final SearchMode None;
    public static final SearchMode ProductListNullSearchPage;
    public static final SearchMode ProductPageBarCode;
    public static final SearchMode UpFrontSearch;
    public static final SearchMode Voice;
    private static final SearchMode a[];

    private SearchMode(String s, int i)
    {
        super(s, i);
    }

    public static SearchMode valueOf(String s)
    {
        return (SearchMode)Enum.valueOf(com/flipkart/android/analytics/SearchMode, s);
    }

    public static SearchMode[] values()
    {
        return (SearchMode[])a.clone();
    }

    static 
    {
        Direct = new SearchMode("Direct", 0);
        Barcode = new SearchMode("Barcode", 1);
        Voice = new SearchMode("Voice", 2);
        UpFrontSearch = new SearchMode("UpFrontSearch", 3);
        CLP = new SearchMode("CLP", 4);
        ProductListNullSearchPage = new SearchMode("ProductListNullSearchPage", 5);
        ProductPageBarCode = new SearchMode("ProductPageBarCode", 6);
        None = new SearchMode("None", 7);
        SearchMode asearchmode[] = new SearchMode[8];
        asearchmode[0] = Direct;
        asearchmode[1] = Barcode;
        asearchmode[2] = Voice;
        asearchmode[3] = UpFrontSearch;
        asearchmode[4] = CLP;
        asearchmode[5] = ProductListNullSearchPage;
        asearchmode[6] = ProductPageBarCode;
        asearchmode[7] = None;
        a = asearchmode;
    }
}
