// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.datahandler.param;


public class ComponentDataParams
{

    private String a;
    private String b;
    private long c;
    private int d;

    public ComponentDataParams(String s, String s1, long l)
    {
        a = s;
        b = s1;
        c = l;
    }

    public String getDataId()
    {
        return b;
    }

    public int getHashCode()
    {
        return d;
    }

    public String getScreenName()
    {
        return a;
    }

    public long getTtl()
    {
        return c;
    }

    public void setDataId(String s)
    {
        b = s;
    }

    public void setHashCode(int i)
    {
        d = i;
    }

    public void setScreenName(String s)
    {
        a = s;
    }

    public void setTtl(long l)
    {
        c = l;
    }
}
