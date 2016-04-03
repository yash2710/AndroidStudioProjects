// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.tagmanager;

import android.content.Context;
import android.os.Process;
import com.google.android.gms.internal.ij;
import com.google.android.gms.internal.il;

// Referenced classes of package com.google.android.gms.tagmanager:
//            bh

class a
{

    private static com.google.android.gms.tagmanager.a aeh;
    private static Object tq = new Object();
    private volatile long aec;
    private volatile long aed;
    private volatile long aee;
    private final ij aef;
    private a aeg;
    private volatile boolean mClosed;
    private final Context mContext;
    private final Thread sf;
    private volatile com.google.android.gms.ads.identifier.AdvertisingIdClient.Info ts;

    private a(Context context)
    {
        this(context, null, il.gb());
    }

    a(Context context, a a1, ij ij1)
    {
        aec = 0xdbba0L;
        aed = 30000L;
        mClosed = false;
        aeg = new _cls1();
        aef = ij1;
        if (context != null)
        {
            mContext = context.getApplicationContext();
        } else
        {
            mContext = context;
        }
        if (a1 != null)
        {
            aeg = a1;
        }
        sf = new Thread(new _cls2());
    }

    static a M(Context context)
    {
        if (aeh == null)
        {
            synchronized (tq)
            {
                if (aeh == null)
                {
                    a a1 = new a(context);
                    aeh = a1;
                    a1.start();
                }
            }
        }
        return aeh;
    }

    static Context a(a a1)
    {
        return a1.mContext;
    }

    static void b(a a1)
    {
        a1.le();
    }

    private void le()
    {
        Process.setThreadPriority(10);
        while (!mClosed) 
        {
            try
            {
                ts = aeg.lg();
                Thread.sleep(aec);
            }
            catch (InterruptedException interruptedexception)
            {
                bh.B("sleep interrupted in AdvertiserDataPoller thread; continuing");
            }
        }
    }

    private void lf()
    {
        if (aef.currentTimeMillis() - aee < aed)
        {
            return;
        } else
        {
            interrupt();
            aee = aef.currentTimeMillis();
            return;
        }
    }

    void interrupt()
    {
        sf.interrupt();
    }

    public boolean isLimitAdTrackingEnabled()
    {
        lf();
        if (ts == null)
        {
            return true;
        } else
        {
            return ts.isLimitAdTrackingEnabled();
        }
    }

    public String ld()
    {
        lf();
        if (ts == null)
        {
            return null;
        } else
        {
            return ts.getId();
        }
    }

    void start()
    {
        sf.start();
    }


    private class _cls1
        implements a
    {

        final com.google.android.gms.tagmanager.a aei;

        public com.google.android.gms.ads.identifier.AdvertisingIdClient.Info lg()
        {
            com.google.android.gms.ads.identifier.AdvertisingIdClient.Info info;
            try
            {
                info = AdvertisingIdClient.getAdvertisingIdInfo(com.google.android.gms.tagmanager.a.a(aei));
            }
            catch (IllegalStateException illegalstateexception)
            {
                bh.D("IllegalStateException getting Advertising Id Info");
                return null;
            }
            catch (GooglePlayServicesRepairableException googleplayservicesrepairableexception)
            {
                bh.D("GooglePlayServicesRepairableException getting Advertising Id Info");
                return null;
            }
            catch (IOException ioexception)
            {
                bh.D("IOException getting Ad Id Info");
                return null;
            }
            catch (GooglePlayServicesNotAvailableException googleplayservicesnotavailableexception)
            {
                bh.D("GooglePlayServicesNotAvailableException getting Advertising Id Info");
                return null;
            }
            catch (Exception exception)
            {
                bh.D("Unknown exception. Could not get the Advertising Id Info.");
                return null;
            }
            return info;
        }

        _cls1()
        {
            aei = a.this;
            super();
        }
    }


    private class _cls2
        implements Runnable
    {

        final com.google.android.gms.tagmanager.a aei;

        public void run()
        {
            com.google.android.gms.tagmanager.a.b(aei);
        }

        _cls2()
        {
            aei = a.this;
            super();
        }
    }


    private class a
    {

        public abstract com.google.android.gms.ads.identifier.AdvertisingIdClient.Info lg();
    }

}
