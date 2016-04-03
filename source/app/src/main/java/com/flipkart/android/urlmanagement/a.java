// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.urlmanagement;


// Referenced classes of package com.flipkart.android.urlmanagement:
//            AppAction

final class a
{

    static final int a[];

    static 
    {
        a = new int[AppAction.values().length];
        try
        {
            a[AppAction.home_page.ordinal()] = 1;
        }
        catch (NoSuchFieldError nosuchfielderror) { }
        try
        {
            a[AppAction.view_product.ordinal()] = 2;
        }
        catch (NoSuchFieldError nosuchfielderror1) { }
        try
        {
            a[AppAction.adxtracking.ordinal()] = 3;
        }
        catch (NoSuchFieldError nosuchfielderror2) { }
        try
        {
            a[AppAction.productPage.ordinal()] = 4;
        }
        catch (NoSuchFieldError nosuchfielderror3) { }
        try
        {
            a[AppAction.productListView.ordinal()] = 5;
        }
        catch (NoSuchFieldError nosuchfielderror4) { }
        try
        {
            a[AppAction.multiWidgetPage.ordinal()] = 6;
        }
        catch (NoSuchFieldError nosuchfielderror5) { }
        try
        {
            a[AppAction.productBrowseView.ordinal()] = 7;
        }
        catch (NoSuchFieldError nosuchfielderror6) { }
        try
        {
            a[AppAction.allCategoriesMenu.ordinal()] = 8;
        }
        catch (NoSuchFieldError nosuchfielderror7) { }
        try
        {
            a[AppAction.openUrlExternal.ordinal()] = 9;
        }
        catch (NoSuchFieldError nosuchfielderror8) { }
        try
        {
            a[AppAction.webView.ordinal()] = 10;
        }
        catch (NoSuchFieldError nosuchfielderror9) { }
        try
        {
            a[AppAction.openEncodedUrlExternal.ordinal()] = 11;
        }
        catch (NoSuchFieldError nosuchfielderror10) { }
        try
        {
            a[AppAction.shareappfacebook.ordinal()] = 12;
        }
        catch (NoSuchFieldError nosuchfielderror11) { }
        try
        {
            a[AppAction.shareapptwitter.ordinal()] = 13;
        }
        catch (NoSuchFieldError nosuchfielderror12)
        {
            return;
        }
    }
}
