// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.j256.ormlite.stmt;

import com.j256.ormlite.dao.CloseableIterator;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.GenericRawResults;
import com.j256.ormlite.db.DatabaseType;
import com.j256.ormlite.field.FieldType;
import com.j256.ormlite.stmt.query.OrderBy;
import com.j256.ormlite.table.TableInfo;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

// Referenced classes of package com.j256.ormlite.stmt:
//            StatementBuilder, ArgumentHolder, PreparedQuery

public class QueryBuilder extends StatementBuilder
{

    private boolean distinct;
    private List groupByList;
    private String groupByRaw;
    private String having;
    private final FieldType idField;
    private boolean isCountOfQuery;
    private boolean isInnerQuery;
    private List joinList;
    private Long limit;
    private Long offset;
    private ArgumentHolder orderByArgs[];
    private List orderByList;
    private String orderByRaw;
    private FieldType resultFieldTypes[];
    private List selectColumnList;
    private boolean selectIdColumn;
    private List selectRawList;

    public QueryBuilder(DatabaseType databasetype, TableInfo tableinfo, Dao dao)
    {
        super(databasetype, tableinfo, dao, StatementBuilder.StatementType.SELECT);
        selectIdColumn = true;
        idField = tableinfo.getIdField();
    }

    private void addJoinInfo(String s, QueryBuilder querybuilder, StatementBuilder.WhereOperation whereoperation)
    {
        JoinInfo joininfo = new JoinInfo(s, querybuilder, whereoperation);
        matchJoinedFields(joininfo, querybuilder);
        if (joinList == null)
        {
            joinList = new ArrayList();
        }
        joinList.add(joininfo);
    }

    private void addSelectColumnToList(String s)
    {
        verifyColumnName(s);
        selectColumnList.add(s);
    }

    private void appendColumnName(StringBuilder stringbuilder, String s)
    {
        if (addTableName)
        {
            databaseType.appendEscapedEntityName(stringbuilder, tableName);
            stringbuilder.append('.');
        }
        databaseType.appendEscapedEntityName(stringbuilder, s);
    }

    private void appendColumns(StringBuilder stringbuilder)
    {
        if (selectColumnList == null)
        {
            if (addTableName)
            {
                databaseType.appendEscapedEntityName(stringbuilder, tableName);
                stringbuilder.append('.');
            }
            stringbuilder.append("* ");
            resultFieldTypes = tableInfo.getFieldTypes();
            return;
        }
        boolean flag;
        ArrayList arraylist;
        Iterator iterator1;
        boolean flag1;
        boolean flag2;
        if (isInnerQuery)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        arraylist = new ArrayList(1 + selectColumnList.size());
        iterator1 = selectColumnList.iterator();
        flag1 = flag;
        flag2 = true;
        while (iterator1.hasNext()) 
        {
            String s = (String)iterator1.next();
            FieldType fieldtype = tableInfo.getFieldTypeByColumnName(s);
            if (fieldtype.isForeignCollection())
            {
                arraylist.add(fieldtype);
            } else
            {
                boolean flag3;
                if (flag2)
                {
                    flag2 = false;
                } else
                {
                    stringbuilder.append(',');
                }
                appendFieldColumnName(stringbuilder, fieldtype, arraylist);
                if (fieldtype == idField)
                {
                    flag3 = true;
                } else
                {
                    flag3 = flag1;
                }
                flag1 = flag3;
            }
        }
        if (!flag1 && selectIdColumn)
        {
            if (!flag2)
            {
                stringbuilder.append(',');
            }
            appendFieldColumnName(stringbuilder, idField, arraylist);
        }
        stringbuilder.append(' ');
        resultFieldTypes = (FieldType[])arraylist.toArray(new FieldType[arraylist.size()]);
    }

    private void appendFieldColumnName(StringBuilder stringbuilder, FieldType fieldtype, List list)
    {
        appendColumnName(stringbuilder, fieldtype.getColumnName());
        if (list != null)
        {
            list.add(fieldtype);
        }
    }

