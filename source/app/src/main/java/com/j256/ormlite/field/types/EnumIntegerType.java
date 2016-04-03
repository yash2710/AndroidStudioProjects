// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.j256.ormlite.field.types;

import com.j256.ormlite.field.FieldType;
import com.j256.ormlite.field.SqlType;
import com.j256.ormlite.support.DatabaseResults;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

// Referenced classes of package com.j256.ormlite.field.types:
//            BaseEnumType

public class EnumIntegerType extends BaseEnumType
{

    private static final EnumIntegerType singleTon = new EnumIntegerType();

    private EnumIntegerType()
    {
        super(SqlType.INTEGER, new Class[0]);
    }

    protected EnumIntegerType(SqlType sqltype, Class aclass[])
    {
        super(sqltype, aclass);
    }

    public static EnumIntegerType getSingleton()
    {
        return singleTon;
    }

    public Class getPrimaryClass()
    {
        return Integer.TYPE;
    }

    public boolean isEscapedValue()
    {
        return false;
    }

    public Object javaToSqlArg(FieldType fieldtype, Object obj)
    {
        return Integer.valueOf(((Enum)obj).ordinal());
    }

    public Object makeConfigObject(FieldType fieldtype)
    {
        HashMap hashmap = new HashMap();
        Enum aenum[] = (Enum[])fieldtype.getType().getEnumConstants();
        if (aenum == null)
        {
            throw new SQLException((new StringBuilder("Field ")).append(fieldtype).append(" improperly configured as type ").append(this).toString());
        }
        int i = aenum.length;
        for (int j = 0; j < i; j++)
        {
            Enum enum = aenum[j];
            hashmap.put(Integer.valueOf(enum.ordinal()), enum);
        }

        return hashmap;
    }

    public Object parseDefaultString(FieldType fieldtype, String s)
    {
        return Integer.valueOf(Integer.parseInt(s));
    }

    public Object resultStringToJava(FieldType fieldtype, String s, int i)
    {
        return sqlArgToJava(fieldtype, Integer.valueOf(Integer.parseInt(s)), i);
    }

    public Object resultToSqlArg(FieldType fieldtype, DatabaseResults databaseresults, int i)
    {
        return Integer.valueOf(databaseresults.getInt(i));
    }

    public Object sqlArgToJava(FieldType fieldtype, Object obj, int i)
    {
        if (fieldtype == null)
        {
            return obj;
        }
        Integer integer = (Integer)obj;
        Map map = (Map)fieldtype.getDataTypeConfigObj();
        if (map == null)
        {
            return enumVal(fieldtype, integer, null, fieldtype.getUnknownEnumVal());
        } else
        {
            return enumVal(fieldtype, integer, (Enum)map.get(integer), fieldtype.getUnknownEnumVal());
        }
    }

}
