// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.j256.ormlite.support;

import com.j256.ormlite.dao.ObjectCache;
import com.j256.ormlite.field.FieldType;
import com.j256.ormlite.stmt.GenericRowMapper;
import java.sql.Savepoint;

// Referenced classes of package com.j256.ormlite.support:
//            CompiledStatement, GeneratedKeyHolder

public interface DatabaseConnection
{

    public static final int DEFAULT_RESULT_FLAGS = -1;
    public static final Object MORE_THAN_ONE = new Object();

    public abstract void close();

    public abstract void closeQuietly();

    public abstract void commit(Savepoint savepoint);

    public abstract CompiledStatement compileStatement(String s, com.j256.ormlite.stmt.StatementBuilder.StatementType statementtype, FieldType afieldtype[], int i);

    public abstract int delete(String s, Object aobj[], FieldType afieldtype[]);

    public abstract int executeStatement(String s, int i);

    public abstract int insert(String s, Object aobj[], FieldType afieldtype[], GeneratedKeyHolder generatedkeyholder);

    public abstract boolean isAutoCommit();

    public abstract boolean isAutoCommitSupported();

    public abstract boolean isClosed();

    public abstract boolean isTableExists(String s);

    public abstract long queryForLong(String s);

    public abstract long queryForLong(String s, Object aobj[], FieldType afieldtype[]);

    public abstract Object queryForOne(String s, Object aobj[], FieldType afieldtype[], GenericRowMapper genericrowmapper, ObjectCache objectcache);

    public abstract void rollback(Savepoint savepoint);

    public abstract void setAutoCommit(boolean flag);

    public abstract Savepoint setSavePoint(String s);

    public abstract int update(String s, Object aobj[], FieldType afieldtype[]);

}
