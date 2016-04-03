// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import java.util.ArrayList;
import java.util.Iterator;

// Referenced classes of package com.google.android.gms.internal:
//            hm

public final class hc
{

    private final b Gp;
    private final ArrayList Gq = new ArrayList();
    final ArrayList Gr = new ArrayList();
    private boolean Gs;
    private final ArrayList Gt = new ArrayList();
    private final Handler mHandler;

    public hc(Context context, Looper looper, b b1)
    {
        Gs = false;
        Gp = b1;
        mHandler = new a(looper);
    }

    static ArrayList a(hc hc1)
    {
        return hc1.Gq;
    }

    static b b(hc hc1)
    {
        return hc1.Gp;
    }

    public final void a(ConnectionResult connectionresult)
    {
        mHandler.removeMessages(1);
        ArrayList arraylist = Gt;
        arraylist;
        JVM INSTR monitorenter ;
        Iterator iterator = (new ArrayList(Gt)).iterator();
_L2:
        com.google.android.gms.common.GooglePlayServicesClient.OnConnectionFailedListener onconnectionfailedlistener;
        if (!iterator.hasNext())
        {
            break MISSING_BLOCK_LABEL_96;
        }
        onconnectionfailedlistener = (com.google.android.gms.common.GooglePlayServicesClient.OnConnectionFailedListener)iterator.next();
        if (!Gp.eO())
        {
            return;
        }
        if (Gt.contains(onconnectionfailedlistener))
        {
            onconnectionfailedlistener.onConnectionFailed(connectionresult);
        }
        if (true) goto _L2; else goto _L1
_L1:
        Exception exception;
        exception;
        throw exception;
        arraylist;
        JVM INSTR monitorexit ;
    }

    public final void ao(int i)
    {
        mHandler.removeMessages(1);
        ArrayList arraylist = Gq;
        arraylist;
        JVM INSTR monitorenter ;
        Gs = true;
        Iterator iterator = (new ArrayList(Gq)).iterator();
        do
        {
            if (!iterator.hasNext())
            {
                break;
            }
            com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks connectioncallbacks = (com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks)iterator.next();
            if (!Gp.eO())
            {
                break;
            }
            if (Gq.contains(connectioncallbacks))
            {
                connectioncallbacks.onConnectionSuspended(i);
            }
        } while (true);
        break MISSING_BLOCK_LABEL_98;
        Exception exception;
        exception;
        throw exception;
        Gs = false;
        arraylist;
        JVM INSTR monitorexit ;
    }

    public final void c(Bundle bundle)
    {
        boolean flag = true;
        ArrayList arraylist = Gq;
        arraylist;
        JVM INSTR monitorenter ;
        boolean flag1;
        if (!Gs)
        {
            flag1 = flag;
        } else
        {
            flag1 = false;
        }
        hm.A(flag1);
        mHandler.removeMessages(1);
        Gs = true;
        Exception exception;
        Iterator iterator;
        if (Gr.size() != 0)
        {
            flag = false;
        }
        hm.A(flag);
        iterator = (new ArrayList(Gq)).iterator();
        do
        {
            if (!iterator.hasNext())
            {
                break;
            }
            com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks connectioncallbacks = (com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks)iterator.next();
            if (!Gp.eO() || !Gp.isConnected())
            {
                break;
            }
            if (!Gr.contains(connectioncallbacks))
            {
                connectioncallbacks.onConnected(bundle);
            }
        } while (true);
        break MISSING_BLOCK_LABEL_154;
        exception;
        throw exception;
        Gr.clear();
        Gs = false;
        arraylist;
        JVM INSTR monitorexit ;
    }

    protected final void cp()
    {
        synchronized (Gq)
        {
            c(Gp.ef());
        }
    }

    public final boolean isConnectionCallbacksRegistered(com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks connectioncallbacks)
    {
        hm.f(connectioncallbacks);
        boolean flag;
        synchronized (Gq)
        {
            flag = Gq.contains(connectioncallbacks);
        }
        return flag;
    }

