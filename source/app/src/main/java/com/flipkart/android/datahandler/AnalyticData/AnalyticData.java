// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.datahandler.AnalyticData;

import com.flipkart.android.utils.PageTypeUtils;
import java.util.HashMap;
import java.util.Map;

public class AnalyticData
{

    private String a;
    private PageTypeUtils b;
    private String c;
    private Map d;

    public AnalyticData()
    {
        a = null;
        b = null;
        c = null;
        d = null;
        d = new HashMap();
    }

    public AnalyticData(String s, String s1)
    {
        a = null;
        b = null;
        c = null;
        d = null;
        a = s;
        d = new HashMap();
    }

    public AnalyticData(String s, String s1, PageTypeUtils pagetypeutils)
    {
        a = null;
        b = null;
        c = null;
        d = null;
        a = s;
        b = pagetypeutils;
        d = new HashMap();
        if (s != null)
        {
            d.put("REQUEST_ID_REFERRAL", s);
        }
        if (c != null)
        {
            d.put("X-SEARCH-TYPE", c);
        }
        if (pagetypeutils != null)
        {
            d.put("X-REFERRER-PAGE-TYPE", pagetypeutils.toString());
        }
    }

    public Map getAnalyticDataMap()
    {
        if (d == null)
        {
            d = new HashMap();
        }
        return d;
    }

    public PageTypeUtils getPageTypeUtils()
    {
        return b;
    }

    public String getRequestId()
    {
        return a;
    }

    public String getSearchType()
    {
        return c;
    }

    public void removeIsPageFirstLanding()
    {
        if (d.containsKey("X-PAGE-FIRST-LANDING"))
        {
            d.remove("X-PAGE-FIRST-LANDING");
        }
    }

    public void removeIsUserClick()
    {
        if (d.containsKey("X-USER-ACTION"))
        {
            d.remove("X-USER-ACTION");
        }
    }

    public void setAnalyticDataMap(Map map)
    {
        d = map;
    }

    public void setIsPageFirstLanding(boolean flag)
    {
        d.put("X-PAGE-FIRST-LANDING", (new StringBuilder()).append(flag).toString());
    }

    public void setIsUserClick(boolean flag)
    {
        d.put("X-USER-ACTION", (new StringBuilder()).append(flag).toString());
    }

    public void setPageTypeUtils(PageTypeUtils pagetypeutils)
    {
        if (pagetypeutils != null)
        {
            d.put("X-REFERRER-PAGE-TYPE", pagetypeutils.toString());
        }
        b = pagetypeutils;
    }

    public void setPageTypeUtilsFromString(String s)
    {
        PageTypeUtils pagetypeutils = PageTypeUtils.valueOf(s);
        if (pagetypeutils != null)
        {
            d.put("X-REFERRER-PAGE-TYPE", pagetypeutils.toString());
        }
        b = pagetypeutils;
    }

    public void setRequestId(String s)
    {
        if (s != null)
        {
            d.put("REQUEST_ID_REFERRAL", s);
        }
        a = s;
    }

    public void setSearchType(String s)
    {
        if (s != null)
        {
            d.put("X-SEARCH-TYPE", s);
            d.put("X-SEARCH-WIDGET-TYPE", "UNIVERSAL");
        }
        c = s;
    }
}
