// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.j256.ormlite.stmt.query;

import com.j256.ormlite.db.DatabaseType;
import com.j256.ormlite.field.FieldType;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

// Referenced classes of package com.j256.ormlite.stmt.query:
//            BaseComparison

public class In extends BaseComparison
{

    private final boolean in;
    private Iterable objects;

    public In(String s, FieldType fieldtype, Iterable iterable, boolean flag)
    {
        super(s, fieldtype, null, true);
        objects = iterable;
        in = flag;
    }

    public In(String s, FieldType fieldtype, Object aobj[], boolean flag)
    {
        super(s, fieldtype, null, true);
        objects = Arrays.asList(aobj);
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
        boolean flag = true;
        Iterator iterator = objects.iterator();
        while (iterator.hasNext()) 
        {
            Object obj = iterator.next();
            if (obj == null)
            {
                throw new IllegalArgumentException((new StringBuilder("one of the IN values for '")).append(columnName).append("' is null").toString());
            }
            boolean flag1;
            if (flag)
            {
                flag1 = false;
            } else
            {
                stringbuilder.append(',');
                flag1 = flag;
            }
            super.appendArgOrValue(databasetype, fieldType, stringbuilder, list, obj);
            flag = flag1;
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
