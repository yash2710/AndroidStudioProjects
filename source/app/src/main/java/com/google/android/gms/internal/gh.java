// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.RemoteException;
import android.text.TextUtils;
import com.google.android.gms.cast.ApplicationMetadata;
import com.google.android.gms.cast.CastDevice;
import com.google.android.gms.cast.LaunchOptions;
import com.google.android.gms.common.api.Status;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;

// Referenced classes of package com.google.android.gms.internal:
//            hb, gn, ge, gi, 
//            gj, gl, gm, hi

public final class gh extends hb
{

    private static final gn BG = new gn("CastClientImpl");
    private static final Object Ca = new Object();
    private static final Object Cb = new Object();
    private double AP;
    private boolean AQ;
    private final com.google.android.gms.cast.Cast.Listener Ae;
    private ApplicationMetadata BH;
    private final CastDevice BI;
    private final gm BJ = new _cls1();
    private final Map BK = new HashMap();
    private final long BL;
    private String BM;
    private boolean BN;
    private boolean BO;
    private boolean BP;
    private AtomicBoolean BQ;
    private int BR;
    private final AtomicLong BS = new AtomicLong(0L);
    private String BT;
    private String BU;
    private Bundle BV;
    private Map BW;
    private b BX;
    private com.google.android.gms.common.api.a.d BY;
    private com.google.android.gms.common.api.a.d BZ;
    private final Handler mHandler;

    public gh(Context context, Looper looper, CastDevice castdevice, long l, com.google.android.gms.cast.Cast.Listener listener, com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks connectioncallbacks, 
            com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener onconnectionfailedlistener)
    {
        super(context, looper, connectioncallbacks, onconnectionfailedlistener, null);
        BI = castdevice;
        Ae = listener;
        BL = l;
        mHandler = new Handler(looper);
        BP = false;
        BR = -1;
        BH = null;
        BM = null;
        BQ = new AtomicBoolean(false);
        AP = 0.0D;
        AQ = false;
        BW = new HashMap();
        BX = new b(null);
        registerConnectionFailedListener(BX);
    }

    static ApplicationMetadata a(gh gh1, ApplicationMetadata applicationmetadata)
    {
        gh1.BH = applicationmetadata;
        return applicationmetadata;
    }

    static com.google.android.gms.common.api.a.d a(gh gh1, com.google.android.gms.common.api.a.d d1)
    {
        gh1.BY = d1;
        return d1;
    }

    static String a(gh gh1, String s)
    {
        gh1.BT = s;
        return s;
    }

    private void a(ge ge1)
    {
        String s = ge1.ec();
        boolean flag;
        gn gn1;
        Object aobj[];
        if (!gi.a(s, BM))
        {
            BM = s;
            flag = true;
        } else
        {
            flag = false;
        }
        gn1 = BG;
        aobj = new Object[2];
        aobj[0] = Boolean.valueOf(flag);
        aobj[1] = Boolean.valueOf(BN);
        gn1.b("hasChanged=%b, mFirstApplicationStatusUpdate=%b", aobj);
        if (Ae != null && (flag || BN))
        {
            Ae.onApplicationStatusChanged();
        }
        BN = false;
    }

    static void a(gh gh1, ge ge1)
    {
        gh1.a(ge1);
    }

    static void a(gh gh1, gj gj1)
    {
        gh1.a(gj1);
    }

    private void a(gj gj1)
    {
        double d1 = gj1.eh();
        boolean flag;
        boolean flag1;
        gn gn1;
        Object aobj[];
        int k;
        boolean flag2;
        gn gn2;
        Object aobj1[];
        if (d1 != (0.0D / 0.0D) && d1 != AP)
        {
            AP = d1;
            flag = true;
        } else
        {
            flag = false;
        }
        flag1 = gj1.en();
        if (flag1 != AQ)
        {
            AQ = flag1;
            flag = true;
        }
        gn1 = BG;
        aobj = new Object[2];
        aobj[0] = Boolean.valueOf(flag);
        aobj[1] = Boolean.valueOf(BO);
        gn1.b("hasVolumeChanged=%b, mFirstDeviceStatusUpdate=%b", aobj);
        if (Ae != null && (flag || BO))
        {
            Ae.onVolumeChanged();
        }
        k = gj1.eo();
        if (k != BR)
        {
            BR = k;
            flag2 = true;
        } else
        {
            flag2 = false;
        }
        gn2 = BG;
        aobj1 = new Object[2];
        aobj1[0] = Boolean.valueOf(flag2);
        aobj1[1] = Boolean.valueOf(BO);
        gn2.b("hasActiveInputChanged=%b, mFirstDeviceStatusUpdate=%b", aobj1);
        if (Ae != null && (flag2 || BO))
        {
            Ae.O(BR);
        }
        BO = false;
    }

