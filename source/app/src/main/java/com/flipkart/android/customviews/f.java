// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.customviews;

import com.flipkart.android.customviews.enums.ActionBarState;

final class f
{

    static final int a[];

    static 
    {
        a = new int[ActionBarState.values().length];
        try
        {
            a[ActionBarState.Home.ordinal()] = 1;
        }
        catch (NoSuchFieldError nosuchfielderror) { }
        try
        {
            a[ActionBarState.Default_Back.ordinal()] = 2;
        }
        catch (NoSuchFieldError nosuchfielderror1) { }
        try
        {
            a[ActionBarState.Search_Page.ordinal()] = 3;
        }
        catch (NoSuchFieldError nosuchfielderror2) { }
        try
        {
            a[ActionBarState.Search_Result_Page.ordinal()] = 4;
        }
        catch (NoSuchFieldError nosuchfielderror3) { }
        try
        {
            a[ActionBarState.Cart.ordinal()] = 5;
        }
        catch (NoSuchFieldError nosuchfielderror4) { }
        try
        {
            a[ActionBarState.ProductImageGallery_Page.ordinal()] = 6;
        }
        catch (NoSuchFieldError nosuchfielderror5) { }
        try
        {
            a[ActionBarState.FiltersPage.ordinal()] = 7;
        }
        catch (NoSuchFieldError nosuchfielderror6) { }
        try
        {
            a[ActionBarState.NoActionBar.ordinal()] = 8;
        }
        catch (NoSuchFieldError nosuchfielderror7)
        {
            return;
        }
    }
}
