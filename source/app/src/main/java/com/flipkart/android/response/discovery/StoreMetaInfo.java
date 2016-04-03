// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.response.discovery;

import java.util.ArrayList;

public class StoreMetaInfo
{

    private ArrayList childMetaInfo;
    private String id;
    private String title;
    private int totalProduct;

    public StoreMetaInfo()
    {
        childMetaInfo = new ArrayList();
    }

    public ArrayList getChildMetaInfo()
    {
        if (childMetaInfo == null)
        {
            childMetaInfo = new ArrayList();
        }
        return childMetaInfo;
    }

    public String getId()
    {
        return id;
    }

    public String getTitle()
    {
        return title;
    }

    public int getTotalProduct()
    {
        return totalProduct;
    }

    public void setChildMetaInfo(ArrayList arraylist)
    {
        childMetaInfo = arraylist;
    }

    public void setId(String s)
    {
        id = s;
    }

    public void setTitle(String s)
    {
        title = s;
    }

    public void setTotalProduct(int i)
    {
        totalProduct = i;
    }
}
