// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.response.discovery;

import java.util.ArrayList;

public class StoreSearchResultResponse
{

    private int numberOfProduct;
    private ArrayList productIds;

    public StoreSearchResultResponse()
    {
        productIds = new ArrayList();
    }

    public int getNumberOfProduct()
    {
        return numberOfProduct;
    }

    public ArrayList getProductIds()
    {
        if (productIds == null)
        {
            productIds = new ArrayList();
        }
        return productIds;
    }

    public void setNumberOfProduct(int i)
    {
        numberOfProduct = i;
    }

    public void setProductIds(ArrayList arraylist)
    {
        productIds = arraylist;
    }
}
