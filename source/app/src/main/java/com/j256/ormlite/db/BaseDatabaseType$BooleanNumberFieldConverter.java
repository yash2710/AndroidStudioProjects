// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.j256.ormlite.db;

import com.j256.ormlite.field.BaseFieldConverter;
import com.j256.ormlite.field.FieldType;
import com.j256.ormlite.field.SqlType;
import com.j256.ormlite.support.DatabaseResults;

public class  extends BaseFieldConverter
{

    public SqlType getSqlType()
    {
        return SqlType.BOOLEAN;
    }

    public Object javaToSqlArg(FieldType fieldtype, Object obj)
    {
        if (((Boolean)obj).booleanValue())
        {
            return Byte.valueOf((byte)1);
        } else
        {
            return Byte.valueOf((byte)0);
        }
    }

    public Object parseDefaultString(FieldType fieldtype, String s)
    {
        if (Boolean.parseBoolean(s))
        {
            return Byte.valueOf((byte)1);
        } else
        {
            return Byte.valueOf((byte)0);
        }
    }

    public Object resultStringToJava(FieldType fieldtype, String s, int i)
    {
        return sqlArgToJava(fieldtype, Byte.valueOf(Byte.parseByte(s)), i);
    }

    public Object resultToSqlArg(FieldType fieldtype, DatabaseResults databaseresults, int i)
    {
        return Byte.valueOf(databaseresults.getByte(i));
    }

    public Object sqlArgToJava(FieldType fieldtype, Object obj, int i)
    {
        if (((Byte)obj).byteValue() == 1)
        {
            return Boolean.valueOf(true);
        } else
        {
            return Boolean.valueOf(false);
        }
    }

    protected ()
    {
    }
}
