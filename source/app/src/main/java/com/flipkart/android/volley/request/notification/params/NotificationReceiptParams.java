// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.volley.request.notification.params;

import com.flipkart.android.utils.StringUtils;

public class NotificationReceiptParams
{

    private String a;
    private String b;
    private long c;

    public NotificationReceiptParams(String s, String s1, long l)
    {
        a = "";
        b = null;
        a = s;
        b = s1;
        c = l;
    }

    public byte[] generateBody()
    {
        return "{\"messageId\": \"notificationid\",\"action\": \"state\",\"actionTS\": timestamp}".replaceFirst("notificationid", a).replaceFirst("state", b).replaceFirst("timestamp", (new StringBuilder()).append(c).toString()).getBytes();
    }

    public String generateURI()
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("?notificationId=").append(a);
        if (!StringUtils.isNullOrEmpty(b))
        {
            stringbuilder.append("state=").append(b);
        }
        return stringbuilder.toString();
    }
}
