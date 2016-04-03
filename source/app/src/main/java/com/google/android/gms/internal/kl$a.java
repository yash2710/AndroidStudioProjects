// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;

abstract class <init> extends com.google.android.gms.plus.a
{

    public com.google.android.gms.plus.ts.LoadMomentsResult an(Status status)
    {
        class _cls1
            implements com.google.android.gms.plus.Moments.LoadMomentsResult
        {

            final kl.a acl;
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
                acl = kl.a.this;
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

    private ult()
    {
    }

    ult(ult ult)
    {
        this();
    }
}
