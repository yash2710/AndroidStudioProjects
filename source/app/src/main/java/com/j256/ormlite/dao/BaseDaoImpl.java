// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.j256.ormlite.dao;

import com.j256.ormlite.db.DatabaseType;
import com.j256.ormlite.field.DataPersister;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.FieldType;
import com.j256.ormlite.misc.BaseDaoEnabled;
import com.j256.ormlite.misc.SqlExceptionUtil;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.GenericRowMapper;
import com.j256.ormlite.stmt.PreparedDelete;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.PreparedUpdate;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.SelectArg;
import com.j256.ormlite.stmt.StatementExecutor;
import com.j256.ormlite.stmt.UpdateBuilder;
import com.j256.ormlite.stmt.Where;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.support.DatabaseConnection;
import com.j256.ormlite.support.DatabaseResults;
import com.j256.ormlite.table.DatabaseTableConfig;
import com.j256.ormlite.table.ObjectFactory;
import com.j256.ormlite.table.TableInfo;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;

// Referenced classes of package com.j256.ormlite.dao:
//            Dao, ReferenceObjectCache, ObjectCache, CloseableIterator, 
//            CloseableWrappedIterableImpl, DaoManager, ForeignCollection, RawRowMapper, 
//            CloseableWrappedIterable, GenericRawResults, RawRowObjectMapper

