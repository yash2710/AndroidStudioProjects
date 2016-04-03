// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.utils;

import java.util.ArrayList;

public class ProductPageReviewContext
{

    private ArrayList a;
    private String b;
    private String c;
    private long d;

    public ProductPageReviewContext()
    {
        a = new ArrayList();
        b = "MOST_USEFUL";
    }

    public String getOption()
    {
        return b;
    }

    public String getProductId()
    {
        return c;
    }

    public ArrayList getReviewList()
    {
        return a;
    }

    public long getTotalReviewCount()
    {
        return d;
    }

    public void setOption(String s)
    {
        b = s;
    }

    public void setProductId(String s)
    {
        c = s;
    }

    public void setReviewList(ArrayList arraylist)
    {
        a = arraylist;
    }

    public void setTotalReviewCount(long l)
    {
        d = l;
    }
}
