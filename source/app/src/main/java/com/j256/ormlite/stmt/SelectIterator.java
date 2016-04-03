// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.j256.ormlite.stmt;

import com.j256.ormlite.dao.CloseableIterator;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.ObjectCache;
import com.j256.ormlite.logger.Logger;
import com.j256.ormlite.logger.LoggerFactory;
import com.j256.ormlite.support.CompiledStatement;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.support.DatabaseConnection;
import com.j256.ormlite.support.DatabaseResults;
import java.sql.SQLException;

// Referenced classes of package com.j256.ormlite.stmt:
//            GenericRowMapper

public class SelectIterator
    implements CloseableIterator
{

    private static final Logger logger = LoggerFactory.getLogger(com/j256/ormlite/stmt/SelectIterator);
    private boolean alreadyMoved;
    private final Dao classDao;
    private boolean closed;
    private final CompiledStatement compiledStmt;
    private final DatabaseConnection connection;
    private final ConnectionSource connectionSource;
    private final Class dataClass;
    private boolean first;
    private Object last;
    private final DatabaseResults results;
    private int rowC;
    private final GenericRowMapper rowMapper;
    private final String statement;

    public SelectIterator(Class class1, Dao dao, GenericRowMapper genericrowmapper, ConnectionSource connectionsource, DatabaseConnection databaseconnection, CompiledStatement compiledstatement, String s, 
            ObjectCache objectcache)
    {
        first = true;
        dataClass = class1;
        classDao = dao;
        rowMapper = genericrowmapper;
        connectionSource = connectionsource;
        connection = databaseconnection;
        compiledStmt = compiledstatement;
        results = compiledstatement.runQuery(objectcache);
        statement = s;
        if (s != null)
        {
            logger.debug("starting iterator @{} for '{}'", Integer.valueOf(hashCode()), s);
        }
    }

    private Object getCurrent()
    {
        last = rowMapper.mapRow(results);
        alreadyMoved = false;
        rowC = 1 + rowC;
        return last;
    }

    public void close()
    {
        if (!closed)
        {
            compiledStmt.close();
            closed = true;
            last = null;
            if (statement != null)
            {
                logger.debug("closed iterator @{} after {} rows", Integer.valueOf(hashCode()), Integer.valueOf(rowC));
            }
            connectionSource.releaseConnection(connection);
        }
    }

    public void closeQuietly()
    {
        try
        {
            close();
            return;
        }
        catch (SQLException sqlexception)
        {
            return;
        }
    }

    public Object current()
    {
        if (closed)
        {
            return null;
        }
        if (first)
        {
            return first();
        } else
        {
            return getCurrent();
        }
    }

    public Object first()
    {
        if (!closed)
        {
            first = false;
            if (results.first())
            {
                return getCurrent();
            }
        }
        return null;
    }

    public DatabaseResults getRawResults()
    {
        return results;
    }

    public boolean hasNext()
    {
        boolean flag;
        try
        {
            flag = hasNextThrow();
        }
        catch (SQLException sqlexception)
        {
            last = null;
            closeQuietly();
            throw new IllegalStateException((new StringBuilder("Errors getting more results of ")).append(dataClass).toString(), sqlexception);
        }
        return flag;
    }

    public boolean hasNextThrow()
    {
        if (closed)
        {
            return false;
        }
        if (alreadyMoved)
        {
            return true;
        }
        boolean flag;
        if (first)
        {
            first = false;
            flag = results.first();
        } else
        {
            flag = results.next();
        }
        if (!flag)
        {
            close();
        }
        alreadyMoved = true;
        return flag;
    }

    public Object moveRelative(int i)
    {
        if (!closed)
        {
            first = false;
            if (results.moveRelative(i))
            {
                return getCurrent();
            }
        }
        return null;
    }

    public void moveToNext()
    {
        last = null;
        first = false;
        alreadyMoved = false;
    }

    public Object next()
    {
        Object obj1 = nextThrow();
        Object obj;
        if (obj1 != null)
        {
            return obj1;
        }
        obj = null;
_L2:
        last = null;
        closeQuietly();
        throw new IllegalStateException((new StringBuilder("Could not get next result for ")).append(dataClass).toString(), ((Throwable) (obj)));
        obj;
        if (true) goto _L2; else goto _L1
_L1:
    }

    public Object nextThrow()
    {
        if (closed)
        {
            return null;
        }
        if (!alreadyMoved)
        {
            boolean flag;
            if (first)
            {
                first = false;
                flag = results.first();
            } else
            {
                flag = results.next();
            }
            if (!flag)
            {
                first = false;
                return null;
            }
        }
        first = false;
        return getCurrent();
    }

    public Object previous()
    {
        if (!closed)
        {
            first = false;
            if (results.previous())
            {
                return getCurrent();
            }
        }
        return null;
    }

    public void remove()
    {
        try
        {
            removeThrow();
            return;
        }
        catch (SQLException sqlexception)
        {
            closeQuietly();
            throw new IllegalStateException((new StringBuilder("Could not delete ")).append(dataClass).append(" object ").append(last).toString(), sqlexception);
        }
    }

    public void removeThrow()
    {
        if (last == null)
        {
            throw new IllegalStateException((new StringBuilder("No last ")).append(dataClass).append(" object to remove. Must be called after a call to next.").toString());
        }
        if (classDao == null)
        {
            throw new IllegalStateException((new StringBuilder("Cannot remove ")).append(dataClass).append(" object because classDao not initialized").toString());
        }
        classDao.delete(last);
        last = null;
        return;
        Exception exception;
        exception;
        last = null;
        throw exception;
    }

}
