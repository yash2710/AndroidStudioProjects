// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.response.urlencoder;

import com.flipkart.android.response.customwidgetitemvalue.Action;

public class UrlDecodeResponse
{

    Action action;
    String notificationId;
    String omnitureData;

    public UrlDecodeResponse()
    {
    }

    public Action getAction()
    {
        return action;
    }

    public String getNotificationId()
    {
        return notificationId;
    }

    public String getOmnitureData()
    {
        return omnitureData;
    }

    public void setAction(Action action1)
    {
        action = action1;
    }

    public void setNotificationId(String s)
    {
        notificationId = s;
    }

    public void setOmnitureData(String s)
    {
        omnitureData = s;
    }
}
