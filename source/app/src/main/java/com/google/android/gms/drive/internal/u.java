// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.drive.internal;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.drive.Contents;
import com.google.android.gms.drive.DriveFolder;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.MetadataChangeSet;
import com.google.android.gms.drive.query.Filters;
import com.google.android.gms.drive.query.Query;
import com.google.android.gms.drive.query.SearchableField;

// Referenced classes of package com.google.android.gms.drive.internal:
//            v, p

public class u extends v
    implements DriveFolder
{

    public u(DriveId driveid)
    {
        super(driveid);
    }

    private PendingResult a(GoogleApiClient googleapiclient, MetadataChangeSet metadatachangeset, Contents contents, int i, boolean flag, String s)
    {
        return googleapiclient.b(new _cls1(contents, metadatachangeset, i, flag, s));
    }

    private PendingResult a(GoogleApiClient googleapiclient, MetadataChangeSet metadatachangeset, Contents contents, boolean flag, String s)
    {
        if (metadatachangeset == null)
        {
            throw new IllegalArgumentException("MetadataChangeSet must be provided.");
        }
        if (contents == null)
        {
            throw new IllegalArgumentException("Contents must be provided.");
        }
        if ("application/vnd.google-apps.folder".equals(metadatachangeset.getMimeType()))
        {
            throw new IllegalArgumentException("May not create folders (mimetype: application/vnd.google-apps.folder) using this method. Use DriveFolder.createFolder() instead.");
        } else
        {
            return a(googleapiclient, metadatachangeset, contents, 0, flag, s);
        }
    }

    public PendingResult createFile(GoogleApiClient googleapiclient, MetadataChangeSet metadatachangeset, Contents contents)
    {
        return a(googleapiclient, metadatachangeset, contents, false, null);
    }

    public PendingResult createFolder(GoogleApiClient googleapiclient, MetadataChangeSet metadatachangeset)
    {
        if (metadatachangeset == null)
        {
            throw new IllegalArgumentException("MetadataChangeSet must be provided.");
        }
        if (metadatachangeset.getMimeType() != null && !metadatachangeset.getMimeType().equals("application/vnd.google-apps.folder"))
        {
            throw new IllegalArgumentException("The mimetype must be of type application/vnd.google-apps.folder");
        } else
        {
            return googleapiclient.b(new _cls2(metadatachangeset));
        }
    }

    public PendingResult listChildren(GoogleApiClient googleapiclient)
    {
        return queryChildren(googleapiclient, null);
    }

    public PendingResult queryChildren(GoogleApiClient googleapiclient, Query query)
    {
        com.google.android.gms.drive.query.Query.Builder builder = (new com.google.android.gms.drive.query.Query.Builder()).addFilter(Filters.in(SearchableField.PARENTS, getDriveId()));
        if (query != null)
        {
            if (query.getFilter() != null)
            {
                builder.addFilter(query.getFilter());
            }
            builder.setPageToken(query.getPageToken());
            builder.setSortOrder(query.getSortOrder());
        }
        return (new p()).query(googleapiclient, builder.build());
    }

    private class _cls1 extends q
    {

        final Contents IM;
        final MetadataChangeSet Jd;
        final int Jf;
        final boolean Jg;
        final String Jh;
        final u Ji;

        protected volatile void a(com.google.android.gms.common.api.Api.a a1)
        {
            a((r)a1);
        }

        protected void a(r r1)
        {
            if (IM != null)
            {
                IM.close();
            }
            r1.gp().a(new CreateFileRequest(Ji.getDriveId(), Jd.gm(), IM, Jf, Jg, Jh), new a(this));
        }

        public Result c(Status status)
        {
            return s(status);
        }

        public com.google.android.gms.drive.DriveFolder.DriveFileResult s(Status status)
        {
            return new d(status, null);
        }

        _cls1(Contents contents, MetadataChangeSet metadatachangeset, int i, boolean flag, String s1)
        {
            Ji = u.this;
            IM = contents;
            Jd = metadatachangeset;
            Jf = i;
            Jg = flag;
            Jh = s1;
            super();
        }

        private class a extends com.google.android.gms.drive.internal.c
        {

            private final com.google.android.gms.common.api.a.d yR;

            public void a(OnDriveIdResponse ondriveidresponse)
            {
                yR.a(new d(Status.En, new s(ondriveidresponse.getDriveId())));
            }

            public void o(Status status)
            {
                yR.a(new d(status, null));
            }

            public a(com.google.android.gms.common.api.a.d d)
            {
                yR = d;
            }
        }


        private class d
            implements com.google.android.gms.drive.DriveFolder.DriveFileResult
        {

            private final DriveFile Jj;
            private final Status yz;

            public DriveFile getDriveFile()
            {
                return Jj;
            }

            public Status getStatus()
            {
                return yz;
            }

            public d(Status status, DriveFile drivefile)
            {
                yz = status;
                Jj = drivefile;
            }
        }

    }


    private class _cls2 extends c
    {
        private class c extends q
        {

            final u Ji;

            public Result c(Status status)
            {
                return t(status);
            }

            public com.google.android.gms.drive.DriveFolder.DriveFolderResult t(Status status)
            {
                return new e(status, null);
            }

            private c()
            {
                Ji = u.this;
                super();
            }

            c(_cls1 _pcls1)
            {
                this();
            }

            private class e
                implements com.google.android.gms.drive.DriveFolder.DriveFolderResult
            {

                private final DriveFolder Jk;
                private final Status yz;

                public DriveFolder getDriveFolder()
                {
                    return Jk;
                }

                public Status getStatus()
                {
                    return yz;
                }

                public e(Status status, DriveFolder drivefolder)
                {
                    yz = status;
                    Jk = drivefolder;
                }
            }

        }


        final MetadataChangeSet Jd;
        final u Ji;

        protected volatile void a(com.google.android.gms.common.api.Api.a a1)
        {
            a((r)a1);
        }

        protected void a(r r1)
        {
            r1.gp().a(new CreateFolderRequest(Ji.getDriveId(), Jd.gm()), new b(this));
        }

        _cls2(MetadataChangeSet metadatachangeset)
        {
            Ji = u.this;
            Jd = metadatachangeset;
            super(null);
        }

        private class b extends com.google.android.gms.drive.internal.c
        {

            private final com.google.android.gms.common.api.a.d yR;

            public void a(OnDriveIdResponse ondriveidresponse)
            {
                yR.a(new e(Status.En, new u(ondriveidresponse.getDriveId())));
            }

            public void o(Status status)
            {
                yR.a(new e(status, null));
            }

            public b(com.google.android.gms.common.api.a.d d)
            {
                yR = d;
            }
        }

    }

}
