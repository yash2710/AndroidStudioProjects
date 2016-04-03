// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing.client.android;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.os.AsyncTask;
import com.google.zxing.client.android.common.executor.AsyncTaskExecInterface;
import com.google.zxing.client.android.common.executor.AsyncTaskExecManager;

// Referenced classes of package com.google.zxing.client.android:
//            q, p

final class n
{

    private static final String a = com/google/zxing/client/android/n.getSimpleName();
    private final Activity b;
    private final AsyncTaskExecInterface c = (AsyncTaskExecInterface)(new AsyncTaskExecManager()).build();
    private final BroadcastReceiver d = new q(this, (byte)0);
    private p e;

    n(Activity activity)
    {
        b = activity;
        a();
    }

    static void a(n n1)
    {
        n1.d();
    }

    static Activity b(n n1)
    {
        return n1.b;
    }

    static String c()
    {
        return a;
    }

    private void d()
    {
        this;
        JVM INSTR monitorenter ;
        p p1 = e;
        if (p1 == null)
        {
            break MISSING_BLOCK_LABEL_22;
        }
        p1.cancel(true);
        e = null;
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    final void a()
    {
        this;
        JVM INSTR monitorenter ;
        d();
        e = new p(this, (byte)0);
        c.execute(e, new Object[0]);
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    final void b()
    {
        d();
    }

    public final void onPause()
    {
        d();
        b.unregisterReceiver(d);
    }

    public final void onResume()
    {
        b.registerReceiver(d, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
        a();
    }

}
