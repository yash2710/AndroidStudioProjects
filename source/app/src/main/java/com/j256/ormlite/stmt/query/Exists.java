// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.j256.ormlite.stmt.query;

import com.j256.ormlite.db.DatabaseType;
import java.util.List;

// Referenced classes of package com.j256.ormlite.stmt.query:
//            Clause

public class Exists
    implements Clause
{

    private final com.j256.ormlite.stmt.QueryBuilder.InternalQueryBuilderWrapper subQueryBuilder;

    public Exists(com.j256.ormlite.stmt.QueryBuilder.InternalQueryBuilderWrapper internalquerybuilderwrapper)
    {
        subQueryBuilder = internalquerybuilderwrapper;
    }

    public void appendSql(DatabaseType databasetype, String s, StringBuilder stringbuilder, List list)
    {
        stringbuilder.append("EXISTS (");
        subQueryBuilder.appendStatementString(stringbuilder, list);
        stringbuilder.append(") ");
    }
}
