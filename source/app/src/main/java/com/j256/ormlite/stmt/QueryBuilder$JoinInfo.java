// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.j256.ormlite.stmt;

import com.j256.ormlite.field.FieldType;

// Referenced classes of package com.j256.ormlite.stmt:
//            QueryBuilder

class operation
{

    FieldType localField;
    Operation operation;
    final QueryBuilder queryBuilder;
    FieldType remoteField;
    final QueryBuilder this$0;
    final String type;

    public Operation(String s, QueryBuilder querybuilder1, Operation operation1)
    {
        this$0 = QueryBuilder.this;
        super();
        type = s;
        queryBuilder = querybuilder1;
        operation = operation1;
    }
}
