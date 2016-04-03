// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.volley.request.logger.params;

import com.flipkart.android.utils.StringUtils;

public class LoggerParams
{

    private String a;
    private String b;

    public LoggerParams(String s, String s1)
    {
        a = s;
        b = s1;
    }

    public byte[] generateToByteArray()
    {
        if (a != null)
        {
            return a.getBytes();
        } else
        {
            return null;
        }
    }

    public String generateURI()
    {
        String s = "";
        if (!StringUtils.isNullOrEmpty(b))
        {
            s = (new StringBuilder("?logType=")).append(b).toString();
        }
        return s;
    }

    public String getMessage()
    {
        return a;
    }

    public String getType()
    {
        return b;
    }

    public void setMessage(String s)
    {
        a = s;
    }

    public void setType(String s)
    {
        b = s;
    }
}
