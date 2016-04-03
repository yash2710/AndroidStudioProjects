// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.games.internal;

import com.google.android.gms.games.multiplayer.turnbased.OnTurnBasedMatchUpdateReceivedListener;

// Referenced classes of package com.google.android.gms.games.internal:
//            GamesClientImpl

final class Oi extends com.google.android.gms.internal.lback
{

    final GamesClientImpl NE;
    private final String Oi;

    protected final void b(OnTurnBasedMatchUpdateReceivedListener onturnbasedmatchupdatereceivedlistener)
    {
        onturnbasedmatchupdatereceivedlistener.onTurnBasedMatchRemoved(Oi);
    }

    protected final void d(Object obj)
    {
        b((OnTurnBasedMatchUpdateReceivedListener)obj);
    }

    protected final void fu()
    {
    }

    ceivedListener(GamesClientImpl gamesclientimpl, OnTurnBasedMatchUpdateReceivedListener onturnbasedmatchupdatereceivedlistener, String s)
    {
        NE = gamesclientimpl;
        super(gamesclientimpl, onturnbasedmatchupdatereceivedlistener);
        Oi = s;
    }
}
