// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.notification.data.pulldown;

import java.util.ArrayList;

public class NotificationPayload
{

    private ArrayList a;

    public NotificationPayload()
    {
    }

    public NotificationPayload(ArrayList arraylist)
    {
        a = arraylist;
    }

    public ArrayList getActions()
    {
        return a;
    }

    public void setActions(ArrayList arraylist)
    {
        a = arraylist;
    }
}
