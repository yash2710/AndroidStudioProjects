// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.response.inAppNotification;

import java.util.ArrayList;

public class InAppNotificationResponse
{

    private ArrayList notifications;
    private int total;
    private int unreadCount;

    public InAppNotificationResponse()
    {
    }

    public ArrayList getNotifications()
    {
        return notifications;
    }

    public int getTotal()
    {
        return total;
    }

    public int getUnreadCount()
    {
        return unreadCount;
    }

    public void setNotifications(ArrayList arraylist)
    {
        notifications = arraylist;
    }

    public void setTotal(int i)
    {
        total = i;
    }

    public void setUnreadCount(int i)
    {
        unreadCount = i;
    }
}
