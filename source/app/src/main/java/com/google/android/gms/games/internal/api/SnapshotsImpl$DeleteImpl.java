// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.games.internal.api;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;

abstract class <init> extends com.google.android.gms.games.mpl
{

    public com.google.android.gms.games.snapshot.esult aa(Status status)
    {
        class _cls1
            implements com.google.android.gms.games.snapshot.Snapshots.DeleteSnapshotResult
        {

            final SnapshotsImpl.DeleteImpl QR;
            final Status yJ;

            public String getSnapshotId()
            {
                return null;
            }

            public Status getStatus()
            {
                return yJ;
            }

            _cls1(Status status)
            {
                QR = SnapshotsImpl.DeleteImpl.this;
                yJ = status;
                super();
            }
        }

        return new _cls1(status);
    }

    public Result c(Status status)
    {
        return aa(status);
    }

    private t()
    {
    }

    t(t t)
    {
        this();
    }
}
