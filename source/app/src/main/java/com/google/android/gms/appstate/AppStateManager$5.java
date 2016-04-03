// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.appstate;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.ga;

final class <init> extends <init>
{

    final int yK;

    protected final volatile void a(com.google.android.gms.common.api.nit> nit>)
    {
        a((ga)nit>);
    }

    protected final void a(ga ga1)
    {
        ga1.a(this, yK);
    }

    public final Result c(Status status)
    {
        return g(status);
    }

    public final ateDeletedResult g(Status status)
    {
        class _cls1
            implements AppStateManager.StateDeletedResult
        {

            final Status yJ;
            final AppStateManager._cls5 yM;

            public int getStateKey()
            {
                return yM.yK;
            }

            public Status getStatus()
            {
                return yJ;
            }

            _cls1(Status status)
            {
                yM = AppStateManager._cls5.this;
                yJ = status;
                super();
            }
        }

        return new _cls1(status);
    }

    ateDeletedResult(int i)
    {
        yK = i;
        super(null);
    }
}
