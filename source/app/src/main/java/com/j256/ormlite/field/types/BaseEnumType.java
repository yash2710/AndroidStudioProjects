// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.j256.ormlite.field.types;

import com.j256.ormlite.field.FieldType;
import com.j256.ormlite.field.SqlType;
import java.lang.reflect.Field;
import java.sql.SQLException;

// Referenced classes of package com.j256.ormlite.field.types:
//            BaseDataType

public abstract class BaseEnumType extends BaseDataType
{

    protected BaseEnumType(SqlType sqltype, Class aclass[])
    {
        super(sqltype, aclass);
    }

    protected static Enum enumVal(FieldType fieldtype, Object obj, Enum enum, Enum enum1)
    {
        if (enum != null)
        {
            return enum;
        }
        if (enum1 == null)
        {
            throw new SQLException((new StringBuilder("Cannot get enum value of '")).append(obj).append("' for field ").append(fieldtype).toString());
        } else
        {
            return enum1;
        }
    }

    public boolean isValidForField(Field field)
    {
        return field.getType().isEnum();
    }
}
