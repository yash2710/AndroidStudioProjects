// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.j256.ormlite.stmt;

import com.j256.ormlite.field.FieldType;
import java.util.List;

// Referenced classes of package com.j256.ormlite.stmt:
//            QueryBuilder

public class queryBuilder
{

    private final QueryBuilder queryBuilder;

    public void appendStatementString(StringBuilder stringbuilder, List list)
    {
        queryBuilder.appendStatementString(stringbuilder, list);
    }

    public FieldType[] getResultFieldTypes()
    {
        return queryBuilder.getResultFieldTypes();
    }

    (QueryBuilder querybuilder)
    {
        queryBuilder = querybuilder;
    }
}
