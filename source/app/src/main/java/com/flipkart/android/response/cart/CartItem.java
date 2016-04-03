// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.response.cart;


public class CartItem
{

    private String listId;
    private String pid;

    public CartItem()
    {
    }

    public CartItem(String s, String s1)
    {
        pid = s;
        listId = s1;
    }

    public String getListId()
    {
        return listId;
    }

    public String getPid()
    {
        return pid;
    }

    public void setListId(String s)
    {
        listId = s;
    }

    public void setPid(String s)
    {
        pid = s;
    }
}