    static boolean a(gh gh1, boolean flag)
    {
        gh1.BP = flag;
        return flag;
    }

    static com.google.android.gms.common.api.a.d b(gh gh1, com.google.android.gms.common.api.a.d d1)
    {
        gh1.BZ = d1;
        return d1;
    }

    static String b(gh gh1, String s)
    {
        gh1.BU = s;
        return s;
    }

    static AtomicBoolean b(gh gh1)
    {
        return gh1.BQ;
    }

    static com.google.android.gms.common.api.a.d c(gh gh1)
    {
        return gh1.BY;
    }

    private void c(com.google.android.gms.common.api.a.d d1)
    {
        synchronized (Ca)
        {
            if (BY != null)
            {
                BY.a(new a(new Status(2002)));
            }
            BY = d1;
        }
    }

    static com.google.android.gms.cast.Cast.Listener d(gh gh1)
    {
        return gh1.Ae;
    }

    static Handler e(gh gh1)
    {
        return gh1.mHandler;
    }

    private void e(com.google.android.gms.common.api.a.d d1)
    {
label0:
        {
            synchronized (Cb)
            {
                if (BZ == null)
                {
                    break label0;
                }
                d1.a(new Status(2001));
            }
            return;
        }
        BZ = d1;
        obj;
        JVM INSTR monitorexit ;
    }

    private void ei()
    {
        BG.b("removing all MessageReceivedCallbacks", new Object[0]);
        synchronized (BK)
        {
            BK.clear();
        }
    }

    private void ej()
    {
        if (!BP || BQ.get())
        {
            throw new IllegalStateException("Not connected to a device");
        } else
        {
            return;
        }
    }

    static gn ek()
    {
        return BG;
    }

    static Object el()
    {
        return Ca;
    }

    static Object em()
    {
        return Cb;
    }

    static Map f(gh gh1)
    {
        return gh1.BK;
    }

    static CastDevice g(gh gh1)
    {
        return gh1.BI;
    }

    static Map h(gh gh1)
    {
        return gh1.BW;
    }

    static com.google.android.gms.common.api.a.d i(gh gh1)
    {
        return gh1.BZ;
    }

    static void j(gh gh1)
    {
        gh1.ei();
    }

    protected final gl G(IBinder ibinder)
    {
        return gl.a.H(ibinder);
    }

    public final void a(double d1)
    {
        if (Double.isInfinite(d1) || Double.isNaN(d1))
        {
            throw new IllegalArgumentException((new StringBuilder("Volume cannot be ")).append(d1).toString());
        } else
        {
            ((gl)ft()).a(d1, AP, AQ);
            return;
        }
    }

    protected final void a(int k, IBinder ibinder, Bundle bundle)
    {
        gn gn1 = BG;
        Object aobj[] = new Object[1];
        aobj[0] = Integer.valueOf(k);
        gn1.b("in onPostInitHandler; statusCode=%d", aobj);
        if (k == 0 || k == 1001)
        {
            BP = true;
            BN = true;
            BO = true;
        } else
        {
            BP = false;
        }
        if (k == 1001)
        {
            BV = new Bundle();
            BV.putBoolean("com.google.android.gms.cast.EXTRA_APP_NO_LONGER_RUNNING", true);
            k = 0;
        }
        super.a(k, ibinder, bundle);
    }

