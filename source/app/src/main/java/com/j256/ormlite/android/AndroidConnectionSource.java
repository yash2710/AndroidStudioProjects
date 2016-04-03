// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.j256.ormlite.android;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.j256.ormlite.db.DatabaseType;
import com.j256.ormlite.db.SqliteAndroidDatabaseType;
import com.j256.ormlite.logger.Logger;
import com.j256.ormlite.logger.LoggerFactory;
import com.j256.ormlite.misc.SqlExceptionUtil;
import com.j256.ormlite.support.BaseConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.support.DatabaseConnection;
import com.j256.ormlite.support.DatabaseConnectionProxyFactory;

// Referenced classes of package com.j256.ormlite.android:
//            AndroidDatabaseConnection

public class AndroidConnectionSource extends BaseConnectionSource
    implements ConnectionSource
{

    private static DatabaseConnectionProxyFactory connectionProxyFactory;
    private static final Logger logger = LoggerFactory.getLogger(com/j256/ormlite/android/AndroidConnectionSource);
    private boolean cancelQueriesEnabled;
    private DatabaseConnection connection;
    private final DatabaseType databaseType;
    private final SQLiteOpenHelper helper;
    private volatile boolean isOpen;
    private final SQLiteDatabase sqliteDatabase;

    public AndroidConnectionSource(SQLiteDatabase sqlitedatabase)
    {
        connection = null;
        isOpen = true;
        databaseType = new SqliteAndroidDatabaseType();
        cancelQueriesEnabled = false;
        helper = null;
        sqliteDatabase = sqlitedatabase;
    }

    public AndroidConnectionSource(SQLiteOpenHelper sqliteopenhelper)
    {
        connection = null;
        isOpen = true;
        databaseType = new SqliteAndroidDatabaseType();
        cancelQueriesEnabled = false;
        helper = sqliteopenhelper;
        sqliteDatabase = null;
    }

    public static void setDatabaseConnectionProxyFactory(DatabaseConnectionProxyFactory databaseconnectionproxyfactory)
    {
        connectionProxyFactory = databaseconnectionproxyfactory;
    }

    public void clearSpecialConnection(DatabaseConnection databaseconnection)
    {
        clearSpecial(databaseconnection, logger);
    }

    public void close()
    {
        isOpen = false;
    }

    public void closeQuietly()
    {
        close();
    }

    public DatabaseType getDatabaseType()
    {
        return databaseType;
    }

    public DatabaseConnection getReadOnlyConnection()
    {
        return getReadWriteConnection();
    }

    public DatabaseConnection getReadWriteConnection()
    {
        DatabaseConnection databaseconnection = getSavedConnection();
        if (databaseconnection != null)
        {
            return databaseconnection;
        }
        if (connection == null)
        {
            SQLiteDatabase sqlitedatabase;
            if (sqliteDatabase == null)
            {
                SQLiteDatabase sqlitedatabase1;
                try
                {
                    sqlitedatabase1 = helper.getWritableDatabase();
                }
                catch (SQLException sqlexception)
                {
                    throw SqlExceptionUtil.create((new StringBuilder("Getting a writable database from helper ")).append(helper).append(" failed").toString(), sqlexception);
                }
                sqlitedatabase = sqlitedatabase1;
            } else
            {
                sqlitedatabase = sqliteDatabase;
            }
            connection = new AndroidDatabaseConnection(sqlitedatabase, true, cancelQueriesEnabled);
            if (connectionProxyFactory != null)
            {
                connection = connectionProxyFactory.createProxy(connection);
            }
            logger.trace("created connection {} for db {}, helper {}", connection, sqlitedatabase, helper);
        } else
        {
            logger.trace("{}: returning read-write connection {}, helper {}", this, connection, helper);
        }
        return connection;
    }

    public boolean isCancelQueriesEnabled()
    {
        return cancelQueriesEnabled;
    }

    public boolean isOpen()
    {
        return isOpen;
    }

    public void releaseConnection(DatabaseConnection databaseconnection)
    {
    }

    public boolean saveSpecialConnection(DatabaseConnection databaseconnection)
    {
        return saveSpecial(databaseconnection);
    }

    public void setCancelQueriesEnabled(boolean flag)
    {
        cancelQueriesEnabled = flag;
    }

    public String toString()
    {
        return (new StringBuilder()).append(getClass().getSimpleName()).append("@").append(Integer.toHexString(super.hashCode())).toString();
    }

}
