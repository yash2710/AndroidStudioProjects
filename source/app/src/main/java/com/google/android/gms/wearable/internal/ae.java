// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.wearable.internal;

import android.content.IntentFilter;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.wearable.MessageApi;

public final class ae
    implements MessageApi
{

    public ae()
    {
    }

    private PendingResult a(GoogleApiClient googleapiclient, com.google.android.gms.wearable.MessageApi.MessageListener messagelistener, IntentFilter aintentfilter[])
    {
        return googleapiclient.a(new _cls2(messagelistener, aintentfilter));
    }

    public final PendingResult addListener(GoogleApiClient googleapiclient, com.google.android.gms.wearable.MessageApi.MessageListener messagelistener)
    {
        return a(googleapiclient, messagelistener, null);
    }

    public final PendingResult removeListener(GoogleApiClient googleapiclient, com.google.android.gms.wearable.MessageApi.MessageListener messagelistener)
    {
        return googleapiclient.a(new _cls3(messagelistener));
    }

    public final PendingResult sendMessage(GoogleApiClient googleapiclient, String s, String s1, byte abyte0[])
    {
        return googleapiclient.a(new _cls1(s, s1, abyte0));
    }

    private class _cls2 extends d
    {

        final IntentFilter alG[];
        final ae alT;
        final com.google.android.gms.wearable.MessageApi.MessageListener alU;

        protected volatile void a(com.google.android.gms.common.api.Api.a a1)
        {
            a((au)a1);
        }

        protected void a(au au1)
        {
            au1.a(this, alU, alG);
        }

        public Result c(Status status)
        {
            return d(status);
        }

        public Status d(Status status)
        {
            return new Status(13);
        }

        _cls2(com.google.android.gms.wearable.MessageApi.MessageListener messagelistener, IntentFilter aintentfilter[])
        {
            alT = ae.this;
            alU = messagelistener;
            alG = aintentfilter;
            super();
        }
    }


    private class _cls3 extends d
    {

        final ae alT;
        final com.google.android.gms.wearable.MessageApi.MessageListener alU;

        protected volatile void a(com.google.android.gms.common.api.Api.a a1)
        {
            a((au)a1);
        }

        protected void a(au au1)
        {
            au1.a(this, alU);
        }

        public Result c(Status status)
        {
            return d(status);
        }

        public Status d(Status status)
        {
            return new Status(13);
        }

        _cls3(com.google.android.gms.wearable.MessageApi.MessageListener messagelistener)
        {
            alT = ae.this;
            alU = messagelistener;
            super();
        }
    }


    private class _cls1 extends d
    {

        final String alR;
        final String alS;
        final ae alT;
        final byte yL[];

        protected volatile void a(com.google.android.gms.common.api.Api.a a1)
        {
            a((au)a1);
        }

        protected void a(au au1)
        {
            au1.a(this, alR, alS, yL);
        }

        protected com.google.android.gms.wearable.MessageApi.SendMessageResult au(Status status)
        {
            return new a(status, -1);
        }

        protected Result c(Status status)
        {
            return au(status);
        }

        _cls1(String s, String s1, byte abyte0[])
        {
            alT = ae.this;
            alR = s;
            alS = s1;
            yL = abyte0;
            super();
        }

        private class a
            implements com.google.android.gms.wearable.MessageApi.SendMessageResult
        {

            private final int ra;
            private final Status yz;

            public int getRequestId()
            {
                return ra;
            }

            public Status getStatus()
            {
                return yz;
            }

            public a(Status status, int i)
            {
                yz = status;
                ra = i;
            }
        }

    }

}
