// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import android.os.RemoteException;

// Referenced classes of package com.google.android.gms.internal:
//            em, dw, eu, du, 
//            ds

public abstract class dq extends em
{

    private final ds ne;
    private final dp.a pT;

    public dq(ds ds, dp.a a1)
    {
        ne = ds;
        pT = a1;
    }

    private static du a(dw dw1, ds ds)
    {
        du du1;
        try
        {
            du1 = dw1.b(ds);
        }
        catch (RemoteException remoteexception)
        {
            eu.c("Could not fetch ad response from ad request service.", remoteexception);
            return null;
        }
        catch (NullPointerException nullpointerexception)
        {
            eu.c("Could not fetch ad response from ad request service due to an Exception.", nullpointerexception);
            return null;
        }
        catch (SecurityException securityexception)
        {
            eu.c("Could not fetch ad response from ad request service due to an Exception.", securityexception);
            return null;
        }
        return du1;
    }

    public final void bh()
    {
        dw dw1 = bt();
        if (dw1 != null) goto _L2; else goto _L1
_L1:
        du du1 = new du(0);
_L4:
        bs();
        pT.a(du1);
        return;
_L2:
        du1 = a(dw1, ne);
        if (du1 != null) goto _L4; else goto _L3
_L3:
        du1 = new du(0);
          goto _L4
        Exception exception;
        exception;
        bs();
        throw exception;
    }

    public abstract void bs();

    public abstract dw bt();

    public final void onStop()
    {
        bs();
    }
}
