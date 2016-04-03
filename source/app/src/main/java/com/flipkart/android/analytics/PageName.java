// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.analytics;


public final class PageName extends Enum
{

    public static final PageName BrowseHistory;
    public static final PageName CLP;
    public static final PageName ExternalWebPage;
    public static final PageName GoogleWebLoginPage;
    public static final PageName HomePageRecommendation;
    public static final PageName Homepage;
    public static final PageName LoginPage;
    public static final PageName Ozone;
    public static final PageName ProductListPage;
    public static final PageName ProductListRecommendation;
    public static final PageName ProductPageRecommendation;
    public static final PageName ProductRefineCategoryPage;
    public static final PageName ProductReviewPage;
    public static final PageName ProductSellerPage;
    public static final PageName ProductSpecificationPage;
    public static final PageName ProductSummaryPage;
    public static final PageName ProductViewPage;
    public static final PageName SearchResultPage;
    public static final PageName SellerInfoPage;
    public static final PageName SignUpPage;
    public static final PageName StoreFront;
    public static final PageName WebPage;
    public static final PageName Wishlist;
    private static final PageName a[];

    private PageName(String s, int i)
    {
        super(s, i);
    }

    public static PageName valueOf(String s)
    {
        return (PageName)Enum.valueOf(com/flipkart/android/analytics/PageName, s);
    }

    public static PageName[] values()
    {
        return (PageName[])a.clone();
    }

    static 
    {
        Homepage = new PageName("Homepage", 0);
        SearchResultPage = new PageName("SearchResultPage", 1);
        LoginPage = new PageName("LoginPage", 2);
        GoogleWebLoginPage = new PageName("GoogleWebLoginPage", 3);
        SignUpPage = new PageName("SignUpPage", 4);
        ProductViewPage = new PageName("ProductViewPage", 5);
        ProductListPage = new PageName("ProductListPage", 6);
        ProductReviewPage = new PageName("ProductReviewPage", 7);
        ProductSpecificationPage = new PageName("ProductSpecificationPage", 8);
        ProductSummaryPage = new PageName("ProductSummaryPage", 9);
        ProductSellerPage = new PageName("ProductSellerPage", 10);
        ProductRefineCategoryPage = new PageName("ProductRefineCategoryPage", 11);
        SellerInfoPage = new PageName("SellerInfoPage", 12);
        WebPage = new PageName("WebPage", 13);
        StoreFront = new PageName("StoreFront", 14);
        CLP = new PageName("CLP", 15);
        Wishlist = new PageName("Wishlist", 16);
        HomePageRecommendation = new PageName("HomePageRecommendation", 17);
        ProductPageRecommendation = new PageName("ProductPageRecommendation", 18);
        ProductListRecommendation = new PageName("ProductListRecommendation", 19);
        BrowseHistory = new PageName("BrowseHistory", 20);
        ExternalWebPage = new PageName("ExternalWebPage", 21);
        Ozone = new PageName("Ozone", 22);
        PageName apagename[] = new PageName[23];
        apagename[0] = Homepage;
        apagename[1] = SearchResultPage;
        apagename[2] = LoginPage;
        apagename[3] = GoogleWebLoginPage;
        apagename[4] = SignUpPage;
        apagename[5] = ProductViewPage;
        apagename[6] = ProductListPage;
        apagename[7] = ProductReviewPage;
        apagename[8] = ProductSpecificationPage;
        apagename[9] = ProductSummaryPage;
        apagename[10] = ProductSellerPage;
        apagename[11] = ProductRefineCategoryPage;
        apagename[12] = SellerInfoPage;
        apagename[13] = WebPage;
        apagename[14] = StoreFront;
        apagename[15] = CLP;
        apagename[16] = Wishlist;
        apagename[17] = HomePageRecommendation;
        apagename[18] = ProductPageRecommendation;
        apagename[19] = ProductListRecommendation;
        apagename[20] = BrowseHistory;
        apagename[21] = ExternalWebPage;
        apagename[22] = Ozone;
        a = apagename;
    }
}
