// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.j256.ormlite.field.types;

import com.j256.ormlite.field.FieldType;
import com.j256.ormlite.field.SqlType;
import com.j256.ormlite.support.DatabaseResults;

// Referenced classes of package com.j256.ormlite.field.types:
//            BaseDataType

public class LongObjectType extends BaseDataType
{

    private static final LongObjectType singleTon = new LongObjectType();

    private LongObjectType()
    {
        super(SqlType.LONG, new Class[] {
            java/lang/Long
        });
    }

    protected LongObjectType(SqlType sqltype, Class aclass[])
    {
        super(sqltype, aclass);
    }

    public static LongObjectType getSingleton()
    {
        return singleTon;
    }

    public Object convertIdNumber(Number number)
    {
        return Long.valueOf(number.longValue());
    }

    public boolean isEscapedValue()
    {
        return false;
    }

    public boolean isValidForVersion()
    {
        return true;
    }

    public boolean isValidGeneratedType()
    {
        return true;
    }

    public Object moveToNextValue(Object obj)
    {
        if (obj == null)
        {
            return Long.valueOf(1L);
        } else
        {
            return Long.valueOf(1L + ((Long)obj).longValue());
        }
    }

    public Object parseDefaultString(FieldType fieldtype, String s)
    {
        return Long.valueOf(Long.parseLong(s));
    }

    public Object resultToSqlArg(FieldType fieldtype, DatabaseResults databaseresults, int i)
    {
        return Long.valueOf(databaseresults.getLong(i));
    }

}
