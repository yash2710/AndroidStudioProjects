// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.j256.ormlite.dao;

import java.util.Iterator;

// Referenced classes of package com.j256.ormlite.dao:
//            CloseableIterable, BaseDaoImpl, CloseableIterator

class this._cls0
    implements CloseableIterable
{

    final BaseDaoImpl this$0;

    public CloseableIterator closeableIterator()
    {
        CloseableIterator closeableiterator;
        try
        {
            closeableiterator = BaseDaoImpl.access$000(BaseDaoImpl.this, -1);
        }
        catch (Exception exception)
        {
            throw new IllegalStateException((new StringBuilder("Could not build iterator for ")).append(dataClass).toString(), exception);
        }
        return closeableiterator;
    }

    public Iterator iterator()
    {
        return closeableIterator();
    }

    tor()
    {
        this$0 = BaseDaoImpl.this;
        super();
    }
}
