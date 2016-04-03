// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.j256.ormlite.android.apptools;

import android.app.Service;
import android.content.Context;
import com.j256.ormlite.support.ConnectionSource;

// Referenced classes of package com.j256.ormlite.android.apptools:
//            OrmLiteSqliteOpenHelper, OpenHelperManager

public abstract class OrmLiteBaseService extends Service
{

    private volatile boolean created;
    private volatile boolean destroyed;
    private volatile OrmLiteSqliteOpenHelper helper;

    public OrmLiteBaseService()
    {
        created = false;
        destroyed = false;
    }

    public ConnectionSource getConnectionSource()
    {
        return getHelper().getConnectionSource();
    }

    public OrmLiteSqliteOpenHelper getHelper()
    {
        if (helper == null)
        {
            if (!created)
            {
                throw new IllegalStateException("A call has not been made to onCreate() yet so the helper is null");
            }
            if (destroyed)
            {
                throw new IllegalStateException("A call to onDestroy has already been made and the helper cannot be used after that point");
            } else
            {
                throw new IllegalStateException("Helper is null for some unknown reason");
            }
        } else
        {
            return helper;
        }
    }

    protected OrmLiteSqliteOpenHelper getHelperInternal(Context context)
    {
        return OpenHelperManager.getHelper(context);
    }

    public void onCreate()
    {
        if (helper == null)
        {
            helper = getHelperInternal(this);
            created = true;
        }
        super.onCreate();
    }

    public void onDestroy()
    {
        super.onDestroy();
        releaseHelper(helper);
        destroyed = true;
    }

    protected void releaseHelper(OrmLiteSqliteOpenHelper ormlitesqliteopenhelper)
    {
        OpenHelperManager.releaseHelper();
        helper = null;
    }
}
