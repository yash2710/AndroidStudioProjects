// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.games.internal;

import android.os.Bundle;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.multiplayer.turnbased.LoadMatchesResponse;

final class >
    implements com.google.android.gms.games.multiplayer.turnbased.
{

    private final LoadMatchesResponse NZ;
    private final Status yz;

    public final LoadMatchesResponse getMatches()
    {
        return NZ;
    }

    public final Status getStatus()
    {
        return yz;
    }

    public final void release()
    {
        NZ.close();
    }

    MatchesResult(Status status, Bundle bundle)
    {
        yz = status;
        NZ = new LoadMatchesResponse(bundle);
    }
}
