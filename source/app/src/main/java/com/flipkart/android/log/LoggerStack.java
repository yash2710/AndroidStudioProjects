// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.log;

import java.util.concurrent.ConcurrentLinkedQueue;

public class LoggerStack extends ConcurrentLinkedQueue
{

    private int a;

    public LoggerStack(int i)
    {
        a = 10;
        a = i;
    }

    public volatile boolean add(Object obj)
    {
        return add((String)obj);
    }

    public boolean add(String s)
    {
        super.add(s);
        for (; size() > a; super.poll()) { }
        return true;
    }
}
