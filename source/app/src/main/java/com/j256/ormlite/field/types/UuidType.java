// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.j256.ormlite.field.types;

import com.j256.ormlite.field.FieldType;
import com.j256.ormlite.field.SqlType;
import com.j256.ormlite.misc.SqlExceptionUtil;
import com.j256.ormlite.support.DatabaseResults;
import java.util.UUID;

// Referenced classes of package com.j256.ormlite.field.types:
//            BaseDataType

public class UuidType extends BaseDataType
{

    public static int DEFAULT_WIDTH = 48;
    private static final UuidType singleTon = new UuidType();

    private UuidType()
    {
        super(SqlType.STRING, new Class[] {
            java/util/UUID
        });
    }

    protected UuidType(SqlType sqltype, Class aclass[])
    {
        super(sqltype, aclass);
    }

    public static UuidType getSingleton()
    {
        return singleTon;
    }

    public Object generateId()
    {
        return UUID.randomUUID();
    }

    public int getDefaultWidth()
    {
        return DEFAULT_WIDTH;
    }

    public boolean isSelfGeneratedId()
    {
        return true;
    }

    public boolean isValidGeneratedType()
    {
        return true;
    }

    public Object javaToSqlArg(FieldType fieldtype, Object obj)
    {
        return ((UUID)obj).toString();
    }

    public Object parseDefaultString(FieldType fieldtype, String s)
    {
        UUID uuid;
        try
        {
            uuid = UUID.fromString(s);
        }
        catch (IllegalArgumentException illegalargumentexception)
        {
            throw SqlExceptionUtil.create((new StringBuilder("Problems with field ")).append(fieldtype).append(" parsing default UUID-string '").append(s).append("'").toString(), illegalargumentexception);
        }
        return uuid;
    }

    public Object resultToSqlArg(FieldType fieldtype, DatabaseResults databaseresults, int i)
    {
        return databaseresults.getString(i);
    }

    public Object sqlArgToJava(FieldType fieldtype, Object obj, int i)
    {
        String s = (String)obj;
        UUID uuid;
        try
        {
            uuid = UUID.fromString(s);
        }
        catch (IllegalArgumentException illegalargumentexception)
        {
            throw SqlExceptionUtil.create((new StringBuilder("Problems with column ")).append(i).append(" parsing UUID-string '").append(s).append("'").toString(), illegalargumentexception);
        }
        return uuid;
    }

}
