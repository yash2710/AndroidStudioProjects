// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;

abstract class yA
    implements Result
{

    protected final Object yA;
    private final Status yz;

    public Status getStatus()
    {
        return yz;
    }

    public tus(Status status, Object obj)
    {
        yz = status;
        yA = obj;
    }
}
