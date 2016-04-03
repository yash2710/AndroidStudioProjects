// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.utils;


public class b
{

    boolean a;
    private String b;
    private long c;
    private int d;

    public boolean equals(Object obj)
    {
        if (obj != null && (obj instanceof b))
        {
            b b1 = (b)obj;
            if (b.equals(b1.getProductId()))
            {
                return true;
            }
        }
        return false;
    }

    public int getInfoLevel()
    {
        return d;
    }

    public String getProductId()
    {
        return b;
    }

    public long getTs()
    {
        return c;
    }

    public boolean isSizeSelected()
    {
        return a;
    }

    public void setInfoLevel(int i)
    {
        d = i;
    }

    public void setProductId(String s)
    {
        b = s;
    }

    public void setSizeSelected(boolean flag)
    {
        a = flag;
    }

    public void setTs(long l)
    {
        c = l;
    }

    public ()
    {
    }

    public (String s)
    {
        b = s;
    }
}