    private void appendGroupBys(StringBuilder stringbuilder)
    {
        boolean flag;
        if (hasGroupStuff())
        {
            appendGroupBys(stringbuilder, true);
            flag = false;
        } else
        {
            flag = true;
        }
        if (joinList != null)
        {
            Iterator iterator1 = joinList.iterator();
            do
            {
                if (!iterator1.hasNext())
                {
                    break;
                }
                JoinInfo joininfo = (JoinInfo)iterator1.next();
                if (joininfo.queryBuilder != null && joininfo.queryBuilder.hasGroupStuff())
                {
                    joininfo.queryBuilder.appendGroupBys(stringbuilder, flag);
                }
            } while (true);
        }
    }

    private void appendGroupBys(StringBuilder stringbuilder, boolean flag)
    {
        if (flag)
        {
            stringbuilder.append("GROUP BY ");
        }
        if (groupByRaw != null)
        {
            if (!flag)
            {
                stringbuilder.append(',');
            }
            stringbuilder.append(groupByRaw);
        } else
        {
            Iterator iterator1 = groupByList.iterator();
            while (iterator1.hasNext()) 
            {
                String s = (String)iterator1.next();
                if (flag)
                {
                    flag = false;
                } else
                {
                    stringbuilder.append(',');
                }
                appendColumnName(stringbuilder, s);
            }
        }
        stringbuilder.append(' ');
    }

    private void appendHaving(StringBuilder stringbuilder)
    {
        if (having != null)
        {
            stringbuilder.append("HAVING ").append(having).append(' ');
        }
    }

    private void appendJoinSql(StringBuilder stringbuilder)
    {
        Iterator iterator1 = joinList.iterator();
        do
        {
            if (!iterator1.hasNext())
            {
                break;
            }
            JoinInfo joininfo = (JoinInfo)iterator1.next();
            stringbuilder.append(joininfo.type).append(" JOIN ");
            databaseType.appendEscapedEntityName(stringbuilder, joininfo.queryBuilder.tableName);
            stringbuilder.append(" ON ");
            databaseType.appendEscapedEntityName(stringbuilder, tableName);
            stringbuilder.append('.');
            databaseType.appendEscapedEntityName(stringbuilder, joininfo.localField.getColumnName());
            stringbuilder.append(" = ");
            databaseType.appendEscapedEntityName(stringbuilder, joininfo.queryBuilder.tableName);
            stringbuilder.append('.');
            databaseType.appendEscapedEntityName(stringbuilder, joininfo.remoteField.getColumnName());
            stringbuilder.append(' ');
            if (joininfo.queryBuilder.joinList != null)
            {
                joininfo.queryBuilder.appendJoinSql(stringbuilder);
            }
        } while (true);
    }

    private void appendLimit(StringBuilder stringbuilder)
    {
        if (limit != null && databaseType.isLimitSqlSupported())
        {
            databaseType.appendLimitValue(stringbuilder, limit.longValue(), offset);
        }
    }

    private void appendOffset(StringBuilder stringbuilder)
    {
        if (offset != null)
        {
            if (databaseType.isOffsetLimitArgument())
            {
                if (limit == null)
                {
                    throw new SQLException("If the offset is specified, limit must also be specified with this database");
                }
            } else
            {
                databaseType.appendOffsetValue(stringbuilder, offset.longValue());
                return;
            }
        }
    }

    private void appendOrderBys(StringBuilder stringbuilder, List list)
    {
        boolean flag;
        if (hasOrderStuff())
        {
            appendOrderBys(stringbuilder, true, list);
            flag = false;
        } else
        {
            flag = true;
        }
        if (joinList != null)
        {
            Iterator iterator1 = joinList.iterator();
            do
            {
                if (!iterator1.hasNext())
                {
                    break;
                }
                JoinInfo joininfo = (JoinInfo)iterator1.next();
                if (joininfo.queryBuilder != null && joininfo.queryBuilder.hasOrderStuff())
                {
                    joininfo.queryBuilder.appendOrderBys(stringbuilder, flag, list);
                }
            } while (true);
        }
    }

