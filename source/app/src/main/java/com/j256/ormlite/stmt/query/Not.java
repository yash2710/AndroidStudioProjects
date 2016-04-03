// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.j256.ormlite.stmt.query;

import com.j256.ormlite.db.DatabaseType;
import java.util.List;

// Referenced classes of package com.j256.ormlite.stmt.query:
//            Clause, NeedsFutureClause, Exists, Comparison

public class Not
    implements Clause, NeedsFutureClause
{

    private Comparison comparison;
    private Exists exists;

    public Not()
    {
        comparison = null;
        exists = null;
    }

    public Not(Clause clause)
    {
        comparison = null;
        exists = null;
        setMissingClause(clause);
    }

    public void appendSql(DatabaseType databasetype, String s, StringBuilder stringbuilder, List list)
    {
        if (comparison == null && exists == null)
        {
            throw new IllegalStateException("Clause has not been set in NOT operation");
        }
        if (comparison == null)
        {
            stringbuilder.append("(NOT ");
            exists.appendSql(databasetype, s, stringbuilder, list);
        } else
        {
            stringbuilder.append("(NOT ");
            if (s != null)
            {
                databasetype.appendEscapedEntityName(stringbuilder, s);
                stringbuilder.append('.');
            }
            databasetype.appendEscapedEntityName(stringbuilder, comparison.getColumnName());
            stringbuilder.append(' ');
            comparison.appendOperation(stringbuilder);
            comparison.appendValue(databasetype, stringbuilder, list);
        }
        stringbuilder.append(") ");
    }

    public void setMissingClause(Clause clause)
    {
        if (comparison != null)
        {
            throw new IllegalArgumentException("NOT operation already has a comparison set");
        }
        if (clause instanceof Comparison)
        {
            comparison = (Comparison)clause;
            return;
        }
        if (clause instanceof Exists)
        {
            exists = (Exists)clause;
            return;
        } else
        {
            throw new IllegalArgumentException((new StringBuilder("NOT operation can only work with comparison SQL clauses, not ")).append(clause).toString());
        }
    }

    public String toString()
    {
        if (comparison == null)
        {
            return "NOT without comparison";
        } else
        {
            return (new StringBuilder("NOT comparison ")).append(comparison).toString();
        }
    }
}
