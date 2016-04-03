// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.customviews.enums;


public final class ActionBarState extends Enum
{

    public static final ActionBarState Cart;
    public static final ActionBarState Default_Back;
    public static final ActionBarState FiltersPage;
    public static final ActionBarState Home;
    public static final ActionBarState NoActionBar;
    public static final ActionBarState ProductImageGallery_Page;
    public static final ActionBarState Search_Page;
    public static final ActionBarState Search_Result_Page;
    private static final ActionBarState a[];

    private ActionBarState(String s, int i)
    {
        super(s, i);
    }

    public static ActionBarState valueOf(String s)
    {
        return (ActionBarState)Enum.valueOf(com/flipkart/android/customviews/enums/ActionBarState, s);
    }

    public static ActionBarState[] values()
    {
        return (ActionBarState[])a.clone();
    }

    static 
    {
        Home = new ActionBarState("Home", 0);
        Default_Back = new ActionBarState("Default_Back", 1);
        Search_Page = new ActionBarState("Search_Page", 2);
        Search_Result_Page = new ActionBarState("Search_Result_Page", 3);
        Cart = new ActionBarState("Cart", 4);
        ProductImageGallery_Page = new ActionBarState("ProductImageGallery_Page", 5);
        FiltersPage = new ActionBarState("FiltersPage", 6);
        NoActionBar = new ActionBarState("NoActionBar", 7);
        ActionBarState aactionbarstate[] = new ActionBarState[8];
        aactionbarstate[0] = Home;
        aactionbarstate[1] = Default_Back;
        aactionbarstate[2] = Search_Page;
        aactionbarstate[3] = Search_Result_Page;
        aactionbarstate[4] = Cart;
        aactionbarstate[5] = ProductImageGallery_Page;
        aactionbarstate[6] = FiltersPage;
        aactionbarstate[7] = NoActionBar;
        a = aactionbarstate;
    }
}