    private void appendOrderBys(StringBuilder stringbuilder, boolean flag, List list)
    {
        if (flag)
        {
            stringbuilder.append("ORDER BY ");
        }
        if (orderByRaw != null)
        {
            if (!flag)
            {
                stringbuilder.append(',');
            }
            stringbuilder.append(orderByRaw);
            if (orderByArgs != null)
            {
                ArgumentHolder aargumentholder[] = orderByArgs;
                int i = aargumentholder.length;
                for (int j = 0; j < i; j++)
                {
                    list.add(aargumentholder[j]);
                }

            }
            flag = false;
        }
        if (orderByList != null)
        {
            Iterator iterator1 = orderByList.iterator();
            do
            {
                if (!iterator1.hasNext())
                {
                    break;
                }
                OrderBy orderby = (OrderBy)iterator1.next();
                if (flag)
                {
                    flag = false;
                } else
                {
                    stringbuilder.append(',');
                }
                appendColumnName(stringbuilder, orderby.getColumnName());
                if (!orderby.isAscending())
                {
                    stringbuilder.append(" DESC");
                }
            } while (true);
        }
        stringbuilder.append(' ');
    }

    private void appendSelectRaw(StringBuilder stringbuilder)
    {
        Iterator iterator1 = selectRawList.iterator();
        boolean flag = true;
        while (iterator1.hasNext()) 
        {
            String s = (String)iterator1.next();
            if (flag)
            {
                flag = false;
            } else
            {
                stringbuilder.append(", ");
            }
            stringbuilder.append(s);
        }
        stringbuilder.append(' ');
    }

    private boolean hasGroupStuff()
    {
        return groupByList != null && !groupByList.isEmpty() || groupByRaw != null;
    }

    private boolean hasOrderStuff()
    {
        return orderByList != null && !orderByList.isEmpty() || orderByRaw != null;
    }

    private void matchJoinedFields(JoinInfo joininfo, QueryBuilder querybuilder)
    {
        int i = 0;
        FieldType afieldtype[] = tableInfo.getFieldTypes();
        int j = afieldtype.length;
        for (int k = 0; k < j; k++)
        {
            FieldType fieldtype1 = afieldtype[k];
            FieldType fieldtype2 = fieldtype1.getForeignIdField();
            if (fieldtype1.isForeign() && fieldtype2.equals(querybuilder.tableInfo.getIdField()))
            {
                joininfo.localField = fieldtype1;
                joininfo.remoteField = fieldtype2;
                return;
            }
        }

        FieldType afieldtype1[] = querybuilder.tableInfo.getFieldTypes();
        for (int l = afieldtype1.length; i < l; i++)
        {
            FieldType fieldtype = afieldtype1[i];
            if (fieldtype.isForeign() && fieldtype.getForeignIdField().equals(idField))
            {
                joininfo.localField = idField;
                joininfo.remoteField = fieldtype;
                return;
            }
        }

        throw new SQLException((new StringBuilder("Could not find a foreign ")).append(tableInfo.getDataClass()).append(" field in ").append(querybuilder.tableInfo.getDataClass()).append(" or vice versa").toString());
    }

    private void setAddTableName(boolean flag)
    {
        addTableName = flag;
        if (joinList != null)
        {
            for (Iterator iterator1 = joinList.iterator(); iterator1.hasNext(); ((JoinInfo)iterator1.next()).queryBuilder.setAddTableName(flag)) { }
        }
    }

    protected void appendStatementEnd(StringBuilder stringbuilder, List list)
    {
        appendGroupBys(stringbuilder);
        appendHaving(stringbuilder);
        appendOrderBys(stringbuilder, list);
        if (!databaseType.isLimitAfterSelect())
        {
            appendLimit(stringbuilder);
        }
        appendOffset(stringbuilder);
        setAddTableName(false);
    }

