// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.urlmanagement;


public final class WebviewAction extends Enum
{

    public static final WebviewAction BUY_NOW;
    public static final WebviewAction CONTACT_US;
    public static final WebviewAction FAQ;
    public static final WebviewAction FLIPKART_FIRST;
    public static final WebviewAction FLIPKART_FIRST_MANAGE;
    public static final WebviewAction FORGOT_PASSWORD;
    public static final WebviewAction MY_ADDRESS;
    public static final WebviewAction MY_ORDERS;
    public static final WebviewAction POLICIES;
    public static final WebviewAction TRACK_ORDER;
    public static final WebviewAction VIEW_CART;
    private static final WebviewAction a[];

    private WebviewAction(String s, int i)
    {
        super(s, i);
    }

    public static WebviewAction valueOf(String s)
    {
        return (WebviewAction)Enum.valueOf(com/flipkart/android/urlmanagement/WebviewAction, s);
    }

    public static WebviewAction[] values()
    {
        return (WebviewAction[])a.clone();
    }

    static 
    {
        BUY_NOW = new WebviewAction("BUY_NOW", 0);
        VIEW_CART = new WebviewAction("VIEW_CART", 1);
        TRACK_ORDER = new WebviewAction("TRACK_ORDER", 2);
        FORGOT_PASSWORD = new WebviewAction("FORGOT_PASSWORD", 3);
        MY_ORDERS = new WebviewAction("MY_ORDERS", 4);
        CONTACT_US = new WebviewAction("CONTACT_US", 5);
        FAQ = new WebviewAction("FAQ", 6);
        POLICIES = new WebviewAction("POLICIES", 7);
        FLIPKART_FIRST = new WebviewAction("FLIPKART_FIRST", 8);
        FLIPKART_FIRST_MANAGE = new WebviewAction("FLIPKART_FIRST_MANAGE", 9);
        MY_ADDRESS = new WebviewAction("MY_ADDRESS", 10);
        WebviewAction awebviewaction[] = new WebviewAction[11];
        awebviewaction[0] = BUY_NOW;
        awebviewaction[1] = VIEW_CART;
        awebviewaction[2] = TRACK_ORDER;
        awebviewaction[3] = FORGOT_PASSWORD;
        awebviewaction[4] = MY_ORDERS;
        awebviewaction[5] = CONTACT_US;
        awebviewaction[6] = FAQ;
        awebviewaction[7] = POLICIES;
        awebviewaction[8] = FLIPKART_FIRST;
        awebviewaction[9] = FLIPKART_FIRST_MANAGE;
        awebviewaction[10] = MY_ADDRESS;
        a = awebviewaction;
    }
}
