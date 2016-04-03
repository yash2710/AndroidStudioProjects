// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.response.discovery;


public class OmnitureData
{

    private String category;
    private String subCategory;
    private String superCategory;
    private String vertical;

    public OmnitureData()
    {
    }

    public String getCategory()
    {
        return category;
    }

    public String getSubCategory()
    {
        return subCategory;
    }

    public String getSuperCategory()
    {
        return superCategory;
    }

    public String getVertical()
    {
        return vertical;
    }

    public void setCategory(String s)
    {
        category = s;
    }

    public void setSubCategory(String s)
    {
        subCategory = s;
    }

    public void setSuperCategory(String s)
    {
        superCategory = s;
    }

    public void setVertical(String s)
    {
        vertical = s;
    }
}
