// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.j256.ormlite.stmt;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.db.DatabaseType;
import com.j256.ormlite.field.FieldType;
import com.j256.ormlite.logger.Logger;
import com.j256.ormlite.logger.LoggerFactory;
import com.j256.ormlite.stmt.mapped.MappedPreparedStmt;
import com.j256.ormlite.table.TableInfo;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package com.j256.ormlite.stmt:
//            Where, ArgumentHolder

public abstract class StatementBuilder
{

    private static Logger logger = LoggerFactory.getLogger(com/j256/ormlite/stmt/StatementBuilder);
    protected boolean addTableName;
    protected final Dao dao;
    protected final DatabaseType databaseType;
    protected final TableInfo tableInfo;
    protected final String tableName;
    protected StatementType type;
    protected Where where;

    public StatementBuilder(DatabaseType databasetype, TableInfo tableinfo, Dao dao1, StatementType statementtype)
    {
        where = null;
        databaseType = databasetype;
        tableInfo = tableinfo;
        tableName = tableinfo.getTableName();
        dao = dao1;
        type = statementtype;
        if (!statementtype.isOkForStatementBuilder())
        {
            throw new IllegalStateException((new StringBuilder("Building a statement from a ")).append(statementtype).append(" statement is not allowed").toString());
        } else
        {
            return;
        }
    }

    protected abstract void appendStatementEnd(StringBuilder stringbuilder, List list);

    protected abstract void appendStatementStart(StringBuilder stringbuilder, List list);

    protected void appendStatementString(StringBuilder stringbuilder, List list)
    {
        appendStatementStart(stringbuilder, list);
        appendWhereStatement(stringbuilder, list, WhereOperation.FIRST);
        appendStatementEnd(stringbuilder, list);
    }

    protected boolean appendWhereStatement(StringBuilder stringbuilder, List list, WhereOperation whereoperation)
    {
        if (where == null)
        {
            return whereoperation == WhereOperation.FIRST;
        }
        whereoperation.appendBefore(stringbuilder);
        Where where1 = where;
        String s;
        if (addTableName)
        {
            s = tableName;
        } else
        {
            s = null;
        }
        where1.appendSql(s, stringbuilder, list);
        whereoperation.appendAfter(stringbuilder);
        return false;
    }

    protected String buildStatementString(List list)
    {
        StringBuilder stringbuilder = new StringBuilder(128);
        appendStatementString(stringbuilder, list);
        String s = stringbuilder.toString();
        logger.debug("built statement {}", s);
        return s;
    }

    public void clear()
    {
        reset();
    }

    protected FieldType[] getResultFieldTypes()
    {
        return null;
    }

    StatementType getType()
    {
        return type;
    }

    protected MappedPreparedStmt prepareStatement(Long long1)
    {
        ArrayList arraylist = new ArrayList();
        String s = buildStatementString(arraylist);
        ArgumentHolder aargumentholder[] = (ArgumentHolder[])arraylist.toArray(new ArgumentHolder[arraylist.size()]);
        FieldType afieldtype[] = getResultFieldTypes();
        FieldType afieldtype1[] = new FieldType[arraylist.size()];
        for (int i = 0; i < aargumentholder.length; i++)
        {
            afieldtype1[i] = aargumentholder[i].getFieldType();
        }

        if (!type.isOkForStatementBuilder())
        {
            throw new IllegalStateException((new StringBuilder("Building a statement from a ")).append(type).append(" statement is not allowed").toString());
        }
        TableInfo tableinfo = tableInfo;
        Long long2;
        if (databaseType.isLimitSqlSupported())
        {
            long2 = null;
        } else
        {
            long2 = long1;
        }
        return new MappedPreparedStmt(tableinfo, s, afieldtype1, afieldtype, aargumentholder, long2, type);
    }

    public StatementInfo prepareStatementInfo()
    {
        ArrayList arraylist = new ArrayList();
        return new StatementInfo(buildStatementString(arraylist), arraylist, null);
    }

    public String prepareStatementString()
    {
        return buildStatementString(new ArrayList());
    }

    public void reset()
    {
        where = null;
    }

    public void setWhere(Where where1)
    {
        where = where1;
    }

    protected boolean shouldPrependTableNameToColumns()
    {
        return false;
    }

