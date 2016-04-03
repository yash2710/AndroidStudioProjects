// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import android.os.Handler;
import java.lang.ref.WeakReference;

// Referenced classes of package com.google.android.gms.internal:
//            et, cj

final class _cls1
{

    private final Runnable lg;
    private volatile boolean oN;

    static boolean a(_cls1 _pcls1)
    {
        return _pcls1.oN;
    }

    public final void bc()
    {
        et.sv.postDelayed(lg, 250L);
    }

    public final void cancel()
    {
        oN = true;
        et.sv.removeCallbacks(lg);
    }

    public _cls1.oP(cj cj)
    {
        oN = false;
        class _cls1
            implements Runnable
        {

            private final WeakReference oO;
            final cj oP;
            final cj.a oQ;

            public void run()
            {
                cj cj1 = (cj)oO.get();
                if (!cj.a.a(oQ) && cj1 != null)
                {
                    cj1.bb();
                    oQ.bc();
                }
            }

            _cls1(cj cj1)
            {
                oQ = cj.a.this;
                oP = cj1;
                super();
                oO = new WeakReference(oP);
            }
        }

        lg = new _cls1(cj);
    }
}
