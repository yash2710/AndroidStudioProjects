// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.response.appconfig;


public class DbTimeout
{

    private long dicoveryTimeout;
    private long prodInfoTimeout;
    private long sellerTimeout;
    private long ugcTimeout;

    public DbTimeout()
    {
    }

    public long getDicoveryTimeout()
    {
        return dicoveryTimeout;
    }

    public long getProdInfoTimeout()
    {
        return prodInfoTimeout;
    }

    public long getSellerTimeout()
    {
        return sellerTimeout;
    }

    public long getUgcTimeout()
    {
        return ugcTimeout;
    }

    public void setDicoveryTimeout(long l)
    {
        dicoveryTimeout = l;
    }

    public void setProdInfoTimeout(long l)
    {
        prodInfoTimeout = l;
    }

    public void setSellerTimeout(long l)
    {
        sellerTimeout = l;
    }

    public void setUgcTimeout(long l)
    {
        ugcTimeout = l;
    }
}
