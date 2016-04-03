// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.DB;

import android.content.Context;
import com.j256.ormlite.android.apptools.OpenHelperManager;

// Referenced classes of package com.flipkart.android.DB:
//            DatabaseHelper

public class DatabaseManager
{

    private DatabaseHelper a;

    public DatabaseManager()
    {
        a = null;
    }

    public DatabaseHelper getHelper(Context context)
    {
        if (a == null)
        {
            a = (DatabaseHelper)OpenHelperManager.getHelper(context, com/flipkart/android/DB/DatabaseHelper);
        }
        return a;
    }

    public void releaseHelper(DatabaseHelper databasehelper)
    {
        if (a != null)
        {
            OpenHelperManager.releaseHelper();
            a = null;
        }
    }
}
