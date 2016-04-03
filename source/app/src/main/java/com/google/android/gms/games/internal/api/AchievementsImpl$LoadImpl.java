// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.games.internal.api;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;

abstract class <init> extends com.google.android.gms.games.pl
{

    public Result c(Status status)
    {
        return v(status);
    }

    public com.google.android.gms.games.achievement.ntsResult v(Status status)
    {
        class _cls1
            implements com.google.android.gms.games.achievement.Achievements.LoadAchievementsResult
        {

            final AchievementsImpl.LoadImpl Pk;
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
                Pk = AchievementsImpl.LoadImpl.this;
                yJ = status;
                super();
            }
        }

        return new _cls1(status);
    }

    private tsResult()
    {
    }

    tsResult(tsResult tsresult)
    {
        this();
    }
}
