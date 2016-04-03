// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.j256.ormlite.android;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import com.j256.ormlite.android.compat.ApiCompatibility;
import com.j256.ormlite.android.compat.ApiCompatibilityUtils;
import com.j256.ormlite.dao.ObjectCache;
import com.j256.ormlite.field.SqlType;
import com.j256.ormlite.logger.Logger;
import com.j256.ormlite.logger.LoggerFactory;
import com.j256.ormlite.misc.SqlExceptionUtil;
import com.j256.ormlite.support.CompiledStatement;
import com.j256.ormlite.support.DatabaseResults;
import com.newrelic.agent.android.instrumentation.SQLiteInstrumentation;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package com.j256.ormlite.android:
//            AndroidDatabaseResults

public class AndroidCompiledStatement
    implements CompiledStatement
{

    private static final String NO_STRING_ARGS[] = new String[0];
    private static final ApiCompatibility apiCompatibility = ApiCompatibilityUtils.getCompatibility();
    private static Logger logger = LoggerFactory.getLogger(com/j256/ormlite/android/AndroidCompiledStatement);
    private List args;
    private final boolean cancelQueriesEnabled;
    private com.j256.ormlite.android.compat.ApiCompatibility.CancellationHook cancellationHook;
    private Cursor cursor;
    private final SQLiteDatabase db;
    private Integer max;
    private final String sql;
    private final com.j256.ormlite.stmt.StatementBuilder.StatementType type;

    public AndroidCompiledStatement(String s, SQLiteDatabase sqlitedatabase, com.j256.ormlite.stmt.StatementBuilder.StatementType statementtype, boolean flag)
    {
        sql = s;
        db = sqlitedatabase;
        type = statementtype;
        cancelQueriesEnabled = flag;
    }

    static int execSql(SQLiteDatabase sqlitedatabase, String s, String s1, Object aobj[])
    {
        if (sqlitedatabase instanceof SQLiteDatabase) goto _L2; else goto _L1
_L1:
        sqlitedatabase.execSQL(s1, aobj);
_L3:
        SQLiteStatement sqlitestatement1 = sqlitedatabase.compileStatement("SELECT CHANGES()");
        SQLiteStatement sqlitestatement = sqlitestatement1;
        long l = sqlitestatement.simpleQueryForLong();
        int i;
        i = (int)l;
        if (sqlitestatement != null)
        {
            sqlitestatement.close();
        }
_L4:
        logger.trace("executing statement {} changed {} rows: {}", s, Integer.valueOf(i), s1);
        return i;
_L2:
        try
        {
            SQLiteInstrumentation.execSQL((SQLiteDatabase)sqlitedatabase, s1, aobj);
        }
        catch (SQLException sqlexception)
        {
            throw SqlExceptionUtil.create((new StringBuilder("Problems executing ")).append(s).append(" Android statement: ").append(s1).toString(), sqlexception);
        }
          goto _L3
        SQLException sqlexception1;
        sqlexception1;
        sqlitestatement = null;
_L7:
        i = 1;
        if (sqlitestatement != null)
        {
            sqlitestatement.close();
        }
          goto _L4
        Exception exception;
        exception;
        Exception exception1;
        sqlitestatement = null;
        exception1 = exception;
_L6:
        if (sqlitestatement != null)
        {
            sqlitestatement.close();
        }
        throw exception1;
        exception1;
        if (true) goto _L6; else goto _L5
_L5:
        SQLException sqlexception2;
        sqlexception2;
          goto _L7
    }

    private Object[] getArgArray()
    {
        if (args == null)
        {
            return NO_STRING_ARGS;
        } else
        {
            return args.toArray(new Object[args.size()]);
        }
    }

    private String[] getStringArray()
    {
        if (args == null)
        {
            return NO_STRING_ARGS;
        } else
        {
            return (String[])args.toArray(new String[args.size()]);
        }
    }

    private void isInPrep()
    {
        if (cursor != null)
        {
            throw new java.sql.SQLException("Query already run. Cannot add argument values.");
        } else
        {
            return;
        }
    }

    public void cancel()
    {
        if (cancellationHook != null)
        {
            cancellationHook.cancel();
        }
    }

    public void close()
    {
        if (cursor != null)
        {
            try
            {
                cursor.close();
            }
            catch (SQLException sqlexception)
            {
                throw SqlExceptionUtil.create("Problems closing Android cursor", sqlexception);
            }
        }
        cancellationHook = null;
    }

    public void closeQuietly()
    {
        try
        {
            close();
            return;
        }
        catch (java.sql.SQLException sqlexception)
        {
            return;
        }
    }

    public int getColumnCount()
    {
        return getCursor().getColumnCount();
    }

    public String getColumnName(int i)
    {
        return getCursor().getColumnName(i);
    }

    public Cursor getCursor()
    {
        if (cursor != null) goto _L2; else goto _L1
_L1:
        String s = null;
        Integer integer;
        String s1;
        try
        {
            integer = max;
        }
        catch (SQLException sqlexception)
        {
            throw SqlExceptionUtil.create((new StringBuilder("Problems executing Android query: ")).append(s).toString(), sqlexception);
        }
        s = null;
        if (integer != null) goto _L4; else goto _L3
_L3:
        s = sql;
_L5:
        if (cancelQueriesEnabled)
        {
            cancellationHook = apiCompatibility.createCancellationHook();
        }
        cursor = apiCompatibility.rawQuery(db, s, getStringArray(), cancellationHook);
        cursor.moveToFirst();
        logger.trace("{}: started rawQuery cursor for: {}", this, s);
_L2:
        return cursor;
_L4:
        s1 = (new StringBuilder()).append(sql).append(" ").append(max).toString();
        s = s1;
          goto _L5
    }

    public int runExecute()
    {
        if (!type.isOkForExecute())
        {
            throw new IllegalArgumentException((new StringBuilder("Cannot call execute on a ")).append(type).append(" statement").toString());
        } else
        {
            return execSql(db, "runExecute", sql, getArgArray());
        }
    }

    public DatabaseResults runQuery(ObjectCache objectcache)
    {
        if (!type.isOkForQuery())
        {
            throw new IllegalArgumentException((new StringBuilder("Cannot call query on a ")).append(type).append(" statement").toString());
        } else
        {
            return new AndroidDatabaseResults(getCursor(), objectcache);
        }
    }

    public int runUpdate()
    {
        if (!type.isOkForUpdate())
        {
            throw new IllegalArgumentException((new StringBuilder("Cannot call update on a ")).append(type).append(" statement").toString());
        }
        String s;
        if (max == null)
        {
            s = sql;
        } else
        {
            s = (new StringBuilder()).append(sql).append(" ").append(max).toString();
        }
        return execSql(db, "runUpdate", s, getArgArray());
    }

    public void setMaxRows(int i)
    {
        isInPrep();
        max = Integer.valueOf(i);
    }

    public void setObject(int i, Object obj, SqlType sqltype)
    {
        isInPrep();
        if (args == null)
        {
            args = new ArrayList();
        }
        if (obj == null)
        {
            args.add(i, null);
            return;
        }
        switch (_cls1..SwitchMap.com.j256.ormlite.field.SqlType[sqltype.ordinal()])
        {
        default:
            throw new java.sql.SQLException((new StringBuilder("Unknown sql argument type: ")).append(sqltype).toString());

        case 1: // '\001'
        case 2: // '\002'
        case 3: // '\003'
        case 4: // '\004'
        case 5: // '\005'
        case 6: // '\006'
        case 7: // '\007'
        case 8: // '\b'
        case 9: // '\t'
        case 10: // '\n'
        case 11: // '\013'
            args.add(i, obj.toString());
            return;

        case 12: // '\f'
        case 13: // '\r'
            args.add(i, obj);
            return;

        case 14: // '\016'
        case 15: // '\017'
            throw new java.sql.SQLException((new StringBuilder("Invalid Android type: ")).append(sqltype).toString());
        }
    }

    public void setQueryTimeout(long l)
    {
    }

    public String toString()
    {
        return (new StringBuilder()).append(getClass().getSimpleName()).append("@").append(Integer.toHexString(super.hashCode())).toString();
    }


    private class _cls1
    {

        static final int $SwitchMap$com$j256$ormlite$field$SqlType[];

        static 
        {
            $SwitchMap$com$j256$ormlite$field$SqlType = new int[SqlType.values().length];
            try
            {
                $SwitchMap$com$j256$ormlite$field$SqlType[SqlType.STRING.ordinal()] = 1;
            }
            catch (NoSuchFieldError nosuchfielderror) { }
            try
            {
                $SwitchMap$com$j256$ormlite$field$SqlType[SqlType.LONG_STRING.ordinal()] = 2;
            }
            catch (NoSuchFieldError nosuchfielderror1) { }
            try
            {
                $SwitchMap$com$j256$ormlite$field$SqlType[SqlType.DATE.ordinal()] = 3;
            }
            catch (NoSuchFieldError nosuchfielderror2) { }
            try
            {
                $SwitchMap$com$j256$ormlite$field$SqlType[SqlType.BOOLEAN.ordinal()] = 4;
            }
            catch (NoSuchFieldError nosuchfielderror3) { }
            try
            {
                $SwitchMap$com$j256$ormlite$field$SqlType[SqlType.CHAR.ordinal()] = 5;
            }
            catch (NoSuchFieldError nosuchfielderror4) { }
            try
            {
                $SwitchMap$com$j256$ormlite$field$SqlType[SqlType.BYTE.ordinal()] = 6;
            }
            catch (NoSuchFieldError nosuchfielderror5) { }
            try
            {
                $SwitchMap$com$j256$ormlite$field$SqlType[SqlType.SHORT.ordinal()] = 7;
            }
            catch (NoSuchFieldError nosuchfielderror6) { }
            try
            {
                $SwitchMap$com$j256$ormlite$field$SqlType[SqlType.INTEGER.ordinal()] = 8;
            }
            catch (NoSuchFieldError nosuchfielderror7) { }
            try
            {
                $SwitchMap$com$j256$ormlite$field$SqlType[SqlType.LONG.ordinal()] = 9;
            }
            catch (NoSuchFieldError nosuchfielderror8) { }
            try
            {
                $SwitchMap$com$j256$ormlite$field$SqlType[SqlType.FLOAT.ordinal()] = 10;
            }
            catch (NoSuchFieldError nosuchfielderror9) { }
            try
            {
                $SwitchMap$com$j256$ormlite$field$SqlType[SqlType.DOUBLE.ordinal()] = 11;
            }
            catch (NoSuchFieldError nosuchfielderror10) { }
            try
            {
                $SwitchMap$com$j256$ormlite$field$SqlType[SqlType.BYTE_ARRAY.ordinal()] = 12;
            }
            catch (NoSuchFieldError nosuchfielderror11) { }
            try
            {
                $SwitchMap$com$j256$ormlite$field$SqlType[SqlType.SERIALIZABLE.ordinal()] = 13;
            }
            catch (NoSuchFieldError nosuchfielderror12) { }
            try
            {
                $SwitchMap$com$j256$ormlite$field$SqlType[SqlType.BLOB.ordinal()] = 14;
            }
            catch (NoSuchFieldError nosuchfielderror13) { }
            try
            {
                $SwitchMap$com$j256$ormlite$field$SqlType[SqlType.BIG_DECIMAL.ordinal()] = 15;
            }
            catch (NoSuchFieldError nosuchfielderror14) { }
            try
            {
                $SwitchMap$com$j256$ormlite$field$SqlType[SqlType.UNKNOWN.ordinal()] = 16;
            }
            catch (NoSuchFieldError nosuchfielderror15)
            {
                return;
            }
        }
    }

}
