// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.DB;

import android.content.Context;
import com.flipkart.logging.FkLogger;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;
import java.sql.SQLException;
import java.util.List;

// Referenced classes of package com.flipkart.android.DB:
//            DatabaseManager, DatabaseHelper, AutoSuggest

public class AutoSuggestDao
{

    private DatabaseHelper a;
    private Dao b;

    public AutoSuggestDao(Context context)
    {
        try
        {
            a = (new DatabaseManager()).getHelper(context);
            b = a.getAutoSuggestDao();
            return;
        }
        catch (SQLException sqlexception)
        {
            FkLogger.printStackTrace(sqlexception);
        }
    }

    public int create(AutoSuggest autosuggest)
    {
        if (getAutoSuggestById(autosuggest.getMd5()) == null && (int)b.countOf() > 19)
        {
            delete((AutoSuggest)getSortByTime(true).get(0));
        }
        int i;
        try
        {
            i = b.createOrUpdate(autosuggest).getNumLinesChanged();
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

    public int delete(AutoSuggest autosuggest)
    {
        int i;
        try
        {
            i = b.delete(autosuggest);
        }
        catch (SQLException sqlexception)
        {
            FkLogger.printStackTrace(sqlexception);
            return 0;
        }
        return i;
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

    public AutoSuggest getAutoSuggestById(String s)
    {
        AutoSuggest autosuggest;
        try
        {
            autosuggest = (AutoSuggest)b.queryForId(s);
        }
        catch (SQLException sqlexception)
        {
            FkLogger.printStackTrace(sqlexception);
            return null;
        }
        return autosuggest;
    }

    public List getSortByTime(boolean flag)
    {
        List list;
        try
        {
            list = b.query(b.queryBuilder().orderBy("time", flag).prepare());
        }
        catch (SQLException sqlexception)
        {
            FkLogger.printStackTrace(sqlexception);
            return null;
        }
        return list;
    }

    public int update(AutoSuggest autosuggest)
    {
        int i;
        try
        {
            i = b.update(autosuggest);
        }
        catch (SQLException sqlexception)
        {
            FkLogger.printStackTrace(sqlexception);
            return 0;
        }
        return i;
    }
}
