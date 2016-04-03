// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.j256.ormlite.field.types;

import com.j256.ormlite.field.FieldType;
import com.j256.ormlite.field.SqlType;
import com.j256.ormlite.support.DatabaseResults;

// Referenced classes of package com.j256.ormlite.field.types:
//            BaseDataType

public class FloatObjectType extends BaseDataType
{

    private static final FloatObjectType singleTon = new FloatObjectType();

    private FloatObjectType()
    {
        super(SqlType.FLOAT, new Class[] {
            java/lang/Float
        });
    }

    protected FloatObjectType(SqlType sqltype, Class aclass[])
    {
        super(sqltype, aclass);
    }

    public static FloatObjectType getSingleton()
    {
        return singleTon;
    }

    public boolean isEscapedValue()
    {
        return false;
    }

    public Object parseDefaultString(FieldType fieldtype, String s)
    {
        return Float.valueOf(Float.parseFloat(s));
    }

    public Object resultToSqlArg(FieldType fieldtype, DatabaseResults databaseresults, int i)
    {
        return Float.valueOf(databaseresults.getFloat(i));
    }

}
