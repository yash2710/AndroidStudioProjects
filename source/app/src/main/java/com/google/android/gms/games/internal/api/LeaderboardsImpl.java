// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.games.internal.api;

import android.content.Intent;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.internal.GamesClientImpl;
import com.google.android.gms.games.leaderboard.LeaderboardScoreBuffer;
import com.google.android.gms.games.leaderboard.Leaderboards;

public final class LeaderboardsImpl
    implements Leaderboards
{

    public LeaderboardsImpl()
    {
    }

    public final Intent getAllLeaderboardsIntent(GoogleApiClient googleapiclient)
    {
        return Games.c(googleapiclient).hd();
    }

    public final Intent getLeaderboardIntent(GoogleApiClient googleapiclient, String s)
    {
        return Games.c(googleapiclient).aR(s);
    }

    public final PendingResult loadCurrentPlayerLeaderboardScore(GoogleApiClient googleapiclient, String s, int i, int j)
    {
        return googleapiclient.a(new _cls3(s, i, j));
    }

    public final PendingResult loadLeaderboardMetadata(GoogleApiClient googleapiclient, String s, boolean flag)
    {
        return googleapiclient.a(new _cls2(s, flag));
    }

    public final PendingResult loadLeaderboardMetadata(GoogleApiClient googleapiclient, boolean flag)
    {
        return googleapiclient.a(new _cls1(flag));
    }

    public final PendingResult loadMoreScores(GoogleApiClient googleapiclient, LeaderboardScoreBuffer leaderboardscorebuffer, int i, int j)
    {
        return googleapiclient.a(new _cls6(leaderboardscorebuffer, i, j));
    }

    public final PendingResult loadPlayerCenteredScores(GoogleApiClient googleapiclient, String s, int i, int j, int k)
    {
        return loadPlayerCenteredScores(googleapiclient, s, i, j, k, false);
    }

    public final PendingResult loadPlayerCenteredScores(GoogleApiClient googleapiclient, String s, int i, int j, int k, boolean flag)
    {
        return googleapiclient.a(new _cls5(s, i, j, k, flag));
    }

    public final PendingResult loadTopScores(GoogleApiClient googleapiclient, String s, int i, int j, int k)
    {
        return loadTopScores(googleapiclient, s, i, j, k, false);
    }

    public final PendingResult loadTopScores(GoogleApiClient googleapiclient, String s, int i, int j, int k, boolean flag)
    {
        return googleapiclient.a(new _cls4(s, i, j, k, flag));
    }

    public final void submitScore(GoogleApiClient googleapiclient, String s, long l)
    {
        submitScore(googleapiclient, s, l, null);
    }

    public final void submitScore(GoogleApiClient googleapiclient, String s, long l, String s1)
    {
        Games.c(googleapiclient).a(null, s, l, s1);
    }

    public final PendingResult submitScoreImmediate(GoogleApiClient googleapiclient, String s, long l)
    {
        return submitScoreImmediate(googleapiclient, s, l, null);
    }

    public final PendingResult submitScoreImmediate(GoogleApiClient googleapiclient, String s, long l, String s1)
    {
        return googleapiclient.b(new _cls7(s, l, s1));
    }

    private class _cls3 extends LoadPlayerScoreImpl
    {
        private class LoadPlayerScoreImpl extends com.google.android.gms.games.Games.BaseGamesApiMethodImpl
        {

            public com.google.android.gms.games.leaderboard.Leaderboards.LoadPlayerScoreResult H(Status status)
            {
                class _cls1
                    implements com.google.android.gms.games.leaderboard.Leaderboards.LoadPlayerScoreResult
                {

                    final LoadPlayerScoreImpl PS;
                    final Status yJ;

                    public LeaderboardScore getScore()
                    {
                        return null;
                    }

                    public Status getStatus()
                    {
                        return yJ;
                    }

                    _cls1(Status status)
                    {
                        PS = LoadPlayerScoreImpl.this;
                        yJ = status;
                        super();
                    }
                }

                return new _cls1(status);
            }

            public Result c(Status status)
            {
                return H(status);
            }

            private LoadPlayerScoreImpl()
            {
            }

            LoadPlayerScoreImpl(_cls1 _pcls1)
            {
                this();
            }
        }


        final LeaderboardsImpl PI;
        final String PJ;
        final int PK;
        final int PL;

        protected volatile void a(com.google.android.gms.common.api.Api.a a1)
        {
            a((GamesClientImpl)a1);
        }

        protected void a(GamesClientImpl gamesclientimpl)
        {
            gamesclientimpl.a(this, null, PJ, PK, PL);
        }

        _cls3(String s, int i, int j)
        {
            PI = LeaderboardsImpl.this;
            PJ = s;
            PK = i;
            PL = j;
            super(null);
        }
    }


    private class _cls2 extends LoadMetadataImpl
    {
        private class LoadMetadataImpl extends com.google.android.gms.games.Games.BaseGamesApiMethodImpl
        {

            public com.google.android.gms.games.leaderboard.Leaderboards.LeaderboardMetadataResult G(Status status)
            {
                class _cls1
                    implements com.google.android.gms.games.leaderboard.Leaderboards.LeaderboardMetadataResult
                {

                    final LoadMetadataImpl PR;
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
                        PR = LoadMetadataImpl.this;
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

            private LoadMetadataImpl()
            {
            }

            LoadMetadataImpl(_cls1 _pcls1)
            {
                this();
            }
        }


        final LeaderboardsImpl PI;
        final String PJ;
        final boolean Pe;

        protected volatile void a(com.google.android.gms.common.api.Api.a a1)
        {
            a((GamesClientImpl)a1);
        }

        protected void a(GamesClientImpl gamesclientimpl)
        {
            gamesclientimpl.a(this, PJ, Pe);
        }

        _cls2(String s, boolean flag)
        {
            PI = LeaderboardsImpl.this;
            PJ = s;
            Pe = flag;
            super(null);
        }
    }


    private class _cls1 extends LoadMetadataImpl
    {

        final LeaderboardsImpl PI;
        final boolean Pe;

        protected volatile void a(com.google.android.gms.common.api.Api.a a1)
        {
            a((GamesClientImpl)a1);
        }

        protected void a(GamesClientImpl gamesclientimpl)
        {
            gamesclientimpl.b(this, Pe);
        }

        _cls1(boolean flag)
        {
            PI = LeaderboardsImpl.this;
            Pe = flag;
            super(null);
        }
    }


    private class _cls6 extends LoadScoresImpl
    {
        private class LoadScoresImpl extends com.google.android.gms.games.Games.BaseGamesApiMethodImpl
        {

            public com.google.android.gms.games.leaderboard.Leaderboards.LoadScoresResult I(Status status)
            {
                class _cls1
                    implements com.google.android.gms.games.leaderboard.Leaderboards.LoadScoresResult
                {

                    final LoadScoresImpl PT;
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
                        PT = LoadScoresImpl.this;
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

            private LoadScoresImpl()
            {
            }

            LoadScoresImpl(_cls1 _pcls1)
            {
                this();
            }
        }


        final LeaderboardsImpl PI;
        final int PM;
        final LeaderboardScoreBuffer PN;
        final int PO;

        protected volatile void a(com.google.android.gms.common.api.Api.a a1)
        {
            a((GamesClientImpl)a1);
        }

        protected void a(GamesClientImpl gamesclientimpl)
        {
            gamesclientimpl.a(this, PN, PM, PO);
        }

        _cls6(LeaderboardScoreBuffer leaderboardscorebuffer, int i, int j)
        {
            PI = LeaderboardsImpl.this;
            PN = leaderboardscorebuffer;
            PM = i;
            PO = j;
            super(null);
        }
    }


    private class _cls5 extends LoadScoresImpl
    {

        final LeaderboardsImpl PI;
        final String PJ;
        final int PK;
        final int PL;
        final int PM;
        final boolean Pe;

        protected volatile void a(com.google.android.gms.common.api.Api.a a1)
        {
            a((GamesClientImpl)a1);
        }

        protected void a(GamesClientImpl gamesclientimpl)
        {
            gamesclientimpl.b(this, PJ, PK, PL, PM, Pe);
        }

        _cls5(String s, int i, int j, int k, boolean flag)
        {
            PI = LeaderboardsImpl.this;
            PJ = s;
            PK = i;
            PL = j;
            PM = k;
            Pe = flag;
            super(null);
        }
    }


    private class _cls4 extends LoadScoresImpl
    {

        final LeaderboardsImpl PI;
        final String PJ;
        final int PK;
        final int PL;
        final int PM;
        final boolean Pe;

        protected volatile void a(com.google.android.gms.common.api.Api.a a1)
        {
            a((GamesClientImpl)a1);
        }

        protected void a(GamesClientImpl gamesclientimpl)
        {
            gamesclientimpl.a(this, PJ, PK, PL, PM, Pe);
        }

        _cls4(String s, int i, int j, int k, boolean flag)
        {
            PI = LeaderboardsImpl.this;
            PJ = s;
            PK = i;
            PL = j;
            PM = k;
            Pe = flag;
            super(null);
        }
    }


    private class _cls7 extends SubmitScoreImpl
    {
        private class SubmitScoreImpl extends com.google.android.gms.games.Games.BaseGamesApiMethodImpl
        {

            public com.google.android.gms.games.leaderboard.Leaderboards.SubmitScoreResult J(Status status)
            {
                class _cls1
                    implements com.google.android.gms.games.leaderboard.Leaderboards.SubmitScoreResult
                {

                    final SubmitScoreImpl PU;
                    final Status yJ;

                    public ScoreSubmissionData getScoreData()
                    {
                        return new ScoreSubmissionData(DataHolder.af(14));
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
                        PU = SubmitScoreImpl.this;
                        yJ = status;
                        super();
                    }
                }

                return new _cls1(status);
            }

            public Result c(Status status)
            {
                return J(status);
            }

            protected SubmitScoreImpl()
            {
            }
        }


        final LeaderboardsImpl PI;
        final String PJ;
        final long PP;
        final String PQ;

        protected volatile void a(com.google.android.gms.common.api.Api.a a1)
        {
            a((GamesClientImpl)a1);
        }

        protected void a(GamesClientImpl gamesclientimpl)
        {
            gamesclientimpl.a(this, PJ, PP, PQ);
        }

        _cls7(String s, long l, String s1)
        {
            PI = LeaderboardsImpl.this;
            PJ = s;
            PP = l;
            PQ = s1;
            super();
        }
    }

}
