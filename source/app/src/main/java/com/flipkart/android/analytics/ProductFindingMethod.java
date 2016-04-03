// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.analytics;


public final class ProductFindingMethod extends Enum
{

    public static final ProductFindingMethod Announcement;
    public static final ProductFindingMethod Banner;
    public static final ProductFindingMethod BrowseHistory;
    public static final ProductFindingMethod Cart;
    public static final ProductFindingMethod Expandable;
    public static final ProductFindingMethod FOZ;
    public static final ProductFindingMethod FilterWidget;
    public static final ProductFindingMethod Flyout;
    public static final ProductFindingMethod Foz_balloon;
    public static final ProductFindingMethod Foz_dotd;
    public static final ProductFindingMethod Foz_tab;
    public static final ProductFindingMethod Merchandizing;
    public static final ProductFindingMethod MetroTile;
    public static final ProductFindingMethod MetroTileExp;
    public static final ProductFindingMethod NewLaunch;
    public static final ProductFindingMethod Notification;
    public static final ProductFindingMethod Omu;
    public static final ProductFindingMethod Recommendation;
    public static final ProductFindingMethod Search;
    public static final ProductFindingMethod SimilarProducts;
    public static final ProductFindingMethod VisualNavigation;
    public static final ProductFindingMethod Wishlist;
    private static final ProductFindingMethod a[];

    private ProductFindingMethod(String s, int i)
    {
        super(s, i);
    }

    public static ProductFindingMethod valueOf(String s)
    {
        return (ProductFindingMethod)Enum.valueOf(com/flipkart/android/analytics/ProductFindingMethod, s);
    }

    public static ProductFindingMethod[] values()
    {
        return (ProductFindingMethod[])a.clone();
    }

    static 
    {
        Flyout = new ProductFindingMethod("Flyout", 0);
        Banner = new ProductFindingMethod("Banner", 1);
        FOZ = new ProductFindingMethod("FOZ", 2);
        Search = new ProductFindingMethod("Search", 3);
        BrowseHistory = new ProductFindingMethod("BrowseHistory", 4);
        Merchandizing = new ProductFindingMethod("Merchandizing", 5);
        VisualNavigation = new ProductFindingMethod("VisualNavigation", 6);
        SimilarProducts = new ProductFindingMethod("SimilarProducts", 7);
        Cart = new ProductFindingMethod("Cart", 8);
        Wishlist = new ProductFindingMethod("Wishlist", 9);
        Recommendation = new ProductFindingMethod("Recommendation", 10);
        NewLaunch = new ProductFindingMethod("NewLaunch", 11);
        FilterWidget = new ProductFindingMethod("FilterWidget", 12);
        MetroTile = new ProductFindingMethod("MetroTile", 13);
        MetroTileExp = new ProductFindingMethod("MetroTileExp", 14);
        Notification = new ProductFindingMethod("Notification", 15);
        Announcement = new ProductFindingMethod("Announcement", 16);
        Foz_balloon = new ProductFindingMethod("Foz_balloon", 17);
        Foz_dotd = new ProductFindingMethod("Foz_dotd", 18);
        Foz_tab = new ProductFindingMethod("Foz_tab", 19);
        Omu = new ProductFindingMethod("Omu", 20);
        Expandable = new ProductFindingMethod("Expandable", 21);
        ProductFindingMethod aproductfindingmethod[] = new ProductFindingMethod[22];
        aproductfindingmethod[0] = Flyout;
        aproductfindingmethod[1] = Banner;
        aproductfindingmethod[2] = FOZ;
        aproductfindingmethod[3] = Search;
        aproductfindingmethod[4] = BrowseHistory;
        aproductfindingmethod[5] = Merchandizing;
        aproductfindingmethod[6] = VisualNavigation;
        aproductfindingmethod[7] = SimilarProducts;
        aproductfindingmethod[8] = Cart;
        aproductfindingmethod[9] = Wishlist;
        aproductfindingmethod[10] = Recommendation;
        aproductfindingmethod[11] = NewLaunch;
        aproductfindingmethod[12] = FilterWidget;
        aproductfindingmethod[13] = MetroTile;
        aproductfindingmethod[14] = MetroTileExp;
        aproductfindingmethod[15] = Notification;
        aproductfindingmethod[16] = Announcement;
        aproductfindingmethod[17] = Foz_balloon;
        aproductfindingmethod[18] = Foz_dotd;
        aproductfindingmethod[19] = Foz_tab;
        aproductfindingmethod[20] = Omu;
        aproductfindingmethod[21] = Expandable;
        a = aproductfindingmethod;
    }
}
