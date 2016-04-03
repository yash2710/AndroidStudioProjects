// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.j256.ormlite.stmt.query;

import com.j256.ormlite.db.DatabaseType;
import com.j256.ormlite.field.FieldType;
import java.util.List;

// Referenced classes of package com.j256.ormlite.stmt.query:
//            BaseComparison

public class SetExpression extends BaseComparison
{

    public SetExpression(String s, FieldType fieldtype, String s1)
    {
        super(s, fieldtype, s1, true);
    }

    protected void appendArgOrValue(DatabaseType databasetype, FieldType fieldtype, StringBuilder stringbuilder, List list, Object obj)
    {
        stringbuilder.append(obj).append(' ');
    }

    public void appendOperation(StringBuilder stringbuilder)
    {
        stringbuilder.append("= ");
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
