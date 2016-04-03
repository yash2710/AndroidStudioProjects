// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.response;

import java.util.ArrayList;

public class GuidedSearchTag
{

    private String filterParams[];
    private ArrayList searchKeywords;
    private String storePath;
    private String title;

    public GuidedSearchTag()
    {
    }

    public String[] getFilterParams()
    {
        return filterParams;
    }

    public ArrayList getSearchKeywords()
    {
        return searchKeywords;
    }

    public String getStorePath()
    {
        return storePath;
    }

    public String getTitle()
    {
        return title;
    }

    public void setFilterParams(String as[])
    {
        filterParams = as;
    }

    public void setSearchKeywords(ArrayList arraylist)
    {
        searchKeywords = arraylist;
    }

    public void setStorePath(String s)
    {
        storePath = s;
    }

    public void setTitle(String s)
    {
        title = s;
    }
}
