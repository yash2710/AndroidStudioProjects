// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing.client.android.history;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.os.Environment;
import android.preference.PreferenceManager;
import com.flipkart.logging.FkLogger;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.Result;
import com.google.zxing.client.android.result.ResultHandler;
import com.newrelic.agent.android.instrumentation.SQLiteInstrumentation;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

// Referenced classes of package com.google.zxing.client.android.history:
//            a, HistoryItem

public final class HistoryManager
{

    private static final String a = com/google/zxing/client/android/history/HistoryManager.getSimpleName();
    private static final String b[] = {
        "text", "display", "format", "timestamp", "details"
    };
    private static final String c[] = {
        "COUNT(1)"
    };
    private static final String d[] = {
        "id"
    };
    private static final String e[] = {
        "id", "details"
    };
    private static final DateFormat f = DateFormat.getDateTimeInstance(2, 2);
    private final Activity g;

    public HistoryManager(Activity activity)
    {
        g = activity;
    }

    static Uri a(String s)
    {
        File file = new File(new File(Environment.getExternalStorageDirectory(), "BarcodeScanner"), "History");
        if (file.exists() || file.mkdirs()) goto _L2; else goto _L1
_L1:
        FkLogger.warn(a, (new StringBuilder("Couldn't make dir ")).append(file).toString());
_L4:
        return null;
_L2:
        File file1 = new File(file, (new StringBuilder("history-")).append(System.currentTimeMillis()).append(".csv").toString());
        OutputStreamWriter outputstreamwriter = new OutputStreamWriter(new FileOutputStream(file1), Charset.forName("UTF-8"));
        Uri uri;
        outputstreamwriter.write(s);
        uri = Uri.parse((new StringBuilder("file://")).append(file1.getAbsolutePath()).toString());
        try
        {
            outputstreamwriter.close();
        }
        catch (IOException ioexception3)
        {
            return uri;
        }
        return uri;
        IOException ioexception;
        ioexception;
        outputstreamwriter = null;
_L7:
        FkLogger.warn(a, (new StringBuilder("Couldn't access file ")).append(file1).append(" due to ").append(ioexception).toString());
        if (outputstreamwriter == null) goto _L4; else goto _L3
_L3:
        try
        {
            outputstreamwriter.close();
        }
        catch (IOException ioexception2)
        {
            return null;
        }
        return null;
        Exception exception1;
        exception1;
        Exception exception;
        outputstreamwriter = null;
        exception = exception1;
_L6:
        if (outputstreamwriter != null)
        {
            try
            {
                outputstreamwriter.close();
            }
            catch (IOException ioexception1) { }
        }
        throw exception;
        exception;
        if (true) goto _L6; else goto _L5
_L5:
        ioexception;
          goto _L7
    }

    private static void a(Cursor cursor, SQLiteDatabase sqlitedatabase)
    {
        if (cursor != null)
        {
            cursor.close();
        }
        if (sqlitedatabase != null)
        {
            sqlitedatabase.close();
        }
    }

    private static String b(String s)
    {
        if (s == null)
        {
            return "";
        } else
        {
            return s.replace("\"", "\"\"");
        }
    }

    final CharSequence a()
    {
        a a1 = new a(g);
        SQLiteDatabase sqlitedatabase1 = a1.getWritableDatabase();
        String as[] = b;
        if (sqlitedatabase1 instanceof SQLiteDatabase) goto _L2; else goto _L1
_L1:
        Cursor cursor3 = sqlitedatabase1.query("history", as, null, null, null, null, "timestamp DESC");
        Cursor cursor2 = cursor3;
_L4:
        StringBuilder stringbuilder;
        stringbuilder = new StringBuilder(1000);
        for (; cursor2.moveToNext(); stringbuilder.append('"').append(b(cursor2.getString(4))).append("\"\r\n"))
        {
            stringbuilder.append('"').append(b(cursor2.getString(0))).append("\",");
            stringbuilder.append('"').append(b(cursor2.getString(1))).append("\",");
            stringbuilder.append('"').append(b(cursor2.getString(2))).append("\",");
            stringbuilder.append('"').append(b(cursor2.getString(3))).append("\",");
            long l = cursor2.getLong(3);
            stringbuilder.append('"').append(b(f.format(new Date(l)))).append("\",");
        }

          goto _L3
        Exception exception2;
        exception2;
        Exception exception;
        Cursor cursor;
        SQLiteDatabase sqlitedatabase;
        cursor = cursor2;
        sqlitedatabase = sqlitedatabase1;
        exception = exception2;
_L5:
        a(cursor, sqlitedatabase);
        throw exception;
_L2:
        Cursor cursor1 = SQLiteInstrumentation.query((SQLiteDatabase)sqlitedatabase1, "history", as, null, null, null, null, "timestamp DESC");
        cursor2 = cursor1;
          goto _L4
_L3:
        a(cursor2, sqlitedatabase1);
        return stringbuilder;
        exception;
        cursor = null;
        sqlitedatabase = null;
          goto _L5
        Exception exception1;
        exception1;
        sqlitedatabase = sqlitedatabase1;
        exception = exception1;
        cursor = null;
          goto _L5
    }

