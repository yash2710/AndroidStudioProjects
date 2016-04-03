// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import android.content.Context;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.RemoteException;
import android.util.Log;

// Referenced classes of package com.google.android.gms.internal:
//            hb, hm, gc, hi

public final class ga extends hb
{

    private final String yQ;

    public ga(Context context, Looper looper, com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks connectioncallbacks, com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener onconnectionfailedlistener, String s, String as[])
    {
        super(context, looper, connectioncallbacks, onconnectionfailedlistener, as);
        yQ = (String)hm.f(s);
    }

    protected final gc D(IBinder ibinder)
    {
        return gc.a.F(ibinder);
    }

    public final void a(com.google.android.gms.common.api.a.d d)
    {
        try
        {
            ((gc)ft()).a(new c(d));
            return;
        }
        catch (RemoteException remoteexception)
        {
            Log.w("AppStateClient", "service died");
        }
    }

    public final void a(com.google.android.gms.common.api.a.d d, int i)
    {
        try
        {
            ((gc)ft()).b(new a(d), i);
            return;
        }
        catch (RemoteException remoteexception)
        {
            Log.w("AppStateClient", "service died");
        }
    }

    public final void a(com.google.android.gms.common.api.a.d d, int i, String s, byte abyte0[])
    {
        try
        {
            ((gc)ft()).a(new e(d), i, s, abyte0);
            return;
        }
        catch (RemoteException remoteexception)
        {
            Log.w("AppStateClient", "service died");
        }
    }

    public final void a(com.google.android.gms.common.api.a.d d, int i, byte abyte0[])
    {
        if (d != null) goto _L2; else goto _L1
_L1:
        Object obj = null;
_L3:
        e e1;
        try
        {
            ((gc)ft()).a(((gb) (obj)), i, abyte0);
            return;
        }
        catch (RemoteException remoteexception)
        {
            Log.w("AppStateClient", "service died");
        }
        break MISSING_BLOCK_LABEL_51;
_L2:
        e1 = new e(d);
        obj = e1;
          goto _L3
    }

    protected final void a(hi hi1, hb.e e1)
    {
        hi1.a(e1, 0x4da6e8, getContext().getPackageName(), yQ, fs());
    }

    public final void b(com.google.android.gms.common.api.a.d d)
    {
        try
        {
            ((gc)ft()).b(new g(d));
            return;
        }
        catch (RemoteException remoteexception)
        {
            Log.w("AppStateClient", "service died");
        }
    }

    public final void b(com.google.android.gms.common.api.a.d d, int i)
    {
        try
        {
            ((gc)ft()).a(new e(d), i);
            return;
        }
        catch (RemoteException remoteexception)
        {
            Log.w("AppStateClient", "service died");
        }
    }

    protected final transient void b(String as[])
    {
        int i = 0;
        boolean flag = false;
        for (; i < as.length; i++)
        {
            if (as[i].equals("https://www.googleapis.com/auth/appstate"))
            {
                flag = true;
            }
        }

        hm.a(flag, String.format("App State APIs requires %s to function.", new Object[] {
            "https://www.googleapis.com/auth/appstate"
        }));
    }

    protected final String bu()
    {
        return "com.google.android.gms.appstate.service.START";
    }

    protected final String bv()
    {
        return "com.google.android.gms.appstate.internal.IAppStateService";
    }

    public final int dU()
    {
        int i;
        try
        {
            i = ((gc)ft()).dU();
        }
        catch (RemoteException remoteexception)
        {
            Log.w("AppStateClient", "service died");
            return 2;
        }
        return i;
    }

    public final int dV()
    {
        int i;
        try
        {
            i = ((gc)ft()).dV();
        }
        catch (RemoteException remoteexception)
        {
            Log.w("AppStateClient", "service died");
            return 2;
        }
        return i;
    }

    protected final IInterface x(IBinder ibinder)
    {
        return D(ibinder);
    }

    private class c extends fz
    {

        private final com.google.android.gms.common.api.a.d yR;

        public final void a(DataHolder dataholder)
        {
            yR.a(new d(dataholder));
        }

        public c(com.google.android.gms.common.api.a.d d)
        {
            yR = (com.google.android.gms.common.api.a.d)hm.b(d, "Result holder must not be null");
        }

        private class d extends b
            implements com.google.android.gms.appstate.AppStateManager.StateListResult
        {

            private final AppStateBuffer yT;

            public final AppStateBuffer getStateBuffer()
            {
                return yT;
            }

            public d(DataHolder dataholder)
            {
                super(dataholder);
                yT = new AppStateBuffer(dataholder);
            }
        }

    }


    private class a extends fz
    {

        private final com.google.android.gms.common.api.a.d yR;

        public final void b(int i, int j)
        {
            Status status = new Status(i);
            yR.a(new b(status, j));
        }

        public a(com.google.android.gms.common.api.a.d d)
        {
            yR = (com.google.android.gms.common.api.a.d)hm.b(d, "Result holder must not be null");
        }

        private class b
            implements com.google.android.gms.appstate.AppStateManager.StateDeletedResult
        {

            private final int yS;
            private final Status yz;

            public final int getStateKey()
            {
                return yS;
            }

            public final Status getStatus()
            {
                return yz;
            }

            public b(Status status, int i)
            {
                yz = status;
                yS = i;
            }
        }

    }


    private class e extends fz
    {

        private final com.google.android.gms.common.api.a.d yR;

        public final void a(int i, DataHolder dataholder)
        {
            yR.a(new f(i, dataholder));
        }

        public e(com.google.android.gms.common.api.a.d d)
        {
            yR = (com.google.android.gms.common.api.a.d)hm.b(d, "Result holder must not be null");
        }

        private class f extends com.google.android.gms.common.api.b
            implements com.google.android.gms.appstate.AppStateManager.StateConflictResult, com.google.android.gms.appstate.AppStateManager.StateLoadedResult, com.google.android.gms.appstate.AppStateManager.StateResult
        {

            private final int yS;
            private final AppStateBuffer yT;

            private boolean dW()
            {
                return yz.getStatusCode() == 2000;
            }

            public final com.google.android.gms.appstate.AppStateManager.StateConflictResult getConflictResult()
            {
                if (dW())
                {
                    return this;
                } else
                {
                    return null;
                }
            }

            public final com.google.android.gms.appstate.AppStateManager.StateLoadedResult getLoadedResult()
            {
                if (dW())
                {
                    this = null;
                }
                return this;
            }

            public final byte[] getLocalData()
            {
                if (yT.getCount() == 0)
                {
                    return null;
                } else
                {
                    return yT.get(0).getLocalData();
                }
            }

            public final String getResolvedVersion()
            {
                if (yT.getCount() == 0)
                {
                    return null;
                } else
                {
                    return yT.get(0).getConflictVersion();
                }
            }

            public final byte[] getServerData()
            {
                if (yT.getCount() == 0)
                {
                    return null;
                } else
                {
                    return yT.get(0).getConflictData();
                }
            }

            public final int getStateKey()
            {
                return yS;
            }

            public final void release()
            {
                yT.close();
            }

            public f(int i, DataHolder dataholder)
            {
                super(dataholder);
                yS = i;
                yT = new AppStateBuffer(dataholder);
            }
        }

    }


    private class g extends fz
    {

        private final com.google.android.gms.common.api.a.d yR;

        public final void dT()
        {
            Status status = new Status(0);
            yR.a(status);
        }

        public g(com.google.android.gms.common.api.a.d d)
        {
            yR = (com.google.android.gms.common.api.a.d)hm.b(d, "Holder must not be null");
        }
    }

}
