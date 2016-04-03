// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import android.os.RemoteException;

// Referenced classes of package com.google.android.gms.internal:
//            bq, bu, eu, bk

class ni
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

    (bk bk, bq bq1)
    {
        nj = bk;
        ni = bq1;
        super();
    }
}
