// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.tagmanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorWindow;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.text.TextUtils;
import com.google.android.gms.internal.ij;
import com.google.android.gms.internal.il;
import com.newrelic.agent.android.instrumentation.SQLiteInstrumentation;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.apache.http.impl.client.DefaultHttpClient;

// Referenced classes of package com.google.android.gms.tagmanager:
//            at, da, bh, au, 
//            ap, ab, cx

class ca
    implements at
{

    private static final String wP = String.format("CREATE TABLE IF NOT EXISTS %s ( '%s' INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, '%s' INTEGER NOT NULL, '%s' TEXT NOT NULL,'%s' INTEGER NOT NULL);", new Object[] {
        "gtm_hits", "hit_id", "hit_time", "hit_url", "hit_first_send_time"
    });
    private ij aef;
    private final b agq;
    private volatile ab agr;
    private final au ags;
    private final Context mContext;
    private final String wS;
    private long wU;
    private final int wV;

    ca(au au1, Context context)
    {
        this(au1, context, "gtm_urls.db", 2000);
    }

    ca(au au1, Context context, String s, int i)
    {
        mContext = context.getApplicationContext();
        wS = s;
        ags = au1;
        aef = il.gb();
        agq = new b(mContext, wS);
        agr = new da(new DefaultHttpClient(), mContext, new a());
        wU = 0L;
        wV = i;
    }

    private SQLiteDatabase S(String s)
    {
        SQLiteDatabase sqlitedatabase;
        try
        {
            sqlitedatabase = agq.getWritableDatabase();
        }
        catch (SQLiteException sqliteexception)
        {
            bh.D(s);
            return null;
        }
        return sqlitedatabase;
    }

    static ij a(ca ca1)
    {
        return ca1.aef;
    }

    static void a(ca ca1, long l)
    {
        ca1.y(l);
    }

    static void a(ca ca1, long l, long l1)
    {
        ca1.c(l, l1);
    }

    static String b(ca ca1)
    {
        return ca1.wS;
    }

    static Context c(ca ca1)
    {
        return ca1.mContext;
    }

    private void c(long l, long l1)
    {
        SQLiteDatabase sqlitedatabase;
        ContentValues contentvalues;
        String as[];
        sqlitedatabase = S("Error opening database for getNumStoredHits.");
        if (sqlitedatabase == null)
        {
            return;
        }
        contentvalues = new ContentValues();
        contentvalues.put("hit_first_send_time", Long.valueOf(l1));
        try
        {
            as = new String[1];
            as[0] = String.valueOf(l);
            if (!(sqlitedatabase instanceof SQLiteDatabase))
            {
                sqlitedatabase.update("gtm_hits", contentvalues, "hit_id=?", as);
                return;
            }
        }
        catch (SQLiteException sqliteexception)
        {
            bh.D((new StringBuilder("Error setting HIT_FIRST_DISPATCH_TIME for hitId: ")).append(l).toString());
            y(l);
            return;
        }
        SQLiteInstrumentation.update((SQLiteDatabase)sqlitedatabase, "gtm_hits", contentvalues, "hit_id=?", as);
        return;
    }

    private void dr()
    {
        int i = 1 + (dt() - wV);
        if (i > 0)
        {
            List list = A(i);
            bh.C((new StringBuilder("Store full, deleting ")).append(list.size()).append(" hits to make room.").toString());
            a((String[])list.toArray(new String[0]));
        }
    }

    private void g(long l, String s)
    {
        SQLiteDatabase sqlitedatabase;
        ContentValues contentvalues;
        sqlitedatabase = S("Error opening database for putHit");
        if (sqlitedatabase == null)
        {
            return;
        }
        contentvalues = new ContentValues();
        contentvalues.put("hit_time", Long.valueOf(l));
        contentvalues.put("hit_url", s);
        contentvalues.put("hit_first_send_time", Integer.valueOf(0));
        if (sqlitedatabase instanceof SQLiteDatabase)
        {
            break MISSING_BLOCK_LABEL_91;
        }
        sqlitedatabase.insert("gtm_hits", null, contentvalues);
_L1:
        SQLiteException sqliteexception;
        ags.s(false);
        return;
        try
        {
            SQLiteInstrumentation.insert((SQLiteDatabase)sqlitedatabase, "gtm_hits", null, contentvalues);
        }
        // Misplaced declaration of an exception variable
        catch (SQLiteException sqliteexception)
        {
            bh.D("Error storing hit");
            return;
        }
          goto _L1
    }

    static String mc()
    {
        return wP;
    }

    private void y(long l)
    {
        String as[] = new String[1];
        as[0] = String.valueOf(l);
        a(as);
    }

    List A(int i)
    {
        Cursor cursor;
        ArrayList arraylist;
        SQLiteDatabase sqlitedatabase;
        cursor = null;
        arraylist = new ArrayList();
        if (i <= 0)
        {
            bh.D("Invalid maxHits specified. Skipping");
            return arraylist;
        }
        sqlitedatabase = S("Error opening database for peekHitIds.");
        if (sqlitedatabase == null)
        {
            return arraylist;
        }
        String as[];
        String s;
        String s1;
        as = (new String[] {
            "hit_id"
        });
        s = String.format("%s ASC", new Object[] {
            "hit_id"
        });
        s1 = Integer.toString(i);
        if (sqlitedatabase instanceof SQLiteDatabase) goto _L2; else goto _L1
_L1:
        Cursor cursor3 = sqlitedatabase.query("gtm_hits", as, null, null, null, null, s, s1);
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
        Cursor cursor2 = SQLiteInstrumentation.query((SQLiteDatabase)sqlitedatabase, "gtm_hits", as, null, null, null, null, s, s1);
        cursor1 = cursor2;
          goto _L3
        SQLiteException sqliteexception;
        sqliteexception;
        cursor1 = null;
_L7:
        bh.D((new StringBuilder("Error in peekHits fetching hitIds: ")).append(sqliteexception.getMessage()).toString());
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

    public List B(int i)
    {
        ArrayList arraylist;
        SQLiteDatabase sqlitedatabase;
        arraylist = new ArrayList();
        sqlitedatabase = S("Error opening database for peekHits");
        if (sqlitedatabase != null) goto _L2; else goto _L1
_L1:
        ArrayList arraylist1 = arraylist;
_L13:
        return arraylist1;
_L2:
        Cursor cursor = null;
        String as[];
        String s;
        String s1;
        as = (new String[] {
            "hit_id", "hit_time", "hit_first_send_time"
        });
        s = String.format("%s ASC", new Object[] {
            "hit_id"
        });
        s1 = Integer.toString(i);
        if (sqlitedatabase instanceof SQLiteDatabase) goto _L4; else goto _L3
_L3:
        Cursor cursor7 = sqlitedatabase.query("gtm_hits", as, null, null, null, null, s, s1);
        Cursor cursor3 = cursor7;
_L11:
        ArrayList arraylist2 = new ArrayList();
        boolean flag;
        if (cursor3.moveToFirst())
        {
            do
            {
                arraylist2.add(new ap(cursor3.getLong(0), cursor3.getLong(1), cursor3.getLong(2)));
                flag = cursor3.moveToNext();
            } while (flag);
        }
        if (cursor3 != null)
        {
            cursor3.close();
        }
        String as1[];
        String s2;
        String s3;
        as1 = (new String[] {
            "hit_id", "hit_url"
        });
        s2 = String.format("%s ASC", new Object[] {
            "hit_id"
        });
        s3 = Integer.toString(i);
        if (sqlitedatabase instanceof SQLiteDatabase) goto _L6; else goto _L5
_L5:
        Cursor cursor6 = sqlitedatabase.query("gtm_hits", as1, null, null, null, null, s2, s3);
        Cursor cursor5 = cursor6;
_L14:
        if (!cursor5.moveToFirst()) goto _L8; else goto _L7
_L7:
        int j = 0;
_L28:
        if (((SQLiteCursor)cursor5).getWindow().getNumRows() <= 0) goto _L10; else goto _L9
_L9:
        ((ap)arraylist2.get(j)).R(cursor5.getString(1));
_L15:
        int k = j + 1;
        boolean flag2 = cursor5.moveToNext();
        if (flag2)
        {
            break MISSING_BLOCK_LABEL_705;
        }
_L8:
        if (cursor5 != null)
        {
            cursor5.close();
        }
        return arraylist2;
_L4:
        Cursor cursor2 = SQLiteInstrumentation.query((SQLiteDatabase)sqlitedatabase, "gtm_hits", as, null, null, null, null, s, s1);
        cursor3 = cursor2;
          goto _L11
        SQLiteException sqliteexception;
        sqliteexception;
        SQLiteException sqliteexception1;
        Cursor cursor1;
        sqliteexception1 = sqliteexception;
        cursor1 = null;
        arraylist1 = arraylist;
_L27:
        bh.D((new StringBuilder("Error in peekHits fetching hitIds: ")).append(sqliteexception1.getMessage()).toString());
        if (cursor1 == null) goto _L13; else goto _L12
_L12:
        cursor1.close();
        return arraylist1;
        Exception exception;
        exception;
_L26:
        if (cursor != null)
        {
            cursor.close();
        }
        throw exception;
_L6:
        Cursor cursor4 = SQLiteInstrumentation.query((SQLiteDatabase)sqlitedatabase, "gtm_hits", as1, null, null, null, null, s2, s3);
        cursor5 = cursor4;
          goto _L14
_L10:
        Object aobj[] = new Object[1];
        aobj[0] = Long.valueOf(((ap)arraylist2.get(j)).dl());
        bh.D(String.format("HitString for hitId %d too large.  Hit will be deleted.", aobj));
          goto _L15
        SQLiteException sqliteexception3;
        sqliteexception3;
        cursor3 = cursor5;
_L25:
        ArrayList arraylist3;
        Iterator iterator;
        bh.D((new StringBuilder("Error in peekHits fetching hit url: ")).append(sqliteexception3.getMessage()).toString());
        arraylist3 = new ArrayList();
        iterator = arraylist2.iterator();
        boolean flag1 = false;
_L21:
        if (!iterator.hasNext()) goto _L17; else goto _L16
_L16:
        ap ap1 = (ap)iterator.next();
        if (!TextUtils.isEmpty(ap1.lO())) goto _L19; else goto _L18
_L18:
        if (flag1) goto _L17; else goto _L20
_L20:
        flag1 = true;
_L19:
        arraylist3.add(ap1);
          goto _L21
        Exception exception1;
        exception1;
_L23:
        if (cursor3 != null)
        {
            cursor3.close();
        }
        throw exception1;
_L17:
        if (cursor3 != null)
        {
            cursor3.close();
        }
        return arraylist3;
        exception1;
        cursor3 = cursor5;
        if (true) goto _L23; else goto _L22
_L22:
        sqliteexception3;
        if (true) goto _L25; else goto _L24
_L24:
        exception;
        cursor = cursor3;
          goto _L26
        exception;
        cursor = cursor1;
          goto _L26
        SQLiteException sqliteexception4;
        sqliteexception4;
        sqliteexception1 = sqliteexception4;
        cursor1 = cursor3;
        arraylist1 = arraylist;
          goto _L27
        SQLiteException sqliteexception2;
        sqliteexception2;
        sqliteexception1 = sqliteexception2;
        cursor1 = cursor3;
        arraylist1 = arraylist2;
          goto _L27
        j = k;
          goto _L28
    }

    void a(String as[])
    {
        if (as != null && as.length != 0) goto _L2; else goto _L1
_L1:
        SQLiteDatabase sqlitedatabase;
        return;
_L2:
        if ((sqlitedatabase = S("Error opening database for deleteHits.")) == null) goto _L1; else goto _L3
_L3:
        String s;
        Object aobj[] = new Object[1];
        aobj[0] = TextUtils.join(",", Collections.nCopies(as.length, "?"));
        s = String.format("HIT_ID in (%s)", aobj);
        if (sqlitedatabase instanceof SQLiteDatabase)
        {
            break MISSING_BLOCK_LABEL_105;
        }
        sqlitedatabase.delete("gtm_hits", s, as);
_L4:
        au au1 = ags;
        boolean flag;
        if (dt() == 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        try
        {
            au1.s(flag);
            return;
        }
        catch (SQLiteException sqliteexception)
        {
            bh.D("Error deleting hits");
        }
        return;
        SQLiteInstrumentation.delete((SQLiteDatabase)sqlitedatabase, "gtm_hits", s, as);
          goto _L4
    }

    public void cq()
    {
        bh.C("GTM Dispatch running...");
        if (agr.cC())
        {
            List list = B(40);
            if (list.isEmpty())
            {
                bh.C("...nothing to dispatch");
                ags.s(true);
                return;
            }
            agr.g(list);
            if (mb() > 0)
            {
                cx.mQ().cq();
                return;
            }
        }
    }

    int ds()
    {
        boolean flag = true;
        long l = aef.currentTimeMillis();
        if (l > 0x5265c00L + wU)
        {
            wU = l;
            SQLiteDatabase sqlitedatabase = S("Error opening database for deleteStaleHits.");
            if (sqlitedatabase != null)
            {
                long l1 = aef.currentTimeMillis() - 0x9a7ec800L;
                String as[] = new String[flag];
                as[0] = Long.toString(l1);
                int i;
                au au1;
                if (!(sqlitedatabase instanceof SQLiteDatabase))
                {
                    i = sqlitedatabase.delete("gtm_hits", "HIT_TIME < ?", as);
                } else
                {
                    i = SQLiteInstrumentation.delete((SQLiteDatabase)sqlitedatabase, "gtm_hits", "HIT_TIME < ?", as);
                }
                au1 = ags;
                if (dt() != 0)
                {
                    flag = false;
                }
                au1.s(flag);
                return i;
            }
        }
        return 0;
    }

    int dt()
    {
        Cursor cursor;
        SQLiteDatabase sqlitedatabase;
        cursor = null;
        sqlitedatabase = S("Error opening database for getNumStoredHits.");
        if (sqlitedatabase == null)
        {
            return 0;
        }
        boolean flag = sqlitedatabase instanceof SQLiteDatabase;
        cursor = null;
        if (flag) goto _L2; else goto _L1
_L1:
        Cursor cursor2 = sqlitedatabase.rawQuery("SELECT COUNT(*) from gtm_hits", null);
        cursor = cursor2;
_L3:
        long l;
        if (!cursor.moveToFirst())
        {
            break MISSING_BLOCK_LABEL_163;
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
        Cursor cursor3 = SQLiteInstrumentation.rawQuery((SQLiteDatabase)sqlitedatabase, "SELECT COUNT(*) from gtm_hits", null);
        cursor = cursor3;
          goto _L3
        SQLiteException sqliteexception;
        sqliteexception;
        Cursor cursor1 = null;
_L7:
        bh.D("Error getting numStoredHits");
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

    public void f(long l, String s)
    {
        ds();
        dr();
        g(l, s);
    }

    int mb()
    {
        Cursor cursor;
        SQLiteDatabase sqlitedatabase;
        cursor = null;
        sqlitedatabase = S("Error opening database for getNumStoredHits.");
        if (sqlitedatabase == null)
        {
            return 0;
        }
        String as[] = {
            "hit_id", "hit_first_send_time"
        };
        if (sqlitedatabase instanceof SQLiteDatabase) goto _L2; else goto _L1
_L1:
        Cursor cursor4 = sqlitedatabase.query("gtm_hits", as, "hit_first_send_time=0", null, null, null, null);
        Cursor cursor3 = cursor4;
_L3:
        int j = cursor3.getCount();
        int i;
        i = j;
        if (cursor3 != null)
        {
            cursor3.close();
        }
_L4:
        return i;
_L2:
        Cursor cursor2 = SQLiteInstrumentation.query((SQLiteDatabase)sqlitedatabase, "gtm_hits", as, "hit_first_send_time=0", null, null, null, null);
        cursor3 = cursor2;
          goto _L3
        SQLiteException sqliteexception;
        sqliteexception;
        Cursor cursor1 = null;
_L7:
        bh.D("Error getting num untried hits");
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
        exception;
        cursor = cursor3;
        continue; /* Loop/switch isn't completed */
        exception1;
        cursor = cursor1;
        exception = exception1;
        if (true) goto _L6; else goto _L5
_L5:
        sqliteexception1;
        cursor1 = cursor3;
          goto _L7
    }


    private class b extends SQLiteOpenHelper
    {

        final ca agt;
        private boolean wX;
        private long wY;

        private void a(SQLiteDatabase sqlitedatabase)
        {
            Cursor cursor;
            HashSet hashset;
            String as[];
            int i;
            if (!(sqlitedatabase instanceof SQLiteDatabase))
            {
                cursor = sqlitedatabase.rawQuery("SELECT * FROM gtm_hits WHERE 0", null);
            } else
            {
                cursor = SQLiteInstrumentation.rawQuery((SQLiteDatabase)sqlitedatabase, "SELECT * FROM gtm_hits WHERE 0", null);
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
            if (!hashset.remove("hit_id") || !hashset.remove("hit_url") || !hashset.remove("hit_time") || !hashset.remove("hit_first_send_time"))
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
            if (wX && 0x36ee80L + wY > ca.a(agt).currentTimeMillis())
            {
                throw new SQLiteException("Database creation failed");
            }
            wX = true;
            wY = ca.a(agt).currentTimeMillis();
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
            ca.c(agt).getDatabasePath(ca.b(agt)).delete();
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
            if (!a("gtm_hits", sqlitedatabase))
            {
                String s = ca.mc();
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

        public void onUpgrade(SQLiteDatabase sqlitedatabase, int i, int j)
        {
        }

        b(Context context, String s)
        {
            agt = ca.this;
            super(context, s, null, 1);
            wY = 0L;
        }
    }


    private class a
        implements da.a
    {

        final ca agt;

        public void a(ap ap1)
        {
            ca.a(agt, ap1.dl());
        }

        public void b(ap ap1)
        {
            ca.a(agt, ap1.dl());
            bh.C((new StringBuilder("Permanent failure dispatching hitId: ")).append(ap1.dl()).toString());
        }

        public void c(ap ap1)
        {
            long l = ap1.lN();
            if (l == 0L)
            {
                ca.a(agt, ap1.dl(), ca.a(agt).currentTimeMillis());
            } else
            if (l + 0xdbba00L < ca.a(agt).currentTimeMillis())
            {
                ca.a(agt, ap1.dl());
                bh.C((new StringBuilder("Giving up on failed hitId: ")).append(ap1.dl()).toString());
                return;
            }
        }

        a()
        {
            agt = ca.this;
            super();
        }
    }

}
