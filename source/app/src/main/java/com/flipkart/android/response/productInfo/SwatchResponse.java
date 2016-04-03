// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.response.productInfo;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class SwatchResponse
{

    private ArrayList soldOut;
    private Map swatchAbout;
    private String swatchAttribute;
    private String swatchType;
    private String swatchValue;

    public SwatchResponse()
    {
        soldOut = new ArrayList();
        swatchAbout = new LinkedHashMap();
    }

    public List getSoldOut()
    {
        if (soldOut == null)
        {
            soldOut = new ArrayList();
        }
        return soldOut;
    }

    public Map getSwatchAbout()
    {
        if (swatchAbout == null)
        {
            swatchAbout = new LinkedHashMap();
        }
        return swatchAbout;
    }

    public String getSwatchAttribute()
    {
        return swatchAttribute;
    }

    public String getSwatchType()
    {
        return swatchType;
    }

    public String getSwatchValue()
    {
        return swatchValue;
    }

    public void setSoldOut(ArrayList arraylist)
    {
        soldOut = arraylist;
    }

    public void setSwatchAbout(Map map)
    {
        swatchAbout = map;
    }

    public void setSwatchAttribute(String s)
    {
        swatchAttribute = s;
    }

    public void setSwatchType(String s)
    {
        swatchType = s;
    }

    public void setSwatchValue(String s)
    {
        swatchValue = s;
    }
}
