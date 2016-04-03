// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.wearable;

import android.os.Handler;
import android.util.Log;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.wearable.internal.af;
import com.google.android.gms.wearable.internal.ai;

// Referenced classes of package com.google.android.gms.wearable:
//            WearableListenerService

class <init> extends com.google.android.gms.wearable.internal.
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
                final WearableListenerService.a alw;

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
                alw = WearableListenerService.a.this;
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

                final WearableListenerService.a alw;
                final af alx;

                public void run()
                {
                    alw.alu.onMessageReceived(alx);
                }

            _cls2(af af)
            {
                alw = WearableListenerService.a.this;
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

                final WearableListenerService.a alw;
                final ai aly;

                public void run()
                {
                    alw.alu.onPeerConnected(aly);
                }

            _cls3(ai ai)
            {
                alw = WearableListenerService.a.this;
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

                final WearableListenerService.a alw;
                final ai aly;

                public void run()
                {
                    alw.alu.onPeerDisconnected(aly);
                }

            _cls4(ai ai)
            {
                alw = WearableListenerService.a.this;
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

    private _cls4(WearableListenerService wearablelistenerservice)
    {
        alu = wearablelistenerservice;
        super();
    }

    alu(WearableListenerService wearablelistenerservice, alu alu1)
    {
        this(wearablelistenerservice);
    }
}
