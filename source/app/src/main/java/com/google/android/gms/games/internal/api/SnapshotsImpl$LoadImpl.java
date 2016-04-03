// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.games.internal.api;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;

abstract class <init> extends com.google.android.gms.games.dImpl
{

    public com.google.android.gms.games.snapshot.Result ab(Status status)
    {
        class _cls1
            implements com.google.android.gms.games.snapshot.Snapshots.LoadSnapshotsResult
        {

            final SnapshotsImpl.LoadImpl QS;
            final Status yJ;

            public SnapshotMetadataBuffer getSnapshots()
            {
                return new SnapshotMetadataBuffer(DataHolder.af(14));
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
                QS = SnapshotsImpl.LoadImpl.this;
                yJ = status;
                super();
            }
        }

        return new _cls1(status);
    }

    public Result c(Status status)
    {
        return ab(status);
    }

    private lt()
    {
    }

    lt(lt lt)
    {
        this();
    }
}
