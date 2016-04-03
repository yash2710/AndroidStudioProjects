// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.j256.ormlite.stmt;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.GenericRawResults;
import com.j256.ormlite.dao.ObjectCache;
import com.j256.ormlite.dao.RawRowMapper;
import com.j256.ormlite.dao.RawRowObjectMapper;
import com.j256.ormlite.db.DatabaseType;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.FieldType;
import com.j256.ormlite.field.SqlType;
import com.j256.ormlite.logger.Logger;
import com.j256.ormlite.logger.LoggerFactory;
import com.j256.ormlite.misc.SqlExceptionUtil;
import com.j256.ormlite.misc.TransactionManager;
import com.j256.ormlite.stmt.mapped.MappedCreate;
import com.j256.ormlite.stmt.mapped.MappedDelete;
import com.j256.ormlite.stmt.mapped.MappedDeleteCollection;
import com.j256.ormlite.stmt.mapped.MappedQueryForId;
import com.j256.ormlite.stmt.mapped.MappedRefresh;
import com.j256.ormlite.stmt.mapped.MappedUpdate;
import com.j256.ormlite.stmt.mapped.MappedUpdateId;
import com.j256.ormlite.support.CompiledStatement;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.support.DatabaseConnection;
import com.j256.ormlite.support.DatabaseResults;
import com.j256.ormlite.table.TableInfo;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;

// Referenced classes of package com.j256.ormlite.stmt:
//            GenericRowMapper, QueryBuilder, PreparedStmt, SelectIterator, 
//            PreparedDelete, RawRowMapperImpl, SelectArg, Where, 
//            RawResultsImpl, PreparedUpdate, PreparedQuery

