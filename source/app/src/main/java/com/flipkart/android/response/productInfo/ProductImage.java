// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.response.productInfo;


public class ProductImage
{

    private int actualHeight;
    private int actualWidth;
    private int expectedHeight;
    private int expectedWidth;
    private String imageId;
    private String url;

    public ProductImage()
    {
    }

    public int getActualHeight()
    {
        return actualHeight;
    }

    public int getActualWidth()
    {
        return actualWidth;
    }

    public int getExpectedHeight()
    {
        return expectedHeight;
    }

    public int getExpectedWidth()
    {
        return expectedWidth;
    }

    public String getImageId()
    {
        return imageId;
    }

    public String getUrl()
    {
        return url;
    }

    public void setActualHeight(int i)
    {
        actualHeight = i;
    }

    public void setActualWidth(int i)
    {
        actualWidth = i;
    }

    public void setExpectedHeight(int i)
    {
        expectedHeight = i;
    }

    public void setExpectedWidth(int i)
    {
        expectedWidth = i;
    }

    public void setImageId(String s)
    {
        imageId = s;
    }

    public void setUrl(String s)
    {
        url = s;
    }
}
