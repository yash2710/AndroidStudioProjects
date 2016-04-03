// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing.client.android.common.executor;

import android.os.AsyncTask;
import com.newrelic.agent.android.instrumentation.AsyncTaskInstrumentation;

// Referenced classes of package com.google.zxing.client.android.common.executor:
//            AsyncTaskExecInterface

public final class DefaultAsyncTaskExecInterface
    implements AsyncTaskExecInterface
{

    public DefaultAsyncTaskExecInterface()
    {
    }

    public final transient void execute(AsyncTask asynctask, Object aobj[])
    {
        if (!(asynctask instanceof AsyncTask))
        {
            asynctask.execute(aobj);
            return;
        } else
        {
            AsyncTaskInstrumentation.execute((AsyncTask)asynctask, aobj);
            return;
        }
    }
}
