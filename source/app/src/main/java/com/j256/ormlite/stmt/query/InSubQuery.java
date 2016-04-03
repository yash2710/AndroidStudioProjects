// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.j256.ormlite.stmt.query;

import com.j256.ormlite.db.DatabaseType;
import com.j256.ormlite.field.FieldType;
import java.sql.SQLException;
import java.util.List;

// Referenced classes of package com.j256.ormlite.stmt.query:
//            BaseComparison

public class InSubQuery extends BaseComparison
{

    private final boolean in;
    private final com.j256.ormlite.stmt.QueryBuilder.InternalQueryBuilderWrapper subQueryBuilder;

    public InSubQuery(String s, FieldType fieldtype, com.j256.ormlite.stmt.QueryBuilder.InternalQueryBuilderWrapper internalquerybuilderwrapper, boolean flag)
    {
        super(s, fieldtype, null, true);
        subQueryBuilder = internalquerybuilderwrapper;
        in = flag;
    }

    public void appendOperation(StringBuilder stringbuilder)
    {
        if (in)
        {
            stringbuilder.append("IN ");
            return;
        } else
        {
            stringbuilder.append("NOT IN ");
            return;
        }
    }

    public volatile void appendSql(DatabaseType databasetype, String s, StringBuilder stringbuilder, List list)
    {
        super.appendSql(databasetype, s, stringbuilder, list);
    }

    public void appendValue(DatabaseType databasetype, StringBuilder stringbuilder, List list)
    {
        stringbuilder.append('(');
        subQueryBuilder.appendStatementString(stringbuilder, list);
        FieldType afieldtype[] = subQueryBuilder.getResultFieldTypes();
        if (afieldtype != null)
        {
            if (afieldtype.length != 1)
            {
                throw new SQLException((new StringBuilder("There must be only 1 result column in sub-query but we found ")).append(afieldtype.length).toString());
            }
            if (fieldType.getSqlType() != afieldtype[0].getSqlType())
            {
                throw new SQLException((new StringBuilder("Outer column ")).append(fieldType).append(" is not the same type as inner column ").append(afieldtype[0]).toString());
            }
        }
        stringbuilder.append(") ");
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
