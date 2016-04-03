// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.SystemClock;
import com.newrelic.agent.android.instrumentation.SQLiteInstrumentation;
import java.util.LinkedList;
import java.util.List;

// Referenced classes of package com.google.android.gms.internal:
//            cv, eu

public class cx
{

    private static final Object ls = new Object();
    private static final String pr = String.format("CREATE TABLE IF NOT EXISTS %s ( %s INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, %s TEXT NOT NULL, %s TEXT NOT NULL, %s INTEGER)", new Object[] {
        "InAppPurchase", "purchase_id", "product_id", "developer_payload", "record_time"
    });
    private static cx pt;
    private final a ps;

    private cx(Context context)
    {
        ps = new a(context, "google_inapp_purchase.db");
    }

    static String bl()
    {
        return pr;
    }

    public static cx k(Context context)
    {
        cx cx1;
        synchronized (ls)
        {
            if (pt == null)
            {
                pt = new cx(context);
            }
            cx1 = pt;
        }
        return cx1;
    }

    public cv a(Cursor cursor)
    {
        if (cursor == null)
        {
            return null;
        } else
        {
            return new cv(cursor.getLong(0), cursor.getString(1), cursor.getString(2));
        }
    }

    public void a(cv cv1)
    {
        if (cv1 == null)
        {
            return;
        }
        Object obj = ls;
        obj;
        JVM INSTR monitorenter ;
        SQLiteDatabase sqlitedatabase = getWritableDatabase();
        if (sqlitedatabase != null)
        {
            break MISSING_BLOCK_LABEL_30;
        }
        obj;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
        String s;
        Object aobj[] = new Object[2];
        aobj[0] = "purchase_id";
        aobj[1] = Long.valueOf(cv1.pl);
        s = String.format("%s = %d", aobj);
        if (sqlitedatabase instanceof SQLiteDatabase)
        {
            break MISSING_BLOCK_LABEL_84;
        }
        sqlitedatabase.delete("InAppPurchase", s, null);
_L2:
        obj;
        JVM INSTR monitorexit ;
        return;
        SQLiteInstrumentation.delete((SQLiteDatabase)sqlitedatabase, "InAppPurchase", s, null);
        if (true) goto _L2; else goto _L1
_L1:
    }

    public void b(cv cv1)
    {
        if (cv1 == null)
        {
            return;
        }
        Object obj = ls;
        obj;
        JVM INSTR monitorenter ;
        SQLiteDatabase sqlitedatabase = getWritableDatabase();
        if (sqlitedatabase != null)
        {
            break MISSING_BLOCK_LABEL_30;
        }
        obj;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
        ContentValues contentvalues;
        contentvalues = new ContentValues();
        contentvalues.put("product_id", cv1.pn);
        contentvalues.put("developer_payload", cv1.pm);
        contentvalues.put("record_time", Long.valueOf(SystemClock.elapsedRealtime()));
        if (sqlitedatabase instanceof SQLiteDatabase) goto _L2; else goto _L1
_L1:
        long l1 = sqlitedatabase.insert("InAppPurchase", null, contentvalues);
_L3:
        cv1.pl = l1;
        if ((long)getRecordCount() > 20000L)
        {
            bk();
        }
        obj;
        JVM INSTR monitorexit ;
        return;
_L2:
        long l = SQLiteInstrumentation.insert((SQLiteDatabase)sqlitedatabase, "InAppPurchase", null, contentvalues);
        l1 = l;
          goto _L3
    }

    public void bk()
    {
        Object obj = ls;
        obj;
        JVM INSTR monitorenter ;
        SQLiteDatabase sqlitedatabase = getWritableDatabase();
        if (sqlitedatabase != null)
        {
            break MISSING_BLOCK_LABEL_18;
        }
        obj;
        JVM INSTR monitorexit ;
        return;
        if (sqlitedatabase instanceof SQLiteDatabase) goto _L2; else goto _L1
_L1:
        Cursor cursor2 = sqlitedatabase.query("InAppPurchase", null, null, null, null, null, "record_time ASC", "1");
        Cursor cursor = cursor2;
_L3:
        if (cursor == null)
        {
            break MISSING_BLOCK_LABEL_71;
        }
        if (cursor.moveToFirst())
        {
            a(a(cursor));
        }
        if (cursor == null)
        {
            break MISSING_BLOCK_LABEL_83;
        }
        cursor.close();
_L5:
        obj;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
_L2:
        Cursor cursor1 = SQLiteInstrumentation.query((SQLiteDatabase)sqlitedatabase, "InAppPurchase", null, null, null, null, null, "record_time ASC", "1");
        cursor = cursor1;
          goto _L3
        SQLiteException sqliteexception;
        sqliteexception;
        cursor = null;
_L7:
        eu.D((new StringBuilder("Error remove oldest record")).append(sqliteexception.getMessage()).toString());
        if (cursor == null) goto _L5; else goto _L4
_L4:
        cursor.close();
          goto _L5
_L6:
        if (cursor == null)
        {
            break MISSING_BLOCK_LABEL_173;
        }
        cursor.close();
        Exception exception1;
        throw exception1;
        exception1;
          goto _L6
        sqliteexception;
          goto _L7
        exception1;
        cursor = null;
          goto _L6
    }

