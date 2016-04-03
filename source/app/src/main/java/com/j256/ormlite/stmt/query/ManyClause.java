// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.j256.ormlite.stmt.query;

import com.j256.ormlite.db.DatabaseType;
import java.util.List;

// Referenced classes of package com.j256.ormlite.stmt.query:
//            Clause, NeedsFutureClause

public class ManyClause
    implements Clause, NeedsFutureClause
{

    public static final String AND_OPERATION = "AND";
    public static final String OR_OPERATION = "OR";
    private final Clause first;
    private final String operation;
    private final Clause others[];
    private Clause second;
    private final int startOthersAt;

    public ManyClause(Clause clause, Clause clause1, Clause aclause[], String s)
    {
        first = clause;
        second = clause1;
        others = aclause;
        startOthersAt = 0;
        operation = s;
    }

    public ManyClause(Clause clause, String s)
    {
        first = clause;
        second = null;
        others = null;
        startOthersAt = 0;
        operation = s;
    }

    public ManyClause(Clause aclause[], String s)
    {
        first = aclause[0];
        if (aclause.length < 2)
        {
            second = null;
            startOthersAt = aclause.length;
        } else
        {
            second = aclause[1];
            startOthersAt = 2;
        }
        others = aclause;
        operation = s;
    }

    public void appendSql(DatabaseType databasetype, String s, StringBuilder stringbuilder, List list)
    {
        stringbuilder.append("(");
        first.appendSql(databasetype, s, stringbuilder, list);
        if (second != null)
        {
            stringbuilder.append(operation);
            stringbuilder.append(' ');
            second.appendSql(databasetype, s, stringbuilder, list);
        }
        if (others != null)
        {
            for (int i = startOthersAt; i < others.length; i++)
            {
                stringbuilder.append(operation);
                stringbuilder.append(' ');
                others[i].appendSql(databasetype, s, stringbuilder, list);
            }

        }
        stringbuilder.append(") ");
    }

    public void setMissingClause(Clause clause)
    {
        second = clause;
    }
}
