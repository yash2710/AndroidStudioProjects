// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.volley.request.UGC.params;

import com.flipkart.android.utils.StringUtils;
import com.flipkart.logging.FkLogger;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class UGCParam
{

    private String a;
    private String b;
    private int c;
    private int d;

    public UGCParam(String s, String s1, int i, int j)
    {
        a = s;
        b = s1;
        c = i;
        d = j;
    }

    public String generateURI()
    {
        StringBuilder stringbuilder = new StringBuilder();
        String s = "";
        stringbuilder.append("?");
        if (!StringUtils.isNullOrEmpty(a))
        {
            String s1;
            try
            {
                stringbuilder.append("ids=").append(URLEncoder.encode(a, "UTF-8"));
                stringbuilder.append("&start=").append(c);
                stringbuilder.append("&count=").append(d);
                if (!StringUtils.isNullOrEmpty(b))
                {
                    stringbuilder.append("&sortOrder=").append(b);
                }
                s1 = stringbuilder.toString();
            }
            catch (UnsupportedEncodingException unsupportedencodingexception)
            {
                FkLogger.printStackTrace(unsupportedencodingexception);
                return s;
            }
            s = s1;
        }
        return s;
    }

    public int getCount()
    {
        return d;
    }

    public String getPid()
    {
        return a;
    }

    public String getSortOption()
    {
        return b;
    }

    public int getStart()
    {
        return c;
    }

    public void setCount(int i)
    {
        d = i;
    }

    public void setPid(String s)
    {
        a = s;
    }

    public void setSortOption(String s)
    {
        b = s;
    }

    public void setStart(int i)
    {
        c = i;
    }
}
