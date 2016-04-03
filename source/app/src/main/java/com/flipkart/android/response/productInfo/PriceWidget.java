// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.response.productInfo;

import java.util.ArrayList;

public class PriceWidget
{

    private String discount;
    private String emi;
    private String emiUrl;
    private ArrayList prices;

    public PriceWidget()
    {
    }

    public String getDiscount()
    {
        return discount;
    }

    public String getEmi()
    {
        return emi;
    }

    public String getEmiUrl()
    {
        return emiUrl;
    }

    public ArrayList getPrices()
    {
        return prices;
    }

    public void setDiscount(String s)
    {
        discount = s;
    }

    public void setEmi(String s)
    {
        emi = s;
    }

    public void setEmiUrl(String s)
    {
        emiUrl = s;
    }

    public void setPrices(ArrayList arraylist)
    {
        prices = arraylist;
    }
}
