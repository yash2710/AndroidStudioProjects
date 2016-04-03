// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.j256.ormlite.stmt;

import com.j256.ormlite.field.FieldType;
import com.j256.ormlite.field.SqlType;
import java.sql.SQLException;

// Referenced classes of package com.j256.ormlite.stmt:
//            ArgumentHolder

public abstract class BaseArgumentHolder
    implements ArgumentHolder
{

    private String columnName;
    private FieldType fieldType;
    private SqlType sqlType;

    public BaseArgumentHolder()
    {
        columnName = null;
        fieldType = null;
        sqlType = null;
    }

    public BaseArgumentHolder(SqlType sqltype)
    {
        columnName = null;
        fieldType = null;
        sqlType = null;
        sqlType = sqltype;
    }

    public BaseArgumentHolder(String s)
    {
        columnName = null;
        fieldType = null;
        sqlType = null;
        columnName = s;
    }

    public String getColumnName()
    {
        return columnName;
    }

    public FieldType getFieldType()
    {
        return fieldType;
    }

    public Object getSqlArgValue()
    {
        if (!isValueSet())
        {
            throw new SQLException((new StringBuilder("Column value has not been set for ")).append(columnName).toString());
        }
        Object obj = getValue();
        if (obj == null)
        {
            obj = null;
        } else
        if (fieldType != null)
        {
            if (fieldType.isForeign() && fieldType.getType() == obj.getClass())
            {
                return fieldType.getForeignIdField().extractJavaFieldValue(obj);
            } else
            {
                return fieldType.convertJavaFieldToSqlArgValue(obj);
            }
        }
        return obj;
    }

    public SqlType getSqlType()
    {
        return sqlType;
    }

    protected abstract Object getValue();

    protected abstract boolean isValueSet();

    public void setMetaInfo(FieldType fieldtype)
    {
        if (fieldType != null && fieldType != fieldtype)
        {
            throw new IllegalArgumentException((new StringBuilder("FieldType name cannot be set twice from ")).append(fieldType).append(" to ").append(fieldtype).append(".  Using a SelectArg twice in query with different columns?").toString());
        } else
        {
            fieldType = fieldtype;
            return;
        }
    }

    public void setMetaInfo(String s)
    {
        if (columnName != null && !columnName.equals(s))
        {
            throw new IllegalArgumentException((new StringBuilder("Column name cannot be set twice from ")).append(columnName).append(" to ").append(s).append(".  Using a SelectArg twice in query with different columns?").toString());
        } else
        {
            columnName = s;
            return;
        }
    }

    public void setMetaInfo(String s, FieldType fieldtype)
    {
        setMetaInfo(s);
        setMetaInfo(fieldtype);
    }

    public abstract void setValue(Object obj);

    public String toString()
    {
        if (!isValueSet())
        {
            return "[unset]";
        }
        Object obj;
        String s;
        try
        {
            obj = getSqlArgValue();
        }
        catch (SQLException sqlexception)
        {
            return (new StringBuilder("[could not get value: ")).append(sqlexception).append("]").toString();
        }
        if (obj == null)
        {
            return "[null]";
        }
        s = obj.toString();
        return s;
    }
}
