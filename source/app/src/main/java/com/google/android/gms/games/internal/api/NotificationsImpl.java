// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.games.internal.api;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.Notifications;
import com.google.android.gms.games.internal.GamesClientImpl;

public final class NotificationsImpl
    implements Notifications
{

    public NotificationsImpl()
    {
    }

    public final void clear(GoogleApiClient googleapiclient, int i)
    {
        Games.c(googleapiclient).ch(i);
    }

    public final void clearAll(GoogleApiClient googleapiclient)
    {
        clear(googleapiclient, 31);
    }
}
