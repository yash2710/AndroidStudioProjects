// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.games.internal;

import com.google.android.gms.common.api.b;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.games.snapshot.SnapshotMetadataBuffer;

final class  extends b
    implements com.google.android.gms.games.snapshot.
{

    public final SnapshotMetadataBuffer getSnapshots()
    {
        return new SnapshotMetadataBuffer(DG);
    }

    (DataHolder dataholder)
    {
        super(dataholder);
    }
}
