// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.tagmanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.text.TextUtils;
import com.google.android.gms.internal.ij;
import com.google.android.gms.internal.il;
import com.newrelic.agent.android.instrumentation.SQLiteInstrumentation;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

// Referenced classes of package com.google.android.gms.tagmanager:
//            bh

class v
    implements DataLayer.c
{

    private static final String afj = String.format("CREATE TABLE IF NOT EXISTS %s ( '%s' INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, '%s' STRING NOT NULL, '%s' BLOB NOT NULL, '%s' INTEGER NOT NULL);", new Object[] {
        "datalayer", "ID", "key", "value", "expires"
    });
    private ij aef;
    private final Executor afk;
    private a afl;
    private int afm;
    private final Context mContext;

    public v(Context context)
    {
        this(context, il.gb(), "google_tagmanager.db", 2000, ((Executor) (Executors.newSingleThreadExecutor())));
    }

    v(Context context, ij ij1, String s, int i, Executor executor)
    {
        mContext = context;
        aef = ij1;
        afm = i;
        afk = executor;
        afl = new a(mContext, s);
    }

    private SQLiteDatabase S(String s)
    {
        SQLiteDatabase sqlitedatabase;
        try
        {
            sqlitedatabase = afl.getWritableDatabase();
        }
        catch (SQLiteException sqliteexception)
        {
            bh.D(s);
            return null;
        }
        return sqlitedatabase;
    }

    static List a(v v1)
    {
        return v1.lC();
    }

    static void a(v v1, String s)
    {
        v1.bQ(s);
    }

    static void a(v v1, List list, long l)
    {
        v1.b(list, l);
    }

    static Context b(v v1)
    {
        return v1.mContext;
    }

    private void b(List list, long l)
    {
        this;
        JVM INSTR monitorenter ;
        long l1 = aef.currentTimeMillis();
        x(l1);
        _mthdo(list.size());
        c(list, l1 + l);
        lF();
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        lF();
        throw exception;
        Exception exception1;
        exception1;
        this;
        JVM INSTR monitorexit ;
        throw exception1;
    }

    private void bQ(String s)
    {
        SQLiteDatabase sqlitedatabase;
        sqlitedatabase = S("Error opening database for clearKeysWithPrefix.");
        if (sqlitedatabase == null)
        {
            return;
        }
        String as[];
        as = new String[2];
        as[0] = s;
        as[1] = (new StringBuilder()).append(s).append(".%").toString();
        if (sqlitedatabase instanceof SQLiteDatabase) goto _L2; else goto _L1
_L1:
        int k = sqlitedatabase.delete("datalayer", "key = ? OR key LIKE ?", as);
_L3:
        bh.C((new StringBuilder("Cleared ")).append(k).append(" items").toString());
        lF();
        return;
_L2:
        int i = SQLiteInstrumentation.delete((SQLiteDatabase)sqlitedatabase, "datalayer", "key = ? OR key LIKE ?", as);
        k = i;
          goto _L3
        SQLiteException sqliteexception;
        sqliteexception;
        bh.D((new StringBuilder("Error deleting entries with key prefix: ")).append(s).append(" (").append(sqliteexception).append(").").toString());
        lF();
        return;
        Exception exception;
        exception;
        lF();
        throw exception;
    }

    private void c(List list, long l)
    {
        SQLiteDatabase sqlitedatabase = S("Error opening database for writeEntryToDatabase.");
        if (sqlitedatabase != null)
        {
            Iterator iterator = list.iterator();
            while (iterator.hasNext()) 
            {
                b b1 = (b)iterator.next();
                ContentValues contentvalues = new ContentValues();
                contentvalues.put("expires", Long.valueOf(l));
                contentvalues.put("key", b1.JL);
                contentvalues.put("value", b1.afs);
                if (!(sqlitedatabase instanceof SQLiteDatabase))
                {
                    sqlitedatabase.insert("datalayer", null, contentvalues);
                } else
                {
                    SQLiteInstrumentation.insert((SQLiteDatabase)sqlitedatabase, "datalayer", null, contentvalues);
                }
            }
        }
    }

    private void _mthdo(int i)
    {
        int k = i + (lE() - afm);
        if (k > 0)
        {
            List list = dp(k);
            bh.B((new StringBuilder("DataLayer store full, deleting ")).append(list.size()).append(" entries to make room.").toString());
            g((String[])list.toArray(new String[0]));
        }
    }

    private List dp(int i)
    {
        Cursor cursor;
        ArrayList arraylist;
        SQLiteDatabase sqlitedatabase;
        cursor = null;
        arraylist = new ArrayList();
        if (i <= 0)
        {
            bh.D("Invalid maxEntries specified. Skipping.");
            return arraylist;
        }
        sqlitedatabase = S("Error opening database for peekEntryIds.");
        if (sqlitedatabase == null)
        {
            return arraylist;
        }
        String as[];
        String s;
        String s1;
        as = (new String[] {
            "ID"
        });
        s = String.format("%s ASC", new Object[] {
            "ID"
        });
        s1 = Integer.toString(i);
        if (sqlitedatabase instanceof SQLiteDatabase) goto _L2; else goto _L1
_L1:
        Cursor cursor3 = sqlitedatabase.query("datalayer", as, null, null, null, null, s, s1);
        Cursor cursor1 = cursor3;
_L3:
        boolean flag;
        if (cursor1.moveToFirst())
        {
            do
            {
                arraylist.add(String.valueOf(cursor1.getLong(0)));
                flag = cursor1.moveToNext();
            } while (flag);
        }
        if (cursor1 != null)
        {
            cursor1.close();
        }
_L4:
        return arraylist;
_L2:
        Cursor cursor2 = SQLiteInstrumentation.query((SQLiteDatabase)sqlitedatabase, "datalayer", as, null, null, null, null, s, s1);
        cursor1 = cursor2;
          goto _L3
        SQLiteException sqliteexception;
        sqliteexception;
        cursor1 = null;
_L7:
        bh.D((new StringBuilder("Error in peekEntries fetching entryIds: ")).append(sqliteexception.getMessage()).toString());
        if (cursor1 != null)
        {
            cursor1.close();
        }
          goto _L4
        Exception exception;
        exception;
_L6:
        if (cursor != null)
        {
            cursor.close();
        }
        throw exception;
        exception;
        cursor = cursor1;
        if (true) goto _L6; else goto _L5
_L5:
        sqliteexception;
          goto _L7
    }

    private List e(List list)
    {
        ArrayList arraylist = new ArrayList();
        b b1;
        for (Iterator iterator = list.iterator(); iterator.hasNext(); arraylist.add(new DataLayer.a(b1.JL, j(b1.afs))))
        {
            b1 = (b)iterator.next();
        }

        return arraylist;
    }

    private List f(List list)
    {
        ArrayList arraylist = new ArrayList();
        DataLayer.a a1;
        for (Iterator iterator = list.iterator(); iterator.hasNext(); arraylist.add(new b(a1.JL, j(a1.afh))))
        {
            a1 = (DataLayer.a)iterator.next();
        }

        return arraylist;
    }

    private void g(String as[])
    {
        if (as != null && as.length != 0) goto _L2; else goto _L1
_L1:
        SQLiteDatabase sqlitedatabase;
        return;
_L2:
        if ((sqlitedatabase = S("Error opening database for deleteEntries.")) == null) goto _L1; else goto _L3
_L3:
        String s;
        Object aobj[] = new Object[2];
        aobj[0] = "ID";
        aobj[1] = TextUtils.join(",", Collections.nCopies(as.length, "?"));
        s = String.format("%s in (%s)", aobj);
        try
        {
            if (!(sqlitedatabase instanceof SQLiteDatabase))
            {
                sqlitedatabase.delete("datalayer", s, as);
                return;
            }
        }
        catch (SQLiteException sqliteexception)
        {
            bh.D((new StringBuilder("Error deleting entries ")).append(Arrays.toString(as)).toString());
            return;
        }
        SQLiteInstrumentation.delete((SQLiteDatabase)sqlitedatabase, "datalayer", s, as);
        return;
    }

    private Object j(byte abyte0[])
    {
        ByteArrayInputStream bytearrayinputstream = new ByteArrayInputStream(abyte0);
        ObjectInputStream objectinputstream = new ObjectInputStream(bytearrayinputstream);
        Object obj = objectinputstream.readObject();
        IOException ioexception;
        IOException ioexception1;
        ClassNotFoundException classnotfoundexception;
        IOException ioexception2;
        Exception exception;
        IOException ioexception3;
        Exception exception1;
        ClassNotFoundException classnotfoundexception1;
        IOException ioexception5;
        try
        {
            objectinputstream.close();
            bytearrayinputstream.close();
        }
        catch (IOException ioexception4)
        {
            return obj;
        }
        return obj;
        ioexception5;
        objectinputstream = null;
_L8:
        if (objectinputstream == null)
        {
            break MISSING_BLOCK_LABEL_47;
        }
        objectinputstream.close();
        bytearrayinputstream.close();
        return null;
        ioexception1;
        return null;
        classnotfoundexception1;
        objectinputstream = null;
_L6:
        if (objectinputstream == null)
        {
            break MISSING_BLOCK_LABEL_69;
        }
        objectinputstream.close();
        bytearrayinputstream.close();
        return null;
        ioexception2;
        return null;
        exception1;
        objectinputstream = null;
        exception = exception1;
_L4:
        if (objectinputstream == null)
        {
            break MISSING_BLOCK_LABEL_95;
        }
        objectinputstream.close();
        bytearrayinputstream.close();
_L2:
        throw exception;
        ioexception3;
        if (true) goto _L2; else goto _L1
_L1:
        exception;
        if (true) goto _L4; else goto _L3
_L3:
        classnotfoundexception;
        if (true) goto _L6; else goto _L5
_L5:
        ioexception;
        if (true) goto _L8; else goto _L7
_L7:
    }

    private byte[] j(Object obj)
    {
        ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream();
        ObjectOutputStream objectoutputstream = new ObjectOutputStream(bytearrayoutputstream);
        byte abyte0[];
        objectoutputstream.writeObject(obj);
        abyte0 = bytearrayoutputstream.toByteArray();
        IOException ioexception;
        IOException ioexception1;
        Exception exception;
        IOException ioexception2;
        Exception exception1;
        IOException ioexception4;
        try
        {
            objectoutputstream.close();
            bytearrayoutputstream.close();
        }
        catch (IOException ioexception3)
        {
            return abyte0;
        }
        return abyte0;
        ioexception4;
        objectoutputstream = null;
_L6:
        if (objectoutputstream == null)
        {
            break MISSING_BLOCK_LABEL_51;
        }
        objectoutputstream.close();
        bytearrayoutputstream.close();
        return null;
        ioexception1;
        return null;
        exception1;
        objectoutputstream = null;
        exception = exception1;
_L4:
        if (objectoutputstream == null)
        {
            break MISSING_BLOCK_LABEL_77;
        }
        objectoutputstream.close();
        bytearrayoutputstream.close();
_L2:
        throw exception;
        ioexception2;
        if (true) goto _L2; else goto _L1
_L1:
        exception;
        if (true) goto _L4; else goto _L3
_L3:
        ioexception;
        if (true) goto _L6; else goto _L5
_L5:
    }

    private List lC()
    {
        List list;
        x(aef.currentTimeMillis());
        list = e(lD());
        lF();
        return list;
        Exception exception;
        exception;
        lF();
        throw exception;
    }

    private List lD()
    {
        ArrayList arraylist;
        Cursor cursor;
        SQLiteDatabase sqlitedatabase = S("Error opening database for loadSerialized.");
        arraylist = new ArrayList();
        if (sqlitedatabase == null)
        {
            return arraylist;
        }
        String as[] = {
            "key", "value"
        };
        Exception exception;
        if (!(sqlitedatabase instanceof SQLiteDatabase))
        {
            cursor = sqlitedatabase.query("datalayer", as, null, null, null, null, "ID", null);
        } else
        {
            cursor = SQLiteInstrumentation.query((SQLiteDatabase)sqlitedatabase, "datalayer", as, null, null, null, null, "ID", null);
        }
        for (; cursor.moveToNext(); arraylist.add(new b(cursor.getString(0), cursor.getBlob(1)))) { }
        break MISSING_BLOCK_LABEL_137;
        exception;
        cursor.close();
        throw exception;
        cursor.close();
        return arraylist;
    }

    private int lE()
    {
        Cursor cursor;
        SQLiteDatabase sqlitedatabase;
        cursor = null;
        sqlitedatabase = S("Error opening database for getNumStoredEntries.");
        if (sqlitedatabase == null)
        {
            return 0;
        }
        boolean flag = sqlitedatabase instanceof SQLiteDatabase;
        cursor = null;
        if (flag) goto _L2; else goto _L1
_L1:
        Cursor cursor2 = sqlitedatabase.rawQuery("SELECT COUNT(*) from datalayer", null);
        cursor = cursor2;
_L3:
        long l;
        if (!cursor.moveToFirst())
        {
            break MISSING_BLOCK_LABEL_164;
        }
        l = cursor.getLong(0);
        int i = (int)l;
_L8:
        if (cursor != null)
        {
            cursor.close();
        }
_L4:
        return i;
_L2:
        Cursor cursor3 = SQLiteInstrumentation.rawQuery((SQLiteDatabase)sqlitedatabase, "SELECT COUNT(*) from datalayer", null);
        cursor = cursor3;
          goto _L3
        SQLiteException sqliteexception;
        sqliteexception;
        Cursor cursor1 = null;
_L7:
        bh.D("Error getting numStoredEntries");
        Exception exception;
        Exception exception1;
        SQLiteException sqliteexception1;
        if (cursor1 != null)
        {
            cursor1.close();
            i = 0;
        } else
        {
            i = 0;
        }
          goto _L4
        exception;
_L6:
        if (cursor != null)
        {
            cursor.close();
        }
        throw exception;
        exception1;
        cursor = cursor1;
        exception = exception1;
        if (true) goto _L6; else goto _L5
_L5:
        sqliteexception1;
        cursor1 = cursor;
          goto _L7
        i = 0;
          goto _L8
    }

    private void lF()
    {
        try
        {
            afl.close();
            return;
        }
        catch (SQLiteException sqliteexception)
        {
            return;
        }
    }

    static String lG()
    {
        return afj;
    }

    private void x(long l)
    {
        SQLiteDatabase sqlitedatabase;
        sqlitedatabase = S("Error opening database for deleteOlderThan.");
        if (sqlitedatabase == null)
        {
            return;
        }
        String as[];
        int k;
        as = new String[1];
        as[0] = Long.toString(l);
        if (sqlitedatabase instanceof SQLiteDatabase)
        {
            break MISSING_BLOCK_LABEL_84;
        }
        k = sqlitedatabase.delete("datalayer", "expires <= ?", as);
_L1:
        SQLiteException sqliteexception;
        bh.C((new StringBuilder("Deleted ")).append(k).append(" expired items").toString());
        return;
        int i;
        try
        {
            i = SQLiteInstrumentation.delete((SQLiteDatabase)sqlitedatabase, "datalayer", "expires <= ?", as);
        }
        // Misplaced declaration of an exception variable
        catch (SQLiteException sqliteexception)
        {
            bh.D("Error deleting old entries.");
            return;
        }
        k = i;
          goto _L1
    }

    public void a(DataLayer.c.a a1)
    {
        afk.execute(new _cls2(a1));
    }

    public void a(List list, long l)
    {
        List list1 = f(list);
        afk.execute(new _cls1(list1, l));
    }

    public void bP(String s)
    {
        afk.execute(new _cls3(s));
    }


    private class a extends SQLiteOpenHelper
    {

        final v afp;

        private void a(SQLiteDatabase sqlitedatabase)
        {
            Cursor cursor;
            HashSet hashset;
            String as[];
            int i;
            if (!(sqlitedatabase instanceof SQLiteDatabase))
            {
                cursor = sqlitedatabase.rawQuery("SELECT * FROM datalayer WHERE 0", null);
            } else
            {
                cursor = SQLiteInstrumentation.rawQuery((SQLiteDatabase)sqlitedatabase, "SELECT * FROM datalayer WHERE 0", null);
            }
            hashset = new HashSet();
            as = cursor.getColumnNames();
            i = 0;
_L2:
            if (i >= as.length)
            {
                break; /* Loop/switch isn't completed */
            }
            hashset.add(as[i]);
            i++;
            if (true) goto _L2; else goto _L1
_L1:
            cursor.close();
            if (!hashset.remove("key") || !hashset.remove("value") || !hashset.remove("ID") || !hashset.remove("expires"))
            {
                throw new SQLiteException("Database column missing");
            }
            break MISSING_BLOCK_LABEL_145;
            Exception exception;
            exception;
            cursor.close();
            throw exception;
            if (!hashset.isEmpty())
            {
                throw new SQLiteException("Database has extra columns");
            } else
            {
                return;
            }
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
            bh.D((new StringBuilder("Error querying for table ")).append(s).toString());
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
            SQLiteDatabase sqlitedatabase1 = super.getWritableDatabase();
            SQLiteDatabase sqlitedatabase = sqlitedatabase1;
_L2:
            if (sqlitedatabase == null)
            {
                sqlitedatabase = super.getWritableDatabase();
            }
            return sqlitedatabase;
            SQLiteException sqliteexception;
            sqliteexception;
            v.b(afp).getDatabasePath("google_tagmanager.db").delete();
            sqlitedatabase = null;
            if (true) goto _L2; else goto _L1
_L1:
        }

        public void onCreate(SQLiteDatabase sqlitedatabase)
        {
            ak.N(sqlitedatabase.getPath());
        }

        public void onOpen(SQLiteDatabase sqlitedatabase)
        {
            Cursor cursor;
            if (android.os.Build.VERSION.SDK_INT >= 15)
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
            if (!a("datalayer", sqlitedatabase))
            {
                String s = v.lG();
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

        public void onUpgrade(SQLiteDatabase sqlitedatabase, int i, int k)
        {
        }

        a(Context context, String s)
        {
            afp = v.this;
            super(context, s, null, 1);
        }
    }


    private class b
    {

        final String JL;
        final byte afs[];

        public String toString()
        {
            return (new StringBuilder("KeyAndSerialized: key = ")).append(JL).append(" serialized hash = ").append(Arrays.hashCode(afs)).toString();
        }

        b(String s, byte abyte0[])
        {
            JL = s;
            afs = abyte0;
        }
    }


    private class _cls2
        implements Runnable
    {

        final v afp;
        final DataLayer.c.a afq;

        public void run()
        {
            afq.d(v.a(afp));
        }

        _cls2(DataLayer.c.a a1)
        {
            afp = v.this;
            afq = a1;
            super();
        }
    }


    private class _cls1
        implements Runnable
    {

        final List afn;
        final long afo;
        final v afp;

        public void run()
        {
            v.a(afp, afn, afo);
        }

        _cls1(List list, long l)
        {
            afp = v.this;
            afn = list;
            afo = l;
            super();
        }
    }


    private class _cls3
        implements Runnable
    {

        final v afp;
        final String afr;

        public void run()
        {
            v.a(afp, afr);
        }

        _cls3(String s)
        {
            afp = v.this;
            afr = s;
            super();
        }
    }

}
