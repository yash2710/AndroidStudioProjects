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

public class MappedDelete extends BaseMappedStatement
{

    private MappedDelete(TableInfo tableinfo, String s, FieldType afieldtype[])
    {
        super(tableinfo, s, afieldtype);
    }

    public static MappedDelete build(DatabaseType databasetype, TableInfo tableinfo)
    {
        FieldType fieldtype = tableinfo.getIdField();
        if (fieldtype == null)
        {
            throw new SQLException((new StringBuilder("Cannot delete from ")).append(tableinfo.getDataClass()).append(" because it doesn't have an id field").toString());
        } else
        {
            StringBuilder stringbuilder = new StringBuilder(64);
            appendTableName(databasetype, stringbuilder, "DELETE FROM ", tableinfo.getTableName());
            appendWhereFieldEq(databasetype, fieldtype, stringbuilder, null);
            return new MappedDelete(tableinfo, stringbuilder.toString(), new FieldType[] {
                fieldtype
            });
        }
    }

    public int delete(DatabaseConnection databaseconnection, Object obj, ObjectCache objectcache)
    {
        int i;
        Object obj1;
        try
        {
            Object aobj[] = getFieldObjects(obj);
            i = databaseconnection.delete(statement, aobj, argFieldTypes);
            logger.debug("delete data with statement '{}' and {} args, changed {} rows", statement, Integer.valueOf(aobj.length), Integer.valueOf(i));
            if (aobj.length > 0)
            {
                logger.trace("delete arguments: {}", ((Object) (aobj)));
            }
        }
        catch (SQLException sqlexception)
        {
            throw SqlExceptionUtil.create((new StringBuilder("Unable to run delete stmt on object ")).append(obj).append(": ").append(statement).toString(), sqlexception);
        }
        if (i <= 0 || objectcache == null)
        {
            break MISSING_BLOCK_LABEL_95;
        }
        obj1 = idField.extractJavaFieldToSqlArgValue(obj);
        objectcache.remove(clazz, obj1);
        return i;
    }

    public int deleteById(DatabaseConnection databaseconnection, Object obj, ObjectCache objectcache)
    {
        int i;
        try
        {
            Object aobj[] = new Object[1];
            aobj[0] = convertIdToFieldObject(obj);
            i = databaseconnection.delete(statement, aobj, argFieldTypes);
            logger.debug("delete data with statement '{}' and {} args, changed {} rows", statement, Integer.valueOf(1), Integer.valueOf(i));
            logger.trace("delete arguments: {}", ((Object) (aobj)));
        }
        catch (SQLException sqlexception)
        {
            throw SqlExceptionUtil.create((new StringBuilder("Unable to run deleteById stmt on id ")).append(obj).append(": ").append(statement).toString(), sqlexception);
        }
        if (i <= 0 || objectcache == null)
        {
            break MISSING_BLOCK_LABEL_84;
        }
        objectcache.remove(clazz, obj);
        return i;
    }
}
