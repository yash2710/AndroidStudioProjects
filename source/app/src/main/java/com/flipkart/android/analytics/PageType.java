// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.analytics;


public final class PageType extends Enum
{

    public static final PageType CLP;
    public static final PageType ExternalWebView;
    public static final PageType Homepage;
    public static final PageType Login;
    public static final PageType Notification;
    public static final PageType OrderConfirmationPage;
    public static final PageType Ozone;
    public static final PageType Product;
    public static final PageType ProductList;
    public static final PageType ProductReview;
    public static final PageType ProductSeller;
    public static final PageType ProductSpecification;
    public static final PageType ProductSummary;
    public static final PageType Recommendation;
    public static final PageType Search;
    public static final PageType SellerInfo;
    public static final PageType StoreFilters;
    public static final PageType StoreFront;
    public static final PageType UserPage;
    public static final PageType Webview;
    private static final PageType a[];

    private PageType(String s, int i)
    {
        super(s, i);
    }

    public static PageType valueOf(String s)
    {
        return (PageType)Enum.valueOf(com/flipkart/android/analytics/PageType, s);
    }

    public static PageType[] values()
    {
        return (PageType[])a.clone();
    }

    static 
    {
        Login = new PageType("Login", 0);
        Homepage = new PageType("Homepage", 1);
        StoreFront = new PageType("StoreFront", 2);
        ProductList = new PageType("ProductList", 3);
        CLP = new PageType("CLP", 4);
        Search = new PageType("Search", 5);
        Product = new PageType("Product", 6);
        SellerInfo = new PageType("SellerInfo", 7);
        Webview = new PageType("Webview", 8);
        StoreFilters = new PageType("StoreFilters", 9);
        ProductReview = new PageType("ProductReview", 10);
        ProductSeller = new PageType("ProductSeller", 11);
        ProductSpecification = new PageType("ProductSpecification", 12);
        ProductSummary = new PageType("ProductSummary", 13);
        UserPage = new PageType("UserPage", 14);
        Recommendation = new PageType("Recommendation", 15);
        Notification = new PageType("Notification", 16);
        OrderConfirmationPage = new PageType("OrderConfirmationPage", 17);
        ExternalWebView = new PageType("ExternalWebView", 18);
        Ozone = new PageType("Ozone", 19);
        PageType apagetype[] = new PageType[20];
        apagetype[0] = Login;
        apagetype[1] = Homepage;
        apagetype[2] = StoreFront;
        apagetype[3] = ProductList;
        apagetype[4] = CLP;
        apagetype[5] = Search;
        apagetype[6] = Product;
        apagetype[7] = SellerInfo;
        apagetype[8] = Webview;
        apagetype[9] = StoreFilters;
        apagetype[10] = ProductReview;
        apagetype[11] = ProductSeller;
        apagetype[12] = ProductSpecification;
        apagetype[13] = ProductSummary;
        apagetype[14] = UserPage;
        apagetype[15] = Recommendation;
        apagetype[16] = Notification;
        apagetype[17] = OrderConfirmationPage;
        apagetype[18] = ExternalWebView;
        apagetype[19] = Ozone;
        a = apagetype;
    }
}
