// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.games.internal.api;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.games.Game;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.GamesMetadata;
import com.google.android.gms.games.internal.GamesClientImpl;

public final class GamesMetadataImpl
    implements GamesMetadata
{

    public GamesMetadataImpl()
    {
    }

    public final Game getCurrentGame(GoogleApiClient googleapiclient)
    {
        return Games.c(googleapiclient).hc();
    }

    public final PendingResult loadGame(GoogleApiClient googleapiclient)
    {
        return googleapiclient.a(new _cls1());
    }

    private class _cls1 extends LoadGamesImpl
    {
        private class LoadGamesImpl extends com.google.android.gms.games.Games.BaseGamesApiMethodImpl
        {

            public com.google.android.gms.games.GamesMetadata.LoadGamesResult E(Status status)
            {
                class _cls1
                    implements com.google.android.gms.games.GamesMetadata.LoadGamesResult
                {

                    final LoadGamesImpl PD;
                    final Status yJ;

                    public GameBuffer getGames()
                    {
                        return new GameBuffer(DataHolder.af(14));
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
                        PD = LoadGamesImpl.this;
                        yJ = status;
                        super();
                    }
                }

                return new _cls1(status);
            }

            public Result c(Status status)
            {
                return E(status);
            }

            private LoadGamesImpl()
            {
            }

            LoadGamesImpl(_cls1 _pcls1)
            {
                this();
            }
        }


        final GamesMetadataImpl Pt;

        protected volatile void a(com.google.android.gms.common.api.Api.a a1)
        {
            a((GamesClientImpl)a1);
        }

        protected void a(GamesClientImpl gamesclientimpl)
        {
            gamesclientimpl.f(this);
        }

        _cls1()
        {
            Pt = GamesMetadataImpl.this;
            super(null);
        }
    }

}
