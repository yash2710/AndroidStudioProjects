// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.j256.ormlite.stmt.mapped;

import com.j256.ormlite.field.FieldType;
import com.j256.ormlite.logger.Logger;
import com.j256.ormlite.stmt.ArgumentHolder;
import com.j256.ormlite.stmt.PreparedDelete;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.PreparedUpdate;
import com.j256.ormlite.support.CompiledStatement;
import com.j256.ormlite.support.DatabaseConnection;
import com.j256.ormlite.table.TableInfo;
import java.sql.SQLException;

// Referenced classes of package com.j256.ormlite.stmt.mapped:
//            BaseMappedQuery

public class MappedPreparedStmt extends BaseMappedQuery
    implements PreparedDelete, PreparedQuery, PreparedUpdate
{

    private final ArgumentHolder argHolders[];
    private final Long limit;
    private final com.j256.ormlite.stmt.StatementBuilder.StatementType type;

    public MappedPreparedStmt(TableInfo tableinfo, String s, FieldType afieldtype[], FieldType afieldtype1[], ArgumentHolder aargumentholder[], Long long1, com.j256.ormlite.stmt.StatementBuilder.StatementType statementtype)
    {
        super(tableinfo, s, afieldtype, afieldtype1);
        argHolders = aargumentholder;
        limit = long1;
        type = statementtype;
    }

    private CompiledStatement assignStatementArguments(CompiledStatement compiledstatement)
    {
        boolean flag;
        if (limit != null)
        {
            compiledstatement.setMaxRows(limit.intValue());
        }
        flag = logger.isLevelEnabled(com.j256.ormlite.logger.Log.Level.TRACE);
        Object aobj[] = null;
        if (!flag) goto _L2; else goto _L1
_L1:
        int i = argHolders.length;
        aobj = null;
        if (i <= 0) goto _L2; else goto _L3
_L3:
        aobj = new Object[argHolders.length];
          goto _L2
_L12:
        int j;
        if (j >= argHolders.length) goto _L5; else goto _L4
_L4:
        Object obj;
        FieldType fieldtype;
        obj = argHolders[j].getSqlArgValue();
        fieldtype = argFieldTypes[j];
        if (fieldtype != null) goto _L7; else goto _L6
_L6:
        com.j256.ormlite.field.SqlType sqltype = argHolders[j].getSqlType();
_L10:
        compiledstatement.setObject(j, obj, sqltype);
        if (aobj == null) goto _L9; else goto _L8
_L8:
        aobj[j] = obj;
          goto _L9
_L7:
        sqltype = fieldtype.getSqlType();
          goto _L10
_L5:
        logger.debug("prepared statement '{}' with {} args", statement, Integer.valueOf(argHolders.length));
        if (aobj == null)
        {
            break MISSING_BLOCK_LABEL_189;
        }
        logger.trace("prepared statement arguments: {}", ((Object) (aobj)));
        return compiledstatement;
        Exception exception;
        exception;
        compiledstatement.close();
        throw exception;
_L2:
        j = 0;
        continue; /* Loop/switch isn't completed */
_L9:
        j++;
        if (true) goto _L12; else goto _L11
_L11:
    }

    public CompiledStatement compile(DatabaseConnection databaseconnection, com.j256.ormlite.stmt.StatementBuilder.StatementType statementtype)
    {
        return compile(databaseconnection, statementtype, -1);
    }

    public CompiledStatement compile(DatabaseConnection databaseconnection, com.j256.ormlite.stmt.StatementBuilder.StatementType statementtype, int i)
    {
        if (type != statementtype)
        {
            throw new SQLException((new StringBuilder("Could not compile this ")).append(type).append(" statement since the caller is expecting a ").append(statementtype).append(" statement.  Check your QueryBuilder methods.").toString());
        } else
        {
            return assignStatementArguments(databaseconnection.compileStatement(statement, statementtype, argFieldTypes, i));
        }
    }

    public String getStatement()
    {
        return statement;
    }

    public com.j256.ormlite.stmt.StatementBuilder.StatementType getType()
    {
        return type;
    }

    public void setArgumentHolderValue(int i, Object obj)
    {
        if (i < 0)
        {
            throw new SQLException((new StringBuilder("argument holder index ")).append(i).append(" must be >= 0").toString());
        }
        if (argHolders.length <= i)
        {
            throw new SQLException((new StringBuilder("argument holder index ")).append(i).append(" is not valid, only ").append(argHolders.length).append(" in statement (index starts at 0)").toString());
        } else
        {
            argHolders[i].setValue(obj);
            return;
        }
    }
}
