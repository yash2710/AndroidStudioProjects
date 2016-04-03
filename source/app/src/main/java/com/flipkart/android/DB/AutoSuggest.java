// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.DB;


public class AutoSuggest
{

    private String a;
    private long b;
    private String c;
    private String d;
    private String e;

    public AutoSuggest()
    {
    }

    public AutoSuggest(String s, long l, String s1, String s2, String s3)
    {
        setMd5(s);
        setTime(l);
        setQuery(s1);
        setStoreId(s2);
        setStoreName(s3);
    }

    public String getMd5()
    {
        return a;
    }

    public String getQuery()
    {
        return c;
    }

    public String getStoreId()
    {
        return d;
    }

    public String getStoreName()
    {
        return e;
    }

    public long getTime()
    {
        return b;
    }

    public void setMd5(String s)
    {
        a = s;
    }

    public void setQuery(String s)
    {
        c = s;
    }

    public void setStoreId(String s)
    {
        d = s;
    }

    public void setStoreName(String s)
    {
        e = s;
    }

    public void setTime(long l)
    {
        b = l;
    }
}
