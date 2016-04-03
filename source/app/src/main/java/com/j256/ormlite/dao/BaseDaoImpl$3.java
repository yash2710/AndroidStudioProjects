// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.j256.ormlite.dao;

import com.j256.ormlite.stmt.PreparedQuery;
import java.util.Iterator;

// Referenced classes of package com.j256.ormlite.dao:
//            CloseableIterable, BaseDaoImpl, CloseableIterator

class 
    implements CloseableIterable
{

    final BaseDaoImpl this$0;
    final PreparedQuery val$preparedQuery;

    public CloseableIterator closeableIterator()
    {
        CloseableIterator closeableiterator;
        try
        {
            closeableiterator = BaseDaoImpl.access$100(BaseDaoImpl.this, val$preparedQuery, -1);
        }
        catch (Exception exception)
        {
            throw new IllegalStateException((new StringBuilder("Could not build prepared-query iterator for ")).append(dataClass).toString(), exception);
        }
        return closeableiterator;
    }

    public Iterator iterator()
    {
        return closeableIterator();
    }

    ()
    {
        this$0 = final_basedaoimpl;
        val$preparedQuery = PreparedQuery.this;
        super();
    }
}
