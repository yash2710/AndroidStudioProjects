// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.response.autoSuggest;

import java.util.ArrayList;

public class AutoSuggestInfo
{

    private ArrayList productList;
    private ArrayList stores;
    private ArrayList suggestions;
    private String word;

    public AutoSuggestInfo()
    {
        suggestions = new ArrayList();
        stores = new ArrayList();
        productList = new ArrayList();
    }

    public ArrayList getProductList()
    {
        if (productList == null)
        {
            productList = new ArrayList();
        }
        return productList;
    }

    public ArrayList getStores()
    {
        if (stores == null)
        {
            stores = new ArrayList();
        }
        return stores;
    }

    public ArrayList getSuggestions()
    {
        if (suggestions == null)
        {
            suggestions = new ArrayList();
        }
        return suggestions;
    }

    public String getWord()
    {
        return word;
    }

    public void setProductList(ArrayList arraylist)
    {
        productList = arraylist;
    }

    public void setStores(ArrayList arraylist)
    {
        stores = arraylist;
    }

    public void setSuggestions(ArrayList arraylist)
    {
        suggestions = arraylist;
    }

    public void setWord(String s)
    {
        word = s;
    }
}
