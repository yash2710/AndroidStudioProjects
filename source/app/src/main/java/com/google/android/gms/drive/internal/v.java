// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.drive.internal;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.drive.Drive;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.DriveResource;
import com.google.android.gms.drive.MetadataChangeSet;

// Referenced classes of package com.google.android.gms.drive.internal:
//            r

public class v
    implements DriveResource
{

    protected final DriveId Hz;

    protected v(DriveId driveid)
    {
        Hz = driveid;
    }

    public PendingResult addChangeListener(GoogleApiClient googleapiclient, com.google.android.gms.drive.events.DriveEvent.Listener listener)
    {
        return ((r)googleapiclient.a(Drive.yH)).a(googleapiclient, Hz, 1, listener);
    }

    public DriveId getDriveId()
    {
        return Hz;
    }

    public PendingResult getMetadata(GoogleApiClient googleapiclient)
    {
        return googleapiclient.a(new _cls1());
    }

    public PendingResult listParents(GoogleApiClient googleapiclient)
    {
        return googleapiclient.a(new _cls2());
    }

    public PendingResult removeChangeListener(GoogleApiClient googleapiclient, com.google.android.gms.drive.events.DriveEvent.Listener listener)
    {
        return ((r)googleapiclient.a(Drive.yH)).b(googleapiclient, Hz, 1, listener);
    }

    public PendingResult updateMetadata(GoogleApiClient googleapiclient, MetadataChangeSet metadatachangeset)
    {
        if (metadatachangeset == null)
        {
            throw new IllegalArgumentException("ChangeSet must be provided.");
        } else
        {
            return googleapiclient.b(new _cls3(metadatachangeset));
        }
    }

    private class _cls1 extends a
    {
        private class a extends q
        {

            final v Jl;

            public Result c(Status status)
            {
                return u(status);
            }

            public com.google.android.gms.drive.DriveResource.MetadataResult u(Status status)
            {
                return new e(status, null);
            }

            private a()
            {
                Jl = v.this;
                super();
            }

            a(_cls1 _pcls1)
            {
                this();
            }

            private class e
                implements com.google.android.gms.drive.DriveResource.MetadataResult
            {

                private final Metadata Jm;
                private final Status yz;

                public Metadata getMetadata()
                {
                    return Jm;
                }

                public Status getStatus()
                {
                    return yz;
                }

                public e(Status status, Metadata metadata)
                {
                    yz = status;
                    Jm = metadata;
                }
            }

        }


        final v Jl;

        protected volatile void a(com.google.android.gms.common.api.Api.a a1)
        {
            a((r)a1);
        }

        protected void a(r r1)
        {
            r1.gp().a(new GetMetadataRequest(Jl.Hz), new d(this));
        }

        _cls1()
        {
            Jl = v.this;
            super(null);
        }

        private class d extends com.google.android.gms.drive.internal.c
        {

            private final com.google.android.gms.common.api.a.d yR;

            public void a(OnMetadataResponse onmetadataresponse)
            {
                yR.a(new e(Status.En, new l(onmetadataresponse.gB())));
            }

            public void o(Status status)
            {
                yR.a(new e(status, null));
            }

            public d(com.google.android.gms.common.api.a.d d1)
            {
                yR = d1;
            }
        }

    }


    private class _cls2 extends c
    {
        private class c extends q
        {

            final v Jl;

            public Result c(Status status)
            {
                return r(status);
            }

            public com.google.android.gms.drive.DriveApi.MetadataBufferResult r(Status status)
            {
                return new p.e(status, null, false);
            }

            private c()
            {
                Jl = v.this;
                super();
            }

            c(_cls1 _pcls1)
            {
                this();
            }
        }


        final v Jl;

        protected volatile void a(com.google.android.gms.common.api.Api.a a1)
        {
            a((r)a1);
        }

        protected void a(r r1)
        {
            r1.gp().a(new ListParentsRequest(Jl.Hz), new b(this));
        }

        _cls2()
        {
            Jl = v.this;
            super(null);
        }

        private class b extends com.google.android.gms.drive.internal.c
        {

            private final com.google.android.gms.common.api.a.d yR;

            public void a(OnListParentsResponse onlistparentsresponse)
            {
                MetadataBuffer metadatabuffer = new MetadataBuffer(onlistparentsresponse.gA(), null);
                yR.a(new p.e(Status.En, metadatabuffer, false));
            }

            public void o(Status status)
            {
                yR.a(new p.e(status, null, false));
            }

            public b(com.google.android.gms.common.api.a.d d)
            {
                yR = d;
            }
        }

    }


    private class _cls3 extends f
    {
        private class f extends q
        {

            final v Jl;

            public Result c(Status status)
            {
                return u(status);
            }

            public com.google.android.gms.drive.DriveResource.MetadataResult u(Status status)
            {
                return new e(status, null);
            }

            private f()
            {
                Jl = v.this;
                super();
            }

            f(_cls1 _pcls1)
            {
                this();
            }
        }


        final MetadataChangeSet Jd;
        final v Jl;

        protected volatile void a(com.google.android.gms.common.api.Api.a a1)
        {
            a((r)a1);
        }

        protected void a(r r1)
        {
            r1.gp().a(new UpdateMetadataRequest(Jl.Hz, Jd.gm()), new d(this));
        }

        _cls3(MetadataChangeSet metadatachangeset)
        {
            Jl = v.this;
            Jd = metadatachangeset;
            super(null);
        }
    }

}
