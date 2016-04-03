// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.DB;


public class FlipkartProductInfo
{

    private String a;
    private long b;
    private byte c[];

    public FlipkartProductInfo()
    {
    }

    public FlipkartProductInfo(String s, long l, byte abyte0[])
    {
        setPid(s);
        setTime(l);
        setResponse(abyte0);
    }

    public String getPid()
    {
        return a;
    }

    public byte[] getResponse()
    {
        return c;
    }

    public long getTime()
    {
        return b;
    }

    public void setPid(String s)
    {
        a = s;
    }

    public void setResponse(byte abyte0[])
    {
        c = abyte0;
    }

    public void setTime(long l)
    {
        b = l;
    }
}
