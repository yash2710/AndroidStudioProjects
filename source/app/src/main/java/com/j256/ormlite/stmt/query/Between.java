// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.j256.ormlite.stmt.query;

import com.j256.ormlite.db.DatabaseType;
import com.j256.ormlite.field.FieldType;
import java.util.List;

// Referenced classes of package com.j256.ormlite.stmt.query:
//            BaseComparison

public class Between extends BaseComparison
{

    private Object high;
    private Object low;

    public Between(String s, FieldType fieldtype, Object obj, Object obj1)
    {
        super(s, fieldtype, null, true);
        low = obj;
        high = obj1;
    }

    public void appendOperation(StringBuilder stringbuilder)
    {
        stringbuilder.append("BETWEEN ");
    }

    public volatile void appendSql(DatabaseType databasetype, String s, StringBuilder stringbuilder, List list)
    {
        super.appendSql(databasetype, s, stringbuilder, list);
    }

    public void appendValue(DatabaseType databasetype, StringBuilder stringbuilder, List list)
    {
        if (low == null)
        {
            throw new IllegalArgumentException((new StringBuilder("BETWEEN low value for '")).append(columnName).append("' is null").toString());
        }
        if (high == null)
        {
            throw new IllegalArgumentException((new StringBuilder("BETWEEN high value for '")).append(columnName).append("' is null").toString());
        } else
        {
            appendArgOrValue(databasetype, fieldType, stringbuilder, list, low);
            stringbuilder.append("AND ");
            appendArgOrValue(databasetype, fieldType, stringbuilder, list, high);
            return;
        }
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
