// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.j256.ormlite.field.types;

import com.j256.ormlite.field.FieldType;
import com.j256.ormlite.field.SqlType;
import java.lang.reflect.Field;
import java.sql.Timestamp;

// Referenced classes of package com.j256.ormlite.field.types:
//            DateType

public class TimeStampType extends DateType
{

    private static final TimeStampType singleTon = new TimeStampType();

    private TimeStampType()
    {
        super(SqlType.DATE, new Class[] {
            java/sql/Timestamp
        });
    }

    protected TimeStampType(SqlType sqltype, Class aclass[])
    {
        super(sqltype, aclass);
    }

    public static TimeStampType getSingleton()
    {
        return singleTon;
    }

    public boolean isValidForField(Field field)
    {
        return field.getType() == java/sql/Timestamp;
    }

    public Object javaToSqlArg(FieldType fieldtype, Object obj)
    {
        return obj;
    }

    public Object moveToNextValue(Object obj)
    {
        long l = System.currentTimeMillis();
        if (obj == null)
        {
            return new Timestamp(l);
        }
        if (l == ((Timestamp)obj).getTime())
        {
            return new Timestamp(l + 1L);
        } else
        {
            return new Timestamp(l);
        }
    }

    public Object sqlArgToJava(FieldType fieldtype, Object obj, int i)
    {
        return obj;
    }

}
