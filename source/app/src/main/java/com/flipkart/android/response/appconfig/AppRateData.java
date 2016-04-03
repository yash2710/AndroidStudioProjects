// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.response.appconfig;

import com.flipkart.android.response.component.data.customvalues.Image;

public class AppRateData
{

    Image appIcon;
    int appOpenCount;
    boolean enabled;
    int maxPromptCount;
    String messageHtml;
    int purchaseCount;
    String title;

    public AppRateData()
    {
    }

    public Image getAppIcon()
    {
        return appIcon;
    }

    public int getAppOpenCount()
    {
        return appOpenCount;
    }

    public int getMaxPromptCount()
    {
        return maxPromptCount;
    }

    public String getMessageHtml()
    {
        return messageHtml;
    }

    public int getPurchaseCount()
    {
        return purchaseCount;
    }

    public String getTitle()
    {
        return title;
    }

    public boolean isEnabled()
    {
        return enabled;
    }

    public void setAppIcon(Image image)
    {
        appIcon = image;
    }

    public void setAppOpenCount(int i)
    {
        appOpenCount = i;
    }

    public void setEnabled(boolean flag)
    {
        enabled = flag;
    }

    public void setMaxPromptCount(int i)
    {
        maxPromptCount = i;
    }

    public void setMessageHtml(String s)
    {
        messageHtml = s;
    }

    public void setPurchaseCount(int i)
    {
        purchaseCount = i;
    }

    public void setTitle(String s)
    {
        title = s;
    }
}
