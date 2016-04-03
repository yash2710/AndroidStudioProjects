// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.j256.ormlite.stmt;

import com.j256.ormlite.dao.CloseableIterator;
import com.j256.ormlite.dao.GenericRawResults;
import com.j256.ormlite.dao.ObjectCache;
import com.j256.ormlite.support.CompiledStatement;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.support.DatabaseConnection;
import com.j256.ormlite.support.DatabaseResults;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

// Referenced classes of package com.j256.ormlite.stmt:
//            SelectIterator, GenericRowMapper

public class RawResultsImpl
    implements GenericRawResults
{

    private final String columnNames[];
    private SelectIterator iterator;

    public RawResultsImpl(ConnectionSource connectionsource, DatabaseConnection databaseconnection, String s, Class class1, CompiledStatement compiledstatement, GenericRowMapper genericrowmapper, ObjectCache objectcache)
    {
        iterator = new SelectIterator(class1, null, genericrowmapper, connectionsource, databaseconnection, compiledstatement, s, objectcache);
        columnNames = iterator.getRawResults().getColumnNames();
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
        return iterator;
    }

    public String[] getColumnNames()
    {
        return columnNames;
    }

    public Object getFirstResult()
    {
        Object obj;
        if (!iterator.hasNextThrow())
        {
            break MISSING_BLOCK_LABEL_24;
        }
        obj = iterator.nextThrow();
        close();
        return obj;
        close();
        return null;
        Exception exception;
        exception;
        close();
        throw exception;
    }

    public int getNumberColumns()
    {
        return columnNames.length;
    }

    public List getResults()
    {
        ArrayList arraylist = new ArrayList();
        for (; iterator.hasNext(); arraylist.add(iterator.next())) { }
        break MISSING_BLOCK_LABEL_45;
        Exception exception;
        exception;
        iterator.close();
        throw exception;
        iterator.close();
        return arraylist;
    }

    public CloseableIterator iterator()
    {
        return iterator;
    }

    public volatile Iterator iterator()
    {
        return iterator();
    }
}
