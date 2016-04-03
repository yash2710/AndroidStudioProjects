// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.j256.ormlite.field.types;

import com.j256.ormlite.field.FieldType;
import com.j256.ormlite.field.SqlType;
import com.j256.ormlite.misc.SqlExceptionUtil;
import com.j256.ormlite.support.DatabaseResults;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Date;

// Referenced classes of package com.j256.ormlite.field.types:
//            BaseDateType

public class DateType extends BaseDateType
{

    private static final DateType singleTon = new DateType();

    private DateType()
    {
        super(SqlType.DATE, new Class[] {
            java/util/Date
        });
    }

    protected DateType(SqlType sqltype, Class aclass[])
    {
        super(sqltype, aclass);
    }

    public static DateType getSingleton()
    {
        return singleTon;
    }

    protected BaseDateType.DateStringFormatConfig getDefaultDateFormatConfig()
    {
        return defaultDateFormatConfig;
    }

    public boolean isArgumentHolderRequired()
    {
        return true;
    }

    public Object javaToSqlArg(FieldType fieldtype, Object obj)
    {
        return new Timestamp(((Date)obj).getTime());
    }

    public Object parseDefaultString(FieldType fieldtype, String s)
    {
        BaseDateType.DateStringFormatConfig datestringformatconfig = convertDateStringConfig(fieldtype, getDefaultDateFormatConfig());
        Timestamp timestamp;
        try
        {
            timestamp = new Timestamp(parseDateString(datestringformatconfig, s).getTime());
        }
        catch (ParseException parseexception)
        {
            throw SqlExceptionUtil.create((new StringBuilder("Problems parsing default date string '")).append(s).append("' using '").append(datestringformatconfig).append('\'').toString(), parseexception);
        }
        return timestamp;
    }

    public Object resultToSqlArg(FieldType fieldtype, DatabaseResults databaseresults, int i)
    {
        return databaseresults.getTimestamp(i);
    }

    public Object sqlArgToJava(FieldType fieldtype, Object obj, int i)
    {
        return new Date(((Timestamp)obj).getTime());
    }

}