    public final boolean isConnectionFailedListenerRegistered(com.google.android.gms.common.GooglePlayServicesClient.OnConnectionFailedListener onconnectionfailedlistener)
    {
        hm.f(onconnectionfailedlistener);
        boolean flag;
        synchronized (Gt)
        {
            flag = Gt.contains(onconnectionfailedlistener);
        }
        return flag;
    }

    public final void registerConnectionCallbacks(com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks connectioncallbacks)
    {
        hm.f(connectioncallbacks);
        ArrayList arraylist = Gq;
        arraylist;
        JVM INSTR monitorenter ;
        if (!Gq.contains(connectioncallbacks))
        {
            break MISSING_BLOCK_LABEL_82;
        }
        Log.w("GmsClientEvents", (new StringBuilder("registerConnectionCallbacks(): listener ")).append(connectioncallbacks).append(" is already registered").toString());
_L2:
        if (Gp.isConnected())
        {
            mHandler.sendMessage(mHandler.obtainMessage(1, connectioncallbacks));
        }
        return;
        Gq.add(connectioncallbacks);
        if (true) goto _L2; else goto _L1
_L1:
        Exception exception;
        exception;
        throw exception;
    }

    public final void registerConnectionFailedListener(com.google.android.gms.common.GooglePlayServicesClient.OnConnectionFailedListener onconnectionfailedlistener)
    {
        hm.f(onconnectionfailedlistener);
        ArrayList arraylist = Gt;
        arraylist;
        JVM INSTR monitorenter ;
        if (!Gt.contains(onconnectionfailedlistener))
        {
            break MISSING_BLOCK_LABEL_53;
        }
        Log.w("GmsClientEvents", (new StringBuilder("registerConnectionFailedListener(): listener ")).append(onconnectionfailedlistener).append(" is already registered").toString());
_L2:
        return;
        Gt.add(onconnectionfailedlistener);
        if (true) goto _L2; else goto _L1
_L1:
        Exception exception;
        exception;
        throw exception;
    }

    public final void unregisterConnectionCallbacks(com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks connectioncallbacks)
    {
        hm.f(connectioncallbacks);
        ArrayList arraylist = Gq;
        arraylist;
        JVM INSTR monitorenter ;
        if (Gq == null) goto _L2; else goto _L1
_L1:
        if (Gq.remove(connectioncallbacks)) goto _L4; else goto _L3
_L3:
        Log.w("GmsClientEvents", (new StringBuilder("unregisterConnectionCallbacks(): listener ")).append(connectioncallbacks).append(" not found").toString());
_L2:
        return;
_L4:
        if (Gs)
        {
            Gr.add(connectioncallbacks);
        }
        if (true) goto _L2; else goto _L5
_L5:
        Exception exception;
        exception;
        throw exception;
    }

    public final void unregisterConnectionFailedListener(com.google.android.gms.common.GooglePlayServicesClient.OnConnectionFailedListener onconnectionfailedlistener)
    {
        hm.f(onconnectionfailedlistener);
        synchronized (Gt)
        {
            if (Gt != null && !Gt.remove(onconnectionfailedlistener))
            {
                Log.w("GmsClientEvents", (new StringBuilder("unregisterConnectionFailedListener(): listener ")).append(onconnectionfailedlistener).append(" not found").toString());
            }
        }
    }

    private class a extends Handler
    {

        final hc Gu;

        public final void handleMessage(Message message)
        {
            if (message.what == 1)
            {
                synchronized (hc.a(Gu))
                {
                    if (hc.b(Gu).eO() && hc.b(Gu).isConnected() && hc.a(Gu).contains(message.obj))
                    {
                        Bundle bundle = hc.b(Gu).ef();
                        ((com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks)message.obj).onConnected(bundle);
                    }
                }
                return;
            } else
            {
                Log.wtf("GmsClientEvents", "Don't know how to handle this message.");
                return;
            }
        }

        public a(Looper looper)
        {
            Gu = hc.this;
            super(looper);
        }
    }


    private class b
    {

        public abstract boolean eO();

        public abstract Bundle ef();

        public abstract boolean isConnected();
    }

}
