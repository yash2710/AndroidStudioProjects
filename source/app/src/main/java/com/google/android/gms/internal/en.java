// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

// Referenced classes of package com.google.android.gms.internal:
//            eu

public final class en
{

    private static final ThreadFactory sh;
    private static final ThreadPoolExecutor si;

    public static void execute(Runnable runnable)
    {
        try
        {
            si.execute(new _cls1(runnable));
            return;
        }
        catch (RejectedExecutionException rejectedexecutionexception)
        {
            eu.c((new StringBuilder("Too many background threads already running. Aborting task.  Current pool size: ")).append(getPoolSize()).toString(), rejectedexecutionexception);
        }
    }

    public static int getPoolSize()
    {
        return si.getPoolSize();
    }

    static 
    {
        sh = new _cls2();
        si = new ThreadPoolExecutor(0, 10, 65L, TimeUnit.SECONDS, new SynchronousQueue(true), sh);
    }

    private class _cls1
        implements Runnable
    {

        final Runnable sj;

        public final void run()
        {
            Process.setThreadPriority(10);
            sj.run();
        }

        _cls1(Runnable runnable)
        {
            sj = runnable;
            super();
        }
    }


    private class _cls2
        implements ThreadFactory
    {

        private final AtomicInteger sk = new AtomicInteger(1);

        public final Thread newThread(Runnable runnable)
        {
            return new Thread(runnable, (new StringBuilder("AdWorker #")).append(sk.getAndIncrement()).toString());
        }

        _cls2()
        {
        }
    }

}
