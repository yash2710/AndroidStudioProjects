// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.j256.ormlite.field.types;

import com.j256.ormlite.field.BaseFieldConverter;
import com.j256.ormlite.field.DataPersister;
import com.j256.ormlite.field.FieldType;
import com.j256.ormlite.field.SqlType;
import com.j256.ormlite.support.DatabaseResults;
import java.lang.reflect.Field;

public abstract class BaseDataType extends BaseFieldConverter
    implements DataPersister
{

    private final Class classes[];
    private final SqlType sqlType;

    public BaseDataType(SqlType sqltype, Class aclass[])
    {
        sqlType = sqltype;
        classes = aclass;
    }

    public Object convertIdNumber(Number number)
    {
        return null;
    }

    public boolean dataIsEqual(Object obj, Object obj1)
    {
        boolean flag;
        if (obj == null)
        {
            flag = false;
            if (obj1 == null)
            {
                flag = true;
            }
        } else
        {
            flag = false;
            if (obj1 != null)
            {
                return obj.equals(obj1);
            }
        }
        return flag;
    }

    public Object generateId()
    {
        throw new IllegalStateException("Should not have tried to generate this type");
    }

    public String[] getAssociatedClassNames()
    {
        return null;
    }

    public Class[] getAssociatedClasses()
    {
        return classes;
    }

    public int getDefaultWidth()
    {
        return 0;
    }

    public Class getPrimaryClass()
    {
        if (classes.length == 0)
        {
            return null;
        } else
        {
            return classes[0];
        }
    }

    public SqlType getSqlType()
    {
        return sqlType;
    }

    public boolean isAppropriateId()
    {
        return true;
    }

    public boolean isArgumentHolderRequired()
    {
        return false;
    }

    public boolean isComparable()
    {
        return true;
    }

    public boolean isEscapedDefaultValue()
    {
        return isEscapedValue();
    }

    public boolean isEscapedValue()
    {
        return true;
    }

    public boolean isPrimitive()
    {
        return false;
    }

    public boolean isSelfGeneratedId()
    {
        return false;
    }

    public boolean isValidForField(Field field)
    {
        if (classes.length != 0) goto _L2; else goto _L1
_L1:
        return true;
_L2:
        Class aclass[] = classes;
        int i = aclass.length;
        int j = 0;
label0:
        do
        {
label1:
            {
                if (j >= i)
                {
                    break label1;
                }
                if (aclass[j].isAssignableFrom(field.getType()))
                {
                    break label0;
                }
                j++;
            }
        } while (true);
        if (true) goto _L1; else goto _L3
_L3:
        return false;
    }

    public boolean isValidForVersion()
    {
        return false;
    }

    public boolean isValidGeneratedType()
    {
        return false;
    }

    public Object makeConfigObject(FieldType fieldtype)
    {
        return null;
    }

    public Object moveToNextValue(Object obj)
    {
        return null;
    }

    public abstract Object parseDefaultString(FieldType fieldtype, String s);

    public Object resultStringToJava(FieldType fieldtype, String s, int i)
    {
        return parseDefaultString(fieldtype, s);
    }

    public abstract Object resultToSqlArg(FieldType fieldtype, DatabaseResults databaseresults, int i);
}
