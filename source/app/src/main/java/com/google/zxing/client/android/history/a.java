// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing.client.android.history;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.newrelic.agent.android.instrumentation.SQLiteInstrumentation;

final class a extends SQLiteOpenHelper
{

    a(Context context)
    {
        super(context, "barcode_scanner_history.db", null, 5);
    }

    public final void onCreate(SQLiteDatabase sqlitedatabase)
    {
        if (!(sqlitedatabase instanceof SQLiteDatabase))
        {
            sqlitedatabase.execSQL("CREATE TABLE history (id INTEGER PRIMARY KEY, text TEXT, format TEXT, display TEXT, timestamp INTEGER, details TEXT);");
            return;
        } else
        {
            SQLiteInstrumentation.execSQL((SQLiteDatabase)sqlitedatabase, "CREATE TABLE history (id INTEGER PRIMARY KEY, text TEXT, format TEXT, display TEXT, timestamp INTEGER, details TEXT);");
            return;
        }
    }

    public final void onUpgrade(SQLiteDatabase sqlitedatabase, int i, int j)
    {
        if (!(sqlitedatabase instanceof SQLiteDatabase))
        {
            sqlitedatabase.execSQL("DROP TABLE IF EXISTS history");
        } else
        {
            SQLiteInstrumentation.execSQL((SQLiteDatabase)sqlitedatabase, "DROP TABLE IF EXISTS history");
        }
        onCreate(sqlitedatabase);
    }
}
