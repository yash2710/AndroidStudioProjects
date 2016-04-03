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

public class EnumStringType extends BaseEnumType
{

    public static int DEFAULT_WIDTH = 100;
    private static final EnumStringType singleTon = new EnumStringType();

    private EnumStringType()
    {
        super(SqlType.STRING, new Class[] {
            java/lang/Enum
        });
    }

    protected EnumStringType(SqlType sqltype, Class aclass[])
    {
        super(sqltype, aclass);
    }

    public static EnumStringType getSingleton()
    {
        return singleTon;
    }

    public int getDefaultWidth()
    {
        return DEFAULT_WIDTH;
    }

    public Object javaToSqlArg(FieldType fieldtype, Object obj)
    {
        return ((Enum)obj).name();
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
            hashmap.put(enum.name(), enum);
        }

        return hashmap;
    }

    public Object parseDefaultString(FieldType fieldtype, String s)
    {
        return s;
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
        if (fieldtype == null)
        {
            return obj;
        }
        String s = (String)obj;
        Map map = (Map)fieldtype.getDataTypeConfigObj();
        if (map == null)
        {
            return enumVal(fieldtype, s, null, fieldtype.getUnknownEnumVal());
        } else
        {
            return enumVal(fieldtype, s, (Enum)map.get(s), fieldtype.getUnknownEnumVal());
        }
    }

}
