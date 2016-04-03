// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.wearable.internal;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.wearable.PutDataRequest;

// Referenced classes of package com.google.android.gms.wearable.internal:
//            d, au, f

class nit> extends d
{

    final PutDataRequest alB;
    final f alC;

    protected volatile void a(com.google.android.gms.common.api.a a1)
    {
        a((au)a1);
    }

    protected void a(au au1)
    {
        au1.a(this, alB);
    }

    public com.google.android.gms.wearable.Api.DataItemResult aq(Status status)
    {
        return new <init>(status, null);
    }

    public Result c(Status status)
    {
        return aq(status);
    }

    t(f f, PutDataRequest putdatarequest)
    {
        alC = f;
        alB = putdatarequest;
        super();
    }
}
