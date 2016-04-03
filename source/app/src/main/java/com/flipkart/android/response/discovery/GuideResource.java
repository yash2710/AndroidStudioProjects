// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.response.discovery;


public class GuideResource
{

    private String params;
    private boolean selected;
    private String uri;

    public GuideResource()
    {
    }

    public String getParams()
    {
        return params;
    }

    public String getUri()
    {
        return uri;
    }

    public boolean isSelected()
    {
        return selected;
    }

    public void setParams(String s)
    {
        params = s;
    }

    public void setSelected(boolean flag)
    {
        selected = flag;
    }

    public void setUri(String s)
    {
        uri = s;
    }
}
