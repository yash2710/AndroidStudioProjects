// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.games.internal.api;

import android.content.Intent;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.internal.GamesClientImpl;
import com.google.android.gms.games.multiplayer.ParticipantResult;
import com.google.android.gms.games.multiplayer.turnbased.OnTurnBasedMatchUpdateReceivedListener;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMatchConfig;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer;
import java.util.List;

public final class TurnBasedMultiplayerImpl
    implements TurnBasedMultiplayer
{

    public TurnBasedMultiplayerImpl()
    {
    }

    public final PendingResult acceptInvitation(GoogleApiClient googleapiclient, String s)
    {
        return googleapiclient.b(new _cls3(s));
    }

    public final PendingResult cancelMatch(GoogleApiClient googleapiclient, String s)
    {
        return googleapiclient.b(new _cls8(s, s));
    }

    public final PendingResult createMatch(GoogleApiClient googleapiclient, TurnBasedMatchConfig turnbasedmatchconfig)
    {
        return googleapiclient.b(new _cls1(turnbasedmatchconfig));
    }

    public final void declineInvitation(GoogleApiClient googleapiclient, String s)
    {
        Games.c(googleapiclient).n(s, 1);
    }

    public final void dismissInvitation(GoogleApiClient googleapiclient, String s)
    {
        Games.c(googleapiclient).m(s, 1);
    }

    public final void dismissMatch(GoogleApiClient googleapiclient, String s)
    {
        Games.c(googleapiclient).aS(s);
    }

    public final PendingResult finishMatch(GoogleApiClient googleapiclient, String s)
    {
        return finishMatch(googleapiclient, s, null, ((ParticipantResult []) (null)));
    }

    public final PendingResult finishMatch(GoogleApiClient googleapiclient, String s, byte abyte0[], List list)
    {
        ParticipantResult aparticipantresult[];
        if (list == null)
        {
            aparticipantresult = null;
        } else
        {
            aparticipantresult = (ParticipantResult[])list.toArray(new ParticipantResult[list.size()]);
        }
        return finishMatch(googleapiclient, s, abyte0, aparticipantresult);
    }

    public final transient PendingResult finishMatch(GoogleApiClient googleapiclient, String s, byte abyte0[], ParticipantResult aparticipantresult[])
    {
        return googleapiclient.b(new _cls5(s, abyte0, aparticipantresult));
    }

    public final Intent getInboxIntent(GoogleApiClient googleapiclient)
    {
        return Games.c(googleapiclient).hf();
    }

    public final int getMaxMatchDataSize(GoogleApiClient googleapiclient)
    {
        return Games.c(googleapiclient).hp();
    }

    public final Intent getSelectOpponentsIntent(GoogleApiClient googleapiclient, int i, int j)
    {
        return Games.c(googleapiclient).a(i, j, true);
    }

    public final Intent getSelectOpponentsIntent(GoogleApiClient googleapiclient, int i, int j, boolean flag)
    {
        return Games.c(googleapiclient).a(i, j, flag);
    }

    public final PendingResult leaveMatch(GoogleApiClient googleapiclient, String s)
    {
        return googleapiclient.b(new _cls6(s));
    }

    public final PendingResult leaveMatchDuringTurn(GoogleApiClient googleapiclient, String s, String s1)
    {
        return googleapiclient.b(new _cls7(s, s1));
    }

    public final PendingResult loadMatch(GoogleApiClient googleapiclient, String s)
    {
        return googleapiclient.a(new _cls10(s));
    }

    public final PendingResult loadMatchesByStatus(GoogleApiClient googleapiclient, int i, int ai[])
    {
        return googleapiclient.a(new _cls9(i, ai));
    }

    public final PendingResult loadMatchesByStatus(GoogleApiClient googleapiclient, int ai[])
    {
        return loadMatchesByStatus(googleapiclient, 0, ai);
    }

    public final void registerMatchUpdateListener(GoogleApiClient googleapiclient, OnTurnBasedMatchUpdateReceivedListener onturnbasedmatchupdatereceivedlistener)
    {
        Games.c(googleapiclient).a(onturnbasedmatchupdatereceivedlistener);
    }

    public final PendingResult rematch(GoogleApiClient googleapiclient, String s)
    {
        return googleapiclient.b(new _cls2(s));
    }

    public final PendingResult takeTurn(GoogleApiClient googleapiclient, String s, byte abyte0[], String s1)
    {
        return takeTurn(googleapiclient, s, abyte0, s1, ((ParticipantResult []) (null)));
    }

    public final PendingResult takeTurn(GoogleApiClient googleapiclient, String s, byte abyte0[], String s1, List list)
    {
        ParticipantResult aparticipantresult[];
        if (list == null)
        {
            aparticipantresult = null;
        } else
        {
            aparticipantresult = (ParticipantResult[])list.toArray(new ParticipantResult[list.size()]);
        }
        return takeTurn(googleapiclient, s, abyte0, s1, aparticipantresult);
    }

    public final transient PendingResult takeTurn(GoogleApiClient googleapiclient, String s, byte abyte0[], String s1, ParticipantResult aparticipantresult[])
    {
        return googleapiclient.b(new _cls4(s, abyte0, s1, aparticipantresult));
    }

    public final void unregisterMatchUpdateListener(GoogleApiClient googleapiclient)
    {
        Games.c(googleapiclient).hi();
    }

    private class _cls3 extends InitiateMatchImpl
    {
        private class InitiateMatchImpl extends com.google.android.gms.games.Games.BaseGamesApiMethodImpl
        {

            public com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer.InitiateMatchResult ae(Status status)
            {
                class _cls1
                    implements com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer.InitiateMatchResult
                {

                    final InitiateMatchImpl Rd;
                    final Status yJ;

                    public TurnBasedMatch getMatch()
                    {
                        return null;
                    }

                    public Status getStatus()
                    {
                        return yJ;
                    }

                    _cls1(Status status)
                    {
                        Rd = InitiateMatchImpl.this;
                        yJ = status;
                        super();
                    }
                }

                return new _cls1(status);
            }

            public Result c(Status status)
            {
                return ae(status);
            }

            private InitiateMatchImpl()
            {
            }

            InitiateMatchImpl(_cls1 _pcls1)
            {
                this();
            }
        }


        final String PG;
        final TurnBasedMultiplayerImpl QV;

        protected volatile void a(com.google.android.gms.common.api.Api.a a1)
        {
            a((GamesClientImpl)a1);
        }

        protected void a(GamesClientImpl gamesclientimpl)
        {
            gamesclientimpl.e(this, PG);
        }

        _cls3(String s)
        {
            QV = TurnBasedMultiplayerImpl.this;
            PG = s;
            super(null);
        }
    }


    private class _cls8 extends CancelMatchImpl
    {
        private class CancelMatchImpl extends com.google.android.gms.games.Games.BaseGamesApiMethodImpl
        {

            private final String xG;

            static String a(CancelMatchImpl cancelmatchimpl)
            {
                return cancelmatchimpl.xG;
            }

            public com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer.CancelMatchResult ad(Status status)
            {
                class _cls1
                    implements com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer.CancelMatchResult
                {

                    final CancelMatchImpl Rc;
                    final Status yJ;

                    public String getMatchId()
                    {
                        return CancelMatchImpl.a(Rc);
                    }

                    public Status getStatus()
                    {
                        return yJ;
                    }

                    _cls1(Status status)
                    {
                        Rc = CancelMatchImpl.this;
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

            public CancelMatchImpl(String s)
            {
                xG = s;
            }
        }


        final TurnBasedMultiplayerImpl QV;
        final String QW;

        protected volatile void a(com.google.android.gms.common.api.Api.a a1)
        {
            a((GamesClientImpl)a1);
        }

        protected void a(GamesClientImpl gamesclientimpl)
        {
            gamesclientimpl.g(this, QW);
        }

        _cls8(String s, String s1)
        {
            QV = TurnBasedMultiplayerImpl.this;
            QW = s1;
            super(s);
        }
    }


    private class _cls1 extends InitiateMatchImpl
    {

        final TurnBasedMatchConfig QU;
        final TurnBasedMultiplayerImpl QV;

        protected volatile void a(com.google.android.gms.common.api.Api.a a1)
        {
            a((GamesClientImpl)a1);
        }

        protected void a(GamesClientImpl gamesclientimpl)
        {
            gamesclientimpl.a(this, QU);
        }

        _cls1(TurnBasedMatchConfig turnbasedmatchconfig)
        {
            QV = TurnBasedMultiplayerImpl.this;
            QU = turnbasedmatchconfig;
            super(null);
        }
    }


    private class _cls5 extends UpdateMatchImpl
    {
        private class UpdateMatchImpl extends com.google.android.gms.games.Games.BaseGamesApiMethodImpl
        {

            public com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer.UpdateMatchResult ai(Status status)
            {
                class _cls1
                    implements com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer.UpdateMatchResult
                {

                    final UpdateMatchImpl Rh;
                    final Status yJ;

                    public TurnBasedMatch getMatch()
                    {
                        return null;
                    }

                    public Status getStatus()
                    {
                        return yJ;
                    }

                    _cls1(Status status)
                    {
                        Rh = UpdateMatchImpl.this;
                        yJ = status;
                        super();
                    }
                }

                return new _cls1(status);
            }

            public Result c(Status status)
            {
                return ai(status);
            }

            private UpdateMatchImpl()
            {
            }

            UpdateMatchImpl(_cls1 _pcls1)
            {
                this();
            }
        }


        final TurnBasedMultiplayerImpl QV;
        final String QW;
        final byte QZ[];
        final ParticipantResult Rb[];

        protected volatile void a(com.google.android.gms.common.api.Api.a a1)
        {
            a((GamesClientImpl)a1);
        }

        protected void a(GamesClientImpl gamesclientimpl)
        {
            gamesclientimpl.a(this, QW, QZ, Rb);
        }

        _cls5(String s, byte abyte0[], ParticipantResult aparticipantresult[])
        {
            QV = TurnBasedMultiplayerImpl.this;
            QW = s;
            QZ = abyte0;
            Rb = aparticipantresult;
            super(null);
        }
    }


    private class _cls6 extends LeaveMatchImpl
    {
        private class LeaveMatchImpl extends com.google.android.gms.games.Games.BaseGamesApiMethodImpl
        {

            public com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer.LeaveMatchResult af(Status status)
            {
                class _cls1
                    implements com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer.LeaveMatchResult
                {

                    final LeaveMatchImpl Re;
                    final Status yJ;

                    public TurnBasedMatch getMatch()
                    {
                        return null;
                    }

                    public Status getStatus()
                    {
                        return yJ;
                    }

                    _cls1(Status status)
                    {
                        Re = LeaveMatchImpl.this;
                        yJ = status;
                        super();
                    }
                }

                return new _cls1(status);
            }

            public Result c(Status status)
            {
                return af(status);
            }

            private LeaveMatchImpl()
            {
            }

            LeaveMatchImpl(_cls1 _pcls1)
            {
                this();
            }
        }


        final TurnBasedMultiplayerImpl QV;
        final String QW;

        protected volatile void a(com.google.android.gms.common.api.Api.a a1)
        {
            a((GamesClientImpl)a1);
        }

        protected void a(GamesClientImpl gamesclientimpl)
        {
            gamesclientimpl.f(this, QW);
        }

        _cls6(String s)
        {
            QV = TurnBasedMultiplayerImpl.this;
            QW = s;
            super(null);
        }
    }


    private class _cls7 extends LeaveMatchImpl
    {

        final TurnBasedMultiplayerImpl QV;
        final String QW;
        final String Ra;

        protected volatile void a(com.google.android.gms.common.api.Api.a a1)
        {
            a((GamesClientImpl)a1);
        }

        protected void a(GamesClientImpl gamesclientimpl)
        {
            gamesclientimpl.a(this, QW, Ra);
        }

        _cls7(String s, String s1)
        {
            QV = TurnBasedMultiplayerImpl.this;
            QW = s;
            Ra = s1;
            super(null);
        }
    }


    private class _cls10 extends LoadMatchImpl
    {
        private class LoadMatchImpl extends com.google.android.gms.games.Games.BaseGamesApiMethodImpl
        {

            public com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer.LoadMatchResult ag(Status status)
            {
                class _cls1
                    implements com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer.LoadMatchResult
                {

                    final LoadMatchImpl Rf;
                    final Status yJ;

                    public TurnBasedMatch getMatch()
                    {
                        return null;
                    }

                    public Status getStatus()
                    {
                        return yJ;
                    }

                    _cls1(Status status)
                    {
                        Rf = LoadMatchImpl.this;
                        yJ = status;
                        super();
                    }
                }

                return new _cls1(status);
            }

            public Result c(Status status)
            {
                return ag(status);
            }

            private LoadMatchImpl()
            {
            }

            LoadMatchImpl(_cls1 _pcls1)
            {
                this();
            }
        }


        final TurnBasedMultiplayerImpl QV;
        final String QW;

        protected volatile void a(com.google.android.gms.common.api.Api.a a1)
        {
            a((GamesClientImpl)a1);
        }

        protected void a(GamesClientImpl gamesclientimpl)
        {
            gamesclientimpl.h(this, QW);
        }

        _cls10(String s)
        {
            QV = TurnBasedMultiplayerImpl.this;
            QW = s;
            super(null);
        }
    }


    private class _cls9 extends LoadMatchesImpl
    {
        private class LoadMatchesImpl extends com.google.android.gms.games.Games.BaseGamesApiMethodImpl
        {

            public com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer.LoadMatchesResult ah(Status status)
            {
                class _cls1
                    implements com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer.LoadMatchesResult
                {

                    final LoadMatchesImpl Rg;
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
                        Rg = LoadMatchesImpl.this;
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

            private LoadMatchesImpl()
            {
            }

            LoadMatchesImpl(_cls1 _pcls1)
            {
                this();
            }
        }


        final TurnBasedMultiplayerImpl QV;
        final int QX;
        final int QY[];

        protected volatile void a(com.google.android.gms.common.api.Api.a a1)
        {
            a((GamesClientImpl)a1);
        }

        protected void a(GamesClientImpl gamesclientimpl)
        {
            gamesclientimpl.a(this, QX, QY);
        }

        _cls9(int i, int ai[])
        {
            QV = TurnBasedMultiplayerImpl.this;
            QX = i;
            QY = ai;
            super(null);
        }
    }


    private class _cls2 extends InitiateMatchImpl
    {

        final TurnBasedMultiplayerImpl QV;
        final String QW;

        protected volatile void a(com.google.android.gms.common.api.Api.a a1)
        {
            a((GamesClientImpl)a1);
        }

        protected void a(GamesClientImpl gamesclientimpl)
        {
            gamesclientimpl.d(this, QW);
        }

        _cls2(String s)
        {
            QV = TurnBasedMultiplayerImpl.this;
            QW = s;
            super(null);
        }
    }


    private class _cls4 extends UpdateMatchImpl
    {

        final TurnBasedMultiplayerImpl QV;
        final String QW;
        final byte QZ[];
        final String Ra;
        final ParticipantResult Rb[];

        protected volatile void a(com.google.android.gms.common.api.Api.a a1)
        {
            a((GamesClientImpl)a1);
        }

        protected void a(GamesClientImpl gamesclientimpl)
        {
            gamesclientimpl.a(this, QW, QZ, Ra, Rb);
        }

        _cls4(String s, byte abyte0[], String s1, ParticipantResult aparticipantresult[])
        {
            QV = TurnBasedMultiplayerImpl.this;
            QW = s;
            QZ = abyte0;
            Ra = s1;
            Rb = aparticipantresult;
            super(null);
        }
    }

}
