// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.j256.ormlite.stmt.mapped;

import com.j256.ormlite.db.DatabaseType;
import com.j256.ormlite.field.FieldType;
import com.j256.ormlite.logger.Logger;
import com.j256.ormlite.logger.LoggerFactory;
import com.j256.ormlite.table.TableInfo;
import java.util.List;

public abstract class BaseMappedStatement
{

    protected static Logger logger = LoggerFactory.getLogger(com/j256/ormlite/stmt/mapped/BaseMappedStatement);
    protected final FieldType argFieldTypes[];
    protected final Class clazz;
    protected final FieldType idField;
    protected final String statement;
    protected final TableInfo tableInfo;

    protected BaseMappedStatement(TableInfo tableinfo, String s, FieldType afieldtype[])
    {
        tableInfo = tableinfo;
        clazz = tableinfo.getDataClass();
        idField = tableinfo.getIdField();
        statement = s;
        argFieldTypes = afieldtype;
    }

    static void appendFieldColumnName(DatabaseType databasetype, StringBuilder stringbuilder, FieldType fieldtype, List list)
    {
        databasetype.appendEscapedEntityName(stringbuilder, fieldtype.getColumnName());
        if (list != null)
        {
            list.add(fieldtype);
        }
        stringbuilder.append(' ');
    }

    static void appendTableName(DatabaseType databasetype, StringBuilder stringbuilder, String s, String s1)
    {
        if (s != null)
        {
            stringbuilder.append(s);
        }
        databasetype.appendEscapedEntityName(stringbuilder, s1);
        stringbuilder.append(' ');
    }

    static void appendWhereFieldEq(DatabaseType databasetype, FieldType fieldtype, StringBuilder stringbuilder, List list)
    {
        stringbuilder.append("WHERE ");
        appendFieldColumnName(databasetype, stringbuilder, fieldtype, list);
        stringbuilder.append("= ?");
    }

    protected Object convertIdToFieldObject(Object obj)
    {
        return idField.convertJavaFieldToSqlArgValue(obj);
    }

    protected Object[] getFieldObjects(Object obj)
    {
        Object aobj[] = new Object[argFieldTypes.length];
        int i = 0;
        while (i < argFieldTypes.length) 
        {
            FieldType fieldtype = argFieldTypes[i];
            if (fieldtype.isAllowGeneratedIdInsert())
            {
                aobj[i] = fieldtype.getFieldValueIfNotDefault(obj);
            } else
            {
                aobj[i] = fieldtype.extractJavaFieldToSqlArgValue(obj);
            }
            if (aobj[i] == null && fieldtype.getDefaultValue() != null)
            {
                aobj[i] = fieldtype.getDefaultValue();
            }
            i++;
        }
        return aobj;
    }

    public String toString()
    {
        return (new StringBuilder("MappedStatement: ")).append(statement).toString();
    }

}
