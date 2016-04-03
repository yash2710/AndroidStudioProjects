// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.utils;


public final class PageTypeUtils extends Enum
{

    public static final PageTypeUtils AppWidget;
    public static final PageTypeUtils BrowseHistory;
    public static final PageTypeUtils CLP;
    public static final PageTypeUtils DeepLink;
    public static final PageTypeUtils FilterPage;
    public static final PageTypeUtils Flyout;
    public static final PageTypeUtils Foz;
    public static final PageTypeUtils HomePage;
    public static final PageTypeUtils HomePageRecommendation;
    public static final PageTypeUtils InAppNotificationPage;
    public static final PageTypeUtils None;
    public static final PageTypeUtils Notification;
    public static final PageTypeUtils ProductGrid;
    public static final PageTypeUtils ProductImageGalleryPage;
    public static final PageTypeUtils ProductList;
    public static final PageTypeUtils ProductListNullPage;
    public static final PageTypeUtils ProductListRecommendation;
    public static final PageTypeUtils ProductMoreSellerPage;
    public static final PageTypeUtils ProductPage;
    public static final PageTypeUtils ProductPageBarCode;
    public static final PageTypeUtils ProductPageRecommendation;
    public static final PageTypeUtils ProductReviewPage;
    public static final PageTypeUtils ProductSpecificationPage;
    public static final PageTypeUtils ProductSummaryPage;
    public static final PageTypeUtils RefineByCategoryPage;
    public static final PageTypeUtils SearchListPage;
    public static final PageTypeUtils SearchType;
    public static final PageTypeUtils SellerPage;
    public static final PageTypeUtils SplashPage;
    public static final PageTypeUtils StoreFront;
    public static final PageTypeUtils WishList;
    private static final PageTypeUtils a[];

    private PageTypeUtils(String s, int i)
    {
        super(s, i);
    }

    public static PageTypeUtils valueOf(String s)
    {
        return (PageTypeUtils)Enum.valueOf(com/flipkart/android/utils/PageTypeUtils, s);
    }

    public static PageTypeUtils[] values()
    {
        return (PageTypeUtils[])a.clone();
    }

    static 
    {
        ProductPage = new PageTypeUtils("ProductPage", 0);
        ProductList = new PageTypeUtils("ProductList", 1);
        ProductGrid = new PageTypeUtils("ProductGrid", 2);
        HomePageRecommendation = new PageTypeUtils("HomePageRecommendation", 3);
        ProductPageRecommendation = new PageTypeUtils("ProductPageRecommendation", 4);
        ProductListRecommendation = new PageTypeUtils("ProductListRecommendation", 5);
        Flyout = new PageTypeUtils("Flyout", 6);
        HomePage = new PageTypeUtils("HomePage", 7);
        AppWidget = new PageTypeUtils("AppWidget", 8);
        Notification = new PageTypeUtils("Notification", 9);
        WishList = new PageTypeUtils("WishList", 10);
        ProductSummaryPage = new PageTypeUtils("ProductSummaryPage", 11);
        ProductSpecificationPage = new PageTypeUtils("ProductSpecificationPage", 12);
        ProductReviewPage = new PageTypeUtils("ProductReviewPage", 13);
        SellerPage = new PageTypeUtils("SellerPage", 14);
        ProductMoreSellerPage = new PageTypeUtils("ProductMoreSellerPage", 15);
        ProductImageGalleryPage = new PageTypeUtils("ProductImageGalleryPage", 16);
        RefineByCategoryPage = new PageTypeUtils("RefineByCategoryPage", 17);
        FilterPage = new PageTypeUtils("FilterPage", 18);
        CLP = new PageTypeUtils("CLP", 19);
        StoreFront = new PageTypeUtils("StoreFront", 20);
        BrowseHistory = new PageTypeUtils("BrowseHistory", 21);
        ProductListNullPage = new PageTypeUtils("ProductListNullPage", 22);
        DeepLink = new PageTypeUtils("DeepLink", 23);
        ProductPageBarCode = new PageTypeUtils("ProductPageBarCode", 24);
        SearchListPage = new PageTypeUtils("SearchListPage", 25);
        InAppNotificationPage = new PageTypeUtils("InAppNotificationPage", 26);
        SearchType = new PageTypeUtils("SearchType", 27);
        Foz = new PageTypeUtils("Foz", 28);
        SplashPage = new PageTypeUtils("SplashPage", 29);
        None = new PageTypeUtils("None", 30);
        PageTypeUtils apagetypeutils[] = new PageTypeUtils[31];
        apagetypeutils[0] = ProductPage;
        apagetypeutils[1] = ProductList;
        apagetypeutils[2] = ProductGrid;
        apagetypeutils[3] = HomePageRecommendation;
        apagetypeutils[4] = ProductPageRecommendation;
        apagetypeutils[5] = ProductListRecommendation;
        apagetypeutils[6] = Flyout;
        apagetypeutils[7] = HomePage;
        apagetypeutils[8] = AppWidget;
        apagetypeutils[9] = Notification;
        apagetypeutils[10] = WishList;
        apagetypeutils[11] = ProductSummaryPage;
        apagetypeutils[12] = ProductSpecificationPage;
        apagetypeutils[13] = ProductReviewPage;
        apagetypeutils[14] = SellerPage;
        apagetypeutils[15] = ProductMoreSellerPage;
        apagetypeutils[16] = ProductImageGalleryPage;
        apagetypeutils[17] = RefineByCategoryPage;
        apagetypeutils[18] = FilterPage;
        apagetypeutils[19] = CLP;
        apagetypeutils[20] = StoreFront;
        apagetypeutils[21] = BrowseHistory;
        apagetypeutils[22] = ProductListNullPage;
        apagetypeutils[23] = DeepLink;
        apagetypeutils[24] = ProductPageBarCode;
        apagetypeutils[25] = SearchListPage;
        apagetypeutils[26] = InAppNotificationPage;
        apagetypeutils[27] = SearchType;
        apagetypeutils[28] = Foz;
        apagetypeutils[29] = SplashPage;
        apagetypeutils[30] = None;
        a = apagetypeutils;
    }
}
