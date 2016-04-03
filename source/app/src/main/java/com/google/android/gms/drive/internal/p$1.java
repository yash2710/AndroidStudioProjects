// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.drive.internal;

import com.google.android.gms.drive.query.Query;

// Referenced classes of package com.google.android.gms.drive.internal:
//            r, QueryRequest, aa, p

class <init> extends <init>
{

    final Query IK;
    final p IL;

    protected volatile void a(com.google.android.gms.common.api.a a1)
    {
        a((r)a1);
    }

    protected void a(r r1)
    {
        r1.gp().a(new QueryRequest(IK), new <init>(this));
    }

    yRequest(p p, Query query)
    {
        IL = p;
        IK = query;
        super(p);
    }
}
