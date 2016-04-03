// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.games.internal.api;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;

abstract class <init> extends com.google.android.gms.games.MetadataImpl
{

    public com.google.android.gms.games.leaderboard.sult G(Status status)
    {
        class _cls1
            implements com.google.android.gms.games.leaderboard.Leaderboards.LeaderboardMetadataResult
        {

            final LeaderboardsImpl.LoadMetadataImpl PR;
            final Status yJ;

            public LeaderboardBuffer getLeaderboards()
            {
                return new LeaderboardBuffer(DataHolder.af(14));
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
                PR = LeaderboardsImpl.LoadMetadataImpl.this;
                yJ = status;
                super();
            }
        }

        return new _cls1(status);
    }

    public Result c(Status status)
    {
        return G(status);
    }

    private ult()
    {
    }

    ult(ult ult)
    {
        this();
    }
}
