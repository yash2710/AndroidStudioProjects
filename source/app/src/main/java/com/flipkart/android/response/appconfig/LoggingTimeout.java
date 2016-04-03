// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.response.appconfig;


public class LoggingTimeout
{

    private boolean loggingEnabled;
    private long maxCount;
    private boolean perfLoggingEnabled;
    private long timeoutCount;

    public LoggingTimeout()
    {
    }

    public long getMaxCount()
    {
        return maxCount;
    }

    public long getTimeoutCount()
    {
        return timeoutCount;
    }

    public boolean isLoggingEnabled()
    {
        return loggingEnabled;
    }

    public boolean isPerfLoggingEnabled()
    {
        return perfLoggingEnabled;
    }

    public void setLoggingEnabled(boolean flag)
    {
        loggingEnabled = flag;
    }

    public void setMaxCount(long l)
    {
        maxCount = l;
    }

    public void setPerfLoggingEnabled(boolean flag)
    {
        perfLoggingEnabled = flag;
    }

    public void setTimeoutCount(long l)
    {
        timeoutCount = l;
    }
}
