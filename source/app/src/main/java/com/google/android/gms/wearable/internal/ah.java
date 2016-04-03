// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.wearable.internal;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.wearable.NodeApi;

public final class ah
    implements NodeApi
{

    public ah()
    {
    }

    public final PendingResult addListener(GoogleApiClient googleapiclient, com.google.android.gms.wearable.NodeApi.NodeListener nodelistener)
    {
        return googleapiclient.a(new _cls3(nodelistener));
    }

    public final PendingResult getConnectedNodes(GoogleApiClient googleapiclient)
    {
        return googleapiclient.a(new _cls2());
    }

    public final PendingResult getLocalNode(GoogleApiClient googleapiclient)
    {
        return googleapiclient.a(new _cls1());
    }

    public final PendingResult removeListener(GoogleApiClient googleapiclient, com.google.android.gms.wearable.NodeApi.NodeListener nodelistener)
    {
        return googleapiclient.a(new _cls4(nodelistener));
    }

    private class _cls3 extends d
    {

        final ah alX;
        final com.google.android.gms.wearable.NodeApi.NodeListener alY;

        protected volatile void a(com.google.android.gms.common.api.Api.a a1)
        {
            a((au)a1);
        }

        protected void a(au au1)
        {
            au1.a(this, alY);
        }

        public Result c(Status status)
        {
            return d(status);
        }

        public Status d(Status status)
        {
            return new Status(13);
        }

        _cls3(com.google.android.gms.wearable.NodeApi.NodeListener nodelistener)
        {
            alX = ah.this;
            alY = nodelistener;
            super();
        }
    }


    private class _cls2 extends d
    {

        final ah alX;

        protected volatile void a(com.google.android.gms.common.api.Api.a a1)
        {
            a((au)a1);
        }

        protected void a(au au1)
        {
            au1.q(this);
        }

        protected com.google.android.gms.wearable.NodeApi.GetConnectedNodesResult aw(Status status)
        {
            return new a(status, null);
        }

        protected Result c(Status status)
        {
            return aw(status);
        }

        _cls2()
        {
            alX = ah.this;
            super();
        }

        private class a
            implements com.google.android.gms.wearable.NodeApi.GetConnectedNodesResult
        {

            private final List alZ;
            private final Status yz;

            public List getNodes()
            {
                return alZ;
            }

            public Status getStatus()
            {
                return yz;
            }

            public a(Status status, List list)
            {
                yz = status;
                alZ = list;
            }
        }

    }


    private class _cls1 extends d
    {

        final ah alX;

        protected volatile void a(com.google.android.gms.common.api.Api.a a1)
        {
            a((au)a1);
        }

        protected void a(au au1)
        {
            au1.p(this);
        }

        protected com.google.android.gms.wearable.NodeApi.GetLocalNodeResult av(Status status)
        {
            return new b(status, null);
        }

        protected Result c(Status status)
        {
            return av(status);
        }

        _cls1()
        {
            alX = ah.this;
            super();
        }

        private class b
            implements com.google.android.gms.wearable.NodeApi.GetLocalNodeResult
        {

            private final Node ama;
            private final Status yz;

            public Node getNode()
            {
                return ama;
            }

            public Status getStatus()
            {
                return yz;
            }

            public b(Status status, Node node)
            {
                yz = status;
                ama = node;
            }
        }

    }


    private class _cls4 extends d
    {

        final ah alX;
        final com.google.android.gms.wearable.NodeApi.NodeListener alY;

        protected volatile void a(com.google.android.gms.common.api.Api.a a1)
        {
            a((au)a1);
        }

        protected void a(au au1)
        {
            au1.b(this, alY);
        }

        public Result c(Status status)
        {
            return d(status);
        }

        public Status d(Status status)
        {
            return new Status(13);
        }

        _cls4(com.google.android.gms.wearable.NodeApi.NodeListener nodelistener)
        {
            alX = ah.this;
            alY = nodelistener;
            super();
        }
    }

}
