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
import java.util.List;

// Referenced classes of package com.flipkart.android.DB:
//            DatabaseManager, DatabaseHelper, c, ComponentWidgetLayout

public class ComponentWidgetLayoutDao
{

    private DatabaseHelper a;
    private Dao b;

    public ComponentWidgetLayoutDao(Context context)
    {
        try
        {
            a = (new DatabaseManager()).getHelper(context);
            b = a.getComponentWidgetLayoutDao();
            return;
        }
        catch (SQLException sqlexception)
        {
            FkLogger.printStackTrace(sqlexception);
        }
    }

    public int create(ComponentWidgetLayout componentwidgetlayout)
    {
        int i;
        try
        {
            i = b.createOrUpdate(componentwidgetlayout).getNumLinesChanged();
        }
        catch (SQLException sqlexception)
        {
            FkLogger.printStackTrace(sqlexception);
            return 0;
        }
        return i;
    }

    public int delete(ComponentWidgetLayout componentwidgetlayout)
    {
        int i;
        try
        {
            i = b.delete(componentwidgetlayout);
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
            b.callBatchTasks(new c(this));
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

    public ComponentWidgetLayout getComponentWidgetLayoutById(String s)
    {
        ComponentWidgetLayout componentwidgetlayout;
        try
        {
            componentwidgetlayout = (ComponentWidgetLayout)b.queryBuilder().where().eq("screenName", s).queryForFirst();
        }
        catch (SQLException sqlexception)
        {
            FkLogger.printStackTrace(sqlexception);
            return null;
        }
        return componentwidgetlayout;
    }

    public int update(ComponentWidgetLayout componentwidgetlayout)
    {
        int i;
        try
        {
            i = b.update(componentwidgetlayout);
        }
        catch (SQLException sqlexception)
        {
            FkLogger.printStackTrace(sqlexception);
            return 0;
        }
        return i;
    }
}
