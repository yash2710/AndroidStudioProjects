// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.games.internal.api;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;

abstract class xG extends com.google.android.gms.games.celMatchImpl
{

    private final String xG;

    static String a(xG xg)
    {
        return xg.xG;
    }

    public com.google.android.gms.games.multiplayer.turnbased.q ad(Status status)
    {
        class _cls1
            implements com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer.CancelMatchResult
        {

            final TurnBasedMultiplayerImpl.CancelMatchImpl Rc;
            final Status yJ;

            public String getMatchId()
            {
                return TurnBasedMultiplayerImpl.CancelMatchImpl.a(Rc);
            }

            public Status getStatus()
            {
                return yJ;
            }

            _cls1(Status status)
            {
                Rc = TurnBasedMultiplayerImpl.CancelMatchImpl.this;
                yJ = status;
                super();
            }
        }

        return new _cls1(status);
    }

    public Result c(Status status)
    {
        return ad(status);
    }

    public Result(String s)
    {
        xG = s;
    }
}
