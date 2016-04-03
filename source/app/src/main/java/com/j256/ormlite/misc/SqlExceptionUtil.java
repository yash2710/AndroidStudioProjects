// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.j256.ormlite.misc;

import java.sql.SQLException;

public class SqlExceptionUtil
{

    private SqlExceptionUtil()
    {
    }

    public static SQLException create(String s, Throwable throwable)
    {
        SQLException sqlexception = new SQLException(s);
        sqlexception.initCause(throwable);
        return sqlexception;
    }
}
