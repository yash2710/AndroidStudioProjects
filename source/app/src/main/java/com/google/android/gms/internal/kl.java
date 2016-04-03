// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import android.net.Uri;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.plus.Moments;
import com.google.android.gms.plus.model.moments.Moment;

public final class kl
    implements Moments
{

    public kl()
    {
    }

    public final PendingResult load(GoogleApiClient googleapiclient)
    {
        return googleapiclient.a(new _cls1());
    }

    public final PendingResult load(GoogleApiClient googleapiclient, int i, String s, Uri uri, String s1, String s2)
    {
        return googleapiclient.a(new _cls2(i, s, uri, s1, s2));
    }

    public final PendingResult remove(GoogleApiClient googleapiclient, String s)
    {
        return googleapiclient.b(new _cls4(s));
    }

    public final PendingResult write(GoogleApiClient googleapiclient, Moment moment)
    {
        return googleapiclient.b(new _cls3(moment));
    }

    private class _cls1 extends a
    {
        private class a extends com.google.android.gms.plus.Plus.a
        {

            public com.google.android.gms.plus.Moments.LoadMomentsResult an(Status status)
            {
                class _cls1
                    implements com.google.android.gms.plus.Moments.LoadMomentsResult
                {

                    final a acl;
                    final Status yJ;

                    public MomentBuffer getMomentBuffer()
                    {
                        return null;
                    }

                    public String getNextPageToken()
                    {
                        return null;
                    }

                    public Status getStatus()
                    {
                        return yJ;
                    }

                    public String getUpdated()
                    {
                        return null;
                    }

                    public void release()
                    {
                    }

                    _cls1(Status status)
                    {
                        acl = a.this;
                        yJ = status;
                        super();
                    }
                }

                return new _cls1(status);
            }

            public Result c(Status status)
            {
                return an(status);
            }

            private a()
            {
            }

            a(_cls1 _pcls1)
            {
                this();
            }
        }


        final kl ace;

        protected volatile void a(com.google.android.gms.common.api.Api.a a1)
        {
            a((e)a1);
        }

        protected void a(e e1)
        {
            e1.k(this);
        }

        _cls1()
        {
            ace = kl.this;
            super(null);
        }
    }


    private class _cls2 extends a
    {

        final int PM;
        final kl ace;
        final String acf;
        final Uri acg;
        final String ach;
        final String aci;

        protected volatile void a(com.google.android.gms.common.api.Api.a a1)
        {
            a((e)a1);
        }

        protected void a(e e1)
        {
            e1.a(this, PM, acf, acg, ach, aci);
        }

        _cls2(int i, String s, Uri uri, String s1, String s2)
        {
            ace = kl.this;
            PM = i;
            acf = s;
            acg = uri;
            ach = s1;
            aci = s2;
            super(null);
        }
    }


    private class _cls4 extends b
    {
        private class b extends com.google.android.gms.plus.Plus.a
        {

            public Result c(Status status)
            {
                return d(status);
            }

            public Status d(Status status)
            {
                return status;
            }

            private b()
            {
            }

            b(_cls1 _pcls1)
            {
                this();
            }
        }


        final kl ace;
        final String ack;

        protected volatile void a(com.google.android.gms.common.api.Api.a a1)
        {
            a((e)a1);
        }

        protected void a(e e1)
        {
            e1.removeMoment(ack);
            b(Status.En);
        }

        _cls4(String s)
        {
            ace = kl.this;
            ack = s;
            super(null);
        }
    }


    private class _cls3 extends c
    {
        private class c extends com.google.android.gms.plus.Plus.a
        {

            public Result c(Status status)
            {
                return d(status);
            }

            public Status d(Status status)
            {
                return status;
            }

            private c()
            {
            }

            c(_cls1 _pcls1)
            {
                this();
            }
        }


        final kl ace;
        final Moment acj;

        protected volatile void a(com.google.android.gms.common.api.Api.a a1)
        {
            a((e)a1);
        }

        protected void a(e e1)
        {
            e1.a(this, acj);
        }

        _cls3(Moment moment)
        {
            ace = kl.this;
            acj = moment;
            super(null);
        }
    }

}
