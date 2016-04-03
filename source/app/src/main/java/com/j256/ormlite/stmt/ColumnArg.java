// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.j256.ormlite.stmt;


public class ColumnArg
{

    private final String columnName;
    private final String tableName;

    public ColumnArg(String s)
    {
        tableName = null;
        columnName = s;
    }

    public ColumnArg(String s, String s1)
    {
        tableName = s;
        columnName = s1;
    }

    public String getColumnName()
    {
        return columnName;
    }

    public String getTableName()
    {
        return tableName;
    }
}
