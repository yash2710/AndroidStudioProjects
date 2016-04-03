// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.urlmanagement;


public class WebviewParams
{

    private String a;
    private String b;
    private int c;
    private String d;

    public WebviewParams()
    {
    }

    public String getPostParams()
    {
        return b;
    }

    public String getProtocol()
    {
        return d;
    }

    public String getUrl()
    {
        return a;
    }

    public int getVerb()
    {
        return c;
    }

    public void setPostParams(String s)
    {
        b = s;
    }

    public void setProtocol(String s)
    {
        d = s;
    }

    public void setUrl(String s)
    {
        a = s;
    }

    public void setVerb(int i)
    {
        c = i;
    }
}
