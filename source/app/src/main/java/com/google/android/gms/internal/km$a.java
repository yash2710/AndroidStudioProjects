// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;

abstract class <init> extends com.google.android.gms.plus.a
{

    public com.google.android.gms.plus.e.LoadPeopleResult ao(Status status)
    {
        class _cls1
            implements com.google.android.gms.plus.People.LoadPeopleResult
        {

            final km.a acq;
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
                acq = km.a.this;
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

    private ult()
    {
    }

    ult(ult ult)
    {
        this();
    }
}
