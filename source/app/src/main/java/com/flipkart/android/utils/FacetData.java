// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.utils;


public class FacetData
{

    private boolean a;
    private String b;
    private String c;
    private int d;
    private String e;
    private String f;

    public FacetData()
    {
    }

    public int getCount()
    {
        return d;
    }

    public String getOfferDescription()
    {
        return f;
    }

    public String getOfferId()
    {
        return e;
    }

    public String getParams()
    {
        return c;
    }

    public String getTitle()
    {
        return b;
    }

    public boolean isSelected()
    {
        return a;
    }

    public void setCount(int i)
    {
        d = i;
    }

    public void setOfferDescription(String s)
    {
        f = s;
    }

    public void setOfferId(String s)
    {
        e = s;
    }

    public void setParams(String s)
    {
        c = s;
    }

    public void setSelected(boolean flag)
    {
        a = flag;
    }

    public void setTitle(String s)
    {
        b = s;
    }
}