    public final void addHistoryItem(Result result, ResultHandler resulthandler)
    {
        if (!g.getIntent().getBooleanExtra("SAVE_HISTORY", true) || resulthandler.areContentsSecure())
        {
            return;
        }
        if (PreferenceManager.getDefaultSharedPreferences(g).getBoolean("preferences_remember_duplicates", false)) goto _L2; else goto _L1
_L1:
        String s;
        a a2;
        s = result.getText();
        a2 = new a(g);
        SQLiteDatabase sqlitedatabase3 = a2.getWritableDatabase();
        SQLiteDatabase sqlitedatabase2 = sqlitedatabase3;
        String as[] = {
            s
        };
        if (sqlitedatabase2 instanceof SQLiteDatabase) goto _L4; else goto _L3
_L3:
        sqlitedatabase2.delete("history", "text=?", as);
_L7:
        a(null, sqlitedatabase2);
_L2:
        ContentValues contentvalues;
        a a1;
        contentvalues = new ContentValues();
        contentvalues.put("text", result.getText());
        contentvalues.put("format", result.getBarcodeFormat().toString());
        contentvalues.put("display", resulthandler.getDisplayContents().toString());
        contentvalues.put("timestamp", Long.valueOf(System.currentTimeMillis()));
        a1 = new a(g);
        SQLiteDatabase sqlitedatabase1 = a1.getWritableDatabase();
        SQLiteDatabase sqlitedatabase = sqlitedatabase1;
        if (sqlitedatabase instanceof SQLiteDatabase) goto _L6; else goto _L5
_L5:
        sqlitedatabase.insert("history", "timestamp", contentvalues);
_L8:
        a(null, sqlitedatabase);
        return;
_L4:
        SQLiteInstrumentation.delete((SQLiteDatabase)sqlitedatabase2, "history", "text=?", as);
          goto _L7
        Exception exception1;
        exception1;
_L11:
        a(null, sqlitedatabase2);
        throw exception1;
_L6:
        SQLiteInstrumentation.insert((SQLiteDatabase)sqlitedatabase, "history", "timestamp", contentvalues);
          goto _L8
        Exception exception;
        exception;
_L10:
        a(null, sqlitedatabase);
        throw exception;
        exception;
        sqlitedatabase = null;
        if (true) goto _L10; else goto _L9
_L9:
        exception1;
        sqlitedatabase2 = null;
          goto _L11
    }

