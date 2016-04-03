// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.j256.ormlite.field.types;

import com.j256.ormlite.field.FieldType;
import com.j256.ormlite.field.SqlType;
import com.j256.ormlite.support.DatabaseResults;

// Referenced classes of package com.j256.ormlite.field.types:
//            BaseDataType

public class DoubleObjectType extends BaseDataType
{

    private static final DoubleObjectType singleTon = new DoubleObjectType();

    private DoubleObjectType()
    {
        super(SqlType.DOUBLE, new Class[] {
            java/lang/Double
        });
    }

    protected DoubleObjectType(SqlType sqltype, Class aclass[])
    {
        super(sqltype, aclass);
    }

    public static DoubleObjectType getSingleton()
    {
        return singleTon;
    }

    public boolean isEscapedValue()
    {
        return false;
    }

    public Object parseDefaultString(FieldType fieldtype, String s)
    {
        return Double.valueOf(Double.parseDouble(s));
    }

    public Object resultToSqlArg(FieldType fieldtype, DatabaseResults databaseresults, int i)
    {
        return Double.valueOf(databaseresults.getDouble(i));
    }

}
