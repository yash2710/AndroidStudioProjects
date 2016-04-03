// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.j256.ormlite.stmt.query;

import com.j256.ormlite.db.DatabaseType;
import com.j256.ormlite.field.FieldType;
import com.j256.ormlite.stmt.ArgumentHolder;
import com.j256.ormlite.stmt.ColumnArg;
import com.j256.ormlite.stmt.SelectArg;
import java.sql.SQLException;
import java.util.List;

// Referenced classes of package com.j256.ormlite.stmt.query:
//            Comparison

abstract class BaseComparison
    implements Comparison
{

    private static final String NUMBER_CHARACTERS = "0123456789.-+";
    protected final String columnName;
    protected final FieldType fieldType;
    private final Object value;

    protected BaseComparison(String s, FieldType fieldtype, Object obj, boolean flag)
    {
        if (flag && fieldtype != null && !fieldtype.isComparable())
        {
            throw new SQLException((new StringBuilder("Field '")).append(s).append("' is of data type ").append(fieldtype.getDataPersister()).append(" which can not be compared").toString());
        } else
        {
            columnName = s;
            fieldType = fieldtype;
            value = obj;
            return;
        }
    }

    protected void appendArgOrValue(DatabaseType databasetype, FieldType fieldtype, StringBuilder stringbuilder, List list, Object obj)
    {
        boolean flag = true;
        if (obj == null)
        {
            throw new SQLException((new StringBuilder("argument for '")).append(fieldtype.getFieldName()).append("' is null").toString());
        }
        if (obj instanceof ArgumentHolder)
        {
            stringbuilder.append('?');
            ArgumentHolder argumentholder = (ArgumentHolder)obj;
            argumentholder.setMetaInfo(columnName, fieldtype);
            list.add(argumentholder);
        } else
        if (obj instanceof ColumnArg)
        {
            ColumnArg columnarg = (ColumnArg)obj;
            String s1 = columnarg.getTableName();
            if (s1 != null)
            {
                databasetype.appendEscapedEntityName(stringbuilder, s1);
                stringbuilder.append('.');
            }
            databasetype.appendEscapedEntityName(stringbuilder, columnarg.getColumnName());
        } else
        if (fieldtype.isArgumentHolderRequired())
        {
            stringbuilder.append('?');
            SelectArg selectarg = new SelectArg();
            selectarg.setMetaInfo(columnName, fieldtype);
            selectarg.setValue(obj);
            list.add(selectarg);
        } else
        if (fieldtype.isForeign() && fieldtype.getType().isAssignableFrom(obj.getClass()))
        {
            FieldType fieldtype1 = fieldtype.getForeignIdField();
            appendArgOrValue(databasetype, fieldtype1, stringbuilder, list, fieldtype1.extractJavaFieldValue(obj));
            flag = false;
        } else
        if (fieldtype.isEscapedValue())
        {
            databasetype.appendEscapedWord(stringbuilder, fieldtype.convertJavaFieldToSqlArgValue(obj).toString());
        } else
        if (fieldtype.isForeign())
        {
            String s = fieldtype.convertJavaFieldToSqlArgValue(obj).toString();
            if (s.length() > 0 && "0123456789.-+".indexOf(s.charAt(0)) < 0)
            {
                throw new SQLException((new StringBuilder("Foreign field ")).append(fieldtype).append(" does not seem to be producing a numerical value '").append(s).append("'. Maybe you are passing the wrong object to comparison: ").append(this).toString());
            }
            stringbuilder.append(s);
        } else
        {
            stringbuilder.append(fieldtype.convertJavaFieldToSqlArgValue(obj));
        }
        if (flag)
        {
            stringbuilder.append(' ');
        }
    }

    public abstract void appendOperation(StringBuilder stringbuilder);

    public void appendSql(DatabaseType databasetype, String s, StringBuilder stringbuilder, List list)
    {
        if (s != null)
        {
            databasetype.appendEscapedEntityName(stringbuilder, s);
            stringbuilder.append('.');
        }
        databasetype.appendEscapedEntityName(stringbuilder, columnName);
        stringbuilder.append(' ');
        appendOperation(stringbuilder);
        appendValue(databasetype, stringbuilder, list);
    }

    public void appendValue(DatabaseType databasetype, StringBuilder stringbuilder, List list)
    {
        appendArgOrValue(databasetype, fieldType, stringbuilder, list, value);
    }

    public String getColumnName()
    {
        return columnName;
    }

    public String toString()
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append(columnName).append(' ');
        appendOperation(stringbuilder);
        stringbuilder.append(' ');
        stringbuilder.append(value);
        return stringbuilder.toString();
    }
}
