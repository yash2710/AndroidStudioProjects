// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.j256.ormlite.dao;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.FieldType;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.GenericRowMapper;
import com.j256.ormlite.stmt.PreparedDelete;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.PreparedUpdate;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.UpdateBuilder;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.support.DatabaseConnection;
import com.j256.ormlite.support.DatabaseResults;
import com.j256.ormlite.table.ObjectFactory;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

// Referenced classes of package com.j256.ormlite.dao:
//            CloseableIterable, ForeignCollection, ObjectCache, RawRowMapper, 
//            CloseableWrappedIterable, CloseableIterator, GenericRawResults, RawRowObjectMapper

public interface Dao
    extends CloseableIterable
{

    public abstract void assignEmptyForeignCollection(Object obj, String s);

    public abstract Object callBatchTasks(Callable callable);

    public abstract void clearObjectCache();

    public abstract void closeLastIterator();

    public abstract void commit(DatabaseConnection databaseconnection);

    public abstract long countOf();

    public abstract long countOf(PreparedQuery preparedquery);

    public abstract int create(Object obj);

    public abstract Object createIfNotExists(Object obj);

    public abstract CreateOrUpdateStatus createOrUpdate(Object obj);

    public abstract int delete(PreparedDelete prepareddelete);

    public abstract int delete(Object obj);

    public abstract int delete(Collection collection);

    public abstract DeleteBuilder deleteBuilder();

    public abstract int deleteById(Object obj);

    public abstract int deleteIds(Collection collection);

    public abstract void endThreadConnection(DatabaseConnection databaseconnection);

    public transient abstract int executeRaw(String s, String as[]);

    public abstract int executeRawNoArgs(String s);

    public abstract Object extractId(Object obj);

    public abstract FieldType findForeignFieldType(Class class1);

    public abstract ConnectionSource getConnectionSource();

    public abstract Class getDataClass();

    public abstract ForeignCollection getEmptyForeignCollection(String s);

    public abstract ObjectCache getObjectCache();

    public abstract RawRowMapper getRawRowMapper();

    public abstract GenericRowMapper getSelectStarRowMapper();

    public abstract CloseableWrappedIterable getWrappedIterable();

    public abstract CloseableWrappedIterable getWrappedIterable(PreparedQuery preparedquery);

    public abstract boolean idExists(Object obj);

    public abstract boolean isAutoCommit();

    public abstract boolean isAutoCommit(DatabaseConnection databaseconnection);

    public abstract boolean isTableExists();

    public abstract boolean isUpdatable();

    public abstract CloseableIterator iterator();

    public abstract CloseableIterator iterator(int i);

    public abstract CloseableIterator iterator(PreparedQuery preparedquery);

    public abstract CloseableIterator iterator(PreparedQuery preparedquery, int i);

    public abstract Object mapSelectStarRow(DatabaseResults databaseresults);

    public abstract String objectToString(Object obj);

    public abstract boolean objectsEqual(Object obj, Object obj1);

    public abstract List query(PreparedQuery preparedquery);

    public abstract QueryBuilder queryBuilder();

    public abstract List queryForAll();

    public abstract List queryForEq(String s, Object obj);

    public abstract List queryForFieldValues(Map map);

    public abstract List queryForFieldValuesArgs(Map map);

    public abstract Object queryForFirst(PreparedQuery preparedquery);

    public abstract Object queryForId(Object obj);

    public abstract List queryForMatching(Object obj);

    public abstract List queryForMatchingArgs(Object obj);

    public abstract Object queryForSameId(Object obj);

    public transient abstract GenericRawResults queryRaw(String s, RawRowMapper rawrowmapper, String as[]);

    public transient abstract GenericRawResults queryRaw(String s, DataType adatatype[], RawRowObjectMapper rawrowobjectmapper, String as[]);

    public transient abstract GenericRawResults queryRaw(String s, DataType adatatype[], String as[]);

    public transient abstract GenericRawResults queryRaw(String s, String as[]);

    public transient abstract long queryRawValue(String s, String as[]);

    public abstract int refresh(Object obj);

    public abstract void rollBack(DatabaseConnection databaseconnection);

    public abstract void setAutoCommit(DatabaseConnection databaseconnection, boolean flag);

    public abstract void setAutoCommit(boolean flag);

    public abstract void setObjectCache(ObjectCache objectcache);

    public abstract void setObjectCache(boolean flag);

    public abstract void setObjectFactory(ObjectFactory objectfactory);

    public abstract DatabaseConnection startThreadConnection();

    public abstract int update(PreparedUpdate preparedupdate);

    public abstract int update(Object obj);

    public abstract UpdateBuilder updateBuilder();

    public abstract int updateId(Object obj, Object obj1);

    public transient abstract int updateRaw(String s, String as[]);
}
