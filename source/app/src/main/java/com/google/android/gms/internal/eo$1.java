// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import android.content.Context;

// Referenced classes of package com.google.android.gms.internal:
//            eo

final class qu
    implements Runnable
{

    final Context qu;

    public final void run()
    {
        synchronized (eo.bU())
        {
            eo.w(eo.q(qu));
            eo.bU().notifyAll();
        }
    }

    (Context context)
    {
        qu = context;
        super();
    }
}
