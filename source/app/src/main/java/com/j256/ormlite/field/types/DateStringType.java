// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.j256.ormlite.field.types;

import com.j256.ormlite.field.FieldType;
import com.j256.ormlite.field.SqlType;
import com.j256.ormlite.misc.SqlExceptionUtil;
import com.j256.ormlite.support.DatabaseResults;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

// Referenced classes of package com.j256.ormlite.field.types:
//            BaseDateType

public class DateStringType extends BaseDateType
{

    public static int DEFAULT_WIDTH = 50;
    private static final DateStringType singleTon = new DateStringType();

    private DateStringType()
    {
        super(SqlType.STRING, new Class[0]);
    }

    protected DateStringType(SqlType sqltype, Class aclass[])
    {
        super(sqltype, aclass);
    }

    public static DateStringType getSingleton()
    {
        return singleTon;
    }

    public int getDefaultWidth()
    {
        return DEFAULT_WIDTH;
    }

    public Class getPrimaryClass()
    {
        return [B;
    }

    public Object javaToSqlArg(FieldType fieldtype, Object obj)
    {
        return convertDateStringConfig(fieldtype, defaultDateFormatConfig).getDateFormat().format((Date)obj);
    }

    public Object makeConfigObject(FieldType fieldtype)
    {
        String s = fieldtype.getFormat();
        if (s == null)
        {
            return defaultDateFormatConfig;
        } else
        {
            return new BaseDateType.DateStringFormatConfig(s);
        }
    }

    public Object parseDefaultString(FieldType fieldtype, String s)
    {
        BaseDateType.DateStringFormatConfig datestringformatconfig = convertDateStringConfig(fieldtype, defaultDateFormatConfig);
        String s1;
        try
        {
            s1 = normalizeDateString(datestringformatconfig, s);
        }
        catch (ParseException parseexception)
        {
            throw SqlExceptionUtil.create((new StringBuilder("Problems with field ")).append(fieldtype).append(" parsing default date-string '").append(s).append("' using '").append(datestringformatconfig).append("'").toString(), parseexception);
        }
        return s1;
    }

    public Object resultStringToJava(FieldType fieldtype, String s, int i)
    {
        return sqlArgToJava(fieldtype, s, i);
    }

    public Object resultToSqlArg(FieldType fieldtype, DatabaseResults databaseresults, int i)
    {
        return databaseresults.getString(i);
    }

    public Object sqlArgToJava(FieldType fieldtype, Object obj, int i)
    {
        String s = (String)obj;
        BaseDateType.DateStringFormatConfig datestringformatconfig = convertDateStringConfig(fieldtype, defaultDateFormatConfig);
        Date date;
        try
        {
            date = parseDateString(datestringformatconfig, s);
        }
        catch (ParseException parseexception)
        {
            throw SqlExceptionUtil.create((new StringBuilder("Problems with column ")).append(i).append(" parsing date-string '").append(s).append("' using '").append(datestringformatconfig).append("'").toString(), parseexception);
        }
        return date;
    }

}
