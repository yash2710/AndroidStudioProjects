// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.j256.ormlite.field.types;

import com.j256.ormlite.field.FieldType;
import com.j256.ormlite.field.SqlType;
import java.lang.reflect.Field;
import java.sql.Date;
import java.sql.Timestamp;

// Referenced classes of package com.j256.ormlite.field.types:
//            DateType

public class SqlDateType extends DateType
{

    private static final SqlDateType singleTon = new SqlDateType();
    private static final BaseDateType.DateStringFormatConfig sqlDateFormatConfig = new BaseDateType.DateStringFormatConfig("yyyy-MM-dd");

    private SqlDateType()
    {
        super(SqlType.DATE, new Class[] {
            java/sql/Date
        });
    }

    protected SqlDateType(SqlType sqltype, Class aclass[])
    {
        super(sqltype, aclass);
    }

    public static SqlDateType getSingleton()
    {
        return singleTon;
    }

    protected BaseDateType.DateStringFormatConfig getDefaultDateFormatConfig()
    {
        return sqlDateFormatConfig;
    }

    public boolean isValidForField(Field field)
    {
        return field.getType() == java/sql/Date;
    }

    public Object javaToSqlArg(FieldType fieldtype, Object obj)
    {
        return new Timestamp(((Date)obj).getTime());
    }

    public Object resultStringToJava(FieldType fieldtype, String s, int i)
    {
        return sqlArgToJava(fieldtype, Timestamp.valueOf(s), i);
    }

    public Object sqlArgToJava(FieldType fieldtype, Object obj, int i)
    {
        return new Date(((Timestamp)obj).getTime());
    }

}
