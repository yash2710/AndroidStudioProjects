// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.common.api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Referenced classes of package com.google.android.gms.common.api:
//            Scope

public final class Api
{

    private final b Dm;
    private final c Dn;
    private final ArrayList Do;

    public transient Api(b b, c c, Scope ascope[])
    {
        Dm = b;
        Dn = c;
        Do = new ArrayList(Arrays.asList(ascope));
    }

    public final List eA()
    {
        return Do;
    }

    public final c eB()
    {
        return Dn;
    }

    public final b ez()
    {
        return Dm;
    }
}