public class StatementExecutor
    implements GenericRowMapper
{

    private static Logger logger = LoggerFactory.getLogger(com/j256/ormlite/stmt/StatementExecutor);
    private static final FieldType noFieldTypes[] = new FieldType[0];
    private String countStarQuery;
    private final Dao dao;
    private final DatabaseType databaseType;
    private FieldType ifExistsFieldTypes[];
    private String ifExistsQuery;
    private MappedDelete mappedDelete;
    private MappedCreate mappedInsert;
    private MappedQueryForId mappedQueryForId;
    private MappedRefresh mappedRefresh;
    private MappedUpdate mappedUpdate;
    private MappedUpdateId mappedUpdateId;
    private PreparedQuery preparedQueryForAll;
    private RawRowMapper rawRowMapper;
    private final TableInfo tableInfo;

    public StatementExecutor(DatabaseType databasetype, TableInfo tableinfo, Dao dao1)
    {
        databaseType = databasetype;
        tableInfo = tableinfo;
        dao = dao1;
    }

    private void assignStatementArguments(CompiledStatement compiledstatement, String as[])
    {
        for (int i = 0; i < as.length; i++)
        {
            compiledstatement.setObject(i, as[i], SqlType.STRING);
        }

    }

    private void prepareQueryForAll()
    {
        if (preparedQueryForAll == null)
        {
            preparedQueryForAll = (new QueryBuilder(databaseType, tableInfo, dao)).prepare();
        }
    }

    public SelectIterator buildIterator(BaseDaoImpl basedaoimpl, ConnectionSource connectionsource, int i, ObjectCache objectcache)
    {
        prepareQueryForAll();
        return buildIterator(basedaoimpl, connectionsource, ((PreparedStmt) (preparedQueryForAll)), objectcache, i);
    }

    public SelectIterator buildIterator(BaseDaoImpl basedaoimpl, ConnectionSource connectionsource, PreparedStmt preparedstmt, ObjectCache objectcache, int i)
    {
        DatabaseConnection databaseconnection;
        CompiledStatement compiledstatement;
        databaseconnection = connectionsource.getReadOnlyConnection();
        compiledstatement = null;
        SelectIterator selectiterator;
        compiledstatement = preparedstmt.compile(databaseconnection, StatementBuilder.StatementType.SELECT, i);
        selectiterator = new SelectIterator(tableInfo.getDataClass(), basedaoimpl, preparedstmt, connectionsource, databaseconnection, compiledstatement, preparedstmt.getStatement(), objectcache);
        return selectiterator;
        Exception exception;
        exception;
        if (compiledstatement != null)
        {
            compiledstatement.close();
        }
        if (databaseconnection != null)
        {
            connectionsource.releaseConnection(databaseconnection);
        }
        throw exception;
    }

    public Object callBatchTasks(DatabaseConnection databaseconnection, boolean flag, Callable callable)
    {
        boolean flag1 = false;
        if (!databaseType.isBatchUseTransaction()) goto _L2; else goto _L1
_L1:
        Object obj1 = TransactionManager.callInTransaction(databaseconnection, flag, databaseType, callable);
_L4:
        return obj1;
_L2:
        boolean flag2 = databaseconnection.isAutoCommitSupported();
        flag1 = false;
        if (!flag2)
        {
            break MISSING_BLOCK_LABEL_81;
        }
        flag1 = databaseconnection.isAutoCommit();
        if (!flag1)
        {
            break MISSING_BLOCK_LABEL_81;
        }
        databaseconnection.setAutoCommit(false);
        logger.debug("disabled auto-commit on table {} before batch tasks", tableInfo.getTableName());
        Object obj = callable.call();
        obj1 = obj;
        if (flag1)
        {
            databaseconnection.setAutoCommit(true);
            logger.debug("re-enabled auto-commit on table {} after batch tasks", tableInfo.getTableName());
            return obj1;
        }
        if (true) goto _L4; else goto _L3
_L3:
        SQLException sqlexception;
        sqlexception;
        throw sqlexception;
        Exception exception;
        exception;
        if (flag1)
        {
            databaseconnection.setAutoCommit(true);
            logger.debug("re-enabled auto-commit on table {} after batch tasks", tableInfo.getTableName());
        }
        throw exception;
        Exception exception1;
        exception1;
        throw SqlExceptionUtil.create("Batch tasks callable threw non-SQL exception", exception1);
    }

    public int create(DatabaseConnection databaseconnection, Object obj, ObjectCache objectcache)
    {
        if (mappedInsert == null)
        {
            mappedInsert = MappedCreate.build(databaseType, tableInfo);
        }
        return mappedInsert.insert(databaseType, databaseconnection, obj, objectcache);
    }

    public int delete(DatabaseConnection databaseconnection, PreparedDelete prepareddelete)
    {
        CompiledStatement compiledstatement = prepareddelete.compile(databaseconnection, StatementBuilder.StatementType.DELETE);
        int i = compiledstatement.runUpdate();
        compiledstatement.close();
        return i;
        Exception exception;
        exception;
        compiledstatement.close();
        throw exception;
    }

    public int delete(DatabaseConnection databaseconnection, Object obj, ObjectCache objectcache)
    {
        if (mappedDelete == null)
        {
            mappedDelete = MappedDelete.build(databaseType, tableInfo);
        }
        return mappedDelete.delete(databaseconnection, obj, objectcache);
    }

    public int deleteById(DatabaseConnection databaseconnection, Object obj, ObjectCache objectcache)
    {
        if (mappedDelete == null)
        {
            mappedDelete = MappedDelete.build(databaseType, tableInfo);
        }
        return mappedDelete.deleteById(databaseconnection, obj, objectcache);
    }

    public int deleteIds(DatabaseConnection databaseconnection, Collection collection, ObjectCache objectcache)
    {
        return MappedDeleteCollection.deleteIds(databaseType, tableInfo, databaseconnection, collection, objectcache);
    }

    public int deleteObjects(DatabaseConnection databaseconnection, Collection collection, ObjectCache objectcache)
    {
        return MappedDeleteCollection.deleteObjects(databaseType, tableInfo, databaseconnection, collection, objectcache);
    }

    public int executeRaw(DatabaseConnection databaseconnection, String s, String as[])
    {
        CompiledStatement compiledstatement;
        logger.debug("running raw execute statement: {}", s);
        if (as.length > 0)
        {
            logger.trace("execute arguments: {}", as);
        }
        compiledstatement = databaseconnection.compileStatement(s, StatementBuilder.StatementType.EXECUTE, noFieldTypes, -1);
        int i;
        assignStatementArguments(compiledstatement, as);
        i = compiledstatement.runExecute();
        compiledstatement.close();
        return i;
        Exception exception;
        exception;
        compiledstatement.close();
        throw exception;
    }

    public int executeRawNoArgs(DatabaseConnection databaseconnection, String s)
    {
        logger.debug("running raw execute statement: {}", s);
        return databaseconnection.executeStatement(s, -1);
    }

    public RawRowMapper getRawRowMapper()
    {
        if (rawRowMapper == null)
        {
            rawRowMapper = new RawRowMapperImpl(tableInfo);
        }
        return rawRowMapper;
    }

    public GenericRowMapper getSelectStarRowMapper()
    {
        prepareQueryForAll();
        return preparedQueryForAll;
    }

    public boolean ifExists(DatabaseConnection databaseconnection, Object obj)
    {
        if (ifExistsQuery == null)
        {
            QueryBuilder querybuilder = new QueryBuilder(databaseType, tableInfo, dao);
            querybuilder.selectRaw(new String[] {
                "COUNT(*)"
            });
            querybuilder.where().eq(tableInfo.getIdField().getColumnName(), new SelectArg());
            ifExistsQuery = querybuilder.prepareStatementString();
            FieldType afieldtype[] = new FieldType[1];
            afieldtype[0] = tableInfo.getIdField();
            ifExistsFieldTypes = afieldtype;
        }
        long l = databaseconnection.queryForLong(ifExistsQuery, new Object[] {
            obj
        }, ifExistsFieldTypes);
        logger.debug("query of '{}' returned {}", ifExistsQuery, Long.valueOf(l));
        return l != 0L;
    }

    public volatile Object mapRow(DatabaseResults databaseresults)
    {
        return mapRow(databaseresults);
    }

    public String[] mapRow(DatabaseResults databaseresults)
    {
        int i = databaseresults.getColumnCount();
        String as[] = new String[i];
        for (int j = 0; j < i; j++)
        {
            as[j] = databaseresults.getString(j);
        }

        return as;
    }

    public List query(ConnectionSource connectionsource, PreparedStmt preparedstmt, ObjectCache objectcache)
    {
        SelectIterator selectiterator = buildIterator(null, connectionsource, preparedstmt, objectcache, -1);
        ArrayList arraylist;
        arraylist = new ArrayList();
        for (; selectiterator.hasNextThrow(); arraylist.add(selectiterator.nextThrow())) { }
        break MISSING_BLOCK_LABEL_54;
        Exception exception;
        exception;
        selectiterator.close();
        throw exception;
        logger.debug("query of '{}' returned {} results", preparedstmt.getStatement(), Integer.valueOf(arraylist.size()));
        selectiterator.close();
        return arraylist;
    }

    public List queryForAll(ConnectionSource connectionsource, ObjectCache objectcache)
    {
        prepareQueryForAll();
        return query(connectionsource, preparedQueryForAll, objectcache);
    }

    public long queryForCountStar(DatabaseConnection databaseconnection)
    {
        if (countStarQuery == null)
        {
            StringBuilder stringbuilder = new StringBuilder(64);
            stringbuilder.append("SELECT COUNT(*) FROM ");
            databaseType.appendEscapedEntityName(stringbuilder, tableInfo.getTableName());
            countStarQuery = stringbuilder.toString();
        }
        long l = databaseconnection.queryForLong(countStarQuery);
        logger.debug("query of '{}' returned {}", countStarQuery, Long.valueOf(l));
        return l;
    }

    public Object queryForFirst(DatabaseConnection databaseconnection, PreparedStmt preparedstmt, ObjectCache objectcache)
    {
        CompiledStatement compiledstatement = preparedstmt.compile(databaseconnection, StatementBuilder.StatementType.SELECT);
        DatabaseResults databaseresults1 = compiledstatement.runQuery(objectcache);
        DatabaseResults databaseresults = databaseresults1;
        Object obj;
        if (!databaseresults.first())
        {
            break MISSING_BLOCK_LABEL_83;
        }
        logger.debug("query-for-first of '{}' returned at least 1 result", preparedstmt.getStatement());
        obj = preparedstmt.mapRow(databaseresults);
        if (databaseresults != null)
        {
            databaseresults.close();
        }
        compiledstatement.close();
        return obj;
        logger.debug("query-for-first of '{}' returned at 0 results", preparedstmt.getStatement());
        if (databaseresults != null)
        {
            databaseresults.close();
        }
        compiledstatement.close();
        return null;
        Exception exception;
        exception;
        Exception exception1;
        databaseresults = null;
        exception1 = exception;
_L2:
        if (databaseresults != null)
        {
            databaseresults.close();
        }
        compiledstatement.close();
        throw exception1;
        exception1;
        if (true) goto _L2; else goto _L1
_L1:
    }

    public Object queryForId(DatabaseConnection databaseconnection, Object obj, ObjectCache objectcache)
    {
        if (mappedQueryForId == null)
        {
            mappedQueryForId = MappedQueryForId.build(databaseType, tableInfo, null);
        }
        return mappedQueryForId.execute(databaseconnection, obj, objectcache);
    }

    public long queryForLong(DatabaseConnection databaseconnection, PreparedStmt preparedstmt)
    {
        DatabaseResults databaseresults;
        CompiledStatement compiledstatement;
        databaseresults = null;
        compiledstatement = preparedstmt.compile(databaseconnection, StatementBuilder.StatementType.SELECT_LONG);
        long l;
        databaseresults = compiledstatement.runQuery(null);
        if (!databaseresults.first())
        {
            break MISSING_BLOCK_LABEL_61;
        }
        l = databaseresults.getLong(0);
        if (databaseresults != null)
        {
            databaseresults.close();
        }
        compiledstatement.close();
        return l;
        throw new SQLException((new StringBuilder("No result found in queryForLong: ")).append(preparedstmt.getStatement()).toString());
        Exception exception;
        exception;
        if (databaseresults != null)
        {
            databaseresults.close();
        }
        compiledstatement.close();
        throw exception;
    }

    public long queryForLong(DatabaseConnection databaseconnection, String s, String as[])
    {
        DatabaseResults databaseresults;
        databaseresults = null;
        logger.debug("executing raw query for long: {}", s);
        if (as.length > 0)
        {
            logger.trace("query arguments: {}", as);
        }
        CompiledStatement compiledstatement1 = databaseconnection.compileStatement(s, StatementBuilder.StatementType.SELECT, noFieldTypes, -1);
        CompiledStatement compiledstatement = compiledstatement1;
        long l;
        assignStatementArguments(compiledstatement, as);
        databaseresults = compiledstatement.runQuery(null);
        if (!databaseresults.first())
        {
            break MISSING_BLOCK_LABEL_112;
        }
        l = databaseresults.getLong(0);
        if (databaseresults != null)
        {
            databaseresults.close();
        }
        if (compiledstatement != null)
        {
            compiledstatement.close();
        }
        return l;
        throw new SQLException((new StringBuilder("No result found in queryForLong: ")).append(s).toString());
        Exception exception;
        exception;
_L2:
        if (databaseresults != null)
        {
            databaseresults.close();
        }
        if (compiledstatement != null)
        {
            compiledstatement.close();
        }
        throw exception;
        exception;
        databaseresults = null;
        compiledstatement = null;
        if (true) goto _L2; else goto _L1
_L1:
    }

    public GenericRawResults queryRaw(ConnectionSource connectionsource, String s, RawRowMapper rawrowmapper, String as[], ObjectCache objectcache)
    {
        DatabaseConnection databaseconnection;
        CompiledStatement compiledstatement;
        logger.debug("executing raw query for: {}", s);
        if (as.length > 0)
        {
            logger.trace("query arguments: {}", as);
        }
        databaseconnection = connectionsource.getReadOnlyConnection();
        compiledstatement = null;
        RawResultsImpl rawresultsimpl;
        compiledstatement = databaseconnection.compileStatement(s, StatementBuilder.StatementType.SELECT, noFieldTypes, -1);
        assignStatementArguments(compiledstatement, as);
        rawresultsimpl = new RawResultsImpl(connectionsource, databaseconnection, s, [Ljava/lang/String;, compiledstatement, new UserRawRowMapper(rawrowmapper, this), objectcache);
        return rawresultsimpl;
        Exception exception;
        exception;
        if (compiledstatement != null)
        {
            compiledstatement.close();
        }
        if (databaseconnection != null)
        {
            connectionsource.releaseConnection(databaseconnection);
        }
        throw exception;
    }

    public GenericRawResults queryRaw(ConnectionSource connectionsource, String s, DataType adatatype[], RawRowObjectMapper rawrowobjectmapper, String as[], ObjectCache objectcache)
    {
        DatabaseConnection databaseconnection;
        CompiledStatement compiledstatement;
        logger.debug("executing raw query for: {}", s);
        if (as.length > 0)
        {
            logger.trace("query arguments: {}", as);
        }
        databaseconnection = connectionsource.getReadOnlyConnection();
        compiledstatement = null;
        RawResultsImpl rawresultsimpl;
        compiledstatement = databaseconnection.compileStatement(s, StatementBuilder.StatementType.SELECT, noFieldTypes, -1);
        assignStatementArguments(compiledstatement, as);
        rawresultsimpl = new RawResultsImpl(connectionsource, databaseconnection, s, [Ljava/lang/String;, compiledstatement, new UserRawRowObjectMapper(rawrowobjectmapper, adatatype), objectcache);
        return rawresultsimpl;
        Exception exception;
        exception;
        if (compiledstatement != null)
        {
            compiledstatement.close();
        }
        if (databaseconnection != null)
        {
            connectionsource.releaseConnection(databaseconnection);
        }
        throw exception;
    }

    public GenericRawResults queryRaw(ConnectionSource connectionsource, String s, DataType adatatype[], String as[], ObjectCache objectcache)
    {
        DatabaseConnection databaseconnection;
        CompiledStatement compiledstatement;
        logger.debug("executing raw query for: {}", s);
        if (as.length > 0)
        {
            logger.trace("query arguments: {}", as);
        }
        databaseconnection = connectionsource.getReadOnlyConnection();
        compiledstatement = null;
        RawResultsImpl rawresultsimpl;
        compiledstatement = databaseconnection.compileStatement(s, StatementBuilder.StatementType.SELECT, noFieldTypes, -1);
        assignStatementArguments(compiledstatement, as);
        rawresultsimpl = new RawResultsImpl(connectionsource, databaseconnection, s, [Ljava/lang/Object;, compiledstatement, new ObjectArrayRowMapper(adatatype), objectcache);
        return rawresultsimpl;
        Exception exception;
        exception;
        if (compiledstatement != null)
        {
            compiledstatement.close();
        }
        if (databaseconnection != null)
        {
            connectionsource.releaseConnection(databaseconnection);
        }
        throw exception;
    }

    public GenericRawResults queryRaw(ConnectionSource connectionsource, String s, String as[], ObjectCache objectcache)
    {
        DatabaseConnection databaseconnection;
        CompiledStatement compiledstatement;
        logger.debug("executing raw query for: {}", s);
        if (as.length > 0)
        {
            logger.trace("query arguments: {}", as);
        }
        databaseconnection = connectionsource.getReadOnlyConnection();
        compiledstatement = null;
        RawResultsImpl rawresultsimpl;
        compiledstatement = databaseconnection.compileStatement(s, StatementBuilder.StatementType.SELECT, noFieldTypes, -1);
        assignStatementArguments(compiledstatement, as);
        rawresultsimpl = new RawResultsImpl(connectionsource, databaseconnection, s, [Ljava/lang/String;, compiledstatement, this, objectcache);
        return rawresultsimpl;
        Exception exception;
        exception;
        if (compiledstatement != null)
        {
            compiledstatement.close();
        }
        if (databaseconnection != null)
        {
            connectionsource.releaseConnection(databaseconnection);
        }
        throw exception;
    }

    public int refresh(DatabaseConnection databaseconnection, Object obj, ObjectCache objectcache)
    {
        if (mappedRefresh == null)
        {
            mappedRefresh = MappedRefresh.build(databaseType, tableInfo);
        }
        return mappedRefresh.executeRefresh(databaseconnection, obj, objectcache);
    }

    public int update(DatabaseConnection databaseconnection, PreparedUpdate preparedupdate)
    {
        CompiledStatement compiledstatement = preparedupdate.compile(databaseconnection, StatementBuilder.StatementType.UPDATE);
        int i = compiledstatement.runUpdate();
        compiledstatement.close();
        return i;
        Exception exception;
        exception;
        compiledstatement.close();
        throw exception;
    }

    public int update(DatabaseConnection databaseconnection, Object obj, ObjectCache objectcache)
    {
        if (mappedUpdate == null)
        {
            mappedUpdate = MappedUpdate.build(databaseType, tableInfo);
        }
        return mappedUpdate.update(databaseconnection, obj, objectcache);
    }

    public int updateId(DatabaseConnection databaseconnection, Object obj, Object obj1, ObjectCache objectcache)
    {
        if (mappedUpdateId == null)
        {
            mappedUpdateId = MappedUpdateId.build(databaseType, tableInfo);
        }
        return mappedUpdateId.execute(databaseconnection, obj, obj1, objectcache);
    }

    public int updateRaw(DatabaseConnection databaseconnection, String s, String as[])
    {
        CompiledStatement compiledstatement;
        logger.debug("running raw update statement: {}", s);
        if (as.length > 0)
        {
            logger.trace("update arguments: {}", as);
        }
        compiledstatement = databaseconnection.compileStatement(s, StatementBuilder.StatementType.UPDATE, noFieldTypes, -1);
        int i;
        assignStatementArguments(compiledstatement, as);
        i = compiledstatement.runUpdate();
        compiledstatement.close();
        return i;
        Exception exception;
        exception;
        compiledstatement.close();
        throw exception;
    }


    private class UserRawRowMapper
        implements GenericRowMapper
    {

        private String columnNames[];
        private final RawRowMapper mapper;
        private final GenericRowMapper stringRowMapper;

        private String[] getColumnNames(DatabaseResults databaseresults)
        {
            if (columnNames != null)
            {
                return columnNames;
            } else
            {
                columnNames = databaseresults.getColumnNames();
                return columnNames;
            }
        }

        public Object mapRow(DatabaseResults databaseresults)
        {
            String as[] = (String[])stringRowMapper.mapRow(databaseresults);
            return mapper.mapRow(getColumnNames(databaseresults), as);
        }

        public UserRawRowMapper(RawRowMapper rawrowmapper, GenericRowMapper genericrowmapper)
        {
            mapper = rawrowmapper;
            stringRowMapper = genericrowmapper;
        }
    }


    private class UserRawRowObjectMapper
        implements GenericRowMapper
    {

        private String columnNames[];
        private final DataType columnTypes[];
        private final RawRowObjectMapper mapper;

        private String[] getColumnNames(DatabaseResults databaseresults)
        {
            if (columnNames != null)
            {
                return columnNames;
            } else
            {
                columnNames = databaseresults.getColumnNames();
                return columnNames;
            }
        }

        public Object mapRow(DatabaseResults databaseresults)
        {
            int i = databaseresults.getColumnCount();
            Object aobj[] = new Object[i];
            int j = 0;
            while (j < i) 
            {
                if (j >= columnTypes.length)
                {
                    aobj[j] = null;
                } else
                {
                    aobj[j] = columnTypes[j].getDataPersister().resultToJava(null, databaseresults, j);
                }
                j++;
            }
            return mapper.mapRow(getColumnNames(databaseresults), columnTypes, aobj);
        }

        public UserRawRowObjectMapper(RawRowObjectMapper rawrowobjectmapper, DataType adatatype[])
        {
            mapper = rawrowobjectmapper;
            columnTypes = adatatype;
        }
    }


    private class ObjectArrayRowMapper
        implements GenericRowMapper
    {

        private final DataType columnTypes[];

        public volatile Object mapRow(DatabaseResults databaseresults)
        {
            return ((Object) (mapRow(databaseresults)));
        }

        public Object[] mapRow(DatabaseResults databaseresults)
        {
            int i = databaseresults.getColumnCount();
            Object aobj[] = new Object[i];
            int j = 0;
            while (j < i) 
            {
                DataType datatype;
                if (j >= columnTypes.length)
                {
                    datatype = DataType.STRING;
                } else
                {
                    datatype = columnTypes[j];
                }
                aobj[j] = datatype.getDataPersister().resultToJava(null, databaseresults, j);
                j++;
            }
            return aobj;
        }

        public ObjectArrayRowMapper(DataType adatatype[])
        {
            columnTypes = adatatype;
        }
    }

}
