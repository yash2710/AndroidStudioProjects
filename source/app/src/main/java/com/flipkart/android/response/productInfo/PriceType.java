// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.response.productInfo;


public class PriceType
{

    private String currency;
    private String displayName;
    private boolean isFinal;
    private String price;

    public PriceType()
    {
    }

    public String getCurrency()
    {
        return currency;
    }

    public String getDisplayName()
    {
        return displayName;
    }

    public String getPrice()
    {
        return price;
    }

    public boolean isFinal()
    {
        return isFinal;
    }

    public void setCurrency(String s)
    {
        currency = s;
    }

    public void setDisplayName(String s)
    {
        displayName = s;
    }

    public void setFinal(boolean flag)
    {
        isFinal = flag;
    }

    public void setPrice(String s)
    {
        price = s;
    }
}
