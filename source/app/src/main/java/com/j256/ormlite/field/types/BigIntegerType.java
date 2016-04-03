// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.j256.ormlite.field.types;

import com.j256.ormlite.field.FieldType;
import com.j256.ormlite.field.SqlType;
import com.j256.ormlite.misc.SqlExceptionUtil;
import com.j256.ormlite.support.DatabaseResults;
import java.math.BigInteger;

// Referenced classes of package com.j256.ormlite.field.types:
//            BaseDataType

public class BigIntegerType extends BaseDataType
{

    public static int DEFAULT_WIDTH = 255;
    private static final BigIntegerType singleTon = new BigIntegerType();

    protected BigIntegerType()
    {
        super(SqlType.STRING, new Class[] {
            java/math/BigInteger
        });
    }

    protected BigIntegerType(SqlType sqltype, Class aclass[])
    {
        super(sqltype, aclass);
    }

    public static BigIntegerType getSingleton()
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
        return ((BigInteger)obj).toString();
    }

    public Object parseDefaultString(FieldType fieldtype, String s)
    {
        BigInteger biginteger;
        try
        {
            biginteger = new BigInteger(s);
        }
        catch (IllegalArgumentException illegalargumentexception)
        {
            throw SqlExceptionUtil.create((new StringBuilder("Problems with field ")).append(fieldtype).append(" parsing default BigInteger string '").append(s).append("'").toString(), illegalargumentexception);
        }
        return biginteger;
    }

    public Object resultToSqlArg(FieldType fieldtype, DatabaseResults databaseresults, int i)
    {
        return databaseresults.getString(i);
    }

    public Object sqlArgToJava(FieldType fieldtype, Object obj, int i)
    {
        BigInteger biginteger;
        try
        {
            biginteger = new BigInteger((String)obj);
        }
        catch (IllegalArgumentException illegalargumentexception)
        {
            throw SqlExceptionUtil.create((new StringBuilder("Problems with column ")).append(i).append(" parsing BigInteger string '").append(obj).append("'").toString(), illegalargumentexception);
        }
        return biginteger;
    }

}
