// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.response.customwidgetitemvalue;


public class CallUsWidgetItemValue
{

    private boolean showCallUs;
    private String subTitle;
    private String title;

    public CallUsWidgetItemValue()
    {
    }

    public String getSubTitle()
    {
        return subTitle;
    }

    public String getTitle()
    {
        return title;
    }

    public boolean isShowCallUs()
    {
        return showCallUs;
    }

    public void setShowCallUs(boolean flag)
    {
        showCallUs = flag;
    }

    public void setSubTitle(String s)
    {
        subTitle = s;
    }

    public void setTitle(String s)
    {
        title = s;
    }
}
