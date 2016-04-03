// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.j256.ormlite.stmt.query;

import com.j256.ormlite.db.DatabaseType;
import com.j256.ormlite.field.FieldType;
import java.util.List;

// Referenced classes of package com.j256.ormlite.stmt.query:
//            BaseComparison

public class SimpleComparison extends BaseComparison
{

    public static final String EQUAL_TO_OPERATION = "=";
    public static final String GREATER_THAN_EQUAL_TO_OPERATION = ">=";
    public static final String GREATER_THAN_OPERATION = ">";
    public static final String LESS_THAN_EQUAL_TO_OPERATION = "<=";
    public static final String LESS_THAN_OPERATION = "<";
    public static final String LIKE_OPERATION = "LIKE";
    public static final String NOT_EQUAL_TO_OPERATION = "<>";
    private final String operation;

    public SimpleComparison(String s, FieldType fieldtype, Object obj, String s1)
    {
        super(s, fieldtype, obj, true);
        operation = s1;
    }

    public void appendOperation(StringBuilder stringbuilder)
    {
        stringbuilder.append(operation);
        stringbuilder.append(' ');
    }

    public volatile void appendSql(DatabaseType databasetype, String s, StringBuilder stringbuilder, List list)
    {
        super.appendSql(databasetype, s, stringbuilder, list);
    }

    public volatile void appendValue(DatabaseType databasetype, StringBuilder stringbuilder, List list)
    {
        super.appendValue(databasetype, stringbuilder, list);
    }

    public volatile String getColumnName()
    {
        return super.getColumnName();
    }

    public volatile String toString()
    {
        return super.toString();
    }
}
