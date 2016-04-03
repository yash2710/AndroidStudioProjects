// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.response.wishlist;

import java.util.ArrayList;

public class WishListJsonResponse
{

    private String errorMessage;
    private boolean isSuccess;
    private ArrayList productIds;
    private int totalItems;

    public WishListJsonResponse()
    {
        productIds = new ArrayList();
    }

    public String getErrorMessage()
    {
        return errorMessage;
    }

    public ArrayList getProductIds()
    {
        if (productIds == null)
        {
            productIds = new ArrayList();
        }
        return productIds;
    }

    public int getTotalItems()
    {
        return totalItems;
    }

    public boolean isSuccess()
    {
        return isSuccess;
    }

    public void setErrorMessage(String s)
    {
        errorMessage = s;
    }

    public void setProductIds(ArrayList arraylist)
    {
        productIds = arraylist;
    }

    public void setSuccess(boolean flag)
    {
        isSuccess = flag;
    }

    public void setTotalItems(int i)
    {
        totalItems = i;
    }
}
