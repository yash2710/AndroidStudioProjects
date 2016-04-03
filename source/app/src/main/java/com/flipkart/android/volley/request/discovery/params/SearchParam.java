// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.volley.request.discovery.params;

import com.flipkart.android.utils.StringUtils;
import com.flipkart.logging.FkLogger;
import java.net.URLEncoder;

public class SearchParam
{

    private String a;
    private String b;
    private int c;
    private int d;
    private String e;
    private String f;
    private String g[];
    private String h[];
    private String i;
    private Boolean j;
    private int k;
    private String l[];

    public SearchParam(String s, String s1, String s2, int i1, int j1, String s3, String as[], 
            String as1[], String s4, int k1, boolean flag, String as2[])
    {
        k = 0;
        e = s;
        a = s1;
        c = i1;
        d = j1;
        f = s3;
        g = as;
        h = as1;
        i = s4;
        k = k1;
        b = s2;
        j = Boolean.valueOf(flag);
        l = as2;
    }

    public String generateURI()
    {
        StringBuilder stringbuilder;
        String s;
        stringbuilder = new StringBuilder();
        stringbuilder.append("?");
        int l1;
        String s1;
        if (!StringUtils.isNullOrEmpty(a))
        {
            try
            {
                stringbuilder.append("store=").append(a).append("&");
            }
            catch (Exception exception1)
            {
                FkLogger.printStackTrace(exception1);
            }
        } else
        {
            stringbuilder.append("store=search.flipkart.com").append("&");
        }
        if (!StringUtils.isNullOrEmpty(e))
        {
            stringbuilder.append("pincode=").append(e).append("&");
        }
        if (f == null || f.length() <= 0) goto _L2; else goto _L1
_L1:
        s1 = URLEncoder.encode(f, "utf-8");
        s = s1;
_L4:
        stringbuilder.append("q=").append(s).append("&");
_L2:
        if (g != null && g.length > 0)
        {
            for (l1 = 0; l1 < g.length; l1++)
            {
                if (!StringUtils.isNullOrEmpty(g[l1]))
                {
                    stringbuilder.append(g[l1]).append("&");
                }
            }

        }
        if (!StringUtils.isNullOrEmpty(i))
        {
            stringbuilder.append(i).append("&");
        }
        if (h != null && h.length > 0)
        {
            for (int k1 = 0; k1 < h.length; k1++)
            {
                if (!StringUtils.isNullOrEmpty(h[k1]))
                {
                    stringbuilder.append(h[k1]).append("&");
                }
            }

        }
        if (l != null)
        {
            int i1 = l.length;
            int j1 = 0;
            if (i1 > 0)
            {
                for (; j1 < l.length; j1++)
                {
                    if (!StringUtils.isNullOrEmpty(l[j1]))
                    {
                        stringbuilder.append(l[j1]).append("&");
                    }
                }

            }
        }
        if (k != 0)
        {
            stringbuilder.append("productInfoCount=").append(k).append("&");
        }
        stringbuilder.append("start=").append(d).append("&");
        stringbuilder.append("count=").append(c);
        if (j.booleanValue())
        {
            stringbuilder.append("&augment=true");
        } else
        {
            stringbuilder.append("&augment=false");
        }
        return stringbuilder.toString();
        Exception exception;
        exception;
        s = null;
        if (true) goto _L4; else goto _L3
_L3:
    }

    public int getCount()
    {
        return c;
    }

    public Boolean getEnableAugmentedSearch()
    {
        return j;
    }

    public String[] getFilter()
    {
        return g;
    }

    public String getPincode()
    {
        return e;
    }

    public int getProductCount()
    {
        return k;
    }

    public String getQuery()
    {
        return f;
    }

    public String getSort()
    {
        return i;
    }

    public int getStart()
    {
        return d;
    }

    public String getStoreId()
    {
        return a;
    }

    public String getStoreName()
    {
        return b;
    }

    public String[] getTag()
    {
        return h;
    }

    public String[] getView()
    {
        return l;
    }

    public void setCount(int i1)
    {
        c = i1;
    }

    public void setEnableAugmentedSearch(Boolean boolean1)
    {
        j = boolean1;
    }

    public void setFilter(String as[])
    {
        g = as;
    }

    public void setPincode(String s)
    {
        e = s;
    }

    public void setProductCount(int i1)
    {
        k = i1;
    }

    public void setQuery(String s)
    {
        f = s;
    }

    public void setSort(String s)
    {
        i = s;
    }

    public void setStart(int i1)
    {
        d = i1;
    }

    public void setStoreId(String s)
    {
        a = s;
    }

    public void setStoreName(String s)
    {
        b = s;
    }

    public void setTag(String as[])
    {
        h = as;
    }

    public void setView(String as[])
    {
        l = as;
    }
}
