// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.j256.ormlite.dao;

import java.util.Iterator;

// Referenced classes of package com.j256.ormlite.dao:
//            CloseableIterable, LazyForeignCollection, Dao, CloseableIterator

class val.flags
    implements CloseableIterable
{

    final LazyForeignCollection this$0;
    final int val$flags;

    public CloseableIterator closeableIterator()
    {
        CloseableIterator closeableiterator;
        try
        {
            closeableiterator = LazyForeignCollection.access$000(LazyForeignCollection.this, val$flags);
        }
        catch (Exception exception)
        {
            throw new IllegalStateException((new StringBuilder("Could not build lazy iterator for ")).append(dao.getDataClass()).toString(), exception);
        }
        return closeableiterator;
    }

    public CloseableIterator iterator()
    {
        return closeableIterator();
    }

    public volatile Iterator iterator()
    {
        return iterator();
    }

    ()
    {
        this$0 = final_lazyforeigncollection;
        val$flags = I.this;
        super();
    }
}
