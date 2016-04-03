// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing.client.android.camera;

import android.content.Context;
import android.content.SharedPreferences;
import android.hardware.Camera;
import android.preference.PreferenceManager;
import com.flipkart.logging.FkLogger;
import com.google.zxing.client.android.common.executor.AsyncTaskExecInterface;
import com.google.zxing.client.android.common.executor.AsyncTaskExecManager;
import java.util.ArrayList;
import java.util.Collection;

// Referenced classes of package com.google.zxing.client.android.camera:
//            c

final class a
    implements android.hardware.Camera.AutoFocusCallback
{

    private static final String a = com/google/zxing/client/android/camera/a.getSimpleName();
    private static final Collection b;
    private boolean c;
    private final boolean d;
    private final Camera e;
    private c f;
    private final AsyncTaskExecInterface g = (AsyncTaskExecInterface)(new AsyncTaskExecManager()).build();

    a(Context context, Camera camera)
    {
        e = camera;
        SharedPreferences sharedpreferences = PreferenceManager.getDefaultSharedPreferences(context);
        String s = camera.getParameters().getFocusMode();
        boolean flag;
        if (sharedpreferences.getBoolean("preferences_auto_focus", true) && b.contains(s))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        d = flag;
        FkLogger.info(a, (new StringBuilder("Current focus mode '")).append(s).append("'; use auto focus? ").append(d).toString());
        a();
    }

    static boolean a(a a1)
    {
        return a1.c;
    }

    final void a()
    {
        this;
        JVM INSTR monitorenter ;
        if (!d)
        {
            break MISSING_BLOCK_LABEL_22;
        }
        c = true;
        e.autoFocus(this);
_L2:
        this;
        JVM INSTR monitorexit ;
        return;
        RuntimeException runtimeexception;
        runtimeexception;
        FkLogger.warn(a, "Unexpected exception while focusing", runtimeexception);
        if (true) goto _L2; else goto _L1
_L1:
        Exception exception;
        exception;
        throw exception;
    }

    final void b()
    {
        this;
        JVM INSTR monitorenter ;
        boolean flag = d;
        if (!flag)
        {
            break MISSING_BLOCK_LABEL_18;
        }
        e.cancelAutoFocus();
_L1:
        if (f != null)
        {
            f.cancel(true);
            f = null;
        }
        c = false;
        this;
        JVM INSTR monitorexit ;
        return;
        RuntimeException runtimeexception;
        runtimeexception;
        FkLogger.warn(a, "Unexpected exception while cancelling focusing", runtimeexception);
          goto _L1
        Exception exception;
        exception;
        throw exception;
    }

    public final void onAutoFocus(boolean flag, Camera camera)
    {
        this;
        JVM INSTR monitorenter ;
        if (c)
        {
            f = new c(this, (byte)0);
            g.execute(f, new Object[0]);
        }
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    static 
    {
        ArrayList arraylist = new ArrayList(2);
        b = arraylist;
        arraylist.add("auto");
        b.add("macro");
    }
}
