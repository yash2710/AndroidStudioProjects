// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.volley.request.recommendations.params;

import com.flipkart.android.utils.StringUtils;
import com.flipkart.logging.FkLogger;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

public class RecommendationsParams
{

    private ArrayList a;
    private int b;
    private String c;

    public RecommendationsParams(ArrayList arraylist, int i, String s)
    {
        a = arraylist;
        b = i;
        c = s;
    }

    public String arrayListToCommaSeparatedString(ArrayList arraylist)
    {
        if (arraylist == null)
        {
            return "";
        }
        StringBuilder stringbuilder = new StringBuilder();
        int i = arraylist.size();
        int j;
        for (j = 0; j < i - 1; j++)
        {
            stringbuilder.append((String)arraylist.get(j)).append(",");
        }

        stringbuilder.append((String)arraylist.get(j));
        return stringbuilder.toString();
    }

    public String generateURI()
    {
        StringBuilder stringbuilder = new StringBuilder();
        try
        {
            stringbuilder.append("?productIds").append("=").append(URLEncoder.encode(arrayListToCommaSeparatedString(a), "UTF-8"));
            if (b > 0)
            {
                stringbuilder.append("&maxItemsToFetch").append("=").append((new StringBuilder()).append(b).toString());
            }
            if (!StringUtils.isNullOrEmpty(c))
            {
                stringbuilder.append("&source").append("=").append(c);
            }
        }
        catch (UnsupportedEncodingException unsupportedencodingexception)
        {
            FkLogger.printStackTrace(unsupportedencodingexception);
            return "";
        }
        return stringbuilder.toString();
    }

    public int getMaxItemsToFetch()
    {
        return b;
    }

    public ArrayList getProdIds()
    {
        return a;
    }

    public String getSource()
    {
        return c;
    }

    public void setMaxItemsToFetch(int i)
    {
        b = i;
    }

    public void setProdId(ArrayList arraylist)
    {
        a = arraylist;
    }

    public void setSource(String s)
    {
        c = s;
    }
}
