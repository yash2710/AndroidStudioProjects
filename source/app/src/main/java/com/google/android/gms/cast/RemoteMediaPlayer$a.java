// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.cast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.internal.gp;
import java.io.IOException;

// Referenced classes of package com.google.android.gms.cast:
//            Cast, RemoteMediaPlayer

class Bn
    implements gp
{

    final RemoteMediaPlayer Ba;
    private GoogleApiClient Bm;
    private long Bn;

    public void a(String s, String s1, long l, String s2)
    {
        if (Bm == null)
        {
            throw new IOException("No GoogleApiClient available");
        } else
        {
            class a
                implements ResultCallback
            {

                private final long Bo;
                final RemoteMediaPlayer.a Bp;

                public final void k(Status status)
                {
                    if (!status.isSuccess())
                    {
                        RemoteMediaPlayer.e(Bp.Ba).a(Bo, status.getStatusCode());
                    }
                }

                public final void onResult(Result result)
                {
                    k((Status)result);
                }

            a(long l)
            {
                Bp = RemoteMediaPlayer.a.this;
                super();
                Bo = l;
            }
            }

            Cast.CastApi.sage(Bm, s, s1).setResultCallback(new a(l));
            return;
        }
    }

    public void b(GoogleApiClient googleapiclient)
    {
        Bm = googleapiclient;
    }

    public long eb()
    {
        long l = 1L + Bn;
        Bn = l;
        return l;
    }

    public t(RemoteMediaPlayer remotemediaplayer)
    {
        Ba = remotemediaplayer;
        super();
        Bn = 0L;
    }
}
