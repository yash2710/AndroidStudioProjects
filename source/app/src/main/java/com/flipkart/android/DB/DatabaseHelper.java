// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.flipkart.logging.FkLogger;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.newrelic.agent.android.instrumentation.SQLiteInstrumentation;
import java.sql.SQLException;

// Referenced classes of package com.flipkart.android.DB:
//            AutoSuggest, BrowseHistory, ComponentWidgetData, ComponentWidgetLayout, 
//            FlipkartProductInfo, ProductDiscovery, Seller, UGC, 
//            WishList

public class DatabaseHelper extends OrmLiteSqliteOpenHelper
{

    private Dao a;
    private RuntimeExceptionDao b;
    private Dao c;
    private RuntimeExceptionDao d;
    private Dao e;
    private RuntimeExceptionDao f;
    private Dao g;
    private RuntimeExceptionDao h;
    private Dao i;
    private RuntimeExceptionDao j;
    private Dao k;
    private RuntimeExceptionDao l;
    private Dao m;
    private RuntimeExceptionDao n;
    private Dao o;
    private RuntimeExceptionDao p;
    private Dao q;
    private RuntimeExceptionDao r;

    public DatabaseHelper(Context context)
    {
        super(context, "flikart_app.db", null, 1);
        a = null;
        b = null;
        c = null;
        d = null;
        e = null;
        f = null;
        g = null;
        h = null;
        i = null;
        j = null;
        k = null;
        l = null;
        m = null;
        n = null;
        o = null;
        p = null;
        q = null;
        r = null;
    }

    public void close()
    {
        super.close();
        a = null;
        c = null;
        e = null;
        g = null;
        i = null;
        k = null;
        m = null;
        o = null;
        q = null;
    }

    public Dao getAutoSuggestDao()
    {
        if (a == null)
        {
            a = getDao(com/flipkart/android/DB/AutoSuggest);
        }
        return a;
    }

    public RuntimeExceptionDao getAutoSuggestDataDao()
    {
        if (b == null)
        {
            b = getRuntimeExceptionDao(com/flipkart/android/DB/AutoSuggest);
        }
        return b;
    }

    public Dao getBrowseHistoryDao()
    {
        if (c == null)
        {
            c = getDao(com/flipkart/android/DB/BrowseHistory);
        }
        return c;
    }

    public RuntimeExceptionDao getBrowseHistoryDataDao()
    {
        if (d == null)
        {
            d = getRuntimeExceptionDao(com/flipkart/android/DB/BrowseHistory);
        }
        return d;
    }

    public RuntimeExceptionDao getCompnentWidgetDataRuntimeDao()
    {
        if (p == null)
        {
            p = getRuntimeExceptionDao(com/flipkart/android/DB/ComponentWidgetData);
        }
        return p;
    }

    public RuntimeExceptionDao getCompnentWidgetLayoutRuntimeDao()
    {
        if (r == null)
        {
            r = getRuntimeExceptionDao(com/flipkart/android/DB/ComponentWidgetLayout);
        }
        return r;
    }

    public Dao getComponentWidgetDataDao()
    {
        if (o == null)
        {
            o = getDao(com/flipkart/android/DB/ComponentWidgetData);
        }
        return o;
    }

    public Dao getComponentWidgetLayoutDao()
    {
        if (q == null)
        {
            q = getDao(com/flipkart/android/DB/ComponentWidgetLayout);
        }
        return q;
    }

    public Dao getFlipkartProductInfoDao()
    {
        if (k == null)
        {
            k = getDao(com/flipkart/android/DB/FlipkartProductInfo);
        }
        return k;
    }

    public RuntimeExceptionDao getFlipkartProductInfoDataDao()
    {
        if (l == null)
        {
            l = getRuntimeExceptionDao(com/flipkart/android/DB/FlipkartProductInfo);
        }
        return l;
    }

    public Dao getProductDiscoveryDao()
    {
        if (m == null)
        {
            m = getDao(com/flipkart/android/DB/ProductDiscovery);
        }
        return m;
    }

    public RuntimeExceptionDao getProductDiscoveryDataDao()
    {
        if (n == null)
        {
            n = getRuntimeExceptionDao(com/flipkart/android/DB/ProductDiscovery);
        }
        return n;
    }

    public Dao getSellerDao()
    {
        if (e == null)
        {
            e = getDao(com/flipkart/android/DB/Seller);
        }
        return e;
    }

    public RuntimeExceptionDao getSellerDataDao()
    {
        if (f == null)
        {
            f = getRuntimeExceptionDao(com/flipkart/android/DB/Seller);
        }
        return f;
    }

    public Dao getUGCDao()
    {
        if (g == null)
        {
            g = getDao(com/flipkart/android/DB/UGC);
        }
        return g;
    }

    public RuntimeExceptionDao getUGCDataDao()
    {
        if (h == null)
        {
            h = getRuntimeExceptionDao(com/flipkart/android/DB/UGC);
        }
        return h;
    }

    public Dao getWishListDao()
    {
        if (i == null)
        {
            i = getDao(com/flipkart/android/DB/WishList);
        }
        return i;
    }

    public RuntimeExceptionDao getWishListDataDao()
    {
        if (j == null)
        {
            j = getRuntimeExceptionDao(com/flipkart/android/DB/WishList);
        }
        return j;
    }

    public void onCreate(SQLiteDatabase sqlitedatabase, ConnectionSource connectionsource)
    {
        try
        {
            TableUtils.createTable(connectionsource, com/flipkart/android/DB/AutoSuggest);
            TableUtils.createTable(connectionsource, com/flipkart/android/DB/BrowseHistory);
            TableUtils.createTable(connectionsource, com/flipkart/android/DB/Seller);
            TableUtils.createTable(connectionsource, com/flipkart/android/DB/UGC);
            TableUtils.createTable(connectionsource, com/flipkart/android/DB/WishList);
            TableUtils.createTable(connectionsource, com/flipkart/android/DB/FlipkartProductInfo);
            TableUtils.createTable(connectionsource, com/flipkart/android/DB/ProductDiscovery);
            TableUtils.createTable(connectionsource, com/flipkart/android/DB/ComponentWidgetData);
            TableUtils.createTable(connectionsource, com/flipkart/android/DB/ComponentWidgetLayout);
            return;
        }
        catch (SQLException sqlexception)
        {
            FkLogger.printStackTrace(sqlexception);
        }
    }

    public void onUpgrade(SQLiteDatabase sqlitedatabase, ConnectionSource connectionsource, int i1, int j1)
    {
        if (!(sqlitedatabase instanceof SQLiteDatabase))
        {
            sqlitedatabase.execSQL("drop database fkdata.db");
        } else
        {
            SQLiteInstrumentation.execSQL((SQLiteDatabase)sqlitedatabase, "drop database fkdata.db");
        }
        onCreate(sqlitedatabase, connectionsource);
    }
}
