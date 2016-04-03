// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.response.discovery;


public class GuideMetaData
{

    private String guideType;
    private String primaryCriteria;
    private String title;

    public GuideMetaData()
    {
    }

    public String getGuideType()
    {
        return guideType;
    }

    public String getPrimaryCriteria()
    {
        return primaryCriteria;
    }

    public String getTitle()
    {
        return title;
    }

    public void setGuideType(String s)
    {
        guideType = s;
    }

    public void setPrimaryCriteria(String s)
    {
        primaryCriteria = s;
    }

    public void setTitle(String s)
    {
        title = s;
    }
}
