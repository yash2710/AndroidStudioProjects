// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.wearable;

import android.app.Service;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Binder;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.util.Log;
import com.google.android.gms.common.GooglePlayServicesUtil;

// Referenced classes of package com.google.android.gms.wearable:
//            DataEventBuffer, MessageEvent, Node

public abstract class WearableListenerService extends Service
    implements DataApi.DataListener, MessageApi.MessageListener, NodeApi.NodeListener
{

    public static final String BIND_LISTENER_INTENT_ACTION = "com.google.android.gms.wearable.BIND_LISTENER";
    private IBinder GC;
    private volatile int alq;
    private Handler alr;
    private Object als;
    private boolean alt;
    private String xQ;

    public WearableListenerService()
    {
        alq = -1;
        als = new Object();
    }

    static String a(WearableListenerService wearablelistenerservice)
    {
        return wearablelistenerservice.xQ;
    }

    static void b(WearableListenerService wearablelistenerservice)
    {
        wearablelistenerservice.nn();
    }

    static Object c(WearableListenerService wearablelistenerservice)
    {
        return wearablelistenerservice.als;
    }

    static boolean d(WearableListenerService wearablelistenerservice)
    {
        return wearablelistenerservice.alt;
    }

    static Handler e(WearableListenerService wearablelistenerservice)
    {
        return wearablelistenerservice.alr;
    }

    private boolean ed(int i)
    {
        String as[];
        boolean flag;
        as = getPackageManager().getPackagesForUid(i);
        flag = false;
        if (as == null) goto _L2; else goto _L1
_L1:
        int j = 0;
_L7:
        int k;
        k = as.length;
        flag = false;
        if (j >= k) goto _L2; else goto _L3
_L3:
        if (!"com.google.android.gms".equals(as[j])) goto _L5; else goto _L4
_L4:
        flag = true;
_L2:
        return flag;
_L5:
        j++;
        if (true) goto _L7; else goto _L6
_L6:
    }

    private void nn()
    {
        int i = Binder.getCallingUid();
        if (i == alq)
        {
            return;
        }
        if (GooglePlayServicesUtil.b(getPackageManager(), "com.google.android.gms") && ed(i))
        {
            alq = i;
            return;
        } else
        {
            throw new SecurityException("Caller is not GooglePlayServices");
        }
    }

    public final IBinder onBind(Intent intent)
    {
        if ("com.google.android.gms.wearable.BIND_LISTENER".equals(intent.getAction()))
        {
            return GC;
        } else
        {
            return null;
        }
    }

    public void onCreate()
    {
        super.onCreate();
        if (Log.isLoggable("WearableLS", 3))
        {
            Log.d("WearableLS", (new StringBuilder("onCreate: ")).append(getPackageName()).toString());
        }
        xQ = getPackageName();
        HandlerThread handlerthread = new HandlerThread("WearableListenerService");
        handlerthread.start();
        alr = new Handler(handlerthread.getLooper());
        GC = new a(null);
    }

    public void onDataChanged(DataEventBuffer dataeventbuffer)
    {
    }

    public void onDestroy()
    {
        synchronized (als)
        {
            alt = true;
            alr.getLooper().quit();
        }
        super.onDestroy();
    }

    public void onMessageReceived(MessageEvent messageevent)
    {
    }

    public void onPeerConnected(Node node)
    {
    }

    public void onPeerDisconnected(Node node)
    {
    }

    private class a extends com.google.android.gms.wearable.internal.ac.a
    {

        final WearableListenerService alu;

        public void Y(DataHolder dataholder)
        {
label0:
            {
                if (Log.isLoggable("WearableLS", 3))
                {
                    Log.d("WearableLS", (new StringBuilder("onDataItemChanged: ")).append(WearableListenerService.a(alu)).append(": ").append(dataholder).toString());
                }
                WearableListenerService.b(alu);
                class _cls1
                    implements Runnable
                {

                    final DataHolder alv;
                    final a alw;

                    public void run()
                    {
                        DataEventBuffer dataeventbuffer = new DataEventBuffer(alv);
                        alw.alu.onDataChanged(dataeventbuffer);
                        dataeventbuffer.release();
                        return;
                        Exception exception1;
                        exception1;
                        dataeventbuffer.release();
                        throw exception1;
                    }

                _cls1(DataHolder dataholder)
                {
                    alw = a.this;
                    alv = dataholder;
                    super();
                }
                }

                synchronized (WearableListenerService.c(alu))
                {
                    if (!WearableListenerService.d(alu))
                    {
                        break label0;
                    }
                    dataholder.close();
                }
                return;
            }
            WearableListenerService.e(alu).post(new _cls1(dataholder));
            obj;
            JVM INSTR monitorexit ;
        }

        public void a(af af)
        {
label0:
            {
                if (Log.isLoggable("WearableLS", 3))
                {
                    Log.d("WearableLS", (new StringBuilder("onMessageReceived: ")).append(af).toString());
                }
                WearableListenerService.b(alu);
                class _cls2
                    implements Runnable
                {

                    final a alw;
                    final af alx;

                    public void run()
                    {
                        alw.alu.onMessageReceived(alx);
                    }

                _cls2(af af)
                {
                    alw = a.this;
                    alx = af;
                    super();
                }
                }

                synchronized (WearableListenerService.c(alu))
                {
                    if (!WearableListenerService.d(alu))
                    {
                        break label0;
                    }
                }
                return;
            }
            WearableListenerService.e(alu).post(new _cls2(af));
            obj;
            JVM INSTR monitorexit ;
        }

        public void a(ai ai)
        {
label0:
            {
                if (Log.isLoggable("WearableLS", 3))
                {
                    Log.d("WearableLS", (new StringBuilder("onPeerConnected: ")).append(WearableListenerService.a(alu)).append(": ").append(ai).toString());
                }
                WearableListenerService.b(alu);
                class _cls3
                    implements Runnable
                {

                    final a alw;
                    final ai aly;

                    public void run()
                    {
                        alw.alu.onPeerConnected(aly);
                    }

                _cls3(ai ai)
                {
                    alw = a.this;
                    aly = ai;
                    super();
                }
                }

                synchronized (WearableListenerService.c(alu))
                {
                    if (!WearableListenerService.d(alu))
                    {
                        break label0;
                    }
                }
                return;
            }
            WearableListenerService.e(alu).post(new _cls3(ai));
            obj;
            JVM INSTR monitorexit ;
        }

        public void b(ai ai)
        {
label0:
            {
                if (Log.isLoggable("WearableLS", 3))
                {
                    Log.d("WearableLS", (new StringBuilder("onPeerDisconnected: ")).append(WearableListenerService.a(alu)).append(": ").append(ai).toString());
                }
                WearableListenerService.b(alu);
                class _cls4
                    implements Runnable
                {

                    final a alw;
                    final ai aly;

                    public void run()
                    {
                        alw.alu.onPeerDisconnected(aly);
                    }

                _cls4(ai ai)
                {
                    alw = a.this;
                    aly = ai;
                    super();
                }
                }

                synchronized (WearableListenerService.c(alu))
                {
                    if (!WearableListenerService.d(alu))
                    {
                        break label0;
                    }
                }
                return;
            }
            WearableListenerService.e(alu).post(new _cls4(ai));
            obj;
            JVM INSTR monitorexit ;
        }

        private a()
        {
            alu = WearableListenerService.this;
            super();
        }

        a(_cls1 _pcls1)
        {
            this();
        }
    }

}
