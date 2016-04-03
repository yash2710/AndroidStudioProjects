// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.DB;


public class UGC
{

    private String a;
    private long b;
    private byte c[];

    public UGC()
    {
    }

    public UGC(String s, long l, byte abyte0[])
    {
        setMd5(s);
        setTime(l);
        setResponse(abyte0);
    }

    public String getMd5()
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

    public void setMd5(String s)
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
