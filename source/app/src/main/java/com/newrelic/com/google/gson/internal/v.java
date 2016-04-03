// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.newrelic.com.google.gson.internal;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

// Referenced classes of package com.newrelic.com.google.gson.internal:
//            LinkedTreeMap, w

abstract class v
    implements Iterator
{

    private w a;
    private w b;
    private int c;
    private LinkedTreeMap d;

    private v(LinkedTreeMap linkedtreemap)
    {
        d = linkedtreemap;
        super();
        a = d.c.d;
        b = null;
        c = d.b;
    }

    v(LinkedTreeMap linkedtreemap, byte byte0)
    {
        this(linkedtreemap);
    }

    final w a()
    {
        w w1 = a;
        if (w1 == d.c)
        {
            throw new NoSuchElementException();
        }
        if (d.b != c)
        {
            throw new ConcurrentModificationException();
        } else
        {
            a = w1.d;
            b = w1;
            return w1;
        }
    }

    public final boolean hasNext()
    {
        return a != d.c;
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
            c = d.b;
            return;
        }
    }
}
