// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.games.internal;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.games.multiplayer.realtime.Room;
import com.google.android.gms.games.multiplayer.realtime.RoomStatusUpdateListener;

// Referenced classes of package com.google.android.gms.games.internal:
//            GamesClientImpl

final class ck extends ck
{

    final GamesClientImpl NE;

    public final void a(RoomStatusUpdateListener roomstatusupdatelistener, Room room)
    {
        roomstatusupdatelistener.onConnectedToRoom(room);
    }

    ck(GamesClientImpl gamesclientimpl, RoomStatusUpdateListener roomstatusupdatelistener, DataHolder dataholder)
    {
        NE = gamesclientimpl;
        super(gamesclientimpl, roomstatusupdatelistener, dataholder);
    }
}
