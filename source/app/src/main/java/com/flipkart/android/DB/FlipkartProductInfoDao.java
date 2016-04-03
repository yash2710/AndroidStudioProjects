// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.DB;

import android.content.Context;
import com.flipkart.logging.FkLogger;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.Where;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package com.flipkart.android.DB:
//            DatabaseManager, DatabaseHelper, FlipkartProductInfo, d, 
//            f, e

public class FlipkartProductInfoDao
{

    private DatabaseHelper a;
    private Dao b;

    public FlipkartProductInfoDao(Context context)
    {
        try
        {
            a = (new DatabaseManager()).getHelper(context);
            b = a.getFlipkartProductInfoDao();
            return;
        }
        catch (SQLException sqlexception)
        {
            FkLogger.printStackTrace(sqlexception);
        }
    }

    public int create(FlipkartProductInfo flipkartproductinfo, boolean flag)
    {
        if (flipkartproductinfo == null) goto _L2; else goto _L1
_L1:
        if (!flag) goto _L4; else goto _L3
_L3:
        int j = b.createOrUpdate(flipkartproductinfo).getNumLinesChanged();
        return j;
        SQLException sqlexception1;
        sqlexception1;
        FkLogger.printStackTrace(sqlexception1);
_L2:
        return 0;
_L4:
        int i;
        if (getFlipkartProductInfoById(flipkartproductinfo.getPid()) != null)
        {
            continue; /* Loop/switch isn't completed */
        }
        i = b.create(flipkartproductinfo);
        return i;
        SQLException sqlexception;
        sqlexception;
        FkLogger.printStackTrace(sqlexception);
        if (true) goto _L2; else goto _L5
_L5:
    }

    public int createInBulk(ArrayList arraylist, boolean flag)
    {
        if (arraylist != null)
        {
            try
            {
                b.callBatchTasks(new d(this, arraylist, flag));
            }
            catch (Exception exception)
            {
                FkLogger.printStackTrace(exception);
            }
        }
        return 0;
    }

    public int delete(FlipkartProductInfo flipkartproductinfo)
    {
        int i;
        try
        {
            i = b.delete(flipkartproductinfo);
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
            b.callBatchTasks(new f(this));
            return;
        }
        catch (Exception exception)
        {
            FkLogger.printStackTrace(exception);
        }
    }

    public void deleteFlipkartProductInfoNotInArgumentsPids(List list)
    {
        List list1;
        try
        {
            list1 = b.queryBuilder().where().notIn("pid", list).query();
        }
        catch (SQLException sqlexception)
        {
            FkLogger.printStackTrace(sqlexception);
            return;
        }
        catch (Exception exception)
        {
            FkLogger.printStackTrace(exception);
            return;
        }
        if (list1 == null)
        {
            break MISSING_BLOCK_LABEL_58;
        }
        if (list1.size() > 0)
        {
            b.callBatchTasks(new e(this, list1));
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

    public FlipkartProductInfo getFlipkartProductInfoById(String s)
    {
        FlipkartProductInfo flipkartproductinfo;
        try
        {
            flipkartproductinfo = (FlipkartProductInfo)b.queryForId(s);
        }
        catch (SQLException sqlexception)
        {
            FkLogger.printStackTrace(sqlexception);
            return null;
        }
        return flipkartproductinfo;
    }

    public List getFlipkartProductInfoList(ArrayList arraylist)
    {
        List list;
        try
        {
            list = b.queryBuilder().where().in("pid", arraylist).query();
        }
        catch (SQLException sqlexception)
        {
            FkLogger.printStackTrace(sqlexception);
            return null;
        }
        return list;
    }

    public int update(FlipkartProductInfo flipkartproductinfo)
    {
        int i;
        try
        {
            i = b.update(flipkartproductinfo);
        }
        catch (SQLException sqlexception)
        {
            FkLogger.printStackTrace(sqlexception);
            return 0;
        }
        return i;
    }
}
