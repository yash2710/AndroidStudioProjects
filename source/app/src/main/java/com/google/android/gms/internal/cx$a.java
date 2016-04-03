// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.newrelic.agent.android.instrumentation.SQLiteInstrumentation;

// Referenced classes of package com.google.android.gms.internal:
//            cx, eu

public class sorFactory extends SQLiteOpenHelper
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

    public sorFactory(cx cx1, Context context, String s)
    {
        pu = cx1;
        super(context, s, null, 4);
    }
}
