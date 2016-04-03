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
//            DatabaseManager, DatabaseHelper, BrowseHistory, a

public class BrowseHistoryDao
{

    private DatabaseHelper a;
    private Dao b;

    public BrowseHistoryDao(Context context)
    {
        try
        {
            a = (new DatabaseManager()).getHelper(context);
            b = a.getBrowseHistoryDao();
            return;
        }
        catch (SQLException sqlexception)
        {
            FkLogger.printStackTrace(sqlexception);
        }
    }

    public int create(BrowseHistory browsehistory)
    {
        if (getBrowseHistoryById(browsehistory.getPid()) == null && (int)b.countOf() > 19)
        {
            delete((BrowseHistory)b.query(b.queryBuilder().orderBy("time", true).prepare()).get(0));
        }
        int i;
        try
        {
            i = b.createOrUpdate(browsehistory).getNumLinesChanged();
        }
        catch (SQLException sqlexception1)
        {
            try
            {
                FkLogger.printStackTrace(sqlexception1);
            }
            catch (SQLException sqlexception) { }
            return 0;
        }
        return i;
    }

    public int delete(BrowseHistory browsehistory)
    {
        int i;
        try
        {
            i = b.delete(browsehistory);
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
            b.callBatchTasks(new a(this));
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

    public ArrayList getAllBrowseHistoryPids()
    {
        List list1 = b.query(b.queryBuilder().orderBy("time", false).prepare());
        List list = list1;
_L2:
        ArrayList arraylist;
        arraylist = new ArrayList();
        int i = 0;
        if (list != null)
        {
            for (; i < list.size(); i++)
            {
                arraylist.add(((BrowseHistory)list.get(i)).getPid());
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

    public BrowseHistory getBrowseHistoryById(String s)
    {
        BrowseHistory browsehistory;
        try
        {
            browsehistory = (BrowseHistory)b.queryForId(s);
        }
        catch (SQLException sqlexception)
        {
            FkLogger.printStackTrace(sqlexception);
            return null;
        }
        return browsehistory;
    }

    public String getLastEntry()
    {
        List list = b.query(b.queryBuilder().orderBy("time", false).prepare());
        if (list == null)
        {
            break MISSING_BLOCK_LABEL_62;
        }
        String s;
        if (list.size() <= 0)
        {
            break MISSING_BLOCK_LABEL_62;
        }
        s = ((BrowseHistory)list.get(0)).getPid();
        return s;
        SQLException sqlexception;
        sqlexception;
        FkLogger.printStackTrace(sqlexception);
        return "";
    }

    public int update(BrowseHistory browsehistory)
    {
        int i;
        try
        {
            i = b.update(browsehistory);
        }
        catch (SQLException sqlexception)
        {
            FkLogger.printStackTrace(sqlexception);
            return 0;
        }
        return i;
    }
}
