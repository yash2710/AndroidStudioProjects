// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.DB;


public class WishList
{

    private String a;
    private long b;

    public WishList()
    {
    }

    public WishList(String s, long l)
    {
        setPid(s);
        setTime(l);
    }

    public String getPid()
    {
        return a;
    }

    public long getTime()
    {
        return b;
    }

    public void setPid(String s)
    {
        a = s;
    }

    public void setTime(long l)
    {
        b = l;
    }
}
