// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.newrelic.agent.android.measurement;


public class ThreadInfo
{

    private long a;
    private String b;

    public ThreadInfo()
    {
        this(Thread.currentThread());
    }

    public ThreadInfo(long l, String s)
    {
        a = l;
        b = s;
    }

    public ThreadInfo(Thread thread)
    {
        this(thread.getId(), thread.getName());
    }

    public static ThreadInfo fromThread(Thread thread)
    {
        return new ThreadInfo(thread);
    }

    public long getId()
    {
        return a;
    }

    public String getName()
    {
        return b;
    }

    public void setId(long l)
    {
        a = l;
    }

    public void setName(String s)
    {
        b = s;
    }

    public String toString()
    {
        return (new StringBuilder("ThreadInfo{id=")).append(a).append(", name='").append(b).append('\'').append('}').toString();
    }
}
