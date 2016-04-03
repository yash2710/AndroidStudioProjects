// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.DB;

import android.content.Context;
import com.flipkart.logging.FkLogger;
import com.j256.ormlite.dao.Dao;
import java.sql.SQLException;
import java.util.List;

// Referenced classes of package com.flipkart.android.DB:
//            DatabaseManager, DatabaseHelper, g, ProductDiscovery

public class ProductDiscoveryDao
{

    private DatabaseHelper a;
    private Dao b;

    public ProductDiscoveryDao(Context context)
    {
        try
        {
            a = (new DatabaseManager()).getHelper(context);
            b = a.getProductDiscoveryDao();
            return;
        }
        catch (SQLException sqlexception)
        {
            FkLogger.printStackTrace(sqlexception);
        }
    }

    public int create(ProductDiscovery productdiscovery)
    {
        int i;
        try
        {
            i = b.createOrUpdate(productdiscovery).getNumLinesChanged();
        }
        catch (SQLException sqlexception)
        {
            FkLogger.printStackTrace(sqlexception);
            return 0;
        }
        return i;
    }

    public int delete(ProductDiscovery productdiscovery)
    {
        int i;
        try
        {
            i = b.delete(productdiscovery);
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
            b.callBatchTasks(new g(this));
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

    public ProductDiscovery getProductDiscoveryById(String s)
    {
        ProductDiscovery productdiscovery;
        try
        {
            productdiscovery = (ProductDiscovery)b.queryForId(s);
        }
        catch (SQLException sqlexception)
        {
            FkLogger.printStackTrace(sqlexception);
            return null;
        }
        return productdiscovery;
    }

    public int update(ProductDiscovery productdiscovery)
    {
        int i;
        try
        {
            i = b.update(productdiscovery);
        }
        catch (SQLException sqlexception)
        {
            FkLogger.printStackTrace(sqlexception);
            return 0;
        }
        return i;
    }
}
