// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.gson.internal;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

// Referenced classes of package com.google.gson.internal:
//            LinkedHashTreeMap, y

abstract class x
    implements Iterator
{

    private y a;
    private y b;
    private int c;
    private LinkedHashTreeMap d;

    private x(LinkedHashTreeMap linkedhashtreemap)
    {
        d = linkedhashtreemap;
        super();
        a = d.a.d;
        b = null;
        c = d.c;
    }

    x(LinkedHashTreeMap linkedhashtreemap, byte byte0)
    {
        this(linkedhashtreemap);
    }

    final y a()
    {
        y y1 = a;
        if (y1 == d.a)
        {
            throw new NoSuchElementException();
        }
        if (d.c != c)
        {
            throw new ConcurrentModificationException();
        } else
        {
            a = y1.d;
            b = y1;
            return y1;
        }
    }

    public final boolean hasNext()
    {
        return a != d.a;
    }

    public final void remove()
    {
        if (b == null)
        {
            throw new IllegalStateException();
        } else
        {
            d.a(b, true);
            b = null;
            c = d.c;
            return;
        }
    }
}