    protected final void a(hi hi1, hb.e e1)
    {
        Bundle bundle = new Bundle();
        gn gn1 = BG;
        Object aobj[] = new Object[2];
        aobj[0] = BT;
        aobj[1] = BU;
        gn1.b("getServiceFromBroker(): mLastApplicationId=%s, mLastSessionId=%s", aobj);
        BI.putInBundle(bundle);
        bundle.putLong("com.google.android.gms.cast.EXTRA_CAST_FLAGS", BL);
        if (BT != null)
        {
            bundle.putString("last_application_id", BT);
            if (BU != null)
            {
                bundle.putString("last_session_id", BU);
            }
        }
        hi1.a(e1, 0x4da6e8, getContext().getPackageName(), BJ.asBinder(), bundle);
    }

    public final void a(String s, com.google.android.gms.cast.Cast.MessageReceivedCallback messagereceivedcallback)
    {
        gi.ak(s);
        aj(s);
        if (messagereceivedcallback != null)
        {
            synchronized (BK)
            {
                BK.put(s, messagereceivedcallback);
            }
            ((gl)ft()).an(s);
        }
    }

    public final void a(String s, LaunchOptions launchoptions, com.google.android.gms.common.api.a.d d1)
    {
        c(d1);
        ((gl)ft()).a(s, launchoptions);
    }

    public final void a(String s, com.google.android.gms.common.api.a.d d1)
    {
        e(d1);
        ((gl)ft()).am(s);
    }

    public final void a(String s, String s1, com.google.android.gms.common.api.a.d d1)
    {
        if (TextUtils.isEmpty(s1))
        {
            throw new IllegalArgumentException("The message payload cannot be null or empty");
        }
        if (s1.length() > 0x10000)
        {
            throw new IllegalArgumentException("Message exceeds maximum size");
        } else
        {
            gi.ak(s);
            ej();
            long l = BS.incrementAndGet();
            ((gl)ft()).a(s, s1, l);
            BW.put(Long.valueOf(l), d1);
            return;
        }
    }

    public final void a(String s, boolean flag, com.google.android.gms.common.api.a.d d1)
    {
        c(d1);
        ((gl)ft()).e(s, flag);
    }

    public final void aj(String s)
    {
        if (TextUtils.isEmpty(s))
        {
            throw new IllegalArgumentException("Channel namespace cannot be null or empty");
        }
        com.google.android.gms.cast.Cast.MessageReceivedCallback messagereceivedcallback;
        synchronized (BK)
        {
            messagereceivedcallback = (com.google.android.gms.cast.Cast.MessageReceivedCallback)BK.remove(s);
        }
        if (messagereceivedcallback == null)
        {
            break MISSING_BLOCK_LABEL_60;
        }
        ((gl)ft()).ao(s);
        return;
        IllegalStateException illegalstateexception;
        illegalstateexception;
        gn gn1 = BG;
        Object aobj[] = new Object[2];
        aobj[0] = s;
        aobj[1] = illegalstateexception.getMessage();
        gn1.a(illegalstateexception, "Error unregistering namespace (%s): %s", aobj);
        return;
    }

    public final void b(String s, String s1, com.google.android.gms.common.api.a.d d1)
    {
        c(d1);
        ((gl)ft()).h(s, s1);
    }

    protected final String bu()
    {
        return "com.google.android.gms.cast.service.BIND_CAST_DEVICE_CONTROLLER_SERVICE";
    }

    protected final String bv()
    {
        return "com.google.android.gms.cast.internal.ICastDeviceController";
    }

    public final void d(com.google.android.gms.common.api.a.d d1)
    {
        e(d1);
        ((gl)ft()).ep();
    }

