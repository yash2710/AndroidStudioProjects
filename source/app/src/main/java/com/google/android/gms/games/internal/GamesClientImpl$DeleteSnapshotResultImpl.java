// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.games.internal;

import com.google.android.gms.common.api.Status;

final class NK
    implements com.google.android.gms.games.snapshot.NK
{

    private final String NK;
    private final Status yz;

    public final String getSnapshotId()
    {
        return NK;
    }

    public final Status getStatus()
    {
        return yz;
    }

    (int i, String s)
    {
        yz = new Status(i);
        NK = s;
    }
}
