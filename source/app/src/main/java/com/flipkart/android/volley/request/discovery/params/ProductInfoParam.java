// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.volley.request.discovery.params;

import com.flipkart.android.utils.StringUtils;

public class ProductInfoParam
{

    private String a[];
    private String b[];
    private String c[];
    private String d;
    private int e;

    public ProductInfoParam(String as[], String as1[], String as2[], String s, int i)
    {
        e = 0;
        a = as;
        d = s;
        e = i;
        b = as1;
        c = as2;
    }

    private static boolean a(String as[])
    {
        int i = 0;
        do
        {
            if (i >= as.length)
            {
                break;
            }
            if (!StringUtils.isNullOrEmpty(as[i]))
            {
                if (as[i] != ",")
                {
                    return false;
                }
                break;
            }
            i++;
        } while (true);
        return true;
    }

    public String generateURI()
    {
        int i = 0;
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("/").append(e);
        stringbuilder.append("?");
        stringbuilder.append("pids=");
        String as[] = a;
        int j = as.length;
        for (int k = 0; k < j; k++)
        {
            stringbuilder.append(as[k]).append(",");
        }

        if (stringbuilder.charAt(-1 + stringbuilder.length()) == ',')
        {
            stringbuilder.deleteCharAt(-1 + stringbuilder.length());
        }
        if (b != null && b.length > 0 && !a(b))
        {
            stringbuilder.append("&");
            stringbuilder.append("lids=");
            String as2[] = b;
            int i1 = as2.length;
            int j1 = 0;
            while (j1 < i1) 
            {
                String s1 = as2[j1];
                if (s1 != null && !s1.equals(","))
                {
                    stringbuilder.append(s1).append(",");
                } else
                {
                    stringbuilder.append(",");
                }
                j1++;
            }
            if (stringbuilder.charAt(-1 + stringbuilder.length()) == ',')
            {
                stringbuilder.deleteCharAt(-1 + stringbuilder.length());
            }
        }
        if (c != null && c.length > 0 && !a(c))
        {
            stringbuilder.append("&");
            stringbuilder.append("offerIds=");
            String as1[] = c;
            int l = as1.length;
            while (i < l) 
            {
                String s = as1[i];
                if (s != null && !s.equals(","))
                {
                    stringbuilder.append(s).append(",");
                } else
                {
                    stringbuilder.append(",");
                }
                i++;
            }
            if (stringbuilder.charAt(-1 + stringbuilder.length()) == ',')
            {
                stringbuilder.deleteCharAt(-1 + stringbuilder.length());
            }
        }
        if (!StringUtils.isNullOrEmpty(d))
        {
            stringbuilder.append("&pin=").append(d);
        }
        return stringbuilder.toString();
    }

    public int getInfoLevel()
    {
        return e;
    }

    public String[] getListIds()
    {
        return b;
    }

    public String[] getOfferIds()
    {
        return c;
    }

    public String getPin()
    {
        return d;
    }

    public String[] getProductIds()
    {
        return a;
    }

    public void setInfoLevel(int i)
    {
        e = i;
    }

    public void setListIds(String as[])
    {
        b = as;
    }

    public void setOfferIds(String as[])
    {
        c = as;
    }

    public void setPin(String s)
    {
        d = s;
    }

    public void setProductIds(String as[])
    {
        a = as;
    }
}