    public final void disconnect()
    {
        gn gn1 = BG;
        Object aobj[] = new Object[2];
        aobj[0] = Boolean.valueOf(BQ.get());
        aobj[1] = Boolean.valueOf(isConnected());
        gn1.b("disconnect(); mDisconnecting=%b, isConnected=%b", aobj);
        if (BQ.getAndSet(true))
        {
            BG.b("mDisconnecting is set, so short-circuiting", new Object[0]);
            return;
        }
        ei();
        if (isConnected() || isConnecting())
        {
            ((gl)ft()).disconnect();
        }
        super.disconnect();
        return;
        RemoteException remoteexception;
        remoteexception;
        gn gn2 = BG;
        Object aobj1[] = new Object[1];
        aobj1[0] = remoteexception.getMessage();
        gn2.a(remoteexception, "Error while disconnecting the controller interface: %s", aobj1);
        super.disconnect();
        return;
        Exception exception;
        exception;
        super.disconnect();
        throw exception;
    }

    public final Bundle ef()
    {
        if (BV != null)
        {
            Bundle bundle = BV;
            BV = null;
            return bundle;
        } else
        {
            return super.ef();
        }
    }

    public final void eg()
    {
        ((gl)ft()).eg();
    }

    public final double eh()
    {
        ej();
        return AP;
    }

    public final ApplicationMetadata getApplicationMetadata()
    {
        ej();
        return BH;
    }

    public final String getApplicationStatus()
    {
        ej();
        return BM;
    }

    public final boolean isMute()
    {
        ej();
        return AQ;
    }

    protected final IInterface x(IBinder ibinder)
    {
        return G(ibinder);
    }

    public final void y(boolean flag)
    {
        ((gl)ft()).a(flag, AP, AQ);
    }


    private class b
        implements com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener
    {

        final gh Cc;

        public void onConnectionFailed(ConnectionResult connectionresult)
        {
            gh.j(Cc);
        }

        private b()
        {
            Cc = gh.this;
            super();
        }

        b(_cls1 _pcls1)
        {
            this();
        }
    }


    private class _cls1 extends gm.a
    {

        final gh Cc;

        private boolean X(int k)
        {
            Object obj = gh.em();
            obj;
            JVM INSTR monitorenter ;
            if (gh.i(Cc) == null)
            {
                break MISSING_BLOCK_LABEL_49;
            }
            gh.i(Cc).a(new Status(k));
            gh.b(Cc, null);
            return true;
            obj;
            JVM INSTR monitorexit ;
            return false;
            Exception exception;
            exception;
            throw exception;
        }

        private void b(long l, int k)
        {
            com.google.android.gms.common.api.a.d d1;
            synchronized (gh.h(Cc))
            {
                d1 = (com.google.android.gms.common.api.a.d)gh.h(Cc).remove(Long.valueOf(l));
            }
            if (d1 != null)
            {
                d1.a(new Status(k));
            }
        }

        public void T(int k)
        {
            gn gn1 = gh.ek();
            Object aobj[] = new Object[1];
            aobj[0] = Integer.valueOf(k);
            gn1.b("ICastDeviceControllerListener.onDisconnected: %d", aobj);
            gh.a(Cc, false);
            gh.b(Cc).set(false);
            gh.a(Cc, null);
            if (k != 0)
            {
                Cc.an(2);
            }
        }

        public void U(int k)
        {
            synchronized (gh.el())
            {
                if (gh.c(Cc) != null)
                {
                    gh.c(Cc).a(new a(new Status(k)));
                    gh.a(Cc, null);
                }
            }
        }

        public void V(int k)
        {
            X(k);
        }

        public void W(int k)
        {
            X(k);
        }

        public void a(ApplicationMetadata applicationmetadata, String s, String s1, boolean flag)
        {
            gh.a(Cc, applicationmetadata);
            gh.a(Cc, applicationmetadata.getApplicationId());
            gh.b(Cc, s1);
            synchronized (gh.el())
            {
                if (gh.c(Cc) != null)
                {
                    gh.c(Cc).a(new a(new Status(0), applicationmetadata, s, s1, flag));
                    gh.a(Cc, null);
                }
            }
        }

        public void a(String s, double d1, boolean flag)
        {
            gh.ek().b("Deprecated callback: \"onStatusreceived\"", new Object[0]);
        }

        public void a(String s, long l)
        {
            b(l, 0);
        }

        public void a(String s, long l, int k)
        {
            b(l, k);
        }

