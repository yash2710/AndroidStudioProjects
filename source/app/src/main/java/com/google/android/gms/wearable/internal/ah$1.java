// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.wearable.internal;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;

// Referenced classes of package com.google.android.gms.wearable.internal:
//            d, au, ah

class it> extends d
{

    final ah alX;

    protected volatile void a(com.google.android.gms.common.api. )
    {
        a((au));
    }

    protected void a(au au1)
    {
        au1.p(this);
    }

    protected com.google.android.gms.wearable.pi.GetLocalNodeResult av(Status status)
    {
        return new <init>(status, null);
    }

    protected Result c(Status status)
    {
        return av(status);
    }

    alNodeResult(ah ah)
    {
        alX = ah;
        super();
    }
}
