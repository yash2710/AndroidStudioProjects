// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.response.appconfig;

import com.flipkart.android.response.component.data.customvalues.Image;

public class AppUpgradeData
{

    Image appIcon;
    String appUpgradeMessageHtml;
    String appUpgradeNotificationHtml;
    String appUpgradeTitle;
    int latestAppVersion;
    int maxPromptCount;
    int minAppLaunchCounts;
    boolean showAppUpgradeNotification;
    boolean showAppUpgradePrompt;

    public AppUpgradeData()
    {
    }

    public Image getAppIcon()
    {
        return appIcon;
    }

    public String getAppUpgradeMessageHtml()
    {
        return appUpgradeMessageHtml;
    }

    public String getAppUpgradeNotificationHtml()
    {
        return appUpgradeNotificationHtml;
    }

    public String getAppUpgradeTitle()
    {
        return appUpgradeTitle;
    }

    public int getLatestAppVersion()
    {
        return latestAppVersion;
    }

    public int getMaxPromptCount()
    {
        return maxPromptCount;
    }

    public int getMinAppLaunchCounts()
    {
        return minAppLaunchCounts;
    }

    public boolean isShowAppUpgradeNotification()
    {
        return showAppUpgradeNotification;
    }

    public boolean isShowAppUpgradePrompt()
    {
        return showAppUpgradePrompt;
    }

    public void setAppIcon(Image image)
    {
        appIcon = image;
    }

    public void setAppUpgradeMessageHtml(String s)
    {
        appUpgradeMessageHtml = s;
    }

    public void setAppUpgradeNotificationHtml(String s)
    {
        appUpgradeNotificationHtml = s;
    }

    public void setAppUpgradeTitle(String s)
    {
        appUpgradeTitle = s;
    }

    public void setLatestAppVersion(int i)
    {
        latestAppVersion = i;
    }

    public void setMaxPromptCount(int i)
    {
        maxPromptCount = i;
    }

    public void setMinAppLaunchCounts(int i)
    {
        minAppLaunchCounts = i;
    }

    public void setShowAppUpgradeNotification(boolean flag)
    {
        showAppUpgradeNotification = flag;
    }

    public void setShowAppUpgradePrompt(boolean flag)
    {
        showAppUpgradePrompt = flag;
    }
}
