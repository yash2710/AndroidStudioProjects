// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.j256.ormlite.field.types;

import com.j256.ormlite.field.FieldType;
import com.j256.ormlite.field.SqlType;
import com.j256.ormlite.misc.SqlExceptionUtil;
import com.j256.ormlite.support.DatabaseResults;
import java.util.Date;

// Referenced classes of package com.j256.ormlite.field.types:
//            BaseDateType

public class DateLongType extends BaseDateType
{

    private static final DateLongType singleTon = new DateLongType();

    private DateLongType()
    {
        super(SqlType.LONG, new Class[0]);
    }

    protected DateLongType(SqlType sqltype, Class aclass[])
    {
        super(sqltype, aclass);
    }

    public static DateLongType getSingleton()
    {
        return singleTon;
    }

    public Class getPrimaryClass()
    {
        return java/util/Date;
    }

    public boolean isEscapedValue()
    {
        return false;
    }

    public Object javaToSqlArg(FieldType fieldtype, Object obj)
    {
        return Long.valueOf(((Date)obj).getTime());
    }

    public Object parseDefaultString(FieldType fieldtype, String s)
    {
        Long long1;
        try
        {
            long1 = Long.valueOf(Long.parseLong(s));
        }
        catch (NumberFormatException numberformatexception)
        {
            throw SqlExceptionUtil.create((new StringBuilder("Problems with field ")).append(fieldtype).append(" parsing default date-long value: ").append(s).toString(), numberformatexception);
        }
        return long1;
    }

    public Object resultStringToJava(FieldType fieldtype, String s, int i)
    {
        return sqlArgToJava(fieldtype, Long.valueOf(Long.parseLong(s)), i);
    }

    public Object resultToSqlArg(FieldType fieldtype, DatabaseResults databaseresults, int i)
    {
        return Long.valueOf(databaseresults.getLong(i));
    }

    public Object sqlArgToJava(FieldType fieldtype, Object obj, int i)
    {
        return new Date(((Long)obj).longValue());
    }

}
