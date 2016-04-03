// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing.client.android;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public final class LocaleManager
{

    private static final Map a;
    private static final Map b;
    private static final Map c;
    private static final Collection d = Arrays.asList(new String[] {
        "de", "en", "es", "fr", "it", "ja", "ko", "nl", "pt", "ru", 
        "zh-rCN", "zh-rTW"
    });

    private LocaleManager()
    {
    }

    private static String a()
    {
        Locale locale = Locale.getDefault();
        if (locale == null)
        {
            return "US";
        } else
        {
            return locale.getCountry();
        }
    }

    private static String a(Map map, Context context)
    {
        String s = (String)map.get(getCountry(context));
        if (s == null)
        {
            s = "com";
        }
        return s;
    }

    public static String getBookSearchCountryTLD(Context context)
    {
        return a(c, context);
    }

    public static String getCountry(Context context)
    {
        String s = PreferenceManager.getDefaultSharedPreferences(context).getString("preferences_search_country", null);
        if (s != null && s.length() > 0 && !"-".equals(s))
        {
            return s;
        } else
        {
            return a();
        }
    }

    public static String getCountryTLD(Context context)
    {
        return a(a, context);
    }

    public static String getProductSearchCountryTLD(Context context)
    {
        return a(b, context);
    }

    public static String getTranslatedAssetLanguage()
    {
        Locale locale = Locale.getDefault();
        String s;
        if (locale == null)
        {
            s = "en";
        } else
        {
            s = locale.getLanguage();
            if (Locale.SIMPLIFIED_CHINESE.getLanguage().equals(s))
            {
                s = (new StringBuilder()).append(s).append("-r").append(a()).toString();
            }
        }
        if (d.contains(s))
        {
            return s;
        } else
        {
            return "en";
        }
    }

    public static boolean isBookSearchUrl(String s)
    {
        return s.startsWith("http://google.com/books") || s.startsWith("http://books.google.");
    }

    static 
    {
        HashMap hashmap = new HashMap();
        a = hashmap;
        hashmap.put("AR", "com.ar");
        a.put("AU", "com.au");
        a.put("BR", "com.br");
        a.put("BG", "bg");
        a.put(Locale.CANADA.getCountry(), "ca");
        a.put(Locale.CHINA.getCountry(), "cn");
        a.put("CZ", "cz");
        a.put("DK", "dk");
        a.put("FI", "fi");
        a.put(Locale.FRANCE.getCountry(), "fr");
        a.put(Locale.GERMANY.getCountry(), "de");
        a.put("GR", "gr");
        a.put("HU", "hu");
        a.put("ID", "co.id");
        a.put("IL", "co.il");
        a.put(Locale.ITALY.getCountry(), "it");
        a.put(Locale.JAPAN.getCountry(), "co.jp");
        a.put(Locale.KOREA.getCountry(), "co.kr");
        a.put("NL", "nl");
        a.put("PL", "pl");
        a.put("PT", "pt");
        a.put("RU", "ru");
        a.put("SK", "sk");
        a.put("SI", "si");
        a.put("ES", "es");
        a.put("SE", "se");
        a.put("CH", "ch");
        a.put(Locale.TAIWAN.getCountry(), "tw");
        a.put("TR", "com.tr");
        a.put(Locale.UK.getCountry(), "co.uk");
        a.put(Locale.US.getCountry(), "com");
        HashMap hashmap1 = new HashMap();
        b = hashmap1;
        hashmap1.put("AU", "com.au");
        b.put(Locale.FRANCE.getCountry(), "fr");
        b.put(Locale.GERMANY.getCountry(), "de");
        b.put(Locale.ITALY.getCountry(), "it");
        b.put(Locale.JAPAN.getCountry(), "co.jp");
        b.put("NL", "nl");
        b.put("ES", "es");
        b.put("CH", "ch");
        b.put(Locale.UK.getCountry(), "co.uk");
        b.put(Locale.US.getCountry(), "com");
        c = a;
    }
}
