// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.DB;

import android.content.Context;
import com.flipkart.logging.FkLogger;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package com.flipkart.android.DB:
//            DatabaseManager, DatabaseHelper, k, j, 
//            WishList

public class WishListDao
{

    private DatabaseHelper a;
    private Dao b;

    public WishListDao(Context context)
    {
        try
        {
            a = (new DatabaseManager()).getHelper(context);
            b = a.getWishListDao();
            return;
        }
        catch (SQLException sqlexception)
        {
            FkLogger.printStackTrace(sqlexception);
        }
    }

    public int create(WishList wishlist)
    {
        int i;
        try
        {
            i = b.createOrUpdate(wishlist).getNumLinesChanged();
        }
        catch (SQLException sqlexception)
        {
            FkLogger.printStackTrace(sqlexception);
            return 0;
        }
        return i;
    }

    public int createInBulk(ArrayList arraylist)
    {
        try
        {
            b.callBatchTasks(new k(this, arraylist));
        }
        catch (Exception exception)
        {
            FkLogger.printStackTrace(exception);
        }
        return 0;
    }

    public int delete(WishList wishlist)
    {
        int i;
        try
        {
            i = b.delete(wishlist);
        }
        catch (SQLException sqlexception)
        {
            FkLogger.printStackTrace(sqlexception);
            return 0;
        }
        return i;
    }

    public void deleteAll()
    {
        try
        {
            b.callBatchTasks(new j(this));
            return;
        }
        catch (Exception exception)
        {
            FkLogger.printStackTrace(exception);
        }
    }

    public List getAll()
    {
        List list;
        try
        {
            list = b.queryForAll();
        }
        catch (SQLException sqlexception)
        {
            FkLogger.printStackTrace(sqlexception);
            return null;
        }
        return list;
    }

    public ArrayList getAllWishListPids()
    {
        ArrayList arraylist = new ArrayList();
        List list1 = b.query(b.queryBuilder().orderBy("time", false).prepare());
        List list = list1;
_L2:
        if (list != null)
        {
            for (int i = 0; i < list.size(); i++)
            {
                arraylist.add(((WishList)list.get(i)).getPid());
            }

        }
        break; /* Loop/switch isn't completed */
        SQLException sqlexception;
        sqlexception;
        FkLogger.printStackTrace(sqlexception);
        list = null;
        if (true) goto _L2; else goto _L1
_L1:
        return arraylist;
    }

    public List getHundredWishListPids()
    {
        List list = getAll();
        ArrayList arraylist = new ArrayList();
        if (list != null)
        {
            for (int i = 0; i < list.size() && i <= 99; i++)
            {
                arraylist.add(((WishList)list.get(i)).getPid());
            }

        }
        return arraylist;
    }

    public WishList getWishListById(String s)
    {
        WishList wishlist;
        try
        {
            wishlist = (WishList)b.queryForId(s);
        }
        catch (SQLException sqlexception)
        {
            FkLogger.printStackTrace(sqlexception);
            return null;
        }
        return wishlist;
    }

    public int totalEntries()
    {
        long l;
        try
        {
            l = b.countOf();
        }
        catch (SQLException sqlexception)
        {
            FkLogger.printStackTrace(sqlexception);
            return 0;
        }
        return (int)l;
    }

    public int update(WishList wishlist)
    {
        int i;
        try
        {
            i = b.update(wishlist);
        }
        catch (SQLException sqlexception)
        {
            FkLogger.printStackTrace(sqlexception);
            return 0;
        }
        return i;
    }
}
