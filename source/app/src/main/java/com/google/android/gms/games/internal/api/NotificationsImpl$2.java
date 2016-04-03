// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.games.internal.api;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.internal.GamesClientImpl;

class eResult extends com.google.android.gms.games.thodImpl
{

    final String PV;

    public com.google.android.gms.games.teStatusChangeResult K(Status status)
    {
        class _cls1
            implements com.google.android.gms.games.Notifications.GameMuteStatusChangeResult
        {

            final NotificationsImpl._cls2 PX;
            final Status yJ;

            public Status getStatus()
            {
                return yJ;
            }

            _cls1(Status status)
            {
                PX = NotificationsImpl._cls2.this;
                yJ = status;
                super();
            }
        }

        return new _cls1(status);
    }

    protected volatile void a(com.google.android.gms.common.api.sImpl._cls2 _pcls2)
    {
        a((GamesClientImpl)_pcls2);
    }

    protected void a(GamesClientImpl gamesclientimpl)
    {
        gamesclientimpl.d(this, PV, false);
    }

    public Result c(Status status)
    {
        return K(status);
    }
}
