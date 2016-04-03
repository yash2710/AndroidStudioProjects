// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.response.component.layout;


public class LayoutData
{

    String dataKey;
    long dataTtl;
    String updatedBy;

    public LayoutData()
    {
        updatedBy = "server";
    }

    public LayoutData(String s)
    {
        dataKey = s;
    }

    public String getDataKey()
    {
        return dataKey;
    }

    public long getDataTtl()
    {
        return dataTtl;
    }

    public String getUpdatedBy()
    {
        return updatedBy;
    }

    public void setDataKey(String s)
    {
        dataKey = s;
    }

    public void setDataTtl(long l)
    {
        dataTtl = l;
    }

    public void setUpdatedBy(String s)
    {
        updatedBy = s;
    }
}
