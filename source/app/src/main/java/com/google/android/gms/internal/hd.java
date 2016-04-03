// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import java.util.HashMap;

public final class hd
    implements android.os.Handler.Callback
{

    private static final Object Gv = new Object();
    private static hd Gw;
    private final HashMap Gx = new HashMap();
    private final Context lz;
    private final Handler mHandler;

    private hd(Context context)
    {
        mHandler = new Handler(context.getMainLooper(), this);
        lz = context.getApplicationContext();
    }

    public static hd E(Context context)
    {
        synchronized (Gv)
        {
            if (Gw == null)
            {
                Gw = new hd(context.getApplicationContext());
            }
        }
        return Gw;
    }

    static HashMap a(hd hd1)
    {
        return hd1.Gx;
    }

    public final boolean a(String s, hb.f f)
    {
        HashMap hashmap = Gx;
        hashmap;
        JVM INSTR monitorenter ;
        a a1 = (a)Gx.get(s);
        if (a1 != null) goto _L2; else goto _L1
_L1:
        a1 = new a(s);
        a1.a(f);
        Intent intent = (new Intent(s)).setPackage("com.google.android.gms");
        a1.B(lz.bindService(intent, a1.fx(), 129));
        Gx.put(s, a1);
_L7:
        boolean flag = a1.isBound();
        hashmap;
        JVM INSTR monitorexit ;
        return flag;
_L2:
        mHandler.removeMessages(0, a1);
        if (a1.c(f))
        {
            throw new IllegalStateException((new StringBuilder("Trying to bind a GmsServiceConnection that was already connected before.  startServiceAction=")).append(s).toString());
        }
        break MISSING_BLOCK_LABEL_152;
        Exception exception;
        exception;
        hashmap;
        JVM INSTR monitorexit ;
        throw exception;
        a1.a(f);
        a1.getState();
        JVM INSTR tableswitch 1 2: default 241
    //                   1 184
    //                   2 201;
           goto _L3 _L4 _L5
_L3:
        if (true) goto _L7; else goto _L6
_L6:
_L4:
        f.onServiceConnected(a1.getComponentName(), a1.getBinder());
          goto _L7
_L5:
        Intent intent1 = (new Intent(s)).setPackage("com.google.android.gms");
        a1.B(lz.bindService(intent1, a1.fx(), 129));
          goto _L7
    }

    public final void b(String s, hb.f f)
    {
        HashMap hashmap = Gx;
        hashmap;
        JVM INSTR monitorenter ;
        a a1 = (a)Gx.get(s);
        if (a1 != null)
        {
            break MISSING_BLOCK_LABEL_56;
        }
        throw new IllegalStateException((new StringBuilder("Nonexistent connection status for service action: ")).append(s).toString());
        Exception exception;
        exception;
        hashmap;
        JVM INSTR monitorexit ;
        throw exception;
        if (!a1.c(f))
        {
            throw new IllegalStateException((new StringBuilder("Trying to unbind a GmsServiceConnection  that was not bound before.  startServiceAction=")).append(s).toString());
        }
        a1.b(f);
        if (a1.fz())
        {
            Message message = mHandler.obtainMessage(0, a1);
            mHandler.sendMessageDelayed(message, 5000L);
        }
        hashmap;
        JVM INSTR monitorexit ;
    }

    public final boolean handleMessage(Message message)
    {
        a a1;
        switch (message.what)
        {
        default:
            return false;

        case 0: // '\0'
            a1 = (a)message.obj;
            break;
        }
        synchronized (Gx)
        {
            if (a1.fz())
            {
                lz.unbindService(a1.fx());
                Gx.remove(a1.fy());
            }
        }
        return true;
    }


    private class a
    {

        private final HashSet GA = new HashSet();
        private boolean GB;
        private IBinder GC;
        private ComponentName GD;
        final hd GE;
        private final String Gy;
        private final a Gz = new a();
        private int mState;

        static int a(a a1, int i)
        {
            a1.mState = i;
            return i;
        }

        static ComponentName a(a a1, ComponentName componentname)
        {
            a1.GD = componentname;
            return componentname;
        }

        static IBinder a(a a1, IBinder ibinder)
        {
            a1.GC = ibinder;
            return ibinder;
        }

        static HashSet a(a a1)
        {
            return a1.GA;
        }

        public final void B(boolean flag)
        {
            GB = flag;
        }

        public final void a(hb.f f)
        {
            GA.add(f);
        }

        public final void b(hb.f f)
        {
            GA.remove(f);
        }

        public final boolean c(hb.f f)
        {
            return GA.contains(f);
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

        public a(String s)
        {
            GE = hd.this;
            super();
            Gy = s;
            class a
                implements ServiceConnection
            {

                final a GF;

                public void onServiceConnected(ComponentName componentname, IBinder ibinder)
                {
                    HashMap hashmap = hd.a(GF.GE);
                    hashmap;
                    JVM INSTR monitorenter ;
                    a.a(GF, ibinder);
                    a.a(GF, componentname);
                    for (Iterator iterator = a.a(GF).iterator(); iterator.hasNext(); ((hb.f)iterator.next()).onServiceConnected(componentname, ibinder)) { }
                    break MISSING_BLOCK_LABEL_78;
                    Exception exception;
                    exception;
                    throw exception;
                    a.a(GF, 1);
                    hashmap;
                    JVM INSTR monitorexit ;
                }

                public void onServiceDisconnected(ComponentName componentname)
                {
                    HashMap hashmap = hd.a(GF.GE);
                    hashmap;
                    JVM INSTR monitorenter ;
                    a.a(GF, null);
                    a.a(GF, componentname);
                    for (Iterator iterator = a.a(GF).iterator(); iterator.hasNext(); ((hb.f)iterator.next()).onServiceDisconnected(componentname)) { }
                    break MISSING_BLOCK_LABEL_75;
                    Exception exception;
                    exception;
                    throw exception;
                    a.a(GF, 2);
                    hashmap;
                    JVM INSTR monitorexit ;
                }

                public a()
                {
                    GF = a.this;
                    super();
                }
            }

            mState = 0;
        }
    }

}
