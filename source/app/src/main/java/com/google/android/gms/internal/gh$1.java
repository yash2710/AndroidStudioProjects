// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import android.os.Handler;
import com.google.android.gms.cast.ApplicationMetadata;
import com.google.android.gms.common.api.Status;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

// Referenced classes of package com.google.android.gms.internal:
//            gh, gn, ge, gj

class <init> extends <init>
{

    final gh Cc;

    private boolean X(int i)
    {
        Object obj = gh.em();
        obj;
        JVM INSTR monitorenter ;
        if (gh.i(Cc) == null)
        {
            break MISSING_BLOCK_LABEL_49;
        }
        gh.i(Cc).(new Status(i));
        gh.b(Cc, null);
        return true;
        obj;
        JVM INSTR monitorexit ;
        return false;
        Exception exception;
        exception;
        throw exception;
    }

    private void b(long l, int i)
    {
        com.google.android.gms.common.api. ;
        synchronized (gh.h(Cc))
        {
             = (com.google.android.gms.common.api.)gh.h(Cc).remove(Long.valueOf(l));
        }
        if ( != null)
        {
            .(new Status(i));
        }
    }

    public void T(int i)
    {
        gn gn1 = gh.ek();
        Object aobj[] = new Object[1];
        aobj[0] = Integer.valueOf(i);
        gn1.b("ICastDeviceControllerListener.onDisconnected: %d", aobj);
        gh.a(Cc, false);
        gh.b(Cc).set(false);
        gh.a(Cc, null);
        if (i != 0)
        {
            Cc.an(2);
        }
    }

    public void U(int i)
    {
        synchronized (gh.el())
        {
            if (gh.c(Cc) != null)
            {
                gh.c(Cc).(new <init>(new Status(i)));
                gh.a(Cc, null);
            }
        }
    }

    public void V(int i)
    {
        X(i);
    }

    public void W(int i)
    {
        X(i);
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
                gh.c(Cc).(new <init>(new Status(0), applicationmetadata, s, s1, flag));
                gh.a(Cc, null);
            }
        }
    }

    public void a(String s, double d, boolean flag)
    {
        gh.ek().b("Deprecated callback: \"onStatusreceived\"", new Object[0]);
    }

    public void a(String s, long l)
    {
        b(l, 0);
    }

    public void a(String s, long l, int i)
    {
        b(l, i);
    }

    public void b(ge ge)
    {
        gh.ek().b("onApplicationStatusChanged", new Object[0]);
        class _cls3
            implements Runnable
        {

            final gh._cls1 Ce;
            final ge Cg;

            public void run()
            {
                gh.a(Ce.Cc, Cg);
            }

            _cls3(ge ge)
            {
                Ce = gh._cls1.this;
                Cg = ge;
                super();
            }
        }

        gh.e(Cc).post(new _cls3(ge));
    }

    public void b(gj gj)
    {
        gh.ek().b("onDeviceStatusChanged", new Object[0]);
        class _cls2
            implements Runnable
        {

            final gh._cls1 Ce;
            final gj Cf;

            public void run()
            {
                gh.a(Ce.Cc, Cf);
            }

            _cls2(gj gj)
            {
                Ce = gh._cls1.this;
                Cf = gj;
                super();
            }
        }

        gh.e(Cc).post(new _cls2(gj));
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

            final gh._cls1 Ce;
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
                Ce = gh._cls1.this;
                zU = s;
                Ch = s1;
                super();
            }
        }

        gh.e(Cc).post(new _cls4(s, s1));
    }

    public void onApplicationDisconnected(int i)
    {
        gh.a(Cc, null);
        gh.b(Cc, null);
        X(i);
        class _cls1
            implements Runnable
        {

            final int Cd;
            final gh._cls1 Ce;

            public void run()
            {
                if (gh.d(Ce.Cc) != null)
                {
                    gh.d(Ce.Cc).onApplicationDisconnected(Cd);
                }
            }

            _cls1(int i)
            {
                Ce = gh._cls1.this;
                Cd = i;
                super();
            }
        }

        if (gh.d(Cc) != null)
        {
            gh.e(Cc).post(new _cls1(i));
        }
    }

    onMetadata(gh gh1)
    {
        Cc = gh1;
        super();
    }
}
