// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.j256.ormlite.dao;

import com.j256.ormlite.support.ConnectionSource;

class clazz
{

    Class clazz;
    ConnectionSource connectionSource;

    public boolean equals(Object obj)
    {
        clazz clazz1;
        if (obj != null && getClass() == obj.getClass())
        {
            if (clazz.equals((clazz1 = (clazz)obj).clazz) && connectionSource.equals(clazz1.connectionSource))
            {
                return true;
            }
        }
        return false;
    }

    public int hashCode()
    {
        return 31 * (31 + clazz.hashCode()) + connectionSource.hashCode();
    }

    public (ConnectionSource connectionsource, Class class1)
    {
        connectionSource = connectionsource;
        clazz = class1;
    }
}
