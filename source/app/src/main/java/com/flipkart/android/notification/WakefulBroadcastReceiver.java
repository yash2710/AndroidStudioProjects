// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.notification;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;
import android.util.SparseArray;
import com.flipkart.logging.FkLogger;

public abstract class WakefulBroadcastReceiver extends BroadcastReceiver
{

    private static final SparseArray a = new SparseArray();
    private static int b = 1;

    public WakefulBroadcastReceiver()
    {
    }

    public static boolean completeWakefulIntent(Intent intent)
    {
        int i;
        while (intent == null || (i = intent.getIntExtra("android.support.content.wakelockid", 0)) == 0) 
        {
            return false;
        }
        SparseArray sparsearray = a;
        sparsearray;
        JVM INSTR monitorenter ;
        android.os.PowerManager.WakeLock wakelock = (android.os.PowerManager.WakeLock)a.get(i);
        if (wakelock == null)
        {
            break MISSING_BLOCK_LABEL_57;
        }
        wakelock.release();
        a.remove(i);
        sparsearray;
        JVM INSTR monitorexit ;
        return true;
        FkLogger.warn("WakefulBroadcastReceiver", (new StringBuilder("No active wake lock id #")).append(i).toString());
        sparsearray;
        JVM INSTR monitorexit ;
        return true;
        Exception exception;
        exception;
        throw exception;
    }

    public static ComponentName startWakefulService(Context context, Intent intent)
    {
        SparseArray sparsearray = a;
        sparsearray;
        JVM INSTR monitorenter ;
        int i;
        int j;
        i = b;
        j = 1 + b;
        b = j;
        if (j > 0)
        {
            break MISSING_BLOCK_LABEL_32;
        }
        b = 1;
        ComponentName componentname;
        intent.putExtra("android.support.content.wakelockid", i);
        componentname = context.startService(intent);
        if (componentname != null)
        {
            break MISSING_BLOCK_LABEL_57;
        }
        sparsearray;
        JVM INSTR monitorexit ;
        return null;
        android.os.PowerManager.WakeLock wakelock = ((PowerManager)context.getSystemService("power")).newWakeLock(1, (new StringBuilder("wake:")).append(componentname.flattenToShortString()).toString());
        wakelock.setReferenceCounted(false);
        wakelock.acquire(60000L);
        a.put(i, wakelock);
        sparsearray;
        JVM INSTR monitorexit ;
        return componentname;
        Exception exception;
        exception;
        throw exception;
    }

}
