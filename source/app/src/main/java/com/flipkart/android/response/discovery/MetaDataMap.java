// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.response.discovery;


public class MetaDataMap
{

    private String deselected_text;
    private boolean is_emphasized;
    private String selected_text;

    public MetaDataMap()
    {
    }

    public String getDeselected_text()
    {
        return deselected_text;
    }

    public String getSelected_text()
    {
        return selected_text;
    }

    public boolean isIs_emphasized()
    {
        return is_emphasized;
    }

    public void setDeselected_text(String s)
    {
        deselected_text = s;
    }

    public void setIs_emphasized(boolean flag)
    {
        is_emphasized = flag;
    }

    public void setSelected_text(String s)
    {
        selected_text = s;
    }
}
