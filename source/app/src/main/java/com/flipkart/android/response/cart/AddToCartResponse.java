// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.response.cart;

import java.util.ArrayList;

public class AddToCartResponse
{

    private boolean addedToCart;
    private String errorMessage;
    private ArrayList items;
    private String productId;

    public AddToCartResponse()
    {
        items = new ArrayList();
    }

    public String getErrorMessage()
    {
        return errorMessage;
    }

    public ArrayList getItems()
    {
        return items;
    }

    public String getProductId()
    {
        return productId;
    }

    public boolean isAddedToCart()
    {
        return addedToCart;
    }

    public void setAddedToCart(boolean flag)
    {
        addedToCart = flag;
    }

    public void setErrorMessage(String s)
    {
        errorMessage = s;
    }

    public void setItems(ArrayList arraylist)
    {
        items = arraylist;
    }

    public void setProductId(String s)
    {
        productId = s;
    }
}
