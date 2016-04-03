// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.response.cart;

import java.util.ArrayList;

public class CartResponse
{

    private ArrayList items;

    public CartResponse()
    {
        items = new ArrayList();
    }

    public ArrayList getItems()
    {
        if (items == null)
        {
            items = new ArrayList();
        }
        return items;
    }

    public void setItems(ArrayList arraylist)
    {
        items = arraylist;
    }
}
