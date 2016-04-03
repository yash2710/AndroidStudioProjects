// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.j256.ormlite.field.types;

import com.j256.ormlite.field.FieldType;
import com.j256.ormlite.support.DatabaseResults;
import java.lang.reflect.Field;

// Referenced classes of package com.j256.ormlite.field.types:
//            BaseDataType

public class VoidType extends BaseDataType
{

    VoidType()
    {
        super(null, new Class[0]);
    }

    public boolean isValidForField(Field field)
    {
        return false;
    }

    public Object parseDefaultString(FieldType fieldtype, String s)
    {
        return null;
    }

    public Object resultToSqlArg(FieldType fieldtype, DatabaseResults databaseresults, int i)
    {
        return null;
    }
}