    protected void appendStatementStart(StringBuilder stringbuilder, List list)
    {
        if (joinList == null)
        {
            setAddTableName(false);
        } else
        {
            setAddTableName(true);
        }
        stringbuilder.append("SELECT ");
        if (databaseType.isLimitAfterSelect())
        {
            appendLimit(stringbuilder);
        }
        if (distinct)
        {
            stringbuilder.append("DISTINCT ");
        }
        if (isCountOfQuery)
        {
            type = StatementBuilder.StatementType.SELECT_LONG;
            stringbuilder.append("COUNT(*) ");
        } else
        if (selectRawList != null && !selectRawList.isEmpty())
        {
            type = StatementBuilder.StatementType.SELECT_RAW;
            appendSelectRaw(stringbuilder);
        } else
        {
            type = StatementBuilder.StatementType.SELECT;
            appendColumns(stringbuilder);
        }
        stringbuilder.append("FROM ");
        databaseType.appendEscapedEntityName(stringbuilder, tableName);
        stringbuilder.append(' ');
        if (joinList != null)
        {
            appendJoinSql(stringbuilder);
        }
    }

    protected boolean appendWhereStatement(StringBuilder stringbuilder, List list, StatementBuilder.WhereOperation whereoperation)
    {
        boolean flag;
        boolean flag1;
        if (whereoperation == StatementBuilder.WhereOperation.FIRST)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (where != null)
        {
            flag = super.appendWhereStatement(stringbuilder, list, whereoperation);
        }
        if (joinList != null)
        {
            Iterator iterator1 = joinList.iterator();
            flag1 = flag;
            while (iterator1.hasNext()) 
            {
                JoinInfo joininfo = (JoinInfo)iterator1.next();
                StatementBuilder.WhereOperation whereoperation1;
                if (flag1)
                {
                    whereoperation1 = StatementBuilder.WhereOperation.FIRST;
                } else
                {
                    whereoperation1 = joininfo.operation;
                }
                flag1 = joininfo.queryBuilder.appendWhereStatement(stringbuilder, list, whereoperation1);
            }
        } else
        {
            flag1 = flag;
        }
        return flag1;
    }

    public void clear()
    {
        reset();
    }

    public long countOf()
    {
        setCountOf(true);
        return dao.countOf(prepare());
    }

    public QueryBuilder distinct()
    {
        distinct = true;
        selectIdColumn = false;
        return this;
    }

    void enableInnerQuery()
    {
        isInnerQuery = true;
    }

    protected FieldType[] getResultFieldTypes()
    {
        return resultFieldTypes;
    }

    int getSelectColumnCount()
    {
        if (isCountOfQuery)
        {
            return 1;
        }
        if (selectRawList != null && !selectRawList.isEmpty())
        {
            return selectRawList.size();
        }
        if (selectColumnList == null)
        {
            return 0;
        } else
        {
            return selectColumnList.size();
        }
    }

    List getSelectColumns()
    {
        if (isCountOfQuery)
        {
            return Collections.singletonList("COUNT(*)");
        }
        if (selectRawList != null && !selectRawList.isEmpty())
        {
            return selectRawList;
        }
        if (selectColumnList == null)
        {
            return Collections.emptyList();
        } else
        {
            return selectColumnList;
        }
    }

    public QueryBuilder groupBy(String s)
    {
        if (verifyColumnName(s).isForeignCollection())
        {
            throw new IllegalArgumentException((new StringBuilder("Can't groupBy foreign colletion field: ")).append(s).toString());
        }
        if (groupByList == null)
        {
            groupByList = new ArrayList();
        }
        groupByList.add(s);
        selectIdColumn = false;
        return this;
    }

    public QueryBuilder groupByRaw(String s)
    {
        groupByRaw = s;
        return this;
    }

    public QueryBuilder having(String s)
    {
        having = s;
        return this;
    }

    public CloseableIterator iterator()
    {
        return dao.iterator(prepare());
    }

    public QueryBuilder join(QueryBuilder querybuilder)
    {
        addJoinInfo("INNER", querybuilder, StatementBuilder.WhereOperation.AND);
        return this;
    }

    public QueryBuilder joinOr(QueryBuilder querybuilder)
    {
        addJoinInfo("INNER", querybuilder, StatementBuilder.WhereOperation.OR);
        return this;
    }

    public QueryBuilder leftJoin(QueryBuilder querybuilder)
    {
        addJoinInfo("LEFT", querybuilder, StatementBuilder.WhereOperation.AND);
        return this;
    }

