// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.j256.ormlite.stmt.query;


public class OrderBy
{

    private final boolean ascending;
    private final String columnName;

    public OrderBy(String s, boolean flag)
    {
        columnName = s;
        ascending = flag;
    }

    public String getColumnName()
    {
        return columnName;
    }

    public boolean isAscending()
    {
        return ascending;
    }
}
