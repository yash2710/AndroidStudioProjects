// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.games.internal.api;

import android.content.Intent;
import android.os.Bundle;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.drive.Contents;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.internal.GamesClientImpl;
import com.google.android.gms.games.snapshot.Snapshot;
import com.google.android.gms.games.snapshot.SnapshotMetadata;
import com.google.android.gms.games.snapshot.SnapshotMetadataChange;
import com.google.android.gms.games.snapshot.Snapshots;

public final class SnapshotsImpl
    implements Snapshots
{

    public SnapshotsImpl()
    {
    }

    public final PendingResult commitAndClose(GoogleApiClient googleapiclient, Snapshot snapshot, SnapshotMetadataChange snapshotmetadatachange)
    {
        return googleapiclient.b(new _cls3(snapshot, snapshotmetadatachange));
    }

    public final PendingResult delete(GoogleApiClient googleapiclient, SnapshotMetadata snapshotmetadata)
    {
        return googleapiclient.b(new _cls4(snapshotmetadata));
    }

    public final void discardAndClose(GoogleApiClient googleapiclient, Snapshot snapshot)
    {
        Games.c(googleapiclient).a(snapshot);
    }

    public final int getMaxCoverImageSize(GoogleApiClient googleapiclient)
    {
        return Games.c(googleapiclient).hu();
    }

    public final int getMaxDataSize(GoogleApiClient googleapiclient)
    {
        return Games.c(googleapiclient).ht();
    }

    public final Intent getSelectSnapshotIntent(GoogleApiClient googleapiclient, String s, boolean flag, boolean flag1, int i)
    {
        return Games.c(googleapiclient).a(s, flag, flag1, i);
    }

    public final SnapshotMetadata getSnapshotFromBundle(Bundle bundle)
    {
        if (bundle == null || !bundle.containsKey("com.google.android.gms.games.SNAPSHOT_METADATA"))
        {
            return null;
        } else
        {
            return (SnapshotMetadata)bundle.getParcelable("com.google.android.gms.games.SNAPSHOT_METADATA");
        }
    }

    public final PendingResult load(GoogleApiClient googleapiclient, boolean flag)
    {
        return googleapiclient.a(new _cls1(flag));
    }

    public final PendingResult open(GoogleApiClient googleapiclient, SnapshotMetadata snapshotmetadata)
    {
        return open(googleapiclient, snapshotmetadata.getUniqueName(), false);
    }

    public final PendingResult open(GoogleApiClient googleapiclient, String s, boolean flag)
    {
        return googleapiclient.b(new _cls2(s, flag));
    }

    public final PendingResult resolveConflict(GoogleApiClient googleapiclient, String s, Snapshot snapshot)
    {
        SnapshotMetadata snapshotmetadata = snapshot.getMetadata();
        SnapshotMetadataChange snapshotmetadatachange = (new com.google.android.gms.games.snapshot.SnapshotMetadataChange.Builder()).fromMetadata(snapshotmetadata).build();
        return resolveConflict(googleapiclient, s, snapshotmetadata.getSnapshotId(), snapshotmetadatachange, snapshot.getContents());
    }

    public final PendingResult resolveConflict(GoogleApiClient googleapiclient, String s, String s1, SnapshotMetadataChange snapshotmetadatachange, Contents contents)
    {
        return googleapiclient.b(new _cls5(s, s1, snapshotmetadatachange, contents));
    }

    private class _cls3 extends CommitImpl
    {
        private class CommitImpl extends com.google.android.gms.games.Games.BaseGamesApiMethodImpl
        {

            public com.google.android.gms.games.snapshot.Snapshots.CommitSnapshotResult Z(Status status)
            {
                class _cls1
                    implements com.google.android.gms.games.snapshot.Snapshots.CommitSnapshotResult
                {

                    final CommitImpl QQ;
                    final Status yJ;

                    public SnapshotMetadata getSnapshotMetadata()
                    {
                        return null;
                    }

                    public Status getStatus()
                    {
                        return yJ;
                    }

                    _cls1(Status status)
                    {
                        QQ = CommitImpl.this;
                        yJ = status;
                        super();
                    }
                }

                return new _cls1(status);
            }

            public Result c(Status status)
            {
                return Z(status);
            }

            private CommitImpl()
            {
            }

            CommitImpl(_cls1 _pcls1)
            {
                this();
            }
        }


        final SnapshotsImpl QI;
        final Snapshot QL;
        final SnapshotMetadataChange QM;

        protected volatile void a(com.google.android.gms.common.api.Api.a a1)
        {
            a((GamesClientImpl)a1);
        }

        protected void a(GamesClientImpl gamesclientimpl)
        {
            gamesclientimpl.a(this, QL, QM);
        }

        _cls3(Snapshot snapshot, SnapshotMetadataChange snapshotmetadatachange)
        {
            QI = SnapshotsImpl.this;
            QL = snapshot;
            QM = snapshotmetadatachange;
            super(null);
        }
    }


    private class _cls4 extends DeleteImpl
    {
        private class DeleteImpl extends com.google.android.gms.games.Games.BaseGamesApiMethodImpl
        {

            public com.google.android.gms.games.snapshot.Snapshots.DeleteSnapshotResult aa(Status status)
            {
                class _cls1
                    implements com.google.android.gms.games.snapshot.Snapshots.DeleteSnapshotResult
                {

                    final DeleteImpl QR;
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
                        QR = DeleteImpl.this;
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

            private DeleteImpl()
            {
            }

            DeleteImpl(_cls1 _pcls1)
            {
                this();
            }
        }


        final SnapshotsImpl QI;
        final SnapshotMetadata QN;

        protected volatile void a(com.google.android.gms.common.api.Api.a a1)
        {
            a((GamesClientImpl)a1);
        }

        protected void a(GamesClientImpl gamesclientimpl)
        {
            gamesclientimpl.j(this, QN.getSnapshotId());
        }

        _cls4(SnapshotMetadata snapshotmetadata)
        {
            QI = SnapshotsImpl.this;
            QN = snapshotmetadata;
            super(null);
        }
    }


    private class _cls1 extends LoadImpl
    {
        private class LoadImpl extends com.google.android.gms.games.Games.BaseGamesApiMethodImpl
        {

            public com.google.android.gms.games.snapshot.Snapshots.LoadSnapshotsResult ab(Status status)
            {
                class _cls1
                    implements com.google.android.gms.games.snapshot.Snapshots.LoadSnapshotsResult
                {

                    final LoadImpl QS;
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
                        QS = LoadImpl.this;
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

            private LoadImpl()
            {
            }

            LoadImpl(_cls1 _pcls1)
            {
                this();
            }
        }


        final boolean Pe;
        final SnapshotsImpl QI;

        protected volatile void a(com.google.android.gms.common.api.Api.a a1)
        {
            a((GamesClientImpl)a1);
        }

        protected void a(GamesClientImpl gamesclientimpl)
        {
            gamesclientimpl.e(this, Pe);
        }

        _cls1(boolean flag)
        {
            QI = SnapshotsImpl.this;
            Pe = flag;
            super(null);
        }
    }


    private class _cls2 extends OpenImpl
    {
        private class OpenImpl extends com.google.android.gms.games.Games.BaseGamesApiMethodImpl
        {

            public com.google.android.gms.games.snapshot.Snapshots.OpenSnapshotResult ac(Status status)
            {
                class _cls1
                    implements com.google.android.gms.games.snapshot.Snapshots.OpenSnapshotResult
                {

                    final OpenImpl QT;
                    final Status yJ;

                    public String getConflictId()
                    {
                        return null;
                    }

                    public Snapshot getConflictingSnapshot()
                    {
                        return null;
                    }

                    public Contents getResolutionContents()
                    {
                        return null;
                    }

                    public Snapshot getSnapshot()
                    {
                        return null;
                    }

                    public Status getStatus()
                    {
                        return yJ;
                    }

                    _cls1(Status status)
                    {
                        QT = OpenImpl.this;
                        yJ = status;
                        super();
                    }
                }

                return new _cls1(status);
            }

            public Result c(Status status)
            {
                return ac(status);
            }

            private OpenImpl()
            {
            }

            OpenImpl(_cls1 _pcls1)
            {
                this();
            }
        }


        final SnapshotsImpl QI;
        final String QJ;
        final boolean QK;

        protected volatile void a(com.google.android.gms.common.api.Api.a a1)
        {
            a((GamesClientImpl)a1);
        }

        protected void a(GamesClientImpl gamesclientimpl)
        {
            gamesclientimpl.b(this, QJ, QK);
        }

        _cls2(String s, boolean flag)
        {
            QI = SnapshotsImpl.this;
            QJ = s;
            QK = flag;
            super(null);
        }
    }


    private class _cls5 extends OpenImpl
    {

        final Contents IM;
        final SnapshotsImpl QI;
        final SnapshotMetadataChange QM;
        final String QO;
        final String QP;

        protected volatile void a(com.google.android.gms.common.api.Api.a a1)
        {
            a((GamesClientImpl)a1);
        }

        protected void a(GamesClientImpl gamesclientimpl)
        {
            gamesclientimpl.a(this, QO, QP, QM, IM);
        }

        _cls5(String s, String s1, SnapshotMetadataChange snapshotmetadatachange, Contents contents)
        {
            QI = SnapshotsImpl.this;
            QO = s;
            QP = s1;
            QM = snapshotmetadatachange;
            IM = contents;
            super(null);
        }
    }

}
