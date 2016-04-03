// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.j256.ormlite.misc;

import com.j256.ormlite.db.DatabaseType;
import com.j256.ormlite.logger.Logger;
import com.j256.ormlite.logger.LoggerFactory;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.support.DatabaseConnection;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;

// Referenced classes of package com.j256.ormlite.misc:
//            SqlExceptionUtil

public class TransactionManager
{

    private static final String SAVE_POINT_PREFIX = "ORMLITE";
    private static final Logger logger = LoggerFactory.getLogger(com/j256/ormlite/misc/TransactionManager);
    private static AtomicInteger savePointCounter = new AtomicInteger();
    private ConnectionSource connectionSource;

    public TransactionManager()
    {
    }

    public TransactionManager(ConnectionSource connectionsource)
    {
        connectionSource = connectionsource;
        initialize();
    }

    public static Object callInTransaction(ConnectionSource connectionsource, Callable callable)
    {
        DatabaseConnection databaseconnection = connectionsource.getReadWriteConnection();
        Object obj = callInTransaction(databaseconnection, connectionsource.saveSpecialConnection(databaseconnection), connectionsource.getDatabaseType(), callable);
        connectionsource.clearSpecialConnection(databaseconnection);
        connectionsource.releaseConnection(databaseconnection);
        return obj;
        Exception exception;
        exception;
        connectionsource.clearSpecialConnection(databaseconnection);
        connectionsource.releaseConnection(databaseconnection);
        throw exception;
    }

    public static Object callInTransaction(DatabaseConnection databaseconnection, DatabaseType databasetype, Callable callable)
    {
        return callInTransaction(databaseconnection, false, databasetype, callable);
    }

    public static Object callInTransaction(DatabaseConnection databaseconnection, boolean flag, DatabaseType databasetype, Callable callable)
    {
        boolean flag1;
        flag1 = false;
        if (flag)
        {
            break MISSING_BLOCK_LABEL_16;
        }
        if (!databasetype.isNestedSavePointsSupported())
        {
            break MISSING_BLOCK_LABEL_261;
        }
        boolean flag2 = databaseconnection.isAutoCommitSupported();
        flag1 = false;
        if (!flag2)
        {
            break MISSING_BLOCK_LABEL_60;
        }
        flag1 = databaseconnection.isAutoCommit();
        if (!flag1)
        {
            break MISSING_BLOCK_LABEL_60;
        }
        databaseconnection.setAutoCommit(false);
        logger.debug("had to set auto-commit to false");
        Savepoint savepoint = databaseconnection.setSavePoint((new StringBuilder("ORMLITE")).append(savePointCounter.incrementAndGet()).toString());
        if (savepoint != null) goto _L2; else goto _L1
_L1:
        logger.debug("started savePoint transaction");
_L3:
        Savepoint savepoint1;
        boolean flag3;
        savepoint1 = savepoint;
        flag3 = true;
_L6:
        Object obj = callable.call();
        if (!flag3)
        {
            break MISSING_BLOCK_LABEL_128;
        }
        commit(databaseconnection, savepoint1);
        if (flag1)
        {
            databaseconnection.setAutoCommit(true);
            logger.debug("restored auto-commit to true");
        }
        return obj;
_L2:
        logger.debug("started savePoint transaction {}", savepoint.getSavepointName());
          goto _L3
        Exception exception;
        exception;
        if (flag1)
        {
            databaseconnection.setAutoCommit(true);
            logger.debug("restored auto-commit to true");
        }
        throw exception;
        SQLException sqlexception1;
        sqlexception1;
        if (!flag3)
        {
            break MISSING_BLOCK_LABEL_207;
        }
        rollBack(databaseconnection, savepoint1);
_L4:
        throw sqlexception1;
        SQLException sqlexception2;
        sqlexception2;
        logger.error(sqlexception1, "after commit exception, rolling back to save-point also threw exception");
          goto _L4
        Exception exception1;
        exception1;
        if (!flag3)
        {
            break MISSING_BLOCK_LABEL_238;
        }
        rollBack(databaseconnection, savepoint1);
_L5:
        throw SqlExceptionUtil.create("Transaction callable threw non-SQL exception", exception1);
        SQLException sqlexception;
        sqlexception;
        logger.error(exception1, "after commit exception, rolling back to save-point also threw exception");
          goto _L5
        flag1 = false;
        savepoint1 = null;
        flag3 = false;
          goto _L6
    }

    private static void commit(DatabaseConnection databaseconnection, Savepoint savepoint)
    {
        String s;
        if (savepoint == null)
        {
            s = null;
        } else
        {
            s = savepoint.getSavepointName();
        }
        databaseconnection.commit(savepoint);
        if (s == null)
        {
            logger.debug("committed savePoint transaction");
            return;
        } else
        {
            logger.debug("committed savePoint transaction {}", s);
            return;
        }
    }

    private static void rollBack(DatabaseConnection databaseconnection, Savepoint savepoint)
    {
        String s;
        if (savepoint == null)
        {
            s = null;
        } else
        {
            s = savepoint.getSavepointName();
        }
        databaseconnection.rollback(savepoint);
        if (s == null)
        {
            logger.debug("rolled back savePoint transaction");
            return;
        } else
        {
            logger.debug("rolled back savePoint transaction {}", s);
            return;
        }
    }

    public Object callInTransaction(Callable callable)
    {
        return callInTransaction(connectionSource, callable);
    }

    public void initialize()
    {
        if (connectionSource == null)
        {
            throw new IllegalStateException((new StringBuilder("dataSource was not set on ")).append(getClass().getSimpleName()).toString());
        } else
        {
            return;
        }
    }

    public void setConnectionSource(ConnectionSource connectionsource)
    {
        connectionSource = connectionsource;
    }

}
