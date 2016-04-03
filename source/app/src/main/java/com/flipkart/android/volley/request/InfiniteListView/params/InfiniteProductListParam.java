// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.volley.request.InfiniteListView.params;

import com.flipkart.android.utils.StringUtils;

public class InfiniteProductListParam
{

    private int a;
    private int b;
    private String c;

    public InfiniteProductListParam(int i, int j, String s)
    {
        a = i;
        b = j;
        c = s;
    }

    public String generateURI()
    {
        StringBuilder stringbuilder = new StringBuilder();
        if (!StringUtils.isNullOrEmpty(c))
        {
            if (!StringUtils.isNullOrEmpty(c))
            {
                stringbuilder.append(c);
            }
            if (a >= 0)
            {
                stringbuilder.append("&start=").append(a);
            }
            if (b > 0)
            {
                stringbuilder.append("&count=").append(b);
                return stringbuilder.toString();
            }
        }
        return " ";
    }

    public String getBaseURL()
    {
        return c;
    }

    public int getCount()
    {
        return b;
    }

    public int getStartIndex()
    {
        return a;
    }

    public void setBaseURL(String s)
    {
        c = s;
    }

    public void setCount(int i)
    {
        b = i;
    }

    public void setStartIndex(int i)
    {
        a = i;
    }
}
