// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.analytics;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorWindow;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.text.TextUtils;
import com.google.android.gms.internal.fd;
import com.newrelic.agent.android.instrumentation.SQLiteInstrumentation;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.http.impl.client.DefaultHttpClient;

// Referenced classes of package com.google.android.gms.analytics:
//            d, ah, aa, e, 
//            y, x, n, ab, 
//            GoogleAnalytics, i

class ac
    implements d
{

    private static final String wP = String.format("CREATE TABLE IF NOT EXISTS %s ( '%s' INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, '%s' INTEGER NOT NULL, '%s' TEXT NOT NULL, '%s' TEXT NOT NULL, '%s' INTEGER);", new Object[] {
        "hits2", "hit_id", "hit_time", "hit_url", "hit_string", "hit_app_id"
    });
    private final Context mContext;
    private final e uc;
    private i uu;
    private final a wQ;
    private volatile n wR;
    private final String wS;
    private ab wT;
    private long wU;
    private final int wV;

    ac(e e1, Context context)
    {
        this(e1, context, "google_analytics_v4.db", 2000);
    }

    ac(e e1, Context context, String s, int j)
    {
        mContext = context.getApplicationContext();
        wS = s;
        uc = e1;
        uu = new _cls1();
        wQ = new a(mContext, wS);
        wR = new ah(new DefaultHttpClient(), mContext);
        wU = 0L;
        wV = j;
    }

    private SQLiteDatabase S(String s)
    {
        SQLiteDatabase sqlitedatabase;
        try
        {
            sqlitedatabase = wQ.getWritableDatabase();
        }
        catch (SQLiteException sqliteexception)
        {
            aa.D(s);
            return null;
        }
        return sqlitedatabase;
    }

    static i a(ac ac1)
    {
        return ac1.uu;
    }

    private void a(Map map, long l1, String s)
    {
        SQLiteDatabase sqlitedatabase;
        ContentValues contentvalues;
        long l2;
        sqlitedatabase = S("Error opening database for putHit");
        if (sqlitedatabase == null)
        {
            return;
        }
        contentvalues = new ContentValues();
        contentvalues.put("hit_string", v(map));
        contentvalues.put("hit_time", Long.valueOf(l1));
        l2 = 0L;
        if (!map.containsKey("AppUID"))
        {
            break MISSING_BLOCK_LABEL_79;
        }
        long l3 = Long.parseLong((String)map.get("AppUID"));
        l2 = l3;
_L3:
        contentvalues.put("hit_app_id", Long.valueOf(l2));
        if (s == null)
        {
            s = "http://www.google-analytics.com/collect";
        }
        if (s.length() == 0)
        {
            aa.D("Empty path: not sending hit");
            return;
        }
        contentvalues.put("hit_url", s);
        if (sqlitedatabase instanceof SQLiteDatabase)
        {
            break MISSING_BLOCK_LABEL_161;
        }
        sqlitedatabase.insert("hits2", null, contentvalues);
_L1:
        SQLiteException sqliteexception;
        uc.s(false);
        return;
        try
        {
            SQLiteInstrumentation.insert((SQLiteDatabase)sqlitedatabase, "hits2", null, contentvalues);
        }
        // Misplaced declaration of an exception variable
        catch (SQLiteException sqliteexception)
        {
            aa.D("Error storing hit");
            return;
        }
          goto _L1
        NumberFormatException numberformatexception;
        numberformatexception;
        if (true) goto _L3; else goto _L2
_L2:
    }

    private void a(Map map, Collection collection)
    {
label0:
        {
            String s = "&_v".substring(1);
            if (collection == null)
            {
                break label0;
            }
            Iterator iterator = collection.iterator();
            fd fd1;
            do
            {
                if (!iterator.hasNext())
                {
                    break label0;
                }
                fd1 = (fd)iterator.next();
            } while (!"appendVersion".equals(fd1.getId()));
            map.put(s, fd1.getValue());
        }
    }

    static String b(ac ac1)
    {
        return ac1.wS;
    }

    static Context c(ac ac1)
    {
        return ac1.mContext;
    }

    private void dr()
    {
        int j = 1 + (dt() - wV);
        if (j > 0)
        {
            List list = A(j);
            aa.C((new StringBuilder("Store full, deleting ")).append(list.size()).append(" hits to make room.").toString());
            a((String[])list.toArray(new String[0]));
        }
    }

    static String du()
    {
        return wP;
    }

    static String v(Map map)
    {
        ArrayList arraylist = new ArrayList(map.size());
        java.util.Map.Entry entry;
        for (Iterator iterator = map.entrySet().iterator(); iterator.hasNext(); arraylist.add((new StringBuilder()).append(y.encode((String)entry.getKey())).append("=").append(y.encode((String)entry.getValue())).toString()))
        {
            entry = (java.util.Map.Entry)iterator.next();
        }

        return TextUtils.join("&", arraylist);
    }

    List A(int j)
    {
        Cursor cursor;
        ArrayList arraylist;
        SQLiteDatabase sqlitedatabase;
        cursor = null;
        arraylist = new ArrayList();
        if (j <= 0)
        {
            aa.D("Invalid maxHits specified. Skipping");
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
        s1 = Integer.toString(j);
        if (sqlitedatabase instanceof SQLiteDatabase) goto _L2; else goto _L1
_L1:
        Cursor cursor3 = sqlitedatabase.query("hits2", as, null, null, null, null, s, s1);
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
        Cursor cursor2 = SQLiteInstrumentation.query((SQLiteDatabase)sqlitedatabase, "hits2", as, null, null, null, null, s, s1);
        cursor1 = cursor2;
          goto _L3
        SQLiteException sqliteexception;
        sqliteexception;
        cursor1 = null;
_L7:
        aa.D((new StringBuilder("Error in peekHits fetching hitIds: ")).append(sqliteexception.getMessage()).toString());
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

    public List B(int j)
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
            "hit_id", "hit_time"
        });
        s = String.format("%s ASC", new Object[] {
            "hit_id"
        });
        s1 = Integer.toString(j);
        if (sqlitedatabase instanceof SQLiteDatabase) goto _L4; else goto _L3
_L3:
        Cursor cursor7 = sqlitedatabase.query("hits2", as, null, null, null, null, s, s1);
        Cursor cursor3 = cursor7;
_L11:
        ArrayList arraylist2 = new ArrayList();
        boolean flag;
        if (cursor3.moveToFirst())
        {
            do
            {
                arraylist2.add(new x(null, cursor3.getLong(0), cursor3.getLong(1)));
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
            "hit_id", "hit_string", "hit_url"
        });
        s2 = String.format("%s ASC", new Object[] {
            "hit_id"
        });
        s3 = Integer.toString(j);
        if (sqlitedatabase instanceof SQLiteDatabase) goto _L6; else goto _L5
_L5:
        Cursor cursor6 = sqlitedatabase.query("hits2", as1, null, null, null, null, s2, s3);
        Cursor cursor5 = cursor6;
_L14:
        if (!cursor5.moveToFirst()) goto _L8; else goto _L7
_L7:
        int k = 0;
_L28:
        if (((SQLiteCursor)cursor5).getWindow().getNumRows() <= 0) goto _L10; else goto _L9
_L9:
        ((x)arraylist2.get(k)).Q(cursor5.getString(1));
        ((x)arraylist2.get(k)).R(cursor5.getString(2));
_L15:
        int i1 = k + 1;
        boolean flag2 = cursor5.moveToNext();
        if (flag2)
        {
            break MISSING_BLOCK_LABEL_723;
        }
_L8:
        if (cursor5 != null)
        {
            cursor5.close();
        }
        return arraylist2;
_L4:
        Cursor cursor2 = SQLiteInstrumentation.query((SQLiteDatabase)sqlitedatabase, "hits2", as, null, null, null, null, s, s1);
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
        aa.D((new StringBuilder("Error in peekHits fetching hitIds: ")).append(sqliteexception1.getMessage()).toString());
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
        Cursor cursor4 = SQLiteInstrumentation.query((SQLiteDatabase)sqlitedatabase, "hits2", as1, null, null, null, null, s2, s3);
        cursor5 = cursor4;
          goto _L14
_L10:
        Object aobj[] = new Object[1];
        aobj[0] = Long.valueOf(((x)arraylist2.get(k)).dl());
        aa.D(String.format("HitString for hitId %d too large.  Hit will be deleted.", aobj));
          goto _L15
        SQLiteException sqliteexception3;
        sqliteexception3;
        cursor3 = cursor5;
_L25:
        ArrayList arraylist3;
        Iterator iterator;
        aa.D((new StringBuilder("Error in peekHits fetching hitString: ")).append(sqliteexception3.getMessage()).toString());
        arraylist3 = new ArrayList();
        iterator = arraylist2.iterator();
        boolean flag1 = false;
_L21:
        if (!iterator.hasNext()) goto _L17; else goto _L16
_L16:
        x x1 = (x)iterator.next();
        if (!TextUtils.isEmpty(x1.dk())) goto _L19; else goto _L18
_L18:
        if (flag1) goto _L17; else goto _L20
_L20:
        flag1 = true;
_L19:
        arraylist3.add(x1);
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
        k = i1;
          goto _L28
    }

    public void a(Map map, long l1, String s, Collection collection)
    {
        ds();
        dr();
        a(map, collection);
        a(map, l1, s);
    }

    void a(String as[])
    {
        if (as != null && as.length != 0) goto _L2; else goto _L1
_L1:
        aa.D("Empty hitIds passed to deleteHits.");
_L4:
        return;
_L2:
        SQLiteDatabase sqlitedatabase = S("Error opening database for deleteHits.");
        if (sqlitedatabase == null) goto _L4; else goto _L3
_L3:
        String s;
        Object aobj[] = new Object[1];
        aobj[0] = TextUtils.join(",", Collections.nCopies(as.length, "?"));
        s = String.format("HIT_ID in (%s)", aobj);
        if (sqlitedatabase instanceof SQLiteDatabase)
        {
            break MISSING_BLOCK_LABEL_125;
        }
        sqlitedatabase.delete("hits2", s, as);
_L5:
        e e1 = uc;
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
            e1.s(flag);
            return;
        }
        catch (SQLiteException sqliteexception)
        {
            aa.D((new StringBuilder("Error deleting hits ")).append(as).toString());
        }
        return;
        SQLiteInstrumentation.delete((SQLiteDatabase)sqlitedatabase, "hits2", s, as);
          goto _L5
    }

    void b(Collection collection)
    {
        if (collection == null || collection.isEmpty())
        {
            aa.D("Empty/Null collection passed to deleteHits.");
            return;
        }
        String as[] = new String[collection.size()];
        Iterator iterator = collection.iterator();
        int k;
        for (int j = 0; iterator.hasNext(); j = k)
        {
            x x1 = (x)iterator.next();
            k = j + 1;
            as[j] = String.valueOf(x1.dl());
        }

        a(as);
    }

    public void cq()
    {
        boolean flag = true;
        aa.C("Dispatch running...");
        if (!wR.cC())
        {
            return;
        }
        List list = B(40);
        if (list.isEmpty())
        {
            aa.C("...nothing to dispatch");
            uc.s(flag);
            return;
        }
        if (wT == null)
        {
            wT = new ab("_t=dispatch&_v=ma4.0.2", flag);
        }
        int j;
        if (dt() > list.size())
        {
            flag = false;
        }
        j = wR.a(list, wT, flag);
        aa.C((new StringBuilder("sent ")).append(j).append(" of ").append(list.size()).append(" hits").toString());
        b(list.subList(0, Math.min(j, list.size())));
        if (j == list.size() && dt() > 0)
        {
            GoogleAnalytics.getInstance(mContext).dispatchLocalHits();
            return;
        } else
        {
            wT = null;
            return;
        }
    }

    public n cr()
    {
        return wR;
    }

    int ds()
    {
        boolean flag = true;
        long l1 = uu.currentTimeMillis();
        if (l1 > 0x5265c00L + wU)
        {
            wU = l1;
            SQLiteDatabase sqlitedatabase = S("Error opening database for deleteStaleHits.");
            if (sqlitedatabase != null)
            {
                long l2 = uu.currentTimeMillis() - 0x9a7ec800L;
                String as[] = new String[flag];
                as[0] = Long.toString(l2);
                int j;
                e e1;
                if (!(sqlitedatabase instanceof SQLiteDatabase))
                {
                    j = sqlitedatabase.delete("hits2", "HIT_TIME < ?", as);
                } else
                {
                    j = SQLiteInstrumentation.delete((SQLiteDatabase)sqlitedatabase, "hits2", "HIT_TIME < ?", as);
                }
                e1 = uc;
                if (dt() != 0)
                {
                    flag = false;
                }
                e1.s(flag);
                return j;
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
        Cursor cursor2 = sqlitedatabase.rawQuery("SELECT COUNT(*) from hits2", null);
        cursor = cursor2;
_L3:
        long l1;
        if (!cursor.moveToFirst())
        {
            break MISSING_BLOCK_LABEL_164;
        }
        l1 = cursor.getLong(0);
        int j = (int)l1;
_L8:
        if (cursor != null)
        {
            cursor.close();
        }
_L4:
        return j;
_L2:
        Cursor cursor3 = SQLiteInstrumentation.rawQuery((SQLiteDatabase)sqlitedatabase, "SELECT COUNT(*) from hits2", null);
        cursor = cursor3;
          goto _L3
        SQLiteException sqliteexception;
        sqliteexception;
        Cursor cursor1 = null;
_L7:
        aa.D("Error getting numStoredHits");
        Exception exception;
        Exception exception1;
        SQLiteException sqliteexception1;
        if (cursor1 != null)
        {
            cursor1.close();
            j = 0;
        } else
        {
            j = 0;
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
        j = 0;
          goto _L8
    }

    public void l(long l1)
    {
        SQLiteDatabase sqlitedatabase = S("Error opening database for clearHits");
        if (sqlitedatabase != null)
        {
            e e1;
            boolean flag;
            if (l1 == 0L)
            {
                if (!(sqlitedatabase instanceof SQLiteDatabase))
                {
                    sqlitedatabase.delete("hits2", null, null);
                } else
                {
                    SQLiteInstrumentation.delete((SQLiteDatabase)sqlitedatabase, "hits2", null, null);
                }
            } else
            {
                String as[] = new String[1];
                as[0] = Long.valueOf(l1).toString();
                if (!(sqlitedatabase instanceof SQLiteDatabase))
                {
                    sqlitedatabase.delete("hits2", "hit_app_id = ?", as);
                } else
                {
                    SQLiteInstrumentation.delete((SQLiteDatabase)sqlitedatabase, "hits2", "hit_app_id = ?", as);
                }
            }
            e1 = uc;
            if (dt() == 0)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            e1.s(flag);
        }
    }


    private class _cls1
        implements i
    {

        final ac wW;

        public long currentTimeMillis()
        {
            return System.currentTimeMillis();
        }

        _cls1()
        {
            wW = ac.this;
            super();
        }
    }


    private class a extends SQLiteOpenHelper
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

        a(Context context, String s)
        {
            wW = ac.this;
            super(context, s, null, 1);
            wY = 0L;
        }
    }

}
