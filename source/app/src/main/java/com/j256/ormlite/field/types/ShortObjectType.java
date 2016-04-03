// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.j256.ormlite.field.types;

import com.j256.ormlite.field.FieldType;
import com.j256.ormlite.field.SqlType;
import com.j256.ormlite.support.DatabaseResults;

// Referenced classes of package com.j256.ormlite.field.types:
//            BaseDataType

public class ShortObjectType extends BaseDataType
{

    private static final ShortObjectType singleTon = new ShortObjectType();

    private ShortObjectType()
    {
        super(SqlType.SHORT, new Class[] {
            java/lang/Short
        });
    }

    protected ShortObjectType(SqlType sqltype, Class aclass[])
    {
        super(sqltype, aclass);
    }

    public static ShortObjectType getSingleton()
    {
        return singleTon;
    }

    public boolean isEscapedValue()
    {
        return false;
    }

    public boolean isValidForVersion()
    {
        return true;
    }

    public Object moveToNextValue(Object obj)
    {
        if (obj == null)
        {
            return Short.valueOf((short)1);
        } else
        {
            return Short.valueOf((short)(1 + ((Short)obj).shortValue()));
        }
    }

    public Object parseDefaultString(FieldType fieldtype, String s)
    {
        return Short.valueOf(Short.parseShort(s));
    }

    public Object resultToSqlArg(FieldType fieldtype, DatabaseResults databaseresults, int i)
    {
        return Short.valueOf(databaseresults.getShort(i));
    }

}
