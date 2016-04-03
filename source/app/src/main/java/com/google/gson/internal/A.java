// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.gson.internal;

import java.util.AbstractSet;
import java.util.Iterator;

// Referenced classes of package com.google.gson.internal:
//            LinkedTreeMap, B

final class A extends AbstractSet
{

    final LinkedTreeMap a;

    A(LinkedTreeMap linkedtreemap)
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
        return (obj instanceof java.util.Map.Entry) && a.a((java.util.Map.Entry)obj) != null;
    }

    public final Iterator iterator()
    {
        return new B(this);
    }

    public final boolean remove(Object obj)
    {
        F f;
        if (obj instanceof java.util.Map.Entry)
        {
            if ((f = a.a((java.util.Map.Entry)obj)) != null)
            {
                a.a(f, true);
                return true;
            }
        }
        return false;
    }

    public final int size()
    {
        return a.a;
    }
}
