// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.j256.ormlite.stmt.query;

import com.j256.ormlite.db.DatabaseType;
import com.j256.ormlite.stmt.ArgumentHolder;
import java.util.List;

// Referenced classes of package com.j256.ormlite.stmt.query:
//            Clause

public class Raw
    implements Clause
{

    private final ArgumentHolder args[];
    private final String statement;

    public Raw(String s, ArgumentHolder aargumentholder[])
    {
        statement = s;
        args = aargumentholder;
    }

    public void appendSql(DatabaseType databasetype, String s, StringBuilder stringbuilder, List list)
    {
        stringbuilder.append(statement);
        stringbuilder.append(' ');
        ArgumentHolder aargumentholder[] = args;
        int i = aargumentholder.length;
        for (int j = 0; j < i; j++)
        {
            list.add(aargumentholder[j]);
        }

    }
}
