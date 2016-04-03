// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.mygson.internal;

import java.util.AbstractSet;
import java.util.Iterator;

// Referenced classes of package com.google.mygson.internal:
//            LinkedTreeMap, s

final class r extends AbstractSet
{

    final LinkedTreeMap a;

    r(LinkedTreeMap linkedtreemap)
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
        return new s(this);
    }

    public final boolean remove(Object obj)
    {
        w w;
        if (obj instanceof java.util.Map.Entry)
        {
            if ((w = a.a((java.util.Map.Entry)obj)) != null)
            {
                a.a(w, true);
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
