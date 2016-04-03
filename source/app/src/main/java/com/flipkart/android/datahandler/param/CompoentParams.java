// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.datahandler.param;


public class CompoentParams
{

    private String a;
    private String b;
    private long c;

    public CompoentParams(String s, String s1)
    {
        a = s;
        b = s1;
    }

    public long getLastUpdated()
    {
        return c;
    }

    public String getScreenId()
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

    public void setScreenId(String s)
    {
        b = s;
    }

    public void setScreenName(String s)
    {
        a = s;
    }
}
