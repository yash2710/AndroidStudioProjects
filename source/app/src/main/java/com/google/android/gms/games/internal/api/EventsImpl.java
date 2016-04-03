// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.games.internal.api;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.event.Events;
import com.google.android.gms.games.internal.GamesClientImpl;

public final class EventsImpl
    implements Events
{

    public EventsImpl()
    {
    }

    public final void increment(GoogleApiClient googleapiclient, String s, int i)
    {
        GamesClientImpl gamesclientimpl = Games.d(googleapiclient);
        if (gamesclientimpl.isConnected())
        {
            gamesclientimpl.l(s, i);
            return;
        } else
        {
            googleapiclient.b(new _cls3(s, i));
            return;
        }
    }

    public final PendingResult load(GoogleApiClient googleapiclient, boolean flag)
    {
        return googleapiclient.a(new _cls2(flag));
    }

    public final transient PendingResult loadByIds(GoogleApiClient googleapiclient, boolean flag, String as[])
    {
        return googleapiclient.a(new _cls1(flag, as));
    }

    private class _cls3 extends UpdateImpl
    {
        private class UpdateImpl extends com.google.android.gms.games.Games.BaseGamesApiMethodImpl
        {

            public Result c(Status status)
            {
                class _cls1
                    implements Result
                {

                    final UpdateImpl Ps;
                    final Status yJ;

                    public Status getStatus()
                    {
                        return yJ;
                    }

                    _cls1(Status status)
                    {
                        Ps = UpdateImpl.this;
                        yJ = status;
                        super();
                    }
                }

                return new _cls1(status);
            }

            private UpdateImpl()
            {
            }

            UpdateImpl(_cls1 _pcls1)
            {
                this();
            }
        }


        final EventsImpl Po;
        final String Pp;
        final int Pq;

        public volatile void a(com.google.android.gms.common.api.Api.a a1)
        {
            a((GamesClientImpl)a1);
        }

        public void a(GamesClientImpl gamesclientimpl)
        {
            gamesclientimpl.l(Pp, Pq);
        }

        _cls3(String s, int i)
        {
            Po = EventsImpl.this;
            Pp = s;
            Pq = i;
            super(null);
        }
    }


    private class _cls2 extends LoadImpl
    {
        private class LoadImpl extends com.google.android.gms.games.Games.BaseGamesApiMethodImpl
        {

            public com.google.android.gms.games.event.Events.LoadEventsResult A(Status status)
            {
                class _cls1
                    implements com.google.android.gms.games.event.Events.LoadEventsResult
                {

                    final LoadImpl Pr;
                    final Status yJ;

                    public EventBuffer getEvents()
                    {
                        return new EventBuffer(DataHolder.af(14));
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
                        Pr = LoadImpl.this;
                        yJ = status;
                        super();
                    }
                }

                return new _cls1(status);
            }

            public Result c(Status status)
            {
                return A(status);
            }

            private LoadImpl()
            {
            }

            LoadImpl(_cls1 _pcls1)
            {
                this();
            }
        }


        final boolean Pe;
        final EventsImpl Po;

        public volatile void a(com.google.android.gms.common.api.Api.a a1)
        {
            a((GamesClientImpl)a1);
        }

        public void a(GamesClientImpl gamesclientimpl)
        {
            gamesclientimpl.d(this, Pe);
        }

        _cls2(boolean flag)
        {
            Po = EventsImpl.this;
            Pe = flag;
            super(null);
        }
    }


    private class _cls1 extends LoadImpl
    {

        final boolean Pe;
        final String Pn[];
        final EventsImpl Po;

        public volatile void a(com.google.android.gms.common.api.Api.a a1)
        {
            a((GamesClientImpl)a1);
        }

        public void a(GamesClientImpl gamesclientimpl)
        {
            gamesclientimpl.a(this, Pe, Pn);
        }

        _cls1(boolean flag, String as[])
        {
            Po = EventsImpl.this;
            Pe = flag;
            Pn = as;
            super(null);
        }
    }

}
