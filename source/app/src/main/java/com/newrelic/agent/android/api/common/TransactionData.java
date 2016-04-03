// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.newrelic.agent.android.api.common;

import java.util.ArrayList;
import java.util.List;

public class TransactionData
{

    private final long a = System.currentTimeMillis();
    private final String b;
    private final String c;
    private final float d;
    private final int e;
    private int f;
    private final Object g = new Object();
    private final long h;
    private final long i;
    private final String j;

    public TransactionData(String s, String s1, float f1, int k, int l, long l1, 
            long l2, String s2)
    {
        int i1 = s.indexOf('?');
        if (i1 < 0)
        {
            i1 = s.indexOf(';');
            if (i1 < 0)
            {
                i1 = s.length();
            }
        }
        b = s.substring(0, i1);
        c = s1;
        d = f1;
        e = k;
        f = l;
        h = l1;
        i = l2;
        j = s2;
    }

    public List asList()
    {
        ArrayList arraylist = new ArrayList();
        arraylist.add(b);
        arraylist.add(c);
        arraylist.add(Float.valueOf(d));
        arraylist.add(Integer.valueOf(e));
        arraylist.add(Integer.valueOf(f));
        arraylist.add(Long.valueOf(h));
        arraylist.add(Long.valueOf(i));
        arraylist.add(j);
        return arraylist;
    }

    public TransactionData clone()
    {
        return new TransactionData(b, c, d, e, f, h, i, j);
    }

    public volatile Object clone()
    {
        return clone();
    }

    public String getAppData()
    {
        return j;
    }

    public long getBytesReceived()
    {
        return i;
    }

    public long getBytesSent()
    {
        return h;
    }

    public String getCarrier()
    {
        return c;
    }

    public int getErrorCode()
    {
        int k;
        synchronized (g)
        {
            k = f;
        }
        return k;
    }

    public int getStatusCode()
    {
        return e;
    }

    public float getTime()
    {
        return d;
    }

    public long getTimestamp()
    {
        return a;
    }

    public String getUrl()
    {
        return b;
    }

    public void setErrorCode(int k)
    {
        synchronized (g)
        {
            f = k;
        }
    }

    public String toString()
    {
        return (new StringBuilder("TransactionData{timestamp=")).append(a).append(", url='").append(b).append('\'').append(", carrier='").append(c).append('\'').append(", time=").append(d).append(", statusCode=").append(e).append(", errorCode=").append(f).append(", errorCodeLock=").append(g).append(", bytesSent=").append(h).append(", bytesReceived=").append(i).append(", appData='").append(j).append('\'').append('}').toString();
    }
}
