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
//            DatabaseManager, DatabaseHelper, b, ComponentWidgetData

public class ComponentWidgetDataDao
{

    private DatabaseHelper a;
    private Dao b;

    public ComponentWidgetDataDao(Context context)
    {
        try
        {
            a = (new DatabaseManager()).getHelper(context);
            b = a.getComponentWidgetDataDao();
            return;
        }
        catch (SQLException sqlexception)
        {
            FkLogger.printStackTrace(sqlexception);
        }
    }

    public int create(ComponentWidgetData componentwidgetdata)
    {
        int i;
        try
        {
            i = b.createOrUpdate(componentwidgetdata).getNumLinesChanged();
        }
        catch (SQLException sqlexception)
        {
            FkLogger.printStackTrace(sqlexception);
            return 0;
        }
        return i;
    }

    public int delete(ComponentWidgetData componentwidgetdata)
    {
        int i;
        try
        {
            i = b.delete(componentwidgetdata);
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
            b.callBatchTasks(new b(this));
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

    public ComponentWidgetData getComponentWidgetDataById(String s, String s1)
    {
        ComponentWidgetData componentwidgetdata;
        try
        {
            componentwidgetdata = (ComponentWidgetData)b.queryForId((new StringBuilder()).append(s).append("/").append(s1).toString());
        }
        catch (SQLException sqlexception)
        {
            FkLogger.printStackTrace(sqlexception);
            return null;
        }
        return componentwidgetdata;
    }

    public int update(ComponentWidgetData componentwidgetdata)
    {
        int i;
        try
        {
            i = b.update(componentwidgetdata);
        }
        catch (SQLException sqlexception)
        {
            FkLogger.printStackTrace(sqlexception);
            return 0;
        }
        return i;
    }
}