    public final void addHistoryItemDetails(String s, String s1)
    {
        a a1 = new a(g);
        SQLiteDatabase sqlitedatabase1 = a1.getWritableDatabase();
        String as[];
        String as1[];
        as = e;
        as1 = (new String[] {
            s
        });
        if (sqlitedatabase1 instanceof SQLiteDatabase) goto _L2; else goto _L1
_L1:
        Cursor cursor3 = sqlitedatabase1.query("history", as, "text=?", as1, null, null, "timestamp DESC", "1");
        Cursor cursor2 = cursor3;
_L9:
        boolean flag = cursor2.moveToNext();
        String s2;
        String s3;
        s2 = null;
        s3 = null;
        if (!flag) goto _L4; else goto _L3
_L3:
        s3 = cursor2.getString(0);
        s2 = cursor2.getString(1);
          goto _L4
_L12:
        if (s1 == null) goto _L6; else goto _L5
_L5:
        ContentValues contentvalues;
        String as2[];
        contentvalues = new ContentValues();
        contentvalues.put("details", s1);
        as2 = (new String[] {
            s3
        });
        if (sqlitedatabase1 instanceof SQLiteDatabase) goto _L8; else goto _L7
_L7:
        sqlitedatabase1.update("history", contentvalues, "id=?", as2);
_L6:
        a(cursor2, sqlitedatabase1);
        return;
_L2:
        Cursor cursor1 = SQLiteInstrumentation.query((SQLiteDatabase)sqlitedatabase1, "history", as, "text=?", as1, null, null, "timestamp DESC", "1");
        cursor2 = cursor1;
          goto _L9
_L13:
        if (s2.contains(s1))
        {
            s1 = null;
            break; /* Loop/switch isn't completed */
        }
        s1 = (new StringBuilder()).append(s2).append(" : ").append(s1).toString();
        break; /* Loop/switch isn't completed */
_L8:
        SQLiteInstrumentation.update((SQLiteDatabase)sqlitedatabase1, "history", contentvalues, "id=?", as2);
          goto _L6
        Exception exception2;
        exception2;
        Exception exception;
        Cursor cursor;
        SQLiteDatabase sqlitedatabase;
        cursor = cursor2;
        sqlitedatabase = sqlitedatabase1;
        exception = exception2;
_L10:
        a(cursor, sqlitedatabase);
        throw exception;
        exception;
        cursor = null;
        sqlitedatabase = null;
        continue; /* Loop/switch isn't completed */
        Exception exception1;
        exception1;
        sqlitedatabase = sqlitedatabase1;
        exception = exception1;
        cursor = null;
        if (true) goto _L10; else goto _L4
_L4:
        if (s3 == null) goto _L6; else goto _L11
_L11:
        if (s2 != null) goto _L13; else goto _L12
    }

    final void b()
    {
        a a1 = new a(g);
        SQLiteDatabase sqlitedatabase1 = a1.getWritableDatabase();
        SQLiteDatabase sqlitedatabase = sqlitedatabase1;
        if (sqlitedatabase instanceof SQLiteDatabase) goto _L2; else goto _L1
_L1:
        sqlitedatabase.delete("history", null, null);
_L4:
        a(null, sqlitedatabase);
        return;
_L2:
        SQLiteInstrumentation.delete((SQLiteDatabase)sqlitedatabase, "history", null, null);
        if (true) goto _L4; else goto _L3
_L3:
        Exception exception;
        exception;
_L6:
        a(null, sqlitedatabase);
        throw exception;
        exception;
        sqlitedatabase = null;
        if (true) goto _L6; else goto _L5
_L5:
    }

    public final HistoryItem buildHistoryItem(int i)
    {
        a a1 = new a(g);
        SQLiteDatabase sqlitedatabase1 = a1.getReadableDatabase();
        String as[] = b;
        if (sqlitedatabase1 instanceof SQLiteDatabase) goto _L2; else goto _L1
_L1:
        Cursor cursor3 = sqlitedatabase1.query("history", as, null, null, null, null, "timestamp DESC");
        Cursor cursor2 = cursor3;
_L4:
        int j = i + 1;
        HistoryItem historyitem;
        cursor2.move(j);
        String s = cursor2.getString(0);
        String s1 = cursor2.getString(1);
        String s2 = cursor2.getString(2);
        long l = cursor2.getLong(3);
        String s3 = cursor2.getString(4);
        historyitem = new HistoryItem(new Result(s, null, null, BarcodeFormat.valueOf(s2), l), s1, s3);
        a(cursor2, sqlitedatabase1);
        return historyitem;
_L2:
        Cursor cursor1 = SQLiteInstrumentation.query((SQLiteDatabase)sqlitedatabase1, "history", as, null, null, null, null, "timestamp DESC");
        cursor2 = cursor1;
        if (true) goto _L4; else goto _L3
_L3:
        Exception exception;
        exception;
        SQLiteDatabase sqlitedatabase;
        Cursor cursor;
        sqlitedatabase = null;
        cursor = null;
_L6:
        a(cursor, sqlitedatabase);
        throw exception;
        Exception exception1;
        exception1;
        sqlitedatabase = sqlitedatabase1;
        exception = exception1;
        cursor = null;
        continue; /* Loop/switch isn't completed */
        Exception exception2;
        exception2;
        sqlitedatabase = sqlitedatabase1;
        exception = exception2;
        cursor = cursor2;
        if (true) goto _L6; else goto _L5
_L5:
    }

