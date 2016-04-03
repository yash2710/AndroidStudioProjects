// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.panorama.PanoramaApi;

// Referenced classes of package com.google.android.gms.internal:
//            ke, kd

public class kf
    implements PanoramaApi
{

    public kf()
    {
    }

    private static void a(Context context, Uri uri)
    {
        context.revokeUriPermission(uri, 1);
    }

    private static void a(Context context, ke ke1, kd kd, Uri uri, Bundle bundle)
    {
        context.grantUriPermission("com.google.android.gms", uri, 1);
        _cls4 _lcls4 = new _cls4(context, uri, kd);
        try
        {
            ke1.a(_lcls4, uri, bundle, true);
            return;
        }
        catch (RemoteException remoteexception)
        {
            a(context, uri);
            throw remoteexception;
        }
        catch (RuntimeException runtimeexception)
        {
            a(context, uri);
            throw runtimeexception;
        }
    }

    static void b(Context context, Uri uri)
    {
        a(context, uri);
    }

    static void b(Context context, ke ke1, kd kd, Uri uri, Bundle bundle)
    {
        a(context, ke1, kd, uri, bundle);
    }

    public PendingResult loadPanoramaInfo(GoogleApiClient googleapiclient, Uri uri)
    {
        return googleapiclient.a(new _cls2(uri));
    }

    public PendingResult loadPanoramaInfoAndGrantAccess(GoogleApiClient googleapiclient, Uri uri)
    {
        return googleapiclient.a(new _cls3(uri));
    }

    private class _cls4 extends kd.a
    {

        final Uri abk;
        final kd abn;
        final Context qu;

        public final void a(int i, Bundle bundle, int j, Intent intent)
        {
            kf.b(qu, abk);
            abn.a(i, bundle, j, intent);
        }

        _cls4(Context context, Uri uri, kd kd1)
        {
            qu = context;
            abk = uri;
            abn = kd1;
            super();
        }
    }


    private class _cls2 extends b
    {
        private class b extends d
        {
            private class d extends com.google.android.gms.common.api.a.b
            {

                protected abstract void a(Context context, ke ke1);

                protected volatile void a(com.google.android.gms.common.api.Api.a a1)
                {
                    a((kg)a1);
                }

                protected final void a(kg kg1)
                {
                    a(kg1.getContext(), (ke)kg1.ft());
                }

                protected d()
                {
                    super(Panorama.yH);
                }
            }


            protected com.google.android.gms.panorama.PanoramaApi.PanoramaResult ak(Status status)
            {
                return new kh(status, null);
            }

            protected Result c(Status status)
            {
                return ak(status);
            }

            private b()
            {
            }

            b(_cls1 _pcls1)
            {
                this();
            }
        }


        final Uri abk;
        final kf abm;

        protected void a(Context context, ke ke1)
        {
            ke1.a(new c(this), abk, null, false);
        }

        _cls2(Uri uri)
        {
            abm = kf.this;
            abk = uri;
            super(null);
        }

        private class c extends kd.a
        {

            private final com.google.android.gms.common.api.a.d yR;

            public final void a(int i, Bundle bundle, int j, Intent intent)
            {
                PendingIntent pendingintent;
                Status status;
                if (bundle != null)
                {
                    pendingintent = (PendingIntent)bundle.getParcelable("pendingIntent");
                } else
                {
                    pendingintent = null;
                }
                status = new Status(i, null, pendingintent);
                yR.a(new kh(status, intent));
            }

            public c(com.google.android.gms.common.api.a.d d)
            {
                yR = d;
            }
        }

    }


    private class _cls3 extends b
    {

        final Uri abk;
        final kf abm;

        protected void a(Context context, ke ke1)
        {
            kf.b(context, ke1, new c(this), abk, null);
        }

        _cls3(Uri uri)
        {
            abm = kf.this;
            abk = uri;
            super(null);
        }
    }

}
