// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.response.appconfig;

import com.flipkart.android.response.component.layout.LayoutDetails;

public class AppTheme
{

    private LayoutDetails actionBar;
    private LayoutDetails browsePage;
    private LayoutDetails multiWidgetFragment;
    private LayoutDetails productPage;
    private LayoutDetails sponsorImage;

    public AppTheme()
    {
    }

    public LayoutDetails getActionBar()
    {
        return actionBar;
    }

    public LayoutDetails getBrowsePage()
    {
        return browsePage;
    }

    public LayoutDetails getMultiWidgetFragment()
    {
        return multiWidgetFragment;
    }

    public LayoutDetails getProductPage()
    {
        return productPage;
    }

    public LayoutDetails getSponsorImage()
    {
        return sponsorImage;
    }

    public void setActionBar(LayoutDetails layoutdetails)
    {
        actionBar = layoutdetails;
    }

    public void setBrowsePage(LayoutDetails layoutdetails)
    {
        browsePage = layoutdetails;
    }

    public void setMultiWidgetFragment(LayoutDetails layoutdetails)
    {
        multiWidgetFragment = layoutdetails;
    }

    public void setProductPage(LayoutDetails layoutdetails)
    {
        productPage = layoutdetails;
    }

    public void setSponsorImage(LayoutDetails layoutdetails)
    {
        sponsorImage = layoutdetails;
    }
}