    public final List buildHistoryItems()
    {
        a a1;
        ArrayList arraylist;
        a1 = new a(g);
        arraylist = new ArrayList();
        SQLiteDatabase sqlitedatabase1 = a1.getReadableDatabase();
        String as[] = b;
        if (sqlitedatabase1 instanceof SQLiteDatabase) goto _L2; else goto _L1
_L1:
        Cursor cursor2 = sqlitedatabase1.query("history", as, null, null, null, null, "timestamp DESC");
        Cursor cursor = cursor2;
_L4:
        while (cursor.moveToNext()) 
        {
            String s = cursor.getString(0);
            String s1 = cursor.getString(1);
            String s2 = cursor.getString(2);
            long l = cursor.getLong(3);
            String s3 = cursor.getString(4);
            arraylist.add(new HistoryItem(new Result(s, null, null, BarcodeFormat.valueOf(s2), l), s1, s3));
        }
          goto _L3
        Exception exception2;
        exception2;
        Exception exception;
        SQLiteDatabase sqlitedatabase;
        sqlitedatabase = sqlitedatabase1;
        exception = exception2;
_L5:
        a(cursor, sqlitedatabase);
        throw exception;
_L2:
        Cursor cursor1 = SQLiteInstrumentation.query((SQLiteDatabase)sqlitedatabase1, "history", as, null, null, null, null, "timestamp DESC");
        cursor = cursor1;
          goto _L4
_L3:
        a(cursor, sqlitedatabase1);
        return arraylist;
        exception;
        sqlitedatabase = null;
        cursor = null;
          goto _L5
        Exception exception1;
        exception1;
        sqlitedatabase = sqlitedatabase1;
        exception = exception1;
        cursor = null;
          goto _L5
    }

    public final void deleteHistoryItem(int i)
    {
        a a1 = new a(g);
        SQLiteDatabase sqlitedatabase1 = a1.getWritableDatabase();
        String as[] = d;
        if (sqlitedatabase1 instanceof SQLiteDatabase) goto _L2; else goto _L1
_L1:
        Cursor cursor3 = sqlitedatabase1.query("history", as, null, null, null, null, "timestamp DESC");
        Cursor cursor2 = cursor3;
_L10:
        int j = i + 1;
        String s;
        cursor2.move(j);
        s = (new StringBuilder("id=")).append(cursor2.getString(0)).toString();
        if (sqlitedatabase1 instanceof SQLiteDatabase) goto _L4; else goto _L3
_L3:
        sqlitedatabase1.delete("history", s, null);
_L6:
        a(cursor2, sqlitedatabase1);
        return;
_L2:
        Cursor cursor1 = SQLiteInstrumentation.query((SQLiteDatabase)sqlitedatabase1, "history", as, null, null, null, null, "timestamp DESC");
        cursor2 = cursor1;
        continue; /* Loop/switch isn't completed */
_L4:
        SQLiteInstrumentation.delete((SQLiteDatabase)sqlitedatabase1, "history", s, null);
        if (true) goto _L6; else goto _L5
_L5:
        Exception exception2;
        exception2;
        Exception exception;
        Cursor cursor;
        SQLiteDatabase sqlitedatabase;
        cursor = cursor2;
        sqlitedatabase = sqlitedatabase1;
        exception = exception2;
_L8:
        a(cursor, sqlitedatabase);
        throw exception;
        exception;
        cursor = null;
        sqlitedatabase = null;
        continue; /* Loop/switch isn't completed */
        Exception exception1;
        exception1;
        sqlitedatabase = sqlitedatabase1;
        exception = exception1;
        cursor = null;
        if (true) goto _L8; else goto _L7
_L7:
        if (true) goto _L10; else goto _L9
_L9:
    }

