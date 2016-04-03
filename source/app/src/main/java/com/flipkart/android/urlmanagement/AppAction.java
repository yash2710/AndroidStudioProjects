// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.urlmanagement;


public final class AppAction extends Enum
{

    public static final AppAction adxtracking;
    public static final AppAction allCategoriesMenu;
    private static final AppAction b[];
    public static final AppAction back;
    public static final AppAction callUsWidget;
    public static final AppAction home_page;
    public static final AppAction multiWidgetPage;
    public static final AppAction openEncodedUrlExternal;
    public static final AppAction openUrlExternal;
    public static final AppAction productBrowseView;
    public static final AppAction productListView;
    public static final AppAction productPage;
    public static final AppAction searchPage;
    public static final AppAction shareappfacebook;
    public static final AppAction shareapptwitter;
    public static final AppAction upgradeApp;
    public static final AppAction view_product;
    public static final AppAction webView;
    private final String a;

    private AppAction(String s, int i, String s1)
    {
        super(s, i);
        a = s1;
    }

    public static AppAction valueOf(String s)
    {
        return (AppAction)Enum.valueOf(com/flipkart/android/urlmanagement/AppAction, s);
    }

    public static AppAction[] values()
    {
        return (AppAction[])b.clone();
    }

    public final String toString()
    {
        return a;
    }

    static 
    {
        view_product = new AppAction("view_product", 0, "view_product");
        home_page = new AppAction("home_page", 1, "home_page");
        back = new AppAction("back", 2, "back");
        productPage = new AppAction("productPage", 3, "productPage");
        productListView = new AppAction("productListView", 4, "productListView");
        productBrowseView = new AppAction("productBrowseView", 5, "productBrowseView");
        multiWidgetPage = new AppAction("multiWidgetPage", 6, "multiWidgetPage");
        allCategoriesMenu = new AppAction("allCategoriesMenu", 7, "allCategoriesMenu");
        webView = new AppAction("webView", 8, "webView");
        openEncodedUrlExternal = new AppAction("openEncodedUrlExternal", 9, "openEncodedUrlExternal");
        openUrlExternal = new AppAction("openUrlExternal", 10, "openUrlExternal");
        searchPage = new AppAction("searchPage", 11, "searchPage");
        adxtracking = new AppAction("adxtracking", 12, "adxtracking");
        callUsWidget = new AppAction("callUsWidget", 13, "callUsWidget");
        shareappfacebook = new AppAction("shareappfacebook", 14, "shareappfacebook");
        shareapptwitter = new AppAction("shareapptwitter", 15, "shareapptwitter");
        upgradeApp = new AppAction("upgradeApp", 16, "upgradeApp");
        AppAction aappaction[] = new AppAction[17];
        aappaction[0] = view_product;
        aappaction[1] = home_page;
        aappaction[2] = back;
        aappaction[3] = productPage;
        aappaction[4] = productListView;
        aappaction[5] = productBrowseView;
        aappaction[6] = multiWidgetPage;
        aappaction[7] = allCategoriesMenu;
        aappaction[8] = webView;
        aappaction[9] = openEncodedUrlExternal;
        aappaction[10] = openUrlExternal;
        aappaction[11] = searchPage;
        aappaction[12] = adxtracking;
        aappaction[13] = callUsWidget;
        aappaction[14] = shareappfacebook;
        aappaction[15] = shareapptwitter;
        aappaction[16] = upgradeApp;
        b = aappaction;
    }
}
