// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.wallet.FullWalletRequest;

// Referenced classes of package com.google.android.gms.internal:
//            lr, lq

class b extends com.google.android.gms.wallet.t.b
{

    final int UD;
    final lq akG;
    final FullWalletRequest akI;

    protected volatile void a(com.google.android.gms.common.api. )
    {
        a((lr));
    }

    protected void a(lr lr1)
    {
        lr1.a(akI, UD);
        b(Status.En);
    }

    letRequest(lq lq, FullWalletRequest fullwalletrequest, int i)
    {
        akG = lq;
        akI = fullwalletrequest;
        UD = i;
        super();
    }
}
