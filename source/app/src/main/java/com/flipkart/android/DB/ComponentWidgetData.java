// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.DB;


public class ComponentWidgetData
{

    private String a;
    private long b;
    private byte c[];

    public ComponentWidgetData()
    {
    }

    public ComponentWidgetData(String s, long l, byte abyte0[])
    {
        a = s;
        b = l;
        c = abyte0;
    }

    public long getLastUpdated()
    {
        return b;
    }

    public byte[] getResponse()
    {
        return c;
    }

    public String getWidgetId()
    {
        return a;
    }

    public void setLastUpdated(long l)
    {
        b = l;
    }

    public void setResponse(byte abyte0[])
    {
        c = abyte0;
    }

    public void setWidgetId(String s)
    {
        a = s;
    }
}
