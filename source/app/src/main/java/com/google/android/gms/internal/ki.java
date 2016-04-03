// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.plus.Account;
import com.google.android.gms.plus.Plus;
import com.google.android.gms.plus.internal.e;

// Referenced classes of package com.google.android.gms.internal:
//            hm

public final class ki
    implements Account
{

    public ki()
    {
    }

    private static e a(GoogleApiClient googleapiclient, com.google.android.gms.common.api.Api.c c)
    {
        boolean flag = true;
        boolean flag1;
        e e1;
        if (googleapiclient != null)
        {
            flag1 = flag;
        } else
        {
            flag1 = false;
        }
        hm.b(flag1, "GoogleApiClient parameter is required.");
        hm.a(googleapiclient.isConnected(), "GoogleApiClient must be connected.");
        e1 = (e)googleapiclient.a(c);
        if (e1 == null)
        {
            flag = false;
        }
        hm.a(flag, "GoogleApiClient is not configured to use the Plus.API Api. Pass this into GoogleApiClient.Builder#addApi() to use this feature.");
        return e1;
    }

    public final void clearDefaultAccount(GoogleApiClient googleapiclient)
    {
        a(googleapiclient, Plus.yH).clearDefaultAccount();
    }

    public final String getAccountName(GoogleApiClient googleapiclient)
    {
        return a(googleapiclient, Plus.yH).getAccountName();
    }

    public final PendingResult revokeAccessAndDisconnect(GoogleApiClient googleapiclient)
    {
        return googleapiclient.b(new _cls1());
    }

    private class _cls1 extends a
    {
        private class a extends com.google.android.gms.plus.Plus.a
        {

            public Result c(Status status)
            {
                return d(status);
            }

            public Status d(Status status)
            {
                return status;
            }

            private a()
            {
            }

            a(_cls1 _pcls1)
            {
                this();
            }
        }


        final ki acd;

        protected volatile void a(com.google.android.gms.common.api.Api.a a1)
        {
            a((e)a1);
        }

        protected void a(e e1)
        {
            e1.m(this);
        }

        _cls1()
        {
            acd = ki.this;
            super(null);
        }
    }

}
