// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.newrelic.agent.android.api.common;

import java.util.concurrent.TimeUnit;

public final class ConnectionState
{

    public static final ConnectionState NULL = new ConnectionState();
    private final Object a;
    private final String b;
    private final long c;
    private final long d;
    private final TimeUnit e;
    private final long f;
    private final TimeUnit g;
    private final long h;
    private final int i;
    private final int j;
    private final boolean k;
    private final int l;

    private ConnectionState()
    {
        a = null;
        b = null;
        c = 0L;
        d = 60L;
        e = TimeUnit.SECONDS;
        f = 600L;
        g = TimeUnit.SECONDS;
        h = 1000L;
        i = 50;
        j = 1024;
        k = true;
        l = 10;
    }

    public ConnectionState(Object obj, String s, long l1, long l2, TimeUnit timeunit, 
            long l3, TimeUnit timeunit1, long l4, int i1, int j1, 
            boolean flag, int k1)
    {
        a = obj;
        b = s;
        c = l1;
        d = l2;
        e = timeunit;
        f = l3;
        g = timeunit1;
        h = l4;
        i = i1;
        j = j1;
        k = flag;
        l = k1;
    }

    public final String getCrossProcessId()
    {
        return b;
    }

    public final Object getDataToken()
    {
        return a;
    }

    public final int getErrorLimit()
    {
        return l;
    }

    public final long getHarvestIntervalInMilliseconds()
    {
        return TimeUnit.MILLISECONDS.convert(d, e);
    }

    public final long getHarvestIntervalInSeconds()
    {
        return TimeUnit.SECONDS.convert(d, e);
    }

    public final long getMaxTransactionAgeInMilliseconds()
    {
        return TimeUnit.MILLISECONDS.convert(f, g);
    }

    public final long getMaxTransactionAgeInSeconds()
    {
        return TimeUnit.SECONDS.convert(f, g);
    }

    public final long getMaxTransactionCount()
    {
        return h;
    }

    public final int getResponseBodyLimit()
    {
        return j;
    }

    public final long getServerTimestamp()
    {
        return c;
    }

    public final int getStackTraceLimit()
    {
        return i;
    }

    public final boolean isCollectingNetworkErrors()
    {
        return k;
    }

    public final String toString()
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append(a);
        return stringbuilder.toString();
    }

}
