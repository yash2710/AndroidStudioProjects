// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.games.internal;

import android.os.Bundle;
import com.google.android.gms.internal.hm;

// Referenced classes of package com.google.android.gms.games.internal:
//            AbstractGamesCallbacks, GamesClientImpl

final class yR extends AbstractGamesCallbacks
{

    final GamesClientImpl NE;
    private final com.google.android.gms.common.api.nit> yR;

    public final void d(int i, Bundle bundle)
    {
        bundle.setClassLoader(getClass().getClassLoader());
        yR.yR(new (i, bundle));
    }

    (GamesClientImpl gamesclientimpl, com.google.android.gms.common.api.ack ack)
    {
        NE = gamesclientimpl;
        super();
        yR = (com.google.android.gms.common.api.ack.yR)hm.b(ack, "Holder must not be null");
    }
}