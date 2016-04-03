// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.plus.internal;

import com.google.android.gms.common.api.Status;

// Referenced classes of package com.google.android.gms.plus.internal:
//            e

final class yz extends com.google.android.gms.internal.
{

    final e abM;
    private final Status yz;

    protected final void d(Object obj)
    {
        n((com.google.android.gms.common.api.)obj);
    }

    protected final void fu()
    {
    }

    protected final void n(com.google.android.gms.common.api..h h)
    {
        abM.disconnect();
        if (h != null)
        {
            h.a(yz);
        }
    }

    public (e e1, com.google.android.gms.common.api. , Status status)
    {
        abM = e1;
        super(e1, );
        yz = status;
    }
}
