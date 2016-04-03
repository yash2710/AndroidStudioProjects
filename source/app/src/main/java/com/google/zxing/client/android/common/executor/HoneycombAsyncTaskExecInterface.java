// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing.client.android.common.executor;

import android.os.AsyncTask;
import com.newrelic.agent.android.instrumentation.AsyncTaskInstrumentation;

// Referenced classes of package com.google.zxing.client.android.common.executor:
//            AsyncTaskExecInterface

public final class HoneycombAsyncTaskExecInterface
    implements AsyncTaskExecInterface
{

    public HoneycombAsyncTaskExecInterface()
    {
    }

    public final transient void execute(AsyncTask asynctask, Object aobj[])
    {
        java.util.concurrent.Executor executor = AsyncTask.THREAD_POOL_EXECUTOR;
        if (!(asynctask instanceof AsyncTask))
        {
            asynctask.executeOnExecutor(executor, aobj);
            return;
        } else
        {
            AsyncTaskInstrumentation.executeOnExecutor((AsyncTask)asynctask, executor, aobj);
            return;
        }
    }
}
