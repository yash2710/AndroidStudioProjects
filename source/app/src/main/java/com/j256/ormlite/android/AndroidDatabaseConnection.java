// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.j256.ormlite.android;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import com.j256.ormlite.dao.ObjectCache;
import com.j256.ormlite.field.FieldType;
import com.j256.ormlite.field.SqlType;
import com.j256.ormlite.logger.Logger;
import com.j256.ormlite.logger.LoggerFactory;
import com.j256.ormlite.misc.SqlExceptionUtil;
import com.j256.ormlite.misc.VersionUtils;
import com.j256.ormlite.stmt.GenericRowMapper;
import com.j256.ormlite.support.CompiledStatement;
import com.j256.ormlite.support.DatabaseConnection;
import com.j256.ormlite.support.GeneratedKeyHolder;
import com.newrelic.agent.android.instrumentation.SQLiteInstrumentation;
import java.sql.SQLException;
import java.sql.Savepoint;

// Referenced classes of package com.j256.ormlite.android:
//            AndroidCompiledStatement, AndroidDatabaseResults

public class AndroidDatabaseConnection
    implements DatabaseConnection
{

    private static final String ANDROID_VERSION = "VERSION__4.48__";
    private static final String NO_STRING_ARGS[] = new String[0];
    private static Logger logger = LoggerFactory.getLogger(com/j256/ormlite/android/AndroidDatabaseConnection);
    private final boolean cancelQueriesEnabled;
    private final SQLiteDatabase db;
    private final boolean readWrite;

    public AndroidDatabaseConnection(SQLiteDatabase sqlitedatabase, boolean flag)
    {
        this(sqlitedatabase, flag, false);
    }

    public AndroidDatabaseConnection(SQLiteDatabase sqlitedatabase, boolean flag, boolean flag1)
    {
        db = sqlitedatabase;
        readWrite = flag;
        cancelQueriesEnabled = flag1;
        logger.trace("{}: db {} opened, read-write = {}", this, sqlitedatabase, Boolean.valueOf(flag));
    }

    private void bindArgs(SQLiteStatement sqlitestatement, Object aobj[], FieldType afieldtype[])
    {
        if (aobj != null) goto _L2; else goto _L1
_L1:
        return;
_L2:
        int i = 0;
_L6:
        if (i >= aobj.length) goto _L1; else goto _L3
_L3:
        Object obj = aobj[i];
        if (obj != null) goto _L5; else goto _L4
_L4:
        sqlitestatement.bindNull(i + 1);
_L8:
        i++;
          goto _L6
_L5:
        SqlType sqltype = afieldtype[i].getSqlType();
        switch (_cls1..SwitchMap.com.j256.ormlite.field.SqlType[sqltype.ordinal()])
        {
        default:
            throw new SQLException((new StringBuilder("Unknown sql argument type: ")).append(sqltype).toString());

        case 1: // '\001'
        case 2: // '\002'
        case 3: // '\003'
            sqlitestatement.bindString(i + 1, obj.toString());
            break;

        case 4: // '\004'
        case 5: // '\005'
        case 6: // '\006'
        case 7: // '\007'
        case 8: // '\b'
            sqlitestatement.bindLong(i + 1, ((Number)obj).longValue());
            break;

        case 9: // '\t'
        case 10: // '\n'
            sqlitestatement.bindDouble(i + 1, ((Number)obj).doubleValue());
            break;

        case 11: // '\013'
        case 12: // '\f'
            sqlitestatement.bindBlob(i + 1, (byte[])obj);
            break;

        case 13: // '\r'
        case 14: // '\016'
        case 15: // '\017'
            throw new SQLException((new StringBuilder("Invalid Android type: ")).append(sqltype).toString());
        }
        if (true) goto _L8; else goto _L7
_L7:
    }

    private String[] toStrings(Object aobj[])
    {
        if (aobj == null || aobj.length == 0)
        {
            return null;
        }
        String as[] = new String[aobj.length];
        int i = 0;
        while (i < aobj.length) 
        {
            Object obj = aobj[i];
            if (obj == null)
            {
                as[i] = null;
            } else
            {
                as[i] = obj.toString();
            }
            i++;
        }
        return as;
    }

    private int update(String s, Object aobj[], FieldType afieldtype[], String s1)
    {
        SQLiteStatement sqlitestatement1 = db.compileStatement(s);
        SQLiteStatement sqlitestatement = sqlitestatement1;
        bindArgs(sqlitestatement, aobj, afieldtype);
        sqlitestatement.execute();
        Exception exception;
        android.database.SQLException sqlexception;
        SQLiteStatement sqlitestatement2;
        Exception exception1;
        SQLiteStatement sqlitestatement3;
        Exception exception2;
        android.database.SQLException sqlexception1;
        int i;
        SQLiteStatement sqlitestatement4;
        android.database.SQLException sqlexception2;
        long l;
        if (sqlitestatement != null)
        {
            sqlitestatement.close();
            sqlitestatement2 = null;
        } else
        {
            sqlitestatement2 = sqlitestatement;
        }
        sqlitestatement4 = db.compileStatement("SELECT CHANGES()");
        sqlitestatement3 = sqlitestatement4;
        l = sqlitestatement3.simpleQueryForLong();
        i = (int)l;
        if (sqlitestatement3 != null)
        {
            sqlitestatement3.close();
        }
_L1:
        logger.trace("{} statement is compiled and executed, changed {}: {}", s1, Integer.valueOf(i), s);
        return i;
        sqlexception;
        sqlitestatement = null;
_L6:
        throw SqlExceptionUtil.create((new StringBuilder("updating database failed: ")).append(s).toString(), sqlexception);
        exception;
_L5:
        if (sqlitestatement != null)
        {
            sqlitestatement.close();
        }
        throw exception;
        sqlexception1;
        sqlitestatement3 = sqlitestatement2;
_L4:
        i = 1;
        if (sqlitestatement3 != null)
        {
            sqlitestatement3.close();
        }
          goto _L1
        exception1;
        sqlitestatement3 = sqlitestatement2;
        exception2 = exception1;
_L3:
        if (sqlitestatement3 != null)
        {
            sqlitestatement3.close();
        }
        throw exception2;
        exception2;
        if (true) goto _L3; else goto _L2
_L2:
        sqlexception2;
          goto _L4
        exception;
        sqlitestatement = null;
          goto _L5
        sqlexception;
          goto _L6
    }

    public void close()
    {
        try
        {
            db.close();
            logger.trace("{}: db {} closed", this, db);
            return;
        }
        catch (android.database.SQLException sqlexception)
        {
            throw SqlExceptionUtil.create("problems closing the database connection", sqlexception);
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

    public void commit(Savepoint savepoint)
    {
        try
        {
            db.setTransactionSuccessful();
            db.endTransaction();
        }
        catch (android.database.SQLException sqlexception)
        {
            if (savepoint == null)
            {
                throw SqlExceptionUtil.create("problems commiting transaction", sqlexception);
            } else
            {
                throw SqlExceptionUtil.create((new StringBuilder("problems commiting transaction ")).append(savepoint.getSavepointName()).toString(), sqlexception);
            }
        }
        if (savepoint != null)
        {
            break MISSING_BLOCK_LABEL_28;
        }
        logger.trace("{}: transaction is successfuly ended", this);
        return;
        logger.trace("{}: transaction {} is successfuly ended", this, savepoint.getSavepointName());
        return;
    }

    public CompiledStatement compileStatement(String s, com.j256.ormlite.stmt.StatementBuilder.StatementType statementtype, FieldType afieldtype[], int i)
    {
        AndroidCompiledStatement androidcompiledstatement = new AndroidCompiledStatement(s, db, statementtype, cancelQueriesEnabled);
        logger.trace("{}: compiled statement got {}: {}", this, androidcompiledstatement, s);
        return androidcompiledstatement;
    }

    public int delete(String s, Object aobj[], FieldType afieldtype[])
    {
        return update(s, aobj, afieldtype, "deleted");
    }

    public int executeStatement(String s, int i)
    {
        return AndroidCompiledStatement.execSql(db, s, s, NO_STRING_ARGS);
    }

    public int insert(String s, Object aobj[], FieldType afieldtype[], GeneratedKeyHolder generatedkeyholder)
    {
        SQLiteStatement sqlitestatement = null;
        long l;
        sqlitestatement = db.compileStatement(s);
        bindArgs(sqlitestatement, aobj, afieldtype);
        l = sqlitestatement.executeInsert();
        if (generatedkeyholder == null)
        {
            break MISSING_BLOCK_LABEL_45;
        }
        generatedkeyholder.addKey(Long.valueOf(l));
        logger.trace("{}: insert statement is compiled and executed, changed {}: {}", this, Integer.valueOf(1), s);
        if (sqlitestatement != null)
        {
            sqlitestatement.close();
        }
        return 1;
        android.database.SQLException sqlexception;
        sqlexception;
        throw SqlExceptionUtil.create((new StringBuilder("inserting to database failed: ")).append(s).toString(), sqlexception);
        Exception exception;
        exception;
        if (sqlitestatement != null)
        {
            sqlitestatement.close();
        }
        throw exception;
    }

    public boolean isAutoCommit()
    {
        boolean flag;
        try
        {
            flag = db.inTransaction();
            logger.trace("{}: in transaction is {}", this, Boolean.valueOf(flag));
        }
        catch (android.database.SQLException sqlexception)
        {
            throw SqlExceptionUtil.create("problems getting auto-commit from database", sqlexception);
        }
        return !flag;
    }

    public boolean isAutoCommitSupported()
    {
        return true;
    }

    public boolean isClosed()
    {
        boolean flag;
        try
        {
            flag = db.isOpen();
            logger.trace("{}: db {} isOpen returned {}", this, db, Boolean.valueOf(flag));
        }
        catch (android.database.SQLException sqlexception)
        {
            throw SqlExceptionUtil.create("problems detecting if the database is closed", sqlexception);
        }
        return !flag;
    }

    public boolean isReadWrite()
    {
        return readWrite;
    }

    public boolean isTableExists(String s)
    {
        Cursor cursor;
        boolean flag;
        SQLiteDatabase sqlitedatabase = db;
        String s1 = (new StringBuilder("SELECT DISTINCT tbl_name FROM sqlite_master WHERE tbl_name = '")).append(s).append("'").toString();
        if (!(sqlitedatabase instanceof SQLiteDatabase))
        {
            cursor = sqlitedatabase.rawQuery(s1, null);
        } else
        {
            cursor = SQLiteInstrumentation.rawQuery((SQLiteDatabase)sqlitedatabase, s1, null);
        }
        if (cursor.getCount() > 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        logger.trace("{}: isTableExists '{}' returned {}", this, s, Boolean.valueOf(flag));
        cursor.close();
        return flag;
        Exception exception;
        exception;
        cursor.close();
        throw exception;
    }

    public long queryForLong(String s)
    {
        SQLiteStatement sqlitestatement = null;
        long l;
        sqlitestatement = db.compileStatement(s);
        l = sqlitestatement.simpleQueryForLong();
        logger.trace("{}: query for long simple query returned {}: {}", this, Long.valueOf(l), s);
        if (sqlitestatement != null)
        {
            sqlitestatement.close();
        }
        return l;
        android.database.SQLException sqlexception;
        sqlexception;
        throw SqlExceptionUtil.create((new StringBuilder("queryForLong from database failed: ")).append(s).toString(), sqlexception);
        Exception exception;
        exception;
        if (sqlitestatement != null)
        {
            sqlitestatement.close();
        }
        throw exception;
    }

    public long queryForLong(String s, Object aobj[], FieldType afieldtype[])
    {
        Cursor cursor = null;
        SQLiteDatabase sqlitedatabase;
        String as[];
        boolean flag;
        sqlitedatabase = db;
        as = toStrings(aobj);
        flag = sqlitedatabase instanceof SQLiteDatabase;
        cursor = null;
        if (flag) goto _L2; else goto _L1
_L1:
        cursor = sqlitedatabase.rawQuery(s, as);
_L5:
        AndroidDatabaseResults androiddatabaseresults = new AndroidDatabaseResults(cursor, null);
        if (!androiddatabaseresults.first()) goto _L4; else goto _L3
_L3:
        long l = androiddatabaseresults.getLong(0);
_L6:
        logger.trace("{}: query for long raw query returned {}: {}", this, Long.valueOf(l), s);
        if (cursor != null)
        {
            cursor.close();
        }
        return l;
_L2:
        Cursor cursor1 = SQLiteInstrumentation.rawQuery((SQLiteDatabase)sqlitedatabase, s, as);
        cursor = cursor1;
          goto _L5
_L4:
        l = 0L;
          goto _L6
        android.database.SQLException sqlexception;
        sqlexception;
        throw SqlExceptionUtil.create((new StringBuilder("queryForLong from database failed: ")).append(s).toString(), sqlexception);
        Exception exception;
        exception;
        if (cursor != null)
        {
            cursor.close();
        }
        throw exception;
          goto _L5
    }

    public Object queryForOne(String s, Object aobj[], FieldType afieldtype[], GenericRowMapper genericrowmapper, ObjectCache objectcache)
    {
        Cursor cursor = null;
        SQLiteDatabase sqlitedatabase;
        String as[];
        boolean flag;
        sqlitedatabase = db;
        as = toStrings(aobj);
        flag = sqlitedatabase instanceof SQLiteDatabase;
        cursor = null;
        if (flag) goto _L2; else goto _L1
_L1:
        Cursor cursor1 = sqlitedatabase.rawQuery(s, as);
        Cursor cursor2 = cursor1;
_L13:
        AndroidDatabaseResults androiddatabaseresults;
        boolean flag1;
        androiddatabaseresults = new AndroidDatabaseResults(cursor2, objectcache);
        logger.trace("{}: queried for one result: {}", this, s);
        flag1 = androiddatabaseresults.first();
        if (flag1) goto _L4; else goto _L3
_L3:
        Object obj;
        if (cursor2 != null)
        {
            cursor2.close();
        }
        obj = null;
_L6:
        return obj;
_L2:
        Cursor cursor3 = SQLiteInstrumentation.rawQuery((SQLiteDatabase)sqlitedatabase, s, as);
        cursor2 = cursor3;
        continue; /* Loop/switch isn't completed */
_L4:
        obj = genericrowmapper.mapRow(androiddatabaseresults);
        if (!androiddatabaseresults.next())
        {
            continue; /* Loop/switch isn't completed */
        }
        obj = MORE_THAN_ONE;
        if (cursor2 == null) goto _L6; else goto _L5
_L5:
        cursor2.close();
        return obj;
        if (cursor2 == null) goto _L6; else goto _L7
_L7:
        cursor2.close();
        return obj;
        android.database.SQLException sqlexception;
        sqlexception;
_L11:
        throw SqlExceptionUtil.create((new StringBuilder("queryForOne from database failed: ")).append(s).toString(), sqlexception);
        Exception exception;
        exception;
_L9:
        if (cursor != null)
        {
            cursor.close();
        }
        throw exception;
        exception;
        cursor = cursor2;
        if (true) goto _L9; else goto _L8
_L8:
        sqlexception;
        cursor = cursor2;
        if (true) goto _L11; else goto _L10
_L10:
        if (true) goto _L13; else goto _L12
_L12:
    }

    public void rollback(Savepoint savepoint)
    {
        try
        {
            db.endTransaction();
        }
        catch (android.database.SQLException sqlexception)
        {
            if (savepoint == null)
            {
                throw SqlExceptionUtil.create("problems rolling back transaction", sqlexception);
            } else
            {
                throw SqlExceptionUtil.create((new StringBuilder("problems rolling back transaction ")).append(savepoint.getSavepointName()).toString(), sqlexception);
            }
        }
        if (savepoint != null)
        {
            break MISSING_BLOCK_LABEL_22;
        }
        logger.trace("{}: transaction is ended, unsuccessfuly", this);
        return;
        logger.trace("{}: transaction {} is ended, unsuccessfuly", this, savepoint.getSavepointName());
        return;
    }

    public void setAutoCommit(boolean flag)
    {
        if (flag)
        {
            if (db.inTransaction())
            {
                db.setTransactionSuccessful();
                db.endTransaction();
            }
        } else
        if (!db.inTransaction())
        {
            db.beginTransaction();
            return;
        }
    }

    public Savepoint setSavePoint(String s)
    {
        OurSavePoint oursavepoint;
        try
        {
            db.beginTransaction();
            logger.trace("{}: save-point set with name {}", this, s);
            oursavepoint = new OurSavePoint(s);
        }
        catch (android.database.SQLException sqlexception)
        {
            throw SqlExceptionUtil.create((new StringBuilder("problems beginning transaction ")).append(s).toString(), sqlexception);
        }
        return oursavepoint;
    }

    public String toString()
    {
        return (new StringBuilder()).append(getClass().getSimpleName()).append("@").append(Integer.toHexString(super.hashCode())).toString();
    }

    public int update(String s, Object aobj[], FieldType afieldtype[])
    {
        return update(s, aobj, afieldtype, "updated");
    }

    static 
    {
        VersionUtils.checkCoreVersusAndroidVersions("VERSION__4.48__");
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
                $SwitchMap$com$j256$ormlite$field$SqlType[SqlType.CHAR.ordinal()] = 3;
            }
            catch (NoSuchFieldError nosuchfielderror2) { }
            try
            {
                $SwitchMap$com$j256$ormlite$field$SqlType[SqlType.BOOLEAN.ordinal()] = 4;
            }
            catch (NoSuchFieldError nosuchfielderror3) { }
            try
            {
                $SwitchMap$com$j256$ormlite$field$SqlType[SqlType.BYTE.ordinal()] = 5;
            }
            catch (NoSuchFieldError nosuchfielderror4) { }
            try
            {
                $SwitchMap$com$j256$ormlite$field$SqlType[SqlType.SHORT.ordinal()] = 6;
            }
            catch (NoSuchFieldError nosuchfielderror5) { }
            try
            {
                $SwitchMap$com$j256$ormlite$field$SqlType[SqlType.INTEGER.ordinal()] = 7;
            }
            catch (NoSuchFieldError nosuchfielderror6) { }
            try
            {
                $SwitchMap$com$j256$ormlite$field$SqlType[SqlType.LONG.ordinal()] = 8;
            }
            catch (NoSuchFieldError nosuchfielderror7) { }
            try
            {
                $SwitchMap$com$j256$ormlite$field$SqlType[SqlType.FLOAT.ordinal()] = 9;
            }
            catch (NoSuchFieldError nosuchfielderror8) { }
            try
            {
                $SwitchMap$com$j256$ormlite$field$SqlType[SqlType.DOUBLE.ordinal()] = 10;
            }
            catch (NoSuchFieldError nosuchfielderror9) { }
            try
            {
                $SwitchMap$com$j256$ormlite$field$SqlType[SqlType.BYTE_ARRAY.ordinal()] = 11;
            }
            catch (NoSuchFieldError nosuchfielderror10) { }
            try
            {
                $SwitchMap$com$j256$ormlite$field$SqlType[SqlType.SERIALIZABLE.ordinal()] = 12;
            }
            catch (NoSuchFieldError nosuchfielderror11) { }
            try
            {
                $SwitchMap$com$j256$ormlite$field$SqlType[SqlType.DATE.ordinal()] = 13;
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


    private class OurSavePoint
        implements Savepoint
    {

        private String name;

        public int getSavepointId()
        {
            return 0;
        }

        public String getSavepointName()
        {
            return name;
        }

        public OurSavePoint(String s)
        {
            name = s;
        }
    }

}
