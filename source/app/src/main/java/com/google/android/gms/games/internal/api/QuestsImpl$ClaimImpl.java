// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.games.internal.api;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;

abstract class <init> extends com.google.android.gms.games.hodImpl
{

    public com.google.android.gms.games.quest.Result T(Status status)
    {
        class _cls1
            implements com.google.android.gms.games.quest.Quests.ClaimMilestoneResult
        {

            final QuestsImpl.ClaimImpl Qu;
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
                Qu = QuestsImpl.ClaimImpl.this;
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

    private _cls1()
    {
    }

    _cls1(_cls1 _pcls1)
    {
        this();
    }
}
