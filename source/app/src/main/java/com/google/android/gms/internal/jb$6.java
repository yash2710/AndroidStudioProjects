// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import com.google.android.gms.common.api.Status;

// Referenced classes of package com.google.android.gms.internal:
//            jg, jb

class <init> extends <init>
{

    final boolean VA;
    final jb Vy;

    protected volatile void a(com.google.android.gms.common.api. )
    {
        a((jg));
    }

    protected void a(jg jg1)
    {
        jg1.setMockMode(VA);
        b(Status.En);
    }

    .a(jb jb, boolean flag)
    {
        Vy = jb;
        VA = flag;
        super(null);
    }
}
