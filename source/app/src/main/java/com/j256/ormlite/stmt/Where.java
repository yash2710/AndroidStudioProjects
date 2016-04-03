// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.j256.ormlite.stmt;

import com.j256.ormlite.dao.CloseableIterator;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.GenericRawResults;
import com.j256.ormlite.db.DatabaseType;
import com.j256.ormlite.field.FieldType;
import com.j256.ormlite.stmt.query.Between;
import com.j256.ormlite.stmt.query.Clause;
import com.j256.ormlite.stmt.query.Exists;
import com.j256.ormlite.stmt.query.In;
import com.j256.ormlite.stmt.query.InSubQuery;
import com.j256.ormlite.stmt.query.IsNotNull;
import com.j256.ormlite.stmt.query.IsNull;
import com.j256.ormlite.stmt.query.ManyClause;
import com.j256.ormlite.stmt.query.NeedsFutureClause;
import com.j256.ormlite.stmt.query.Not;
import com.j256.ormlite.stmt.query.Raw;
import com.j256.ormlite.stmt.query.SimpleComparison;
import com.j256.ormlite.table.TableInfo;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Referenced classes of package com.j256.ormlite.stmt:
//            QueryBuilder, StatementBuilder, PreparedStmt, ArgumentHolder, 
//            PreparedQuery

public class Where
{

    private static final int CLAUSE_STACK_START_SIZE = 4;
    private Clause clauseStack[];
    private int clauseStackLevel;
    private final DatabaseType databaseType;
    private final String idColumnName;
    private final FieldType idFieldType;
    private NeedsFutureClause needsFuture;
    private final StatementBuilder statementBuilder;
    private final TableInfo tableInfo;

    Where(TableInfo tableinfo, StatementBuilder statementbuilder, DatabaseType databasetype)
    {
        clauseStack = new Clause[4];
        needsFuture = null;
        tableInfo = tableinfo;
        statementBuilder = statementbuilder;
        idFieldType = tableinfo.getIdField();
        if (idFieldType == null)
        {
            idColumnName = null;
        } else
        {
            idColumnName = idFieldType.getColumnName();
        }
        databaseType = databasetype;
    }

    private void addClause(Clause clause)
    {
        if (needsFuture == null)
        {
            push(clause);
            return;
        } else
        {
            needsFuture.setMissingClause(clause);
            needsFuture = null;
            return;
        }
    }

    private void addNeedsFuture(NeedsFutureClause needsfutureclause)
    {
        if (needsFuture != null)
        {
            throw new IllegalStateException((new StringBuilder()).append(needsFuture).append(" is already waiting for a future clause, can't add: ").append(needsfutureclause).toString());
        } else
        {
            needsFuture = needsfutureclause;
            return;
        }
    }

    private Clause[] buildClauseArray(Where awhere[], String s)
    {
        Clause aclause[];
        if (awhere.length == 0)
        {
            aclause = null;
        } else
        {
            aclause = new Clause[awhere.length];
            int i = -1 + awhere.length;
            while (i >= 0) 
            {
                aclause[i] = pop(s);
                i--;
            }
        }
        return aclause;
    }

    private QueryBuilder checkQueryBuilderMethod(String s)
    {
        if (statementBuilder instanceof QueryBuilder)
        {
            return (QueryBuilder)statementBuilder;
        } else
        {
            throw new SQLException((new StringBuilder("Cannot call ")).append(s).append(" on a statement of type ").append(statementBuilder.getType()).toString());
        }
    }

    private FieldType findColumnFieldType(String s)
    {
        return tableInfo.getFieldTypeByColumnName(s);
    }

    private Where in(boolean flag, String s, QueryBuilder querybuilder)
    {
        if (querybuilder.getSelectColumnCount() != 1)
        {
            if (querybuilder.getSelectColumnCount() == 0)
            {
                throw new SQLException("Inner query must have only 1 select column specified instead of *");
            } else
            {
                throw new SQLException((new StringBuilder("Inner query must have only 1 select column specified instead of ")).append(querybuilder.getSelectColumnCount()).append(": ").append(Arrays.toString(querybuilder.getSelectColumns().toArray(new String[0]))).toString());
            }
        } else
        {
            querybuilder.enableInnerQuery();
            addClause(new InSubQuery(s, findColumnFieldType(s), new QueryBuilder.InternalQueryBuilderWrapper(querybuilder), flag));
            return this;
        }
    }

