// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.games.internal.api;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;

abstract class <init> extends com.google.android.gms.games.RequestsImpl
{

    public com.google.android.gms.games.request.Impl Y(Status status)
    {
        class _cls1
            implements com.google.android.gms.games.request.Requests.UpdateRequestsResult
        {

            final RequestsImpl.UpdateRequestsImpl QH;
            final Status yJ;

            public Set getRequestIds()
            {
                return null;
            }

            public int getRequestOutcome(String s)
            {
                throw new IllegalArgumentException((new StringBuilder("Unknown request ID ")).append(s).toString());
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
                QH = RequestsImpl.UpdateRequestsImpl.this;
                yJ = status;
                super();
            }
        }

        return new _cls1(status);
    }

    public Result c(Status status)
    {
        return Y(status);
    }

    private _cls1()
    {
    }

    _cls1(_cls1 _pcls1)
    {
        this();
    }
}
