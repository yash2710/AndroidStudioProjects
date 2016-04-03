// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.response.android.widget;

import java.util.ArrayList;

public class AndroidWidgetActionParams
{

    private String facet;
    private ArrayList pids;
    private String query;
    private String screenName;
    private String sort;
    private String storeId;
    private String tag;
    private String url;

    public AndroidWidgetActionParams()
    {
        pids = new ArrayList();
    }

    public String getFacet()
    {
        return facet;
    }

    public ArrayList getPids()
    {
        return pids;
    }

    public String getQuery()
    {
        return query;
    }

    public String getScreenName()
    {
        return screenName;
    }

    public String getSort()
    {
        return sort;
    }

    public String getStoreId()
    {
        return storeId;
    }

    public String getTag()
    {
        return tag;
    }

    public String getUrl()
    {
        return url;
    }

    public void setFacet(String s)
    {
        facet = s;
    }

    public void setPids(ArrayList arraylist)
    {
        pids = arraylist;
    }

    public void setQuery(String s)
    {
        query = s;
    }

    public void setScreenName(String s)
    {
        screenName = s;
    }

    public void setSort(String s)
    {
        sort = s;
    }

    public void setStoreId(String s)
    {
        storeId = s;
    }

    public void setTag(String s)
    {
        tag = s;
    }

    public void setUrl(String s)
    {
        url = s;
    }
}
