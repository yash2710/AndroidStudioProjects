// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

final class eger
    implements ThreadFactory
{

    private final AtomicInteger sk = new AtomicInteger(1);

    public final Thread newThread(Runnable runnable)
    {
        return new Thread(runnable, (new StringBuilder("AdWorker #")).append(sk.getAndIncrement()).toString());
    }

    eger()
    {
    }
}
