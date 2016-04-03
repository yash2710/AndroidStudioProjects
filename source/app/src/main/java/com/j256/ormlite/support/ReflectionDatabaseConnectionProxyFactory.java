// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.j256.ormlite.support;

import com.j256.ormlite.misc.SqlExceptionUtil;
import java.lang.reflect.Constructor;

// Referenced classes of package com.j256.ormlite.support:
//            DatabaseConnectionProxyFactory, DatabaseConnection

public class ReflectionDatabaseConnectionProxyFactory
    implements DatabaseConnectionProxyFactory
{

    private final Constructor constructor;
    private final Class proxyClass;

    public ReflectionDatabaseConnectionProxyFactory(Class class1)
    {
        proxyClass = class1;
        try
        {
            constructor = class1.getConstructor(new Class[] {
                com/j256/ormlite/support/DatabaseConnection
            });
            return;
        }
        catch (Exception exception)
        {
            throw new IllegalArgumentException((new StringBuilder("Could not find constructor with DatabaseConnection argument in ")).append(class1).toString());
        }
    }

    public DatabaseConnection createProxy(DatabaseConnection databaseconnection)
    {
        DatabaseConnection databaseconnection1;
        try
        {
            databaseconnection1 = (DatabaseConnection)constructor.newInstance(new Object[] {
                databaseconnection
            });
        }
        catch (Exception exception)
        {
            throw SqlExceptionUtil.create((new StringBuilder("Could not create a new instance of ")).append(proxyClass).toString(), exception);
        }
        return databaseconnection1;
    }
}
