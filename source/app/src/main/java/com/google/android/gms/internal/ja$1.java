// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import android.app.PendingIntent;
import com.google.android.gms.common.api.Status;

// Referenced classes of package com.google.android.gms.internal:
//            jg, ja

class <init> extends <init>
{

    final long Vt;
    final PendingIntent Vu;
    final ja Vv;

    protected volatile void a(com.google.android.gms.common.api. )
    {
        a((jg));
    }

    protected void a(jg jg1)
    {
        jg1.requestActivityUpdates(Vt, Vu);
        b(Status.En);
    }

    .a(ja ja, long l, PendingIntent pendingintent)
    {
        Vv = ja;
        Vt = l;
        Vu = pendingintent;
        super(null);
    }
}