    protected FieldType verifyColumnName(String s)
    {
        return tableInfo.getFieldTypeByColumnName(s);
    }

    public Where where()
    {
        where = new Where(tableInfo, this, databaseType);
        return where;
    }


    private class StatementType extends Enum
    {

        private static final StatementType $VALUES[];
        public static final StatementType DELETE;
        public static final StatementType EXECUTE;
        public static final StatementType SELECT;
        public static final StatementType SELECT_LONG;
        public static final StatementType SELECT_RAW;
        public static final StatementType UPDATE;
        private final boolean okForExecute;
        private final boolean okForQuery;
        private final boolean okForStatementBuilder;
        private final boolean okForUpdate;

        public static StatementType valueOf(String s)
        {
            return (StatementType)Enum.valueOf(com/j256/ormlite/stmt/StatementBuilder$StatementType, s);
        }

        public static StatementType[] values()
        {
            return (StatementType[])$VALUES.clone();
        }

        public final boolean isOkForExecute()
        {
            return okForExecute;
        }

        public final boolean isOkForQuery()
        {
            return okForQuery;
        }

        public final boolean isOkForStatementBuilder()
        {
            return okForStatementBuilder;
        }

        public final boolean isOkForUpdate()
        {
            return okForUpdate;
        }

        static 
        {
            SELECT = new StatementType("SELECT", 0, true, true, false, false);
            SELECT_LONG = new StatementType("SELECT_LONG", 1, true, true, false, false);
            SELECT_RAW = new StatementType("SELECT_RAW", 2, true, true, false, false);
            UPDATE = new StatementType("UPDATE", 3, true, false, true, false);
            DELETE = new StatementType("DELETE", 4, true, false, true, false);
            EXECUTE = new StatementType("EXECUTE", 5, false, false, false, true);
            StatementType astatementtype[] = new StatementType[6];
            astatementtype[0] = SELECT;
            astatementtype[1] = SELECT_LONG;
            astatementtype[2] = SELECT_RAW;
            astatementtype[3] = UPDATE;
            astatementtype[4] = DELETE;
            astatementtype[5] = EXECUTE;
            $VALUES = astatementtype;
        }

        private StatementType(String s, int i, boolean flag, boolean flag1, boolean flag2, boolean flag3)
        {
            super(s, i);
            okForStatementBuilder = flag;
            okForQuery = flag1;
            okForUpdate = flag2;
            okForExecute = flag3;
        }
    }


    private class WhereOperation extends Enum
    {

        private static final WhereOperation $VALUES[];
        public static final WhereOperation AND;
        public static final WhereOperation FIRST;
        public static final WhereOperation OR;
        private final String after;
        private final String before;

        public static WhereOperation valueOf(String s)
        {
            return (WhereOperation)Enum.valueOf(com/j256/ormlite/stmt/StatementBuilder$WhereOperation, s);
        }

        public static WhereOperation[] values()
        {
            return (WhereOperation[])$VALUES.clone();
        }

        public final void appendAfter(StringBuilder stringbuilder)
        {
            if (after != null)
            {
                stringbuilder.append(after);
            }
        }

        public final void appendBefore(StringBuilder stringbuilder)
        {
            if (before != null)
            {
                stringbuilder.append(before);
            }
        }

        static 
        {
            FIRST = new WhereOperation("FIRST", 0, "WHERE ", null);
            AND = new WhereOperation("AND", 1, "AND (", ") ");
            OR = new WhereOperation("OR", 2, "OR (", ") ");
            WhereOperation awhereoperation[] = new WhereOperation[3];
            awhereoperation[0] = FIRST;
            awhereoperation[1] = AND;
            awhereoperation[2] = OR;
            $VALUES = awhereoperation;
        }

        private WhereOperation(String s, int i, String s1, String s2)
        {
            super(s, i);
            before = s1;
            after = s2;
        }
    }


    private class StatementInfo
    {

        private final List argList;
        private final String statement;

        public List getArgList()
        {
            return argList;
        }

        public String getStatement()
        {
            return statement;
        }

        private StatementInfo(String s, List list)
        {
            argList = list;
            statement = s;
        }

        StatementInfo(String s, List list, _cls1 _pcls1)
        {
            this(s, list);
        }
    }

}
