// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.j256.ormlite.support;

import com.j256.ormlite.dao.ObjectCache;
import com.j256.ormlite.field.FieldType;
import com.j256.ormlite.stmt.GenericRowMapper;
import java.sql.Savepoint;

// Referenced classes of package com.j256.ormlite.support:
//            DatabaseConnection, CompiledStatement, GeneratedKeyHolder

public class DatabaseConnectionProxy
    implements DatabaseConnection
{

    private final DatabaseConnection proxy;

    public DatabaseConnectionProxy(DatabaseConnection databaseconnection)
    {
        proxy = databaseconnection;
    }

    public void close()
    {
        if (proxy != null)
        {
            proxy.close();
        }
    }

    public void closeQuietly()
    {
        if (proxy != null)
        {
            proxy.closeQuietly();
        }
    }

    public void commit(Savepoint savepoint)
    {
        if (proxy != null)
        {
            proxy.commit(savepoint);
        }
    }

    public CompiledStatement compileStatement(String s, com.j256.ormlite.stmt.StatementBuilder.StatementType statementtype, FieldType afieldtype[], int i)
    {
        if (proxy == null)
        {
            return null;
        } else
        {
            return proxy.compileStatement(s, statementtype, afieldtype, i);
        }
    }

    public int delete(String s, Object aobj[], FieldType afieldtype[])
    {
        if (proxy == null)
        {
            return 0;
        } else
        {
            return proxy.delete(s, aobj, afieldtype);
        }
    }

    public int executeStatement(String s, int i)
    {
        if (proxy == null)
        {
            return 0;
        } else
        {
            return proxy.executeStatement(s, i);
        }
    }

    public int insert(String s, Object aobj[], FieldType afieldtype[], GeneratedKeyHolder generatedkeyholder)
    {
        if (proxy == null)
        {
            return 0;
        } else
        {
            return proxy.insert(s, aobj, afieldtype, generatedkeyholder);
        }
    }

    public boolean isAutoCommit()
    {
        if (proxy == null)
        {
            return false;
        } else
        {
            return proxy.isAutoCommit();
        }
    }

    public boolean isAutoCommitSupported()
    {
        if (proxy == null)
        {
            return false;
        } else
        {
            return proxy.isAutoCommitSupported();
        }
    }

    public boolean isClosed()
    {
        if (proxy == null)
        {
            return true;
        } else
        {
            return proxy.isClosed();
        }
    }

    public boolean isTableExists(String s)
    {
        if (proxy == null)
        {
            return false;
        } else
        {
            return proxy.isTableExists(s);
        }
    }

    public long queryForLong(String s)
    {
        if (proxy == null)
        {
            return 0L;
        } else
        {
            return proxy.queryForLong(s);
        }
    }

    public long queryForLong(String s, Object aobj[], FieldType afieldtype[])
    {
        if (proxy == null)
        {
            return 0L;
        } else
        {
            return proxy.queryForLong(s, aobj, afieldtype);
        }
    }

    public Object queryForOne(String s, Object aobj[], FieldType afieldtype[], GenericRowMapper genericrowmapper, ObjectCache objectcache)
    {
        if (proxy == null)
        {
            return null;
        } else
        {
            return proxy.queryForOne(s, aobj, afieldtype, genericrowmapper, objectcache);
        }
    }

    public void rollback(Savepoint savepoint)
    {
        if (proxy != null)
        {
            proxy.rollback(savepoint);
        }
    }

    public void setAutoCommit(boolean flag)
    {
        if (proxy != null)
        {
            proxy.setAutoCommit(flag);
        }
    }

    public Savepoint setSavePoint(String s)
    {
        if (proxy == null)
        {
            return null;
        } else
        {
            return proxy.setSavePoint(s);
        }
    }

    public int update(String s, Object aobj[], FieldType afieldtype[])
    {
        if (proxy == null)
        {
            return 0;
        } else
        {
            return proxy.update(s, aobj, afieldtype);
        }
    }
}
