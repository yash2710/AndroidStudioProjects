// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.fk_android_batchnetworking;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import com.flipkart.logging.FkLogger;
import com.newrelic.agent.android.instrumentation.SQLiteInstrumentation;

// Referenced classes of package com.flipkart.fk_android_batchnetworking:
//            b, BatchNetworking, GroupPriorityQueue, Data, 
//            Group, GroupDataHandler

final class a
{

    private b a;
    private String b;
    private String c;
    private String d;
    private String e;
    private SQLiteStatement f;

    public a(Context context)
    {
        b = "eventId";
        c = "groupId";
        d = "data";
        e = "expiry";
        a = new b(this, context);
        String s = (new StringBuilder("insert into cache (")).append(b).append(", ").append(c).append(", ").append(d).append(", ").append(e).append(") values (?, ?, ?, ?)").toString();
        f = a.getDatabase().compileStatement(s);
    }

    static String a(a a1)
    {
        return a1.b;
    }

    static String b(a a1)
    {
        return a1.c;
    }

    static String c(a a1)
    {
        return a1.d;
    }

    static String d(a a1)
    {
        return a1.e;
    }

    public final boolean dataExists(long l)
    {
        SQLiteDatabase sqlitedatabase = a.getDatabase();
        String as[] = new String[1];
        as[0] = b;
        String s = (new StringBuilder()).append(b).append("=?").toString();
        String as1[] = new String[1];
        as1[0] = (new StringBuilder()).append(l).toString();
        Cursor cursor;
        boolean flag;
        if (!(sqlitedatabase instanceof SQLiteDatabase))
        {
            cursor = sqlitedatabase.query("cache", as, s, as1, null, null, null);
        } else
        {
            cursor = SQLiteInstrumentation.query((SQLiteDatabase)sqlitedatabase, "cache", as, s, as1, null, null, null);
        }
        flag = cursor.moveToFirst();
        if (cursor != null && !cursor.isClosed())
        {
            cursor.close();
        }
        return flag;
    }

    public final void emptyThisTable()
    {
        SQLiteDatabase sqlitedatabase = a.getDatabase();
        if (!(sqlitedatabase instanceof SQLiteDatabase))
        {
            sqlitedatabase.delete("cache", null, null);
            return;
        } else
        {
            SQLiteInstrumentation.delete((SQLiteDatabase)sqlitedatabase, "cache", null, null);
            return;
        }
    }

    public final void loadCachedDataInBatchNetworkingInstance(BatchNetworking batchnetworking)
    {
        if (batchnetworking != null) goto _L2; else goto _L1
_L1:
        return;
_L2:
        Cursor cursor;
        Group group;
        SQLiteDatabase sqlitedatabase = a.getDatabase();
        String as[] = new String[4];
        as[0] = b;
        as[1] = c;
        as[2] = d;
        as[3] = e;
        String s = b;
        String s1;
        if (!(sqlitedatabase instanceof SQLiteDatabase))
        {
            cursor = sqlitedatabase.query("cache", as, null, null, null, null, s);
        } else
        {
            cursor = SQLiteInstrumentation.query((SQLiteDatabase)sqlitedatabase, "cache", as, null, null, null, null, s);
        }
        if (!cursor.moveToFirst())
        {
            break; /* Loop/switch isn't completed */
        }
        s1 = cursor.getString(1);
        group = batchnetworking.getGroupPriorityQueue().getGroupForGroupId(s1);
        if (group != null)
        {
            try
            {
                Data data = new Data();
                data.setEventId(cursor.getLong(0));
                data.setData(group.getBatchDataHandler().deSerializeIndividualData(cursor.getBlob(2)));
                data.setCacheState(Data.DataCacheState.CSTATE_CACHED);
                group.push(data);
            }
            catch (Exception exception) { }
        }
        if (cursor.moveToNext())
        {
            break MISSING_BLOCK_LABEL_84;
        }
_L4:
        if (cursor != null && !cursor.isClosed())
        {
            cursor.close();
            return;
        }
        if (true) goto _L1; else goto _L3
_L3:
        FkLogger.info("dbmanager", "In loadCachedDataInBatchNetworkingInstance: No data to load");
          goto _L4
        if (true) goto _L1; else goto _L5
_L5:
    }

    public final void persistData(long l, String s, byte abyte0[], long l1)
    {
        try
        {
            f.bindLong(1, l);
            f.bindString(2, s);
            f.bindBlob(3, abyte0);
            f.bindLong(4, l1);
            f.executeInsert();
            return;
        }
        catch (Exception exception)
        {
            FkLogger.error("dbmanager", (new StringBuilder("Exception in persistBatchDatum ")).append(exception).toString());
        }
    }

    public final void removeData(long l)
    {
        SQLiteDatabase sqlitedatabase = a.getDatabase();
        String s = (new StringBuilder()).append(b).append("=?").toString();
        String as[] = new String[1];
        as[0] = (new StringBuilder()).append(l).toString();
        if (!(sqlitedatabase instanceof SQLiteDatabase))
        {
            sqlitedatabase.delete("cache", s, as);
            return;
        } else
        {
            SQLiteInstrumentation.delete((SQLiteDatabase)sqlitedatabase, "cache", s, as);
            return;
        }
    }
}