    private transient Where in(boolean flag, String s, Object aobj[])
    {
        if (aobj.length == 1)
        {
            if (aobj[0].getClass().isArray())
            {
                StringBuilder stringbuilder = new StringBuilder("Object argument to ");
                String s1;
                if (flag)
                {
                    s1 = "IN";
                } else
                {
                    s1 = "notId";
                }
                throw new IllegalArgumentException(stringbuilder.append(s1).append(" seems to be an array within an array").toString());
            }
            if (aobj[0] instanceof Where)
            {
                StringBuilder stringbuilder1 = new StringBuilder("Object argument to ");
                String s2;
                if (flag)
                {
                    s2 = "IN";
                } else
                {
                    s2 = "notId";
                }
                throw new IllegalArgumentException(stringbuilder1.append(s2).append(" seems to be a Where object, did you mean the QueryBuilder?").toString());
            }
            if (aobj[0] instanceof PreparedStmt)
            {
                StringBuilder stringbuilder2 = new StringBuilder("Object argument to ");
                String s3;
                if (flag)
                {
                    s3 = "IN";
                } else
                {
                    s3 = "notId";
                }
                throw new IllegalArgumentException(stringbuilder2.append(s3).append(" seems to be a prepared statement, did you mean the QueryBuilder?").toString());
            }
        }
        addClause(new In(s, findColumnFieldType(s), aobj, flag));
        return this;
    }

    private Clause peek()
    {
        return clauseStack[-1 + clauseStackLevel];
    }

    private Clause pop(String s)
    {
        if (clauseStackLevel == 0)
        {
            throw new IllegalStateException((new StringBuilder("Expecting there to be a clause already defined for '")).append(s).append("' operation").toString());
        } else
        {
            Clause aclause[] = clauseStack;
            int i = -1 + clauseStackLevel;
            clauseStackLevel = i;
            Clause clause = aclause[i];
            clauseStack[clauseStackLevel] = null;
            return clause;
        }
    }

    private void push(Clause clause)
    {
        if (clauseStackLevel == clauseStack.length)
        {
            Clause aclause1[] = new Clause[clauseStackLevel << 1];
            for (int j = 0; j < clauseStackLevel; j++)
            {
                aclause1[j] = clauseStack[j];
                clauseStack[j] = null;
            }

            clauseStack = aclause1;
        }
        Clause aclause[] = clauseStack;
        int i = clauseStackLevel;
        clauseStackLevel = i + 1;
        aclause[i] = clause;
    }

    public Where and()
    {
        ManyClause manyclause = new ManyClause(pop("AND"), "AND");
        push(manyclause);
        addNeedsFuture(manyclause);
        return this;
    }

    public Where and(int i)
    {
        if (i == 0)
        {
            throw new IllegalArgumentException("Must have at least one clause in and(numClauses)");
        }
        Clause aclause[] = new Clause[i];
        for (int j = i - 1; j >= 0; j--)
        {
            aclause[j] = pop("AND");
        }

        addClause(new ManyClause(aclause, "AND"));
        return this;
    }

    public transient Where and(Where where, Where where1, Where awhere[])
    {
        Clause aclause[] = buildClauseArray(awhere, "AND");
        Clause clause = pop("AND");
        addClause(new ManyClause(pop("AND"), clause, aclause, "AND"));
        return this;
    }

    void appendSql(String s, StringBuilder stringbuilder, List list)
    {
        if (clauseStackLevel == 0)
        {
            throw new IllegalStateException("No where clauses defined.  Did you miss a where operation?");
        }
        if (clauseStackLevel != 1)
        {
            throw new IllegalStateException("Both the \"left-hand\" and \"right-hand\" clauses have been defined.  Did you miss an AND or OR?");
        }
        if (needsFuture != null)
        {
            throw new IllegalStateException("The SQL statement has not been finished since there are previous operations still waiting for clauses.");
        } else
        {
            peek().appendSql(databaseType, s, stringbuilder, list);
            return;
        }
    }

    public Where between(String s, Object obj, Object obj1)
    {
        addClause(new Between(s, findColumnFieldType(s), obj, obj1));
        return this;
    }

    public Where clear()
    {
        return reset();
    }

    public long countOf()
    {
        return checkQueryBuilderMethod("countOf()").countOf();
    }

    public Where eq(String s, Object obj)
    {
        addClause(new SimpleComparison(s, findColumnFieldType(s), obj, "="));
        return this;
    }

    public Where exists(QueryBuilder querybuilder)
    {
        querybuilder.enableInnerQuery();
        addClause(new Exists(new QueryBuilder.InternalQueryBuilderWrapper(querybuilder)));
        return this;
    }

    public Where ge(String s, Object obj)
    {
        addClause(new SimpleComparison(s, findColumnFieldType(s), obj, ">="));
        return this;
    }

    public String getStatement()
    {
        StringBuilder stringbuilder = new StringBuilder();
        appendSql(null, stringbuilder, new ArrayList());
        return stringbuilder.toString();
    }

    public Where gt(String s, Object obj)
    {
        addClause(new SimpleComparison(s, findColumnFieldType(s), obj, ">"));
        return this;
    }

    public Where idEq(Dao dao, Object obj)
    {
        if (idColumnName == null)
        {
            throw new SQLException("Object has no id column specified");
        } else
        {
            addClause(new SimpleComparison(idColumnName, idFieldType, dao.extractId(obj), "="));
            return this;
        }
    }

