// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.games.internal;

import com.google.android.gms.games.multiplayer.realtime.RoomStatusUpdateListener;

// Referenced classes of package com.google.android.gms.games.internal:
//            GamesClientImpl

final class Oq extends com.google.android.gms.internal.lback
{

    final GamesClientImpl NE;
    private final String Oq;

    public final void a(RoomStatusUpdateListener roomstatusupdatelistener)
    {
        if (roomstatusupdatelistener != null)
        {
            roomstatusupdatelistener.onP2PDisconnected(Oq);
        }
    }

    public final void d(Object obj)
    {
        a((RoomStatusUpdateListener)obj);
    }

    protected final void fu()
    {
    }

    (GamesClientImpl gamesclientimpl, RoomStatusUpdateListener roomstatusupdatelistener, String s)
    {
        NE = gamesclientimpl;
        super(gamesclientimpl, roomstatusupdatelistener);
        Oq = s;
    }
}
