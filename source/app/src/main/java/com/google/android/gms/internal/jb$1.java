// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;

// Referenced classes of package com.google.android.gms.internal:
//            jg, jb

class <init> extends <init>
{

    final LocationRequest Vw;
    final LocationListener Vx;
    final jb Vy;

    protected volatile void a(com.google.android.gms.common.api. )
    {
        a((jg));
    }

    protected void a(jg jg1)
    {
        jg1.requestLocationUpdates(Vw, Vx);
        b(Status.En);
    }

    ionListener(jb jb, LocationRequest locationrequest, LocationListener locationlistener)
    {
        Vy = jb;
        Vw = locationrequest;
        Vx = locationlistener;
        super(null);
    }
}
