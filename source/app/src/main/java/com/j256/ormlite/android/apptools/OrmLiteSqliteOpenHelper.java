// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.j256.ormlite.android.apptools;

import android.content.Context;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.j256.ormlite.android.AndroidConnectionSource;
import com.j256.ormlite.android.AndroidDatabaseConnection;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.logger.Logger;
import com.j256.ormlite.logger.LoggerFactory;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.DatabaseTableConfigLoader;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.SQLException;

public abstract class OrmLiteSqliteOpenHelper extends SQLiteOpenHelper
{

    protected static Logger logger = LoggerFactory.getLogger(com/j256/ormlite/android/apptools/OrmLiteSqliteOpenHelper);
    protected boolean cancelQueriesEnabled;
    protected AndroidConnectionSource connectionSource;
    private volatile boolean isOpen;

    public OrmLiteSqliteOpenHelper(Context context, String s, android.database.sqlite.SQLiteDatabase.CursorFactory cursorfactory, int i)
    {
        super(context, s, cursorfactory, i);
        connectionSource = new AndroidConnectionSource(this);
        isOpen = true;
        logger.trace("{}: constructed connectionSource {}", this, connectionSource);
    }

    public OrmLiteSqliteOpenHelper(Context context, String s, android.database.sqlite.SQLiteDatabase.CursorFactory cursorfactory, int i, int j)
    {
        this(context, s, cursorfactory, i, openFileId(context, j));
    }

    public OrmLiteSqliteOpenHelper(Context context, String s, android.database.sqlite.SQLiteDatabase.CursorFactory cursorfactory, int i, File file)
    {
        this(context, s, cursorfactory, i, openFile(file));
    }

    public OrmLiteSqliteOpenHelper(Context context, String s, android.database.sqlite.SQLiteDatabase.CursorFactory cursorfactory, int i, InputStream inputstream)
    {
        super(context, s, cursorfactory, i);
        connectionSource = new AndroidConnectionSource(this);
        isOpen = true;
        if (inputstream == null)
        {
            return;
        }
        DaoManager.addCachedDatabaseConfigs(DatabaseTableConfigLoader.loadDatabaseConfigFromReader(new BufferedReader(new InputStreamReader(inputstream), 4096)));
        try
        {
            inputstream.close();
            return;
        }
        catch (IOException ioexception1)
        {
            return;
        }
        SQLException sqlexception;
        sqlexception;
        throw new IllegalStateException("Could not load object config file", sqlexception);
        Exception exception;
        exception;
        try
        {
            inputstream.close();
        }
        catch (IOException ioexception) { }
        throw exception;
    }

    private static InputStream openFile(File file)
    {
        if (file == null)
        {
            return null;
        }
        FileInputStream fileinputstream;
        try
        {
            fileinputstream = new FileInputStream(file);
        }
        catch (FileNotFoundException filenotfoundexception)
        {
            throw new IllegalArgumentException((new StringBuilder("Could not open config file ")).append(file).toString(), filenotfoundexception);
        }
        return fileinputstream;
    }

    private static InputStream openFileId(Context context, int i)
    {
        InputStream inputstream = context.getResources().openRawResource(i);
        if (inputstream == null)
        {
            throw new IllegalStateException((new StringBuilder("Could not find object config file with id ")).append(i).toString());
        } else
        {
            return inputstream;
        }
    }

    public void close()
    {
        super.close();
        connectionSource.close();
        isOpen = false;
    }

    public ConnectionSource getConnectionSource()
    {
        if (!isOpen)
        {
            logger.warn(new IllegalStateException(), "Getting connectionSource was called after closed");
        }
        return connectionSource;
    }

    public Dao getDao(Class class1)
    {
        return DaoManager.createDao(getConnectionSource(), class1);
    }

    public RuntimeExceptionDao getRuntimeExceptionDao(Class class1)
    {
        RuntimeExceptionDao runtimeexceptiondao;
        try
        {
            runtimeexceptiondao = new RuntimeExceptionDao(getDao(class1));
        }
        catch (SQLException sqlexception)
        {
            throw new RuntimeException((new StringBuilder("Could not create RuntimeExcepitionDao for class ")).append(class1).toString(), sqlexception);
        }
        return runtimeexceptiondao;
    }

    public boolean isOpen()
    {
        return isOpen;
    }

    public final void onCreate(SQLiteDatabase sqlitedatabase)
    {
        boolean flag = true;
        ConnectionSource connectionsource = getConnectionSource();
        com.j256.ormlite.support.DatabaseConnection databaseconnection = connectionsource.getSpecialConnection();
        Object obj;
        Exception exception;
        if (databaseconnection == null)
        {
            obj = new AndroidDatabaseConnection(sqlitedatabase, flag, cancelQueriesEnabled);
            try
            {
                connectionsource.saveSpecialConnection(((com.j256.ormlite.support.DatabaseConnection) (obj)));
            }
            catch (SQLException sqlexception)
            {
                throw new IllegalStateException("Could not save special connection", sqlexception);
            }
        } else
        {
            obj = databaseconnection;
            flag = false;
        }
        onCreate(sqlitedatabase, connectionsource);
        if (flag)
        {
            connectionsource.clearSpecialConnection(((com.j256.ormlite.support.DatabaseConnection) (obj)));
        }
        return;
        exception;
        if (flag)
        {
            connectionsource.clearSpecialConnection(((com.j256.ormlite.support.DatabaseConnection) (obj)));
        }
        throw exception;
    }

    public abstract void onCreate(SQLiteDatabase sqlitedatabase, ConnectionSource connectionsource);

    public final void onUpgrade(SQLiteDatabase sqlitedatabase, int i, int j)
    {
        boolean flag = true;
        ConnectionSource connectionsource = getConnectionSource();
        com.j256.ormlite.support.DatabaseConnection databaseconnection = connectionsource.getSpecialConnection();
        Object obj;
        Exception exception;
        if (databaseconnection == null)
        {
            obj = new AndroidDatabaseConnection(sqlitedatabase, flag, cancelQueriesEnabled);
            try
            {
                connectionsource.saveSpecialConnection(((com.j256.ormlite.support.DatabaseConnection) (obj)));
            }
            catch (SQLException sqlexception)
            {
                throw new IllegalStateException("Could not save special connection", sqlexception);
            }
        } else
        {
            obj = databaseconnection;
            flag = false;
        }
        onUpgrade(sqlitedatabase, connectionsource, i, j);
        if (flag)
        {
            connectionsource.clearSpecialConnection(((com.j256.ormlite.support.DatabaseConnection) (obj)));
        }
        return;
        exception;
        if (flag)
        {
            connectionsource.clearSpecialConnection(((com.j256.ormlite.support.DatabaseConnection) (obj)));
        }
        throw exception;
    }

    public abstract void onUpgrade(SQLiteDatabase sqlitedatabase, ConnectionSource connectionsource, int i, int j);

    public String toString()
    {
        return (new StringBuilder()).append(getClass().getSimpleName()).append("@").append(Integer.toHexString(super.hashCode())).toString();
    }

}
