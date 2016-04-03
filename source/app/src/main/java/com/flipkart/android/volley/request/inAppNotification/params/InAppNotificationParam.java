// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.volley.request.inAppNotification.params;

import com.flipkart.android.utils.StringUtils;

public class InAppNotificationParam
{

    private int a;
    private long b;
    private String c;
    private boolean d;
    private String e;

    public InAppNotificationParam(int i, long l, String s, boolean flag, String s1)
    {
        a = i;
        b = l;
        d = flag;
        c = s;
        e = s1;
    }

    public byte[] generateToByteArray()
    {
        return "".getBytes();
    }

    public String generateURI()
    {
        StringBuilder stringbuilder = new StringBuilder();
        if (!StringUtils.isNullOrEmpty(c))
        {
            stringbuilder.append("/").append(c);
            if (d)
            {
                stringbuilder.append("/markAsRead");
            }
            if (!StringUtils.isNullOrEmpty(e))
            {
                stringbuilder.append("?type=").append(e);
            }
            return stringbuilder.toString();
        }
        if (a > 0)
        {
            stringbuilder.append("?count=").append(a);
            stringbuilder.append("&before=").append(b);
            return stringbuilder.toString();
        } else
        {
            return "";
        }
    }

    public int getCount()
    {
        return a;
    }

    public String getNotificationId()
    {
        return c;
    }

    public String getNotificationType()
    {
        return e;
    }

    public long getTimeStamp()
    {
        return b;
    }

    public boolean isMarkAsRead()
    {
        return d;
    }

    public void setCount(int i)
    {
        a = i;
    }

    public void setMarkAsRead(boolean flag)
    {
        d = flag;
    }

    public void setNotificationId(String s)
    {
        c = s;
    }

    public void setNotificationType(String s)
    {
        e = s;
    }

    public void setTimeStamp(long l)
    {
        b = l;
    }
}
