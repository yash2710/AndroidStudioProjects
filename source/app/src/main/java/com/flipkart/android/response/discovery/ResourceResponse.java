// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.response.discovery;


public class ResourceResponse
{

    private String params;
    private boolean selected;

    public ResourceResponse()
    {
    }

    public String getParams()
    {
        return params;
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
}