    public final boolean hasHistoryItems()
    {
        a a1 = new a(g);
        SQLiteDatabase sqlitedatabase1 = a1.getReadableDatabase();
        String as[] = c;
        if (sqlitedatabase1 instanceof SQLiteDatabase) goto _L2; else goto _L1
_L1:
        Cursor cursor3 = sqlitedatabase1.query("history", as, null, null, null, null, null);
        Cursor cursor2 = cursor3;
_L4:
        int i;
        cursor2.moveToFirst();
        i = cursor2.getInt(0);
        Cursor cursor1;
        boolean flag;
        if (i > 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        a(cursor2, sqlitedatabase1);
        return flag;
_L2:
        cursor1 = SQLiteInstrumentation.query((SQLiteDatabase)sqlitedatabase1, "history", as, null, null, null, null, null);
        cursor2 = cursor1;
        if (true) goto _L4; else goto _L3
_L3:
        Exception exception;
        exception;
        SQLiteDatabase sqlitedatabase;
        Cursor cursor;
        sqlitedatabase = null;
        cursor = null;
_L6:
        a(cursor, sqlitedatabase);
        throw exception;
        Exception exception1;
        exception1;
        sqlitedatabase = sqlitedatabase1;
        exception = exception1;
        cursor = null;
        continue; /* Loop/switch isn't completed */
        Exception exception2;
        exception2;
        cursor = cursor2;
        sqlitedatabase = sqlitedatabase1;
        exception = exception2;
        if (true) goto _L6; else goto _L5
_L5:
    }

    public final void trimHistory()
    {
        a a1 = new a(g);
        SQLiteDatabase sqlitedatabase2 = a1.getWritableDatabase();
        String as[] = d;
        if (sqlitedatabase2 instanceof SQLiteDatabase) goto _L2; else goto _L1
_L1:
        Cursor cursor4 = sqlitedatabase2.query("history", as, null, null, null, null, "timestamp DESC");
        Cursor cursor2 = cursor4;
_L8:
        cursor2.move(2000);
_L7:
        if (!cursor2.moveToNext()) goto _L4; else goto _L3
_L3:
        String s1;
        String s = cursor2.getString(0);
        FkLogger.info(a, (new StringBuilder("Deleting scan history ID ")).append(s).toString());
        s1 = (new StringBuilder("id=")).append(s).toString();
        if (sqlitedatabase2 instanceof SQLiteDatabase) goto _L6; else goto _L5
_L5:
        sqlitedatabase2.delete("history", s1, null);
          goto _L7
        SQLiteException sqliteexception2;
        sqliteexception2;
        SQLiteException sqliteexception;
        Cursor cursor;
        SQLiteDatabase sqlitedatabase;
        cursor = cursor2;
        sqlitedatabase = sqlitedatabase2;
        sqliteexception = sqliteexception2;
_L11:
        FkLogger.warn(a, sqliteexception);
        a(cursor, sqlitedatabase);
        return;
_L2:
        Cursor cursor3 = SQLiteInstrumentation.query((SQLiteDatabase)sqlitedatabase2, "history", as, null, null, null, null, "timestamp DESC");
        cursor2 = cursor3;
          goto _L8
_L6:
        SQLiteInstrumentation.delete((SQLiteDatabase)sqlitedatabase2, "history", s1, null);
          goto _L7
        Exception exception2;
        exception2;
        Exception exception;
        SQLiteDatabase sqlitedatabase1;
        sqlitedatabase1 = sqlitedatabase2;
        exception = exception2;
_L10:
        a(cursor2, sqlitedatabase1);
        throw exception;
_L4:
        a(cursor2, sqlitedatabase2);
        return;
        exception;
        sqlitedatabase1 = null;
        cursor2 = null;
        continue; /* Loop/switch isn't completed */
        Exception exception1;
        exception1;
        sqlitedatabase1 = sqlitedatabase2;
        exception = exception1;
        cursor2 = null;
        continue; /* Loop/switch isn't completed */
        exception;
        Cursor cursor1 = cursor;
        sqlitedatabase1 = sqlitedatabase;
        cursor2 = cursor1;
        if (true) goto _L10; else goto _L9
_L9:
        sqliteexception;
        cursor = null;
        sqlitedatabase = null;
          goto _L11
        SQLiteException sqliteexception1;
        sqliteexception1;
        sqlitedatabase = sqlitedatabase2;
        sqliteexception = sqliteexception1;
        cursor = null;
          goto _L11
    }

}