    public Where idEq(Object obj)
    {
        if (idColumnName == null)
        {
            throw new SQLException("Object has no id column specified");
        } else
        {
            addClause(new SimpleComparison(idColumnName, idFieldType, obj, "="));
            return this;
        }
    }

    public Where in(String s, QueryBuilder querybuilder)
    {
        return in(true, s, querybuilder);
    }

    public Where in(String s, Iterable iterable)
    {
        addClause(new In(s, findColumnFieldType(s), iterable, true));
        return this;
    }

    public transient Where in(String s, Object aobj[])
    {
        return in(true, s, aobj);
    }

    public Where isNotNull(String s)
    {
        addClause(new IsNotNull(s, findColumnFieldType(s)));
        return this;
    }

    public Where isNull(String s)
    {
        addClause(new IsNull(s, findColumnFieldType(s)));
        return this;
    }

    public CloseableIterator iterator()
    {
        return checkQueryBuilderMethod("iterator()").iterator();
    }

    public Where le(String s, Object obj)
    {
        addClause(new SimpleComparison(s, findColumnFieldType(s), obj, "<="));
        return this;
    }

    public Where like(String s, Object obj)
    {
        addClause(new SimpleComparison(s, findColumnFieldType(s), obj, "LIKE"));
        return this;
    }

    public Where lt(String s, Object obj)
    {
        addClause(new SimpleComparison(s, findColumnFieldType(s), obj, "<"));
        return this;
    }

    public Where ne(String s, Object obj)
    {
        addClause(new SimpleComparison(s, findColumnFieldType(s), obj, "<>"));
        return this;
    }

    public Where not()
    {
        Not not1 = new Not();
        addClause(not1);
        addNeedsFuture(not1);
        return this;
    }

    public Where not(Where where)
    {
        addClause(new Not(pop("NOT")));
        return this;
    }

    public Where notIn(String s, QueryBuilder querybuilder)
    {
        return in(false, s, querybuilder);
    }

    public Where notIn(String s, Iterable iterable)
    {
        addClause(new In(s, findColumnFieldType(s), iterable, false));
        return this;
    }

    public transient Where notIn(String s, Object aobj[])
    {
        return in(false, s, aobj);
    }

    public Where or()
    {
        ManyClause manyclause = new ManyClause(pop("OR"), "OR");
        push(manyclause);
        addNeedsFuture(manyclause);
        return this;
    }

    public Where or(int i)
    {
        if (i == 0)
        {
            throw new IllegalArgumentException("Must have at least one clause in or(numClauses)");
        }
        Clause aclause[] = new Clause[i];
        for (int j = i - 1; j >= 0; j--)
        {
            aclause[j] = pop("OR");
        }

        addClause(new ManyClause(aclause, "OR"));
        return this;
    }

    public transient Where or(Where where, Where where1, Where awhere[])
    {
        Clause aclause[] = buildClauseArray(awhere, "OR");
        Clause clause = pop("OR");
        addClause(new ManyClause(pop("OR"), clause, aclause, "OR"));
        return this;
    }

    public PreparedQuery prepare()
    {
        return statementBuilder.prepareStatement(null);
    }

    public List query()
    {
        return checkQueryBuilderMethod("query()").query();
    }

    public Object queryForFirst()
    {
        return checkQueryBuilderMethod("queryForFirst()").queryForFirst();
    }

    public GenericRawResults queryRaw()
    {
        return checkQueryBuilderMethod("queryRaw()").queryRaw();
    }

    public String[] queryRawFirst()
    {
        return checkQueryBuilderMethod("queryRawFirst()").queryRawFirst();
    }

    public transient Where raw(String s, ArgumentHolder aargumentholder[])
    {
        int i = aargumentholder.length;
        for (int j = 0; j < i; j++)
        {
            ArgumentHolder argumentholder = aargumentholder[j];
            String s1 = argumentholder.getColumnName();
            if (s1 == null)
            {
                if (argumentholder.getSqlType() == null)
                {
                    throw new IllegalArgumentException("Either the column name or SqlType must be set on each argument");
                }
            } else
            {
                argumentholder.setMetaInfo(findColumnFieldType(s1));
            }
        }

        addClause(new Raw(s, aargumentholder));
        return this;
    }

    public Where rawComparison(String s, String s1, Object obj)
    {
        addClause(new SimpleComparison(s, findColumnFieldType(s), obj, s1));
        return this;
    }

    public Where reset()
    {
        for (int i = 0; i < clauseStackLevel; i++)
        {
            clauseStack[i] = null;
        }

        clauseStackLevel = 0;
        return this;
    }

    public String toString()
    {
        if (clauseStackLevel == 0)
        {
            return "empty where clause";
        } else
        {
            Clause clause = peek();
            return (new StringBuilder("where clause: ")).append(clause).toString();
        }
    }
}
