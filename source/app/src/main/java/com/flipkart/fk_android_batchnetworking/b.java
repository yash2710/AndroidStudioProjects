// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.fk_android_batchnetworking;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.newrelic.agent.android.instrumentation.SQLiteInstrumentation;

// Referenced classes of package com.flipkart.fk_android_batchnetworking:
//            a

final class b extends SQLiteOpenHelper
{

    private SQLiteDatabase a;
    private a b;

    b(a a1, Context context)
    {
        b = a1;
        super(context, "batch.db", null, 1);
        a = null;
        a = getWritableDatabase();
    }

    public final SQLiteDatabase getDatabase()
    {
        return a;
    }

    public final void onCreate(SQLiteDatabase sqlitedatabase)
    {
        String s = (new StringBuilder("CREATE TABLE IF NOT EXISTS cache (")).append(com.flipkart.fk_android_batchnetworking.a.a(b)).append(" INTEGER primary key, ").append(com.flipkart.fk_android_batchnetworking.a.b(b)).append(" TEXT, ").append(com.flipkart.fk_android_batchnetworking.a.c(b)).append(" BLOB, ").append(com.flipkart.fk_android_batchnetworking.a.d(b)).append(" INTEGER)").toString();
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

    public final void onUpgrade(SQLiteDatabase sqlitedatabase, int i, int j)
    {
        String s;
        if (!(sqlitedatabase instanceof SQLiteDatabase))
        {
            sqlitedatabase.execSQL("DROP TABLE IF EXISTS cache");
        } else
        {
            SQLiteInstrumentation.execSQL((SQLiteDatabase)sqlitedatabase, "DROP TABLE IF EXISTS cache");
        }
        s = (new StringBuilder("CREATE TABLE IF NOT EXISTS cache (")).append(com.flipkart.fk_android_batchnetworking.a.a(b)).append(" INTEGER primary key, ").append(com.flipkart.fk_android_batchnetworking.a.b(b)).append(" TEXT, ").append(com.flipkart.fk_android_batchnetworking.a.c(b)).append(" BLOB, ").append(com.flipkart.fk_android_batchnetworking.a.d(b)).append(" INTEGER)").toString();
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
}
