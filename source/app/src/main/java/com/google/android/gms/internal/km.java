// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.plus.People;
import com.google.android.gms.plus.Plus;
import com.google.android.gms.plus.internal.e;
import com.google.android.gms.plus.model.people.Person;
import java.util.Collection;

public final class km
    implements People
{

    public km()
    {
    }

    public final Person getCurrentPerson(GoogleApiClient googleapiclient)
    {
        return Plus.a(googleapiclient, Plus.yH).getCurrentPerson();
    }

    public final PendingResult load(GoogleApiClient googleapiclient, Collection collection)
    {
        return googleapiclient.a(new _cls4(collection));
    }

    public final transient PendingResult load(GoogleApiClient googleapiclient, String as[])
    {
        return googleapiclient.a(new _cls5(as));
    }

    public final PendingResult loadConnected(GoogleApiClient googleapiclient)
    {
        return googleapiclient.a(new _cls3());
    }

    public final PendingResult loadVisible(GoogleApiClient googleapiclient, int i, String s)
    {
        return googleapiclient.a(new _cls1(i, s));
    }

    public final PendingResult loadVisible(GoogleApiClient googleapiclient, String s)
    {
        return googleapiclient.a(new _cls2(s));
    }

    private class _cls4 extends a
    {
        private class a extends com.google.android.gms.plus.Plus.a
        {

            public com.google.android.gms.plus.People.LoadPeopleResult ao(Status status)
            {
                class _cls1
                    implements com.google.android.gms.plus.People.LoadPeopleResult
                {

                    final a acq;
                    final Status yJ;

                    public String getNextPageToken()
                    {
                        return null;
                    }

                    public PersonBuffer getPersonBuffer()
                    {
                        return null;
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
                        acq = a.this;
                        yJ = status;
                        super();
                    }
                }

                return new _cls1(status);
            }

            public Result c(Status status)
            {
                return ao(status);
            }

            private a()
            {
            }

            a(_cls1 _pcls1)
            {
                this();
            }
        }


        final km acn;
        final Collection aco;

        protected volatile void a(com.google.android.gms.common.api.Api.a a1)
        {
            a((e)a1);
        }

        protected void a(e e1)
        {
            e1.a(this, aco);
        }

        _cls4(Collection collection)
        {
            acn = km.this;
            aco = collection;
            super(null);
        }
    }


    private class _cls5 extends a
    {

        final km acn;
        final String acp[];

        protected volatile void a(com.google.android.gms.common.api.Api.a a1)
        {
            a((e)a1);
        }

        protected void a(e e1)
        {
            e1.d(this, acp);
        }

        _cls5(String as[])
        {
            acn = km.this;
            acp = as;
            super(null);
        }
    }


    private class _cls3 extends a
    {

        final km acn;

        protected volatile void a(com.google.android.gms.common.api.Api.a a1)
        {
            a((e)a1);
        }

        protected void a(e e1)
        {
            e1.l(this);
        }

        _cls3()
        {
            acn = km.this;
            super(null);
        }
    }


    private class _cls1 extends a
    {

        final String acf;
        final int acm;
        final km acn;

        protected volatile void a(com.google.android.gms.common.api.Api.a a1)
        {
            a((e)a1);
        }

        protected void a(e e1)
        {
            a(e1.a(this, acm, acf));
        }

        _cls1(int i, String s)
        {
            acn = km.this;
            acm = i;
            acf = s;
            super(null);
        }
    }


    private class _cls2 extends a
    {

        final String acf;
        final km acn;

        protected volatile void a(com.google.android.gms.common.api.Api.a a1)
        {
            a((e)a1);
        }

        protected void a(e e1)
        {
            a(e1.r(this, acf));
        }

        _cls2(String s)
        {
            acn = km.this;
            acf = s;
            super(null);
        }
    }

}
