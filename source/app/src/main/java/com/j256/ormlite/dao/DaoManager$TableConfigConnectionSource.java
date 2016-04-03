// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.j256.ormlite.dao;

import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.DatabaseTableConfig;

class tableConfig
{

    ConnectionSource connectionSource;
    DatabaseTableConfig tableConfig;

    public boolean equals(Object obj)
    {
        tableConfig tableconfig;
        if (obj != null && getClass() == obj.getClass())
        {
            if (tableConfig.equals((tableconfig = (tableConfig)obj).tableConfig) && connectionSource.equals(tableconfig.connectionSource))
            {
                return true;
            }
        }
        return false;
    }

    public int hashCode()
    {
        return 31 * (31 + tableConfig.hashCode()) + connectionSource.hashCode();
    }

    public A(ConnectionSource connectionsource, DatabaseTableConfig databasetableconfig)
    {
        connectionSource = connectionsource;
        tableConfig = databasetableconfig;
    }
}
