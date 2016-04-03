// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.volley;

import android.os.Handler;
import java.util.concurrent.Executor;

// Referenced classes of package com.android.volley:
//            ExecutorDelivery

final class b
    implements Executor
{

    private Handler a;

    b(ExecutorDelivery executordelivery, Handler handler)
    {
        a = handler;
        super();
    }

    public final void execute(Runnable runnable)
    {
        a.post(runnable);
    }
}
