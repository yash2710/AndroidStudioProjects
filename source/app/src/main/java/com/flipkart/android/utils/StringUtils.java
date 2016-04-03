// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.utils;

import android.text.SpannableString;
import android.text.style.URLSpan;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class StringUtils
{

    public StringUtils()
    {
    }

    public static transient String arrayToCSV(String as[])
    {
        StringBuilder stringbuilder = new StringBuilder();
        int i = as.length;
        for (int j = 0; j < i; j++)
        {
            stringbuilder.append(as[j]);
            stringbuilder.append(",");
        }

        if (stringbuilder.length() > 0)
        {
            return stringbuilder.substring(0, -1 + stringbuilder.length());
        } else
        {
            return "";
        }
    }

    public static String getHtmlTextWithCss(String s)
    {
        return (new StringBuilder("<html><head><style type=\"text/css\">body{color: #767676; background-color: #fff;font-family:Roboto.Light}</style></head><body><div style=\"position:relative;right:7%\">")).append(s).append("</div></body></html>").toString();
    }

    public static SpannableString getHyperLinkedText(String s)
    {
        SpannableString spannablestring = new SpannableString(s);
        spannablestring.setSpan(new URLSpan(""), 0, s.length(), 33);
        return spannablestring;
    }

    public static boolean isHttps(String s)
    {
        return s.startsWith("https");
    }

    public static boolean isHttps(URL url)
    {
        return url.toString().startsWith("https");
    }

    public static boolean isNull(Object obj)
    {
        return obj == null;
    }

    public static boolean isNullOrEmpty(String s)
    {
        return s == null || s.length() == 0 || s.equalsIgnoreCase("null");
    }

    public static boolean isNullOrEmpty(ArrayList arraylist)
    {
        return arraylist == null || arraylist.size() == 0;
    }

    public static boolean isNullOrEmpty(List list)
    {
        return list == null || list.size() == 0;
    }

    public static boolean isNullOrEmpty(Map map)
    {
        return map == null || map.size() == 0;
    }

    public static boolean isValidIndianPin(String s)
    {
        while (s == null || s.length() != 6 || !s.matches("^[1-9][0-9]+")) 
        {
            return false;
        }
        return true;
    }

    public static StringBuilder join(ArrayList arraylist, String s)
    {
        StringBuilder stringbuilder = new StringBuilder();
        if (arraylist != null)
        {
            for (Iterator iterator = arraylist.iterator(); iterator.hasNext(); stringbuilder.append((String)iterator.next()).append(s)) { }
        }
        return stringbuilder;
    }

    public static Map splitIntoMap(String s, String s1, String s2)
    {
        HashMap hashmap;
        String s3;
        hashmap = new HashMap();
        s3 = "";
        String s5 = URLDecoder.decode(s, "UTF-8");
        s3 = s5;
_L2:
        String as[] = s3.split(s1);
        int i = as.length;
        for (int j = 0; j < i; j++)
        {
            String s4 = as[j];
            if (!s4.contains(s2))
            {
                continue;
            }
            String as1[] = s4.split(s2);
            if (as1.length == 2)
            {
                hashmap.put(as1[0], as1[1]);
            }
        }

        return hashmap;
        Exception exception;
        exception;
        if (true) goto _L2; else goto _L1
_L1:
    }

    public static String trim(String s, String s1)
    {
        for (; s.endsWith(s1); s = s.substring(0, s.length() - s1.length())) { }
        for (; s.startsWith(s1); s = s.substring(s1.length(), s.length())) { }
        return s;
    }
}
