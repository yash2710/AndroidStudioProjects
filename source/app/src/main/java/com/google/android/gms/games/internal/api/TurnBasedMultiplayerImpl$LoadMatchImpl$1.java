// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.games.internal.api;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMatch;

class yJ
    implements com.google.android.gms.games.multiplayer.turnbased._cls1
{

    final yJ Rf;
    final Status yJ;

    public TurnBasedMatch getMatch()
    {
        return null;
    }

    public Status getStatus()
    {
        return yJ;
    }

    sult(sult sult, Status status)
    {
        Rf = sult;
        yJ = status;
        super();
    }
}