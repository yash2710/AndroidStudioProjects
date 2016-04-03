// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.DB;


public class ComponentWidgetLayout
{

    private String a;
    private byte b[];
    private long c;

    public ComponentWidgetLayout()
    {
    }

    public ComponentWidgetLayout(String s, byte abyte0[], long l)
    {
        a = s;
        b = abyte0;
        c = l;
    }

    public long getLastUpdated()
    {
        return c;
    }

    public byte[] getResponse()
    {
        return b;
    }

    public String getScreenName()
    {
        return a;
    }

    public void setLastUpdated(long l)
    {
        c = l;
    }

    public void setResponse(byte abyte0[])
    {
        b = abyte0;
    }

    public void setScreenName(String s)
    {
        a = s;
    }
}