public abstract class BaseDaoImpl
    implements Dao
{

    private static final ThreadLocal daoConfigLevelLocal = new _cls1();
    private static ReferenceObjectCache defaultObjectCache;
    protected ConnectionSource connectionSource;
    protected final Class dataClass;
    protected DatabaseType databaseType;
    private boolean initialized;
    protected CloseableIterator lastIterator;
    private ObjectCache objectCache;
    protected ObjectFactory objectFactory;
    protected StatementExecutor statementExecutor;
    protected DatabaseTableConfig tableConfig;
    protected TableInfo tableInfo;

    protected BaseDaoImpl(ConnectionSource connectionsource, DatabaseTableConfig databasetableconfig)
    {
        this(connectionsource, databasetableconfig.getDataClass(), databasetableconfig);
    }

    protected BaseDaoImpl(ConnectionSource connectionsource, Class class1)
    {
        this(connectionsource, class1, null);
    }

    private BaseDaoImpl(ConnectionSource connectionsource, Class class1, DatabaseTableConfig databasetableconfig)
    {
        dataClass = class1;
        tableConfig = databasetableconfig;
        if (connectionsource != null)
        {
            connectionSource = connectionsource;
            initialize();
        }
    }

    protected BaseDaoImpl(Class class1)
    {
        this(null, class1, null);
    }

    public static void clearAllInternalObjectCaches()
    {
        com/j256/ormlite/dao/BaseDaoImpl;
        JVM INSTR monitorenter ;
        if (defaultObjectCache != null)
        {
            defaultObjectCache.clearAll();
            defaultObjectCache = null;
        }
        com/j256/ormlite/dao/BaseDaoImpl;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    static Dao createDao(ConnectionSource connectionsource, DatabaseTableConfig databasetableconfig)
    {
        return new _cls5(connectionsource, databasetableconfig);
    }

    static Dao createDao(ConnectionSource connectionsource, Class class1)
    {
        return new _cls4(connectionsource, class1);
    }

    private CloseableIterator createIterator(int i)
    {
        com.j256.ormlite.stmt.SelectIterator selectiterator;
        try
        {
            selectiterator = statementExecutor.buildIterator(this, connectionSource, i, objectCache);
        }
        catch (Exception exception)
        {
            throw new IllegalStateException((new StringBuilder("Could not build iterator for ")).append(dataClass).toString(), exception);
        }
        return selectiterator;
    }

    private CloseableIterator createIterator(PreparedQuery preparedquery, int i)
    {
        com.j256.ormlite.stmt.SelectIterator selectiterator;
        try
        {
            selectiterator = statementExecutor.buildIterator(this, connectionSource, preparedquery, objectCache, i);
        }
        catch (SQLException sqlexception)
        {
            throw SqlExceptionUtil.create((new StringBuilder("Could not build prepared-query iterator for ")).append(dataClass).toString(), sqlexception);
        }
        return selectiterator;
    }

    private ForeignCollection makeEmptyForeignCollection(Object obj, String s)
    {
        checkForInitialized();
        Object obj1;
        FieldType afieldtype[];
        int i;
        if (obj == null)
        {
            obj1 = null;
        } else
        {
            obj1 = extractId(obj);
        }
        afieldtype = tableInfo.getFieldTypes();
        i = afieldtype.length;
        for (int j = 0; j < i; j++)
        {
            FieldType fieldtype = afieldtype[j];
            if (fieldtype.getColumnName().equals(s))
            {
                BaseForeignCollection baseforeigncollection = fieldtype.buildForeignCollection(obj, obj1);
                if (obj != null)
                {
                    fieldtype.assignField(obj, baseforeigncollection, true, null);
                }
                return baseforeigncollection;
            }
        }

        throw new IllegalArgumentException((new StringBuilder("Could not find a field named ")).append(s).toString());
    }

    private List queryForFieldValues(Map map, boolean flag)
    {
        checkForInitialized();
        QueryBuilder querybuilder = queryBuilder();
        Where where = querybuilder.where();
        Iterator iterator1 = map.entrySet().iterator();
        while (iterator1.hasNext()) 
        {
            java.util.Map.Entry entry = (java.util.Map.Entry)iterator1.next();
            Object obj = entry.getValue();
            Object obj1;
            if (flag)
            {
                obj1 = new SelectArg(obj);
            } else
            {
                obj1 = obj;
            }
            where.eq((String)entry.getKey(), obj1);
        }
        if (map.size() == 0)
        {
            return Collections.emptyList();
        } else
        {
            where.and(map.size());
            return querybuilder.query();
        }
    }

    private List queryForMatching(Object obj, boolean flag)
    {
        checkForInitialized();
        QueryBuilder querybuilder = queryBuilder();
        Where where = querybuilder.where();
        FieldType afieldtype[] = tableInfo.getFieldTypes();
        int i = afieldtype.length;
        int j = 0;
        int k = 0;
        while (j < i) 
        {
            FieldType fieldtype = afieldtype[j];
            Object obj1 = fieldtype.getFieldValueIfNotDefault(obj);
            int l;
            if (obj1 != null)
            {
                Object obj2;
                if (flag)
                {
                    obj2 = new SelectArg(obj1);
                } else
                {
                    obj2 = obj1;
                }
                where.eq(fieldtype.getColumnName(), obj2);
                l = k + 1;
            } else
            {
                l = k;
            }
            j++;
            k = l;
        }
        if (k == 0)
        {
            return Collections.emptyList();
        } else
        {
            where.and(k);
            return querybuilder.query();
        }
    }

    public void assignEmptyForeignCollection(Object obj, String s)
    {
        makeEmptyForeignCollection(obj, s);
    }

    public Object callBatchTasks(Callable callable)
    {
        DatabaseConnection databaseconnection;
        checkForInitialized();
        databaseconnection = connectionSource.getReadWriteConnection();
        Object obj;
        boolean flag = connectionSource.saveSpecialConnection(databaseconnection);
        obj = statementExecutor.callBatchTasks(databaseconnection, flag, callable);
        connectionSource.clearSpecialConnection(databaseconnection);
        connectionSource.releaseConnection(databaseconnection);
        return obj;
        Exception exception;
        exception;
        connectionSource.clearSpecialConnection(databaseconnection);
        connectionSource.releaseConnection(databaseconnection);
        throw exception;
    }

    protected void checkForInitialized()
    {
        if (!initialized)
        {
            throw new IllegalStateException("you must call initialize() before you can use the dao");
        } else
        {
            return;
        }
    }

    public void clearObjectCache()
    {
        if (objectCache != null)
        {
            objectCache.clear(dataClass);
        }
    }

    public void closeLastIterator()
    {
        if (lastIterator != null)
        {
            lastIterator.close();
            lastIterator = null;
        }
    }

    public CloseableIterator closeableIterator()
    {
        return iterator(-1);
    }

    public void commit(DatabaseConnection databaseconnection)
    {
        databaseconnection.commit(null);
    }

    public long countOf()
    {
        DatabaseConnection databaseconnection;
        checkForInitialized();
        databaseconnection = connectionSource.getReadOnlyConnection();
        long l = statementExecutor.queryForCountStar(databaseconnection);
        connectionSource.releaseConnection(databaseconnection);
        return l;
        Exception exception;
        exception;
        connectionSource.releaseConnection(databaseconnection);
        throw exception;
    }

    public long countOf(PreparedQuery preparedquery)
    {
        DatabaseConnection databaseconnection;
        checkForInitialized();
        if (preparedquery.getType() != com.j256.ormlite.stmt.StatementBuilder.StatementType.SELECT_LONG)
        {
            throw new IllegalArgumentException((new StringBuilder("Prepared query is not of type ")).append(com.j256.ormlite.stmt.StatementBuilder.StatementType.SELECT_LONG).append(", did you call QueryBuilder.setCountOf(true)?").toString());
        }
        databaseconnection = connectionSource.getReadOnlyConnection();
        long l = statementExecutor.queryForLong(databaseconnection, preparedquery);
        connectionSource.releaseConnection(databaseconnection);
        return l;
        Exception exception;
        exception;
        connectionSource.releaseConnection(databaseconnection);
        throw exception;
    }

    public int create(Object obj)
    {
        DatabaseConnection databaseconnection;
        checkForInitialized();
        if (obj == null)
        {
            return 0;
        }
        if (obj instanceof BaseDaoEnabled)
        {
            ((BaseDaoEnabled)obj).setDao(this);
        }
        databaseconnection = connectionSource.getReadWriteConnection();
        int i = statementExecutor.create(databaseconnection, obj, objectCache);
        connectionSource.releaseConnection(databaseconnection);
        return i;
        Exception exception;
        exception;
        connectionSource.releaseConnection(databaseconnection);
        throw exception;
    }

    public Object createIfNotExists(Object obj)
    {
        if (obj == null)
        {
            return null;
        }
        Object obj1 = queryForSameId(obj);
        if (obj1 == null)
        {
            create(obj);
            return obj;
        } else
        {
            return obj1;
        }
    }

    public Dao.CreateOrUpdateStatus createOrUpdate(Object obj)
    {
        if (obj == null)
        {
            return new Dao.CreateOrUpdateStatus(false, false, 0);
        }
        Object obj1 = extractId(obj);
        if (obj1 == null || !idExists(obj1))
        {
            return new Dao.CreateOrUpdateStatus(true, false, create(obj));
        } else
        {
            return new Dao.CreateOrUpdateStatus(false, true, update(obj));
        }
    }

    public int delete(PreparedDelete prepareddelete)
    {
        DatabaseConnection databaseconnection;
        checkForInitialized();
        databaseconnection = connectionSource.getReadWriteConnection();
        int i = statementExecutor.delete(databaseconnection, prepareddelete);
        connectionSource.releaseConnection(databaseconnection);
        return i;
        Exception exception;
        exception;
        connectionSource.releaseConnection(databaseconnection);
        throw exception;
    }

    public int delete(Object obj)
    {
        DatabaseConnection databaseconnection;
        checkForInitialized();
        if (obj == null)
        {
            return 0;
        }
        databaseconnection = connectionSource.getReadWriteConnection();
        int i = statementExecutor.delete(databaseconnection, obj, objectCache);
        connectionSource.releaseConnection(databaseconnection);
        return i;
        Exception exception;
        exception;
        connectionSource.releaseConnection(databaseconnection);
        throw exception;
    }

    public int delete(Collection collection)
    {
        DatabaseConnection databaseconnection;
        checkForInitialized();
        if (collection == null || collection.isEmpty())
        {
            return 0;
        }
        databaseconnection = connectionSource.getReadWriteConnection();
        int i = statementExecutor.deleteObjects(databaseconnection, collection, objectCache);
        connectionSource.releaseConnection(databaseconnection);
        return i;
        Exception exception;
        exception;
        connectionSource.releaseConnection(databaseconnection);
        throw exception;
    }

    public DeleteBuilder deleteBuilder()
    {
        checkForInitialized();
        return new DeleteBuilder(databaseType, tableInfo, this);
    }

    public int deleteById(Object obj)
    {
        DatabaseConnection databaseconnection;
        checkForInitialized();
        if (obj == null)
        {
            return 0;
        }
        databaseconnection = connectionSource.getReadWriteConnection();
        int i = statementExecutor.deleteById(databaseconnection, obj, objectCache);
        connectionSource.releaseConnection(databaseconnection);
        return i;
        Exception exception;
        exception;
        connectionSource.releaseConnection(databaseconnection);
        throw exception;
    }

    public int deleteIds(Collection collection)
    {
        DatabaseConnection databaseconnection;
        checkForInitialized();
        if (collection == null || collection.isEmpty())
        {
            return 0;
        }
        databaseconnection = connectionSource.getReadWriteConnection();
        int i = statementExecutor.deleteIds(databaseconnection, collection, objectCache);
        connectionSource.releaseConnection(databaseconnection);
        return i;
        Exception exception;
        exception;
        connectionSource.releaseConnection(databaseconnection);
        throw exception;
    }

    public void endThreadConnection(DatabaseConnection databaseconnection)
    {
        connectionSource.clearSpecialConnection(databaseconnection);
        connectionSource.releaseConnection(databaseconnection);
    }

    public transient int executeRaw(String s, String as[])
    {
        DatabaseConnection databaseconnection;
        checkForInitialized();
        databaseconnection = connectionSource.getReadWriteConnection();
        int i = statementExecutor.executeRaw(databaseconnection, s, as);
        connectionSource.releaseConnection(databaseconnection);
        return i;
        SQLException sqlexception;
        sqlexception;
        throw SqlExceptionUtil.create((new StringBuilder("Could not run raw execute statement ")).append(s).toString(), sqlexception);
        Exception exception;
        exception;
        connectionSource.releaseConnection(databaseconnection);
        throw exception;
    }

    public int executeRawNoArgs(String s)
    {
        DatabaseConnection databaseconnection;
        checkForInitialized();
        databaseconnection = connectionSource.getReadWriteConnection();
        int i = statementExecutor.executeRawNoArgs(databaseconnection, s);
        connectionSource.releaseConnection(databaseconnection);
        return i;
        SQLException sqlexception;
        sqlexception;
        throw SqlExceptionUtil.create((new StringBuilder("Could not run raw execute statement ")).append(s).toString(), sqlexception);
        Exception exception;
        exception;
        connectionSource.releaseConnection(databaseconnection);
        throw exception;
    }

    public Object extractId(Object obj)
    {
        checkForInitialized();
        FieldType fieldtype = tableInfo.getIdField();
        if (fieldtype == null)
        {
            throw new SQLException((new StringBuilder("Class ")).append(dataClass).append(" does not have an id field").toString());
        } else
        {
            return fieldtype.extractJavaFieldValue(obj);
        }
    }

    public FieldType findForeignFieldType(Class class1)
    {
        checkForInitialized();
        FieldType afieldtype[] = tableInfo.getFieldTypes();
        int i = afieldtype.length;
        for (int j = 0; j < i; j++)
        {
            FieldType fieldtype = afieldtype[j];
            if (fieldtype.getType() == class1)
            {
                return fieldtype;
            }
        }

        return null;
    }

    public ConnectionSource getConnectionSource()
    {
        return connectionSource;
    }

    public Class getDataClass()
    {
        return dataClass;
    }

    public ForeignCollection getEmptyForeignCollection(String s)
    {
        return makeEmptyForeignCollection(null, s);
    }

    public ObjectCache getObjectCache()
    {
        return objectCache;
    }

    public ObjectFactory getObjectFactory()
    {
        return objectFactory;
    }

    public RawRowMapper getRawRowMapper()
    {
        return statementExecutor.getRawRowMapper();
    }

    public GenericRowMapper getSelectStarRowMapper()
    {
        return statementExecutor.getSelectStarRowMapper();
    }

    public DatabaseTableConfig getTableConfig()
    {
        return tableConfig;
    }

    public TableInfo getTableInfo()
    {
        return tableInfo;
    }

    public CloseableWrappedIterable getWrappedIterable()
    {
        checkForInitialized();
        return new CloseableWrappedIterableImpl(new _cls2());
    }

    public CloseableWrappedIterable getWrappedIterable(final PreparedQuery preparedQuery)
    {
        checkForInitialized();
        return new CloseableWrappedIterableImpl(new _cls3());
    }

    public boolean idExists(Object obj)
    {
        DatabaseConnection databaseconnection = connectionSource.getReadOnlyConnection();
        boolean flag = statementExecutor.ifExists(databaseconnection, obj);
        connectionSource.releaseConnection(databaseconnection);
        return flag;
        Exception exception;
        exception;
        connectionSource.releaseConnection(databaseconnection);
        throw exception;
    }

    public void initialize()
    {
        if (!initialized) goto _L2; else goto _L1
_L1:
        return;
_L2:
        List list;
        int i;
        BaseDaoImpl basedaoimpl;
        if (connectionSource == null)
        {
            throw new IllegalStateException((new StringBuilder("connectionSource was never set on ")).append(getClass().getSimpleName()).toString());
        }
        databaseType = connectionSource.getDatabaseType();
        if (databaseType == null)
        {
            throw new IllegalStateException((new StringBuilder("connectionSource is getting a null DatabaseType in ")).append(getClass().getSimpleName()).toString());
        }
        FieldType afieldtype[];
        int j;
        int k;
        if (tableConfig == null)
        {
            tableInfo = new TableInfo(connectionSource, this, dataClass);
        } else
        {
            tableConfig.extractFieldTypes(connectionSource);
            tableInfo = new TableInfo(databaseType, this, tableConfig);
        }
        statementExecutor = new StatementExecutor(databaseType, tableInfo, this);
        list = (List)daoConfigLevelLocal.get();
        list.add(this);
        if (list.size() > 1) goto _L1; else goto _L3
_L3:
        i = 0;
_L7:
        if (i >= list.size())
        {
            break; /* Loop/switch isn't completed */
        }
        basedaoimpl = (BaseDaoImpl)list.get(i);
        DaoManager.registerDao(connectionSource, basedaoimpl);
        afieldtype = basedaoimpl.getTableInfo().getFieldTypes();
        j = afieldtype.length;
        k = 0;
_L5:
        if (k >= j)
        {
            break; /* Loop/switch isn't completed */
        }
        afieldtype[k].configDaoInformation(connectionSource, basedaoimpl.getDataClass());
        k++;
        if (true) goto _L5; else goto _L4
        SQLException sqlexception;
        sqlexception;
        DaoManager.unregisterDao(connectionSource, basedaoimpl);
        throw sqlexception;
        Exception exception;
        exception;
        list.clear();
        daoConfigLevelLocal.remove();
        throw exception;
_L4:
        basedaoimpl.initialized = true;
        i++;
        if (true) goto _L7; else goto _L6
_L6:
        list.clear();
        daoConfigLevelLocal.remove();
        return;
    }

    public boolean isAutoCommit()
    {
        DatabaseConnection databaseconnection = connectionSource.getReadWriteConnection();
        boolean flag = isAutoCommit(databaseconnection);
        connectionSource.releaseConnection(databaseconnection);
        return flag;
        Exception exception;
        exception;
        connectionSource.releaseConnection(databaseconnection);
        throw exception;
    }

    public boolean isAutoCommit(DatabaseConnection databaseconnection)
    {
        return databaseconnection.isAutoCommit();
    }

    public boolean isTableExists()
    {
        DatabaseConnection databaseconnection;
        checkForInitialized();
        databaseconnection = connectionSource.getReadOnlyConnection();
        boolean flag = databaseconnection.isTableExists(tableInfo.getTableName());
        connectionSource.releaseConnection(databaseconnection);
        return flag;
        Exception exception;
        exception;
        connectionSource.releaseConnection(databaseconnection);
        throw exception;
    }

    public boolean isUpdatable()
    {
        return tableInfo.isUpdatable();
    }

    public CloseableIterator iterator()
    {
        return iterator(-1);
    }

    public CloseableIterator iterator(int i)
    {
        checkForInitialized();
        lastIterator = createIterator(i);
        return lastIterator;
    }

    public CloseableIterator iterator(PreparedQuery preparedquery)
    {
        return iterator(preparedquery, -1);
    }

    public CloseableIterator iterator(PreparedQuery preparedquery, int i)
    {
        checkForInitialized();
        lastIterator = createIterator(preparedquery, i);
        return lastIterator;
    }

    public volatile Iterator iterator()
    {
        return iterator();
    }

    public Object mapSelectStarRow(DatabaseResults databaseresults)
    {
        return statementExecutor.getSelectStarRowMapper().mapRow(databaseresults);
    }

    public String objectToString(Object obj)
    {
        checkForInitialized();
        return tableInfo.objectToString(obj);
    }

    public boolean objectsEqual(Object obj, Object obj1)
    {
        checkForInitialized();
        FieldType afieldtype[] = tableInfo.getFieldTypes();
        int i = afieldtype.length;
        for (int j = 0; j < i; j++)
        {
            FieldType fieldtype = afieldtype[j];
            Object obj2 = fieldtype.extractJavaFieldValue(obj);
            Object obj3 = fieldtype.extractJavaFieldValue(obj1);
            if (!fieldtype.getDataPersister().dataIsEqual(obj2, obj3))
            {
                return false;
            }
        }

        return true;
    }

    public List query(PreparedQuery preparedquery)
    {
        checkForInitialized();
        return statementExecutor.query(connectionSource, preparedquery, objectCache);
    }

    public QueryBuilder queryBuilder()
    {
        checkForInitialized();
        return new QueryBuilder(databaseType, tableInfo, this);
    }

    public List queryForAll()
    {
        checkForInitialized();
        return statementExecutor.queryForAll(connectionSource, objectCache);
    }

    public List queryForEq(String s, Object obj)
    {
        return queryBuilder().where().eq(s, obj).query();
    }

    public List queryForFieldValues(Map map)
    {
        return queryForFieldValues(map, false);
    }

    public List queryForFieldValuesArgs(Map map)
    {
        return queryForFieldValues(map, true);
    }

    public Object queryForFirst(PreparedQuery preparedquery)
    {
        DatabaseConnection databaseconnection;
        checkForInitialized();
        databaseconnection = connectionSource.getReadOnlyConnection();
        Object obj = statementExecutor.queryForFirst(databaseconnection, preparedquery, objectCache);
        connectionSource.releaseConnection(databaseconnection);
        return obj;
        Exception exception;
        exception;
        connectionSource.releaseConnection(databaseconnection);
        throw exception;
    }

    public Object queryForId(Object obj)
    {
        DatabaseConnection databaseconnection;
        checkForInitialized();
        databaseconnection = connectionSource.getReadOnlyConnection();
        Object obj1 = statementExecutor.queryForId(databaseconnection, obj, objectCache);
        connectionSource.releaseConnection(databaseconnection);
        return obj1;
        Exception exception;
        exception;
        connectionSource.releaseConnection(databaseconnection);
        throw exception;
    }

    public List queryForMatching(Object obj)
    {
        return queryForMatching(obj, false);
    }

    public List queryForMatchingArgs(Object obj)
    {
        return queryForMatching(obj, true);
    }

    public Object queryForSameId(Object obj)
    {
        checkForInitialized();
        Object obj1;
        if (obj != null)
        {
            if ((obj1 = extractId(obj)) != null)
            {
                return queryForId(obj1);
            }
        }
        return null;
    }

    public transient GenericRawResults queryRaw(String s, RawRowMapper rawrowmapper, String as[])
    {
        checkForInitialized();
        GenericRawResults genericrawresults;
        try
        {
            genericrawresults = statementExecutor.queryRaw(connectionSource, s, rawrowmapper, as, objectCache);
        }
        catch (SQLException sqlexception)
        {
            throw SqlExceptionUtil.create((new StringBuilder("Could not perform raw query for ")).append(s).toString(), sqlexception);
        }
        return genericrawresults;
    }

    public transient GenericRawResults queryRaw(String s, DataType adatatype[], RawRowObjectMapper rawrowobjectmapper, String as[])
    {
        checkForInitialized();
        GenericRawResults genericrawresults;
        try
        {
            genericrawresults = statementExecutor.queryRaw(connectionSource, s, adatatype, rawrowobjectmapper, as, objectCache);
        }
        catch (SQLException sqlexception)
        {
            throw SqlExceptionUtil.create((new StringBuilder("Could not perform raw query for ")).append(s).toString(), sqlexception);
        }
        return genericrawresults;
    }

    public transient GenericRawResults queryRaw(String s, DataType adatatype[], String as[])
    {
        checkForInitialized();
        GenericRawResults genericrawresults;
        try
        {
            genericrawresults = statementExecutor.queryRaw(connectionSource, s, adatatype, as, objectCache);
        }
        catch (SQLException sqlexception)
        {
            throw SqlExceptionUtil.create((new StringBuilder("Could not perform raw query for ")).append(s).toString(), sqlexception);
        }
        return genericrawresults;
    }

    public transient GenericRawResults queryRaw(String s, String as[])
    {
        checkForInitialized();
        GenericRawResults genericrawresults;
        try
        {
            genericrawresults = statementExecutor.queryRaw(connectionSource, s, as, objectCache);
        }
        catch (SQLException sqlexception)
        {
            throw SqlExceptionUtil.create((new StringBuilder("Could not perform raw query for ")).append(s).toString(), sqlexception);
        }
        return genericrawresults;
    }

    public transient long queryRawValue(String s, String as[])
    {
        DatabaseConnection databaseconnection;
        checkForInitialized();
        databaseconnection = connectionSource.getReadOnlyConnection();
        long l = statementExecutor.queryForLong(databaseconnection, s, as);
        connectionSource.releaseConnection(databaseconnection);
        return l;
        SQLException sqlexception;
        sqlexception;
        throw SqlExceptionUtil.create((new StringBuilder("Could not perform raw value query for ")).append(s).toString(), sqlexception);
        Exception exception;
        exception;
        connectionSource.releaseConnection(databaseconnection);
        throw exception;
    }

    public int refresh(Object obj)
    {
        DatabaseConnection databaseconnection;
        checkForInitialized();
        if (obj == null)
        {
            return 0;
        }
        if (obj instanceof BaseDaoEnabled)
        {
            ((BaseDaoEnabled)obj).setDao(this);
        }
        databaseconnection = connectionSource.getReadOnlyConnection();
        int i = statementExecutor.refresh(databaseconnection, obj, objectCache);
        connectionSource.releaseConnection(databaseconnection);
        return i;
        Exception exception;
        exception;
        connectionSource.releaseConnection(databaseconnection);
        throw exception;
    }

    public void rollBack(DatabaseConnection databaseconnection)
    {
        databaseconnection.rollback(null);
    }

    public void setAutoCommit(DatabaseConnection databaseconnection, boolean flag)
    {
        databaseconnection.setAutoCommit(flag);
    }

    public void setAutoCommit(boolean flag)
    {
        DatabaseConnection databaseconnection = connectionSource.getReadWriteConnection();
        setAutoCommit(databaseconnection, flag);
        connectionSource.releaseConnection(databaseconnection);
        return;
        Exception exception;
        exception;
        connectionSource.releaseConnection(databaseconnection);
        throw exception;
    }

    public void setConnectionSource(ConnectionSource connectionsource)
    {
        connectionSource = connectionsource;
    }

    public void setObjectCache(ObjectCache objectcache)
    {
        if (objectcache == null)
        {
            if (objectCache != null)
            {
                objectCache.clear(dataClass);
                objectCache = null;
            }
            return;
        }
        if (objectCache != null && objectCache != objectcache)
        {
            objectCache.clear(dataClass);
        }
        if (tableInfo.getIdField() == null)
        {
            throw new SQLException((new StringBuilder("Class ")).append(dataClass).append(" must have an id field to enable the object cache").toString());
        } else
        {
            objectCache = objectcache;
            objectCache.registerClass(dataClass);
            return;
        }
    }

    public void setObjectCache(boolean flag)
    {
        if (!flag) goto _L2; else goto _L1
_L1:
        if (objectCache != null)
        {
            break MISSING_BLOCK_LABEL_93;
        }
        if (tableInfo.getIdField() == null)
        {
            throw new SQLException((new StringBuilder("Class ")).append(dataClass).append(" must have an id field to enable the object cache").toString());
        }
        com/j256/ormlite/dao/BaseDaoImpl;
        JVM INSTR monitorenter ;
        if (defaultObjectCache == null)
        {
            defaultObjectCache = ReferenceObjectCache.makeWeakCache();
        }
        objectCache = defaultObjectCache;
        com/j256/ormlite/dao/BaseDaoImpl;
        JVM INSTR monitorexit ;
        objectCache.registerClass(dataClass);
_L4:
        return;
        Exception exception;
        exception;
        throw exception;
_L2:
        if (objectCache != null)
        {
            objectCache.clear(dataClass);
            objectCache = null;
            return;
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    public void setObjectFactory(ObjectFactory objectfactory)
    {
        checkForInitialized();
        objectFactory = objectfactory;
    }

    public void setTableConfig(DatabaseTableConfig databasetableconfig)
    {
        tableConfig = databasetableconfig;
    }

    public DatabaseConnection startThreadConnection()
    {
        DatabaseConnection databaseconnection = connectionSource.getReadWriteConnection();
        connectionSource.saveSpecialConnection(databaseconnection);
        return databaseconnection;
    }

    public int update(PreparedUpdate preparedupdate)
    {
        DatabaseConnection databaseconnection;
        checkForInitialized();
        databaseconnection = connectionSource.getReadWriteConnection();
        int i = statementExecutor.update(databaseconnection, preparedupdate);
        connectionSource.releaseConnection(databaseconnection);
        return i;
        Exception exception;
        exception;
        connectionSource.releaseConnection(databaseconnection);
        throw exception;
    }

    public int update(Object obj)
    {
        DatabaseConnection databaseconnection;
        checkForInitialized();
        if (obj == null)
        {
            return 0;
        }
        databaseconnection = connectionSource.getReadWriteConnection();
        int i = statementExecutor.update(databaseconnection, obj, objectCache);
        connectionSource.releaseConnection(databaseconnection);
        return i;
        Exception exception;
        exception;
        connectionSource.releaseConnection(databaseconnection);
        throw exception;
    }

    public UpdateBuilder updateBuilder()
    {
        checkForInitialized();
        return new UpdateBuilder(databaseType, tableInfo, this);
    }

    public int updateId(Object obj, Object obj1)
    {
        DatabaseConnection databaseconnection;
        checkForInitialized();
        if (obj == null)
        {
            return 0;
        }
        databaseconnection = connectionSource.getReadWriteConnection();
        int i = statementExecutor.updateId(databaseconnection, obj, obj1, objectCache);
        connectionSource.releaseConnection(databaseconnection);
        return i;
        Exception exception;
        exception;
        connectionSource.releaseConnection(databaseconnection);
        throw exception;
    }

    public transient int updateRaw(String s, String as[])
    {
        DatabaseConnection databaseconnection;
        checkForInitialized();
        databaseconnection = connectionSource.getReadWriteConnection();
        int i = statementExecutor.updateRaw(databaseconnection, s, as);
        connectionSource.releaseConnection(databaseconnection);
        return i;
        SQLException sqlexception;
        sqlexception;
        throw SqlExceptionUtil.create((new StringBuilder("Could not run raw update statement ")).append(s).toString(), sqlexception);
        Exception exception;
        exception;
        connectionSource.releaseConnection(databaseconnection);
        throw exception;
    }




    private class _cls5 extends BaseDaoImpl
    {

        public final volatile Iterator iterator()
        {
            return iterator();
        }

        _cls5(ConnectionSource connectionsource, DatabaseTableConfig databasetableconfig)
        {
            super(connectionsource, databasetableconfig);
        }
    }


    private class _cls4 extends BaseDaoImpl
    {

        public final volatile Iterator iterator()
        {
            return iterator();
        }

        _cls4(ConnectionSource connectionsource, Class class1)
        {
            super(connectionsource, class1);
        }
    }


    private class _cls2
        implements CloseableIterable
    {

        final BaseDaoImpl this$0;

        public CloseableIterator closeableIterator()
        {
            CloseableIterator closeableiterator;
            try
            {
                closeableiterator = createIterator(-1);
            }
            catch (Exception exception)
            {
                throw new IllegalStateException((new StringBuilder("Could not build iterator for ")).append(dataClass).toString(), exception);
            }
            return closeableiterator;
        }

        public Iterator iterator()
        {
            return closeableIterator();
        }

        _cls2()
        {
            this$0 = BaseDaoImpl.this;
            super();
        }
    }


    private class _cls3
        implements CloseableIterable
    {

        final BaseDaoImpl this$0;
        final PreparedQuery val$preparedQuery;

        public CloseableIterator closeableIterator()
        {
            CloseableIterator closeableiterator;
            try
            {
                closeableiterator = createIterator(preparedQuery, -1);
            }
            catch (Exception exception)
            {
                throw new IllegalStateException((new StringBuilder("Could not build prepared-query iterator for ")).append(dataClass).toString(), exception);
            }
            return closeableiterator;
        }

        public Iterator iterator()
        {
            return closeableIterator();
        }

        _cls3()
        {
            this$0 = BaseDaoImpl.this;
            preparedQuery = preparedquery;
            super();
        }
    }


    private class _cls1 extends ThreadLocal
    {

        protected final volatile Object initialValue()
        {
            return initialValue();
        }

        protected final List initialValue()
        {
            return new ArrayList(10);
        }

        _cls1()
        {
        }
    }

}
