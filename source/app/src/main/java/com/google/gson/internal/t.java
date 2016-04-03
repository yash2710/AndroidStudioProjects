// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.gson.internal;

import java.util.AbstractSet;
import java.util.Iterator;

// Referenced classes of package com.google.gson.internal:
//            LinkedHashTreeMap, u

final class t extends AbstractSet
{

    final LinkedHashTreeMap a;

    t(LinkedHashTreeMap linkedhashtreemap)
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
        return (obj instanceof java.util.Map.Entry) && a.a((java.util.Map.Entry)obj) != null;
    }

    public final Iterator iterator()
    {
        return new u(this);
    }

    public final boolean remove(Object obj)
    {
        y y;
        if (obj instanceof java.util.Map.Entry)
        {
            if ((y = a.a((java.util.Map.Entry)obj)) != null)
            {
                a.a(y, true);
                return true;
            }
        }
        return false;
    }

    public final int size()
    {
        return a.b;
    }
}
