// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.gson.internal;

import java.util.AbstractSet;
import java.util.Iterator;

// Referenced classes of package com.google.gson.internal:
//            LinkedHashTreeMap, w

final class v extends AbstractSet
{

    final LinkedHashTreeMap a;

    v(LinkedHashTreeMap linkedhashtreemap)
    {
        a = linkedhashtreemap;
        super();
    }

    public final void clear()
    {
        a.clear();
    }

    public final boolean contains(Object obj)
    {
        return a.containsKey(obj);
    }

    public final Iterator iterator()
    {
        return new w(this);
    }

    public final boolean remove(Object obj)
    {
        return a.a(obj) != null;
    }

    public final int size()
    {
        return a.b;
    }
}
