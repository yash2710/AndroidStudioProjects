// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.games.internal.api;

import android.content.Intent;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.achievement.Achievements;
import com.google.android.gms.games.internal.GamesClientImpl;

public final class AchievementsImpl
    implements Achievements
{

    public AchievementsImpl()
    {
    }

    public final Intent getAchievementsIntent(GoogleApiClient googleapiclient)
    {
        return Games.c(googleapiclient).he();
    }

    public final void increment(GoogleApiClient googleapiclient, String s, int i)
    {
        googleapiclient.b(new _cls6(s, s, i));
    }

    public final PendingResult incrementImmediate(GoogleApiClient googleapiclient, String s, int i)
    {
        return googleapiclient.b(new _cls7(s, s, i));
    }

    public final PendingResult load(GoogleApiClient googleapiclient, boolean flag)
    {
        return googleapiclient.a(new _cls1(flag));
    }

    public final void reveal(GoogleApiClient googleapiclient, String s)
    {
        googleapiclient.b(new _cls2(s, s));
    }

    public final PendingResult revealImmediate(GoogleApiClient googleapiclient, String s)
    {
        return googleapiclient.b(new _cls3(s, s));
    }

    public final void setSteps(GoogleApiClient googleapiclient, String s, int i)
    {
        googleapiclient.b(new _cls8(s, s, i));
    }

    public final PendingResult setStepsImmediate(GoogleApiClient googleapiclient, String s, int i)
    {
        return googleapiclient.b(new _cls9(s, s, i));
    }

    public final void unlock(GoogleApiClient googleapiclient, String s)
    {
        googleapiclient.b(new _cls4(s, s));
    }

    public final PendingResult unlockImmediate(GoogleApiClient googleapiclient, String s)
    {
        return googleapiclient.b(new _cls5(s, s));
    }

    private class _cls6 extends UpdateImpl
    {
        private class UpdateImpl extends com.google.android.gms.games.Games.BaseGamesApiMethodImpl
        {

            private final String xG;

            static String a(UpdateImpl updateimpl)
            {
                return updateimpl.xG;
            }

            public Result c(Status status)
            {
                return w(status);
            }

            public com.google.android.gms.games.achievement.Achievements.UpdateAchievementResult w(Status status)
            {
                class _cls1
                    implements com.google.android.gms.games.achievement.Achievements.UpdateAchievementResult
                {

                    final UpdateImpl Pl;
                    final Status yJ;

                    public String getAchievementId()
                    {
                        return UpdateImpl.a(Pl);
                    }

                    public Status getStatus()
                    {
                        return yJ;
                    }

                    _cls1(Status status)
                    {
                        Pl = UpdateImpl.this;
                        yJ = status;
                        super();
                    }
                }

                return new _cls1(status);
            }

            public UpdateImpl(String s)
            {
                xG = s;
            }
        }


        final AchievementsImpl Pf;
        final String Pi;
        final int Pj;

        public volatile void a(com.google.android.gms.common.api.Api.a a1)
        {
            a((GamesClientImpl)a1);
        }

        public void a(GamesClientImpl gamesclientimpl)
        {
            gamesclientimpl.a(null, Pi, Pj);
        }

        _cls6(String s, String s1, int i)
        {
            Pf = AchievementsImpl.this;
            Pi = s1;
            Pj = i;
            super(s);
        }
    }


    private class _cls7 extends UpdateImpl
    {

        final AchievementsImpl Pf;
        final String Pi;
        final int Pj;

        public volatile void a(com.google.android.gms.common.api.Api.a a1)
        {
            a((GamesClientImpl)a1);
        }

        public void a(GamesClientImpl gamesclientimpl)
        {
            gamesclientimpl.a(this, Pi, Pj);
        }

        _cls7(String s, String s1, int i)
        {
            Pf = AchievementsImpl.this;
            Pi = s1;
            Pj = i;
            super(s);
        }
    }


    private class _cls1 extends LoadImpl
    {
        private class LoadImpl extends com.google.android.gms.games.Games.BaseGamesApiMethodImpl
        {

            public Result c(Status status)
            {
                return v(status);
            }

            public com.google.android.gms.games.achievement.Achievements.LoadAchievementsResult v(Status status)
            {
                class _cls1
                    implements com.google.android.gms.games.achievement.Achievements.LoadAchievementsResult
                {

                    final LoadImpl Pk;
                    final Status yJ;

                    public AchievementBuffer getAchievements()
                    {
                        return new AchievementBuffer(DataHolder.af(14));
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
                        Pk = LoadImpl.this;
                        yJ = status;
                        super();
                    }
                }

                return new _cls1(status);
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
        final AchievementsImpl Pf;

        public volatile void a(com.google.android.gms.common.api.Api.a a1)
        {
            a((GamesClientImpl)a1);
        }

        public void a(GamesClientImpl gamesclientimpl)
        {
            gamesclientimpl.c(this, Pe);
        }

        _cls1(boolean flag)
        {
            Pf = AchievementsImpl.this;
            Pe = flag;
            super(null);
        }
    }


    private class _cls2 extends UpdateImpl
    {

        final AchievementsImpl Pf;
        final String Pi;

        public volatile void a(com.google.android.gms.common.api.Api.a a1)
        {
            a((GamesClientImpl)a1);
        }

        public void a(GamesClientImpl gamesclientimpl)
        {
            gamesclientimpl.b(null, Pi);
        }

        _cls2(String s, String s1)
        {
            Pf = AchievementsImpl.this;
            Pi = s1;
            super(s);
        }
    }


    private class _cls3 extends UpdateImpl
    {

        final AchievementsImpl Pf;
        final String Pi;

        public volatile void a(com.google.android.gms.common.api.Api.a a1)
        {
            a((GamesClientImpl)a1);
        }

        public void a(GamesClientImpl gamesclientimpl)
        {
            gamesclientimpl.b(this, Pi);
        }

        _cls3(String s, String s1)
        {
            Pf = AchievementsImpl.this;
            Pi = s1;
            super(s);
        }
    }


    private class _cls8 extends UpdateImpl
    {

        final AchievementsImpl Pf;
        final String Pi;
        final int Pj;

        public volatile void a(com.google.android.gms.common.api.Api.a a1)
        {
            a((GamesClientImpl)a1);
        }

        public void a(GamesClientImpl gamesclientimpl)
        {
            gamesclientimpl.b(null, Pi, Pj);
        }

        _cls8(String s, String s1, int i)
        {
            Pf = AchievementsImpl.this;
            Pi = s1;
            Pj = i;
            super(s);
        }
    }


    private class _cls9 extends UpdateImpl
    {

        final AchievementsImpl Pf;
        final String Pi;
        final int Pj;

        public volatile void a(com.google.android.gms.common.api.Api.a a1)
        {
            a((GamesClientImpl)a1);
        }

        public void a(GamesClientImpl gamesclientimpl)
        {
            gamesclientimpl.b(this, Pi, Pj);
        }

        _cls9(String s, String s1, int i)
        {
            Pf = AchievementsImpl.this;
            Pi = s1;
            Pj = i;
            super(s);
        }
    }


    private class _cls4 extends UpdateImpl
    {

        final AchievementsImpl Pf;
        final String Pi;

        public volatile void a(com.google.android.gms.common.api.Api.a a1)
        {
            a((GamesClientImpl)a1);
        }

        public void a(GamesClientImpl gamesclientimpl)
        {
            gamesclientimpl.c(null, Pi);
        }

        _cls4(String s, String s1)
        {
            Pf = AchievementsImpl.this;
            Pi = s1;
            super(s);
        }
    }


    private class _cls5 extends UpdateImpl
    {

        final AchievementsImpl Pf;
        final String Pi;

        public volatile void a(com.google.android.gms.common.api.Api.a a1)
        {
            a((GamesClientImpl)a1);
        }

        public void a(GamesClientImpl gamesclientimpl)
        {
            gamesclientimpl.c(this, Pi);
        }

        _cls5(String s, String s1)
        {
            Pf = AchievementsImpl.this;
            Pi = s1;
            super(s);
        }
    }

}
