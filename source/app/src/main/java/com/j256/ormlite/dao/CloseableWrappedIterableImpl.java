// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.j256.ormlite.dao;

import java.sql.SQLException;
import java.util.Iterator;

// Referenced classes of package com.j256.ormlite.dao:
//            CloseableWrappedIterable, CloseableIterator, CloseableIterable

public class CloseableWrappedIterableImpl
    implements CloseableWrappedIterable
{

    private final CloseableIterable iterable;
    private CloseableIterator iterator;

    public CloseableWrappedIterableImpl(CloseableIterable closeableiterable)
    {
        iterable = closeableiterable;
    }

    public void close()
    {
        if (iterator != null)
        {
            iterator.close();
            iterator = null;
        }
    }

    public CloseableIterator closeableIterator()
    {
        try
        {
            close();
        }
        catch (SQLException sqlexception) { }
        iterator = iterable.closeableIterator();
        return iterator;
    }

    public CloseableIterator iterator()
    {
        return closeableIterator();
    }

    public volatile Iterator iterator()
    {
        return iterator();
    }
}
