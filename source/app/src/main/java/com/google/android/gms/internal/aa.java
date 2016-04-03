// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import org.json.JSONObject;

// Referenced classes of package com.google.android.gms.internal:
//            ev

public final class aa
{

    private final String lo;
    private final JSONObject lp;
    private final String lq;
    private final String lr;

    public aa(String s, ev ev1, String s1, JSONObject jsonobject)
    {
        lr = ev1.sw;
        lp = jsonobject;
        lq = s;
        lo = s1;
    }

    public final String ar()
    {
        return lo;
    }

    public final String as()
    {
        return lr;
    }

    public final JSONObject at()
    {
        return lp;
    }

    public final String au()
    {
        return lq;
    }
}
