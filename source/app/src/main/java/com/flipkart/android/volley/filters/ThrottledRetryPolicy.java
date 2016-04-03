// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.volley.filters;

import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.flipkart.android.utils.NetworkMonitor;

public class ThrottledRetryPolicy
    implements RetryPolicy
{

    private int a;
    private int b;
    private int c;
    private String d;

    public ThrottledRetryPolicy()
    {
        if (NetworkMonitor.isNetworkFast() == 3)
        {
            a = 2000;
            c = 6;
        } else
        {
            a = 10000;
            c = 3;
        }
        d = NetworkMonitor.getNetworkTypeVerbose();
    }

    public ThrottledRetryPolicy(int i, int j)
    {
        NetworkMonitor.isNetworkFast();
        a = j;
        c = i;
        d = NetworkMonitor.getNetworkTypeVerbose();
    }

    public int getCurrentRetryCount()
    {
        return b;
    }

    public int getCurrentTimeout()
    {
        return a;
    }

    public String getNetworkType()
    {
        return d;
    }

    protected boolean hasAttemptRemaining()
    {
        return b <= c;
    }

    public void retry(VolleyError volleyerror)
    {
        b = 1 + b;
        if (!hasAttemptRemaining())
        {
            throw volleyerror;
        } else
        {
            return;
        }
    }

    public void setNetworkType(String s)
    {
        d = s;
    }
}
