// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.mygson.internal;

import java.util.AbstractSet;
import java.util.Iterator;

// Referenced classes of package com.google.mygson.internal:
//            LinkedTreeMap, u

final class t extends AbstractSet
{

    final LinkedTreeMap a;

    t(LinkedTreeMap linkedtreemap)
    {
        a = linkedtreemap;
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
        return new u(this);
    }

    public final boolean remove(Object obj)
    {
        return a.a(obj) != null;
    }

    public final int size()
    {
        return a.a;
    }
}
