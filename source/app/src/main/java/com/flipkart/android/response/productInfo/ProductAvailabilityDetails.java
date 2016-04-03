// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.response.productInfo;


public class ProductAvailabilityDetails
{

    private String ShippingText;
    private String availabilityStatus;
    private String availabilityStatusIntent;
    private String availabilityStatusMessage;
    private boolean enableCheckout;
    private boolean showBookNow;
    private boolean showBuyButton;
    private boolean showNotifyme;
    private boolean showPreorder;
    private boolean showStatus;

    public ProductAvailabilityDetails()
    {
    }

    public String getAvailabilityStatus()
    {
        return availabilityStatus;
    }

    public String getAvailabilityStatusIntent()
    {
        return availabilityStatusIntent;
    }

    public String getAvailabilityStatusMessage()
    {
        return availabilityStatusMessage;
    }

    public String getShippingText()
    {
        return ShippingText;
    }

    public boolean isEnableCheckout()
    {
        return enableCheckout;
    }

    public boolean isShowBookNow()
    {
        return showBookNow;
    }

    public boolean isShowBuyButton()
    {
        return showBuyButton;
    }

    public boolean isShowNotifyme()
    {
        return showNotifyme;
    }

    public boolean isShowPreorder()
    {
        return showPreorder;
    }

    public boolean isShowStatus()
    {
        return showStatus;
    }

    public void setAvailabilityStatus(String s)
    {
        availabilityStatus = s;
    }

    public void setAvailabilityStatusIntent(String s)
    {
        availabilityStatusIntent = s;
    }

    public void setAvailabilityStatusMessage(String s)
    {
        availabilityStatusMessage = s;
    }

    public void setEnableCheckout(boolean flag)
    {
        enableCheckout = flag;
    }

    public void setShippingText(String s)
    {
        ShippingText = s;
    }

    public void setShowBookNow(boolean flag)
    {
        showBookNow = flag;
    }

    public void setShowBuyButton(boolean flag)
    {
        showBuyButton = flag;
    }

    public void setShowNotifyme(boolean flag)
    {
        showNotifyme = flag;
    }

    public void setShowPreorder(boolean flag)
    {
        showPreorder = flag;
    }

    public void setShowStatus(boolean flag)
    {
        showStatus = flag;
    }
}
