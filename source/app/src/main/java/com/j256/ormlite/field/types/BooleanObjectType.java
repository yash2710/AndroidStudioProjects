// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.j256.ormlite.field.types;

import com.j256.ormlite.field.FieldType;
import com.j256.ormlite.field.SqlType;
import com.j256.ormlite.support.DatabaseResults;

// Referenced classes of package com.j256.ormlite.field.types:
//            BaseDataType

public class BooleanObjectType extends BaseDataType
{

    private static final BooleanObjectType singleTon = new BooleanObjectType();

    private BooleanObjectType()
    {
        super(SqlType.BOOLEAN, new Class[] {
            java/lang/Boolean
        });
    }

    protected BooleanObjectType(SqlType sqltype, Class aclass[])
    {
        super(sqltype, aclass);
    }

    public static BooleanObjectType getSingleton()
    {
        return singleTon;
    }

    public boolean isAppropriateId()
    {
        return false;
    }

    public boolean isEscapedValue()
    {
        return false;
    }

    public Object parseDefaultString(FieldType fieldtype, String s)
    {
        return Boolean.valueOf(Boolean.parseBoolean(s));
    }

    public Object resultToSqlArg(FieldType fieldtype, DatabaseResults databaseresults, int i)
    {
        return Boolean.valueOf(databaseresults.getBoolean(i));
    }

}