    public QueryBuilder leftJoinOr(QueryBuilder querybuilder)
    {
        addJoinInfo("LEFT", querybuilder, StatementBuilder.WhereOperation.OR);
        return this;
    }

    public QueryBuilder limit(int i)
    {
        return limit(Long.valueOf(i));
    }

    public QueryBuilder limit(Long long1)
    {
        limit = long1;
        return this;
    }

    public QueryBuilder offset(int i)
    {
        return offset(Long.valueOf(i));
    }

    public QueryBuilder offset(Long long1)
    {
        if (databaseType.isOffsetSqlSupported())
        {
            offset = long1;
            return this;
        } else
        {
            throw new SQLException("Offset is not supported by this database");
        }
    }

    public QueryBuilder orderBy(String s, boolean flag)
    {
        if (verifyColumnName(s).isForeignCollection())
        {
            throw new IllegalArgumentException((new StringBuilder("Can't orderBy foreign colletion field: ")).append(s).toString());
        }
        if (orderByList == null)
        {
            orderByList = new ArrayList();
        }
        orderByList.add(new OrderBy(s, flag));
        return this;
    }

    public QueryBuilder orderByRaw(String s)
    {
        return orderByRaw(s, null);
    }

    public transient QueryBuilder orderByRaw(String s, ArgumentHolder aargumentholder[])
    {
        orderByRaw = s;
        orderByArgs = aargumentholder;
        return this;
    }

    public PreparedQuery prepare()
    {
        return super.prepareStatement(limit);
    }

    public List query()
    {
        return dao.query(prepare());
    }

    public Object queryForFirst()
    {
        return dao.queryForFirst(prepare());
    }

    public GenericRawResults queryRaw()
    {
        return dao.queryRaw(prepareStatementString(), new String[0]);
    }

    public String[] queryRawFirst()
    {
        return (String[])dao.queryRaw(prepareStatementString(), new String[0]).getFirstResult();
    }

    public void reset()
    {
        super.reset();
        distinct = false;
        selectIdColumn = true;
        selectColumnList = null;
        selectRawList = null;
        orderByList = null;
        orderByRaw = null;
        groupByList = null;
        groupByRaw = null;
        isInnerQuery = false;
        isCountOfQuery = false;
        having = null;
        limit = null;
        offset = null;
        if (joinList != null)
        {
            joinList.clear();
            joinList = null;
        }
        addTableName = false;
    }

    public QueryBuilder selectColumns(Iterable iterable)
    {
        if (selectColumnList == null)
        {
            selectColumnList = new ArrayList();
        }
        for (Iterator iterator1 = iterable.iterator(); iterator1.hasNext(); addSelectColumnToList((String)iterator1.next())) { }
        return this;
    }

    public transient QueryBuilder selectColumns(String as[])
    {
        if (selectColumnList == null)
        {
            selectColumnList = new ArrayList();
        }
        int i = as.length;
        for (int j = 0; j < i; j++)
        {
            addSelectColumnToList(as[j]);
        }

        return this;
    }

    public transient QueryBuilder selectRaw(String as[])
    {
        if (selectRawList == null)
        {
            selectRawList = new ArrayList();
        }
        int i = as.length;
        for (int j = 0; j < i; j++)
        {
            String s = as[j];
            selectRawList.add(s);
        }

        return this;
    }

    public QueryBuilder setCountOf(boolean flag)
    {
        isCountOfQuery = flag;
        return this;
    }

    protected boolean shouldPrependTableNameToColumns()
    {
        return joinList != null;
    }

    private class JoinInfo
    {

        FieldType localField;
        StatementBuilder.WhereOperation operation;
        final QueryBuilder queryBuilder;
        FieldType remoteField;
        final QueryBuilder this$0;
        final String type;

        public JoinInfo(String s, QueryBuilder querybuilder1, StatementBuilder.WhereOperation whereoperation)
        {
            this$0 = QueryBuilder.this;
            super();
            type = s;
            queryBuilder = querybuilder1;
            operation = whereoperation;
        }
    }

}
