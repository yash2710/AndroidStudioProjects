// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.plus.internal.e;

// Referenced classes of package com.google.android.gms.internal:
//            kl

class <init> extends <init>
{

    final kl ace;
    final String ack;

    protected volatile void a(com.google.android.gms.common.api. )
    {
        a((e));
    }

    protected void a(e e1)
    {
        e1.removeMoment(ack);
        b(Status.En);
    }

    e(kl kl, String s)
    {
        ace = kl;
        ack = s;
        super(null);
    }
}
