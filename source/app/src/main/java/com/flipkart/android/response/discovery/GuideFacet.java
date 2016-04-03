// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.response.discovery;

import java.util.ArrayList;

public class GuideFacet
{

    private String title;
    private ArrayList value;

    public GuideFacet()
    {
    }

    public String getTitle()
    {
        return title;
    }

    public ArrayList getValue()
    {
        return value;
    }

    public void setTitle(String s)
    {
        title = s;
    }

    public void setValue(ArrayList arraylist)
    {
        value = arraylist;
    }
}
