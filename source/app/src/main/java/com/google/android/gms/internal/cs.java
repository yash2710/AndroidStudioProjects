// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.SystemClock;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

// Referenced classes of package com.google.android.gms.internal:
//            em, cr, cx, et, 
//            eu, cv, cy, dg, 
//            cz

public class cs extends em
    implements ServiceConnection
{

    private final Object ls = new Object();
    private Context mContext;
    private boolean oW;
    private dg oX;
    private cr oY;
    private cx oZ;
    private List pa;
    private cz pb;

    public cs(Context context, dg dg, cz cz)
    {
        oW = false;
        pa = null;
        mContext = context;
        oX = dg;
        pb = cz;
        oY = new cr(context);
        oZ = cx.k(mContext);
        pa = oZ.d(10L);
    }

    static cz a(cs cs1)
    {
        return cs1.pb;
    }

    private void a(cv cv1, String s, String s1)
    {
        Intent intent = new Intent();
        intent.putExtra("RESPONSE_CODE", 0);
        intent.putExtra("INAPP_PURCHASE_DATA", s);
        intent.putExtra("INAPP_DATA_SIGNATURE", s1);
        et.sv.post(new _cls1(cv1, intent));
    }

    static Context b(cs cs1)
    {
        return cs1.mContext;
    }

    private void b(long l)
    {
        do
        {
            if (!c(l))
            {
                eu.D("Timeout waiting for pending transaction to be processed.");
            }
        } while (!oW);
    }

    private void bi()
    {
        if (!pa.isEmpty()) goto _L2; else goto _L1
_L1:
        return;
_L2:
        HashMap hashmap = new HashMap();
        cv cv2;
        for (Iterator iterator = pa.iterator(); iterator.hasNext(); hashmap.put(cv2.pn, cv2))
        {
            cv2 = (cv)iterator.next();
        }

        String s = null;
        do
        {
            String s2;
label0:
            {
                Bundle bundle = oY.b(mContext.getPackageName(), s);
                if (bundle != null && cy.a(bundle) == 0)
                {
                    ArrayList arraylist = bundle.getStringArrayList("INAPP_PURCHASE_ITEM_LIST");
                    ArrayList arraylist1 = bundle.getStringArrayList("INAPP_PURCHASE_DATA_LIST");
                    ArrayList arraylist2 = bundle.getStringArrayList("INAPP_DATA_SIGNATURE_LIST");
                    s2 = bundle.getString("INAPP_CONTINUATION_TOKEN");
                    for (int i = 0; i < arraylist.size(); i++)
                    {
                        if (!hashmap.containsKey(arraylist.get(i)))
                        {
                            continue;
                        }
                        String s3 = (String)arraylist.get(i);
                        String s4 = (String)arraylist1.get(i);
                        String s5 = (String)arraylist2.get(i);
                        cv cv1 = (cv)hashmap.get(s3);
                        String s6 = cy.p(s4);
                        if (cv1.pm.equals(s6))
                        {
                            a(cv1, s4, s5);
                            hashmap.remove(s3);
                        }
                    }

                    if (s2 != null && !hashmap.isEmpty())
                    {
                        break label0;
                    }
                }
                Iterator iterator1 = hashmap.keySet().iterator();
                while (iterator1.hasNext()) 
                {
                    String s1 = (String)iterator1.next();
                    oZ.a((cv)hashmap.get(s1));
                }
            }
            if (true)
            {
                continue;
            }
            s = s2;
        } while (true);
        if (true) goto _L1; else goto _L3
_L3:
    }

    static dg c(cs cs1)
    {
        return cs1.oX;
    }

    private boolean c(long l)
    {
        long l1 = 60000L - (SystemClock.elapsedRealtime() - l);
        if (l1 <= 0L)
        {
            return false;
        }
        try
        {
            ls.wait(l1);
        }
        catch (InterruptedException interruptedexception)
        {
            eu.D("waitWithTimeout_lock interrupted");
        }
        return true;
    }

    public void bh()
    {
        synchronized (ls)
        {
            Context context = mContext;
            Intent intent = new Intent("com.android.vending.billing.InAppBillingService.BIND");
            Context _tmp = mContext;
            context.bindService(intent, this, 1);
            b(SystemClock.elapsedRealtime());
            mContext.unbindService(this);
            oY.destroy();
        }
    }

    public void onServiceConnected(ComponentName componentname, IBinder ibinder)
    {
        synchronized (ls)
        {
            oY.o(ibinder);
            bi();
            oW = true;
            ls.notify();
        }
    }

    public void onServiceDisconnected(ComponentName componentname)
    {
        eu.B("In-app billing service disconnected.");
        oY.destroy();
    }

    public void onStop()
    {
        synchronized (ls)
        {
            mContext.unbindService(this);
            oY.destroy();
        }
    }

    private class _cls1
        implements Runnable
    {

        final cv pc;
        final Intent pd;
        final cs pe;

        public void run()
        {
            if (cs.a(pe).a(pc.pm, -1, pd))
            {
                cs.c(pe).a(new cw(cs.b(pe), pc.pn, true, -1, pd, pc));
                return;
            }
            try
            {
                cs.c(pe).a(new cw(cs.b(pe), pc.pn, false, -1, pd, pc));
                return;
            }
            catch (RemoteException remoteexception)
            {
                eu.D("Fail to verify and dispatch pending transaction");
            }
            return;
        }

        _cls1(cv cv1, Intent intent)
        {
            pe = cs.this;
            pc = cv1;
            pd = intent;
            super();
        }
    }

}
