// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.j256.ormlite.field.types;

import com.j256.ormlite.field.FieldType;
import com.j256.ormlite.field.SqlType;
import com.j256.ormlite.misc.SqlExceptionUtil;
import com.j256.ormlite.support.DatabaseResults;
import java.math.BigDecimal;

// Referenced classes of package com.j256.ormlite.field.types:
//            BaseDataType

public class BigDecimalStringType extends BaseDataType
{

    public static int DEFAULT_WIDTH = 255;
    private static final BigDecimalStringType singleTon = new BigDecimalStringType();

    private BigDecimalStringType()
    {
        super(SqlType.STRING, new Class[] {
            java/math/BigDecimal
        });
    }

    protected BigDecimalStringType(SqlType sqltype, Class aclass[])
    {
        super(sqltype, aclass);
    }

    public static BigDecimalStringType getSingleton()
    {
        return singleTon;
    }

    public int getDefaultWidth()
    {
        return DEFAULT_WIDTH;
    }

    public boolean isAppropriateId()
    {
        return false;
    }

    public Object javaToSqlArg(FieldType fieldtype, Object obj)
    {
        return ((BigDecimal)obj).toString();
    }

    public Object parseDefaultString(FieldType fieldtype, String s)
    {
        BigDecimal bigdecimal;
        try
        {
            bigdecimal = new BigDecimal(s);
        }
        catch (IllegalArgumentException illegalargumentexception)
        {
            throw SqlExceptionUtil.create((new StringBuilder("Problems with field ")).append(fieldtype).append(" parsing default BigDecimal string '").append(s).append("'").toString(), illegalargumentexception);
        }
        return bigdecimal;
    }

    public Object resultToSqlArg(FieldType fieldtype, DatabaseResults databaseresults, int i)
    {
        return databaseresults.getString(i);
    }

    public Object sqlArgToJava(FieldType fieldtype, Object obj, int i)
    {
        BigDecimal bigdecimal;
        try
        {
            bigdecimal = new BigDecimal((String)obj);
        }
        catch (IllegalArgumentException illegalargumentexception)
        {
            throw SqlExceptionUtil.create((new StringBuilder("Problems with column ")).append(i).append(" parsing BigDecimal string '").append(obj).append("'").toString(), illegalargumentexception);
        }
        return bigdecimal;
    }

}
