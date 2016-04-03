// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.volley.request.component.params;

import com.flipkart.android.utils.HashUtils;
import com.flipkart.android.utils.StringUtils;
import com.flipkart.logging.FkLogger;

public class ComponentParam
{

    private String a;
    private String b;
    private long c;
    private boolean d;

    public ComponentParam(String s, String s1, long l)
    {
        a = s;
        b = s1;
        c = l;
        d = false;
    }

    public ComponentParam(String s, String s1, long l, boolean flag)
    {
        a = s;
        b = s1;
        c = l;
        d = flag;
    }

    public String generateMD5()
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("/").append(a);
        if (!StringUtils.isNullOrEmpty(b))
        {
            stringbuilder.append("/").append(b);
        }
        return HashUtils.md5(stringbuilder.toString());
    }

    public String generateURI()
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("/").append(a);
        if (!StringUtils.isNullOrEmpty(b))
        {
            stringbuilder.append("/").append(b);
        }
        FkLogger.debug("Test", (new StringBuilder("time stamp ")).append(c).toString());
        if (c != 0L)
        {
            stringbuilder.append("?layoutId=").append(c);
        }
        return stringbuilder.toString();
    }

    public String getScreenId()
    {
        return b;
    }

    public String getScreenName()
    {
        return a;
    }

    public long getTimeStamp()
    {
        return c;
    }

    public boolean isLowPriority()
    {
        return d;
    }

    public void setLowPriority(boolean flag)
    {
        d = flag;
    }

    public void setScreenId(String s)
    {
        b = s;
    }

    public void setScreenName(String s)
    {
        a = s;
    }

    public void setTimeStamp(long l)
    {
        c = l;
    }
}
