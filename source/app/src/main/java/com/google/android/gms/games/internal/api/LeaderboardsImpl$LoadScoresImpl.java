// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.games.internal.api;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;

abstract class <init> extends com.google.android.gms.games.adScoresImpl
{

    public com.google.android.gms.games.leaderboard. I(Status status)
    {
        class _cls1
            implements com.google.android.gms.games.leaderboard.Leaderboards.LoadScoresResult
        {

            final LeaderboardsImpl.LoadScoresImpl PT;
            final Status yJ;

            public Leaderboard getLeaderboard()
            {
                return null;
            }

            public LeaderboardScoreBuffer getScores()
            {
                return new LeaderboardScoreBuffer(DataHolder.af(14));
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
                PT = LeaderboardsImpl.LoadScoresImpl.this;
                yJ = status;
                super();
            }
        }

        return new _cls1(status);
    }

    public Result c(Status status)
    {
        return I(status);
    }

    private _cls1()
    {
    }

    _cls1(_cls1 _pcls1)
    {
        this();
    }
}
