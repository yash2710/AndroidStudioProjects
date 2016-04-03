// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.games.internal.api;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;

abstract class <init> extends com.google.android.gms.games.dMatchesImpl
{

    public com.google.android.gms.games.multiplayer.turnbased.Q ah(Status status)
    {
        class _cls1
            implements com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer.LoadMatchesResult
        {

            final TurnBasedMultiplayerImpl.LoadMatchesImpl Rg;
            final Status yJ;

            public LoadMatchesResponse getMatches()
            {
                return new LoadMatchesResponse(new Bundle());
            }

            public Status getStatus()
            {
                return yJ;
            }

            public void release()
            {
            }

            _cls1(Status status)
            {
                Rg = TurnBasedMultiplayerImpl.LoadMatchesImpl.this;
                yJ = status;
                super();
            }
        }

        return new _cls1(status);
    }

    public Result c(Status status)
    {
        return ah(status);
    }

    private Result()
    {
    }

    Result(Result result)
    {
        this();
    }
}
