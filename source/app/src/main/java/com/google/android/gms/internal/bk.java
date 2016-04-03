// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import android.content.Context;
import android.os.Handler;
import java.util.Iterator;
import java.util.List;

// Referenced classes of package com.google.android.gms.internal:
//            eu, bm, bl, bq, 
//            bp, ds, et, bt

public final class bk
{

    private final bt kB;
    private final Object ls = new Object();
    private final Context mContext;
    private final ds ne;
    private final bm nf;
    private boolean ng;
    private bp nh;

    public bk(Context context, ds ds1, bt bt, bm bm1)
    {
        ng = false;
        mContext = context;
        ne = ds1;
        kB = bt;
        nf = bm1;
    }

    public final bq a(long l, long l1)
    {
        Iterator iterator;
        eu.z("Starting mediation.");
        iterator = nf.nr.iterator();
_L4:
        bl bl1;
        Iterator iterator1;
        if (!iterator.hasNext())
        {
            break MISSING_BLOCK_LABEL_244;
        }
        bl1 = (bl)iterator.next();
        eu.B((new StringBuilder("Trying mediation network: ")).append(bl1.nl).toString());
        iterator1 = bl1.nm.iterator();
_L2:
        String s;
label0:
        {
            if (!iterator1.hasNext())
            {
                break; /* Loop/switch isn't completed */
            }
            s = (String)iterator1.next();
            bq bq1;
            synchronized (ls)
            {
                if (!ng)
                {
                    break label0;
                }
                bq1 = new bq(-1);
            }
            return bq1;
        }
        nh = new bp(mContext, s, kB, nf, bl1, ne.pX, ne.kT, ne.kQ);
        obj;
        JVM INSTR monitorexit ;
        bq bq2;
        bq2 = nh.b(l, l1);
        if (bq2.nL == 0)
        {
            eu.z("Adapter succeeded.");
            return bq2;
        }
        break MISSING_BLOCK_LABEL_216;
        exception;
        throw exception;
        if (bq2.nN != null)
        {
            et.sv.post(new _cls1(bq2));
        }
        if (true) goto _L2; else goto _L1
_L1:
        if (true) goto _L4; else goto _L3
_L3:
        return new bq(1);
    }

    public final void cancel()
    {
        synchronized (ls)
        {
            ng = true;
            if (nh != null)
            {
                nh.cancel();
            }
        }
    }

    private class _cls1
        implements Runnable
    {

        final bq ni;
        final bk nj;

        public void run()
        {
            try
            {
                ni.nN.destroy();
                return;
            }
            catch (RemoteException remoteexception)
            {
                eu.c("Could not destroy mediation adapter.", remoteexception);
            }
        }

        _cls1(bq bq1)
        {
            nj = bk.this;
            ni = bq1;
            super();
        }
    }

}
