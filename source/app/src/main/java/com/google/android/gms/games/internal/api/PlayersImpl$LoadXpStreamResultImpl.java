// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.games.internal.api;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;

abstract class _cls1 extends com.google.android.gms.games.amResultImpl
{

    public com.google.android.gms.games.amResultImpl R(Status status)
    {
        class _cls1
            implements com.google.android.gms.games.Players.LoadXpStreamResult
        {

            final PlayersImpl.LoadXpStreamResultImpl Qm;
            final Status yJ;

            public Status getStatus()
            {
                return yJ;
            }

            _cls1(Status status)
            {
                Qm = PlayersImpl.LoadXpStreamResultImpl.this;
                yJ = status;
                super();
            }
        }

        return new _cls1(status);
    }

    public Result c(Status status)
    {
        return R(status);
    }

    private _cls1()
    {
    }
}