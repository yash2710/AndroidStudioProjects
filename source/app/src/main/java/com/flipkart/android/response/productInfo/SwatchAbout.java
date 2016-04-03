// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.response.productInfo;


public class SwatchAbout
{

    private String id;
    private String imageUrl;
    private boolean isAvailable;
    private boolean isBuyable;
    private String sellingPrice;

    public SwatchAbout()
    {
    }

    public String getId()
    {
        return id;
    }

    public String getImageUrl()
    {
        return imageUrl;
    }

    public String getSellingPrice()
    {
        return sellingPrice;
    }

    public boolean isAvailable()
    {
        return isAvailable;
    }

    public boolean isBuyable()
    {
        return isBuyable;
    }

    public void setAvailable(boolean flag)
    {
        isAvailable = flag;
    }

    public void setBuyable(boolean flag)
    {
        isBuyable = flag;
    }

    public void setId(String s)
    {
        id = s;
    }

    public void setImageUrl(String s)
    {
        imageUrl = s;
    }

    public void setSellingPrice(String s)
    {
        sellingPrice = s;
    }
}