    public List d(long l)
    {
        Object obj = ls;
        obj;
        JVM INSTR monitorenter ;
        LinkedList linkedlist = new LinkedList();
        if (l > 0L)
        {
            break MISSING_BLOCK_LABEL_26;
        }
        obj;
        JVM INSTR monitorexit ;
        return linkedlist;
        SQLiteDatabase sqlitedatabase = getWritableDatabase();
        if (sqlitedatabase != null)
        {
            break MISSING_BLOCK_LABEL_42;
        }
        obj;
        JVM INSTR monitorexit ;
        return linkedlist;
        String s = String.valueOf(l);
        if (sqlitedatabase instanceof SQLiteDatabase) goto _L2; else goto _L1
_L1:
        Cursor cursor2 = sqlitedatabase.query("InAppPurchase", null, null, null, null, null, "record_time ASC", s);
        Cursor cursor = cursor2;
_L3:
        boolean flag;
        if (cursor.moveToFirst())
        {
            do
            {
                linkedlist.add(a(cursor));
                flag = cursor.moveToNext();
            } while (flag);
        }
        if (cursor == null)
        {
            break MISSING_BLOCK_LABEL_128;
        }
        cursor.close();
_L5:
        obj;
        JVM INSTR monitorexit ;
        return linkedlist;
_L2:
        Cursor cursor1 = SQLiteInstrumentation.query((SQLiteDatabase)sqlitedatabase, "InAppPurchase", null, null, null, null, null, "record_time ASC", s);
        cursor = cursor1;
          goto _L3
        SQLiteException sqliteexception;
        sqliteexception;
        cursor = null;
_L8:
        eu.D((new StringBuilder("Error extracing purchase info: ")).append(sqliteexception.getMessage()).toString());
        if (cursor == null) goto _L5; else goto _L4
_L4:
        cursor.close();
          goto _L5
        Exception exception;
        exception;
        throw exception;
        Exception exception1;
        exception1;
        cursor = null;
_L7:
        if (cursor == null)
        {
            break MISSING_BLOCK_LABEL_228;
        }
        cursor.close();
        throw exception1;
        exception1;
        if (true) goto _L7; else goto _L6
_L6:
        sqliteexception;
          goto _L8
    }

    public int getRecordCount()
    {
        Cursor cursor = null;
        Object obj = ls;
        obj;
        JVM INSTR monitorenter ;
        SQLiteDatabase sqlitedatabase = getWritableDatabase();
        if (sqlitedatabase != null)
        {
            break MISSING_BLOCK_LABEL_23;
        }
        obj;
        JVM INSTR monitorexit ;
        return 0;
        boolean flag = sqlitedatabase instanceof SQLiteDatabase;
        cursor = null;
        if (flag) goto _L2; else goto _L1
_L1:
        cursor = sqlitedatabase.rawQuery("select count(*) from InAppPurchase", null);
_L3:
        int i;
        if (!cursor.moveToFirst())
        {
            break MISSING_BLOCK_LABEL_103;
        }
        i = cursor.getInt(0);
        if (cursor == null)
        {
            break MISSING_BLOCK_LABEL_74;
        }
        cursor.close();
        obj;
        JVM INSTR monitorexit ;
        return i;
        Exception exception;
        exception;
        throw exception;
_L2:
        Cursor cursor1 = SQLiteInstrumentation.rawQuery((SQLiteDatabase)sqlitedatabase, "select count(*) from InAppPurchase", null);
        cursor = cursor1;
          goto _L3
        if (cursor == null)
        {
            break MISSING_BLOCK_LABEL_113;
        }
        cursor.close();
_L5:
        obj;
        JVM INSTR monitorexit ;
        return 0;
        SQLiteException sqliteexception;
        sqliteexception;
        eu.D((new StringBuilder("Error getting record count")).append(sqliteexception.getMessage()).toString());
        if (cursor == null) goto _L5; else goto _L4
_L4:
        cursor.close();
          goto _L5
        Exception exception1;
        exception1;
        if (cursor == null)
        {
            break MISSING_BLOCK_LABEL_167;
        }
        cursor.close();
        throw exception1;
    }

    public SQLiteDatabase getWritableDatabase()
    {
        SQLiteDatabase sqlitedatabase;
        try
        {
            sqlitedatabase = ps.getWritableDatabase();
        }
        catch (SQLiteException sqliteexception)
        {
            eu.D("Error opening writable conversion tracking database");
            return null;
        }
        return sqlitedatabase;
    }


    private class a extends SQLiteOpenHelper
    {

        final cx pu;

        public void onCreate(SQLiteDatabase sqlitedatabase)
        {
            String s = cx.bl();
            if (!(sqlitedatabase instanceof SQLiteDatabase))
            {
                sqlitedatabase.execSQL(s);
                return;
            } else
            {
                SQLiteInstrumentation.execSQL((SQLiteDatabase)sqlitedatabase, s);
                return;
            }
        }

        public void onUpgrade(SQLiteDatabase sqlitedatabase, int i, int j)
        {
            eu.B((new StringBuilder("Database updated from version ")).append(i).append(" to version ").append(j).toString());
            if (!(sqlitedatabase instanceof SQLiteDatabase))
            {
                sqlitedatabase.execSQL("DROP TABLE IF EXISTS InAppPurchase");
            } else
            {
                SQLiteInstrumentation.execSQL((SQLiteDatabase)sqlitedatabase, "DROP TABLE IF EXISTS InAppPurchase");
            }
            onCreate(sqlitedatabase);
        }

        public a(Context context, String s)
        {
            pu = cx.this;
            super(context, s, null, 4);
        }
    }

}
