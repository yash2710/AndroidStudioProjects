// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.j256.ormlite.stmt.mapped;

import com.j256.ormlite.dao.ObjectCache;
import com.j256.ormlite.db.DatabaseType;
import com.j256.ormlite.field.FieldType;
import com.j256.ormlite.logger.Logger;
import com.j256.ormlite.support.DatabaseConnection;
import com.j256.ormlite.table.TableInfo;
import java.sql.SQLException;

// Referenced classes of package com.j256.ormlite.stmt.mapped:
//            BaseMappedQuery

public class MappedQueryForId extends BaseMappedQuery
{

    private final String label;

    protected MappedQueryForId(TableInfo tableinfo, String s, FieldType afieldtype[], FieldType afieldtype1[], String s1)
    {
        super(tableinfo, s, afieldtype, afieldtype1);
        label = s1;
    }

    public static MappedQueryForId build(DatabaseType databasetype, TableInfo tableinfo, FieldType fieldtype)
    {
        if (fieldtype == null)
        {
            fieldtype = tableinfo.getIdField();
            if (fieldtype == null)
            {
                throw new SQLException((new StringBuilder("Cannot query-for-id with ")).append(tableinfo.getDataClass()).append(" because it doesn't have an id field").toString());
            }
        }
        return new MappedQueryForId(tableinfo, buildStatement(databasetype, tableinfo, fieldtype), new FieldType[] {
            fieldtype
        }, tableinfo.getFieldTypes(), "query-for-id");
    }

    protected static String buildStatement(DatabaseType databasetype, TableInfo tableinfo, FieldType fieldtype)
    {
        StringBuilder stringbuilder = new StringBuilder(64);
        appendTableName(databasetype, stringbuilder, "SELECT * FROM ", tableinfo.getTableName());
        appendWhereFieldEq(databasetype, fieldtype, stringbuilder, null);
        return stringbuilder.toString();
    }

    private void logArgs(Object aobj[])
    {
        if (aobj.length > 0)
        {
            logger.trace("{} arguments: {}", label, ((Object) (aobj)));
        }
    }

    public Object execute(DatabaseConnection databaseconnection, Object obj, ObjectCache objectcache)
    {
        if (objectcache != null)
        {
            Object obj2 = objectcache.get(clazz, obj);
            if (obj2 != null)
            {
                return obj2;
            }
        }
        Object aobj[] = new Object[1];
        aobj[0] = convertIdToFieldObject(obj);
        Object obj1 = databaseconnection.queryForOne(statement, aobj, argFieldTypes, this, objectcache);
        if (obj1 == null)
        {
            logger.debug("{} using '{}' and {} args, got no results", label, statement, Integer.valueOf(1));
        } else
        {
            if (obj1 == DatabaseConnection.MORE_THAN_ONE)
            {
                logger.error("{} using '{}' and {} args, got >1 results", label, statement, Integer.valueOf(1));
                logArgs(aobj);
                throw new SQLException((new StringBuilder()).append(label).append(" got more than 1 result: ").append(statement).toString());
            }
            logger.debug("{} using '{}' and {} args, got 1 result", label, statement, Integer.valueOf(1));
        }
        logArgs(aobj);
        return obj1;
    }
}
