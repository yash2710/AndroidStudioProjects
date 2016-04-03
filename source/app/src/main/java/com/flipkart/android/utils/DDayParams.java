// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.utils;


public class DDayParams
{

    private long a;
    private long b;
    private boolean c;

    public DDayParams()
    {
    }

    public long getEndTime()
    {
        return b;
    }

    public long getStartTime()
    {
        return a;
    }

    public boolean isDDay()
    {
        return c;
    }

    public void setDDay(boolean flag)
    {
        c = flag;
    }

    public void setEndTime(long l)
    {
        b = l;
    }

    public void setStartTime(long l)
    {
        a = l;
    }
}
