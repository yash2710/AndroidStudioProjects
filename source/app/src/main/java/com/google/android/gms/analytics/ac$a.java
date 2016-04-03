// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.analytics;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import com.newrelic.agent.android.instrumentation.SQLiteInstrumentation;
import java.io.File;
import java.util.HashSet;
import java.util.Set;

// Referenced classes of package com.google.android.gms.analytics:
//            aa, ac, i, p

class wY extends SQLiteOpenHelper
{

    final ac wW;
    private boolean wX;
    private long wY;

    private void a(SQLiteDatabase sqlitedatabase)
    {
        Cursor cursor;
        HashSet hashset;
        String as[];
        int j;
        if (!(sqlitedatabase instanceof SQLiteDatabase))
        {
            cursor = sqlitedatabase.rawQuery("SELECT * FROM hits2 WHERE 0", null);
        } else
        {
            cursor = SQLiteInstrumentation.rawQuery((SQLiteDatabase)sqlitedatabase, "SELECT * FROM hits2 WHERE 0", null);
        }
        hashset = new HashSet();
        as = cursor.getColumnNames();
        j = 0;
_L2:
        if (j >= as.length)
        {
            break; /* Loop/switch isn't completed */
        }
        hashset.add(as[j]);
        j++;
        if (true) goto _L2; else goto _L1
_L1:
        cursor.close();
        if (!hashset.remove("hit_id") || !hashset.remove("hit_url") || !hashset.remove("hit_string") || !hashset.remove("hit_time"))
        {
            throw new SQLiteException("Database column missing");
        }
        break MISSING_BLOCK_LABEL_145;
        Exception exception;
        exception;
        cursor.close();
        throw exception;
label0:
        {
            boolean flag;
            if (!hashset.remove("hit_app_id"))
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (!hashset.isEmpty())
            {
                throw new SQLiteException("Database has extra columns");
            }
            if (flag)
            {
                if (sqlitedatabase instanceof SQLiteDatabase)
                {
                    break label0;
                }
                sqlitedatabase.execSQL("ALTER TABLE hits2 ADD COLUMN hit_app_id");
            }
            return;
        }
        SQLiteInstrumentation.execSQL((SQLiteDatabase)sqlitedatabase, "ALTER TABLE hits2 ADD COLUMN hit_app_id");
        return;
    }

    private boolean a(String s, SQLiteDatabase sqlitedatabase)
    {
        Cursor cursor = null;
        String as[];
        String as1[];
        as = (new String[] {
            "name"
        });
        as1 = (new String[] {
            s
        });
        if (sqlitedatabase instanceof SQLiteDatabase) goto _L2; else goto _L1
_L1:
        Cursor cursor4 = sqlitedatabase.query("SQLITE_MASTER", as, "name=?", as1, null, null, null);
        Cursor cursor3 = cursor4;
_L3:
        boolean flag = cursor3.moveToFirst();
        if (cursor3 != null)
        {
            cursor3.close();
        }
        return flag;
_L2:
        Cursor cursor2 = SQLiteInstrumentation.query((SQLiteDatabase)sqlitedatabase, "SQLITE_MASTER", as, "name=?", as1, null, null, null);
        cursor3 = cursor2;
          goto _L3
        SQLiteException sqliteexception;
        sqliteexception;
        Cursor cursor1 = null;
_L7:
        aa.D((new StringBuilder("Error querying for table ")).append(s).toString());
        if (cursor1 != null)
        {
            cursor1.close();
        }
        return false;
        Exception exception;
        exception;
_L5:
        if (cursor != null)
        {
            cursor.close();
        }
        throw exception;
        exception;
        cursor = cursor3;
        continue; /* Loop/switch isn't completed */
        Exception exception1;
        exception1;
        cursor = cursor1;
        exception = exception1;
        if (true) goto _L5; else goto _L4
_L4:
        SQLiteException sqliteexception1;
        sqliteexception1;
        cursor1 = cursor3;
        if (true) goto _L7; else goto _L6
_L6:
    }

    public SQLiteDatabase getWritableDatabase()
    {
        if (wX && 0x36ee80L + wY > ac.a(wW).currentTimeMillis())
        {
            throw new SQLiteException("Database creation failed");
        }
        wX = true;
        wY = ac.a(wW).currentTimeMillis();
        SQLiteDatabase sqlitedatabase1 = super.getWritableDatabase();
        SQLiteDatabase sqlitedatabase = sqlitedatabase1;
_L2:
        if (sqlitedatabase == null)
        {
            sqlitedatabase = super.getWritableDatabase();
        }
        wX = false;
        return sqlitedatabase;
        SQLiteException sqliteexception;
        sqliteexception;
        ac.c(wW).getDatabasePath(ac.b(wW)).delete();
        sqlitedatabase = null;
        if (true) goto _L2; else goto _L1
_L1:
    }

    public void onCreate(SQLiteDatabase sqlitedatabase)
    {
        p.N(sqlitedatabase.getPath());
    }

    public void onOpen(SQLiteDatabase sqlitedatabase)
    {
        Cursor cursor;
        if (android.os..VERSION.SDK_INT >= 15)
        {
            break MISSING_BLOCK_LABEL_36;
        }
        if (!(sqlitedatabase instanceof SQLiteDatabase))
        {
            cursor = sqlitedatabase.rawQuery("PRAGMA journal_mode=memory", null);
        } else
        {
            cursor = SQLiteInstrumentation.rawQuery((SQLiteDatabase)sqlitedatabase, "PRAGMA journal_mode=memory", null);
        }
        cursor.moveToFirst();
        cursor.close();
        if (!a("hits2", sqlitedatabase))
        {
            String s = ac.du();
            Exception exception;
            if (!(sqlitedatabase instanceof SQLiteDatabase))
            {
                sqlitedatabase.execSQL(s);
                return;
            } else
            {
                SQLiteInstrumentation.execSQL((SQLiteDatabase)sqlitedatabase, s);
                return;
            }
        } else
        {
            a(sqlitedatabase);
            return;
        }
        exception;
        cursor.close();
        throw exception;
    }

    public void onUpgrade(SQLiteDatabase sqlitedatabase, int j, int k)
    {
    }

    orFactory(ac ac1, Context context, String s)
    {
        wW = ac1;
        super(context, s, null, 1);
        wY = 0L;
    }
}