        public void b(ge ge1)
        {
            gh.ek().b("onApplicationStatusChanged", new Object[0]);
            class _cls3
                implements Runnable
            {

                final _cls1 Ce;
                final ge Cg;

                public void run()
                {
                    gh.a(Ce.Cc, Cg);
                }

                _cls3(ge ge1)
                {
                    Ce = _cls1.this;
                    Cg = ge1;
                    super();
                }
            }

            gh.e(Cc).post(new _cls3(ge1));
        }

        public void b(gj gj1)
        {
            gh.ek().b("onDeviceStatusChanged", new Object[0]);
            class _cls2
                implements Runnable
            {

                final _cls1 Ce;
                final gj Cf;

                public void run()
                {
                    gh.a(Ce.Cc, Cf);
                }

                _cls2(gj gj1)
                {
                    Ce = _cls1.this;
                    Cf = gj1;
                    super();
                }
            }

            gh.e(Cc).post(new _cls2(gj1));
        }

        public void b(String s, byte abyte0[])
        {
            gn gn1 = gh.ek();
            Object aobj[] = new Object[2];
            aobj[0] = s;
            aobj[1] = Integer.valueOf(abyte0.length);
            gn1.b("IGNORING: Receive (type=binary, ns=%s) <%d bytes>", aobj);
        }

        public void g(String s, String s1)
        {
            gh.ek().b("Receive (type=text, ns=%s) %s", new Object[] {
                s, s1
            });
            class _cls4
                implements Runnable
            {

                final _cls1 Ce;
                final String Ch;
                final String zU;

                public void run()
                {
                    com.google.android.gms.cast.Cast.MessageReceivedCallback messagereceivedcallback;
                    synchronized (gh.f(Ce.Cc))
                    {
                        messagereceivedcallback = (com.google.android.gms.cast.Cast.MessageReceivedCallback)gh.f(Ce.Cc).get(zU);
                    }
                    if (messagereceivedcallback != null)
                    {
                        messagereceivedcallback.onMessageReceived(gh.g(Ce.Cc), zU, Ch);
                        return;
                    } else
                    {
                        gn gn1 = gh.ek();
                        Object aobj[] = new Object[1];
                        aobj[0] = zU;
                        gn1.b("Discarded message for unknown namespace '%s'", aobj);
                        return;
                    }
                }

                _cls4(String s, String s1)
                {
                    Ce = _cls1.this;
                    zU = s;
                    Ch = s1;
                    super();
                }
            }

            gh.e(Cc).post(new _cls4(s, s1));
        }

        public void onApplicationDisconnected(int k)
        {
            gh.a(Cc, null);
            gh.b(Cc, null);
            X(k);
            class _cls1
                implements Runnable
            {

                final int Cd;
                final _cls1 Ce;

                public void run()
                {
                    if (gh.d(Ce.Cc) != null)
                    {
                        gh.d(Ce.Cc).onApplicationDisconnected(Cd);
                    }
                }

                _cls1(int k)
                {
                    Ce = _cls1.this;
                    Cd = k;
                    super();
                }
            }

            if (gh.d(Cc) != null)
            {
                gh.e(Cc).post(new _cls1(k));
            }
        }

        _cls1()
        {
            Cc = gh.this;
            super();
        }
    }


    private class a
        implements com.google.android.gms.cast.Cast.ApplicationConnectionResult
    {

        private final ApplicationMetadata Ci;
        private final String Cj;
        private final boolean Ck;
        private final String rR;
        private final Status yz;

        public final ApplicationMetadata getApplicationMetadata()
        {
            return Ci;
        }

        public final String getApplicationStatus()
        {
            return Cj;
        }

        public final String getSessionId()
        {
            return rR;
        }

        public final Status getStatus()
        {
            return yz;
        }

        public final boolean getWasLaunched()
        {
            return Ck;
        }

        public a(Status status)
        {
            this(status, null, null, null, false);
        }

        public a(Status status, ApplicationMetadata applicationmetadata, String s, String s1, boolean flag)
        {
            yz = status;
            Ci = applicationmetadata;
            Cj = s;
            rR = s1;
            Ck = flag;
        }
    }

}
