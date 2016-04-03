// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import java.util.HashSet;
import java.util.Iterator;

// Referenced classes of package com.google.android.gms.internal:
//            hd

final class mState
{

    private final HashSet GA = new HashSet();
    private boolean GB;
    private IBinder GC;
    private ComponentName GD;
    final hd GE;
    private final String Gy;
    private final a Gz = new a();
    private int mState;

    static int a(mState mstate, int i)
    {
        mstate.mState = i;
        return i;
    }

    static ComponentName a(mState mstate, ComponentName componentname)
    {
        mstate.GD = componentname;
        return componentname;
    }

    static IBinder a(GD gd, IBinder ibinder)
    {
        gd.GC = ibinder;
        return ibinder;
    }

    static HashSet a(GC gc)
    {
        return gc.GA;
    }

    public final void B(boolean flag)
    {
        GB = flag;
    }

    public final void a(GB gb)
    {
        GA.add(gb);
    }

    public final void b(GA ga)
    {
        GA.remove(ga);
    }

    public final boolean c(GA ga)
    {
        return GA.contains(ga);
    }

    public final a fx()
    {
        return Gz;
    }

    public final String fy()
    {
        return Gy;
    }

    public final boolean fz()
    {
        return GA.isEmpty();
    }

    public final IBinder getBinder()
    {
        return GC;
    }

    public final ComponentName getComponentName()
    {
        return GD;
    }

    public final int getState()
    {
        return mState;
    }

    public final boolean isBound()
    {
        return GB;
    }

    public a.GF(hd hd, String s)
    {
        GE = hd;
        super();
        Gy = s;
        class a
            implements ServiceConnection
        {

            final hd.a GF;

            public void onServiceConnected(ComponentName componentname, IBinder ibinder)
            {
                java.util.HashMap hashmap = com.google.android.gms.internal.hd.a(GF.GE);
                hashmap;
                JVM INSTR monitorenter ;
                hd.a.a(GF, ibinder);
                hd.a.a(GF, componentname);
                for (Iterator iterator = hd.a.a(GF).iterator(); iterator.hasNext(); ((hb.f)iterator.next()).onServiceConnected(componentname, ibinder)) { }
                break MISSING_BLOCK_LABEL_78;
                Exception exception;
                exception;
                throw exception;
                hd.a.a(GF, 1);
                hashmap;
                JVM INSTR monitorexit ;
            }

            public void onServiceDisconnected(ComponentName componentname)
            {
                java.util.HashMap hashmap = com.google.android.gms.internal.hd.a(GF.GE);
                hashmap;
                JVM INSTR monitorenter ;
                hd.a.a(GF, null);
                hd.a.a(GF, componentname);
                for (Iterator iterator = hd.a.a(GF).iterator(); iterator.hasNext(); ((hb.f)iterator.next()).onServiceDisconnected(componentname)) { }
                break MISSING_BLOCK_LABEL_75;
                Exception exception;
                exception;
                throw exception;
                hd.a.a(GF, 2);
                hashmap;
                JVM INSTR monitorexit ;
            }

            public a()
            {
                GF = hd.a.this;
                super();
            }
        }

        mState = 0;
    }
}
