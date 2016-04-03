// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.analytics;


public final class AddCartLocation extends Enum
{

    public static final AddCartLocation BrowseHistoryListPage;
    public static final AddCartLocation HomePageRecommendationListPage;
    public static final AddCartLocation ProductListPage;
    public static final AddCartLocation ProductListRecommendationPage;
    public static final AddCartLocation ProductMultiSellerPage;
    public static final AddCartLocation ProductPage;
    public static final AddCartLocation ProductPageRecommendationListPage;
    public static final AddCartLocation ProductReviewPage;
    public static final AddCartLocation ProductSpecificationPage;
    public static final AddCartLocation ProductSummaryPage;
    public static final AddCartLocation WishListPage;
    private static final AddCartLocation a[];

    private AddCartLocation(String s, int i)
    {
        super(s, i);
    }

    public static AddCartLocation valueOf(String s)
    {
        return (AddCartLocation)Enum.valueOf(com/flipkart/android/analytics/AddCartLocation, s);
    }

    public static AddCartLocation[] values()
    {
        return (AddCartLocation[])a.clone();
    }

    static 
    {
        ProductPage = new AddCartLocation("ProductPage", 0);
        ProductListPage = new AddCartLocation("ProductListPage", 1);
        ProductMultiSellerPage = new AddCartLocation("ProductMultiSellerPage", 2);
        ProductReviewPage = new AddCartLocation("ProductReviewPage", 3);
        ProductSpecificationPage = new AddCartLocation("ProductSpecificationPage", 4);
        ProductSummaryPage = new AddCartLocation("ProductSummaryPage", 5);
        WishListPage = new AddCartLocation("WishListPage", 6);
        HomePageRecommendationListPage = new AddCartLocation("HomePageRecommendationListPage", 7);
        ProductPageRecommendationListPage = new AddCartLocation("ProductPageRecommendationListPage", 8);
        BrowseHistoryListPage = new AddCartLocation("BrowseHistoryListPage", 9);
        ProductListRecommendationPage = new AddCartLocation("ProductListRecommendationPage", 10);
        AddCartLocation aaddcartlocation[] = new AddCartLocation[11];
        aaddcartlocation[0] = ProductPage;
        aaddcartlocation[1] = ProductListPage;
        aaddcartlocation[2] = ProductMultiSellerPage;
        aaddcartlocation[3] = ProductReviewPage;
        aaddcartlocation[4] = ProductSpecificationPage;
        aaddcartlocation[5] = ProductSummaryPage;
        aaddcartlocation[6] = WishListPage;
        aaddcartlocation[7] = HomePageRecommendationListPage;
        aaddcartlocation[8] = ProductPageRecommendationListPage;
        aaddcartlocation[9] = BrowseHistoryListPage;
        aaddcartlocation[10] = ProductListRecommendationPage;
        a = aaddcartlocation;
    }
}
