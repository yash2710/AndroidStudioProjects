// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.j256.ormlite.field;

import com.j256.ormlite.support.DatabaseResults;

// Referenced classes of package com.j256.ormlite.field:
//            FieldConverter, FieldType

public abstract class BaseFieldConverter
    implements FieldConverter
{

    public BaseFieldConverter()
    {
    }

    public boolean isStreamType()
    {
        return false;
    }

    public Object javaToSqlArg(FieldType fieldtype, Object obj)
    {
        return obj;
    }

    public Object resultToJava(FieldType fieldtype, DatabaseResults databaseresults, int i)
    {
        Object obj = resultToSqlArg(fieldtype, databaseresults, i);
        if (obj == null)
        {
            return null;
        } else
        {
            return sqlArgToJava(fieldtype, obj, i);
        }
    }

    public Object sqlArgToJava(FieldType fieldtype, Object obj, int i)
    {
        return obj;
    }
}
