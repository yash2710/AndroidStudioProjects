// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;


// Referenced classes of package com.google.android.gms.internal:
//            bn

public final class bo extends bv.a
{

    private final Object ls = new Object();
    private bq.a nA;
    private bn nB;

    public bo()
    {
    }

    public final void a(bn bn1)
    {
        synchronized (ls)
        {
            nB = bn1;
        }
    }

    public final void a(bq.a a1)
    {
        synchronized (ls)
        {
            nA = a1;
        }
    }

    public final void onAdClicked()
    {
        synchronized (ls)
        {
            if (nB != null)
            {
                nB.ab();
            }
        }
    }

    public final void onAdClosed()
    {
        synchronized (ls)
        {
            if (nB != null)
            {
                nB.ac();
            }
        }
    }

    public final void onAdFailedToLoad(int i)
    {
        Object obj = ls;
        obj;
        JVM INSTR monitorenter ;
        if (nA == null)
        {
            break MISSING_BLOCK_LABEL_38;
        }
        int j;
        if (i == 3)
        {
            j = 1;
        } else
        {
            j = 2;
        }
        nA.g(j);
        nA = null;
        obj;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public final void onAdLeftApplication()
    {
        synchronized (ls)
        {
            if (nB != null)
            {
                nB.ad();
            }
        }
    }

    public final void onAdLoaded()
    {
label0:
        {
            synchronized (ls)
            {
                if (nA == null)
                {
                    break label0;
                }
                nA.g(0);
                nA = null;
            }
            return;
        }
        if (nB != null)
        {
            nB.af();
        }
        obj;
        JVM INSTR monitorexit ;
    }

    public final void onAdOpened()
    {
        synchronized (ls)
        {
            if (nB != null)
            {
                nB.ae();
            }
        }
    }
}
