// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.notification.data.pulldown;


public class NotificationAction
{

    private String a;
    private String b;
    private String c;
    private String d;

    public NotificationAction()
    {
    }

    public NotificationAction(String s, String s1, String s2, String s3)
    {
        a = s;
        b = s1;
        c = s2;
        d = s3;
    }

    public String getAction()
    {
        return c;
    }

    public String getIcon()
    {
        return a;
    }

    public String getOmniture()
    {
        return d;
    }

    public String getTitle()
    {
        return b;
    }

    public void setAction(String s)
    {
        c = s;
    }

    public void setIcon(String s)
    {
        a = s;
    }

    public void setOmniture(String s)
    {
        d = s;
    }

    public void setTitle(String s)
    {
        b = s;
    }
}
