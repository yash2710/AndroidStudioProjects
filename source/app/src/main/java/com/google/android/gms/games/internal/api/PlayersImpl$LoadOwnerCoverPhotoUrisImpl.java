// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.games.internal.api;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;

abstract class _cls1 extends com.google.android.gms.games.hotoUrisImpl
{

    public com.google.android.gms.games.hotoUrisImpl O(Status status)
    {
        class _cls1
            implements com.google.android.gms.games.Players.LoadOwnerCoverPhotoUrisResult
        {

            final PlayersImpl.LoadOwnerCoverPhotoUrisImpl Qj;
            final Status yJ;

            public Status getStatus()
            {
                return yJ;
            }

            _cls1(Status status)
            {
                Qj = PlayersImpl.LoadOwnerCoverPhotoUrisImpl.this;
                yJ = status;
                super();
            }
        }

        return new _cls1(status);
    }

    public Result c(Status status)
    {
        return O(status);
    }

    private _cls1()
    {
    }
}
