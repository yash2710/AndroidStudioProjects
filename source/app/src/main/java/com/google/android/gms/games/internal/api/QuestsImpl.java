// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.games.internal.api;

import android.content.Intent;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.internal.GamesClientImpl;
import com.google.android.gms.games.quest.QuestUpdateListener;
import com.google.android.gms.games.quest.Quests;

public final class QuestsImpl
    implements Quests
{

    public QuestsImpl()
    {
    }

    public final PendingResult accept(GoogleApiClient googleapiclient, String s)
    {
        return googleapiclient.b(new _cls1(s));
    }

    public final PendingResult claim(GoogleApiClient googleapiclient, String s, String s1)
    {
        return googleapiclient.b(new _cls2(s, s1));
    }

    public final Intent getQuestIntent(GoogleApiClient googleapiclient, String s)
    {
        return Games.c(googleapiclient).aU(s);
    }

    public final Intent getQuestsIntent(GoogleApiClient googleapiclient, int ai[])
    {
        return Games.c(googleapiclient).a(ai);
    }

    public final PendingResult load(GoogleApiClient googleapiclient, int ai[], int i, boolean flag)
    {
        return googleapiclient.a(new _cls3(ai, i, flag));
    }

    public final transient PendingResult loadByIds(GoogleApiClient googleapiclient, boolean flag, String as[])
    {
        return googleapiclient.a(new _cls4(flag, as));
    }

    public final void registerQuestUpdateListener(GoogleApiClient googleapiclient, QuestUpdateListener questupdatelistener)
    {
        Games.c(googleapiclient).a(questupdatelistener);
    }

    public final void unregisterQuestUpdateListener(GoogleApiClient googleapiclient)
    {
        Games.c(googleapiclient).hj();
    }

    private class _cls1 extends AcceptImpl
    {
        private class AcceptImpl extends com.google.android.gms.games.Games.BaseGamesApiMethodImpl
        {

            public com.google.android.gms.games.quest.Quests.AcceptQuestResult S(Status status)
            {
                class _cls1
                    implements com.google.android.gms.games.quest.Quests.AcceptQuestResult
                {

                    final AcceptImpl Qt;
                    final Status yJ;

                    public Quest getQuest()
                    {
                        return null;
                    }

                    public Status getStatus()
                    {
                        return yJ;
                    }

                    _cls1(Status status)
                    {
                        Qt = AcceptImpl.this;
                        yJ = status;
                        super();
                    }
                }

                return new _cls1(status);
            }

            public Result c(Status status)
            {
                return S(status);
            }

            private AcceptImpl()
            {
            }

            AcceptImpl(_cls1 _pcls1)
            {
                this();
            }
        }


        final String Qn;
        final QuestsImpl Qo;

        protected volatile void a(com.google.android.gms.common.api.Api.a a1)
        {
            a((GamesClientImpl)a1);
        }

        protected void a(GamesClientImpl gamesclientimpl)
        {
            gamesclientimpl.i(this, Qn);
        }

        _cls1(String s)
        {
            Qo = QuestsImpl.this;
            Qn = s;
            super(null);
        }
    }


    private class _cls2 extends ClaimImpl
    {
        private class ClaimImpl extends com.google.android.gms.games.Games.BaseGamesApiMethodImpl
        {

            public com.google.android.gms.games.quest.Quests.ClaimMilestoneResult T(Status status)
            {
                class _cls1
                    implements com.google.android.gms.games.quest.Quests.ClaimMilestoneResult
                {

                    final ClaimImpl Qu;
                    final Status yJ;

                    public Milestone getMilestone()
                    {
                        return null;
                    }

                    public Quest getQuest()
                    {
                        return null;
                    }

                    public Status getStatus()
                    {
                        return yJ;
                    }

                    _cls1(Status status)
                    {
                        Qu = ClaimImpl.this;
                        yJ = status;
                        super();
                    }
                }

                return new _cls1(status);
            }

            public Result c(Status status)
            {
                return T(status);
            }

            private ClaimImpl()
            {
            }

            ClaimImpl(_cls1 _pcls1)
            {
                this();
            }
        }


        final String Qn;
        final QuestsImpl Qo;
        final String Qp;

        protected volatile void a(com.google.android.gms.common.api.Api.a a1)
        {
            a((GamesClientImpl)a1);
        }

        protected void a(GamesClientImpl gamesclientimpl)
        {
            gamesclientimpl.b(this, Qn, Qp);
        }

        _cls2(String s, String s1)
        {
            Qo = QuestsImpl.this;
            Qn = s;
            Qp = s1;
            super(null);
        }
    }


    private class _cls3 extends LoadsImpl
    {
        private class LoadsImpl extends com.google.android.gms.games.Games.BaseGamesApiMethodImpl
        {

            public com.google.android.gms.games.quest.Quests.LoadQuestsResult U(Status status)
            {
                class _cls1
                    implements com.google.android.gms.games.quest.Quests.LoadQuestsResult
                {

                    final LoadsImpl Qv;
                    final Status yJ;

                    public QuestBuffer getQuests()
                    {
                        return null;
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
                        Qv = LoadsImpl.this;
                        yJ = status;
                        super();
                    }
                }

                return new _cls1(status);
            }

            public Result c(Status status)
            {
                return U(status);
            }

            private LoadsImpl()
            {
            }

            LoadsImpl(_cls1 _pcls1)
            {
                this();
            }
        }


        final int PE;
        final boolean Pe;
        final QuestsImpl Qo;
        final int Qq[];

        protected volatile void a(com.google.android.gms.common.api.Api.a a1)
        {
            a((GamesClientImpl)a1);
        }

        protected void a(GamesClientImpl gamesclientimpl)
        {
            gamesclientimpl.a(this, Qq, PE, Pe);
        }

        _cls3(int ai[], int i, boolean flag)
        {
            Qo = QuestsImpl.this;
            Qq = ai;
            PE = i;
            Pe = flag;
            super(null);
        }
    }


    private class _cls4 extends LoadsImpl
    {

        final boolean Pe;
        final QuestsImpl Qo;
        final String Qr[];

        protected volatile void a(com.google.android.gms.common.api.Api.a a1)
        {
            a((GamesClientImpl)a1);
        }

        protected void a(GamesClientImpl gamesclientimpl)
        {
            gamesclientimpl.b(this, Pe, Qr);
        }

        _cls4(boolean flag, String as[])
        {
            Qo = QuestsImpl.this;
            Pe = flag;
            Qr = as;
            super(null);
        }
    }

}
