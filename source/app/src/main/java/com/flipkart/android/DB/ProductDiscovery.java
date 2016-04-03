// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.DB;


public class ProductDiscovery
{

    private String a;
    private long b;
    private byte c[];
    private String d;
    private String e;
    private String f;

    public ProductDiscovery()
    {
    }

    public ProductDiscovery(String s, long l, byte abyte0[], String s1, String s2, String s3)
    {
        setMd5(s);
        setTime(l);
        setResponse(abyte0);
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
        return d;
    }

    public byte[] getResponse()
    {
        return c;
    }

    public String getStoreId()
    {
        return e;
    }

    public String getStoreName()
    {
        return f;
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
        d = s;
    }

    public void setResponse(byte abyte0[])
    {
        c = abyte0;
    }

    public void setStoreId(String s)
    {
        e = s;
    }

    public void setStoreName(String s)
    {
        f = s;
    }

    public void setTime(long l)
    {
        b = l;
    }
}
