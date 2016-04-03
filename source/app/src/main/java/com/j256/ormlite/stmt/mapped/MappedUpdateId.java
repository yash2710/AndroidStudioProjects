// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.j256.ormlite.stmt.mapped;

import com.j256.ormlite.dao.ObjectCache;
import com.j256.ormlite.db.DatabaseType;
import com.j256.ormlite.field.FieldType;
import com.j256.ormlite.logger.Logger;
import com.j256.ormlite.misc.SqlExceptionUtil;
import com.j256.ormlite.support.DatabaseConnection;
import com.j256.ormlite.table.TableInfo;
import java.sql.SQLException;

// Referenced classes of package com.j256.ormlite.stmt.mapped:
//            BaseMappedStatement

public class MappedUpdateId extends BaseMappedStatement
{

    private MappedUpdateId(TableInfo tableinfo, String s, FieldType afieldtype[])
    {
        super(tableinfo, s, afieldtype);
    }

    public static MappedUpdateId build(DatabaseType databasetype, TableInfo tableinfo)
    {
        FieldType fieldtype = tableinfo.getIdField();
        if (fieldtype == null)
        {
            throw new SQLException((new StringBuilder("Cannot update-id in ")).append(tableinfo.getDataClass()).append(" because it doesn't have an id field").toString());
        } else
        {
            StringBuilder stringbuilder = new StringBuilder(64);
            appendTableName(databasetype, stringbuilder, "UPDATE ", tableinfo.getTableName());
            stringbuilder.append("SET ");
            appendFieldColumnName(databasetype, stringbuilder, fieldtype, null);
            stringbuilder.append("= ? ");
            appendWhereFieldEq(databasetype, fieldtype, stringbuilder, null);
            return new MappedUpdateId(tableinfo, stringbuilder.toString(), new FieldType[] {
                fieldtype, fieldtype
            });
        }
    }

    private Object extractIdToFieldObject(Object obj)
    {
        return idField.extractJavaFieldToSqlArgValue(obj);
    }

    public int execute(DatabaseConnection databaseconnection, Object obj, Object obj1, ObjectCache objectcache)
    {
        Object aobj[];
        int i;
        Object obj2;
        Object obj3;
        try
        {
            aobj = new Object[2];
            aobj[0] = convertIdToFieldObject(obj1);
            aobj[1] = extractIdToFieldObject(obj);
            i = databaseconnection.update(statement, aobj, argFieldTypes);
        }
        catch (SQLException sqlexception)
        {
            throw SqlExceptionUtil.create((new StringBuilder("Unable to run update-id stmt on object ")).append(obj).append(": ").append(statement).toString(), sqlexception);
        }
        if (i <= 0)
        {
            break MISSING_BLOCK_LABEL_114;
        }
        if (objectcache == null)
        {
            break MISSING_BLOCK_LABEL_102;
        }
        obj2 = idField.extractJavaFieldValue(obj);
        obj3 = objectcache.updateId(clazz, obj2, obj1);
        if (obj3 == null || obj3 == obj)
        {
            break MISSING_BLOCK_LABEL_102;
        }
        idField.assignField(obj3, obj1, false, objectcache);
        idField.assignField(obj, obj1, false, objectcache);
        logger.debug("updating-id with statement '{}' and {} args, changed {} rows", statement, Integer.valueOf(2), Integer.valueOf(i));
        logger.trace("updating-id arguments: {}", ((Object) (aobj)));
        return i;
    }
}
