// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;


// Referenced classes of package com.google.android.gms.internal:
//            gh

class Cd
    implements Runnable
{

    final int Cd;
    final Cd Ce;

    public void run()
    {
        if (gh.d(Ce.) != null)
        {
            gh.d(Ce.).onApplicationDisconnected(Cd);
        }
    }

    er(er er, int i)
    {
        Ce = er;
        Cd = i;
        super();
    }
}
