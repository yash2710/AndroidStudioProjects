// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

// Referenced classes of package com.flipkart.android.utils:
//            StringUtils

public class UrlUtils
{

    public UrlUtils()
    {
    }

    public static String addGetParameter(String s, String s1, String s2)
    {
        if (StringUtils.isNullOrEmpty(s2) || StringUtils.isNullOrEmpty(s1) || StringUtils.isNullOrEmpty(s))
        {
            return s;
        }
        String s3;
        String s4;
        String s5;
        try
        {
            s3 = URLEncoder.encode(s2, "UTF-8");
            s4 = URLEncoder.encode(s1, "UTF-8");
        }
        catch (UnsupportedEncodingException unsupportedencodingexception)
        {
            return s;
        }
        if (s.contains("?"))
        {
            s5 = (new StringBuilder()).append(s).append("&").toString();
        } else
        {
            s5 = (new StringBuilder()).append(s).append("?").toString();
        }
        return (new StringBuilder()).append(s5).append(s4).append("=").append(s3).toString();
    }

    public static String addPostParameter(String s, String s1, String s2)
    {
        if (StringUtils.isNullOrEmpty(s1) || StringUtils.isNullOrEmpty(s2))
        {
            return s;
        }
        String s3;
        String s4;
        String s5;
        try
        {
            s3 = URLEncoder.encode(s2, "UTF-8");
            s4 = URLEncoder.encode(s1, "UTF-8");
        }
        catch (UnsupportedEncodingException unsupportedencodingexception)
        {
            return s;
        }
        s5 = (new StringBuilder()).append(s4).append("=").append(s3).toString();
        if (StringUtils.isNullOrEmpty(s))
        {
            return s5;
        } else
        {
            return (new StringBuilder()).append(s).append("&").append(s5).toString();
        }
    }

    public static String getFullUrl(String s, String s1)
    {
        if (StringUtils.isNullOrEmpty(s1))
        {
            return s;
        }
        if (StringUtils.isNullOrEmpty(s))
        {
            return "";
        }
        if (s.endsWith("/"))
        {
            s = s.substring(0, -1 + s.length());
        }
        if (s1.startsWith("/"))
        {
            s1 = s1.substring(1, s1.length());
        }
        return (new StringBuilder()).append(s).append("/").append(s1).toString();
    }

    public static String removeParamsFromUri(String s)
    {
        return s.substring(0, s.indexOf("?"));
    }
}
