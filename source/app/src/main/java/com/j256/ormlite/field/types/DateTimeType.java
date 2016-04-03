// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.j256.ormlite.field.types;

import com.j256.ormlite.field.FieldType;
import com.j256.ormlite.field.SqlType;
import com.j256.ormlite.misc.SqlExceptionUtil;
import com.j256.ormlite.support.DatabaseResults;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

// Referenced classes of package com.j256.ormlite.field.types:
//            BaseDataType

public class DateTimeType extends BaseDataType
{

    private static final String associatedClassNames[] = {
        "org.joda.time.DateTime"
    };
    private static Class dateTimeClass = null;
    private static Method getMillisMethod = null;
    private static Constructor millisConstructor = null;
    private static final DateTimeType singleTon = new DateTimeType();

    private DateTimeType()
    {
        super(SqlType.LONG, new Class[0]);
    }

    protected DateTimeType(SqlType sqltype, Class aclass[])
    {
        super(sqltype, aclass);
    }

    private Constructor getConstructor()
    {
        if (millisConstructor == null)
        {
            Class class1 = getDateTimeClass();
            Class aclass[] = new Class[1];
            aclass[0] = Long.TYPE;
            millisConstructor = class1.getConstructor(aclass);
        }
        return millisConstructor;
    }

    private Class getDateTimeClass()
    {
        if (dateTimeClass == null)
        {
            dateTimeClass = Class.forName("org.joda.time.DateTime");
        }
        return dateTimeClass;
    }

    private Method getMillisMethod()
    {
        if (getMillisMethod == null)
        {
            getMillisMethod = getDateTimeClass().getMethod("getMillis", new Class[0]);
        }
        return getMillisMethod;
    }

    public static DateTimeType getSingleton()
    {
        return singleTon;
    }

    public String[] getAssociatedClassNames()
    {
        return associatedClassNames;
    }

    public Class getPrimaryClass()
    {
        Class class1;
        try
        {
            class1 = getDateTimeClass();
        }
        catch (ClassNotFoundException classnotfoundexception)
        {
            return null;
        }
        return class1;
    }

    public boolean isAppropriateId()
    {
        return false;
    }

    public boolean isEscapedValue()
    {
        return false;
    }

    public Object javaToSqlArg(FieldType fieldtype, Object obj)
    {
        Method method;
        Object obj1;
        try
        {
            method = getMillisMethod();
        }
        catch (Exception exception)
        {
            throw SqlExceptionUtil.create((new StringBuilder("Could not use reflection to get millis from Joda DateTime: ")).append(obj).toString(), exception);
        }
        if (obj == null)
        {
            return null;
        }
        obj1 = method.invoke(obj, new Object[0]);
        return obj1;
    }

    public Object parseDefaultString(FieldType fieldtype, String s)
    {
        return Long.valueOf(Long.parseLong(s));
    }

    public Object resultToSqlArg(FieldType fieldtype, DatabaseResults databaseresults, int i)
    {
        return Long.valueOf(databaseresults.getLong(i));
    }

    public Object sqlArgToJava(FieldType fieldtype, Object obj, int i)
    {
        Object obj1;
        try
        {
            Constructor constructor = getConstructor();
            Object aobj[] = new Object[1];
            aobj[0] = (Long)obj;
            obj1 = constructor.newInstance(aobj);
        }
        catch (Exception exception)
        {
            throw SqlExceptionUtil.create("Could not use reflection to construct a Joda DateTime", exception);
        }
        return obj1;
    }

}
