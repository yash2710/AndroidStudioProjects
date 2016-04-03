// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.response.discovery;

import java.util.ArrayList;

public class GuideStoreTag
{

    private ArrayList stores;
    private String title;

    public GuideStoreTag()
    {
    }

    public ArrayList getStores()
    {
        return stores;
    }

    public String getTitle()
    {
        return title;
    }

    public void setStores(ArrayList arraylist)
    {
        stores = arraylist;
    }

    public void setTitle(String s)
    {
        title = s;
    }
}
