// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import java.util.Map;

// Referenced classes of package com.google.android.gms.internal:
//            bc, eu, es, ex, 
//            ev

final class 
    implements bc
{

    public final void b(ex ex1, Map map)
    {
        String s = (String)map.get("u");
        if (s == null)
        {
            eu.D("URL missing from httpTrack GMSG.");
            return;
        } else
        {
            (new es(ex1.getContext(), ex1.cd().sw, s)).start();
            return;
        }
    }

    ()
    {
    }
}
