// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.volley.request.seller.params;

import com.flipkart.android.utils.StringUtils;
import com.flipkart.logging.FkLogger;

public class SellerParam
{

    private String a;
    private int b;
    private int c;

    public SellerParam(String s, int i, int j)
    {
        a = s;
        b = i;
        c = j;
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
                stringbuilder.append("sellerId=").append(a).append("&");
                stringbuilder.append("start=").append(c).append("&");
                stringbuilder.append("count=").append(b);
                s1 = stringbuilder.toString();
            }
            catch (Exception exception)
            {
                FkLogger.printStackTrace(exception);
                return s;
            }
            s = s1;
        }
        return s;
    }

    public int getCount()
    {
        return b;
    }

    public String getSellerId()
    {
        return a;
    }

    public int getStart()
    {
        return c;
    }

    public void setCount(int i)
    {
        b = i;
    }

    public void setSellerId(String s)
    {
        a = s;
    }

    public void setStart(int i)
    {
        c = i;
    }
}
