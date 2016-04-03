// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.newrelic.agent.android.stats;


// Referenced classes of package com.newrelic.agent.android.stats:
//            a

public class TicToc
{

    private long a;
    private long b;
    private a c;

    public TicToc()
    {
    }

    public void tic()
    {
        c = a.b;
        a = System.currentTimeMillis();
    }

    public long toc()
    {
        b = System.currentTimeMillis();
        if (c == a.b)
        {
            c = a.a;
            return b - a;
        } else
        {
            return -1L;
        }
    }
}
